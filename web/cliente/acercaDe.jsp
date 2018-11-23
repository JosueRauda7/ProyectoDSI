<%-- 
    Document   : misCompras
    Created on : 09-26-2018, 06:16:30 PM
    Author     : admi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BigShop</title>
        <jsp:include page="/head.jsp"/>   
        
        <jsp:include page="/scripts.jsp"/>
    </head>
    <!-- BEGIN NAVIGATION -->
                
    <body>
        <jsp:include page="menuCliente.jsp"/>
        <div class="main">
            <div class="container">
                <div class="col-md-12 col-sm-12">
                    <div class="col-md-12 col-sm-12">
                        <div class="margin-bottom-20"><div class="row"></div></div>
                        <h1>Acerca de Nosotros</h1>
                        <div class="col-lg-12">
                            <center><img class="img-responsive" src="assets/logoBigShop.png" /></center>
                        </div>
                        <div class="content-page col-lg-6">

                            <div class="col-lg-12">
                                <h3><center>¿Quiénes somos?</center></h3><hr>
                                <p style="text-align: justify;">BigShop es una tienda en línea
                                    dedicada a la venta de diferentes
                                    artículos de gran variedad.
                                    Además, con el sitio web se espera generar en
                                    los usuarios finales de El Salvador una experiencia
                                    agradable que genere un sentido de pertenencia hacia
                                    la empresa, una mayor confianza y preferencia en utilizar
                                    plataformas e-commerce para realizar sus próximas compras.  </p>
                                <p style="text-align: justify;">
                                    BigShop es una oportunidad para todo tipo de empresas, en el cuál es abierto
                                    de recibirlos y apoyarlos ofrenciendole nuestro sitio web como servicio, e ir innovando
                                    día tras día nuevas estrategias para que la empresa permanezca como un líder
                                    de comercio electrónico.
                                </p>
                            </div>



                            <div class="col-lg-12">
                                <h3><center><span class="fa fa-shield"></span> Misión</center></h3><hr>
                                <p style="text-align: justify;">Garantizar y satisfacer la confiabilidad, la automatización de procesos de ventas de productos, control de finanzas y la promoción de productos de empresas de manera atrayente, dinámica, eficaz y eficiente.</p>
                            </div>
                            <div class="col-lg-12">
                                <h3><center><span class="fa fa-diamond"></span> Visión</center></h3><hr>
                                <p style="text-align: justify;">Ser un referente de clientes para la venta de productos en línea por su confianza y fiabilidad en el servicio que se ofrece a empresas sin importar si es micro, mediano o macro.</p>
                            </div>
                            <div class="col-lg-12">
                                <h3><center>Redes sociales</center></h3><hr>
                                <div class="margin-bottom-15"></div>
                                <div class="col-lg-5"></div>
                                <ul class="col-lg-3 social-icons">
                                    <li><a class="facebook" data-original-title="facebook" href="javascript:;"></a></li>
                                    <li><a class="twitter" data-original-title="twitter" href="javascript:;"></a></li>
                                    <li><a class="googleplus" data-original-title="googleplus" href="javascript:;"></a></li>
                                </ul>
                                <div class="margin-bottom-15"></div>
                            </div>
                        </div>
                        <div class="content-page col-lg-5">
                            <h3><center>Equipo de Desarrollo.</center></h3><hr>
                            <div style="background-color: rgba(0,0,0,0.4); padding: 2%;" class="col-lg-12">
                                <img class="col-lg-4" height="100" src="assets/imagen1.png" /><div class="margin-bottom-25"></div>
                                <p class="col-lg-8" style="text-align: center;">Project Manager y Desarrollador de Software</p>
                            </div>
                            <div class="margin-bottom-15"><div class="row"></div></div>
                            <div style="background-color: rgba(0,0,0,0.2); padding: 2%;" class="col-lg-12">
                                <img class="col-lg-4" height="100" src="assets/imagen2.png" /><div class="margin-bottom-35"></div>
                                <p class="col-lg-8" style="text-align: center;">Desarrollador de Software</p>
                            </div>
                            <div class="margin-bottom-15"><div class="row"></div></div>
                            <div style="background-color: rgba(0,0,0,0.4); padding: 2%;" class="col-lg-12">
                                <img class="col-lg-4" height="100" src="assets/imagen3.png" /><div class="margin-bottom-25"></div>
                                <p class="col-lg-8" style="text-align: center;">Analista en Sistemas y Desarrollador de Software</p>
                            </div>
                            <div class="margin-bottom-15"><div class="row"></div></div>
                            <div style="background-color: rgba(0,0,0,0.2); padding: 2%;" class="col-lg-12">
                                <img class="col-lg-4"  height="100" src="assets/imagen4.png" />
                                <div class="margin-bottom-25"></div>
                                <p class="col-lg-8" style="text-align: center;">Database Manager y Desarrollador de Software</p>
                            </div>
                        </div>
                        <!-- END CONTENT -->
                    </div>
                </div>
            </div>
        </div>
                 <script type="text/javascript">
            jQuery(document).ready(function () {
                Layout.init();
                Layout.initOWL();
                Layout.initTwitter();
                Layout.initImageZoom();
                Layout.initTouchspin();
                Layout.initUniform();
            });

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
        </script>



    <div class="margin-bottom-40" style="margin-top: 150px;"></div>
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
