<%-- 
    Document   : comprasCliente
    Created on : 18-sep-2018, 18:24:00
    Author     : Ferh
--%>
<%@taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Empresa</title>

        <jsp:include page="head.jsp"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
        <style>
            .remove{
                position:absolute;
                top:0px;
                opacity:1;
                transition:opacity 0.5s linear;
                -webkit-transition:opacity 0.5s linear;
                cursor:pointer;
                border:0px;
                width:32px;
                height:32px
            }
        </style>
    </head>
    <!-- Head END -->

    <body class="ecommerce" >
        <jsp:include page="/empresa/menuEmpresa.jsp"/>

        <div class="container" >
            <h1 class="text-center">Agregar oferta</h1><br/>
            <c:if test="${not empty requestScope.listaErrores}">
                <div class="alert alert-danger">
                    <ul>
                        <c:forEach var="error" items="${requestScope.listaErrores}">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </div>
            </c:if>
            <form role="form" action="${base}/empresas.do" method="POST" enctype="multipart/form-data">
                <div class=" panel panel-body col-md-12">
                    <div class=" panel-body text-center" >                                                
                        <input type="hidden" name="operacion" value="insertarOferta"/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="titulo" >Titulo oferta:</label>
                                    <input type="text" id="titulo" name="titulo" class="form-control" value="${oferta.titulo}">

                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="fechaInicio">Fecha inicio oferta:</label>
                                    <input type="date" id="fechaInicio" name="fechaInicio" class="form-control" value="${oferta.fechaInicio}">
                                    <label id="Error" class="hidden" style="color:red; font-weight: normal;">El nombre del producto es requerido</label>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="fechaFin">Fecha fin oferta:</label>
                                    <input type="date" id="fechaFin" name="fechaFin" class="form-control" value="${oferta.fechaFin}">
                                    <label id="Error" class="hidden" style="color:red; font-weight: normal;">El nombre del producto es requerido</label>
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="descripcion">Descripción:</label>
                                    <textarea id="descripcion" name="descripcion" value="${oferta.descripcion}">${oferta.descripcion}</textarea>
                                </div>
                            </div>

                        </div>

                        <div class="row">         
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="descuento">Descuento:</label>
                                    <input type="number" step="1" min="1" max="60" id="descuento" name="descuento" class="form-control" value="${oferta.descuento}">
                                </div>
                            </div>                            
                        </div>

                        <div class="row">         
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="regular">Producto:</label>
                                    <select class="form-control productosSelect" id="productosSelect" name="productosSelect">
                                        <c:forEach var="productos" items="${requestScope.productos}"> 
                                            <option value="${productos.idProducto}">${productos.producto}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>                            
                        </div>
                        <div class="row">         
                            <div class="col-md-12">
                                <div class="form-group col-md-6 col-sm-6">
                                    <label for="imagen">Imagen oferta:</label>
                                    <input type="file" id="imagen" name="imagen" class="form-control" value="${oferta.urlFoto}"  onchange="eliminarImagen();">
                                </div>
                                <div class="col-sm-6 col-md-6 hidden" id="imgBox">
                                    <img id="img" height="20%" width="45%" />
                                </div>
                            </div>                            
                        </div>
                        <button type="submit" class="btn btn-primary">Agregar</button>
                    </div>
                </div>


            </form>
        </div>





        <ckeditor:replace replace="descripcion" basePath="${base}/ckeditor/" />

        <div class="footer"  style="width: 100%;-webkit-box-sizing:border-box;
             -moz-box-sizing:border-box;
             box-sizing:border-box; position: absolute; bottom: auto;" >
            <div class="container">
                <div class="row">
                    <!-- BEGIN COPYRIGHT -->
                    <div class="col-md-12 text-center">
                        2018 © Universidad Don Bosco. Derechos reservados. 
                    </div>
                </div>
            </div>
        </div>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js">
        </script>
        <script>
             window.addEventListener('load', init, false);

            function init() {
                var inputFile = document.getElementById('imagen');
                inputFile.addEventListener('change', mostrarImagen, false);
            }

            function mostrarImagen(event) {
                var file = event.target.files[0];
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = document.getElementById('img');
                    img.src = event.target.result;
                    $('#imgBox').removeClass("hidden");
                }
                reader.readAsDataURL(file);
            }

            function eliminarImagen() {
                $('#imgBox').addClass("hidden");
            }
           

            $(document).ready(function () {
                $('select[name=categoria]').on('change', function () {
                    $.ajax({
                        type: "GET",
                        url: 'empresas.do?operacion=subcategorias',
                        data: 'codigo=' + $('select[name=categoria]').val(),
                        statusCode: {
                            404: function () {
                                alert('Pagina no encontrada');
                            },
                            500: function () {
                                alert('Error servidor');
                            }
                        },
                        success: function (dados) {
                            $('select[name=subcategoria] option[name=sub]').remove();
                            var datos = dados.split(":");
                            for (var i = 0; i < datos.length - 1; i++) {
                                var codigoSubcategoria = datos[i].split("-")[0];
                                var nombreSubcategoria = datos[i].split("-")[1];
                                $('select[name=subcategoria]').append('<option name="sub" value="' + codigoSubcategoria + '">' + nombreSubcategoria + '</option>')
                            }
                        }
                    });
                })

                $('#productosSelect').select2({
                    language: "es"
                });
            });

        </script>
    </body>
    <!-- END BODY -->
</html>
