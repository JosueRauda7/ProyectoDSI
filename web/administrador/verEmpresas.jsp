<%-- 
    Document   : verAdministradores
    Created on : 09-19-2018, 08:08:39 PM
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
            <div class="margin-bottom-50"><div class="container"></div></div>
            <div class="container">
                <div class="col-md-12 col-sm-12">
                    <h1>Lista de Usuarios</h1>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/usuarios.do?operacion=agregarUsuario">Nuevo Usuario</a>
                    <br><br>
                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <div class="table-wrapper-responsive">
                                <table id="tabla" summary="Shopping cart">
                                    <thead>
                                        <tr>
                                            <th class="goods-page-image">Nombre</th>
                                            <th class="goods-page-description">Apellidos</th>
                                            <th class="goods-page-ref-no">Correo</th>
                                            <th class="goods-page-quantity">Teléfono</th>
                                            <th class="goods-page-quantity">Tipo Usuario</th>
                                            <th class="goods-page-total" colspan="2">Operaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.listaUsuarios}" var="usuario">
                                        <tr>
                                            <td class="goods-page-image">
                                                ${usuario.nombre}
                                            </td>
                                            <td class="goods-page-description">
                                                ${usuario.apellido}
                                            </td>
                                            <td class="goods-page-ref-no">
                                                ${usuario.correo}
                                            </td>
                                            <td class="goods-page-price">
                                                ${usuario.telefono}
                                            </td>
                                            <td class="goods-page-price">
                                        <c:choose>
                                            <c:when test="${usuario.tipoUser == 1}">
                                                Administrador
                                            </c:when>
                                            <c:when test="${usuario.tipoUser == 3}">
                                                Empleado Marketing
                                            </c:when>
                                            <c:when test="${usuario.tipoUser == 4}">
                                                Empleado Productos
                                            </c:when>
                                            <c:when test="${usuario.tipoUser == 5}">
                                                Empresa
                                            </c:when>
                                                <c:otherwise>
                                                    
                                                </c:otherwise>
                                        </c:choose>
                                        </td>
                                        <td class="goods-page-total">
                                            <c:choose>
                                                        <c:when test="${usuario.confirmado eq '1'}">
                                                            <a onclick="javascript:modificar('${usuario.idUsuario}')" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a>
                                                            <a onclick="javascript:deshabilitar('${usuario.idUsuario}')" style="margin-left: 2%; color:white" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a>
                                                        </c:when>
                                                        <c:when test="${usuario.confirmado eq '0'}">
                                                            Cuenta no confirmada
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a onclick="javascript:modificar('${usuario.idUsuario}')" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a>
                                                            <a onclick="javascript:habilitar('${usuario.idUsuario}')" style="margin-left: 2%; color:white" class="btn btn-success"><span class="glyphicon glyphicon-check"></span></a>
                                                        </c:otherwise>
                                                    </c:choose>
                                        </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>

                    </div>
                </div>
                <!-- END CONTENT -->
            </div>
        </div>
        
        <script>
            <c:if test="${not empty exito}">
                swal({
                    title: "Bien!",
                    text: "${exito}",
                    icon: "success",
                });
                <c:set var="exito" value="" scope="session"/>
            </c:if>

            <c:if test="${not empty fracaso}">
                swal({
                    title: "Ups!",
                    text: "${fracaso}",
                    icon: "error",
                });
                <c:set var="fracaso" value="" scope="session"/>
            </c:if>
                    function deshabilitar(id) {
                    swal({
                    title: '¿Seguro que lo deseas deshabilitar?',
                            text: "Si aceptas, lo puedes volver habilitar!",
                            icon: 'warning',
                            buttons: true,
                            dangerMode: true,
                    }).then((willDelete) => {
                    if (willDelete) {
                    location.href = 'usuarios.do?operacion=deshabilitarUsuario&id=' + id;
                    }
                    });
                    }
                    ;
            function habilitar(id) {
            swal({
            title: '¿Seguro que lo deseas habilitar?',
                    text: "Si aceptas, lo puedes volver deshabilitar!",
                    icon: 'warning',
                    buttons: true,
                    dangerMode: true,
            }).then((willDelete) => {
            if (willDelete) {
            location.href = 'usuarios.do?operacion=habilitarUsuario&id=' + id;
            }
            });
            }
            ;
            function modificar(id) {
            location.href = 'usuarios.do?operacion=modificarUsuario&id=' + id;
            }
            ;
                    </script>
                    <div class="margin-bottom-60"></div>
                        <jsp:include page="footer.jsp"/>
                        </body>
                        </html>
