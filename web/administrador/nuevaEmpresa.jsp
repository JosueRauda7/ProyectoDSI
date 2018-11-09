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
                <!-- BEGIN FORM-->
                <form action="${pageContext.request.contextPath}/administrador.do" method="POST" class="default-form" role="form"
                      enctype="multipart/form-data">
                    <input type="hidden" name="operacion" value="insertarEmpresa"/>
                    <h1 class="text-center">Nueva Empresa</h1>
                    <div class="col-md-6">

                        <div class="content-page col-md-12">
                            <span style="color: #e94d1c;">Paso 1. </span><p style="color: gray; display: inline-block;"> Ingrese los datos correspondientes sobre la empresa</p>
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

                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="empresa">Nombre de la empresa:</label>
                                    <input type="text" value="${empresa.empresa}" class="form-control" name="empresa" id="empresa">
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

                            <!-- END FORM--> 
                        </div>
                    </div>
                    <div class="col-md-6 ">
                        <div class="content-page">
                            <span style="color: #e94d1c;">Paso 2. </span><p style="color: gray; display: inline-block;"> Ingrese los datos correspondientes del contacto</p>
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
                                <label for="dui">DUI:</label>
                                <input type="text" value="${requestScope.usuario.dui}" class="form-control" name="dui" id="dui">
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
                        </div>
                    </div>
                    <div class=" form-group text-center col-md-12" style="margin-top: 2%;">                  
                        <button type="submit" class="btn btn-primary">Ingresar empresa</button>                                
                    </div>
                </form>
            </div>
        </div>

        <div class="footer text-center" style="position: absolute; bottom: auto; width: 100%;">
            <div class="container">
                <div class="row">
                    <!-- BEGIN COPYRIGHT -->
                    <div class="col-md-12 padding-top-10">
                        2018 © Universidad Don Bosco. Derechos reservados. 
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
