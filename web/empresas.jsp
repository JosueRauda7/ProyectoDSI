
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
    <!--<![endif]-->

    <!-- Head BEGIN -->
    <head>
        <title>Empresas</title>   
        <jsp:include page="/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
    </head>
    <!-- Head END -->

    <!-- Body BEGIN -->
    <body class="ecommerce">

        <jsp:include page="/menu.jsp"/>
        <jsp:include page="/modal.jsp"/>
        <div class="main">
            <div class="container">

                <!-- BEGIN SIDEBAR & CONTENT -->
                <!-- BEGIN SIMILAR PRODUCTS -->
                <div class="row" style="margin-top: 25px;">
                    <div class="col-md-12 col-sm-12">
                       
                        <div class="row product-list">
                            <center><h1>Empresas</h1></center>
                            <hr>
                            <c:forEach var="empresa" items="${requestScope.listaEmpresas}">
                                <div class="col-md-4 col-sm-6 col-xs-12" >
                                    <div class="product-item" style="height: 450px;">
                                        <div class="pi-img-wrapper">
                                            <img src="images/${empresa.urlEmpresa}"  style="height: 350px;" class="img-responsive" alt="Berry Lace Dress">
                                            <div>
                                                <a href="images/${empresa.urlEmpresa}" class="btn btn-default fancybox-button">Ver imagen</a>                               
                                            </div>
                                        </div>
                                                <h3 style="margin-top: 7%;" class="text-center"><a href="#">${empresa.empresa}</a></h3>
                                      
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- END SIMILAR PRODUCTS -->
                </div>

            </div>
        </div>
    </div>
    <!-- END CONTENT -->
    <!-- END SIDEBAR & CONTENT -->

   

    <script src="assets/pages/scripts/ModalLog.js" type="text/javascript"></script>

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
    <!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
    <jsp:include page="footer.jsp" />
<!-- END BODY -->
</html>