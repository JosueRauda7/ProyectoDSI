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
    </head>
    <!-- Head END -->

    <body class="ecommerce" >
        <jsp:include page="/empresa/menuEmpresa.jsp"/>

        <div class="container" >
            <form role="form" action="${base}/empresas.do" method="POST" enctype="multipart/form-data">
                <div class=" panel panel-body col-md-7">
                    <div class=" panel-body text-center" >

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


                        <input type="hidden" name="operacion" value="insertar"/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="producto" >Nombre del producto</label>
                                    <input type="text" id="producto" name="producto" class="form-control" value="${producto.producto}">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="cantidad">Existencias</label>
                                    <input type="number" id="cantidad" name="cantidad" class="form-control" value="${producto.cantidad}">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="descripcion">Descripción</label>
                                    <textarea id="descripcion" name="descripcion" value="${producto.descripcion}"></textarea>
                                </div>
                            </div>

                        </div>

                        <div class="row">         
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="regular">Precio regular($)</label>
                                    <input type="number" step="0.01" id="regular" name="regular" class="form-control" value="${producto.precioRegular}">
                                </div>
                            </div>                            
                        </div>

                        <div class="text-center">                  
                            <button class="btn btn-primary" type="submit">Registrar</button>
                        </div>

                    </div>
                </div>

                <div class="panel panel-body col-md-4" style="margin-left: 5%;" >
                    <p style="text-align: center;">Categoría del producto</p>
                    <div class="form-group">
                        <select id="categoria" name="categoria" class="form-control">
                            <option value="0">Selecciona una categoría</option>
                            <c:forEach var="categoria" items="${requestScope.listaCategoria}">
                                <option value="${categoria.idCategoria}">${categoria.categoria}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <hr>
                    <div class="form-group">
                        <select id="subcategoria" name="subcategoria" class="form-control">
                            <option value="0">Seleccione una sub categoría</option>
                        </select>
                    </div>
                </div>

                <div class="panel panel-body col-md-4" style="margin-left: 5%;" >
                    <p style="text-align: center;">Imagen principal del producto</p>
                    <hr>
                    <div Class="form-group">
                        <div class="text-center">
                            <img id="img" height="30%" width="45%" />
                        </div>
                        <input data-language="es" type="file" name="imagen" id="imagen" class="form-control" width="50%" data-allowed-file-extensions='["jpg", "png"]'
                               value="${base}/images/${imagenes.urlimagen}/"/>
                    </div>
                </div>

                <div class="panel panel-body col-md-4" style="margin-left: 5%; float: left;" >
                    <p style="text-align: center;">Galería del producto</p>
                    <div id="imgbox1">
                        <div class="text-center">
                            <img id="img1" height="30%" width="45%" />
                        </div>                        
                    </div>
                    <div class="hidden" id="imgbox2">
                        <div class="text-center">
                            <img id="img2" height="30%" width="45%" />
                        </div>                        
                    </div>
                    <div class="hidden" id="imgbox3">
                        <div class="text-center">
                            <img id="img3" height="30%" width="45%" />
                        </div>                        
                    </div>
                    <div class="hidden" id="imgbox4">
                        <div class="text-center">
                            <img id="img4" height="30%" width="45%" />
                        </div>                        
                    </div>
                    <hr>
                    <a class="glyphicon glyphicon-plus-sign" title="Añadir fotos a galería" data-toggle="modal" href="#exampleModal"> Añadir fotos a galería</a>
                </div>

                <div class="modal fade" id="exampleModal" >
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h5 class="modal-title" id="exampleModalLabel">Galería de imágenes</h5>                                                                            
                            </div>
                            <div style="display: inline-block;">
                                <div id="imgboxM1">
                                    <div>
                                        <label for="imagen1">
                                            <img id="imgM1" style="cursor: pointer; max-width: 80%; max-height: 80%;" src="${base}/images/add-image.png" alt ="Click aquí para subir tu foto" title ="Click aquí para subir tu foto" />
                                        </label>
                                    </div>

                                    <input type="file" style="display: none;" name="imagen1" id="imagen1" class="form-control" data-allowed-file-extensions='["jpg", "png"]'
                                           value="${base}/images/${imagenes.urlimagen}"/>
                                </div>
                                <div class="hidden" id="imgboxM2">
                                    <div>
                                        <label for="imagen2">
                                            <img id="imgM2" style="cursor: pointer; max-width: 80%; max-height: 80%;" src="${base}/images/add-image.png" alt ="Click aquí para subir tu foto" title ="Click aquí para subir tu foto" />
                                        </label>
                                    </div>
                                    <input type="file" style="display: none;" name="imagen2" id="imagen2" class="form-control" data-allowed-file-extensions='["jpg", "png"]'
                                           value="${base}/images/${imagenes.urlimagen}"/>
                                </div>
                                <div class="hidden" id="imgboxM3">
                                    <div>
                                        <label for="imagen3">
                                            <img id="imgM3" style="cursor: pointer; max-width: 80%; max-height: 80%;" src="${base}/images/add-image.png" alt ="Click aquí para subir tu foto" title ="Click aquí para subir tu foto" />
                                        </label>
                                    </div>
                                    <input style="display: none;" type="file" name="imagen3" id="imagen3" class="form-control" data-allowed-file-extensions='["jpg", "png"]'
                                           value="${base}/images/${imagenes.urlimagen}"/>
                                </div>
                                <div class="hidden" id="imgboxM4">
                                    <div>
                                        <label for="imagen4">
                                            <img id="imgM4" style="cursor: pointer; max-width: 80%; max-height: 80%;" src="${base}/images/add-image.png" alt ="Click aquí para subir tu foto" title ="Click aquí para subir tu foto" />
                                        </label>
                                    </div>
                                    <input style="display: none;" type="file" name="imagen4" id="imagen4" class="form-control" data-allowed-file-extensions='["jpg", "png"]'
                                           value="${base}/images/${imagenes.urlimagen}"/>
                                </div> 
                            </div>
                        </div>
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
                    $('#imgboxM2').removeClass("hidden");
                }
                reader.readAsDataURL(file);

            }
            function mostrarImagen2(event) {
                var file = event.target.files[0];
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = document.getElementById('imgM2');
                    img.src = event.target.result;
                    $('#imgboxM3').removeClass("hidden");
                }
                reader.readAsDataURL(file);

            }
            function mostrarImagen3(event) {
                var file = event.target.files[0];
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = document.getElementById('imgM3');
                    img.src = event.target.result;
                    $('#imgboxM4').removeClass("hidden");
                }
                reader.readAsDataURL(file);

            }
            function mostrarImagen4(event) {
                var file = event.target.files[0];
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = document.getElementById('imgM4');
                    img.src = event.target.result;
                }
                reader.readAsDataURL(file);
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