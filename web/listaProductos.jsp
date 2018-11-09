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
        <title>Productos</title>

        <jsp:include page="/head.jsp"/>        

        <jsp:include page="/scripts.jsp"/>
    </head>

    <!-- Head END -->

    <body class="ecommerce">


        <jsp:include page="menu.jsp"/>
        <jsp:include page="/modal.jsp"/>


        <div class="container" style="margin-top:25px;">
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
                                            <a href="${base}/public.do?operacion=listaProductos&idsubcat=${subcat.id_sub_categoria}" class="collapsed"><i class="fa fa-minus"></i> ${subcat.subcategoria}</a>
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

                    </div>
                    <!-- BEGIN PRODUCT LIST -->
                    <div class="row product-list">
                        <ul id="myTab" class="nav nav-tabs">
                            <c:if test="${empty requestScope.compagiOfer}">
                                <li class="active"><a href="#Description" data-toggle="tab">Productos</a></li>                           
                                <li><a href="#Reviews" data-toggle="tab">ofertas</a></li>
                                </c:if>
                                <c:if test="${not empty requestScope.compagiOfer}">
                                <li ><a href="#Description" data-toggle="tab">Productos</a></li>                           
                                <li class="active"><a href="#Reviews" data-toggle="tab">ofertas</a></li>
                                </c:if>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <c:if test="${empty requestScope.compagiOfer}">
                                <div class="tab-pane fade in active" id="Description">
                                </c:if>
                                <c:if test="${not empty requestScope.compagiOfer}">
                                    <div class="tab-pane fade" id="Description">
                                    </c:if>
                                        
                                    <!-- PRODUCT ITEM START -->
                                    <div class="row">
                                    <c:forEach var="productos" items="${requestScope.listaProductos}">
                                        <div class="col-md-4 col-sm-6 col-xs-12">
                                            <div class="product-item" style="height: 375px;">
                                                <div class="pi-img-wrapper">
                                                    <img src="images/${productos.urlImagen}" class="img-responsive" alt="Berry Lace Dress" style="height: 250px;">
                                                    <div>
                                                        <a href="images/${productos.urlImagen}" class="btn btn-default fancybox-button">Ver producto</a>

                                                    </div>
                                                </div>
                                                <h3 class="text-center"><a href="${base}/public.do?operacion=verProducto&idproduct=${productos.idProducto}">${productos.producto}</a></h3>
                                                <div class="pi-price" style="text-align: center;float:none;">$${productos.precioRegular}</div>

                                            </div>

                                        </div>

                                    </c:forEach>
                                        </div>
                                    <div class="row">
                                        <div class="col-md-4 col-sm-4 items-info">Articulos ${requestScope.pagina+1} a ${requestScope.paginas+1} de ${requestScope.paginas+1} en total</div>
                                        <div class="col-md-8 col-sm-8">
                                            <ul class="pagination pull-right">
                                                <c:if test="${requestScope.pagina <=0}"> 
                                                    <li><a href="${base}/public.do?operacion=otraPagina&pagina=${requestScope.pagina}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}&paginaOfer=${requestScope.paginaOfer}">&laquo;</a></li>
                                                    </c:if>
                                                    <c:if test="${requestScope.pagina >0 }">
                                                    <li><a href="${base}/public.do?operacion=otraPagina&pagina=${requestScope.pagina-1}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}&paginaOfer=${requestScope.paginaOfer}">&laquo;</a></li>
                                                    </c:if>

                                                <c:forEach var = "i" begin = "0" end = "${requestScope.paginas}">
                                                    <li><a href="${base}/public.do?operacion=otraPagina&pagina=${i}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}&paginaOfer=${requestScope.paginaOfer}">${i+1}</a></li>
                                                    </c:forEach>
                                                    <c:if test="${requestScope.pagina >= requestScope.paginas}"> 
                                                    <li><a href="${base}/public.do?operacion=otraPagina&pagina=${requestScope.pagina}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}&paginaOfer=${requestScope.paginaOfer}">&raquo;</a></li>
                                                    </c:if>
                                                    <c:if test="${requestScope.pagina < requestScope.paginas}">
                                                    <li><a href="${base}/public.do?operacion=otraPagina&pagina=${requestScope.pagina+1}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}&paginaOfer=${requestScope.paginaOfer}">&raquo;</a></li>
                                                    </c:if>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${empty requestScope.compagiOfer}">
                                <div class="tab-pane fade" id="Reviews">
                                </c:if>
                                <c:if test="${not empty requestScope.compagiOfer}">
                                    <div class="tab-pane fade in active" id="Reviews">
                                    </c:if>
                                    <c:forEach var="ofertas" items="${requestScope.listaOfertas}">
                                        
                                        <div class="col-md-4 col-sm-6 col-xs-12">
                                            <div class="product-item" style="height: 375px;">
                                                <div class="pi-img-wrapper">
                                                    <img src="images/${ofertas.urlFoto}" class="img-responsive" alt="Berry Lace Dress" style="height: 250px;">
                                                    <div>
                                                        <a href="images/${ofertas.urlFoto}" class="btn btn-default fancybox-button">Ver oferta</a>

                                                    </div>
                                                </div>
                                                <h3><a href="${base}/public.do?operacion=verProducto&idproduct=${ofertas.producto.idProducto}">${ofertas.titulo}</a></h3>
                                                <div class="pi-price" style="text-align: center;float:none;">$${ofertas.totalDescuento}</div>

                                            </div>

                                        </div>

                                    </c:forEach>
                                    
                                    <div class="row">
                                        <div class="col-md-4 col-sm-4 items-info">Articulos ${requestScope.pagina+1} a ${requestScope.paginas+1} de ${requestScope.paginas+1} en total</div>
                                        <div class="col-md-8 col-sm-8">
                                            <ul class="pagination pull-right">
                                                <c:if test="${requestScope.paginaOfer <=0}"> 
                                                    <li><a href="${base}/public.do?operacion=otraPagina&pagina=${requestScope.pagina}&paginaOfer=${requestScope.paginaOfer}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}&compagiOfer=1">&laquo;</a></li>
                                                    </c:if>
                                                    <c:if test="${requestScope.paginaOfer >0 }">
                                                    <li><a href="${base}/public.do?operacion=otraPagina&pagina=${requestScope.pagina}&paginaOfer=${requestScope.paginaOfer-1}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}&compagiOfer=1">&laquo;</a></li>
                                                    </c:if>

                                                <c:forEach var = "i" begin = "0" end = "${requestScope.paginasOfert}">
                                                    <li><a href="${base}/public.do?operacion=otraPagina&paginaOfer=${i}&pagina=${requestScope.pagina}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}&compagiOfer=1">${i+1}</a></li>
                                                    </c:forEach>
                                                    <c:if test="${requestScope.paginaOfer >= requestScope.paginasOfert}"> 
                                                    <li><a href="${base}/public.do?operacion=otraPagina&pagina=${requestScope.pagina}&paginaOfer=${requestScope.paginaOfer}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}&compagiOfer=1">&raquo;</a></li>
                                                    </c:if>
                                                    <c:if test="${requestScope.paginaOfer < requestScope.paginasOfert}">
                                                    <li><a href="${base}/public.do?operacion=otraPagina&pagina=${requestScope.pagina}&paginaOfer=${requestScope.paginaOfer+1}&idsubcat=${requestScope.idsubcat}&precio1=${requestScope.precio1}&precio2=${requestScope.precio2}&compagiOfer=1">&raquo;</a></li>
                                                    </c:if>
                                            </ul>
                                        </div>
                                    </div>
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
        <script src="assets/pages/scripts/ModalLog.js" type="text/javascript"></script>

        <script>

            document.getElementById("btnfiltrar").addEventListener("click", function () {
                var valor = document.getElementById("amount").value;
                var count = valor.indexOf("-");
                var precio1 = valor.substring(1, count - 1);
                var precio2 = valor.substring(count + 3, 11);
                location.href = 'public.do?operacion=listaProductos&idsubcat=' +${requestScope.idsubcat} + '&precio1=' + precio1 + '&precio2=' + precio2;

            });

        </script>
    </body>
</html>