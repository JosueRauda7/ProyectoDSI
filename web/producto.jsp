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
        <title>Categoria</title>
        <jsp:include page="/head.jsp"/>        
    </head>
    <!-- Head END -->

    <!-- Body BEGIN -->
    <body class="ecommerce">
        <!-- BEGIN STYLE CUSTOMIZER -->
        <div class="color-panel hidden-sm">
            <div class="color-mode-icons icon-color"></div>
            <div class="color-mode-icons icon-color-close"></div>
            <div class="color-mode">
                <p>THEME COLOR</p>
                <ul class="inline">
                    <li class="color-red current color-default" data-style="red"></li>
                    <li class="color-blue" data-style="blue"></li>
                    <li class="color-green" data-style="green"></li>
                    <li class="color-orange" data-style="orange"></li>
                    <li class="color-gray" data-style="gray"></li>
                    <li class="color-turquoise" data-style="turquoise"></li>
                </ul>
            </div>
        </div>
        <!-- END BEGIN STYLE CUSTOMIZER --> 


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
            <div class="main">
                <div class="container">

                    <!-- BEGIN SIDEBAR & CONTENT -->
                    <!-- BEGIN SIMILAR PRODUCTS -->
                    <div class="row margin-bottom-40">
                        <div class="col-md-12 col-sm-12">
                            <center><h2>Nombre de la categoria</h2></center>
                            <div class="owl-carousel owl-carousel4">
                                <div>
                                    <div class="product-item">
                                        <div class="pi-img-wrapper">
                                            <img src="assets/pages/img/products/k1.jpg" class="img-responsive" alt="Berry Lace Dress">
                                            <div>
                                                <a href="#" class="btn btn-default">Entrar</a>
                                            </div>
                                        </div>
                                        <h3><a href="shop-item.html">categoria 1</a></h3>
                                    </div>
                                </div>
                                <div>
                                    <div class="product-item">
                                        <div class="pi-img-wrapper">
                                            <img src="assets/pages/img/mueble.jpg" class="img-responsive" alt="Berry Lace Dress">
                                            <div>
                                                <a href="#" class="btn btn-default">Entrar</a>
                                            </div>
                                        </div>
                                        <h3><a href="shop-item.html">categoria 2</a></h3>
                                    </div>
                                </div>
                                <div>
                                    <div class="product-item">
                                        <div class="pi-img-wrapper">
                                            <img src="assets/pages/img/Electrodomestico.jpg" class="img-responsive" alt="Berry Lace Dress">
                                            <div>
                                                <a href="#" class="btn btn-default">Entrar</a>
                                            </div>
                                        </div>
                                        <h3><a href="shop-item.html">categoria 3</a></h3>
                                    </div>
                                </div>
                                <div>
                                    <div class="product-item">
                                        <div class="pi-img-wrapper">
                                            <img src="assets/pages/img/dispositivoElectronico.jpg" class="img-responsive" alt="Berry Lace Dress">
                                            <div>
                                                <a href="#" class="btn btn-default">Entrar</a>
                                            </div>
                                        </div>
                                        <h3><a href="shop-item.html">categoria 4</a></h3>

                                    </div>
                                </div>

                            </div>
                        </div>
                        <!-- END SIMILAR PRODUCTS -->
                    </div>

                </div>
            </div>
        </div>
        <!-- END CONTENT -->
    <!-- END SIDEBAR & CONTENT -->

    <!-- BEGIN FOOTER -->
    <div class="footer" style="position: absolute; bottom: 0; width: 100%;">
        
            <div >
                <!-- BEGIN COPYRIGHT -->
                <div class="col-md-12 text-center ">
                    2018 © Universidad Don Bosco. Derechos reservados. 
                </div>
                <!-- END COPYRIGHT -->
                
            </div>
        
    </div>
    <!-- END FOOTER -->

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