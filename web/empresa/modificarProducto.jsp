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
                        <input type="hidden" name="operacion" value="reenviar"/>
                        <input type="hidden" name="id" value="${id}">/
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="producto">Nombre del producto</label>
                                    <input type="text" id="producto" name="producto" class="form-control" value="${producto.producto}">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="cantidad">Cantidad</label>
                                    <input type="number" id="cantidad" name="cantidad" class="form-control" value="${producto.cantidad}">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="descripcion">Descripción</label>
                                    <textarea id="descripcion" name="descripcion" class="form-control" value="${producto.descripcion}"></textarea>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="regular">Precio regular</label>
                                    <input type="number" step="0.01" id="regular" name="regular" class="form-control" value="${producto.precioRegular}">
                                </div>
                            </div>
                        </div>

                        <div class="row">                            
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="subcategoria">Sub categoría</label>
                                    <select id="subcategoria" name="subcategoria" class="form-control">
                                        <c:forEach var="subcategoria" items="${requestScope.listaSubcategoria}">
                                            <option value="${subcategoria.idSubCategoria}">${subcategoria.subCategoria}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="imagen">Imagen</label>
                                    <input data-language="es" type="file" name="archivo" id="imagen" class="form-control file file-loading" data-allowed-file-extensions='["jpg", "png"]'
                                           value="${base}/images/${producto.urlImagen}/"/>
                                </div>
                            </div>
                        </div>

                        <div class="text-center" style="padding-bottom: 2%; ">                  
                            <button class="btn btn-primary" type="submit">Reenviar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>       
    </body>
    <!-- END BODY -->
</html>