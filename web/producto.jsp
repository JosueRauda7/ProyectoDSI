<%-- 
    Document   : index
    Created on : 09-14-2018, 08:03:03 PM
    Author     : admi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Template: Metronic Frontend Freebie - Responsive HTML Template Based On Twitter Bootstrap 3.3.4
Version: 1.0.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase Premium Metronic Admin Theme: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->

    <!-- Head BEGIN -->
    <head>
        <title>Producto</title>
        <jsp:include page="/head.jsp"/>        
    </head>
    <!-- Head END -->

    <!-- Body BEGIN -->
    <body class="ecommerce">
        <!-- BEGIN HEADER -->
        <div class="header">
            <div class="container">
                <a class="site-logo" href="shop-index.html">LOGO EMPRESA</a>

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
        <!-- Header END -->       
        <!-- END SLIDER -->

        <div class="container">

            <div class="product-page">
                <div class="row">
                    <div class="col-md-6 col-sm-6">
                        <div class="product-main">
                            <img src="assets/pages/img/products/model7.jpg" alt="Cool green dress with red bell" class="img-responsive" data-BigImgsrc="assets/pages/img/products/model7.jpg">
                        </div>
                        <div class="price-availability-block clearfix text-center">
                            <div class="price text-center">
                                <div class="row col-md-12 col-sm-12">
                                    <strong>Precio regular: $47.00</strong>
                                </div>
                                <div class="row col-md-12 col-sm-12">
                                    <em>Precio de oferta: $62.00</em>
                                </div>
                                
                            </div>
                        </div>
                        <div class="product-other-images">
                            <div class="product-page-cart">
                                <div class="product-quantity">
                                    <input id="product-quantity" type="text" value="1" readonly class="form-control input-sm">
                                </div>
                                <button class="btn btn-primary" type="submit">Añadir a carrito</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6">
                        <h1>Producto</h1>
                        
                        <div class="description">
                            <p>Lorem ipsum dolor ut sit ame dolore  adipiscing elit, sed nonumy nibh sed euismod laoreet dolore magna aliquarm erat volutpat 
                                Nostrud duis molestie at dolore.</p>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                            </p>
                            <p>
                                Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Nullam varius, turpis et commodo pharetra, est eros bibendum elit, nec luctus magna felis sollicitudin mauris. Integer in mauris eu nibh euismod gravida. Duis ac tellus et risus vulputate vehicula. Donec lobortis risus a elit. Etiam tempor. Ut ullamcorper, ligula eu tempor congue, eros est euismod turpis, id tincidunt sapien risus a quam. Maecenas fermentum consequat mi. Donec fermentum. Pellentesque malesuada nulla a mi. Duis sapien sem, aliquet nec, commodo eget, consequat quis, neque. Aliquam faucibus, elit ut dictum aliquet, felis nisl adipiscing sapien, sed malesuada diam lacus eget erat. Cras mollis scelerisque nunc. Nullam arcu. Aliquam consequat. Curabitur augue lorem, dapibus quis, laoreet et, pretium ac, nisi. Aenean magna nisl, mollis quis, molestie eu, feugiat in, orci. In hac habitasse platea dictumst.
                            </p>
                            <p>
                                Fusce convallis, mauris imperdiet gravida bibendum, nisl turpis suscipit mauris, sed placerat ipsum urna sed risus. In convallis tellus a mauris. Curabitur non elit ut libero tristique sodales. Mauris a lacus. Donec mattis semper leo. In hac habitasse platea dictumst. Vivamus facilisis diam at odio. Mauris dictum, nisi eget consequat elementum, lacus ligula molestie metus, non feugiat orci magna ac sem. Donec turpis. Donec vitae metus. Morbi tristique neque eu mauris. Quisque gravida ipsum non sapien. Proin turpis lacus, scelerisque vitae, elementum at, lobortis ac, quam. Aliquam dictum eleifend risus. In hac habitasse platea dictumst. Etiam sit amet diam. Suspendisse odio. Suspendisse nunc. In semper bibendum libero.
                            </p>

                        </div>
                    </div>                    
                </div>
            </div>

        </div>

        <!-- BEGIN FOOTER -->
        <div class="footer" style=" width: 100%;">

            <div >
                <!-- BEGIN COPYRIGHT -->
                <div class="col-md-12 text-center ">
                    2018 © Universidad Don Bosco. Derechos reservados. 
                </div>
                <!-- END COPYRIGHT -->

            </div>

        </div>
        <!-- END FOOTER -->
        <!-- END CONTENT -->
        <!-- END SIDEBAR & CONTENT -->



        <!-- Load javascripts at bottom, this will reduce page load time -->
        <!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
        <!--[if lt IE 9]>
        <script src="assets/plugins/respond.min.js"></script>  
        <![endif]-->  
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
        </script>
        <!-- END PAGE LEVEL JAVASCRIPTS -->
    </body>
    <!-- END BODY -->
</html>