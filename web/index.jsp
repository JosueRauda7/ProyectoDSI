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
        <title>Index</title>
        <jsp:include page="/head.jsp"/>        
    </head>
    <!-- Head END -->

    <!-- Body BEGIN -->
    <body class="ecommerce">
        <!-- BEGIN HEADER -->
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
                <div class="header-navigation">
                    <ul>
                        <li class="">
                            <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                                Acerca de  

                            </a>

                            <!-- END DROPDOWN MENU -->
                        </li>
                        <li class="dropdown dropdown-megamenu">
                            <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                                Categorias

                            </a>
                            <ul class="dropdown-menu" style="overflow-y:scroll; height:400px; overflow-x: scroll; width: 60%; overflow: scroll;">
                                <li>
                                    <div class="header-navigation-content">
                                        <div class="row">
                                            <div class="col-md-4 header-navigation-col">
                                                <h4>Footwear</h4>
                                                <ul>
                                                    <li><a href="shop-product-list.html">Astro Trainers</a></li>

                                                </ul>
                                            </div>
                                            <div class="col-md-4 header-navigation-col">
                                                <h4>Clothing</h4>
                                                <ul>
                                                    <li><a href="shop-product-list.html">Base Layer</a></li>
                                                    <li><a href="shop-product-list.html">Character</a></li>
                                                    <li><a href="shop-product-list.html">Chinos</a></li>
                                                    <li><a href="shop-product-list.html">Combats</a></li>
                                                    <li><a href="shop-product-list.html">Cricket Clothing</a></li>
                                                    <li><a href="shop-product-list.html">Fleeces</a></li>
                                                    <li><a href="shop-product-list.html">Gilets</a></li>
                                                    <li><a href="shop-product-list.html">Golf Tops</a></li>
                                                </ul>
                                            </div>

                                            <div class="col-md-4 header-navigation-col">
                                                <h4>Accessories</h4>
                                                <ul>
                                                    <li><a href="shop-product-list.html">Belts</a></li>
                                                    <li><a href="shop-product-list.html">Caps</a></li>
                                                    <li><a href="shop-product-list.html">Gloves, Hats and Scarves</a></li>
                                                </ul>
                                            </div>                    

                                            <!--<div class="col-md-12 nav-brands">
                                              <ul>
                                                <li><a href="shop-product-list.html"><img title="esprit" alt="esprit" src="assets/pages/img/brands/esprit.jpg"></a></li>
                                                <li><a href="shop-product-list.html"><img title="gap" alt="gap" src="assets/pages/img/brands/gap.jpg"></a></li>
                                                <li><a href="shop-product-list.html"><img title="next" alt="next" src="assets/pages/img/brands/next.jpg"></a></li>
                                                <li><a href="shop-product-list.html"><img title="puma" alt="puma" src="assets/pages/img/brands/puma.jpg"></a></li>
                                                <li><a href="shop-product-list.html"><img title="zara" alt="zara" src="assets/pages/img/brands/zara.jpg"></a></li>
                                              </ul>
                                            </div>-->
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li><a href="shop-item.html">Ingresar</a></li>




                        <!-- BEGIN TOP SEARCH -->
                        <li class="menu-search">
                            <span class="sep"></span>
                            <i class="fa fa-search search-btn"></i>
                            <div class="search-box">
                                <form action="#">
                                    <div class="input-group">
                                        <input type="text" placeholder="Buscar articulo" class="form-control">
                                        <span class="input-group-btn">
                                            <button class="btn btn-primary" type="submit">Buscar</button>
                                        </span>
                                    </div>
                                </form>
                            </div> 
                        </li>
                        <!-- END TOP SEARCH -->
                    </ul>
                </div>
                <!-- END NAVIGATION -->
            </div>
        </div>
        <!-- Header END -->
        <!-- BEGIN SLIDER -->
        <div class="page-slider margin-bottom-35">
            <div id="carousel-example-generic" class="carousel slide carousel-slider">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <!-- paginador de slider -->
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>                
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <!-- First slide -->

                    <div class="item carousel-item-four active">
                        <div class="container">
                            <div class="carousel-position-four text-center">
                                <h2 class="margin-bottom-20 animate-delay carousel-title-v3 border-bottom-title text-uppercase" data-animation="animated fadeInDown">
                                    Título <br/><span class="color-red-v2">Slider</span><br/> designed
                                </h2>
                                <p class="carousel-subtitle-v2" data-animation="animated fadeInUp">Lorem ipsum dolor sit amet constectetuer diam <br/>
                                    adipiscing elit euismod ut laoreet dolore.</p>
                            </div>
                        </div>
                    </div>

            <!-- Controls -->
            <a class="left carousel-control carousel-control-shop" href="#carousel-example-generic" role="button" data-slide="prev">
                <i class="fa fa-angle-left" aria-hidden="true"></i>
            </a>
            <a class="right carousel-control carousel-control-shop" href="#carousel-example-generic" role="button" data-slide="next">
                <i class="fa fa-angle-right" aria-hidden="true"></i>
            </a>
        </div>
    </div>
    <!-- END SLIDER -->
    <div class="main">
      <div class="container">

        <!-- BEGIN SIDEBAR & CONTENT -->
        
        <div class="row margin-bottom-40"></div>
        <div class="col-lg-12">
        <div class="col-lg-6">
                  
        <!-- END SLIDER -->
        <div class="main">
            <div class="container">       
                <!-- BEGIN SIDEBAR & CONTENT -->

                <div class="row margin-bottom-40"></div>        
                <div class="row">

                    <div class="row product-list">
                        <center><h1>Ultimos Productos</h1></center>
                        <!-- PRODUCT ITEM START -->
                        <div class="col-md-4 col-sm-6 col-xs-6">
                            <div class="product-item">
                                <div class="pi-img-wrapper">
                                    <img src="assets/pages/img/products/model4.jpg" class="img-responsive" alt="Berry Lace Dress">
                                    <div>
                                        <a href="assets/pages/img/products/model4.jpg" class="btn btn-default fancybox-button">Zoom</a>
                                        <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">View</a>
                                    </div>
                                </div>
                                <h3><a href="shop-item.html">Berry Lace Dress Berry Lace Dress</a></h3>
                                <div class="pi-price">$29.00</div>
                                <a href="javascript:;" class="btn btn-default add2cart">Add to cart</a>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="product-item">
                                <div class="pi-img-wrapper">
                                    <img src="assets/pages/img/products/model5.jpg" class="img-responsive" alt="Berry Lace Dress">
                                    <div>
                                        <a href="assets/pages/img/products/model5.jpg" class="btn btn-default fancybox-button">Zoom</a>
                                        <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">View</a>
                                    </div>
                                </div>
                                <h3><a href="shop-item.html">Berry Lace Dress Berry Lace Dress</a></h3>
                                <div class="pi-price">$29.00</div>
                                <a href="javascript:;" class="btn btn-default add2cart">Add to cart</a>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="product-item">
                                <div class="pi-img-wrapper">
                                    <img src="assets/pages/img/products/model6.jpg" class="img-responsive" alt="Berry Lace Dress">
                                    <div>
                                        <a href="assets/pages/img/products/model6.jpg" class="btn btn-default fancybox-button">Zoom</a>
                                        <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">View</a>
                                    </div>
                                </div>
                                <h3><a href="shop-item.html">Berry Lace Dress Berry Lace Dress</a></h3>
                                <div class="pi-price">$29.00</div>
                                <a href="javascript:;" class="btn btn-default add2cart">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- PRODUCT ITEM END -->
                <div class="row">

                    <div style="float:right;" class="row product-list">
                        <center><h1 style="text-align: center;">Tendencias</h1></center>
                        <!-- PRODUCT ITEM START -->

                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="product-item">
                                <div class="pi-img-wrapper">
                                    <img src="assets/pages/img/products/model1.jpg" class="img-responsive" alt="Berry Lace Dress">
                                    <div>
                                        <a href="assets/pages/img/products/model1.jpg" class="btn btn-default fancybox-button">Zoom</a>
                                        <a href="#" class="btn btn-default fancybox-fast-view">View</a>
                                    </div>
                                </div>
                                <h3><a href="shop-item.html">Berry Lace Dress Berry Lace Dress</a></h3>
                                <div class="pi-price">$29.00</div>
                                <a href="javascript:;" class="btn btn-default add2cart">Add to cart</a>
                            </div>
                        </div>


                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="product-item">
                                <div class="pi-img-wrapper">
                                    <img src="assets/pages/img/products/model2.jpg" class="img-responsive" alt="Berry Lace Dress">
                                    <div>
                                        <a href="assets/pages/img/products/model2.jpg" class="btn btn-default fancybox-button">Zoom</a>
                                        <a href="#" class="btn btn-default fancybox-fast-view">View</a>
                                    </div>
                                </div>
                                <h3><a href="shop-item.html">Berry Lace Dress Berry Lace Dress</a></h3>
                                <div class="pi-price">$29.00</div>
                                <a href="javascript:;" class="btn btn-default add2cart">Add to cart</a>
                            </div>
                        </div>


                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="product-item">
                                <div class="pi-img-wrapper">
                                    <img src="assets/pages/img/products/model3.jpg" class="img-responsive" alt="Berry Lace Dress">
                                    <div>
                                        <a href="assets/pages/img/products/model3.jpg" class="btn btn-default fancybox-button">Zoom</a>
                                        <a href="#" class="btn btn-default fancybox-fast-view">View</a>
                                    </div>
                                </div>
                                <h3><a href="shop-item.html">Berry Lace Dress Berry Lace Dress</a></h3>
                                <div class="pi-price">$29.00</div>
                                <a href="javascript:;" class="btn btn-default add2cart">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- PRODUCT ITEM END -->
            </div>




        </div>
    </div>
