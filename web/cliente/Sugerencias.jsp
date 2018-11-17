<%-- 
    Document   : Sugerencias
    Created on : 16-nov-2018, 18:22:15
    Author     : Ferh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sugerencias</title>
        <jsp:include page="/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
    </head>
    <body class="ecommerce">
        
        <jsp:include page="menuCliente.jsp"/>
        
        <div class="main" style="margin-top: 40px;">
      <div class="container">
        
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
          <!-- BEGIN SIDEBAR -->
          <div class="sidebar col-md-3 col-sm-3">
            

            <h2>Nuestros contactos</h2>
            <address>
              Avenida Albert Einstein 233,<br>Antiguo Cuscatlán<br>
              <abbr title="Teléfono">Tel:</abbr>2233-4455<br>
            </address>
            <address>
              <strong>Correo</strong><br>
              <a href="mailto:becas.fantel.sa.sv@gmail.com">becas.fantel.sa.sv@gmail.com</a><br>
              
            </address>
            
          </div>
          <!-- END SIDEBAR -->

          <!-- BEGIN CONTENT -->
          <div class="col-md-9 col-sm-9">
            <h1>Sugerencias</h1>
            
            <div class="content-page">
                

              <h2>Ingresa una sugerencia</h2>
              <hr/>
              <p>Al ingresar una sugerencia se informará a los administradores de nuestro sitio, quienes la tomaran en cuenta.</p>
              
              <!-- BEGIN FORM-->
              <form action="${pageContext.request.contextPath}/clientes.do" class="default-form" role="form">
                 <input type="hidden" name="operacion" value="agregarSugerencia"/>
                <div class="form-group">
                  <label for="Asunto">Asunto <span class="require">*</span></label>
                  <input type="text" class="form-control" id="asunto" name="asunto" required="true">
                </div>
                <div class="form-group">
                  <label for="Descripción">Descripción <span class="require">*</span></label>
                  <textarea class="form-control" rows="8" id="descripcion" name="descripcion" required="true"></textarea>
                </div>
                <div class="padding-top-20">                  
                  <button type="submit" class="btn btn-primary">Enviar</button>
                </div>
              </form>
              <!-- END FORM-->          
            </div>
          </div>
          <!-- END CONTENT -->
        </div>
        <!-- END SIDEBAR & CONTENT -->
      </div>
    </div>

    <!-- BEGIN BRANDS -->
    <div class="brands">
      <div class="container">
            <div class="owl-carousel owl-carousel6-brands">
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/canon.jpg" alt="canon" title="canon"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/esprit.jpg" alt="esprit" title="esprit"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/gap.jpg" alt="gap" title="gap"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/next.jpg" alt="next" title="next"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/puma.jpg" alt="puma" title="puma"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/zara.jpg" alt="zara" title="zara"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/canon.jpg" alt="canon" title="canon"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/esprit.jpg" alt="esprit" title="esprit"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/gap.jpg" alt="gap" title="gap"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/next.jpg" alt="next" title="next"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/puma.jpg" alt="puma" title="puma"></a>
              <a href="shop-product-list.html"><img src="assets/pages/img/brands/zara.jpg" alt="zara" title="zara"></a>
            </div>
        </div>
    </div>
    <!-- END BRANDS -->
    
    <script>
            <c:if test="${not empty exito}">

            swal({
                title: "Felicidades!",
                text: "${exito}",
                icon: "success",
            });
                <c:set var="exito" value="" scope="session"/>
            </c:if>

            <c:if test="${not empty fracaso}">
            swal({
                title: "Ups!",
                text: "${fracaso}",
                icon: "error",
            });
                <c:set var="fracaso" value="" scope="session"/>
            </c:if>
    </script>
    
<div class="margin-bottom-40" style="margin-top: 150px;"></div>
    <jsp:include page="footer.jsp" />

        
    </body>
</html>
