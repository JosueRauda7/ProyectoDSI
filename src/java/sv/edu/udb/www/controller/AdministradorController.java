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
import sv.edu.udb.www.beans.SubCategoria;
import sv.edu.udb.www.model.CategoriasModel;
import sv.edu.udb.www.model.EmpresasModel;
import sv.edu.udb.www.model.SubCategoriasModel;
import sv.edu.udb.www.model.UsuariosModel;
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
    SubCategoriasModel modeloSubCategoria = new SubCategoriasModel();
    ArrayList listaErrores = new ArrayList();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*if (request.getSession().getAttribute("usuario") == null || !request.getSession().getAttribute("tipousuario").toString().equals("1")) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }*/

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
                    case "listarEmpresas":
                        listarEmpresas(request, response);
                        break;
                    case "nuevaEmpresa":
                        nuevaEmpresa(request, response);
                        break;
                    case "obtenerEmpresa":
                        obtenerEmpresa(request, response);
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
            listaErrores.clear();
            Empresa empresa = new Empresa();
            empresa.setEmpresa(multi.getParameter("empresa"));
            empresa.setComision(multi.getParameter("comision"));
            empresa.setIdUsuario(Integer.parseInt(multi.getParameter("contacto")));
            empresa.setIdEstadoEmpresa(1);

            if (Validaciones.isEmpty(multi.getParameter("empresa"))) {
                listaErrores.add("El nombre de la empresa es obligatorio");
            }
            if (Validaciones.isEmpty(multi.getParameter("comision"))) {
                listaErrores.add("Debe ingresar una comision");
            } else if (!Validaciones.esDecimal(multi.getParameter("comision"))) {
                listaErrores.add("Debe ingresar un numero decimal");
            } else if (!Validaciones.esDecimalPositivo(multi.getParameter("comision"))) {
                listaErrores.add("La comision debe tener un valor positivo");
            } else if (Double.parseDouble(multi.getParameter("comision")) > 50.0) {
                listaErrores.add("La comision no puede tener un valor mayor a 50");
            }

            if (multi.getParameter("contacto") == null) {
                listaErrores.add("No hay contactos disponibles");
            }

            if (multi.getFile("archivo") == null) {
                listaErrores.add("La imagen es obligatoria");
            } else {
                File ficheroTemp = multi.getFile("archivo");
                empresa.setUrlEmpresa(ficheroTemp.getName());
            }

            if (listaErrores.isEmpty()) {
                if (modeloEmpresa.insertarEmpresa(empresa) == 1) {
                    request.getSession().setAttribute("exito", "Empresa registrada existosamente.");
                } else {
                    request.getSession().setAttribute("fracaso", "Ocurrio un error, no se pudo registrar la empresa...");
                }
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarEmpresas");
            } else {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("listaContactoDisponible", modeloUsuario.listaContactoDisponible());
                request.setAttribute("empresa", empresa);
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
            if (empresa != null) {
                request.setAttribute("empresa", empresa);
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
            listaErrores.clear();
            Empresa empresa = new Empresa();
            empresa.setEmpresa(multi.getParameter("empresa"));
            empresa.setComision(multi.getParameter("comision"));
            empresa.setIdEstadoEmpresa(1);

            int codigo = Integer.parseInt(multi.getParameter("id"));

            if (Validaciones.isEmpty(multi.getParameter("empresa"))) {
                listaErrores.add("El nombre de la empresa es obligatorio");
            }
            if (Validaciones.isEmpty(multi.getParameter("comision"))) {
                listaErrores.add("Debe ingresar una comision");
            } else if (!Validaciones.esDecimal(multi.getParameter("comision"))) {
                listaErrores.add("Debe ingresar un numero decimal");
            } else if (!Validaciones.esDecimalPositivo(multi.getParameter("comision"))) {
                listaErrores.add("La comision debe tener un valor positivo");
            } else if (Double.parseDouble(multi.getParameter("comision")) > 50.0) {
                listaErrores.add("La comision no puede tener un valor mayor a 50");
            }

            if (multi.getParameter("contacto") == null) {
                listaErrores.add("No hay contactos disponibles");
            } else {
                empresa.setIdUsuario(Integer.parseInt(multi.getParameter("contacto")));
            }

            if (multi.getFile("archivo") == null) {
                listaErrores.add("La imagen es obligatoria");
            } else {
                File ficheroTemp = multi.getFile("archivo");
                empresa.setUrlEmpresa(ficheroTemp.getName());
            }

            if (listaErrores.isEmpty()) {
                if (modeloEmpresa.modificarEmpresa(empresa, codigo) == 1) {
                    request.getSession().setAttribute("exito", "Empresa modificada existosamente.");
                } else {
                    request.getSession().setAttribute("fracaso", "Ocurrio un error, no se pudo modificar la empresa...");
                }
                response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarEmpresas");
            } else {
                request.setAttribute("listaErrores", listaErrores);
                request.setAttribute("listaContactoDisponible", modeloUsuario.listaContactoDisponible());
                request.setAttribute("empresa", empresa);
                request.getRequestDispatcher("/administrador/nuevaEmpresa.jsp").forward(request, response);
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
            request.setAttribute("listaSubCategorias", modeloSubCategoria.listarSubCategorias());
            try {
                request.getRequestDispatcher("/administrador/listaSubCategorias.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void nuevaSubCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", modeloSubCategoria.listarCategorias());
            request.getRequestDispatcher("/administrador/nuevaSubCategoria.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obtenerSubCategoria(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("listaCategorias", modeloCategoria.listarCategorias());
            request.setAttribute("subCategoria", modeloSubCategoria.obtenerSubCategoria(request.getParameter("id")));
            request.getRequestDispatcher("/administrador/editarSubCategoria.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
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
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias");
                } else {
                    request.getSession().setAttribute("fracaso", "Dato no registrado. Ya existe esta sub categoria");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias");
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
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias");
                } else {
                    request.getSession().setAttribute("fracaso", "Dato no modificado. Ya existe esta sub categoria");
                    response.sendRedirect(request.getContextPath() + "/administrador.do?operacion=listarSubCategorias");
                }
            } catch (SQLException | IOException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
