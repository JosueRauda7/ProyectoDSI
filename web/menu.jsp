<!-- BEGIN HEADER -->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<div class="header" style="margin-bottom: 0;">
    <div class="container">
        <a class="site-logo" href="public.do?operacion=publicIndex"><img src="assets/logoMenuBigShop.png" /></a>

        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>

        <!-- BEGIN CART -->
        <div class="top-cart-block">
            <a href="#">Términos y condiciones</a>

        </div>
        <!--END CART -->

        <!-- BEGIN NAVIGATION -->
        <div class="header-navigation">
            <ul>
                <li class="">
                    <a class="dropdown-toggle" href="${base}/public.do?operacion=acercaDe">
                        Acerca de  

                    </a>

                    <!-- END DROPDOWN MENU -->
                </li>
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Categorías

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
                                                    <li><a href="${base}/public.do?operacion=listaProductos&idsubcat=${subcat.id_sub_categoria}" >${subcat.subcategoria}</a></li>
                                                    </c:forEach>
                                            </ul>
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
                <li><a class="dropdown-toggle" style="cursor: pointer;" href="public.do?operacion=verEmpresas">Empresas</a></li>
                <li><a class="dropdown-toggle" data-toggle="modal" style="cursor: pointer;" data-target="#exampleModal">Logueate</a></li>




                <!-- BEGIN TOP SEARCH -->
                <li class="menu-search">
                    <span class="sep"></span>
                    <i class="fa fa-search search-btn"></i>
                    <div class="search-box" style="width:300px;">
                        <form action="${base}/public.do">
                            <input type="hidden" name="operacion" value="buscarProductos"/>


                            <div class="input-group" style="width: 100%;">
                                <select name="categoria" class="form-control" style="font-size: 16px;">
                                    <option value="0">Todas las categorias</option>
                                    <c:forEach var="categoria" items="${requestScope.listaCategorias}">
                                        <option value="${categoria.idCategoria}">${categoria.categoria}</option>
                                    </c:forEach>
                                </select>
                            </div><br/>

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