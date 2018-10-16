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
                <h1>Nueva Categoría</h1>

                <div class="col-md-12 col-sm-12">
                    <div class="margin-bottom-65"></div>
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
                            </div><br>
                        </c:if>
                        <!-- BEGIN FORM-->
                        <form action="${pageContext.request.contextPath}/categorias.do" method="POST" class="default-form" role="form" enctype="multipart/form-data">
                            <input type="hidden" name="operacion" value="guardar"/>
                            <input type="hidden" value="${requestScope.category.idCategoria}" name="codigo" id="codigo">
                            
                            <div class="form-group">
                                <label for="nombre">Nombre Categoría:</label>
                                <input type="text" class="form-control" value="${requestScope.category.categoria}" name="nombre" id="nombre">
                            </div>
                            <div class="form-group">
                                <label for="estado">Estado de Categoría:</label>
                                <select class="form-control" id="estado" name="estado">
                                    <c:choose>
                                        <c:when test="${requestScope.category.estadoCategoria.idEstadoCategoria eq '2'}">
                                            <option value="2">Inhabilitado</option>
                                            <option value="1">Habilitado</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="1">Habilitado</option>
                                            <option value="2">Inhabilitado</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <label for="imagen">Imagen</label>
                                <input data-language="es" type="file" name="archivo" id="imagen" class="form-control file file-loading" data-allowed-file-extensions='["jpg", "png"]'
                                       value="${base}/images/${requestScope.category.urlCategoria}/"/>
                            </div>
                            
                            <div class="padding-top-20">                  
                                <button type="submit" class="btn btn-primary">Aceptar</button>
                                <a href="${pageContext.request.contextPath}/categorias.do?operacion=listar" style="background-color: #F3565D;color:white;" class="btn btn-primary">Cancelar</a>
                            </div>
                        </form>
                        <!-- END FORM--> 
                    </div>
                </div>
            </div>
        </div>
        <br><br><br><br><br>
        <div class="margin-bottom-65"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
