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
import sv.edu.udb.www.beans.Comentario;
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
            if (request.getSession().getAttribute("usuario") != null || request.getSession().getAttribute("tipousuario") != null || request.getSession().getAttribute("nombreUser") != null) {
                if (request.getSession().getAttribute("usuario") == null || !request.getSession().getAttribute("tipousuario").toString().equals("2")) {
                    response.sendRedirect(request.getContextPath() + "/public.do?operacion=publicIndex");
                    return;
                }

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
                    case "buscarProductos":
                        buscarProductos(request, response);
                        break;
                    case "agregarProducto":
                        agregarProducto(request, response);
                        break;
                    case "eliminarArticulo":
                        eliminarArticulo(request, response);
                        break;
                    case "eliminarOferta":
                        eliminarOferta(request, response);
                        break;
                    case "verCarrito":
                        verCarrito(request, response);
                        break;
                    case "cantidadProducto":
                        cantidadProducto(request, response);
                        break;
                    case "agregarComentario":
                        agregarComentario(request, response);
                        break;
                    case "eliminarComentario":
                        eliminarComentario(request, response);
                        break;
                    case "modificarComentario":
                        modificarComentario(request, response);
                        break;
                    case "agregarOferta":
                        agregarOferta(request, response);
                        break;
                }
            } else {
                request.getRequestDispatcher("/public.do?operacion=publicIndex").forward(request, response);
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
            request.setAttribute("ultimasOfertas", clienteModel.ultimasOfertas());
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
            int idpro = Integer.parseInt(request.getParameter("idproduct"));
            request.setAttribute("productosRelacionados", clienteModel.productosRelacionados(idpro));
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            if (clienteModel.ofertaProducto(idpro) == null) {
                request.setAttribute("producto", clienteModel.verProducto(idpro));
                request.setAttribute("comentarios", clienteModel.listaComentarios(idpro));
                request.getRequestDispatcher("/cliente/producto.jsp").forward(request, response);
            } else {
                request.setAttribute("oferta", clienteModel.ofertaProducto(idpro));
                request.setAttribute("comentarios", clienteModel.listaComentarios(idpro));
                request.getRequestDispatcher("/cliente/oferta.jsp").forward(request, response);
            }
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarProductos(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nombre = request.getParameter("nombre");

            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            //request.setAttribute("listarProductos", ProductoModel.busquedaProductos(nombre));
            request.setAttribute("datoBusqueda", nombre);
            try {
                request.getRequestDispatcher("/cliente/resultadosBusqueda.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idproduct = Integer.parseInt(request.getParameter("idproduct"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            int user = (int) request.getSession().getAttribute("usuario");
            if (clienteModel.agregarProducto(user, idproduct, cantidad) > 0) {
                request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
                request.setAttribute("producto", clienteModel.verProducto(idproduct));
                request.getSession().setAttribute("pedidosProduc", clienteModel.listaCarrito((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("exito", "Has añadido este articulo a tu carrito.");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verProducto&idproduct=" + idproduct);
            } else {
                request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
                request.setAttribute("producto", clienteModel.verProducto(idproduct));
                request.getSession().setAttribute("pedidosProduc", clienteModel.listaCarrito((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("fracaso", "Ya no hay existencias de este producto");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verProducto&idproduct=" + idproduct);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarArticulo(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {

            int iddetallepedido = Integer.parseInt(request.getParameter("iddetalle"));
            String urlactual = "/" + request.getParameter("url");
            if (clienteModel.eliminarProductoCarrito(iddetallepedido) > 0) {
                if (urlactual.equals("/usuarios.do")) {
                    request.getSession().setAttribute("exito", "El item se ha eliminado exitosamente");
                    request.getSession().setAttribute("pedidosProduc", clienteModel.listaCarrito((int) request.getSession().getAttribute("usuario")));
                    request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                    request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                    response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=publicIndex");
                }
                request.getSession().setAttribute("exito", "El item se ha eliminado exitosamente");
                request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("pedidosProduc", clienteModel.listaCarrito((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                out.println("<script>window.location = document.referrer;</script>");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verCarrito(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.getRequestDispatcher("/cliente/Carrito.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cantidadProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            int iddetalle = Integer.parseInt(request.getParameter("iddetalle"));
            int idproduct = Integer.parseInt(request.getParameter("idproduc"));
            if (cantidad <= 0) {
                request.getSession().setAttribute("fracaso", "La cantidad debe ser mayor a 1");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verCarrito");
            } else {

                if (clienteModel.cantidadProducto(cantidad, iddetalle, idproduct) > 0) {
                    request.getSession().setAttribute("exito", "Item modificado exitosamente.");
                    request.getSession().setAttribute("pedidosProduc", clienteModel.listaCarrito((int) request.getSession().getAttribute("usuario")));
                    request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                    response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verCarrito");
                } else {
                    request.getSession().setAttribute("fracaso", "Existencias limitadas de este articulo");
                    response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verCarrito");
                }
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarComentario(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Obtener fecha
            Calendar c = Calendar.getInstance();
            String dia, mes, annio, fecha;
            dia = Integer.toString(c.get(Calendar.DATE));
            mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            annio = Integer.toString(c.get(Calendar.YEAR));
            fecha = annio + "-" + mes + "-" + dia;
            String horas, minutos, segundos, hora;
            horas = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
            minutos = Integer.toString(c.get(Calendar.MINUTE));
            segundos = Integer.toString(c.get(Calendar.SECOND));
            hora = horas + ":" + minutos + ":" + segundos;
            //fin obtener hora
            String comentarios = request.getParameter("comentario");
            int idproducto = Integer.parseInt(request.getParameter("idproducto"));
            int idusuario = (int) request.getSession().getAttribute("usuario");
            Comentario comentario = new Comentario();
            comentario.setComentario(comentarios);
            comentario.setFechaComentario(fecha);
            comentario.setHoraComentario(hora);
            comentario.setIdUsuario(idusuario);
            comentario.setIdProducto(idproducto);
            if (clienteModel.agregarComentario(comentario) > 0) {
                request.getSession().setAttribute("exito", "Comentario agregado exitosamente.");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verProducto&idproduct=" + idproducto);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarComentario(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idcomentario = Integer.parseInt(request.getParameter("idcomentario"));
            int idproducto = Integer.parseInt(request.getParameter("producto"));
            if (clienteModel.eliminarComentario(idcomentario) > 0) {
                request.getSession().setAttribute("exito", "Comentario eliminado exitosamente.");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verProducto&idproduct=" + idproducto);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarComentario(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idcomentario = Integer.parseInt(request.getParameter("idcomentario"));
            int idproducto = Integer.parseInt(request.getParameter("producto"));
            String comentario = request.getParameter("comentario");
            if (clienteModel.modificarComentario(idcomentario, comentario) > 0) {
                request.getSession().setAttribute("exito", "Comentario modificado exitosamente.");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verProducto&idproduct=" + idproducto);
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarOferta(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idoferta = Integer.parseInt(request.getParameter("idoferta"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            int idproduct = Integer.parseInt(request.getParameter("idproducto"));
            int user = (int) request.getSession().getAttribute("usuario");
            if (clienteModel.agregarOferta(user, idoferta, cantidad) > 0) {
                request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
                request.getSession().setAttribute("pedidosOfert", clienteModel.listaCarritoOfertas((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("exito", "Has añadido este articulo a tu carrito.");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verProducto&idproduct=" + idproduct);
            } else {
                request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
                request.getSession().setAttribute("pedidosOfert", clienteModel.listaCarritoOfertas((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("fracaso", "Ya no hay existencias de este producto");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verProducto&idproduct=" + idproduct);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarOferta(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {

            int iddetallepedido = Integer.parseInt(request.getParameter("iddetalle"));
            String urlactual = "/" + request.getParameter("url");
            if (clienteModel.eliminarOferta(iddetallepedido) > 0) {
                if (urlactual.equals("/usuarios.do")) {
                    request.getSession().setAttribute("exito", "El item se ha eliminado exitosamente");
                    request.getSession().setAttribute("pedidosOfert", clienteModel.listaCarritoOfertas((int) request.getSession().getAttribute("usuario")));
                    request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                    request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                    response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=publicIndex");
                }
                request.getSession().setAttribute("exito", "El item se ha eliminado exitosamente");
                request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("pedidosOfert", clienteModel.listaCarritoOfertas((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                out.println("<script>window.location = document.referrer;</script>");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
