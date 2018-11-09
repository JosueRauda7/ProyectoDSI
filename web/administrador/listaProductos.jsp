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


        <jsp:include page="menuAdmin.jsp"/>


        <div class="container">
            <div class="product-page-content">

                <ul id="myTab" class="nav nav-tabs" style="margin-bottom: 2%;">
                    <li><a href="${base}/administrador.do?operacion=listarProductos&estado=1" >Listar en espera</a></li>
                    <li><a href="${base}/administrador.do?operacion=listarProductos&estado=2" >Listar activos</a></li>
                    <li><a href="${base}/administrador.do?operacion=listarProductos&estado=3" >Listar rechazados</a></li>                    
                </ul>

                <c:forEach var="producto" items="${requestScope.listarProducto}">
                    <div class="col-md-4" style="margin-top: 2%;">
                        <div class="product-item">
                            <div class="pi-img-wrapper text-center">
                                <c:forEach items="${requestScope.listarImagenes}" var="imagenes">
                                    <c:if test="${producto.idProducto eq imagenes.idProducto}">
                                        <img height="100px" src="${base}/images/${imagenes.urlimagen}"/>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="text-center">
                                <h3>Producto</h3>
                                <p>${producto.producto}</p>
                                <h3>Descripción</h3>
                                <p>${producto.descripcion}</p>
                                <h3>Existencias</h3>
                                <p>${producto.cantidad}</p>
                            </div>
                            <div class="pi-price">Precio: $${producto.precioRegular}</div>
                            <c:if test="${producto.estadoProducto.estado eq 'En espera'}">
                            <a href="${base}/administrador.do?operacion=aceptarRechazar&estado=2&id=${producto.idProducto}" class="btn btn-default add2cart">Aceptar</a>
                            <a href="${base}/administrador.do?operacion=aceptarRechazar&estado=3&id=${producto.idProducto}" class="btn btn-default add2cart">Rechazar</a>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
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