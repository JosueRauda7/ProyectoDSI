

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html lang="en" style="overflow-y: scroll;">
    <!--<![endif]-->

    <!-- Head BEGIN -->
    <head>
        <title>Index</title>
        <jsp:include page="/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
    </head>
    <!-- Head END -->
    <jsp:include page="menuCliente2.jsp"/>
    <!-- Body BEGIN -->
    <body class="ecommerce" style="overflow-x: hidden; ">

        <div id="myCarousel" class="carousel slide" data-ride="carousel" style="height: 570px;">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                <li data-target="#myCarousel" data-slide-to="3"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active" id="myNavbar">

                    <img src="images/shopping.png" alt="Los Angeles"  style="height: 570px;">
                    <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0,0,0,.5)">
                        <h1 style="color: orangered;">Bienvenido a BigShop</h1>
                        <p>Tu sitio de compras online.</p>
                        <a href="#ultimos"><button class="btn btn-primary">Empezar a comprar</button></a>
                    </div>
                </div>
                <c:forEach var="ofertas" items="${requestScope.ultimasOfertas}">
                    <div class="item">
                        <img  src="images/${ofertas.urlFoto}" alt="New York" style="height: 570px; width: 100%;">
                        <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0,0,0,.5)">
                            <h1 style="color: orangered;">${ofertas.titulo}</h1>
                            <p>${ofertas.descripcion}</p>
                            <a href="${base}/clientes.do?operacion=verProducto&idproduct=${ofertas.producto.idProducto}"><button class="btn btn-primary">Ir a la oferta</button></a>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
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
                                <div class="row" id="ultimos">

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
                                                    <a href="${base}/clientes.do?operacion=agregarProducto&idproduct=${ultpro.idProducto}&cantidad=1" class="btn btn-default add2cart">Agregar al carrito</a>
                                                </div>
                                                <div class="sticker sticker-new"></div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <!-- PRODUCT ITEM END -->
                                <div class="row">

                                    <div  class="row product-list">
                                      <center><h1>Ultimas ofertas</h1></center>
                                        <hr>
                                        <c:forEach var="ultofer" items="${requestScope.ultimasOfertas}">
                                            <div class="col-md-4 col-sm-6 col-xs-12" >
                                                <div class="product-item" style="height: 450px;">
                                                    <div class="pi-img-wrapper">
                                                        <img src="images/${ultofer.urlFoto}"  style="height: 350px;" class="img-responsive" alt="Berry Lace Dress">
                                                        <div>
                                                            <a href="images/${ultofer.urlFoto}" class="btn btn-default fancybox-button">Ver imagen</a>                               
                                                        </div>
                                                    </div>
                                                    <h3 class="text-center"><a href="${base}/clientes.do?operacion=verProducto&idproduct=${ultofer.producto.idProducto}">${ultofer.titulo}</a></h3>
                                                    <div class="pi-price">Precio: $${ultofer.totalDescuento}</div>
                                                    <a href="${base}/clientes.do?operacion=agregarOferta&idproducto=${ultofer.producto.idProducto}&cantidad=1&idoferta=${ultofer.idOferta}" class="btn btn-default add2cart">Agregar al carrito</a>
                                                </div>
                                                <div class="sticker sticker-sale" style="margin-left: 4%;"></div>
                                            </div>
                                        </c:forEach>
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
        <script>
            $(document).ready(function () {
                // Add scrollspy to <body>
                $('body').scrollspy({
                    target: ".navbar",
                    offset: 50
                });
                // Add smooth scrolling on all links inside the navbar
                $("#myNavbar a").on('click', function (event) {
                    // Make sure this.hash has a value before overriding default behavior
                    if (this.hash !== "") {
                        // Prevent default anchor click behavior
                        event.preventDefault();
                        // Store hash
                        var hash = this.hash;
                        // Using jQuery's animate() method to add smooth page scroll
                        // The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
                        $('html, body').animate({
                            scrollTop: $(hash).offset().top
                        }, 800, function () {
                            // Add hash (#) to URL when done scrolling (default click behavior)
                            window.location.hash = hash;
                        });
                    } // End if
                });
            });
        </script>

        <!-- END PAGE LEVEL JAVASCRIPTS -->
    </body>
    <jsp:include page="footer.jsp" />
    <!-- END BODY -->
</html>