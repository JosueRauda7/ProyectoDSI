<%-- 
    Document   : menuAdmin
    Created on : 19-sep-2018, 18:43:31
    Author     : Ferh
--%>

<!-- BEGIN HEADER -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<div class="header">
    <div class="container">
        <a class="site-logo" href="${base}/empresas.do?operacion=inicio"><img src="${base}/assets/logoMenuBigShop.png" /></a>       
        <!-- BEGIN CART -->
        <div class="top-cart-block">
            <div class="header-navigation">
                <ul>
                    <li class="btn dropdown dropdown-megamenu" >
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" data-target="#" aria-haspopup="true" aria-expanded="false">
                            ${sessionScope.nombreUser}
                        </button>
                        <ul class="dropdown-menu">                                                        
                            <li><a style="padding: 10px" href="#">Cambiar contraseña</a></li>
                            <li><a style="padding: 10px" href="${base}/usuarios.do?operacion=cerrarSesion">Salir</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <!-- BEGIN NAVIGATION -->
        <div class="header-navigation">
            <ul>
                <li class="dropdown">
                    <a  href="${base}/empresas.do?operacion=inicio">
                        Inicio 
                    </a>
                    <!-- BEGIN DROPDOWN MENU -->
                </li>                
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Productos
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/empresas.do?operacion=nuevo">Registrar producto</a></li>
                        <li><a href="${base}/empresas.do?operacion=listar&estado=1">Ver productos</a></li>
                    </ul>
                </li>                
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Estadisticas
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/empresas.do?operacion=grafica">Ventas anuales</a></li>                        
                    </ul>
                </li>
                <li class="dropdown dropdown-megamenu">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                        Ofertas
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${base}/empresas.do?operacion=agregarOfertas">Agregar ofertas</a></li>
                        <li><a href="${base}/empresas.do?operacion=listaOfertas">Ver ofertas</a></li>                         
                    </ul>
                </li>
                <li><a href="${base}/empresas.do?operacion=terminosLegales">Términos y condiciones</a></li>  
                <!-- END TOP SEARCH -->
            </ul>
        </div>

        <!-- END NAVIGATION -->
    </div>
</div>
<!-- Header END -->