<%-- 
    Document   : verCategorias
    Created on : 09-19-2018, 11:51:13 PM
    Author     : admi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <h1>Lista de Categorias</h1>
                    <a href="${pageContext.request.contextPath}/administrador.do?operacion=nuevaCategoria" class="btn btn-primary">Nueva Categoria</a>
                    <br><br>
                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <div class="table-wrapper-responsive">
                                <table id="tabla" class="table table-responsive" summary="Shopping cart">
                                    <thead>
                                        <tr>
                                            <th class="goods-page-total">ID</th>
                                            <th class="goods-page-image">Categoria</th>
                                            <th class="goods-page-description">Estado Categoría</th>
                                            <th class="goods-page-total">Operaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listaCategorias}" var="categoria">
                                            <tr>
                                                <td class="goods-page-total" style="width: 5%;">
                                                    ${categoria.idCategoria}
                                                </td>
                                                <td class="goods-page-image" style="width: 30%;">
                                                    ${categoria.categoria}
                                                </td>
                                                <td class="goods-page-description" style="width: 40%;">
                                                    <c:choose>
                                                        <c:when test="${categoria.estadoCategoria.idEstadoCategoria eq '1'}">
                                                            Habilitado
                                                        </c:when>
                                                        <c:otherwise>
                                                            Inhabilitado
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="goods-page-total">
                                                    <c:choose>
                                                        <c:when test="${categoria.estadoCategoria.idEstadoCategoria eq '1'}">
                                                            <a onclick="javascript:modificar('${categoria.idCategoria}')" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a>
                                                            <a onclick="javascript:deshabilitar('${categoria.idCategoria}')" style="margin-left: 2%; color:white" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a onclick="javascript:modificar('${categoria.idCategoria}')" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a>
                                                            <a onclick="javascript:habilitar('${categoria.idCategoria}')" style="margin-left: 2%; color:white" class="btn btn-success"><span class="glyphicon glyphicon-check"></span></a>
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
                        location.href = 'administrador.do?operacion=deshabilitarCategoria&id=' + id;
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
                        location.href = 'administrador.do?operacion=habilitarCategoria&id=' + id;
                    }
                });
            }
            ;
            function modificar(id) {
                location.href = 'administrador.do?operacion=modificarCategoria&id=' + id;
            }
            ;
        </script>
        <div class="margin-bottom-40"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
