<%-- 
    Document   : comprasCliente
    Created on : 18-sep-2018, 18:24:00
    Author     : Ferh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Empresa</title>

        <jsp:include page="head.jsp"/>

    </head>
    <!-- Head END -->

    <body class="ecommerce">


        <jsp:include page="/empresa/menuEmpresa.jsp"/>


        <div class="container">
            <div class="col-lg-2"></div>
            <div class="content-page col-lg-8 margin-top-10">
                <div class="panel">
                    
                    <c:if test="${not empty requestScope.listaErrores}">
                        <div class="alert alert-danger">
                            <ul>
                                <c:forEach var="error" items="${requestScope.listaErrores}">
                                    <li>${error}</li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    <h1>Nuevo Producto</h1><br/>

                    <form role="form" action="${base}/empresas.do" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="operacion" value="insertar"/>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nombre">Nombre del producto</label>
                                    <input type="text" id="nombre" name="nombre" class="form-control" value="${producto.producto}">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="vencimiento">Cantidad</label>
                                    <input type="number" id="cantidad" name="cantidad" class="form-control" value="${producto.cantidad}">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="descripcion">Descripcion</label>
                                    <textarea id="descripcion" name="descripcion" class="form-control"></textarea>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="regular">Precio regular</label>
                                    <input type="number" step="0.01" id="regular" name="regular" class="form-control" value="${producto}">
                                </div>
                            </div>
                        </div>

                        <div class="row">                            
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="subcategoria">Sub categoria</label>
                                    <select id="subcategoria" class="form-control">
                                        <option>Sofá</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="editor">Descripcion</label>
                                    <textarea id="descripcion" name="editor"></textarea>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="imagen">Imagen</label>
                                    <input data-language="es" type="file" name="archivo" id="imagen" class="form-control file file-loading" data-allowed-file-extensions='["jpg", "png"]'
                                       value="${base}/img/productos/"/>
                                </div>
                            </div>
                            <div class="text-center" style="padding-bottom: 2%; ">                  
                                <button class="btn btn-primary" type="submit">Registrar</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="../footer.jsp"/>





        <!-- Load javascripts at bottom, this will reduce page load time -->
        <!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
        <!--[if lt IE 9]>
        <script src="assets/plugins/respond.min.js"></script>  
        <![endif]-->  
        <script src="../assets/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="../assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
        <script src="../assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>      

        <script src="../assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->

        <!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
        <script src="../assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script><!-- pop up -->
        <script src="../assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script><!-- slider for products -->
        <script src='../assets/plugins/zoom/jquery.zoom.min.js' type="text/javascript"></script><!-- product zoom -->
        <script src="../assets/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script><!-- Quantity -->
        <script src="../assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
        <script src="../assets/plugins/rateit/src/jquery.rateit.js" type="text/javascript"></script>

        <script src="../assets/corporate/scripts/layout.js" type="text/javascript"></script>
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
    <!-- END BODY -->
</html>