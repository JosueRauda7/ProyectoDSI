<%-- 
    Document   : verClientes
    Created on : 09-19-2018, 07:49:42 PM
    Author     : admi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Nombre empresa | Administrador</title>
        <jsp:include page="head.jsp"/>        
    </head>
    <body class="ecommerce">
        <jsp:include page="menuAdmin.jsp"/>
        <div class="main">
            <div class="container">
                <div class="col-md-12 col-sm-12">
                    <h1>Lista de Clientes</h1>
                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <div class="table-wrapper-responsive">
                                <table summary="Shopping cart">
                                    <c:if test="${empty requestScope.listaClientes}">
                                        <h1>No hay clientes registrados</h1>
                                    </c:if>
                                    <c:if test="${not empty requestScope.listaClientes}">
                                        <tr>
                                            <th class="goods-page-image">Nombre</th>
                                            <th class="goods-page-description">Apellidos</th>
                                            <th class="goods-page-ref-no">Correo</th>
                                            <th class="goods-page-quantity">Teléfono</th>
                                            <th class="goods-page-price">DUI</th>
                                            <th class="goods-page-total" colspan="2">Compras</th>
                                        </tr>
                                        <c:forEach items="${requestScope.listaClientes}" var="cliente">

                                            <tr>
                                                <td>${cliente.nombre}</td>
                                                <td>${cliente.apellido}</td>
                                                <td>${cliente.correo}</td>
                                                <td>${cliente.telefono}</td>
                                                <td>${cliente.dui}</td>


                                                <td>
                                                    <a class="btn btn-default add2cart" href="${pageContext.request.contextPath}/usuarios.do?operacion=comprasCliente&id=${cliente.idUsuario}">Ver compras</a>
                                                </td>
                                            </tr>

                                        </c:forEach>
                                    </c:if>
                                </table>
                            </div>

                        </div>

                    </div>
                </div>
                <!-- END CONTENT -->
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
