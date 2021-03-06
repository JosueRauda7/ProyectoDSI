/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Categoria;
import sv.edu.udb.www.beans.EstadoCategoria;
import sv.edu.udb.www.model.CategoriasModel;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author admi
 */
@WebServlet(name = "CategoriasController", urlPatterns = {"/categorias.do"})
public class CategoriasController extends HttpServlet {

    CategoriasModel model = new CategoriasModel();
    ArrayList<String> listaErrores = new ArrayList();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("operacion");
            switch (operacion) {
                case "listar":
                    listar(request, response);
                    break;
                case "nuevo":
                    nuevo(request, response);
                    break;
                case "modificar":
                    modificar(request,response);
                    break;
                case "deshabilitar":
                    deshabilitar(request, response);
                    break;
                case "habilitar":
                    habilitar(request, response);
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
        listaErrores.clear();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            String directorio = getServletContext().getRealPath("/images");
            MultipartRequest multi = new MultipartRequest(request, directorio, 1 * 1024 * 1024, new DefaultFileRenamePolicy());
            String operacion = multi.getParameter("operacion");
            if (operacion.equals("agregar")) {
                agregar(multi, request, response);
            } else if (operacion.equals("guardar")) {
                guardar(multi, request, response);
            }
        } catch (FileNotFoundException e) {
            out.println("La ruta de ubicacion no existe");
        }
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

    private void agregar(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) {
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
                request.getRequestDispatcher("/categorias.do?operacion=nuevo").forward(request, response);
            } else {
                if (model.insertarCategoria(categoria) > 0) {
                    request.getSession().setAttribute("exito", "Categoría registrada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/categorias.do?operacion=listar");
                } else {
                    request.getSession().setAttribute("fracaso", "Categoría no registrada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/categorias.do?operacion=listar");
                }
            }
        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", model.listarCategorias());
            request.getRequestDispatcher("/administrador/verCategorias.jsp").forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/administrador/nuevaCategoria.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            request.setAttribute("category", model.obtenerCategoria(id));
            request.getRequestDispatcher("/administrador/modificarCategoria.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardar(MultipartRequest multi, HttpServletRequest request, HttpServletResponse response) {
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
            }else{
                categoria.setUrlCategoria(null);
            }

            if (listaErrores.size() > 0) {
                request.setAttribute("category", categoria);
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("/categorias.do?operacion=modificar").forward(request, response);
            } else {
                if (model.modificarCategoria(categoria) > 0) {
                    request.getSession().setAttribute("exito", "Categoría registrada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/categorias.do?operacion=listar");
                } else {
                    request.getSession().setAttribute("fracaso", "Categoría no registrada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/categorias.do?operacion=listar");
                }
            }
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deshabilitar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (model.deshabilitarCategoria(id) > 0) {
                request.getSession().setAttribute("exito", "Categoría ha sido deshabilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/categorias.do?operacion=listar");
            } else {
                request.getSession().setAttribute("fracaso", "Categoría no ha sido deshabilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/categorias.do?operacion=listar");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void habilitar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (model.habilitarCategoria(id) > 0) {
                request.getSession().setAttribute("exito", "Categoría ha sido habilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/categorias.do?operacion=listar");
            } else {
                request.getSession().setAttribute("fracaso", "Categoría no ha sido habilitado exitosamente");
                response.sendRedirect(request.getContextPath() + "/categorias.do?operacion=listar");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
