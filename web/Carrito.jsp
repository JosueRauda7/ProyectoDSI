

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <!--<![endif]-->

    <!-- Head BEGIN -->
    <head>
        <title>Carrito de compra</title>
        <jsp:include page="/head.jsp"/>        
    </head>
    <!-- Head END -->

    <!-- Body BEGIN -->
    <body class="ecommerce">
        <!-- BEGIN STYLE CUSTOMIZER -->

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
        <div class="main" style="margin-bottom: 5%;">
            <div class="container">
                <!-- BEGIN SIDEBAR & CONTENT -->
                <div class="row margin-bottom-40">
                    <!-- BEGIN CONTENT -->
                    <div class="col-md-12 col-sm-12">
                        <h1>Carrito de compras</h1>
                        <div class="goods-page">
                            <div class="goods-data clearfix">
                                <div class="table-wrapper-responsive">
                                    <table summary="Shopping cart">
                                        <tr>
                                            <th class="goods-page-image">Imagen</th>
                                            <th class="goods-page-description">Descripción</th>
                                            <th class="goods-page-ref-no">Codigo producto</th>
                                            <th class="goods-page-quantity">Cantidad</th>
                                            <th class="goods-page-price">Precio unitario</th>
                                            <th class="goods-page-total">Total</th>
                                            <th>Eliminar</th>
                                        </tr>
                                        <tr>
                                            <td class="goods-page-image">
                                                <a href="javascript:;"><img src="assets/pages/img/products/model3.jpg" alt="Berry Lace Dress"></a>
                                            </td>
                                            <td class="goods-page-description">
                                                <h3><a href="javascript:;">Chaleco elegante con botones</a></h3>
                                                <p><strong>Item 1</strong> - Color: Negro; Talla: S</p>
                                                <em>Mas informacion aqui</em>
                                            </td>
                                            <td class="goods-page-ref-no">
                                               
                                                 ROPCHA001
                                            </td>
                                            <td class="goods-page-quantity">
                                                <div class="product-quantity">
                                                    <input id="product-quantity" type="text" value="1" readonly class="form-control input-sm">
                                                </div>
                                            </td>
                                            <td class="goods-page-price">
                                                <strong><span>$</span>47.00</strong>
                                            </td>
                                            <td class="goods-page-total">
                                                <strong><span>$</span>47.00</strong>
                                            </td>
                                            <td >
                                                <a  class="del-goods" >&nbsp;</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="goods-page-image">
                                                <a href="javascript:;"><img src="assets/pages/img/products/model4.jpg" alt="Berry Lace Dress"></a>
                                            </td>
                                            <td class="goods-page-description">
                                                 <h3><a href="javascript:;">Camisa tipo centro Sport</a></h3>
                                                <p><strong>Item 2</strong> - Color: Celeste; Talla: M</p>
                                                <em>Mas informacion aqui</em>
                                            </td>
                                            <td class="goods-page-ref-no">
                                                ROPCAM001
                                            </td>
                                            <td class="goods-page-quantity">
                                                <div class="product-quantity">
                                                    <input id="product-quantity2" type="text" value="1" readonly class="form-control input-sm">
                                                </div>
                                            </td>
                                            <td class="goods-page-price">
                                                <strong><span>$</span>47.00</strong>
                                            </td>
                                            <td class="goods-page-total">
                                                <strong><span>$</span>47.00</strong>
                                            </td>
                                            <td class="del-goods-col">
                                                <a class="del-goods" href="javascript:;">&nbsp;</a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>

                                <div class="shopping-total">
                                    <ul>
                                        <li>
                                            <em>Sub total</em>
                                            <strong class="price"><span>$</span>47.00</strong>
                                        </li>
                                        <li>
                                            <em>Costo de envio</em>
                                            <strong class="price"><span>$</span>3.00</strong>
                                        </li>
                                        <li class="shopping-total-price">
                                            <em>Total</em>
                                            <strong class="price"><span>$</span>50.00</strong>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <button class="btn btn-default" type="submit">Continuar comprando <i class="fa fa-shopping-cart"></i></button>
                            <button class="btn btn-primary" type="submit">Proceder a pagar <i class="fa fa-check"></i></button>
                        </div>
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
        <div class="footer" style="width: 100%;-webkit-box-sizing:border-box;
    -moz-box-sizing:border-box;
    box-sizing:border-box;">

            <div >
                <!-- BEGIN COPYRIGHT -->
                <div class="text-center ">
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