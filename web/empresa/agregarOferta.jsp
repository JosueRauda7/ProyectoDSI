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
                        <input type="hidden" name="operacion" value="insertar"/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="producto" >Titulo oferta:</label>
                                    <input type="text" id="producto" name="producto" class="form-control" onclick="validarVacio();" onchange="validarVacio();" on value="${producto.producto}">
                                    <label id="Error" class="hidden" style="color:red; font-weight: normal;">El nombre del producto es requerido</label>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="cantidad">Fecha inicio oferta:</label>
                                    <input type="date" id="cantidad" name="cantidad" class="form-control" value="${producto.cantidad}">
                                    <label id="Error" class="hidden" style="color:red; font-weight: normal;">El nombre del producto es requerido</label>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="cantidad">Fecha fin oferta:</label>
                                    <input type="date" id="cantidad" name="cantidad" class="form-control" value="${producto.cantidad}">
                                    <label id="Error" class="hidden" style="color:red; font-weight: normal;">El nombre del producto es requerido</label>
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="descripcion">Descripción:</label>
                                    <textarea id="descripcion" name="descripcion" value="${producto.descripcion}">${producto.descripcion}</textarea>
                                </div>
                            </div>

                        </div>

                        <div class="row">         
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="regular">Descuento:</label>
                                    <input type="number" step="0.01" id="regular" name="regular" class="form-control" value="${producto.precioRegular}">
                                </div>
                            </div>                            
                        </div>

                        <div class="row">         
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="regular">Producto:</label>
                                    <select class="form-control">
                                        <c:forEach var="productos" items="${requestScope.productos}"> 
                                            <option>${productos.producto}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>                            
                        </div>
                        <div class="row">         
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="regular">Imagen oferta:</label>
                                    <input type="file" step="0.01" id="regular" name="regular" class="form-control" value="${producto.precioRegular}">
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

        <script>
            window.addEventListener('load', init, false);

            function init() {
                var inputFile = document.getElementById('imagen');
                var inputFile1 = document.getElementById('imagen1');
                var inputFile2 = document.getElementById('imagen2');
                var inputFile3 = document.getElementById('imagen3');
                var inputFile4 = document.getElementById('imagen4');
                inputFile.addEventListener('change', mostrarImagen, false);
                inputFile1.addEventListener('change', mostrarImagen1, false);
                inputFile2.addEventListener('change', mostrarImagen2, false);
                inputFile3.addEventListener('change', mostrarImagen3, false);
                inputFile4.addEventListener('change', mostrarImagen4, false);
            }

            function mostrarImagen(event) {
                var file = event.target.files[0];
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = document.getElementById('img');
                    img.src = event.target.result;
                }
                reader.readAsDataURL(file);
            }
            function mostrarImagen1(event) {
                var file = event.target.files[0];
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = document.getElementById('imgM1');
                    img.src = event.target.result;
                    document.getElementById('img1').src = event.target.result;
                    $('#imgboxM2').removeClass("hidden");
                    $('#imgM1r').removeClass("hidden");
                }
                reader.readAsDataURL(file);

            }
            function mostrarImagen2(event) {
                var file = event.target.files[0];
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = document.getElementById('imgM2');
                    img.src = event.target.result;
                    document.getElementById('img2').src = event.target.result;
                    $('#imgboxM3').removeClass("hidden");
                    $('#imgM2r').removeClass("hidden");
                }
                reader.readAsDataURL(file);

            }
            function mostrarImagen3(event) {
                var file = event.target.files[0];
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = document.getElementById('imgM3');
                    img.src = event.target.result;
                    document.getElementById('img3').src = event.target.result;
                    $('#imgboxM4').removeClass("hidden");
                    $('#imgM3r').removeClass("hidden");
                }
                reader.readAsDataURL(file);

            }
            function mostrarImagen4(event) {
                var file = event.target.files[0];
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = document.getElementById('imgM4');
                    img.src = event.target.result;
                    document.getElementById('img4').src = event.target.result;
                    $('#imgM4r').removeClass("hidden");
                }
                reader.readAsDataURL(file);
            }

            function quitarimagen(input, img, imgbox, img2, remove) {
                var inputs = document.getElementById(input);
                var imgs = document.getElementById(img);
                inputs.value = '';
                imgs.src = '${base}/images/add-image.png';
                document.getElementById(img2).src = '';
                $('#' + imgbox).addClass("hidden");
                $('#' + remove).addClass("hidden");
            }

            function cancelar() {
                var i;
                for (i = 1; i <= 4; i++) {
                    document.getElementById('imgM' + i).src = '${base}/images/add-image.png';
                    document.getElementById('imagen' + i).values = '';

                    $('#imgM' + i + 'r').addClass("hidden");
                    if (i > 1) {
                        $('#imgboxM' + i).addClass("hidden");
                    }
                }
            }

            function mostrar() {
                var i;
                for (i = 1; i <= 4; i++) {

                    $('#imgbox' + i).removeClass("hidden");

                }
            }
            function validarVacio() {

                if ($('#producto').val() == "") {
                    $('#Error').removeClass("hidden");
                }
                if ($('#producto').val() != "") {
                    $('#Error').addClass("hidden");
                }
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
            });

        </script>
    </body>
    <!-- END BODY -->
</html>
