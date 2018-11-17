/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Comentario;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.beans.Pedido;
import sv.edu.udb.www.beans.Producto;
import sv.edu.udb.www.beans.Sugerencia;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.model.CategoriasModel;
import sv.edu.udb.www.model.ClientesModel;
import sv.edu.udb.www.model.ProductosModel;
import sv.edu.udb.www.model.SubCategoriasModel;
import sv.edu.udb.www.model.SugerenciasModel;
import sv.edu.udb.www.model.UsuariosModel;

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
    UsuariosModel usuario = new UsuariosModel();
    SugerenciasModel sugerenciasModel = new SugerenciasModel();

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
                    case "cantidadOfertas":
                        cantidadOferta(request, response);
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
                    case "listaProductos":
                        listaProductos(request, response);
                        break;
                    case "otraPagina":
                        otraPagina(request, response);
                        break;
                    case "otraPagina1":
                        otraPagina1(request, response);
                        break;
                    case "listaPedidos":
                        listaPedidos(request, response);
                        break;
                    case "carritoPasado":
                        carritoPasado(request, response);
                        break;
                    case "pagoPedido":
                        pagoPedido(request, response);
                        break;
                    case "confirmarPedido":
                        confirmarPedido(request, response);
                        break;
                    case "cancelarPedido":
                        cancelarPedido(request, response);
                        break;
                    case "acercaDe":
                        acercaDe(request, response);
                        break;
                    case "sugerencias":
                        sugerencias(request, response);
                        break;
                    case "agregarSugerencia":
                        agregarSugerencia(request, response);
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
            String horas, minutos, segundos, hora;
            horas = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
            minutos = Integer.toString(c.get(Calendar.MINUTE));
            segundos = Integer.toString(c.get(Calendar.SECOND));
            hora = horas + ":" + minutos + ":" + segundos;
            Pedido pedido = new Pedido();
            pedido.setFechaCompra(fecha);
            pedido.setHoraCompra(hora);
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
                Producto producto = new Producto();
                producto = clienteModel.verProducto(idpro);
                request.setAttribute("otrasImagenes", ProductoModel.otrasImagenesProducto(idpro, producto.getUrlImagen()));
                request.setAttribute("producto", clienteModel.verProducto(idpro));
                request.setAttribute("comentarios", clienteModel.listaComentarios(idpro));
                request.getRequestDispatcher("/cliente/producto.jsp").forward(request, response);
            } else {
                Producto producto = new Producto();
                Oferta oferta = new Oferta();
                oferta = clienteModel.ofertaProducto(idpro);
                producto = oferta.getProducto();
                request.setAttribute("otrasImagenes", ProductoModel.otrasImagenesProducto(idpro, producto.getUrlImagen()));
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
            String idCategoria = request.getParameter("categoria");
            List<Producto> producto = new ArrayList<>();
            List<Producto> producto1 = new ArrayList<>();
            List<Oferta> productoOferta = new ArrayList<>();
            producto = ProductoModel.busquedaProductos(nombre, idCategoria);
            producto1 = ProductoModel.busquedaProductos1(0, nombre, idCategoria);

            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listarProductos", producto1);
            request.setAttribute("datoBusqueda", nombre);
            request.setAttribute("categoria", idCategoria);
            request.setAttribute("pagina", 0);
            request.setAttribute("paginas", (int) producto.size() / 9);

            for (Producto p : producto) {
                if (clienteModel.ofertaProducto(p.getIdProducto()) != null) {
                    productoOferta.add(clienteModel.ofertaProducto(p.getIdProducto()));
                }
            }

            request.setAttribute("listarProductosOfertados", productoOferta);
            request.setAttribute("cantidadOfertas", productoOferta.size());

            try {
                request.getRequestDispatcher("/cliente/resultadosBusqueda.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void otraPagina1(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nombre = request.getParameter("nombre");
            String idCategoria = request.getParameter("categoria");
            int pagina = Integer.parseInt(request.getParameter("pagina"));

            List<Producto> producto = new ArrayList<>();
            List<Producto> producto1 = new ArrayList<>();
            List<Oferta> productoOferta = new ArrayList<>();

            producto = ProductoModel.busquedaProductos(nombre, idCategoria);
            producto1 = ProductoModel.busquedaProductos1(pagina * 9, nombre, idCategoria);

            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listarProductos", producto1);

            int filas = producto.size();

            request.setAttribute("datoBusqueda", nombre);
            request.setAttribute("categoria", idCategoria);

            request.setAttribute("pagina", pagina);

            request.setAttribute("paginas", (int) filas / 9);

            for (Producto p : producto) {
                if (clienteModel.ofertaProducto(p.getIdProducto()) != null) {
                    productoOferta.add(clienteModel.ofertaProducto(p.getIdProducto()));
                }
            }

            request.setAttribute("listarProductosOfertados", productoOferta);
            request.setAttribute("cantidadOfertas", productoOferta.size());

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
            int estado = (int) request.getSession().getAttribute("estado");

            if (estado != 1) {
                request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
                request.setAttribute("producto", clienteModel.verProducto(idproduct));
                request.getSession().setAttribute("pedidosProduc", clienteModel.listaCarrito((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("fracaso", "Para adquirir este producto, debes crear tu carretilla.");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verProducto&idproduct=" + idproduct);
            } else {

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
        try (PrintWriter out = response.getWriter()) {
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            int iddetalle = Integer.parseInt(request.getParameter("iddetalle"));
            int idproduct = Integer.parseInt(request.getParameter("idproduc"));
            if (cantidad <= 0) {
                request.getSession().setAttribute("fracaso", "La cantidad debe ser mayor a 1");
                out.println("<script>window.location = document.referrer;</script>");
            } else {

                if (clienteModel.cantidadProducto(cantidad, iddetalle, idproduct) > 0) {
                    request.getSession().setAttribute("exito", "Item modificado exitosamente.");
                    request.getSession().setAttribute("pedidosProduc", clienteModel.listaCarrito((int) request.getSession().getAttribute("usuario")));
                    request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                    out.println("<script>window.location = document.referrer;</script>");
                } else {
                    request.getSession().setAttribute("fracaso", "Existencias limitadas de este articulo");
                    out.println("<script>window.location = document.referrer;</script>");
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
            int estado = (int) request.getSession().getAttribute("estado");

            if (estado != 1) {
                request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
                request.getSession().setAttribute("pedidosOfert", clienteModel.listaCarritoOfertas((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("fracaso", "Para adquirir esta oferta, debes crear tu carretilla.");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=verProducto&idproduct=" + idproduct);

            } else {
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
            int filasOfer = clienteModel.countOferta(idsubcat, Double.parseDouble(precio1), Double.parseDouble(precio2));

            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listaProductos", clienteModel.listaProductosSubCat(0, idsubcat, Double.parseDouble(precio1), Double.parseDouble(precio2)));
            request.setAttribute("listaOfertas", clienteModel.listaOfertasSubCat(0, idsubcat, Double.parseDouble(precio1), Double.parseDouble(precio2)));
            request.setAttribute("precio1", precio1);
            request.setAttribute("precio2", precio2);
            request.setAttribute("pagina", 0);
            request.setAttribute("paginaOfer", 0);
            request.setAttribute("idsubcat", idsubcat);
            request.setAttribute("paginas", (int) filas / 9);
            request.setAttribute("paginasOfert", (int) filasOfer / 9);
            request.getRequestDispatcher("/cliente/listaProductos.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void otraPagina(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idsubcat = Integer.parseInt(request.getParameter("idsubcat"));
            int pagina = Integer.parseInt(request.getParameter("pagina"));
            String compagiofer = request.getParameter("compagiOfer");

            int paginaOfer;
            if (request.getParameter("paginaOfer") == null) {
                paginaOfer = 0;
            } else {
                paginaOfer = Integer.parseInt(request.getParameter("paginaOfer"));
            }
            String precio1 = request.getParameter("precio1");
            String precio2 = request.getParameter("precio2");
            if (precio1 == null && precio2 == null) {
                precio1 = "0";
                precio2 = "1000";
            }
            int filas = clienteModel.countProducto(idsubcat, Double.parseDouble(precio1), Double.parseDouble(precio2));
            int filasOfer = clienteModel.countOferta(idsubcat, Double.parseDouble(precio1), Double.parseDouble(precio2));
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listaProductos", clienteModel.listaProductosSubCat(pagina * 9, idsubcat, Double.parseDouble(precio1), Double.parseDouble(precio2)));
            request.setAttribute("listaOfertas", clienteModel.listaOfertasSubCat(paginaOfer * 9, idsubcat, Double.parseDouble(precio1), Double.parseDouble(precio2)));
            request.setAttribute("idsubcat", idsubcat);
            request.setAttribute("pagina", pagina);
            request.setAttribute("paginaOfer", paginaOfer);
            request.setAttribute("precio1", precio1);
            request.setAttribute("precio2", precio2);
            request.setAttribute("compagiOfer", compagiofer);
            request.setAttribute("paginas", (int) filas / 9);
            request.setAttribute("paginasOfert", (int) filasOfer / 9);
            request.getRequestDispatcher("/cliente/listaProductos.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cantidadOferta(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            int iddetalle = Integer.parseInt(request.getParameter("iddetalle"));
            int idproduct = Integer.parseInt(request.getParameter("idproduc"));
            if (cantidad <= 0) {
                request.getSession().setAttribute("fracaso", "La cantidad debe ser mayor a 1");
                out.println("<script>window.location = document.referrer;</script>");;
            } else {

                if (clienteModel.cantidadOferta(cantidad, iddetalle, idproduct) > 0) {
                    request.getSession().setAttribute("exito", "Item modificado exitosamente.");
                    request.getSession().setAttribute("pedidosOfert", clienteModel.listaCarritoOfertas((int) request.getSession().getAttribute("usuario")));
                    request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));
                    out.println("<script>window.location = document.referrer;</script>");
                } else {
                    request.getSession().setAttribute("fracaso", "Existencias limitadas de este articulo");
                    out.println("<script>window.location = document.referrer;</script>");
                }
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listaPedidos(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idusuario = (int) request.getSession().getAttribute("usuario");
            request.setAttribute("listaPedidos", clienteModel.listaPedidos(idusuario));
            request.getRequestDispatcher("/cliente/listaPedidos.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carritoPasado(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idpedido = Integer.parseInt(request.getParameter("idpedido"));
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.setAttribute("listaoferPasado", clienteModel.listaCarritoPasadoOfertas(idpedido));
            request.setAttribute("listaproductPasado", clienteModel.listaCarritoPasado(idpedido));
            request.setAttribute("totalpedidopasado", clienteModel.totalPedidoPasado(idpedido));
            request.setAttribute("fechaPedido", clienteModel.fechaPedido(idpedido));
            request.getRequestDispatcher("/cliente/CarritoPasado.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pagoPedido(HttpServletRequest request, HttpServletResponse response) {
        try {
            int estado = (int) request.getSession().getAttribute("estado");
            Calendar c = Calendar.getInstance();
            String dia, mes, annio, fecha;
            dia = Integer.toString(c.get(Calendar.DATE));
            mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            annio = Integer.toString(c.get(Calendar.YEAR));
            fecha = annio + "-" + mes + "-" + dia;
            if (estado != 1) {
                request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
                request.getSession().setAttribute("fracaso", "No tienes un pedido activo.");
                request.getRequestDispatcher("/cliente/Carrito.jsp").forward(request, response);
            } else {
                int idusuario = (int) request.getSession().getAttribute("usuario");
                request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
                request.setAttribute("idPedido", clienteModel.obtenerIdPedido(idusuario));
                request.setAttribute("clienteInfo", usuario.obtenerCliente(idusuario));
                request.setAttribute("fechaPedido", fecha);

                request.getRequestDispatcher("/cliente/pagoPedido.jsp").forward(request, response);
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void confirmarPedido(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idusuario = (int) request.getSession().getAttribute("usuario");
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
            if (clienteModel.confirmarPedido(idusuario, fecha, hora) > 0) {
                request.getSession().setAttribute("exito", "Tu pedido a sido confirmado, espera a que tus articulos lleguen a la comodidad de tu casa.");
                request.getSession().setAttribute("estado", clienteModel.estadoPedido((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("pedidosProduc", clienteModel.listaCarrito((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("pedidosOfert", clienteModel.listaCarritoOfertas((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));

                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=publicIndex");
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cancelarPedido(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idusuario = (int) request.getSession().getAttribute("usuario");
            if (clienteModel.cancelarPedido(idusuario) > 0) {
                request.getSession().setAttribute("exito", "Tu pedido a sido cancelado");
                request.getSession().setAttribute("estado", clienteModel.estadoPedido((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("pedidosProduc", clienteModel.listaCarrito((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("pedidosOfert", clienteModel.listaCarritoOfertas((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("cantidadpedidos", clienteModel.cantidadProduct((int) request.getSession().getAttribute("usuario")));
                request.getSession().setAttribute("totalPedido", clienteModel.totalPedido((int) request.getSession().getAttribute("usuario")));

                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=publicIndex");
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void acercaDe(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.getRequestDispatcher("acercaDe.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(PublicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sugerencias(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", CategoriaModel.listarCategorias());
            request.getRequestDispatcher("/cliente/Sugerencias.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarSugerencia(HttpServletRequest request, HttpServletResponse response) {

        Sugerencia sugerencia = new Sugerencia();
        Usuario usuario = new Usuario();
        int idusuario = (int) request.getSession().getAttribute("usuario");
        usuario.setIdUsuario(idusuario);

        sugerencia.setAsunto(request.getParameter("asunto"));
        sugerencia.setDetalle(request.getParameter("descripcion"));
        sugerencia.setUsuario(usuario);

        try {

            if (sugerenciasModel.insertarSugerencia(sugerencia) > 0) {
                request.getSession().setAttribute("exito", "Sugerencia enviada exitosamente");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=sugerencias");
            } else {
                request.getSession().setAttribute("fracaso", "Sugerencia no registrada");
                response.sendRedirect(request.getContextPath() + "/clientes.do?operacion=sugerencias");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
