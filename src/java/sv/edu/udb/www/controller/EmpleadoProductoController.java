/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.model.CategoriasModel;
import sv.edu.udb.www.model.ClientesModel;
import sv.edu.udb.www.model.ProductosModel;

/**
 *
 * @author ivanm
 */
@WebServlet(name = "EmpleadoProductoController", urlPatterns = {"/empleados.do"})
public class EmpleadoProductoController extends HttpServlet {

    ProductosModel modeloProducto = new ProductosModel();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CategoriasModel CategoriaModel = new CategoriasModel();
        ProductosModel ProductoModel = new ProductosModel();

        ClientesModel clienteModel = new ClientesModel();
        try {

            String operacion = request.getParameter("operacion");
            if (request.getSession().getAttribute("usuario") != null || request.getSession().getAttribute("tipousuario") != null || request.getSession().getAttribute("nombreUser") != null) {
                if (request.getSession().getAttribute("usuario") == null || !request.getSession().getAttribute("tipousuario").toString().equals("4")) {
                    response.sendRedirect(request.getContextPath()+"/public.do?operacion=publicIndex");
                    return;
                }
                switch (operacion) {
                    case "listar":
                        listar(request, response);
                        break;
                    case "inicio":
                        request.getRequestDispatcher("/empleadoProducto/inicioEmpresaProducto.jsp").forward(request, response);
                        break;
                    case "aceptarRechazar":
                        aceptarRechazar(request, response);
                        break;

                    default:
                        request.getRequestDispatcher("/error404.jsp").forward(request, response);
                        break;
                }
            } else {
                request.getRequestDispatcher("/public.do?operacion=publicIndex").forward(request, response);
            }
        }  finally {
            out.close();
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

    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int estado = Integer.parseInt(request.getParameter("estado"));
            request.setAttribute("listarProducto", modeloProducto.listarProducto(estado));
            request.setAttribute("listarImagenes", modeloProducto.listarImagenesProducto());
            request.setAttribute("tab", estado);
            try {
                request.getRequestDispatcher("/empleadoProducto/listaProductos.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aceptarRechazar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int codigo = Integer.parseInt(request.getParameter("id"));
            int estado = Integer.parseInt(request.getParameter("estado"));
            if (modeloProducto.rechazarAceptarProducto(codigo, estado) == 0) {
                request.getSession().setAttribute("fracaso", "Ocurrio un error, no se pudo aplicar la acción");
            } else {
                request.getSession().setAttribute("exito", "Producto calificado.");
            }

            request.setAttribute("listarProducto", modeloProducto.listarProducto(1));
            try {
                request.getRequestDispatcher("/empleadoProducto/listaProductos.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
