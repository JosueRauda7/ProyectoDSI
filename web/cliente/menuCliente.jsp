<!-- BEGIN HEADER -->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>

<div class="header">
    <div class="container">
        <a class="site-logo" href="${base}/clientes.do?operacion=publicIndex">BigShop</a>

        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>

        <!-- BEGIN CART -->
        <div class="top-cart-block">
            <div class="top-cart-info">
                <a href="javascript:void(0);" class="top-cart-info-count">3 items</a>
                <a href="javascript:void(0);" class="top-cart-info-value">$1260</a>
            </div>
            <i class="fa fa-shopping-cart"></i>

            <div class="top-cart-content-wrapper">
                <div class="top-cart-content">
                    <ul class="scroller" style="height: 250px; ">
                        <li >
                            <a href="shop-item.html"><img src="assets/pages/img/cart-img.jpg" alt="Rolex Classic Watch" width="37" height="34"></a>
                            <span class="cart-content-count">x 1</span>
                            <strong><a href="shop-item.html">Rolex Classic Watch</a></strong>
                            <em>$1230</em>
                            <a  style="margin-left: 90%;margin-top: -8%;" href="#" class="del-goods">&nbsp;</a>
                        </li>
                        <li>
                            <a href="shop-item.html"><img src="assets/pages/img/cart-img.jpg" alt="Rolex Classic Watch" width="37" height="34"></a>
                            <span class="cart-content-count">x 1</span>
                            <strong><a href="shop-item.html">Rolex Classic Watch</a></strong>
                            <em>$1230</em>
                            <a  style="margin-left: 90%;margin-top: -8%;"  href="javascript:void(0);" class="del-goods">&nbsp;</a>
                        </li>
                        <li>
                            <a href="shop-item.html"><img src="assets/pages/img/cart-img.jpg" alt="Rolex Classic Watch" width="37" height="34"></a>
                            <span class="cart-content-count">x 1</span>
                            <strong><a href="shop-item.html">Rolex Classic Watch</a></strong>
                            <em>$1230</em>
                            <a href="javascript:void(0);" class="del-goods">&nbsp;</a>
                        </li>
                        <li>
                            <a href="shop-item.html"><img src="assets/pages/img/cart-img.jpg" alt="Rolex Classic Watch" width="37" height="34"></a>
                            <span class="cart-content-count">x 1</span>
                            <strong><a href="shop-item.html">Rolex Classic Watch</a></strong>
                            <em>$1230</em>
                            <a href="javascript:void(0);" class="del-goods">&nbsp;</a>
                        </li>
                        <li>
                            <a href="shop-item.html"><img src="assets/pages/img/cart-img.jpg" alt="Rolex Classic Watch" width="37" height="34"></a>
                            <span class="cart-content-count">x 1</span>
                            <strong><a href="shop-item.html">Rolex Classic Watch</a></strong>
                            <em>$1230</em>
                            <a href="javascript:void(0);" class="del-goods">&nbsp;</a>
                        </li>
                        <li>
                            <a href="shop-item.html"><img src="assets/pages/img/cart-img.jpg" alt="Rolex Classic Watch" width="37" height="34"></a>
                            <span class="cart-content-count">x 1</span>
                            <strong><a href="shop-item.html">Rolex Classic Watch</a></strong>
                            <em>$1230</em>
                            <a href="javascript:void(0);" class="del-goods">&nbsp;</a>
                        </li>
                        <li>
                            <a href="shop-item.html"><img src="assets/pages/img/cart-img.jpg" alt="Rolex Classic Watch" width="37" height="34"></a>
                            <span class="cart-content-count">x 1</span>
                            <strong><a href="shop-item.html">Rolex Classic Watch</a></strong>
                            <em>$1230</em>
                            <a href="javascript:void(0);" class="del-goods">&nbsp;</a>
                        </li>
                        <li>
                            <a href="shop-item.html"><img src="assets/pages/img/cart-img.jpg" alt="Rolex Classic Watch" width="37" height="34"></a>
                            <span class="cart-content-count">x 1</span>
                            <strong><a href="shop-item.html">Rolex Classic Watch</a></strong>
                            <em>$1230</em>
                            <a href="javascript:void(0);" class="del-goods">&nbsp;</a>
                        </li>
                    </ul>
                    <div class="text-right" style="display: flex; margin-left: 2%;">
                        <a href="shop-shopping-cart.html" class="btn btn-default">Ver Carritos</a>

                        <a href="${base}/clientes.do?operacion=crearCarrito" id="crear" class="btn btn-primary" >Crear carrito</a>
                        <a href="${base}/clientes.do?operacion=crearCarrito" style="width: 30%;" id="ver" class="btn btn-primary" >Ver carrito</a>

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
                                            <h4 ><a style="color: #757575;" href="${base}/clientes.do?operacion=versubcategoria&idcat=${categorias.idCategoria}">${categorias.categoria}</a></h4>
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
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;"><span class="glyphicon glyphicon-user"></span> ${sessionScope.nombreUser}</a>

                    <ul class="dropdown-menu">
                        <li><a href="shop-index.html"><span class="glyphicon glyphicon-log-out"></span> Cerrar sesión</a></li>
                        <li><a href="shop-index-header-fix.html"> <span class="glyphicon glyphicon-lock"></span> Cambiar contraseña</a></li>
                    </ul>
                </li>



                <!-- BEGIN TOP SEARCH -->
                <li class="menu-search">
                    <span class="sep"></span>
                    <i class="fa fa-search search-btn"></i>
                    <div class="search-box">
                        <form action="${base}/clientes.do">
                            <input type="hidden" name="operacion" value="buscarProductos"/>
                            <div class="input-group">
                                <input type="text" placeholder="Buscar articulo" required="true" name="nombre" class="form-control" minlength="4">
                                <span class="input-group-btn">
                                    <button class="btn btn-primary" type="submit">Buscar</button>
                                </span>
                            </div>
                        </form>
                    </div> 
                </li>
                <!-- END TOP SEARCH -->
            </ul>
        </div>
        <!-- END NAVIGATION -->
    </div>
</div>
<script>
    <c:if test="${estado eq 1 }">
    document.getElementById("crear").style.display = "none";
    document.getElementById("ver").style.display = "block";
    </c:if>
    <c:if test="${estado eq 2 || estado eq 3 || estado eq 0}">
    document.getElementById("crear").style.display = "block";
    document.getElementById("ver").style.display = "none";
    </c:if>
</script>