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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.model.CategoriasModel;
import sv.edu.udb.www.model.ClientesModel;
import sv.edu.udb.www.model.ProductosModel;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author Emerson Torres
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/usuarios.do"})
public class UsuarioController extends HttpServlet {

    UsuariosModel modelo = new UsuariosModel();
    ArrayList listaErrores = new ArrayList();
    CategoriasModel CategoriaModel = new CategoriasModel();
    ProductosModel ProductoModel = new ProductosModel();

    ClientesModel clienteModel = new ClientesModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("operacion");
            if (request.getParameter("operacion").equals("cerrarSesion")) {
                cerrarSesion(request, response);
                return;
            }

            switch (operacion) {
                case "registroCliente":
                    registroClien(request, response);
                    break;
                case "verificar":
                    confirmar(request, response);
                    break;

                case "login":
                    login(request, response);

                    break;
                case "listarClientes":
                    listarClientes(request, response);
                    break;
                case "comprasCliente":
                    obtenerProductosCliente(request, response);

                    break;
                case "agregarAdministrador":
                    agregarAdministrador(request, response);
                    break;
                case "listarUsuarios":
                    listarUsuarios(request, response);
                    break;
                case "agregarUsuario":
                    agregarUsuario(request, response);
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

    private void registroClien(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            listaErrores.clear();
            Usuario usuario = new Usuario();
            usuario.setCorreo(request.getParameter("correo"));
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setDireccion(request.getParameter("direccion"));
            usuario.setDui(request.getParameter("dui"));
            usuario.setFecha_nac(request.getParameter("fechanac"));
            usuario.setPassword(request.getParameter("contrasena"));
            usuario.setTipoUser(2);
            usuario.setTelefono(request.getParameter("telefono"));
            String urlmodel = request.getParameter("url");

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

            if (usuario.getDui().isEmpty()) {
                listaErrores.add("El dui es requerido");
            } else if (!Validaciones.esDui(usuario.getDui())) {
                listaErrores.add("El dui no tiene el formato correcto");
            }

            if (usuario.getFecha_nac().isEmpty()) {
                listaErrores.add("La Fecha de nacimiento es requerida");
            }

            if (usuario.getTelefono().isEmpty()) {
                listaErrores.add("El numero de telefono es requerido");
            } else if (!Validaciones.esTelefono(usuario.getTelefono())) {
                listaErrores.add("El numero de telefono no tiene el formato correcto");

            }

            if (usuario.getPassword().isEmpty()) {
                listaErrores.add("La contraseña es requerida");
            } else if (!Validaciones.esContraseña(usuario.getPassword())) {
                listaErrores.add("La contraseña debe tener una longitud mínima de 8 caracteres"
                        + " y debe contener al menos una mayuscula, "
                        + "una minuscula y un numero o caracter especial");
            }
            if (!listaErrores.isEmpty()) {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("usuario", usuario);
                request.setAttribute("url", urlmodel);
                request.getRequestDispatcher(urlmodel).forward(request, response);
            } else {
                String cadenaAleatoria = UUID.randomUUID().toString();

                if (modelo.insertarUsuario(usuario, cadenaAleatoria) > 0) {
                    request.getSession().setAttribute("exito", "Usuario registrado "
                            + "existosamente. Se te ha enviado un correo para que "
                            + "confirmes tu cuenta");

                    String enlace = request.getRequestURL().toString()
                            + "?operacion=verificar&id=" + cadenaAleatoria;
                    String texto = "<div class='container2' style='color: white;border: solid black 2px;border-radius: 25px;width: 30%;padding: 1%;background-color: #e84d1c;'><h1 style=\"text-align: center;\">Bienvenido a BigShop</h1><div><p>BigShop es tu nueva tienda oline, aquí te ofrecemos una gran variedad de productos a un buen precio, tambien tenemos los mejores productos de tus marcas favoritas, todo lo que decees esta aqui.</p><p>Para poder acceder a nuestro sitio debes validar tu usuario, da click al boton para empezar a comprar.</p><a target='_blank' href='" + enlace + "'><button type='button' style='background-color: white;color: black;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;border: solid 1px #67656E;  font-family:fantasy;margin-left:30%;'   onmouseover='this.style.backgroundColor=\"#A5A1B3\" ' onmouseout='this.style.backgroundColor=\"\"'>Entrar</button></a></div></div>";
                    Correo correo = new Correo();
                    correo.setAsunto("Confirmacion de registro");
                    correo.setMensaje(texto);
                    correo.setDestinatario(usuario.getCorreo());
                    correo.enviarCorreo();
                    response.sendRedirect(request.getContextPath() + "/" + urlmodel);
                } else {
                    listaErrores.add("Este usuario ya existe");
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("usuario", usuario);
                    request.setAttribute("url", urlmodel);
                    request.getRequestDispatcher(urlmodel).forward(request, response);
                }
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void confirmar(HttpServletRequest request, HttpServletResponse response) {
        try {
            modelo.confirmarCuenta(request.getParameter("id"));
            request.getSession().setAttribute("exito", "Cuenta verificada exitosamente, " + "ya puedes iniciar session");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaClientes", modelo.listarClientes());

            try {
                request.getRequestDispatcher("/administrador/verClientes.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obtenerProductosCliente(HttpServletRequest request, HttpServletResponse response) {
        try {

            int id = Integer.parseInt(request.getParameter("id"));

            request.setAttribute("cliente", modelo.obtenerCliente(id));
            request.setAttribute("listaPedidos", modelo.listarPedidos(id));
            request.setAttribute("listaDetallePedidos", modelo.listarProductosCliente(id));
            request.getRequestDispatcher("/administrador/comprasCliente.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        try {
            listaErrores.clear();
            Usuario usuario = new Usuario();
            usuario.setCorreo(request.getParameter("correo"));
            usuario.setPassword(request.getParameter("password"));
            String urlmodel = request.getParameter("url");

            if (usuario.getCorreo().isEmpty()) {
                listaErrores.add("El correo es requerido");
            } else if (!Validaciones.esCorreo(usuario.getCorreo())) {
                listaErrores.add("El correo no tiene el formato correcto");
            }

            if (usuario.getPassword().isEmpty()) {
                listaErrores.add("La contraseña es requerida");
            } else if (!Validaciones.esContraseña(usuario.getPassword())) {
                listaErrores.add("La contraseña debe tener una longitud mínima de 8 caracteres"
                        + " y debe contener al menos una mayuscula, "
                        + "una minuscula y un numero o caracter especial");
            }

            if (!listaErrores.isEmpty()) {
                request.setAttribute("listaErrores2", listaErrores);
                request.setAttribute("url", urlmodel);
                request.getRequestDispatcher(urlmodel).forward(request, response);
            } else {
                usuario = modelo.verificarCuenta(usuario);
                if (usuario == null) {
                    listaErrores.add("Usuario y/o correo incorrectos");
                    request.setAttribute("listaErrores2", listaErrores);
                    request.setAttribute("url", urlmodel);
                    request.getRequestDispatcher(urlmodel).forward(request, response);
                } else if (usuario.getIdConfirmacion() == 0) {
                    listaErrores.add("La cuenta no ha sido validada");
                    request.setAttribute("listaErrores2", listaErrores);
                    request.setAttribute("url", urlmodel);
                    request.getRequestDispatcher(urlmodel).forward(request, response);
                } else if (usuario.getIdConfirmacion() == 1) {

                    request.getSession().setAttribute("usuario", usuario.getIdUsuario());
                    request.getSession().setAttribute("tipousuario", usuario.getTipoUser());
                    request.getSession().setAttribute("nombreUser", modelo.obtenerNombreUsuario(usuario.getIdUsuario()));
                    switch (usuario.getTipoUser()) {
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
                        default:
                            listaErrores.add("Usuario no encontrado");
                            request.setAttribute("listaErrores2", listaErrores);
                            request.setAttribute("url", urlmodel);
                            request.getRequestDispatcher(urlmodel).forward(request, response);
                            break;
                    }
                }
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //INICIO PARTE RAUDA
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
                request.getRequestDispatcher("/usuarios.do?operacion=agregarUsuario").forward(request, response);
            } else {
                String cadenaAleatoria = UUID.randomUUID().toString();

                if (modelo.insertarUsuario(usuario, cadenaAleatoria) > 0) {
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
                    response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=listarUsuarios");
                } else {
                    listaErrores.add("Este usuario ya existe");
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("/usuarios.do?operacion=agregarUsuario").forward(request, response);
                }
            }
        } catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaUsuarios", modelo.listarUsuarios());
            request.getRequestDispatcher("/administrador/verAdministradores.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/administrador/nuevoAdministrador.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            request.setAttribute("usuario", modelo.obtenerUsuario(Integer.parseInt(id)));
            request.getRequestDispatcher("/administrador/modificarAdministrador.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
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
                request.getRequestDispatcher("/usuarios.do?operacion=modificarUsuario").forward(request, response);
            } else {
                if (modelo.modificarUsuario(usuario) > 0) {
                    request.getSession().setAttribute("exito", "Usuario modificado exitosamente");
                    response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=listarUsuarios");
                } else {
                    request.getSession().setAttribute("fracaso", "Usuario no modificado exitosamente");
                    response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=listarUsuarios");
                }
            }
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void habilitarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (modelo.habilitarUsuario(id) > 0) {
                request.getSession().setAttribute("exito", "Usuario ha sido habilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=listarUsuarios");
            } else {
                request.getSession().setAttribute("fracaso", "Usuario no ha sido habilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=listarUsuarios");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deshabilitarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (modelo.deshabilitarUsuario(id) > 0) {
                request.getSession().setAttribute("exito", "Usuario ha sido deshabilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=listarUsuarios");
            } else {
                request.getSession().setAttribute("fracaso", "Usuario no ha sido deshabilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/usuarios.do?operacion=listarUsuarios");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("usuario", null);
            request.getSession().setAttribute("tipousuario", null);
            request.getSession().setAttribute("nombreUser", null);
            request.getRequestDispatcher("/public.do?operacion=publicIndex").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //FIN PARTE RAUDA

}
