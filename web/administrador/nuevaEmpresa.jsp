<%-- 
    Document   : nuevoAdministrador
    Created on : 09-19-2018, 08:19:24 PM
    Author     : admi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BigShop | Administrador</title>
        <jsp:include page="head.jsp"/>        
    </head>
    <body class="ecommerce">
        <jsp:include page="menuAdmin.jsp"/>
        <div class="main">
            <div class="container">
                <div class="col-md-12 col-sm-12">
                    <h1>Nueva Empresa</h1>
                    <div class="col-lg-3"></div>
                    <div class="content-page col-lg-6">
                        <c:if test="${not empty requestScope.listaErrores}">
                            <div class="row col-lg-12">
                                <div class="alert alert-danger">
                                    <ul>
                                        <c:forEach items="${requestScope.listaErrores}" var="error">
                                            <li>${error}</li>
                                            </c:forEach> 
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        <!-- BEGIN FORM-->
                        <form action="${pageContext.request.contextPath}/administrador.do" method="POST" class="default-form" role="form"
                              enctype="multipart/form-data">
                            <input type="hidden" name="operacion" value="insertarEmpresa"/>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="empresa">Empresa:</label>
                                    <input type="text" value="${empresa.empresa}" class="form-control" name="empresa" id="empresa">
                                </div>
                                <div class="form-group">
                                    <label for="comision">Comisión:</label>
                                    <input type="text" value="${empresa.comision}" class="form-control" name="comision" id="comision">
                                </div>
                                <div class="form-group">
                                    <label for="contacto">Contacto:</label>
                                    <select id="contacto" name="contacto" class="form-control">
                                        <c:forEach var="contacto" items="${requestScope.listaContactoDisponible}">
                                            <option value="${contacto.idUsuario}">${contacto.nombre} ${contacto.apellido}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="imagen">Logo de la empresa</label>
                                    <input data-language="es" type="file" name="archivo" id="imagen" class="form-control file file-loading" data-allowed-file-extensions='["jpg", "png"]'
                                           value="${base}/images/${empresa.urlEmpresa}/"/>
                                </div>
                            </div>
                            <div class="padding-top-20">                  
                                <button type="submit" class="btn btn-primary">Aceptar</button>                                
                            </div>
                        </form>
                        <!-- END FORM--> 
                    </div>
                </div>
            </div>
        </div>
        <div class="margin-bottom-40"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
