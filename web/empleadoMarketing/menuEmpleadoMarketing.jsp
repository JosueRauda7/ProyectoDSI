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
        <a class="site-logo"  href="${base}/empleadoMarketing.do?operacion=inicio"><img src="${base}/assets/logoMenuBigShop.png" /></a>        
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
                    <a href="${pageContext.request.contextPath}/empleadoMarketing.do?operacion=inicio">
                        Inicio 
                    </a>
                    <!-- BEGIN DROPDOWN MENU -->
                </li>
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/empleadoMarketing.do?operacion=nuevoCorreos">
                        Nuevo Correo 
                    </a>
                    <!-- BEGIN DROPDOWN MENU -->
                </li>
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/empleadoMarketing.do?operacion=verListaOfertas">
                        Ofertas 
                    </a>
                    <!-- BEGIN DROPDOWN MENU -->
                </li>
                
                <!-- END TOP SEARCH -->
            </ul>
        </div>
        <!-- END NAVIGATION -->
    </div>
</div>
<!-- Header END -->
