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
import sv.edu.udb.www.model.ProductosModel;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.Validaciones;

@WebServlet(name = "EmpleadoMarketingController", urlPatterns = {"/empleadoMarketing.do"})
public class EmpleadoMarketingController extends HttpServlet {

    ArrayList listaErrores = new ArrayList();
    UsuariosModel modelo = new UsuariosModel();
    ProductosModel modeloProductos = new ProductosModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getSession().getAttribute("usuario") != null || request.getSession().getAttribute("tipousuario") != null || request.getSession().getAttribute("nombreUser") != null) {
                if (request.getSession().getAttribute("usuario") == null || !request.getSession().getAttribute("tipousuario").toString().equals("3")) {
                    response.sendRedirect(request.getContextPath() + "/public.do?operacion=publicIndex");
                    return;
                }
                if (request.getParameter("operacion") != null) {
                    String operacion = request.getParameter("operacion");

                    switch (operacion) {
                        case "nuevoCorreos":
                            nuevoCorreos(request, response);
                            break;
                        case "enviarCorreos":
                            enviarCorreos(request, response);
                            break;
                        case "verListaOfertas":
                            verListaOfertas(request,response);
                            break;
                    }
                } else {
                    request.getRequestDispatcher("/public.do?operacion=publicIndex").forward(request, response);
                }

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

    private void nuevoCorreos(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/empleadoMarketing/inicioMarketing.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarCorreos(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            listaErrores.clear();
            String asunto = request.getParameter("asunto");
            String mensaje = request.getParameter("mensaje");

            if (asunto.isEmpty()) {
                listaErrores.add("El asunto es requerido");
            }
            if (mensaje.isEmpty()) {
                listaErrores.add("El mensaje es requerido");
            }

            if (!listaErrores.isEmpty()) {
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("/empleadoMarketing/enviarCorreos.jsp").forward(request, response);
            } else {

                request.getSession().setAttribute("exito", "Los correos han sido enviados exitosamente");

                String enlace = request.getRequestURL().toString();
                String texto = "<div class='container2' style='color: white;border: solid black 2px;border-radius: 25px;width: 30%;padding: 1%;background-color: #e84d1c;'><h1 style=\"text-align: center;\">Bienvenido a BigShop</h1><div><p>BigShop es tu nueva tienda oline, aquí te ofrecemos una gran variedad de productos a un buen precio, tambien tenemos los mejores productos de tus marcas favoritas, todo lo que decees esta aqui.</p><p>Para poder acceder a nuestro sitio debes validar tu usuario, da click al boton para empezar a comprar.</p><a target='_blank' href='" + enlace + "'><button type='button' style='background-color: white;color: black;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;border: solid 1px #67656E;  font-family:fantasy;margin-left:30%;'   onmouseover='this.style.backgroundColor=\"#A5A1B3\" ' onmouseout='this.style.backgroundColor=\"\"'>Entrar</button></a></div></div>";
                Correo correo = new Correo();
                correo.setAsunto("<b>" + asunto + "</b>");
                correo.setMensaje(texto);

                correo.enviarCorreo(modelo.obtenerCorreosCliente());
                request.getSession().setAttribute("exito", "Tu usuario se creo exitosamente.");
                response.sendRedirect(request.getContextPath() + "/empleadoMarketing.do?operacion=nuevoCorreos");

            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoMarketingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verListaOfertas(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaOfertas", modeloProductos.listarOfertasSinPublicar());
            request.getRequestDispatcher("/empleadoMarketing/listaOfertasAPublicar.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoMarketingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
