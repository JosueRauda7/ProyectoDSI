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
        <!-- Theme styles END -->
    </head>
    <!-- Head END -->

    <body class="ecommerce">


        <jsp:include page="menuEmpresa.jsp"/>


        <div class="container">
            <div class="product-page-content">
                <a style="margin-bottom: 2%;" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=nuevo">Nuevo producto</a>
                <ul id="myTab" class="nav nav-tabs">
                    <li><a href="${base}/empresas.do?operacion=listar&estado=1" data-toggle="tab">Listar en espera</a></li>
                    <li><a href="${base}/empresas.do?operacion=listar&estado=2" data-toggle="tab">Listar activos</a></li>
                    <li><a href="${base}/empresas.do?operacion=listar&estado=3" data-toggle="tab">Listar rechazados</a></li>                    
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
                                            <tr>
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
                                                    <img height="100px" src="${base}/images/${productos.urlImagen}"/>
                                                </td>
                                                <td class="goods-page text-center">
                                                    <c:choose>
                                                        <c:when test="${productos.estadoProducto.estado eq 'Activo'}">
                                                            <a class="btn btn-info" title="agregar" style="padding: 5%;" href="#">+</a>
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

        <div id="product-pop-up" style="display: none; width: 700px;">
            <div class="col-md-12">
                <h3>Renovación de producto</h3>
                <p>Nombre del prodcuto</p>
                <form role="form" action="#">
                    <div class="form-group">
                        <label for="cantidad">Nueva cantidad</label>
                        <input type="text" id="cantidad" class="form-control" name="cantidad">
                    </div>
                    <div class="form-group">
                        <label for="vencimiento">Fecha de vencimiento</label>
                        <input type="date" id="vencimiento" class="form-control" name="vencimiento">
                    </div>

                    <div class="padding-top-20">                  
                        <button class="btn btn-primary" type="submit">Renovar</button>
                    </div>
                    <hr>

                </form>
            </div>
        </div>

        <br/><br/><br/>

        <jsp:include page="footer.jsp"/>

        <!-- END PAGE LEVEL JAVASCRIPTS -->
    </body>
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
    </script>
</html>