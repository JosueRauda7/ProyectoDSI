<%-- 
    Document   : comprasCliente
    Created on : 18-sep-2018, 18:24:00
    Author     : Ferh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Empresa</title>

        <jsp:include page="head.jsp"/>        
        
        <link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Theme styles END -->
    </head>
    <!-- Head END -->

    <body class="ecommerce">


        <jsp:include page="menuEmpresa.jsp"/>


        <div class="container">
            <div class="product-page-content">
                <a style="margin-bottom: 2%;" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=nuevo">Nuevo producto</a>
                <ul id="myTab" class="nav nav-tabs">                    
                    <li <c:if test="${requestScope.tab eq 1}"> class="active"</c:if> ><a href="${base}/empresas.do?operacion=listar&estado=1" data-toggle="tab">Listar en espera</a></li>
                    <li <c:if test="${requestScope.tab eq 2}"> class="active"</c:if> ><a href="${base}/empresas.do?operacion=listar&estado=2" data-toggle="tab">Listar activos</a></li>
                    <li <c:if test="${requestScope.tab eq 3}"> class="active"</c:if> ><a href="${base}/empresas.do?operacion=listar&estado=3" data-toggle="tab">Listar rechazados</a></li>                    
                    </ul>

                    <div class="tab-pane fade in active" id="activos">

                        <div class="goods-page">
                            <div class="goods-data clearfix">
                                <div class="table-wrapper-responsive">
                                    <table  id="tabla">
                                        <thead>
                                            <tr>
                                                <th class="goods-page-description text-center">Producto</th>
                                                <th class="goods-page-quantity text-center">Precio</th>
                                                <th class="goods-page-description text-center">Disponibles</th>                                            
                                                <th class="goods-page-image text-center">Imagen</th>                                                                                       
                                                <th class="goods-page text-center">Operacion</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.listarProducto}" var="productos">
                                            <tr class="text-center">
                                                <td class="goods-page-description">
                                                    ${productos.producto}
                                                </td>
                                                <td class="goods-page-quantity">
                                                    ${productos.precioRegular}
                                                </td>
                                                <td class="goods-page-description">
                                                    ${productos.cantidad}
                                                </td>
                                                <td class="goods-page-image">
                                                    <c:forEach items="${requestScope.listarImagenes}" var="imagenes">
                                                        <c:if test="${productos.idProducto eq imagenes.idProducto}">
                                                            <img height="100px" src="${base}/images/${imagenes.urlimagen}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                                <td class="goods-page text-center">
                                                    <c:choose>
                                                        <c:when test="${productos.estadoProducto.estado eq 'Activo'}">
                                                            <a class="btn btn-info" title="agregar" style="padding: 5%;" href="#product-pop-up">+</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${productos.estadoProducto.estado eq 'Rechazado'}">
                                                                    <a class="btn btn-info" title="Reenviar" style="padding: 5%;"
                                                                       href="${base}/empresas.do?operacion=obtener&id=${productos.idProducto}">Reenviar</a>
                                                                </c:when>
                                                            </c:choose>
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
            </div>

        </div>

        <!-- Button trigger modal -->
        <a class="btn btn-primary" data-toggle="modal" href="#exampleModal">
            Launch demo modal
        </a>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>

        <!-- END PAGE LEVEL JAVASCRIPTS -->
    </body>
    <script type="text/javascript">
    
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
    </script>
</html>