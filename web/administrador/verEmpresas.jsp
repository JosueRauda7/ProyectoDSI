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
                    <h1>Lista de Empresas</h1>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/administrador.do?operacion=nuevaEmpresa">Nueva empresa</a>
                    <br><br>
                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <div class="table-wrapper-responsive">
                                <table id="tabla" summary="Shopping cart">
                                    <thead>
                                        <tr>
                                            <th class="goods-page-description text-center">Empresa</th>
                                            <th class="goods-page-description text-center">Comisión</th>
                                            <th class="goods-page-ref-no text-center">Contacto</th>
                                            <th class="goods-page-description text-center">Estado</th>
                                            <th class="goods-page-image text-center ">Logo</th>
                                            <th class="goods-page-total text-center">Operaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listarEmpresas}" var="empresa">
                                            <tr>
                                                <td class="goods-page-image text-center">
                                                    ${empresa.empresa}
                                                </td>
                                                <td class="goods-page-description text-center">
                                                    ${empresa.comision}
                                                </td>
                                                <td class="goods-page-ref-no text-center">
                                                    ${empresa.usuario.nombre} ${empresa.usuario.apellido}
                                                </td>
                                                <td class="goods-page-price text-center">
                                                    ${empresa.estadoEmpresa.estadoEmpresa}
                                                </td>
                                                <td class="goods-page-image text-center">
                                                    <img height="100px" src="${pageContext.request.contextPath}/images/${empresa.urlEmpresa}"/>
                                                </td>
                                                <td class="goods-page text-center">
                                                    <a class="btn btn-info glyphicon glyphicon-edit" title="modificar" style="padding: 5%; color:white;"
                                                       href="${pageContext.request.contextPath}/administrador.do?operacion=obtenerEmpresa&id=${empresa.idEmpresa}"></a>
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
