<%-- 
    Document   : misCompras
    Created on : 09-26-2018, 06:16:30 PM
    Author     : admi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />
    <body>
        <div class="header">
            <div class="container">
                <a class="site-logo" href="shop-index.html">BigShop</a>

                <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>

                <!-- BEGIN CART -->
                <div class="top-cart-block">
                    <div class="top-cart-info">
                        <a href="javascript:void(0);" class="top-cart-info-count">1 artículos</a>
                        <a href="javascript:void(0);" class="top-cart-info-value">$1230</a>
                    </div>
                    <i class="fa fa-shopping-cart"></i>

                    <div class="top-cart-content-wrapper">
                        <div class="top-cart-content">
                            <ul class="scroller" style="height: 250px;">
                                <li>
                                    <!-- Objetos de carrito -->
                                    <a href="shop-item.html"><img src="assets/pages/img/cart-img.jpg" alt="Rolex Classic Watch" width="37" height="34"></a>
                                    <span class="cart-content-count">x 1</span>
                                    <strong><a href="shop-item.html">Rolex Classic Watch</a></strong>
                                    <em>$1230</em>
                                    <a href="javascript:void(0);" class="del-goods">&nbsp;</a>
                                </li>                
                            </ul>
                            <div class="text-right">
                                <a href="shop-shopping-cart.html" class="btn btn-default">Ver Carrito</a>
                                <a href="shop-checkout.html" class="btn btn-primary">Revisar</a>
                            </div>
                        </div>
                    </div>            
                </div>
                <!--END CART -->

                <!-- BEGIN NAVIGATION -->
                <jsp:include page="/menu.jsp"/>
                <!-- END NAVIGATION -->
            </div>
        </div>
        <div class="main">
            <div class="container">
                <div class="col-md-12 col-sm-12">
                    <h1>Productos comprados</h1>
                    <br><br>
                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <div class="table-wrapper-responsive">
                                <table summary="Shopping cart">
                                    <tr>
                                        <th class="goods-page-image">Imagen</th>
                                        <th class="goods-page-image">Producto</th>
                                        <th class="goods-page-description">Precio</th>
                                        <th class="goods-page-ref-no">Fecha de Compra</th>
                                        <th class="goods-page-total" colspan="2">Operaciones</th>
                                    </tr>
                                    <tr>
                                        <td class="goods-page-image">
                                            <a href="javascript:;"><img src="assets/pages/img/products/model3.jpg" alt="Berry Lace Dress"></a>
                                        </td>
                                        <td class="goods-page-image">
                                            Camisa Polo
                                        </td>
                                        <td class="goods-page-description">
                                            $ 2.50
                                        </td>
                                        <td class="goods-page-ref-no">
                                            josue.rauda@gmail.com
                                        </td>
                                        <td class="goods-page-total">
                                            <a class="btn btn-default">Editar</a>
                                            <a style="margin-left: 2%; color:white" class="btn btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="goods-page-image">
                                            <a href="javascript:;"><img src="assets/pages/img/products/model3.jpg" alt="Berry Lace Dress"></a>
                                        </td>
                                        <td class="goods-page-image">
                                            Camisa Polo
                                        </td>
                                        <td class="goods-page-description">
                                            $ 2.50
                                        </td>
                                        <td class="goods-page-ref-no">
                                            josue.rauda@gmail.com
                                        </td>
                                        <td class="goods-page-total">
                                            <a class="btn btn-default">Editar</a>
                                            <a style="margin-left: 2%; color:white" class="btn btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="goods-page-image">
                                            <a href="javascript:;"><img src="assets/pages/img/products/model3.jpg" alt="Berry Lace Dress"></a>
                                        </td>
                                        <td class="goods-page-image">
                                            Camisa Polo
                                        </td>
                                        <td class="goods-page-description">
                                            $ 2.50
                                        </td>
                                        <td class="goods-page-ref-no">
                                            josue.rauda@gmail.com
                                        </td>
                                        <td class="goods-page-total">
                                            <a class="btn btn-default">Editar</a>
                                            <a style="margin-left: 2%; color:white" class="btn btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="goods-page-image">
                                            <a href="javascript:;"><img src="assets/pages/img/products/model3.jpg" alt="Berry Lace Dress"></a>
                                        </td>
                                        <td class="goods-page-image">
                                            Camisa Polo
                                        </td>
                                        <td class="goods-page-description">
                                            $ 2.50
                                        </td>
                                        <td class="goods-page-ref-no">
                                            josue.rauda@gmail.com
                                        </td>
                                        <td class="goods-page-total">
                                            <a class="btn btn-default">Editar</a>
                                            <a style="margin-left: 2%; color:white" class="btn btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                </table>
                            </div>

                        </div>

                    </div>
                </div>
                <!-- END CONTENT -->
            </div>
        </div>
        <jsp:include page="footer.jsp" />
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
</html>
