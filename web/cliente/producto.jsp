<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <!-- Head BEGIN -->
    <head>
        <title>Producto</title>     
        <jsp:include page="/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
    </head>

    <body class="ecommerce" >

        <!-- BEGIN NAVIGATION -->
        <jsp:include page="menuCliente.jsp"/>
        <!-- END NAVIGATION -->
        <div class="container">

            <div class="product-page">
                <div class="row">
                    <div class="col-md-6 col-sm-6">
                        <div class="product-main-image">
                            <img src="images/${requestScope.producto.urlImagen}" alt="Cool green dress with red bell" class="img-responsive" data-BigImgsrc="images/${requestScope.producto.urlImagen}">
                        </div>

                    </div>
                    <div class="col-md-6 col-sm-6">
                        <h1>${requestScope.producto.producto}</h1>
                        <div class="price-availability-block clearfix">
                            <div class="price">
                                <em>Precio: </em> <strong><span>$</span>${requestScope.producto.precioRegular}</strong>

                            </div>
                            <div class="availability">
                                Estado: <strong>${requestScope.producto.estadoProducto.estado}</strong>
                            </div>
                        </div>
                        <div class="description">
                            <p>${requestScope.producto.descripcion}</p>
                        </div>
                        <div class="product-page-options">
                            <div class="pull-left"  style="display: flex;">
                                <label class="control-label">Cantidad disponible:</label><p>${requestScope.producto.cantidad}</p>  
                            </div>
                            <form class="product-page-cart" action="clientes.do">
                                <input type="hidden" value="agregarProducto" name="operacion">
                                <input type="hidden" value="${requestScope.producto.idProducto}" name="idproduct">
                                <div class="product-quantity">
                                    <input id="product-quantity" type="text" value="1" name="cantidad" readonly class="form-control input-sm">
                                </div>
                                <button class="btn btn-primary" type="submit">Agregar al carrito</button>
                            </form>
                            <div class="marca" >
                                <label class="control-label">Empresa proveedora:</label><p>${requestScope.producto.empresa.empresa}</p>  
                                <a href="images/${requestScope.producto.empresa.urlEmpresa}" class="fancybox-button" rel="photos-lib"><img alt="Berry Lace Dress" src="images/${requestScope.producto.empresa.urlEmpresa}" style="width: 35%;"></a>
                            </div>
                            <div class="review" style="display: flex;">
                                <p>Subcategoria: </p><a href=""> ${requestScope.producto.subCategoria.subCategoria}</a> 

                            </div>

                            <ul class="social-icons">
                                <li><a class="facebook social-button" data-original-title="facebook"  href="https://www.facebook.com/sharer/sharer.php?u=http://localhost:8080/ProyectoDSI/clientes.do?operacion=verProducto&idproduct=8"  target="_blank" rel="nofollow"></a></li>
                                <li><a class="twitter social-button"  data-original-title="twitter" href="https://twitter.com/share?url=http://localhost:8080/ProyectoDSI/clientes.do?operacion=verProducto&idproduct=8&text=Hola" target="_blank" rel="nofollow"></a></li>
                                <li><a class="googleplus social-button"  data-original-title="googleplus" href="https://plus.google.com/share?text=localhost:8080/ProyectoDSI/clientes.do?operacion=verProducto&idproduct=7"  target="_blan" rel="nofollow"></a></li>
                                <li><a class="evernote social-button" data-original-title="evernote" href="javascript:;"></a></li>
                                <li><a class="tumblr social-button"  data-original-title="tumblr" href="javascript:;"></a></li>
                            </ul>
                               
                        </div>

                    </div>
                </div>

            </div>
        </div>


        <jsp:include page="/scripts.jsp"/>
        <script type="text/javascript">
            jQuery(document).ready(function () {
                Layout.init();
                Layout.initOWL();
                Layout.initTwitter();
                Layout.initImageZoom();
                Layout.initTouchspin();
                Layout.initUniform();
            });
            var URLactual = window.location;
              var lba = document.getElementsByClassName("social-button");

        function myPopup() {
            window.open(this.href, 'mywin',
                    'left=20,top=20,width=500,height=500,toolbar=1,resizable=0');
            event.preventDefault();
            return false;
        }

        for (var i = 0; i < lba.length; i++) {
            lba[i].addEventListener("click", myPopup, false);
        }
        </script>
        <!-- END PAGE LEVEL JAVASCRIPTS -->
    </body>
    <script>  
        <c:if test="${not empty exito}">
                swal({
                    title: "Felicidades!",
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
                </c:if></script>
    <jsp:include page="footer.jsp" />
    <!-- END BODY -->
</html>