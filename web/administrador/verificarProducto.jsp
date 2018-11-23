<%-- 
    Document   : verificarProducto
    Created on : 11-22-2018, 11:46:59 PM
    Author     : admi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BigShop | Administrador</title>
        <jsp:include page="head.jsp"/>       
    </head>
    <body>
        <jsp:include page="menuAdmin.jsp"/>
        <div class="main">
            <div class="margin-bottom-50"><div class="container"></div></div>
            <div class="container">
                <div class="col-lg-4">
                    <div class="row"></div>
                </div>
                <div class="col-lg-4 product-item">
                    <div class="pi-img-wrapper" style="text-align: center;">
                        <img width="200" src="${base}/images/${producto.urlImagen}"/>
                    </div>
                    <div class="text-center">
                        <h3>Producto:</h3>
                        <p>${producto.producto}</p>
                        <h3>Descripción:</h3>
                        <p>${producto.descripcion}</p>
                        <h3>Existencias:</h3>
                        <p>${producto.cantidad}</p>
                        <h3>Empresa:</h3>
                        <p>${producto.empresa.empresa}</p>
                        <h3>Sub Categoría:</h3>
                        <p>${producto.subCategoria.subCategoria}</p>
                        <c:forEach var="detalle" items="${requestScope.lista}">
                            <h3>${detalle.detalle}</h3>
                            <p>${detalle.detalleAtributo}</p>
                        </c:forEach>
                        <div class="pi-price" >Precio: $${producto.precioRegular}</div>
                        <br><br>
                    </div>
                    <a href="${base}/administrador.do?operacion=listarProductos&estado=1" class="btn btn-default add2cart">Cancelar</a>
                    <div class="col-lg-1"><div class="row"></div></div>
                    <c:if test="${producto.estadoProducto.estado eq 'En espera'}">
                        <a href="${base}/administrador.do?operacion=aceptarRechazar&estado=3&id=${producto.idProducto}" class="btn btn-default add2cart">Rechazar</a>
                        <div class="col-lg-2"><div class="row"></div></div>
                        <a href="${base}/administrador.do?operacion=aceptarRechazar&estado=2&id=${producto.idProducto}" class="btn btn-default add2cart">Aceptar</a>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="margin-bottom-65"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
