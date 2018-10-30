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
import sv.edu.udb.www.model.EmpresasModel;
import sv.edu.udb.www.model.ProductosModel;
import sv.edu.udb.www.model.SubCategoriasModel;

/**
 *
 * @author Emerson Torres
 */
@WebServlet(name = "PublicController", urlPatterns = {"/public.do"})
public class PublicController extends HttpServlet {

    CategoriasModel CategoriaModel = new CategoriasModel();
    ProductosModel ProductoModel = new ProductosModel();
    SubCategoriasModel subcategoriaModel = new SubCategoriasModel();
    ClientesModel clienteModel = new ClientesModel();
    EmpresasModel empresasModel = new EmpresasModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("operacion");
            if (request.getSession().getAttribute("usuario") == null || request.getSession().getAttribute("tipousuario") == null || request.getSession().getAttribute("nombreUser") == null) {
                switch (operacion) {
                    case "publicIndex":
                        publicIndex(request, response);
                        break;
                    case "vercategoria":
                        vercategoria(request, response);
                        break;
                    case "buscarProductos":
                        buscarProductos(request, response);
                        break;
                    case "verProducto":
                        verProducto(request, response);
                        break;
                    case "verEmpresas":
                        listaEmpresas(request, response);
                        break;
                }
            } else {
                switch (Integer.parseInt(request.getSession().getAttribute("tipousuario").toString())) {
                    case 1:
                        request.getRequestDispatcher("/administrador/inicioAdmin.jsp").forward(request, response);
                        break;
                    case 2:
                        request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
                        request.setAttribute("ultimosProductos", ProductoModel.listaUltimosProductos());
                        request.getSession().setAttribute("estado", clienteModel.estadoPedido((int) request.getSession().getAttribute("usuario")));
                        request.getSession().setAttribute("pedidosProduc", clienteModel.listaCarrito((int) request.getSession().getAttribute("usuario")));
                        request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));

                        request.getRequestDispatcher("/cliente/index.jsp").forward(request, response);
                        break;
                    case 3:
                        //Aun no existe
                        request.getRequestDispatcher("/marketing/inicioMarketing.jsp").forward(request, response);
                        break;
                    case 4:
                        request.getRequestDispatcher("/empleadoProducto/inicioEmpresaProducto").forward(request, response);
                        break;
                    case 5:
                        request.getRequestDispatcher("/empresa/inicioEmpresa.jsp").forward(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void publicIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("ultimosProductos", ProductoModel.listaUltimosProductos());
            request.setAttribute("ultimasOfertas", clienteModel.ultimasOfertas());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void vercategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idcat = Integer.parseInt(request.getParameter("idcat"));
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listaSubcategoria", subcategoriaModel.listarporCategoria(idcat));
            request.setAttribute("nombreCategoria", CategoriaModel.nombreCaterogia(idcat));
            request.getRequestDispatcher("subcategoria.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarProductos(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nombre = request.getParameter("nombre");
            String idCategoria = request.getParameter("categoria");

            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listarProductos", ProductoModel.busquedaProductos(nombre, idCategoria));
            request.setAttribute("datoBusqueda", nombre);
            try {
                request.getRequestDispatcher("/resultadosBusqueda.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void verProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idpro = Integer.parseInt(request.getParameter("idproduct"));
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("producto", clienteModel.verProducto(idpro));
            request.setAttribute("comentarios", clienteModel.listaComentarios(idpro));
            request.getRequestDispatcher("/producto.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void listaEmpresas(HttpServletRequest request, HttpServletResponse response) {
        try {
           
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listaEmpresas", empresasModel.listarEmpresas());
            
            request.getRequestDispatcher("empresas.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
