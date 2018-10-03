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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("operacion");
            switch (operacion) {
                case "registroCliente":
                    registroClien(request, response);
                    break;
                case "verificar":
                    confirmar(request, response);
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
                listaErrores.add("La direccion es querida");
            }

            if (usuario.getDui().isEmpty()) {
                listaErrores.add("El dui es querido");
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

                    String texto = "Te has registrado exitosamente.<br>";
                    texto += "Para confirmar tu cuenta debes dar click ";

                    String enlace = request.getRequestURL().toString()
                            + "?operacion=verificar&id=" + cadenaAleatoria;
                    texto += "<a target='a_blank' "
                            + "href='" + enlace + "'>aqui</a>";

                    Correo correo = new Correo();
                    correo.setAsunto("Confirmacion de registro");
                    correo.setMensaje(texto);
                    correo.setDestinatario(usuario.getCorreo());
                    correo.enviarCorreo();
                    response.sendRedirect(request.getContextPath() +"/"+ urlmodel);
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

}
