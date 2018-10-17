/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Pedido;
import sv.edu.udb.www.model.CategoriasModel;
import sv.edu.udb.www.model.ClientesModel;
import sv.edu.udb.www.model.ProductosModel;
import sv.edu.udb.www.model.SubCategoriasModel;

/**
 *
 * @author Emerson Torres
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/clientes.do"})
public class ClienteController extends HttpServlet {

    CategoriasModel CategoriaModel = new CategoriasModel();
    ProductosModel ProductoModel = new ProductosModel();
    ClientesModel clienteModel = new ClientesModel();
    SubCategoriasModel subcategoriaModel = new SubCategoriasModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("operacion");
            switch (operacion) {
                case "publicIndex":
                    publicIndexClien(request, response);
                    break;
                case "versubcategoria":
                    vercategoriaClien(request, response);
                    break;
                case "crearCarrito":
                    crearCarrito(request, response);
                    break;
                case "verProducto":
                    verProducto(request, response);
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void publicIndexClien(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("ultimosProductos", ProductoModel.listaUltimosProductos());
            request.getRequestDispatcher("/cliente/index.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void vercategoriaClien(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idcat = Integer.parseInt(request.getParameter("idcat"));
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listaSubcategoria", subcategoriaModel.listarporCategoria(idcat));
            request.setAttribute("nombreCategoria", CategoriaModel.nombreCaterogia(idcat));
            request.getRequestDispatcher("/cliente/subcategoria.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearCarrito(HttpServletRequest request, HttpServletResponse response) {
        try {
            Calendar c = Calendar.getInstance();
            String dia, mes, annio, fecha;
            dia = Integer.toString(c.get(Calendar.DATE));
            mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            annio = Integer.toString(c.get(Calendar.YEAR));
            fecha = annio + "-" + mes + "-" + dia;
            Pedido pedido = new Pedido();
            pedido.setFechaCompra(fecha);
            pedido.setIdEstadoCompra(1);
            pedido.setIdUsuario((int) request.getSession().getAttribute("usuario"));
            if (clienteModel.crearCarrito(pedido) > 0) {
                request.getSession().setAttribute("estado", clienteModel.estadoPedido((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("exito", "Tu carrito se ha creado exitosamente");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=publicIndex");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idpro= Integer.parseInt(request.getParameter("idproduct"));
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("producto", clienteModel.verProducto(idpro));
            request.getRequestDispatcher("/cliente/producto.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
