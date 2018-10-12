<%-- 
    Document   : comprasCliente
    Created on : 18-sep-2018, 18:24:00
    Author     : Ferh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Nombre empresa | Administrador</title>

        <jsp:include page="head.jsp"/>
        <!-- Theme styles END -->
    </head>
    <!-- Head END -->

    <body class="ecommerce">


        <jsp:include page="/administrador/menuAdmin.jsp"/>


        <div class="container">


            <div class="row margin-bottom-40">
                <!-- BEGIN CONTENT -->
                <div class="col-md-12 col-sm-12">
                    <br><h1>Productos comprados</h1><br>
                    <h2>Cliente: ${requestScope.cliente.nombre} ${requestScope.cliente.apellido}</h2>
                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <div class="table-wrapper-responsive">
                                <table summary="Shopping cart">
                                    <c:if test="${empty requestScope.listaPedidos}">
                                        <h1>Este cliente no ha realizado compras</h1>
                                    </c:if>

                                    <c:if test="${not empty requestScope.listaPedidos}">
                                        <tr>
                                            <th class="goods-page-description">Producto</th>
                                            <th class="goods-page-ref-no">Cantidad</th>
                                            <th class="goods-page-ref-no">Categoria</th>
                                        </tr>


                                        <c:forEach items="${requestScope.listaPedidos}" var="pedido">


                                            <c:forEach items="${requestScope.listaDetallePedidos}" var="detalle">

                                                <c:if test="${pedido.idPedido eq detalle.pedido.idPedido}">

                                                    <tr style="background-color: rgba(0,0,0,.05)">

                                                        <td class="goods-page-description">
                                                            <h3><a href="javascript:;">${detalle.producto.producto}</a></h3>
                                                            <p>${detalle.producto.descripcion}</p>

                                                        </td>
                                                        <td class="goods-page-ref-no">
                                                            ${detalle.cantidad}
                                                        </td>
                                                        <td class="goods-page-quantity">
                                                            <h3><a href="javascript:;">${detalle.producto.subCategoria.subCategoria}</a></h3>
                                                            <p>${detalle.producto.subCategoria.categoria.categoria}</p>
                                                        </td>

                                                    </tr>




                                                </c:if>


                                            </c:forEach>

                                            <c:if test="${not empty pedido.fechaCompra}">

                                                <tr>

                                                    <td>
                                                        <h3><a href="javascript:;">Detalle del pedido</a></h3>
                                                    </td>
                                                    <td class="goods-page-ref-no">
                                                        <strong><span>Fecha de compra: </span>${pedido.fechaCompra}</strong>
                                                    </td>

                                                    <td class="goods-page-total">
                                                        <strong><span>Monto total: $</span>${pedido.montoTotal}</strong>
                                                    </td>

                                                </tr>

                                            </c:if>

                                        </c:forEach>

                                    </c:if>

                                </table>
                            </div>


                        </div>

                    </div>
                </div>


            </div>
        </div>





        <!-- BEGIN FOOTER -->
        <div class="footer">
            <div class="container">
                <div class="row">
                    <!-- BEGIN COPYRIGHT -->
                    <div class="col-md-4 col-sm-4 padding-top-10">
                        2015 © Keenthemes. ALL Rights Reserved. 
                    </div>
                    <!-- END COPYRIGHT -->
                    <!-- BEGIN PAYMENTS -->
                    <div class="col-md-4 col-sm-4">
                        <ul class="list-unstyled list-inline pull-right">
                            <li><img src="../assets/corporate/img/payments/western-union.jpg" alt="We accept Western Union" title="We accept Western Union"></li>
                            <li><img src="../assets/corporate/img/payments/american-express.jpg" alt="We accept American Express" title="We accept American Express"></li>
                            <li><img src="../assets/corporate/img/payments/MasterCard.jpg" alt="We accept MasterCard" title="We accept MasterCard"></li>
                            <li><img src="../assets/corporate/img/payments/PayPal.jpg" alt="We accept PayPal" title="We accept PayPal"></li>
                            <li><img src="../assets/corporate/img/payments/visa.jpg" alt="We accept Visa" title="We accept Visa"></li>
                        </ul>
                    </div>
                    <!-- END PAYMENTS -->
                    <!-- BEGIN POWERED -->
                    <div class="col-md-4 col-sm-4 text-right">
                        <p class="powered">Powered by: <a href="http://www.keenthemes.com/">KeenThemes.com</a></p>
                    </div>
                    <!-- END POWERED -->
                </div>
            </div>
        </div>
        <!-- END FOOTER -->

        <!-- BEGIN fast view of a product -->
        <div id="product-pop-up" style="display: none; width: 700px;">
            <div class="product-page product-pop-up">
                <div class="row">
                    <div class="col-md-6 col-sm-6 col-xs-3">
                        <div class="product-main-image">
                            <img src="assets/pages/img/products/model7.jpg" alt="Cool green dress with red bell" class="img-responsive">
                        </div>
                        <div class="product-other-images">
                            <a href="javascript:;" class="active"><img alt="Berry Lace Dress" src="../assets/pages/img/products/model3.jpg"></a>
                            <a href="javascript:;"><img alt="Berry Lace Dress" src="../assets/pages/img/products/model4.jpg"></a>
                            <a href="javascript:;"><img alt="Berry Lace Dress" src="../assets/pages/img/products/model5.jpg"></a>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-9">
                        <h2>Cool green dress with red bell</h2>
                        <div class="price-availability-block clearfix">
                            <div class="price">
                                <strong><span>$</span>47.00</strong>
                                <em>$<span>62.00</span></em>
                            </div>
                            <div class="availability">
                                Availability: <strong>In Stock</strong>
                            </div>
                        </div>
                        <div class="description">
                            <p>Lorem ipsum dolor ut sit ame dolore  adipiscing elit, sed nonumy nibh sed euismod laoreet dolore magna aliquarm erat volutpat 
                                Nostrud duis molestie at dolore.</p>
                        </div>
                        <div class="product-page-options">
                            <div class="pull-left">
                                <label class="control-label">Size:</label>
                                <select class="form-control input-sm">
                                    <option>L</option>
                                    <option>M</option>
                                    <option>XL</option>
                                </select>
                            </div>
                            <div class="pull-left">
                                <label class="control-label">Color:</label>
                                <select class="form-control input-sm">
                                    <option>Red</option>
                                    <option>Blue</option>
                                    <option>Black</option>
                                </select>
                            </div>
                        </div>
                        <div class="product-page-cart">
                            <div class="product-quantity">
                                <input id="product-quantity2" type="text" value="1" readonly class="form-control input-sm">
                            </div>
                            <button class="btn btn-primary" type="submit">Add to cart</button>
                            <a href="shop-item.html" class="btn btn-default">More details</a>
                        </div>
                    </div>

                    <div class="sticker sticker-sale"></div>
                </div>
            </div>
        </div>
        <!-- END fast view of a product -->

        <!-- Load javascripts at bottom, this will reduce page load time -->
        <!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
        <!--[if lt IE 9]>
        <script src="assets/plugins/respond.min.js"></script>  
        <![endif]-->  
        <script src="../assets/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="../assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
        <script src="../assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>      

        <script src="../assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->

        <!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
        <script src="../assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script><!-- pop up -->
        <script src="../assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script><!-- slider for products -->
        <script src='../assets/plugins/zoom/jquery.zoom.min.js' type="text/javascript"></script><!-- product zoom -->
        <script src="../assets/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script><!-- Quantity -->
        <script src="../assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
        <script src="../assets/plugins/rateit/src/jquery.rateit.js" type="text/javascript"></script>

        <script src="../assets/corporate/scripts/layout.js" type="text/javascript"></script>
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
        <!-- END PAGE LEVEL JAVASCRIPTS -->
    </body>
    <!-- END BODY -->
</html>