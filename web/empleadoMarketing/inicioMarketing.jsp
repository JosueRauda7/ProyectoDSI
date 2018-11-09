<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>BigShop | Marketing</title>
        <jsp:include page="/empleadoMarketing/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
    </head>
    <body class="ecommerce">
        <jsp:include page="/empleadoMarketing/menuEmpleadoMarketing.jsp"/>
        <div class="main" style="margin-top: 20px;">
            <div class="container">
                <div class="row product-list col-lg-12">
                    <center><h1>Bienvenido </h1></center>
                    <hr/><br/>
                    <!-- PRODUCT ITEM START -->
                    <div class="col-lg-4">
                        <div class="product-item">
                            <div class="text-center">
                                <img src="${base}/images/correo.png" style="min-height: 160px; border-radius: 150px;" class="col-lg-11" alt="Berry Lace Dress">
                            </div><br>
                            <h3 class="text-center"><a href="${base}/empleadoMarketing.do?operacion=nuevoCorreos">Correo</a></h3>
                            <div class="text-center">
                                <a href="${base}/empleadoMarketing.do?operacion=nuevoCorreos" class="btn btn-default" 
                                   style="color: #a8aeb3;border: 1px #ededed solid; padding: 3px 6px;">Administrar</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="product-item">
                            <div class="text-center">
                                <img src="${base}/images/ofertaicono.png" style="min-height: 160px; border-radius: 150px;" class="col-lg-11" alt="Berry Lace Dress">
                            </div>
                            <h3 class="text-center"><a href="${base}/empleadoMarketing.do?operacion=verListaOfertas">Ofertas</a></h3>
                            <div class="text-center">
                                <a href="${base}/empleadoMarketing.do?operacion=verListaOfertas" class="btn btn-default"
                                   style="color: #a8aeb3;border: 1px #ededed solid; padding: 3px 6px;">Administrar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="margin-bottom-40" style="margin-top: 150px;"></div>
        <jsp:include page="/empleadoMarketing/footer.jsp"/>
    </body>
</html>
