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
                    <h1>Editar Usuario</h1>
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
                        <form action="${pageContext.request.contextPath}/usuarios.do" method="POST" class="default-form" role="form">
                            <input type="hidden" name="operacion" value="realizarModificacionUsuario"/>
                            <input type="hidden" value="${requestScope.usuario.idUsuario}" name="codigo" id="codigo">
                            <div class="form-group">
                                <label for="nombre">Nombres:</label>
                                <input type="text" value="${requestScope.usuario.nombre}" class="form-control" name="nombre" id="nombre">
                            </div>
                            <div class="form-group">
                                <label for="apellido">Apellidos:</label>
                                <input type="text" value="${requestScope.usuario.apellido}" class="form-control" name="apellido" id="apellido">
                            </div>
                            <div class="form-group">
                                <label for="correo">Correo:</label>
                                <input type="text" value="${requestScope.usuario.correo}" class="form-control" name="correo" id="correo">
                            </div>
                            <div class="form-group">
                                <label for="telefono">Teléfono:</label>
                                <input type="text" value="${requestScope.usuario.telefono}" class="form-control" name="telefono" id="telefono">
                            </div>
                            <div class="form-group">
                                <label for="direccion">Dirección:</label>
                                <textarea class="form-control" style="max-width: 100%;" name="direccion" id="direccion">${requestScope.usuario.direccion}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="tipoUsuario">Tipo Usuario:</label>
                                <select class="form-control" id="tipoUsuario" name="tipoUsuario">
                                    <c:if test="${requestScope.usuario.tipoUser==1}">
                                        <option value="1">Administrador</option>
                                        <option value="3">Empleado Marketing</option>
                                        <option value="4">Empleado Productos</option>
                                        <option value="5">Empresa</option>
                                    </c:if>
                                    <c:if test="${requestScope.usuario.tipoUser==3}">
                                        <option value="3">Empleado Marketing</option>
                                        <option value="1">Administrador</option>
                                        <option value="4">Empleado Productos</option>
                                        <option value="5">Empresa</option>
                                    </c:if>
                                    <c:if test="${requestScope.usuario.tipoUser==4}">
                                        <option value="4">Empleado Productos</option>
                                        <option value="1">Administrador</option>
                                        <option value="3">Empleado Marketing</option>
                                        <option value="5">Empresa</option>
                                    </c:if>
                                    <c:if test="${requestScope.usuario.tipoUser==5}">
                                        <option value="5">Empresa</option>
                                        <option value="1">Administrador</option>
                                        <option value="3">Empleado Marketing</option>
                                        <option value="4">Empleado Productos</option>
                                    </c:if>
                                    <c:if test="${requestScope.usuario.tipoUser==null}">
                                        <option value="1">Administrador</option>
                                        <option value="3">Empleado Marketing</option>
                                        <option value="4">Empleado Productos</option>
                                        <option value="5">Empresa</option>
                                    </c:if>
                                </select>
                            </div>
                            <div class="padding-top-20">                  
                                <button type="submit" class="btn btn-primary">Aceptar</button>
                                <button type="submit" style="background-color: #F3565D;" class="btn btn-primary">Cancelar</button>
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
