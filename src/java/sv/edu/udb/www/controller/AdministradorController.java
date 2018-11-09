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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Categoria;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.EstadoCategoria;
import sv.edu.udb.www.beans.Pedido;
import sv.edu.udb.www.beans.SubCategoria;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.model.CategoriasModel;
import sv.edu.udb.www.model.ClientesModel;
import sv.edu.udb.www.model.EmpresasModel;
import sv.edu.udb.www.model.PedidosModel;
import sv.edu.udb.www.model.ProductosModel;
import sv.edu.udb.www.model.SubCategoriasModel;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author ivanm
 */
@WebServlet(name = "AdministradorController", urlPatterns = {"/administrador.do"})
public class AdministradorController extends HttpServlet {

    EmpresasModel modeloEmpresa = new EmpresasModel();
    UsuariosModel modeloUsuario = new UsuariosModel();
    CategoriasModel modeloCategoria = new CategoriasModel();
    PedidosModel modeloPedido = new PedidosModel();
    SubCategoriasModel modeloSubCategoria = new SubCategoriasModel();
    ProductosModel modeloProducto = new ProductosModel();
    ArrayList listaErrores = new ArrayList();
    ArrayList listaErroresEmpresa = new ArrayList();
    ArrayList listaErroresUsuario = new ArrayList();

    CategoriasModel CategoriaModel = new CategoriasModel();
    ProductosModel ProductoModel = new ProductosModel();

