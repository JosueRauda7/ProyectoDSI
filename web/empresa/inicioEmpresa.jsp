<%-- 
    Document   : index
    Created on : 09-14-2018, 08:03:03 PM
    Author     : admi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
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
        <title>Empresa</title>
        <jsp:include page="head.jsp"/>        
    </head>
    <!-- Head END -->

    <!-- Body BEGIN -->
    <body class="ecommerce">
        <jsp:include page="menuEmpresa.jsp"/>
        <div class="main">
            <div class="container">
                <div class="row product-list col-lg-12">
                    <center><h1>Bienvenido </h1></center>
                    <!-- PRODUCT ITEM START -->
                    <div class="col-lg-4">
                        <div class="product-item">
                            <div class="text-center">
                                <img src="${base}/assets/pages/img/administrador/productosicon.png" style="min-height: 160px; border-radius: 150px;" class="col-lg-11" alt="Berry Lace Dress">
                            </div><br>
                            <h3 class="text-center"><a href="shop-item.html">Productos</a></h3>
                            <div class="text-center">
                                <a href="${base}/empresas.do?operacion=listar&estado=1" class="btn btn-default" 
                                   style="color: #a8aeb3;border: 1px #ededed solid; padding: 3px 6px;">Administrar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="margin-bottom-40"></div>
            <jsp:include page="footer.jsp"/>
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