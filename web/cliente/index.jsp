

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html lang="en">
    <!--<![endif]-->

    <!-- Head BEGIN -->
    <head>
        <title>Index</title>
        <jsp:include page="/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
    </head>
    <!-- Head END -->

    <!-- Body BEGIN -->
    <body class="ecommerce" style="overflow-x: hidden;">
        <jsp:include page="menuCliente.jsp"/>
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
                                <h2 class="margin-bottom-20 animate-delay carousel-title-v3 border-bottom-title" data-animation="animated fadeInDown">
                                    Biebvenido a<br/><span class="color-red-v2">BigShop</span><br/>Tu tienda online
                                </h2>
                                <p class="carousel-subtitle-v2" data-animation="animated fadeInUp">BigShop se convertira en tu tienda favorita <br/>
                                    te</p>
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
                                            <hr>
                                            <c:forEach var="ultpro" items="${requestScope.ultimosProductos}">
                                                <div class="col-md-4 col-sm-6 col-xs-12" >
                                                    <div class="product-item" style="height: 450px;">
                                                        <div class="pi-img-wrapper">
                                                            <img src="images/${ultpro.urlImagen}"  style="height: 350px;" class="img-responsive" alt="Berry Lace Dress">
                                                            <div>
                                                                <a href="images/${ultpro.urlImagen}" class="btn btn-default fancybox-button">Ver producto</a>                               
                                                            </div>
                                                        </div>
                                                        <h3 class="text-center"><a href="${base}/clientes.do?operacion=verProducto&idproduct=${ultpro.idProducto}">${ultpro.producto}</a></h3>
                                                        <div class="pi-price">Precio: $${ultpro.precioRegular}</div>

                                                    </div>
                                                </div>
                                            </c:forEach>
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
                            <c:forEach items="${requestScope.listaCategorias}"  var="categoriasfoot">
                                <div>
                                    <div class="product-item">
                                        <div class="pi-img-wrapper">
                                            <img src="images/${categoriasfoot.urlCategoria}" class="img-responsive" alt="Berry Lace Dress">
                                            <div>
                                                <a href="images/${categoriasfoot.urlCategoria}" class="btn btn-default fancybox-button">Ver categoria</a> 
                                            </div>
                                        </div>
                                        <h3 class="text-center"><a href="${base}/clientes.do?operacion=versubcategoria&idcat=${categoriasfoot.idCategoria}">${categoriasfoot.categoria}</a></h3>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                    <!-- END SIMILAR PRODUCTS -->
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

            <!-- END PAGE LEVEL JAVASCRIPTS -->
    </body>
    <jsp:include page="footer.jsp" />
    <!-- END BODY -->
</html>