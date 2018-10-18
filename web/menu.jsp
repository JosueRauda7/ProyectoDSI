<!-- BEGIN HEADER -->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<div class="header">
    <div class="container">
        <a class="site-logo" href="public.do?operacion=publicIndex">BigShop</a>

        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>

        <!-- BEGIN CART -->
        <div class="top-cart-block">
            <div class="top-cart-info">
                <a href="javascript:void(0);" class="top-cart-info-count">0 artículos</a>
                <a href="javascript:void(0);" class="top-cart-info-value">$0</a>
            </div>
            <i style="background-color:#9E9E9E;" class="fa fa-shopping-cart"></i>

            <div class="top-cart-content-wrapper">
                <div class="top-cart-content">
                    <ul class="scroller" style="height: 250px;">
                        <li>
                            <!-- Objetos de carrito -->
                            <a href="shop-item.html"><img src="assets/pages/img/cart-img.jpg" alt="Rolex Classic Watch" width="37" height="34"></a>
                            <span class="cart-content-count">x 1</span>
                            <strong><a href="shop-item.html">Rolex Classic Watch</a></strong>
                            <em>$1230</em>
                            <a href="javascript:void(0);" class="del-goods">&nbsp;</a>
                        </li>                
                    </ul>
                    <div class="text-right">
                        <a href="shop-shopping-cart.html" class="btn btn-default">Ver Carrito</a>
                        <a href="shop-checkout.html" class="btn btn-primary">Revisar</a>
                    </div>
                </div>
            </div>            
        </div>
        <!--END CART -->

        <!-- BEGIN NAVIGATION -->
        <div class="header-navigation">
            <ul>
                <li class="">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Acerca de  

                    </a>

                    <!-- END DROPDOWN MENU -->
                </li>
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Categorias

                    </a>
                    <ul class="dropdown-menu" style="overflow-y:scroll; height:400px; overflow-x: scroll; width: 60%; overflow: scroll;">
                        <li>
                            <div class="header-navigation-content">
                                <div class="row">
                                   
                                    <c:forEach var="categorias" items="${requestScope.listaCategorias}">
                                     <div class="col-md-4 header-navigation-col">
                                         <h4 ><a style="color: #757575;" href="public.do?operacion=vercategoria&idcat=${categorias.idCategoria}">${categorias.categoria}</a></h4>
                                        <ul>
                                            <sql:query var="ql" dataSource="jdbc/mysql">
                                                SELECT * FROM sub_categoria WHERE id_categoria=${categorias.idCategoria}
                                            </sql:query>
                                            <c:forEach var="subcat" items="${ql.rows}">
                                            <li><a href="#" >${subcat.subcategoria}</a></li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    </c:forEach>
                                
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
                <li><a type="button" data-toggle="modal" style="cursor: pointer;" data-target="#exampleModal">Logueate</a></li>




                <!-- BEGIN TOP SEARCH -->
                <li class="menu-search">
                    <span class="sep"></span>
                    <i class="fa fa-search search-btn"></i>
                    <div class="search-box">
                        <form action="${base}/public.do">
                            <input type="hidden" name="operacion" value="buscarProductos"/>
                            <div class="input-group">
                                <input type="text" placeholder="Buscar articulo" required="true" name="nombre" class="form-control" minlength="4">
                                <span class="input-group-btn">
                                    <button class="btn btn-primary" type="submit">Buscar</button>
                                </span>
                            </div>
                        </form
                    </div> 
                </li>
                <!-- END TOP SEARCH -->
            </ul>
        </div>
        <!-- END NAVIGATION -->
    </div>
</div>