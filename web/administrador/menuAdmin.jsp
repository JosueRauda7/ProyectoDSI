<%-- 
    Document   : menuAdmin
    Created on : 19-sep-2018, 18:43:31
    Author     : Ferh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 

<div class="header">
    <div class="container">
        <a class="site-logo"  href="${base}/administrador.do?operacion=inicio"><img src="${base}/assets/logoMenuBigShop.png" /></a>        
        <!-- BEGIN CART -->
        <div class="top-cart-block">
                <ul>
                    <li class="btn dropdown dropdown-megamenu" >
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" data-target="#" aria-haspopup="true" aria-expanded="false">
                            <span class="glyphicon glyphicon-user"></span> ${sessionScope.nombreUser}
                        </button>
                        <ul class="dropdown-menu">
                            <li><a style="padding: 10px" href="${base}/usuarios.do?operacion=cerrarSesion">Salir</a></li>
                        </ul>
                    </li>
                </ul>
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
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Usuarios
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=agregarUsuario">Registrar Usuario</a></li>
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=listarUsuarios">Ver Usuarios</a></li>
                    </ul>
                </li>
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Categorías
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=nuevaCategoria">Registrar categoría</a></li>
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=listarCategorias">Ver categorías</a></li>
                    </ul>
                </li>
                

                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Productos
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/administrador.do?operacion=listarProductos&estado=1">Ver productos</a></li>
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
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Estadisticas
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/administrador.do?operacion=grafica">Ventas anuales</a></li>                        
                    </ul>
                </li>
                
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Otros
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/administrador.do?operacion=listarSugerencias">Sugerencias</a></li>                        
                    </ul>
                </li>
                <!-- END TOP SEARCH -->
            </ul>
        </div>
        <!-- END NAVIGATION -->
    </div>
</div>
<!-- Header END -->
