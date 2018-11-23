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
                <div class="col-lg-4 product-item">
                    <div class="row">
                        <h2>Requisitos de Aceptación:</h2>
                        <p><span class="fa fa-check"></span>No sea perecedero.</p>
                        <p><span class="fa fa-check"></span>Cantidad Mínima de productos: 10.</p>
                        <p><span class="fa fa-check"></span>No sea de carácter ofensivo el producto.</p>
                        <p><span class="fa fa-check"></span>Respeta ortografía.</p>
                        <p><span class="fa fa-check"></span>Imágenes que no contengan contenido inapropiado.</p>
                        <div class="col-lg-12">
                            <c:if test="${producto.estadoProducto.estado eq 'En espera'}">
                                <p style="font-size: 20px;"><b>Estado de producto</b></p>
                                <a href="${base}/administrador.do?operacion=aceptarRechazar&estado=2&id=${producto.idProducto}" class="btn btn-default">Aceptado</a>
                                <a href="${base}/administrador.do?operacion=aceptarRechazar&estado=3&id=${producto.idProducto}" class="btn btn-default">Rechazado</a>
                                <a href="${base}/administrador.do?operacion=listarProductos&estado=1" class="btn btn-default">Cancelar</a>
                            </c:if>
                            <c:if test="${!(producto.estadoProducto.estado eq 'En espera')}">
                                <p style="font-size: 20px;"><b>Estado de producto</b></p>
                                <p>${producto.estadoProducto.estado}</p>
                                <a href="${base}/administrador.do?operacion=listarProductos&estado=1" class="btn btn-default">Regresar</a>
                            </c:if>
                    </div>
                        </div>
                </div>
                <div class="col-lg-4 product-item">
                    <h2 class="text-center">Producto</h2>
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
                        <div class="pi-price" >Precio: $${producto.precioRegular}</div>
                        <br>
                        <br><br>
                    </div>
                </div>
                <div class="col-lg-4 product-item">
                    <div class="row">
                        <h2 class="text-center">Detalles</h2>
                        <c:if test="${empty requestScope.lista}">
                            <p class="text-center">No contiene detalles.</p>
                        </c:if>
                        <c:forEach var="detalle" items="${requestScope.lista}">
                            <h3 class="text-center">${detalle.detalle}: </h3>
                            <p class="text-center">${detalle.detalleAtributo}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="margin-bottom-65"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
