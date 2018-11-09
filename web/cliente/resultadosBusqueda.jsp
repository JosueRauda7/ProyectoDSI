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

            <br/><br/>
            <center><h1>Resultados de la busqueda: ${requestScope.datoBusqueda}</h1></center>
            <hr/>
            <br/>

            <div class="row margin-bottom-40">

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



                </div>

                <div class="col-md-9 col-sm-7">

                    <c:if test="${empty requestScope.listarProductos}">
                        <h2>No se ha encontrado ningún producto</h2><br/>
                    </c:if>
                        <div class="row product-list">
                    <c:if test="${not empty requestScope.listarProductos}">
                        <div class="row">
                            <c:forEach var="ultpro" items="${requestScope.listarProductos}">
                                <div class="col-md-4 col-sm-6 col-xs-12" >
                                    <div class="product-item" style="height: 375px;">

                                        <c:forEach var="productoOferta" items="${requestScope.listarProductosOfertados}">

                                            <c:if test="${productoOferta.producto.idProducto eq ultpro.idProducto}">

                                                <div style="display: none;">${ultpro.precioRegular = productoOferta.totalDescuento}</div>

                                            </c:if>

                                        </c:forEach>

                                        <div class="pi-img-wrapper">
                                            <img src="images/${ultpro.urlImagen}"  style="height: 250px;" class="img-responsive" alt="Berry Lace Dress">
                                            <div>
                                                <a href="images/${ultpro.urlImagen}" class="btn btn-default fancybox-button">Ver producto</a>                               
                                            </div>
                                        </div>
                                        <h3 class="text-center"><a href="${base}/clientes.do?operacion=verProducto&idproduct=${ultpro.idProducto}">${ultpro.producto}</a></h3>
                                        <div class="pi-price">$${ultpro.precioRegular}</div>
                                        
                                        <c:set var="i" value="${0}" />
                                        
                                        <c:forEach var="productoOferta" items="${requestScope.listarProductosOfertados}">
                                            
                                            <c:if test="${productoOferta.producto.idProducto eq ultpro.idProducto}">
                                                <a href="${base}/clientes.do?operacion=agregarOferta&idproducto=${ultpro.idProducto}&cantidad=1&idoferta=${productoOferta.idOferta}" class="btn btn-default add2cart">Agregar al carrito</a>
                                            </c:if>
                                            <c:if test="${productoOferta.producto.idProducto ne ultpro.idProducto}">
                                                <div style="display: none;">${i = i+1}</div>
                                                <c:if test="${i eq requestScope.cantidadOfertas}">
                                                    <a href="${base}/clientes.do?operacion=agregarProducto&idproduct=${ultpro.idProducto}&cantidad=1" class="btn btn-default add2cart">Agregar al carrito</a>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>


                                    </div>
                                        
                                    <c:forEach var="productoOferta" items="${requestScope.listarProductosOfertados}">
                                        <c:if test="${productoOferta.producto.idProducto eq ultpro.idProducto}">
                                            <div class="sticker sticker-sale" style="margin-left: 4%;"></div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <br/>
                    <div class="row">
                        <div class="col-md-4 col-sm-4 items-info">Articulos ${requestScope.pagina+1} a ${requestScope.paginas+1} de ${requestScope.paginas+1} en total</div>
                        <div class="col-md-8 col-sm-8">
                            <ul class="pagination pull-right">
                                <c:if test="${requestScope.pagina <=0}"> 
                                    <li><a href="${base}/clientes.do?operacion=otraPagina1&pagina=${requestScope.pagina}&categoria=${requestScope.categoria}&nombre=${requestScope.datoBusqueda}">&laquo;</a></li>
                                    </c:if>
                                    <c:if test="${requestScope.pagina >0 }">
                                    <li><a href="${base}/clientes.do?operacion=otraPagina1&pagina=${requestScope.pagina-1}&categoria=${requestScope.categoria}&nombre=${requestScope.datoBusqueda}">&laquo;</a></li>
                                    </c:if>

                                <c:forEach var = "i" begin = "0" end = "${requestScope.paginas}">
                                    <li><a href="${base}/clientes.do?operacion=otraPagina1&pagina=${i}&categoria=${requestScope.categoria}&nombre=${requestScope.datoBusqueda}">${i+1}</a></li>
                                    </c:forEach>
                                    <c:if test="${requestScope.pagina >= requestScope.paginas}"> 
                                    <li><a href="${base}/clientes.do?operacion=otraPagina1&pagina=${requestScope.pagina}&categoria=${requestScope.categoria}&nombre=${requestScope.datoBusqueda}">&raquo;</a></li>
                                    </c:if>
                                    <c:if test="${requestScope.pagina < requestScope.paginas}">
                                    <li><a href="${base}/clientes.do?operacion=otraPagina1&pagina=${requestScope.pagina+1}&categoria=${requestScope.categoria}&nombre=${requestScope.datoBusqueda}">&raquo;</a></li>
                                    </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
                        </div>
            </div>
        </div>


        <br/><br/><br/>

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

        <jsp:include page="footer.jsp"/>

        <script src="assets/pages/scripts/ModalLog.js" type="text/javascript"></script>

        <!-- END PAGE LEVEL JAVASCRIPTS -->
    </body>

</html>