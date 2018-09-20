<%-- 
    Document   : menuAdmin
    Created on : 19-sep-2018, 18:43:31
    Author     : Ferh
--%>

<!-- BEGIN HEADER -->
<div class="header">
    <div class="container">
        <a class="site-logo" href="shop-index.html">LOGO EMPRESA</a>

        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>

        <!-- BEGIN CART -->
        <div class="top-cart-block">

            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" data-target="#" aria-haspopup="true" aria-expanded="false">
                    Mi cuenta
                </button>
                <ul class="dropdown-menu">

                    <li><a style="padding: 10px" href="shop-product-list.html">Configurar</a></li>
                    <li><a style="padding: 10px" href="shop-product-list.html">Salir</a></li>

                </ul>
            </div>

        </div>


        <!-- BEGIN NAVIGATION -->
        <div class="header-navigation">
            <ul>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Inicio 

                    </a>

                    <!-- BEGIN DROPDOWN MENU -->

                    <!-- END DROPDOWN MENU -->
                </li>

                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">Clientes</a></li>
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Categorías

                    </a>
                    <ul class="dropdown-menu">

                        <li><a href="shop-product-list.html">Registrar categoría</a></li>
                        <li><a href="shop-product-list.html">Ver categorías</a></li>

                    </ul>
                </li>
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Productos

                    </a>
                    <ul class="dropdown-menu">

                        <li><a href="shop-product-list.html">Registrar productos</a></li>
                        <li><a href="shop-product-list.html">Ver productos</a></li>

                    </ul>
                </li>


                <!-- BEGIN TOP SEARCH -->
                <li class="menu-search">
                    <span class="sep"></span>
                    <i class="fa fa-search search-btn"></i>
                    <div class="search-box">
                        <form action="#">
                            <div class="input-group">
                                <input type="text" placeholder="Buscar" class="form-control">
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
<!-- Header END -->
