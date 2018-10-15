

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
       
        <!-- END BEGIN STYLE CUSTOMIZER --> 


        <!-- BEGIN HEADER -->
             <jsp:include page="/menu.jsp"/>
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