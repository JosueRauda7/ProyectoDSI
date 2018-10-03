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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.beans.Categoria;
import sv.edu.udb.www.beans.SubCategoria;
import sv.edu.udb.www.model.CategoriasModel;
import sv.edu.udb.www.model.SubCategoriasModel;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author Ferh
 */
@WebServlet(name = "SubCategoriaController", urlPatterns = {"/SubCategoria.do"})
public class SubCategoriaController extends HttpServlet {

    ArrayList listaErrores = new ArrayList();
    SubCategoriasModel modelo = new SubCategoriasModel();
    CategoriasModel modeloCategorias = new CategoriasModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("operacion") == null) {
                listar(request, response);
                return;
            }

            String operacion = request.getParameter("operacion");

            switch (operacion) {
                case "listar":
                    listar(request, response);

                    break;
                case "nuevo":
                    nuevo(request, response);

                    break;

                case "insertar":

                    insertar(request, response);

                    break;

                case "obtener":

                    obtener(request, response);

                    break;

                case "modificar":

                    modificar(request, response);

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
            request.setAttribute("listaSubCategorias", modelo.listarSubCategorias());

            try {
                request.getRequestDispatcher("/administrador/listaSubCategorias.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(SubCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SubCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response) {
        listaErrores.clear();
        SubCategoria subCategoria = new SubCategoria();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));

        subCategoria.setSubCategoria(request.getParameter("subcategoria"));
        subCategoria.setCategoria(categoria);

        if (Validaciones.isEmpty(subCategoria.getSubCategoria())) {
            listaErrores.add("El nombre de la sub categoria es obligatorio");
        }

        if (Validaciones.isEmpty(String.valueOf(categoria.getIdCategoria()))) {
            listaErrores.add("Debe seleccionar una categoria");
        }

        if (listaErrores.size() > 0) {
            try {
                request.setAttribute("subCategoria", subCategoria);
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("/SubCategoria.do?operacion=nuevo").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(SubCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {

                if (modelo.insertarSubCategoria(subCategoria) > 0) {
                    request.getSession().setAttribute("exito", "Sub categoria registrada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/SubCategoria.do?operacion=listar");
                } else {
                    request.getSession().setAttribute("fracaso", "Dato no registrado. Ya existe esta sub categoria");
                    response.sendRedirect(request.getContextPath() + "/SubCategoria.do?operacion=listar");
                }
            } catch (SQLException | IOException ex) {
                Logger.getLogger(SubCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", modeloCategorias.listarCategorias());
            request.setAttribute("subCategoria", modelo.obtenerSubCategoria(request.getParameter("id")));
            request.getRequestDispatcher("/administrador/editarSubCategoria.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(SubCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificar(HttpServletRequest request, HttpServletResponse response) {
        listaErrores.clear();
        SubCategoria subCategoria = new SubCategoria();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));

        subCategoria.setSubCategoria(request.getParameter("subcategoria"));
        subCategoria.setCategoria(categoria);

        subCategoria.setIdSubCategoria(Integer.parseInt(request.getParameter("id")));

        if (Validaciones.isEmpty(subCategoria.getSubCategoria())) {
            listaErrores.add("El nombre de la sub categoria es obligatorio");
        }

        if (Validaciones.isEmpty(String.valueOf(categoria.getIdCategoria()))) {
            listaErrores.add("Debe seleccionar una categoria");
        }

        if (listaErrores.size() > 0) {
            try {
                request.setAttribute("subCategoria", subCategoria);
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("/SubCategoria.do?operacion=nuevo").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(SubCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {

                if (modelo.modificarSubCategoria(subCategoria) > 0) {
                    request.getSession().setAttribute("exito", "Sub categoria modificada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/SubCategoria.do?operacion=listar");
                } else {
                    request.getSession().setAttribute("fracaso", "Dato no modificado. Ya existe esta sub categoria");
                    response.sendRedirect(request.getContextPath() + "/SubCategoria.do?operacion=listar");
                }
            } catch (SQLException | IOException ex) {
                Logger.getLogger(SubCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deshabilitar(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            int id = Integer.parseInt(request.getParameter("id"));

            if (modelo.deshabilitarSubCategoria(id) > 0) {
                request.getSession().setAttribute("exito", "Sub categoria deshabilitada exitosamente");
                response.sendRedirect(request.getContextPath() + "/SubCategoria.do?operacion=listar");
            } else {
                request.getSession().setAttribute("fracaso", "No se pudo deshabilitar esta sub categoria");
                response.sendRedirect(request.getContextPath() + "/SubCategoria.do?operacion=listar");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(SubCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void habilitar(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            int id = Integer.parseInt(request.getParameter("id"));

            if (modelo.habilitarSubCategoria(id) > 0) {
                request.getSession().setAttribute("exito", "Sub categoria habilitada exitosamente");
                response.sendRedirect(request.getContextPath() + "/SubCategoria.do?operacion=listar");
            } else {
                request.getSession().setAttribute("fracaso", "No se pudo habilitar esta sub categoria");
                response.sendRedirect(request.getContextPath() + "/SubCategoria.do?operacion=listar");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(SubCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", modeloCategorias.listarCategorias());
            request.getRequestDispatcher("/administrador/nuevaSubCategoria.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(SubCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
