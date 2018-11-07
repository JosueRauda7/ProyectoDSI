

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html lang="en">
    <!--<![endif]-->

    <!-- Head BEGIN -->
    <head>
        <title>Carrito de compra</title>
        <jsp:include page="/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
    </head>
    <!-- Head END -->

    <!-- Body BEGIN -->
    <body class="ecommerce">
        <!-- BEGIN STYLE CUSTOMIZER -->

        <!-- END BEGIN STYLE CUSTOMIZER --> 


        <jsp:include page="menuCliente.jsp"/>
        <!-- END SLIDER -->

        <div class="main" style="margin-bottom: 5%;">
            <div class="container">
                <!-- BEGIN SIDEBAR & CONTENT -->
                <div class="row margin-bottom-40">
                    <!-- BEGIN CONTENT -->
                    <div class="col-md-12 col-sm-12">
                        <h1>Carrito de compras del ${requestScope.fechaPedido}</h1>
                        <div class="goods-page">
                            <div class="goods-data clearfix">
                                <div class="table-wrapper-responsive" style="height: 400px; overflow-y: scroll;">
                                    <table summary="Shopping cart">
                                        <tr>
                                            <td colspan="7"><h4 class="text-center">Productos</h4></td>
                                        </tr>
                                        <tr>
                                            <th class="goods-page-image">Imagen</th>
                                            <th class="goods-page-description">Descripción</th>
                                            <th class="goods-page-ref-no">Empresa</th>
                                            <th class="goods-page-quantity">Cantidad</th>
                                            <th class="goods-page-price">Precio unitario</th>
                                            <th class="goods-page-total">Total</th>
                                            
                                        </tr>
                                        <c:if test="${not empty requestScope.listaproductPasado}">
                                            <c:forEach var="productos" varStatus="loop"  items="${requestScope.listaproductPasado}" >
                                                <tr>
                                                    <td class="goods-page-image">
                                                        <a href="javascript:;"><img src="images/${productos.producto.urlImagen}" alt="${productos.producto.producto}"></a>
                                                    </td>
                                                    <td class="goods-page-description" style="width: 35%;">
                                                        <h3><a href="${base}/clientes.do?operacion=verProducto&idproduct=${productos.producto.idProducto}">${productos.producto.producto}</a></h3>
                                                        <p><strong>Item ${loop.index +1}</strong> -${productos.producto.descripcion}</p>

                                                    </td>
                                                    <td class="goods-page-ref-no">
                                                        ${productos.producto.empresa.empresa}
                                                        <br>
                                                        <a href="javascript:;"><img src="images/${productos.producto.empresa.urlEmpresa}" alt="${productos.producto.empresa.empresa}" width="100"></a>
                                                    </td>
                                                    <td class="goods-page-quantity">
                                                        <div class="product-quantity">
                                                            <p>${productos.cantidad}</p>
                                                        </div>
                                                    </td>
                                                    <td class="goods-page-price">
                                                        <strong><span>$</span>${productos.producto.precioRegular}</strong>
                                                    </td>
                                                    <td class="goods-page-total">
                                                        <strong><span>$</span>${productos.producto.precioRegular * productos.cantidad}</strong>
                                                    </td>

                                                </tr>
                                            </c:forEach> 
                                        </c:if>
                                        <c:if test="${empty requestScope.listaproductPasado}">
                                            <tr>
                                                <td colspan="7"><h4 class="text-center">No hay productos que mostrar</h4></td>
                                            </tr>
                                        </c:if>
                                        <tr>
                                            <td colspan="7"><h4 class="text-center">Ofertas</h4></td>
                                        </tr>
                                        <tr>
                                            <th class="goods-page-image">Imagen</th>
                                            <th class="goods-page-description">Descripción</th>
                                            <th class="goods-page-ref-no">Descuento</th>
                                            <th class="goods-page-quantity">Cantidad</th>
                                            <th class="goods-page-price">Precio unitario</th>
                                            <th class="goods-page-total">Total</th>
                                            
                                        </tr>
                                        <c:if test="${not empty requestScope.listaoferPasado}">
                                            <c:forEach var="ofertas" varStatus="loop" items="${requestScope.listaoferPasado}">
                                                <tr>
                                                    <td class="goods-page-image">
                                                        <a href="javascript:;"><img src="images/${ofertas.oferta.urlFoto}" alt="${ofertas.oferta.titulo}"></a>
                                                    </td>
                                                    <td class="goods-page-description" style="width: 35%;">
                                                        <h3><a href="${base}/clientes.do?operacion=verProducto&idproduct=${ofertas.oferta.producto.idProducto}">${ofertas.oferta.titulo}</a></h3>
                                                        <p><strong>Item ${loop.index +1}</strong> -${ofertas.oferta.descripcion}</p>

                                                    </td>
                                                    <td class="goods-page-ref-no">
                                                        <p>%${ofertas.oferta.descuento}</p>
                                                    </td>
                                                    <td class="goods-page-quantity">
                                                        <div class="product-quantity">
                                                            <p>${ofertas.cantidad}</p>
                                                        </div>
                                                    </td>
                                                    <td class="goods-page-price">
                                                        <strong><span>$</span>${ofertas.oferta.totalDescuento}</strong>
                                                    </td>
                                                    <td class="goods-page-total">
                                                        <strong><span>$</span>${ofertas.oferta.totalDescuento * ofertas.cantidad}</strong>
                                                    </td>
                                                 
                                                </tr>
                                            </c:forEach> 
                                        </c:if>
                                        <c:if test="${empty requestScope.listaoferPasado}">
                                            <tr>
                                                <td colspan="7"><h4 class="text-center">No hay ofertas que mostrar</h4></td>
                                            </tr>
                                        </c:if>
                                    </table>
                                </div>

                                <div class="shopping-total">
                                    <ul>
                                        <li>
                                            <em>Sub total</em>
                                            <strong class="price"><span>$</span>${requestScope.totalpedidopasado}</strong>
                                        </li>
                                        <li>
                                            <em>Costo de envio</em>
                                            <strong class="price"><span>$</span><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${requestScope.totalpedidopasado*0.05}"/></strong>
                                        </li>
                                        <li class="shopping-total-price">
                                            <em>Total</em>
                                            <strong class="price"><span>$</span><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${(requestScope.totalpedidopasado*0.05) +requestScope.totalpedidopasado}"/></strong>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <a href="${base}/clientes.do?operacion=publicIndex"><button class="btn btn-default" >Continuar comprando <i class="fa fa-shopping-cart"></i></button></a>   
                    </div>
                    <!-- END CONTENT -->
                </div>
                <!-- END SIDEBAR & CONTENT -->

                <!-- BEGIN SIMILAR PRODUCTS -->

                <!-- END SIMILAR PRODUCTS -->
            </div>
        </div>

        <!-- END CONTENT -->
        <!-- END SIDEBAR & CONTENT -->

        <!-- BEGIN FOOTER -->



        <script type="text/javascript">
            jQuery(document).ready(function () {
                Layout.init();
                Layout.initOWL();
                Layout.initTwitter();
                Layout.initImageZoom();
                Layout.initTouchspin();
                Layout.initUniform();
            });



        </script>


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
            </c:if>

            function eliminar(id) {

                var URLactual = window.location;
                var url = URLactual.toString().substring(34);
                swal({
                    title: '¿Seguro que quieres eliminar este articulo del carrito?',
                    text: "Una vez eliminado, no habra vuelta atras.",
                    icon: 'warning',
                    buttons: true,
                    dangerMode: true,
                }).then((willDelete) => {
                    if (willDelete) {

                        location.href = 'clientes.do?operacion=eliminarArticulo&iddetalle=' + id + '&url=' + url;
                    }
                });
            }
            ;

            function eliminarOfer(id) {

                var URLactual = window.location;
                var url = URLactual.toString().substring(34);
                swal({
                    title: '¿Seguro que quieres eliminar este articulo del carrito?',
                    text: "Una vez eliminado, no habra vuelta atras.",
                    icon: 'warning',
                    buttons: true,
                    dangerMode: true,
                }).then((willDelete) => {
                    if (willDelete) {

                        location.href = 'clientes.do?operacion=eliminarOferta&iddetalle=' + id + '&url=' + url;
                    }
                });
            }
            ;

        </script>

    </body>
    <jsp:include page="footer.jsp" />
    <!-- END BODY -->
</html>