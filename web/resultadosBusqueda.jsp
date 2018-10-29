<%-- 
    Document   : comprasCliente
    Created on : 18-sep-2018, 18:24:00
    Author     : Ferh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Productos</title>

        <jsp:include page="/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
        <!-- Theme styles END -->
    </head>
    <!-- Head END -->

    <body class="ecommerce">


        <jsp:include page="menu.jsp"/>
        <jsp:include page="/modal.jsp"/>

        <div class="container">

            <br/>
            <h1>Resultados de la busqueda: ${requestScope.datoBusqueda}</h1>
            <br/>

            <c:if test="${empty requestScope.listarProductos}">
                <h2>No se ha encontrado ningún producto</h2><br/>
            </c:if>

            <c:if test="${not empty requestScope.listarProductos}">
                <c:forEach var="ultpro" items="${requestScope.listarProductos}">
                    <div class="col-md-4 col-sm-6 col-xs-12" >
                        <div class="product-item" style="height: 450px;">
                            <div class="pi-img-wrapper">
                                <img src="images/${ultpro.urlImagen}"  style="height: 350px;" class="img-responsive" alt="Berry Lace Dress">
                                <div>
                                    <a href="images/${ultpro.urlImagen}" class="btn btn-default fancybox-button">Ver imagen</a>                               
                                </div>
                            </div>
                            <h3 class="text-center"><a href="${base}/public.do?operacion=verProducto&idproduct=${ultpro.idProducto}">${ultpro.producto}</a></h3>
                            <div class="pi-price">Precio: $${ultpro.precioRegular}</div>

                        </div>
                    </div>
                </c:forEach>
            </c:if>
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