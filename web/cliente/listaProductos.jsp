<%-- 
    Document   : comprasCliente
    Created on : 18-sep-2018, 18:24:00
    Author     : Ferh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
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
                        <c:forEach var="categorias" items="${requestScope.listaCategorias}">
                            <li class="list-group-item clearfix dropdown">
                                <a href="javascript:void(0);" class="collapsed"><i class="fa fa-angle-right"></i>${categorias.categoria}</a>
                                    <sql:query var="ql" dataSource="jdbc/mysql">
                                    SELECT * FROM sub_categoria WHERE id_categoria=${categorias.idCategoria}
                                </sql:query>
                                <c:forEach var="subcat" items="${ql.rows}">
                                    <ul class="dropdown-menu" >
                                        <li class="list-group-item dropdown clearfix">
                                            <a href="${base}/clientes.do?operacion=listaProductos&idsubcat=${subcat.id_sub_categoria}" class="collapsed"><i class="fa fa-minus"></i> ${subcat.subcategoria}</a>
                                        </li>                      
                                    </ul>
                                </c:forEach>
                            </li>
                        </c:forEach>

                    </ul>

                    <div class="sidebar-filter margin-bottom-25">
                        <h2>Filtra</h2>

                        <h3>Precio</h3>
                        <p>
                            <label for="amount">Rango:</label>
                            <input type="text" id="amount"  oncuechange="verprecio();"  style="border:0; color:#f6931f; font-weight:bold;">
                        </p>
                        <div id="slider-range" ></div>
                        <br>
                        <button class="btn btn-primary" id="btnfiltrar" style="margin-left: 35%;"><span class="glyphicon glyphicon-search"></span> Buscar</button>
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
                        <ul id="myTab" class="nav nav-tabs">
                            <li class="active"><a href="#Description" data-toggle="tab">Productos</a></li>                           
                            <li><a href="#Reviews" data-toggle="tab">ofertas</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="Description">
                                <!-- PRODUCT ITEM START -->
                                <c:forEach var="productos" items="${requestScope.listaProductos}">
                                    <div class="col-md-4 col-sm-6 col-xs-12">
                                        <div class="product-item" style="height: 375px;">
                                            <div class="pi-img-wrapper">
                                                <img src="images/${productos.urlImagen}" class="img-responsive" alt="Berry Lace Dress" style="height: 250px;">
                                                <div>
                                                    <a href="images/${productos.urlImagen}" class="btn btn-default fancybox-button">Ver producto</a>

                                                </div>
                                            </div>
                                            <h3><a href="${base}/clientes.do?operacion=verProducto&idproduct=${productos.idProducto}">${productos.producto}</a></h3>
                                            <div class="pi-price">$${productos.precioRegular}</div>
                                            <a href="${base}/clientes.do?operacion=agregarProducto&idproduct=${productos.idProducto}&cantidad=1" class="btn btn-default add2cart">Agregar al carrito</a>
                                        </div>

                                    </div>

                                </c:forEach>
                                <div class="row">
                                    <div class="col-md-4 col-sm-4 items-info">Items ${requestScope.pagina+1} to 9 of 10 total</div>
                                    <div class="col-md-8 col-sm-8">
                                        <ul class="pagination pull-right">
                                            <c:if test="${requestScope.pagina <=0}"> 
                                                <li><a href="${base}/clientes.do?operacion=otraPagina&pagina=${requestScope.pagina}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}">&laquo;</a></li>
                                                </c:if>
                                                <c:if test="${requestScope.pagina >0 }">
                                                <li><a href="${base}/clientes.do?operacion=otraPagina&pagina=${requestScope.pagina-1}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}">&laquo;</a></li>
                                                </c:if>

                                            <c:forEach var = "i" begin = "0" end = "${requestScope.paginas}">
                                                <li><a href="${base}/clientes.do?operacion=otraPagina&pagina=${i}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}">${i+1}</a></li>
                                                </c:forEach>
                                                <c:if test="${requestScope.pagina >= requestScope.paginas}"> 
                                                <li><a href="${base}/clientes.do?operacion=otraPagina&pagina=${requestScope.pagina}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}">&raquo;</a></li>
                                                </c:if>
                                                <c:if test="${requestScope.pagina < requestScope.paginas}">
                                                <li><a href="${base}/clientes.do?operacion=otraPagina&pagina=${requestScope.pagina+1}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}">&raquo;</a></li>
                                                </c:if>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="Reviews">
                                <c:forEach var="ofertas" items="${requestScope.listaOfertas}">
                                    <div class="col-md-4 col-sm-6 col-xs-12">
                                        <div class="product-item" style="height: 375px;">
                                            <div class="pi-img-wrapper">
                                                <img src="images/${ofertas.urlFoto}" class="img-responsive" alt="Berry Lace Dress" style="height: 250px;">
                                                <div>
                                                    <a href="images/${ofertas.urlFoto}" class="btn btn-default fancybox-button">Ver oferta</a>

                                                </div>
                                            </div>
                                            <h3><a href="${base}/clientes.do?operacion=verProducto&idproduct=${ofertas.producto.idProducto}">${ofertas.titulo}</a></h3>
                                            <div class="pi-price">$${ofertas.totalDescuento}</div>
                                            <a href="${base}/clientes.do?operacion=agregarOferta&idproducto=${ofertas.producto.idProducto}&cantidad=1&idoferta=${ofertas.idOferta}" class="btn btn-default add2cart">Agregar al carrito</a>
                                        </div>
                                        <div class="sticker sticker-sale" style="margin-left: 5%;"></div>
                                    </div>

                                </c:forEach>
                            </div>
                        </div>
                        <!-- PRODUCT ITEM END -->
                    </div>




                    <!-- PRODUCT ITEM END -->
                </div>
                <!-- END PRODUCT LIST -->
                <!-- BEGIN PAGINATOR -->

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
    <script>
    
        document.getElementById("btnfiltrar").addEventListener("click", function () {
            var valor = document.getElementById("amount").value;
            var count = valor.indexOf("-");
            var precio1 = valor.substring(1, count - 1);
            var precio2 = valor.substring(count + 3, 11);
            location.href = 'clientes.do?operacion=listaProductos&idsubcat='+${requestScope.idsubcat}+'&precio1='+ precio1+ '&precio2='+precio2;

        });

    </script>
</body>
</html>