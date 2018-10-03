<%-- 
    Document   : comprasCliente
    Created on : 18-sep-2018, 18:24:00
    Author     : Ferh
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Empresa</title>

        <jsp:include page="head.jsp"/>        
        <!-- Theme styles END -->
    </head>
    <!-- Head END -->

    <body class="ecommerce">


        <jsp:include page="menuEmpresa.jsp"/>


        <div class="container">

            <div class="product-page-content">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#activos" data-toggle="tab">Productos activos</a></li>
                    <li><a href="#vencidos" data-toggle="tab">Productos vencidos</a></li>

                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade in active" id="activos">



                        <div class="goods-page">
                            <div class="goods-data clearfix">
                                <div class="table-wrapper-responsive">
                                    <table summary="Shopping cart">
                                        <tr>
                                            <th class="goods-page-image">Imagen</th>
                                            <th class="goods-page-description">Producto</th>
                                            <th class="goods-page-description">Disponibles</th>
                                            <th class="goods-page-quantity">Precio</th>
                                            <th class="goods-page-quantity">Fecha de vencimiento</th>

                                        </tr>
                                        <tr>
                                            <td class="goods-page-image">
                                                <a href="javascript:;"><img src="assets/pages/img/products/model3.jpg" alt="Berry Lace Dress"></a>
                                            </td>
                                            <td class="goods-page-description">
                                                <h3><a href="javascript:;">Sofá</a></h3>
                                                <p>Café oscuro de madera tapizado</p>

                                            </td>
                                            <td class="goods-page-description">
                                                50
                                            </td>
                                            <td class="goods-page-total">
                                                <strong><span>$</span>100.00</strong>
                                            </td>
                                            <td class="goods-page-quantity">
                                                15-04-2018
                                            </td>



                                        </tr>

                                        <tr>
                                            <td class="goods-page-image">
                                                <a href="javascript:;"><img src="assets/pages/img/products/model3.jpg" alt="Berry Lace Dress"></a>
                                            </td>
                                            <td class="goods-page-description">
                                                <h3><a href="javascript:;">Sofá</a></h3>
                                                <p>Café oscuro de madera tapizado</p>

                                            </td>
                                            <td class="goods-page-description">
                                                50
                                            </td>
                                            <td class="goods-page-total">
                                                <strong><span>$</span>100.00</strong>
                                            </td>
                                            <td class="goods-page-quantity">
                                                15-04-2018
                                            </td>

                                            <td class="goods-page-total">

                                            </td>

                                        </tr>

                                    </table>
                                </div>


                            </div>

                        </div>



                    </div>
                    <div class="tab-pane fade" id="vencidos">


                        <div class="goods-page">
                            <div class="goods-data clearfix">
                                <div class="table-wrapper-responsive">
                                    <table summary="Shopping cart">
                                        <tr>
                                            <th class="goods-page-image">Imagen</th>
                                            <th class="goods-page-description">Producto</th>
                                            <th class="goods-page-description">Disponibles</th>
                                            <th class="goods-page-quantity">Precio</th>
                                            <th class="goods-page-quantity">Fecha de vencimiento</th>
                                            <th class="goods-page-description">Operacion</th>
                                        </tr>
                                        <tr>
                                            <td class="goods-page-image">
                                                <a href="javascript:;"><img src="assets/pages/img/products/model3.jpg" alt="Berry Lace Dress"></a>
                                            </td>
                                            <td class="goods-page-description">
                                                <h3><a href="javascript:;">Sofá</a></h3>
                                                <p>Café oscuro de madera tapizado</p>

                                            </td>
                                            <td class="goods-page-description">
                                                50
                                            </td>
                                            <td class="goods-page-total">
                                                <strong><span>$</span>100.00</strong>
                                            </td>
                                            <td class="goods-page-quantity">
                                                15-04-2018
                                            </td>

                                            <td class="goods-page-description">
                                                <a class="btn btn-primary fancybox-fast-view" style="color:white; float: none;" href="#product-pop-up">Renovar</a>
                                            </td>

                                        </tr>

                                        <tr>
                                            <td class="goods-page-image">
                                                <a href="javascript:;"><img src="assets/pages/img/products/model3.jpg" alt="Berry Lace Dress"></a>
                                            </td>
                                            <td class="goods-page-description">
                                                <h3><a href="javascript:;">Sofá</a></h3>
                                                <p>Café oscuro de madera tapizado</p>

                                            </td>
                                            <td class="goods-page-description">
                                                50
                                            </td>
                                            <td class="goods-page-total">
                                                <strong><span>$</span>100.00</strong>
                                            </td>
                                            <td class="goods-page-quantity">
                                                15-04-2018
                                            </td>

                                            <td class="goods-page-description">

                                                <a class="btn btn-primary fancybox-fast-view" style="color:white; float: none;" href="#product-pop-up">Renovar</a>

                                            </td>

                                        </tr>

                                    </table>
                                </div>


                            </div>

                        </div>


                    </div>

                </div>
            </div>

        </div>






        <div id="product-pop-up" style="display: none; width: 700px;">
            <div class="col-md-12">
                <h3>Renovación de producto</h3>
                <p>Nombre del prodcuto</p>
                <form role="form" action="#">
                    <div class="form-group">
                        <label for="cantidad">Nueva cantidad</label>
                        <input type="text" id="cantidad" class="form-control" name="cantidad">
                    </div>
                    <div class="form-group">
                        <label for="vencimiento">Fecha de vencimiento</label>
                        <input type="date" id="vencimiento" class="form-control" name="vencimiento">
                    </div>
                    
                    <div class="padding-top-20">                  
                        <button class="btn btn-primary" type="submit">Renovar</button>
                    </div>
                    <hr>
                    
                </form>
            </div>
        </div>

        <br/><br/><br/>

        <jsp:include page="footer.jsp"/>

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