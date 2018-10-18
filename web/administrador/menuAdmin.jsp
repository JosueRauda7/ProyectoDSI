<%-- 
    Document   : menuAdmin
    Created on : 19-sep-2018, 18:43:31
    Author     : Ferh
--%>

<!-- BEGIN HEADER -->
<div class="header">
    <div class="container">
        <a class="site-logo" href="shop-index.html">BigShop</a>
        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>
        <!-- BEGIN CART -->
        <div class="top-cart-block">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" data-target="#" aria-haspopup="true" aria-expanded="false">
                    Mi cuenta
                </button>
                <ul class="dropdown-menu">
                    <li><a style="padding: 10px" href="shop-product-list.html">Modo Cliente</a></li>
                    <li><a style="padding: 10px" href="shop-product-list.html">Mis Compras</a></li>
                    <li><a style="padding: 10px" href="shop-product-list.html">Configurar</a></li>
                    <li><a style="padding: 10px" href="shop-product-list.html">Salir</a></li>
                </ul>
            </div>
        </div>
        <!-- BEGIN NAVIGATION -->
        <div class="header-navigation">
            <ul>
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/administrador.do?operacion=inicio">
                        Inicio 
                    </a>
                    <!-- BEGIN DROPDOWN MENU -->
                </li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">Clientes</a></li>
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Categorķas
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=nuevaCategoria">Registrar categorķa</a></li>
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=listarCategorias">Ver categorķas</a></li>
                    </ul>
                </li>
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Sub Categorķas
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=nuevaSubCategoria">Registrar sub categorķa</a></li>
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=listarSubCategorias">Ver sub categorķas</a></li>
                    </ul>
                </li>

                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Productos
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Registrar productos</a></li>
                        <li><a href="#">Ver productos</a></li>
                    </ul>
                </li>
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Empresas
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=nuevaEmpresa">Registrar empresa</a></li>
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=listarEmpresas">Ver empresas</a></li>
                    </ul>
                </li>
                <!-- END TOP SEARCH -->
            </ul>
        </div>
        <!-- END NAVIGATION -->
    </div>
</div>
<!-- Header END -->
