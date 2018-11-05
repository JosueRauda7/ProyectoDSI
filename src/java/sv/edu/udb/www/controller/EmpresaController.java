/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.io.File;
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
import sv.edu.udb.www.beans.Imagen;
import sv.edu.udb.www.beans.Pedido;
import sv.edu.udb.www.beans.Producto;
import sv.edu.udb.www.beans.SubCategoria;
import sv.edu.udb.www.model.CategoriasModel;
import sv.edu.udb.www.model.ClientesModel;
import sv.edu.udb.www.model.PedidosModel;
import sv.edu.udb.www.model.ProductosModel;
import sv.edu.udb.www.model.SubCategoriasModel;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author ivanm
 */
@WebServlet(name = "EmpresaController", urlPatterns = {"/empresas.do"})
public class EmpresaController extends HttpServlet {

    ProductosModel modeloProducto = new ProductosModel();
    SubCategoriasModel modeloSubcategoria = new SubCategoriasModel();
    CategoriasModel modeloCategoria = new CategoriasModel();
    PedidosModel modeloPedido = new PedidosModel();
    ArrayList listaErrores = new ArrayList();

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
            if (request.getSession().getAttribute("usuario") != null || request.getSession().getAttribute("tipousuario") != null || request.getSession().getAttribute("nombreUser") != null) {
                if (request.getSession().getAttribute("usuario") == null || !request.getSession().getAttribute("tipousuario").toString().equals("5")) {
                    response.sendRedirect(request.getContextPath() + "/public.do?operacion=publicIndex");
                    return;
                }
                if (request.getParameter("operacion") != null) {
                    String operacion = request.getParameter("operacion");
                    switch (operacion) {
                        case "listar":
                            listar(request, response);
                            break;
                        case "nuevo":
                            nuevo(request, response);
                            break;
                        case "inicio":
                            request.getRequestDispatcher("/empresa/inicioEmpresa.jsp").forward(request, response);
                            break;
                        case "obtener":
                            obtener(request, response);
                            break;
                        case "existencias":
                            existencias(request, response);
                            break;

                        case "grafica":
                            grafica(request,response);
                            break;
                            
                        case "ventadiaria":
                            try{
                            ventadiaria(request,response);
                            }catch(SQLException e){
                                
                            }
                            break;
                        case "subcategorias":
                            try {
                                subcategorias(request, response);
                            } catch (SQLException e) {

                            }
                            break;

                        default:
                            request.getRequestDispatcher("/error404.jsp").forward(request, response);
                            break;
                    }
                } else {
                    String directorio = getServletContext().getRealPath("/images");
                    MultipartRequest multi = new MultipartRequest(request, directorio, 1 * 1024 * 1024, new DefaultFileRenamePolicy());
                    String operacion = multi.getParameter("operacion");
                    switch (operacion) {
                        case "insertar":
                            insertar(multi, request, response);
                            break;
                        case "reenviar":
                            reenviarproducto(multi, request, response);
                            break;
                    }
                }
            } else {
                request.getRequestDispatcher("/public.do?operacion=publicIndex").forward(request, response);
            }
        } finally {
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
            int usuario = Integer.parseInt(request.getSession().getAttribute("usuario").toString());
            request.setAttribute("listarProducto", modeloProducto.listarProducto(usuario, estado));
            request.setAttribute("listarImagenes", modeloProducto.listarImagenesProducto());
            request.setAttribute("tab", estado);
            try {
                request.getRequestDispatcher("/empresa/listaProductos.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaSubcategoria", modeloSubcategoria.listarSubCategorias());
            request.setAttribute("listaCategoria", modeloCategoria.listarCategorias());
            request.getRequestDispatcher("/empresa/nuevoProducto.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertar(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErrores.clear();
            int usuario = Integer.parseInt(request.getSession().getAttribute("usuario").toString());
            Producto producto = new Producto();
            Imagen imagen = new Imagen();
            List<String> imagenes = new ArrayList();

            producto.setProducto(multi.getParameter("producto"));
            producto.setDescripcion(multi.getParameter("descripcion"));
            producto.setPrecioRegular(multi.getParameter("regular"));
            producto.setCantidad(multi.getParameter("cantidad"));
            producto.setIdsubCategoria(Integer.parseInt(multi.getParameter("subcategoria")));

            if (Validaciones.isEmpty(producto.getProducto())) {
                listaErrores.add("El nombre del producto es obligatorio");
            }

            if (Validaciones.isEmpty(producto.getDescripcion())) {
                listaErrores.add("Agrege una descripción al producto");
            }

            if (Validaciones.isEmpty(producto.getPrecioRegular())) {
                listaErrores.add("El precio del producto es obligatorio");
            } else if (!Validaciones.esDecimal(producto.getPrecioRegular())) {
                listaErrores.add("El precio debe ser un decimal");
            } else if (!Validaciones.esDecimalPositivo(producto.getPrecioRegular())) {
                listaErrores.add("El precio debe ser un número positivo");
            }

            if (Validaciones.isEmpty(producto.getCantidad())) {
                listaErrores.add("La cantidad de producto es obligatoria");
            } else if (!Validaciones.esEntero(producto.getCantidad())) {
                listaErrores.add("La cantidad de productos debe ser un número");
            } else if (!Validaciones.esEnteroPositivo(producto.getCantidad())) {
                listaErrores.add("La cantidad debe ser un número positivo");
            }

            if (multi.getFile("imagen") == null) {
                listaErrores.add("La imagen es obligatoria");
            } else {
                File ficheroTemp = multi.getFile("imagen");
                imagen.setUrlimagen(ficheroTemp.getName());
            }

            if (multi.getFile("imagen1") != null) {
                File ficheroTemp = multi.getFile("imagen1");
                imagenes.add(ficheroTemp.getName());
            }

            if (multi.getFile("imagen2") != null) {
                File ficheroTemp = multi.getFile("imagen2");
                imagenes.add(ficheroTemp.getName());
            }

            if (multi.getFile("imagen3") != null) {
                File ficheroTemp = multi.getFile("imagen3");
                imagenes.add(ficheroTemp.getName());
            }

            if (multi.getFile("imagen4") != null) {
                File ficheroTemp = multi.getFile("imagen4");
                imagenes.add(ficheroTemp.getName());
            }

            if (listaErrores.isEmpty()) {
                if (modeloProducto.insertarProducto(producto, usuario, imagen, imagenes) == 1) {
                    request.getSession().setAttribute("exito", "Producto registrado existosamente.");
                } else {
                    request.getSession().setAttribute("fracaso", "Ocurrio un error, no se pudo registrar el producto...");
                }

                response.sendRedirect(request.getContextPath() + "/empresas.do?operacion=listar&estado=1");
            } else {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("listaSubcategoria", modeloSubcategoria.listarSubCategorias());
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("/empresa/nuevoProducto.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        try {
            int codigo = Integer.parseInt(request.getParameter("id"));
            int usuario = Integer.parseInt(request.getSession().getAttribute("usuario").toString());
            Producto producto = modeloProducto.obtenerProducto(usuario, codigo);
            if (producto != null) {
                request.setAttribute("producto", producto);
                request.setAttribute("id", codigo);
                request.setAttribute("listaSubcategoria", modeloSubcategoria.listarSubCategorias());
                request.getRequestDispatcher("/empresa/modificarProducto.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("fracaso", "Ocurrio un error, no se pudo encontrar el producto...");
                response.sendRedirect(request.getContextPath() + "/empresas.do?operacion=listar&estado=1");
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reenviarproducto(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) throws IOException {
        listaErrores.clear();
        try {
            Producto producto = new Producto();
            int usuario = Integer.parseInt(request.getSession().getAttribute("usuario").toString());
            int codigo = Integer.parseInt(multi.getParameter("id"));
            producto.setProducto(multi.getParameter("producto"));
            producto.setDescripcion(multi.getParameter("descripcion"));
            producto.setPrecioRegular(multi.getParameter("regular"));
            producto.setCantidad(multi.getParameter("cantidad"));
            producto.setIdsubCategoria(Integer.parseInt(multi.getParameter("subcategoria")));

            if (Validaciones.isEmpty(producto.getProducto())) {
                listaErrores.add("El nombre del producto es obligatorio");
            }

            if (Validaciones.isEmpty(producto.getDescripcion())) {
                listaErrores.add("Agrege una descripción al producto");
            }

            if (Validaciones.isEmpty(producto.getPrecioRegular())) {
                listaErrores.add("El precio del producto es obligatorio");
            } else if (!Validaciones.esDecimal(producto.getPrecioRegular())) {
                listaErrores.add("El precio debe ser un decimal");
            } else if (!Validaciones.esDecimalPositivo(producto.getPrecioRegular())) {
                listaErrores.add("El precio debe ser un número positivo");
            }

            if (Validaciones.isEmpty(producto.getCantidad())) {
                listaErrores.add("La cantidad de producto es obligatoria");
            } else if (!Validaciones.esEntero(producto.getCantidad())) {
                listaErrores.add("La cantidad de productos debe ser un número");
            } else if (!Validaciones.esEnteroPositivo(producto.getCantidad())) {
                listaErrores.add("La cantidad debe ser un número positivo");
            }

            if (multi.getFile("archivo") == null) {
                listaErrores.add("La imagen es obligatoria");
            } else {
                File ficheroTemp = multi.getFile("archivo");
                producto.setUrlImagen(ficheroTemp.getName());
            }

            if (listaErrores.isEmpty()) {
                if (modeloProducto.modificarProducto(producto, codigo) == 1) {
                    request.getSession().setAttribute("exito", "Producto reenviado existosamente.");
                } else {
                    request.getSession().setAttribute("fracaso", "Ocurrio un error, no se pudo reenviar el producto...");
                }

                response.sendRedirect(request.getContextPath() + "/empresas.do?operacion=listar&estado=1");
            } else {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("listaSubcategoria", modeloSubcategoria.listarSubCategorias());
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("/empresa/modificarProducto.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void existencias(HttpServletRequest request, HttpServletResponse response) {
        try {
            Producto producto = new Producto();
            producto.setIdProducto(Integer.parseInt(request.getParameter("id")));
            producto.setCantidad(request.getParameter("existencias"));

            if (modeloProducto.actualizarExistencias(producto) != 0) {
                int usuario = Integer.parseInt(request.getSession().getAttribute("usuario").toString());
                request.setAttribute("listarProducto", modeloProducto.listarProducto(usuario, 2));
                request.setAttribute("listarImagenes", modeloProducto.listarImagenesProducto());
                request.setAttribute("tab", 2);
                request.getSession().setAttribute("exito", "Se actualizaron las existencias del producto");
                request.getRequestDispatcher("/empresa/listaProductos.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("fracaso", "Ocurrio un error, no se actualizaron las existencias...");
                response.sendRedirect(request.getContextPath() + "/empresas.do?operacion=listar&estado=1");
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void subcategorias(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        PrintWriter out = response.getWriter();
        int categoria = Integer.parseInt(request.getParameter("codigo"));

        List<SubCategoria> subcategoria = modeloSubcategoria.listarSubCategorias(categoria);
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < subcategoria.size(); i++) {
            sb.append(subcategoria.get(i).getIdSubCategoria() + "-" + subcategoria.get(i).getSubCategoria() + ":");
        }
        out.write(sb.toString());
    }

    private void grafica(HttpServletRequest request, HttpServletResponse response) {
        try {            
            request.setAttribute("ventas", modeloPedido.ventasDiarias().size());
            request.getRequestDispatcher("/empresa/estadisticaEmpresa.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ventadiaria(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        
        PrintWriter out = response.getWriter();
        //Necesito la cantidad de datos que devuelve la consulta
        request.setAttribute("ventas", modeloPedido.ventasDiarias().size());
        //Arreglo con los datos que solicito en la consulta (AUN DEBO REVISAR BIEN LA CONSULTA)
        List<Pedido> pedidos = modeloPedido.ventasDiarias();
        
        //Como solo obtengo 2 datos, la fecha y monto, esta variable me servirá para mandar ambos como respuesta Json del Arreglo anterior
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < pedidos.size(); i++) {
            sb.append(pedidos.get(i).getFechaCompra()+ "," + pedidos.get(i).getMontoTotal() + ":");
        }
        out.write(sb.toString());
    }

}
