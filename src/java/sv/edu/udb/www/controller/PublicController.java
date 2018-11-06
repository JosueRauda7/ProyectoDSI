package sv.edu.udb.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.beans.Producto;
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
                    case "listaProductos":
                        listaProductos(request, response);
                        break;

                    case "otraPagina":
                        otraPagina(request, response);
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
            request.getRequestDispatcher("/subcategoria.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarProductos(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nombre = request.getParameter("nombre");
            String idCategoria = request.getParameter("categoria");
            List<Producto> producto = new ArrayList<>();
            List<Producto> productoOferta = new ArrayList<>();
            producto = ProductoModel.busquedaProductos(nombre, idCategoria);

            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listarProductos", producto);
            request.setAttribute("datoBusqueda", nombre);

            for (Producto p : producto) {
                if (clienteModel.ofertaProducto(p.getIdProducto()) != null) {
                    productoOferta.add(p);
                }
            }

            request.setAttribute("listarProductosOfertados", productoOferta);

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
            request.setAttribute("productosRelacionados", clienteModel.productosRelacionados(idpro));
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            if (clienteModel.ofertaProducto(idpro) == null) {
                Producto producto = new Producto();
                producto = clienteModel.verProducto(idpro);
                request.setAttribute("otrasImagenes", ProductoModel.otrasImagenesProducto(idpro, producto.getUrlImagen()));
                request.setAttribute("producto", clienteModel.verProducto(idpro));
                request.setAttribute("comentarios", clienteModel.listaComentarios(idpro));
                request.getRequestDispatcher("/producto.jsp").forward(request, response);
            } else {
                Producto producto = new Producto();
                Oferta oferta = new Oferta();
                oferta = clienteModel.ofertaProducto(idpro);
                producto = oferta.getProducto();
                request.setAttribute("otrasImagenes", ProductoModel.otrasImagenesProducto(idpro, producto.getUrlImagen()));
                request.setAttribute("oferta", clienteModel.ofertaProducto(idpro));
                request.setAttribute("comentarios", clienteModel.listaComentarios(idpro));
                request.getRequestDispatcher("/oferta.jsp").forward(request, response);
            }
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

    private void listaProductos(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idsubcat = Integer.parseInt(request.getParameter("idsubcat"));
            String precio1 = request.getParameter("precio1");
           
            String precio2 = request.getParameter("precio2");
            if (precio1 == null && precio2 == null) {
                precio1 = "0";
                precio2 = "1000";
            }
            int filas = clienteModel.countProducto(idsubcat, Double.parseDouble(precio1), Double.parseDouble(precio2));
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listaProductos", clienteModel.listaProductosSubCat(0, idsubcat,Double.parseDouble(precio1), Double.parseDouble(precio2))); request.setAttribute("listaOfertas", clienteModel.listaOfertasSubCat(0, idsubcat));
            request.setAttribute("pagina", 0);
            request.setAttribute("idsubcat", idsubcat);
            request.setAttribute("paginas", (int) filas / 10);
            request.getRequestDispatcher("/listaProductos.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void otraPagina(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idsubcat = Integer.parseInt(request.getParameter("idsubcat"));
            int pagina = Integer.parseInt(request.getParameter("pagina"));
            String precio1 = request.getParameter("precio1");            
            String precio2 = request.getParameter("precio2");
            if(precio1 ==null && precio2 ==null){
                precio1 ="0";
                precio2 = "1000";
            }
            int filas = clienteModel.countProducto(idsubcat,Double.parseDouble(precio1), Double.parseDouble(precio2));
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listaProductos", clienteModel.listaProductosSubCat(pagina * 9, idsubcat,Double.parseDouble(precio1), Double.parseDouble(precio2)));
            request.setAttribute("listaOfertas", clienteModel.listaOfertasSubCat(0, idsubcat));
            request.setAttribute("idsubcat", idsubcat);
            request.setAttribute("pagina", pagina);
            request.setAttribute("paginas", (int) filas / 9);
            request.getRequestDispatcher("/listaProductos.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