</div>
<!-- END CONTENT -->
</div>
<!-- END SIDEBAR & CONTENT -->

<!-- BEGIN SIMILAR PRODUCTS -->
<div class="row margin-bottom-40">
    <div class="col-md-12 col-sm-12">
        <center><h2>Categorias</h2></center>
        <div class="owl-carousel owl-carousel4">
            <div>
                <div class="product-item">
                    <div class="pi-img-wrapper">
                        <img src="assets/pages/img/products/k1.jpg" class="img-responsive" alt="Berry Lace Dress">
                        <div>
                            <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">Entrar</a>
                        </div>
                    </div>
                    <h3><a href="shop-item.html">Ropa</a></h3>
                </div>
            </div>
            <div>
                <div class="product-item">
                    <div class="pi-img-wrapper">
                        <img src="assets/pages/img/mueble.jpg" class="img-responsive" alt="Berry Lace Dress">
                        <div>
                            <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">Entrar</a>
                        </div>
                    </div>
                    <h3><a href="shop-item.html">Muebles</a></h3>
                </div>
            </div>
            <div>
                <div class="product-item">
                    <div class="pi-img-wrapper">
                        <img src="assets/pages/img/Electrodomestico.jpg" class="img-responsive" alt="Berry Lace Dress">
                        <div>
                            <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">Entrar</a>
                        </div>
                    </div>
                    <h3><a href="shop-item.html">Electrodomésticos</a></h3>
                </div>
            </div>
            <div>
                <div class="product-item">
                    <div class="pi-img-wrapper">
                        <img src="assets/pages/img/dispositivoElectronico.jpg" class="img-responsive" alt="Berry Lace Dress">
                        <div>
                            <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">Entrar</a>
                        </div>
                    </div>
                    <h3><a href="shop-item.html">Dispositivos Electrónicos</a></h3>

                </div>
            </div>

        </div>
    </div>
    <!-- END SIMILAR PRODUCTS -->
</div>
<jsp:include page="footer.jsp" />
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
</script>
<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>