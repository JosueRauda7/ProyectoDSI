<%-- 
    Document   : verCategorias
    Created on : 09-19-2018, 11:51:13 PM
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
                    <h1>Lista de Categorias</h1>
                    <a href="${pageContext.request.contextPath}/categorias.do?operacion=nuevo" class="btn btn-primary">Nueva Categoria</a>
                    <br><br>
                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <div class="table-wrapper-responsive">
                                <table id="tabla" class="table table-responsive" summary="Shopping cart">
                                    <thead>
                                        <tr>
                                            <th class="goods-page-image">Categoria</th>
                                            <th class="goods-page-description">Estado Categoría</th>
                                            <th class="goods-page-total">Operaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listaCategorias}" var="categoria">
                                            <tr>
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
                                                    <a onclick="javascript:modificar('${categoria.idCategoria}')" class="btn btn-default">Editar</a>
                                                    <a onclick="javascript:deshabilitar('${categoria.idCategoria}')" style="margin-left: 2%; color:white" class="btn btn-danger">Deshabilitar</a>
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
            $(document).ready(function () {
                $('#tabla').DataTable();
            });
            function eliminar(id) {
                alertify.confirm('¿Desea eliminar este libro?', function (e) {
                    if (e) {
                        location.href = 'categorias.do?operacion=deshabilitar&id=' + id;
                    }
                });
            }
            ;
            function modificar(id) {
                location.href = 'categorias.do?operacion=modificar&id=' + id;
            };
        </script>
        <div class="margin-bottom-40"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
