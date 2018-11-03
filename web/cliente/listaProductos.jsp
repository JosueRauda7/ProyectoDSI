<%-- 
    Document   : comprasCliente
    Created on : 18-sep-2018, 18:24:00
    Author     : Ferh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <title>Productos busqueda</title>

        <jsp:include page="/head.jsp"/>        

        <jsp:include page="/scripts.jsp"/>
    </head>

    <!-- Head END -->

    <body class="ecommerce">


        <jsp:include page="menuCliente.jsp"/>


        <div class="container">
            <h1>Muebles</h1>
            <div class="row margin-bottom-40">
                <!-- BEGIN SIDEBAR -->
                <div class="sidebar col-md-3 col-sm-5">
                    <ul class="list-group margin-bottom-25 sidebar-menu">
                        <li class="list-group-item clearfix"><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Ladies</a></li>
                        <li class="list-group-item clearfix dropdown active">
                            <a href="javascript:void(0);" class="collapsed">
                                <i class="fa fa-angle-right"></i>
                                Mens

                            </a>
                            <ul class="dropdown-menu" style="display:block;">
                                <li class="list-group-item dropdown clearfix active">
                                    <a href="javascript:void(0);" class="collapsed"><i class="fa fa-angle-right"></i> Shoes </a>
                                    <ul class="dropdown-menu" style="display:block;">
                                        <li class="list-group-item dropdown clearfix">
                                            <a href="javascript:void(0);"><i class="fa fa-angle-right"></i> Classic </a>
                                            <ul class="dropdown-menu">
                                                <li><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Classic 1</a></li>
                                                <li><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Classic 2</a></li>
                                            </ul>
                                        </li>
                                        <li class="list-group-item dropdown clearfix active">
                                            <a href="javascript:void(0);" class="collapsed"><i class="fa fa-angle-right"></i> Sport  </a>
                                            <ul class="dropdown-menu" style="display:block;">
                                                <li class="active"><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Sport 1</a></li>
                                                <li><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Sport 2</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Trainers</a></li>
                                <li><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Jeans</a></li>
                                <li><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Chinos</a></li>
                                <li><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> T-Shirts</a></li>
                            </ul>
                        </li>
                        <li class="list-group-item clearfix"><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Kids</a></li>
                        <li class="list-group-item clearfix"><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Accessories</a></li>
                        <li class="list-group-item clearfix"><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Sports</a></li>
                        <li class="list-group-item clearfix"><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Brands</a></li>
                        <li class="list-group-item clearfix"><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Electronics</a></li>
                        <li class="list-group-item clearfix"><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Home & Garden</a></li>
                        <li class="list-group-item clearfix"><a href="shop-product-list.html"><i class="fa fa-angle-right"></i> Custom Link</a></li>
                    </ul>

                    <div class="sidebar-filter margin-bottom-25">
                        <h2>Filter</h2>
                        <h3>Availability</h3>
                        <div class="checkbox-list">
                            <label><input type="checkbox"> Not Available (3)</label>
                            <label><input type="checkbox"> In Stock (26)</label>
                        </div>

                        <h3>Price</h3>
                        <p>
                            <label for="amount">Range:</label>
                            <input type="text" id="amount" style="border:0; color:#f6931f; font-weight:bold;">
                        </p>
                        <div id="slider-range"></div>
                    </div>

                    <div class="sidebar-products clearfix">
                        <h2>Bestsellers</h2>
                        <div class="item">
                            <a href="shop-item.html"><img src="assets/pages/img/products/k1.jpg" alt="Some Shoes in Animal with Cut Out"></a>
                            <h3><a href="shop-item.html">Some Shoes in Animal with Cut Out</a></h3>
                            <div class="price">$31.00</div>
                        </div>
                        <div class="item">
                            <a href="shop-item.html"><img src="assets/pages/img/products/k4.jpg" alt="Some Shoes in Animal with Cut Out"></a>
                            <h3><a href="shop-item.html">Some Shoes in Animal with Cut Out</a></h3>
                            <div class="price">$23.00</div>
                        </div>
                        <div class="item">
                            <a href="shop-item.html"><img src="assets/pages/img/products/k3.jpg" alt="Some Shoes in Animal with Cut Out"></a>
                            <h3><a href="shop-item.html">Some Shoes in Animal with Cut Out</a></h3>
                            <div class="price">$86.00</div>
                        </div>
                    </div>
                </div>
                <!-- END SIDEBAR -->
                <!-- BEGIN CONTENT -->
                <div class="col-md-9 col-sm-7">
                    <div class="row list-view-sorting clearfix">
                        <div class="col-md-2 col-sm-2 list-view">
                            <a href="javascript:;"><i class="fa fa-th-large"></i></a>
                            <a href="javascript:;"><i class="fa fa-th-list"></i></a>
                        </div>
                        <div class="col-md-10 col-sm-10">
                            <div class="pull-right">
                                <label class="control-label">Show:</label>
                                <select class="form-control input-sm">
                                    <option value="#?limit=24" selected="selected">24</option>
                                    <option value="#?limit=25">25</option>
                                    <option value="#?limit=50">50</option>
                                    <option value="#?limit=75">75</option>
                                    <option value="#?limit=100">100</option>
                                </select>
                            </div>
                            <div class="pull-right">
                                <label class="control-label">Sort&nbsp;By:</label>
                                <select class="form-control input-sm">
                                    <option value="#?sort=p.sort_order&amp;order=ASC" selected="selected">Default</option>
                                    <option value="#?sort=pd.name&amp;order=ASC">Name (A - Z)</option>
                                    <option value="#?sort=pd.name&amp;order=DESC">Name (Z - A)</option>
                                    <option value="#?sort=p.price&amp;order=ASC">Price (Low &gt; High)</option>
                                    <option value="#?sort=p.price&amp;order=DESC">Price (High &gt; Low)</option>
                                    <option value="#?sort=rating&amp;order=DESC">Rating (Highest)</option>
                                    <option value="#?sort=rating&amp;order=ASC">Rating (Lowest)</option>
                                    <option value="#?sort=p.model&amp;order=ASC">Model (A - Z)</option>
                                    <option value="#?sort=p.model&amp;order=DESC">Model (Z - A)</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- BEGIN PRODUCT LIST -->
                    <div class="row product-list">
                        <!-- PRODUCT ITEM START -->
                        <c:forEach var="productos" items="${requestScope.listaProductos}">
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="product-item">
                                    <div class="pi-img-wrapper">
                                        <img src="images/${productos.producto.urlImagen}" class="img-responsive" alt="Berry Lace Dress" style="height: 250px;">
                                        <div>
                                            <a href="images/${productos.producto.urlImagen}" class="btn btn-default fancybox-button">Ver producto</a>

                                        </div>
                                    </div>
                                    <h3><a href="${base}/clientes.do?operacion=verProducto&idproduct=${productos.producto.idProducto}">${productos.producto.producto}</a></h3>
                                        <c:if test="${productos.idOferta != 0}">
                                        <div class="pi-price">$${productos.totalDescuento}</div>
                                    </c:if>
                                    <c:if test="${productos.idOferta == 0}">
                                        <div class="pi-price">$${productos.producto.precioRegular}</div>
                                    </c:if>
                                    <a href="${base}/clientes.do?operacion=verificarCompra&idproducto=${productos.producto.idProducto}&idoferta=${productos.idOferta}&cantidad=1" class="btn btn-default add2cart">Agregar al carrito</a>
                                </div>
                                <c:if test="${productos.idOferta != 0}">
                                    <div class="sticker sticker-sale"></div>
                                </c:if>
                            </div>

                        </c:forEach>
                        <!-- PRODUCT ITEM END -->
                    </div>




                    <!-- PRODUCT ITEM END -->
                </div>
                <!-- END PRODUCT LIST -->
                <!-- BEGIN PAGINATOR -->
                <div class="row">
                    <div class="col-md-4 col-sm-4 items-info">Items 1 to 9 of 10 total</div>
                    <div class="col-md-8 col-sm-8">
                        <ul class="pagination pull-right">
                            <li><a href="javascript:;">&laquo;</a></li>
                                <c:forEach var = "i" begin = "0" end = "${requestScope.paginas}">
                                <li><a href="${base}/clientes.do?operacion=otraPagina&pagina=${i}&idsubcat=${requestScope.idsubcat}">${i+1}</a></li>
                                </c:forEach>
                            <li><a href="javascript:;">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
                <!-- END PAGINATOR -->
            </div>
            <!-- END CONTENT -->
        </div>


    </div>


    <jsp:include page="footer.jsp"/>

    <!-- END PAGE LEVEL JAVASCRIPTS -->

    <script type="text/javascript">
        jQuery(document).ready(function () {
            Layout.init();
            Layout.initOWL();
            Layout.initTwitter();
            Layout.initImageZoom();
            Layout.initTouchspin();
            Layout.initUniform();
            Layout.initSliderRange();
        });
    </script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script><!-- for slider-range -->

</body>
</html>