    ClientesModel clienteModel = new ClientesModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*if (request.getSession().getAttribute("usuario") == null || !request.getSession().getAttribute("tipousuario").toString().equals("1")) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }*/
            if (request.getSession().getAttribute("usuario") != null || request.getSession().getAttribute("tipousuario") != null || request.getSession().getAttribute("nombreUser") != null) {
                if (request.getSession().getAttribute("usuario") == null || !request.getSession().getAttribute("tipousuario").toString().equals("1")) {
                    response.sendRedirect(request.getContextPath() + "/public.do?operacion=publicIndex");
                    return;
                }
                if (request.getParameter("operacion") != null) {

                    String operacion = request.getParameter("operacion");
                    switch (operacion) {
                        //-------------------CATEGORIAS!--------------------
                        case "listarCategorias":
                            listarCategorias(request, response);
                            break;
                        case "nuevaCategoria":
                            nuevaCategoria(request, response);
                            break;
                        case "modificarCategoria":
                            modificarCategoria(request, response);
                            break;
                        case "deshabilitarCategoria":
                            deshabilitarCategoria(request, response);
                            break;
                        case "habilitarCategoria":
                            habilitarCategoria(request, response);
                            break;
                        //--------------------------------------------------
                        //--------------------SUB CATEGORIAS!---------------
                        case "listarSubCategorias":
                            listarSubCategorias(request, response);
                            break;
                        case "nuevaSubCategoria":
                            nuevaSubCategoria(request, response);
                            break;
                        case "obtenerSubCategoria":
                            obtenerSubCategoria(request, response);
                            break;
                        case "deshabilitarSubCategoria":
                            deshabilitarSubCategoria(request, response);
                            break;
                        case "habilitarSubCategoria":
                            habilitarSubCategoria(request, response);
                            break;
                        //--------------------------------------------------
                        //------------------CONTROL DE USUARIOS-------------
                        case "listarUsuarios":
                            listarUsuarios(request, response);
                            break;
                        case "agregarUsuario":
                            agregarUsuario(request, response);
                            break;
                        case "agregarAdministrador":
                            agregarAdministrador(request, response);
                            break;
                        case "modificarUsuario":
                            modificarUsuario(request, response);
                            break;
                        case "realizarModificacionUsuario":
                            realizarModificacionUsuario(request, response);
                            break;
                        case "deshabilitarUsuario":
                            deshabilitarUsuario(request, response);
                            break;
                        case "habilitarUsuario":
                            habilitarUsuario(request, response);
                            break;
                        //--------------------------------------------------   
                        //-----------APROVAR/RECHAZAR PRODUCTOS-------------
                        case "listarProductos":
                            listarProductos(request, response);
                            break;
                        case "aceptarRechazar":
                            aceptarRechazar(request, response);
                            break;
                        //--------------------------------------------------
                        case "listarEmpresas":
                            listarEmpresas(request, response);
                            break;
                        case "nuevaEmpresa":
                            nuevaEmpresa(request, response);
                            break;
                        case "obtenerEmpresa":
                            obtenerEmpresa(request, response);
                            break;

                        case "grafica":
                            grafica(request, response);
                            break;

                        case "ventaanual":
                            try {
                                ventaanual(request, response);
                            } catch (SQLException e) {

                            }
                            break;
                        case "inicio":
                            request.getRequestDispatcher("/administrador/inicioAdmin.jsp").forward(request, response);
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
                        case "agregarCategoria":
                            agregarCategoria(multi, request, response);
                            break;
                        case "guardarCategoria":
                            guardarCategoria(multi, request, response);
                            break;
                        case "insertarSubCategoria":
                            insertarSubCategoria(multi, request, response);
                            break;
                        case "modificarSubCategoria":
                            modificarSubCategoria(multi, request, response);
                            break;
                        case "insertarEmpresa":
                            insertarEmpresa(multi, request, response);
                            break;
                        case "modificarEmpresa":
                            modificarEmpresa(multi, request, response);
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

    private void listarEmpresas(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listarEmpresas", modeloEmpresa.listarEmpresas());
            try {
                request.getRequestDispatcher("/administrador/verEmpresas.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void nuevaEmpresa(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaContactoDisponible", modeloUsuario.listaContactoDisponible());
            request.getRequestDispatcher("/administrador/nuevaEmpresa.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertarEmpresa(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErroresEmpresa.clear();
            listaErroresUsuario.clear();
            
                    
            Empresa empresa = new Empresa();
            empresa.setEmpresa(multi.getParameter("empresa"));
            empresa.setComision("0.8");            
            empresa.setIdEstadoEmpresa(1);

            if (Validaciones.isEmpty(multi.getParameter("empresa"))) {
                listaErroresEmpresa.add("El nombre de la empresa es obligatorio");
            }
            
            if (multi.getFile("imagen") == null) {
                listaErroresEmpresa.add("La imagen es obligatoria");
            } else {
                File ficheroTemp = multi.getFile("imagen");
                empresa.setUrlEmpresa(ficheroTemp.getName());
            }
            
            Usuario usuario = new Usuario();
            
            String contraAleatoria = "";
            String key = "abcdefghijklmnopqrstuvwxyz0123456789";
            for (int i = 0; i < 6; i++) {
                contraAleatoria += (key.charAt((int) (Math.random() * key.length())));
            }
            
            usuario.setNombre(multi.getParameter("nombre"));
            usuario.setApellido(multi.getParameter("apellido"));
            usuario.setCorreo(multi.getParameter("correo"));
            usuario.setTelefono(multi.getParameter("telefono"));
            usuario.setPassword(contraAleatoria);
            usuario.setDui(multi.getParameter("dui"));
            usuario.setDireccion(multi.getParameter("direccion"));
            
            if(Validaciones.isEmpty(multi.getParameter("nombre"))){
                listaErroresUsuario.add("El nombre del contacto es obligatorio");
            }
            if(Validaciones.isEmpty(multi.getParameter("apellido"))){
                listaErroresUsuario.add("El apellido del contacto es obligatorio");
            }
            if(Validaciones.isEmpty(multi.getParameter("correo"))){
                listaErroresUsuario.add("El correo es obligatorio");
            }else if(!Validaciones.esCorreo(multi.getParameter("correo"))){
                listaErroresUsuario.add("El correo tiene un formato incorrecto");
            }
            if(Validaciones.isEmpty(multi.getParameter("telefono"))){
                listaErroresUsuario.add("El telefono es obligatorio");
            }else if(!Validaciones.esTelefono(multi.getParameter("telefono"))){
                listaErroresUsuario.add("El tenefono debe tener el siguiente formato 0000-0000");
            }
            if(Validaciones.isEmpty(multi.getParameter("dui"))){
                listaErroresUsuario.add("El DUI es obligatorio");
            }else if(!Validaciones.esDui(multi.getParameter("dui"))){
                listaErroresUsuario.add("El formato del DUI debe ser el siguiente 00000000-0");
            }
            if(Validaciones.isEmpty(multi.getParameter("direccion"))){
                listaErroresUsuario.add("La dirección del contacto es obligatoria");
            }
                
            if (listaErroresUsuario.isEmpty() && listaErroresEmpresa.isEmpty()) {
                if (modeloEmpresa.insertarEmpresa(empresa, usuario) == 1) {
                    request.getSession().setAttribute("exito", "Empresa registrada existosamente.");
                    
                    String cadenaAleatoria = UUID.randomUUID().toString();
                    String enlace = request.getRequestURL().toString()
                            + "?operacion=verificar&id=" + cadenaAleatoria;
                    String texto = "<div class='container2' style='color: white;border: solid black 2px;border-radius: 25px;width: 30%;padding: 1%;background-color: #e84d1c;'><h1 style=\"text-align: center;\">Bienvenido a BigShop</h1><div>"
                            + "<p>BigShop es tu nueva tienda oline, aquí te ofrecemos una gran variedad de productos a un buen precio, tambien tenemos los mejores productos de tus marcas favoritas, todo lo que decees esta aqui.</p>"
                            + "<p>Has sido registrado como administrador tu contraseña es: " + usuario.getPassword() + ".</p>"
                            + "<p>Para poder acceder a nuestro sitio debes validar tu usuario, da click al boton para empezar a comprar.</p>"
                            + "<a target='_blank' href='" + enlace + "'><button type='button' style='background-color: white;color: black;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;border: solid 1px #67656E;  font-family:fantasy;margin-left:30%;'   onmouseover='this.style.backgroundColor=\"#A5A1B3\" ' onmouseout='this.style.backgroundColor=\"\"'>Entrar</button></a></div></div>";
                    
                    Correo correo = new Correo();
                    correo.setAsunto("Confirmacion de registro");
                    correo.setMensaje(texto);
                    correo.setDestinatario(usuario.getCorreo());
                    correo.enviarCorreo();
                } else {
                    request.getSession().setAttribute("fracaso", "Ocurrio un error, no se pudo registrar la empresa...");
                }
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarEmpresas");
            } else {
                request.setAttribute("listaErroresEmpresa", listaErroresEmpresa);
                request.setAttribute("listaErroresUsuario", listaErroresUsuario);
                request.setAttribute("listaContactoDisponible", modeloUsuario.listaContactoDisponible());
                request.setAttribute("empresa", empresa);
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/administrador/nuevaEmpresa.jsp").forward(request, response);
            }

        } catch (IOException | NumberFormatException | SQLException | ServletException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obtenerEmpresa(HttpServletRequest request, HttpServletResponse response) {
        try {
            int codigo = Integer.parseInt(request.getParameter("id"));
            Empresa empresa = modeloEmpresa.obtenerEmpresa(codigo);
            Usuario usuario = modeloEmpresa.obtenerUsuario(codigo);
            if (empresa != null) {
                request.setAttribute("empresa", empresa);
                request.setAttribute("usuario", usuario);
                request.setAttribute("id", codigo);
                request.setAttribute("listaContactoDisponible", modeloUsuario.listaContactoDisponible());
                request.getRequestDispatcher("/administrador/modificarEmpresa.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("fracaso", "Ocurrio un error, no se pudo encontrar la empresa...");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarEmpresas");
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarEmpresa(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErroresEmpresa.clear();
            listaErroresUsuario.clear();
            
            int codigo = Integer.parseInt(multi.getParameter("id"));
            
            Empresa empresa = new Empresa();
            empresa.setEmpresa(multi.getParameter("empresa"));
            empresa.setComision("80.00");            
            empresa.setIdEstadoEmpresa(1);

            if (Validaciones.isEmpty(multi.getParameter("empresa"))) {
                listaErroresEmpresa.add("El nombre de la empresa es obligatorio");
            }
            
            if (multi.getFile("imagen") == null) {
                listaErroresEmpresa.add("La imagen es obligatoria");
            } else {
                File ficheroTemp = multi.getFile("imagen");
                empresa.setUrlEmpresa(ficheroTemp.getName());
            }
            
            Usuario usuario = new Usuario();                        
            
            usuario.setNombre(multi.getParameter("nombre"));
            usuario.setApellido(multi.getParameter("apellido"));
            usuario.setCorreo(multi.getParameter("correo"));
            usuario.setTelefono(multi.getParameter("telefono"));            
            usuario.setDui(multi.getParameter("dui"));
            usuario.setDireccion(multi.getParameter("direccion"));
            
            if(Validaciones.isEmpty(multi.getParameter("nombre"))){
                listaErroresUsuario.add("El nombre del contacto es obligatorio");
            }
            if(Validaciones.isEmpty(multi.getParameter("apellido"))){
                listaErroresUsuario.add("El apellido del contacto es obligatorio");
            }
            if(Validaciones.isEmpty(multi.getParameter("correo"))){
                listaErroresUsuario.add("El correo es obligatorio");
            }else if(!Validaciones.esCorreo(multi.getParameter("correo"))){
                listaErroresUsuario.add("El correo tiene un formato incorrecto");
            }
            if(Validaciones.isEmpty(multi.getParameter("telefono"))){
                listaErroresUsuario.add("El telefono es obligatorio");
            }else if(!Validaciones.esTelefono(multi.getParameter("telefono"))){
                listaErroresUsuario.add("El tenefono debe tener el siguiente formato 0000-0000");
            }
            if(Validaciones.isEmpty(multi.getParameter("dui"))){
                listaErroresUsuario.add("El DUI es obligatorio");
            }else if(!Validaciones.esDui(multi.getParameter("dui"))){
                listaErroresUsuario.add("El formato del DUI debe ser el siguiente 00000000-0");
            }
            if(Validaciones.isEmpty(multi.getParameter("direccion"))){
                listaErroresUsuario.add("La dirección del contacto es obligatoria");
            }

            if (listaErroresUsuario.isEmpty() && listaErroresEmpresa.isEmpty()) {
                if (modeloEmpresa.modificarEmpresa(empresa,usuario, codigo) == 1) {
                    request.getSession().setAttribute("exito", "Empresa modificada existosamente.");
                } else {
                    request.getSession().setAttribute("fracaso", "Ocurrio un error, no se pudo modificar la empresa...");
                }
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarEmpresas");
            } else {
                 request.setAttribute("listaErroresEmpresa", listaErroresEmpresa);
                request.setAttribute("listaErroresUsuario", listaErroresUsuario);
                request.setAttribute("listaContactoDisponible", modeloUsuario.listaContactoDisponible());
                request.setAttribute("empresa", empresa);
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/administrador/modificarEmpresa.jsp").forward(request, response);
            }

        } catch (IOException | NumberFormatException | SQLException | ServletException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", modeloCategoria.listarCategorias());
            request.getRequestDispatcher("/administrador/verCategorias.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void nuevaCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/administrador/nuevaCategoria.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            request.setAttribute("category", modeloCategoria.obtenerCategoria(id));
            request.getRequestDispatcher("/administrador/modificarCategoria.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deshabilitarCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (modeloCategoria.deshabilitarCategoria(id) > 0) {
                request.getSession().setAttribute("exito", "Categoría ha sido deshabilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
            } else {
                request.getSession().setAttribute("fracaso", "Categoría no ha sido deshabilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void habilitarCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (modeloCategoria.habilitarCategoria(id) > 0) {
                request.getSession().setAttribute("exito", "Categoría ha sido habilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
            } else {
                request.getSession().setAttribute("fracaso", "Categoría no ha sido habilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarCategoria(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErrores.clear();
            Categoria categoria = new Categoria();
            EstadoCategoria estadoCategoria = new EstadoCategoria();
            estadoCategoria.setIdEstadoCategoria(Integer.parseInt(multi.getParameter("estado")));
            categoria.setCategoria(multi.getParameter("nombre"));
            categoria.setEstadoCategoria(estadoCategoria);

            if (Validaciones.isEmpty(categoria.getCategoria())) {
                listaErrores.add("El nombre de la categoría es obligatorio.");
            }
            if (!Validaciones.esEnteroPositivo(multi.getParameter("estado"))) {
                listaErrores.add("Estado de categoría incorrecto.");
            }
            if (multi.getFile("archivo") == null) {
                listaErrores.add("Debe seleccionar una imagen");
            } else {
                File ficheroTemp = multi.getFile("archivo");
                categoria.setUrlCategoria(ficheroTemp.getName());
            }

            if (listaErrores.size() > 0) {
                request.setAttribute("category", categoria);
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("/administrador.do?operacion=nuevaCategoria").forward(request, response);
            } else {
                if (modeloCategoria.insertarCategoria(categoria) > 0) {
                    request.getSession().setAttribute("exito", "Categoría registrada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
                } else {
                    request.getSession().setAttribute("fracaso", "Categoría no registrada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
                }
            }
        } catch (IOException | NumberFormatException | SQLException | ServletException e) {

        }
    }

    private void guardarCategoria(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErrores.clear();
            Categoria categoria = new Categoria();
            EstadoCategoria estadoCategoria = new EstadoCategoria();
            estadoCategoria.setIdEstadoCategoria(Integer.parseInt(multi.getParameter("estado")));
            categoria.setIdCategoria(Integer.parseInt(multi.getParameter("codigo")));
            categoria.setCategoria(multi.getParameter("nombre"));
            categoria.setEstadoCategoria(estadoCategoria);

            if (Validaciones.isEmpty(categoria.getCategoria())) {
                listaErrores.add("El nombre de la categoría es obligatorio.");
            }
            if (!Validaciones.esEnteroPositivo(multi.getParameter("estado"))) {
                listaErrores.add("Estado de categoría incorrecto.");
            }

            if (multi.getFile("archivo") != null) {
                File ficheroTemp = multi.getFile("archivo");
                categoria.setUrlCategoria(ficheroTemp.getName());
            } else {
                categoria.setUrlCategoria(null);
            }

            if (listaErrores.size() > 0) {
                request.setAttribute("category", categoria);
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("/administrador.do?operacion=modificarCategoria").forward(request, response);
            } else {
                if (modeloCategoria.modificarCategoria(categoria) > 0) {
                    request.getSession().setAttribute("exito", "Categoría registrada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
                } else {
                    request.getSession().setAttribute("fracaso", "Categoría no registrada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
                }
            }
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarSubCategorias(HttpServletRequest request, HttpServletResponse response) {
        try {

            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id").toString());
                request.setAttribute("categoria", modeloCategoria.obtenerCategoria(request.getParameter("id")));

                if (request.getAttribute("categoria").equals(null)) {
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
                }
                request.setAttribute("listaSubCategorias", modeloSubCategoria.listarSubCategorias(id));

            } else {
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
            }
            try {
                request.getRequestDispatcher("/administrador/listaSubCategorias.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            try {
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
            } catch (IOException ex1) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    private void nuevaSubCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("categoria", modeloCategoria.obtenerCategoria(request.getParameter("idCategoria")));

            if (request.getAttribute("categoria").equals(null)) {
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
            }

            request.getRequestDispatcher("/administrador/nuevaSubCategoria.jsp").forward(request, response);
        } catch (Exception ex) {
            try {
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
            } catch (IOException ex1) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    private void obtenerSubCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("categoria", modeloCategoria.obtenerCategoria(request.getParameter("idCategoria")));

            if (request.getAttribute("categoria").equals(null)) {
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
            }

            request.setAttribute("subCategoria", modeloSubCategoria.obtenerSubCategoria(request.getParameter("id")));
            request.getRequestDispatcher("/administrador/editarSubCategoria.jsp").forward(request, response);
        } catch (Exception ex) {
            try {
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarCategorias");
            } catch (IOException ex1) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    private void deshabilitarSubCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {

            int id = Integer.parseInt(request.getParameter("id"));

            if (modeloSubCategoria.deshabilitarSubCategoria(id) > 0) {
                request.getSession().setAttribute("exito", "Sub categoria deshabilitada exitosamente");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias");
            } else {
                request.getSession().setAttribute("fracaso", "No se pudo deshabilitar esta sub categoria");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void habilitarSubCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {

            int id = Integer.parseInt(request.getParameter("id"));

            if (modeloSubCategoria.habilitarSubCategoria(id) > 0) {
                request.getSession().setAttribute("exito", "Sub categoria habilitada exitosamente");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias");
            } else {
                request.getSession().setAttribute("fracaso", "No se pudo habilitar esta sub categoria");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertarSubCategoria(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) {
        listaErrores.clear();
        SubCategoria subCategoria = new SubCategoria();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(Integer.parseInt(multi.getParameter("categoria")));

        subCategoria.setSubCategoria(multi.getParameter("subcategoria"));
        subCategoria.setCategoria(categoria);

        if (Validaciones.isEmpty(subCategoria.getSubCategoria())) {
            listaErrores.add("El nombre de la sub categoria es obligatorio");
        }

        if (Validaciones.isEmpty(String.valueOf(categoria.getIdCategoria()))) {
            listaErrores.add("Debe seleccionar una categoria");
        }

        if (multi.getFile("archivo") == null) {
            listaErrores.add("Debe seleccionar una imagen");
        } else {
            File ficheroTemp = multi.getFile("archivo");
            subCategoria.setUrlSubcategoria(ficheroTemp.getName());
        }

        if (listaErrores.size() > 0) {
            try {
                request.setAttribute("listaCategorias", modeloCategoria.listarCategorias());
                request.setAttribute("subCategoria", subCategoria);
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("/administrador/nuevaSubCategoria.jsp").forward(request, response);
            } catch (ServletException | IOException | SQLException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {

                if (modeloSubCategoria.insertarSubCategoria(subCategoria) > 0) {
                    request.getSession().setAttribute("exito", "Sub categoria registrada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias&id=" + categoria.getIdCategoria());
                } else {
                    request.getSession().setAttribute("fracaso", "Dato no registrado. Ya existe esta sub categoria");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias&id=" + categoria.getIdCategoria());
                }
            } catch (SQLException | IOException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void modificarSubCategoria(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) {
        listaErrores.clear();
        SubCategoria subCategoria = new SubCategoria();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(Integer.parseInt(multi.getParameter("categoria")));

        subCategoria.setSubCategoria(multi.getParameter("subcategoria"));
        subCategoria.setCategoria(categoria);

        subCategoria.setIdSubCategoria(Integer.parseInt(multi.getParameter("id")));

        if (Validaciones.isEmpty(subCategoria.getSubCategoria())) {
            listaErrores.add("El nombre de la sub categoria es obligatorio");
        }

        if (Validaciones.isEmpty(String.valueOf(categoria.getIdCategoria()))) {
            listaErrores.add("Debe seleccionar una categoria");
        }

        if (multi.getFile("archivo") != null) {
            File ficheroTemp = multi.getFile("archivo");
            subCategoria.setUrlSubcategoria(ficheroTemp.getName());
        }

        if (listaErrores.size() > 0) {
            try {
                request.setAttribute("listaCategorias", modeloCategoria.listarCategorias());
                request.setAttribute("subCategoria", subCategoria);
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("/administrador/editarSubCategoria.jsp").forward(request, response);

            } catch (ServletException | IOException | SQLException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {

                if (modeloSubCategoria.modificarSubCategoria(subCategoria) > 0) {
                    request.getSession().setAttribute("exito", "Sub categoria modificada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias&id=" + categoria.getIdCategoria());
                } else {
                    request.getSession().setAttribute("fracaso", "Dato no modificado. Ya existe esta sub categoria");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias&id=" + categoria.getIdCategoria());
                }
            } catch (SQLException | IOException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaUsuarios", modeloUsuario.listarUsuarios());
            request.getRequestDispatcher("/administrador/verAdministradores.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/administrador/nuevoAdministrador.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarAdministrador(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            listaErrores.clear();
            String contraAleatoria = "";
            String key = "abcdefghijklmnopqrstuvwxyz0123456789";
            for (int i = 0; i < 6; i++) {
                contraAleatoria += (key.charAt((int) (Math.random() * key.length())));
            }
            Usuario usuario = new Usuario();
            usuario.setCorreo(request.getParameter("correo"));
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setDireccion(request.getParameter("direccion"));
            usuario.setDui(request.getParameter("dui"));
            usuario.setPassword(contraAleatoria);
            usuario.setTipoUser(Integer.parseInt(request.getParameter("tipoUsuario")));
            usuario.setTelefono(request.getParameter("telefono"));

//            String urlmodel = request.getParameter("url");
//
//            if (usuario.getCorreo().isEmpty()) {
//                listaErrores.add("El correo es requerido");
//            } else if (!Validaciones.esCorreo(usuario.getCorreo())) {
//                listaErrores.add("El correo no tiene el formato correcto");
//            }
//
//            if (usuario.getPassword().isEmpty()) {
//                listaErrores.add("La contraseña es requerida");
//            } else if (!Validaciones.esContraseña(usuario.getPassword())) {
//                listaErrores.add("La contraseña debe tener una longitud mínima de 8 caracteres"
//                        + " y debe contener al menos una mayuscula, "
//                        + "una minuscula y un numero o caracter especial");
//            }
//
//            if (!listaErrores.isEmpty()) {
//                request.setAttribute("listaErrores2", listaErrores);
//                request.setAttribute("url", urlmodel);
//                request.getRequestDispatcher(urlmodel).forward(request, response);
//            } else {
//                if (modelo.verificarCuenta(usuario) == null) {
//                    listaErrores.add("Usuario y/o clave incorrectos");
//                    request.setAttribute("listaErrores2", listaErrores);
//                    request.setAttribute("url", urlmodel);
//                    request.getRequestDispatcher(urlmodel).forward(request, response);
//                } else if (usuario.getIdConfirmacion() == 0) {
//                    listaErrores.add("El usuario no ha sido confirmado");
//                    request.setAttribute("listaErrores2", listaErrores);
//                    request.setAttribute("url", urlmodel);
//                    request.getRequestDispatcher(urlmodel).forward(request, response);
//                }
//            }
//        } catch (SQLException | ServletException | IOException ex) {
//            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
//        }
            if (usuario.getNombre().isEmpty()) {
                listaErrores.add("El nombre es requerido");
            }
            if (usuario.getApellido().isEmpty()) {
                listaErrores.add("El apellido es requerido");
            }

            if (usuario.getDireccion().isEmpty()) {
                listaErrores.add("La direccion es requerida");
            }

            if (usuario.getDui().isEmpty()) {
                listaErrores.add("El dui es requerido");
            } else if (!Validaciones.esDui(usuario.getDui())) {
                listaErrores.add("El dui no tiene el formato correcto");
            }

            if (usuario.getTelefono().isEmpty()) {
                listaErrores.add("El numero de telefono es requerido");
            } else if (!Validaciones.esTelefono(usuario.getTelefono())) {
                listaErrores.add("El numero de telefono no tiene el formato correcto");

            }

            if (!listaErrores.isEmpty()) {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("usuario", usuario);
                //request.setAttribute("url", urlmodel);
                request.getRequestDispatcher("/administrador.do?operacion=agregarUsuario").forward(request, response);
            } else {
                String cadenaAleatoria = UUID.randomUUID().toString();

                if (modeloUsuario.insertarUsuario(usuario, cadenaAleatoria) > 0) {
                    request.getSession().setAttribute("exito", "Usuario registrado "
                            + "existosamente. Se te ha enviado un correo para que "
                            + "confirmes tu cuenta");

                    String enlace = request.getRequestURL().toString()
                            + "?operacion=verificar&id=" + cadenaAleatoria;
                    String texto = "<div class='container2' style='color: white;border: solid black 2px;border-radius: 25px;width: 30%;padding: 1%;background-color: #e84d1c;'><h1 style=\"text-align: center;\">Bienvenido a BigShop</h1><div>"
                            + "<p>BigShop es tu nueva tienda oline, aquí te ofrecemos una gran variedad de productos a un buen precio, tambien tenemos los mejores productos de tus marcas favoritas, todo lo que decees esta aqui.</p>"
                            + "<p>Has sido registrado como administrador tu contraseña es: " + usuario.getPassword() + ".</p>"
                            + "<p>Para poder acceder a nuestro sitio debes validar tu usuario, da click al boton para empezar a comprar.</p>"
                            + "<a target='_blank' href='" + enlace + "'><button type='button' style='background-color: white;color: black;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;border: solid 1px #67656E;  font-family:fantasy;margin-left:30%;'   onmouseover='this.style.backgroundColor=\"#A5A1B3\" ' onmouseout='this.style.backgroundColor=\"\"'>Entrar</button></a></div></div>";
                    Correo correo = new Correo();
                    correo.setAsunto("Confirmacion de registro");
                    correo.setMensaje(texto);
                    correo.setDestinatario(usuario.getCorreo());
                    correo.enviarCorreo();
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarUsuarios");
                } else {
                    listaErrores.add("Este usuario ya existe");
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("/administrador.do?operacion=agregarUsuario").forward(request, response);
                }
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            request.setAttribute("usuario", modeloUsuario.obtenerUsuario(Integer.parseInt(id)));
            request.getRequestDispatcher("/administrador/modificarAdministrador.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void realizarModificacionUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErrores.clear();
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(Integer.parseInt(request.getParameter("codigo")));
            usuario.setCorreo(request.getParameter("correo"));
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setDireccion(request.getParameter("direccion"));
            usuario.setTipoUser(Integer.parseInt(request.getParameter("tipoUsuario")));
            usuario.setTelefono(request.getParameter("telefono"));

            if (usuario.getCorreo().isEmpty()) {
                listaErrores.add("El correo es requerido");
            } else if (!Validaciones.esCorreo(usuario.getCorreo())) {
                listaErrores.add("El correo no tiene el formato correcto");
            }

            if (usuario.getNombre().isEmpty()) {
                listaErrores.add("El nombre es requerido");
            }
            if (usuario.getApellido().isEmpty()) {
                listaErrores.add("El apellido es requerido");
            }

            if (usuario.getDireccion().isEmpty()) {
                listaErrores.add("La direccion es requerida");
            }

            if (usuario.getTelefono().isEmpty()) {
                listaErrores.add("El numero de telefono es requerido");
            } else if (!Validaciones.esTelefono(usuario.getTelefono())) {
                listaErrores.add("El numero de telefono no tiene el formato correcto");

            }

            if (!listaErrores.isEmpty()) {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/administrador.do?operacion=modificarUsuario").forward(request, response);
            } else {
                if (modeloUsuario.modificarUsuario(usuario) > 0) {
                    request.getSession().setAttribute("exito", "Usuario modificado exitosamente");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarUsuarios");
                } else {
                    request.getSession().setAttribute("fracaso", "Usuario no modificado exitosamente");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarUsuarios");
                }
            }
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deshabilitarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (modeloUsuario.deshabilitarUsuario(id) > 0) {
                request.getSession().setAttribute("exito", "Usuario ha sido deshabilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarUsuarios");
            } else {
                request.getSession().setAttribute("fracaso", "Usuario no ha sido deshabilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarUsuarios");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void habilitarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (modeloUsuario.habilitarUsuario(id) > 0) {
                request.getSession().setAttribute("exito", "Usuario ha sido habilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarUsuarios");
            } else {
                request.getSession().setAttribute("fracaso", "Usuario no ha sido habilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarUsuarios");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) {
        try {
            int estado = Integer.parseInt(request.getParameter("estado"));
            request.setAttribute("listarProducto", modeloProducto.listarProducto(estado));
            request.setAttribute("listarImagenes", modeloProducto.listarImagenesProducto());
            request.setAttribute("tab", estado);
            try {
                request.getRequestDispatcher("/administrador/listaProductos.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
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

            request.setAttribute("listarProducto", modeloProducto.listarProducto(estado));
            try {
                request.getRequestDispatcher("/administrador/listaProductos.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void grafica(HttpServletRequest request, HttpServletResponse response) {
        try {
            int anio = 0;
            if (request.getParameter("anio") != null) {
                request.getSession().setAttribute("anio", request.getParameter("anio"));
                //anio = Integer.parseInt(request.getParameter("anio"));
            } else {
                request.getSession().setAttribute("anio", anio);
            }

            System.out.println(anio);
            request.setAttribute("ventas", modeloPedido.ventasDiarias().size());
            request.setAttribute("ventasHoy", modeloPedido.ventaHoy());
            request.getRequestDispatcher("/administrador/estadisticaAdmin.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ventaanual(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        PrintWriter out = response.getWriter();
        int anio = Integer.parseInt(request.getSession().getAttribute("anio").toString());     
        int usuario = Integer.parseInt(request.getSession().getAttribute("usuario").toString());
        System.out.println(anio);
        //Necesito la cantidad de datos que devuelve la consulta
        request.setAttribute("ventas", modeloPedido.ventaAnual(anio).size());
        request.setAttribute("ventasHoy", modeloPedido.ventaHoy());
        //Arreglo con los datos que solicito en la consulta (AUN DEBO REVISAR BIEN LA CONSULTA)
        List<Pedido> pedidos = modeloPedido.ventaAnual(anio);

        //Como solo obtengo 2 datos, la fecha y monto, esta variable me servirá para mandar ambos como respuesta Json del Arreglo anterior
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < pedidos.size(); i++) {
            sb.append(pedidos.get(i).getFechaCompra() + "," + pedidos.get(i).getMontoTotal() + ":");
        }
        out.write(sb.toString());
    }

}
