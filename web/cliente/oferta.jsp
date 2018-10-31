<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

    <!-- Head BEGIN -->
    <head>
        <title>Producto</title>     
        <link rel="stylesheet" href="assets/pages/css/w3.css">
        <jsp:include page="/head.jsp"/>     
        <link rel="stylesheet" href="${base}/assets/pages/css/responsivebiñeta.css">
        <jsp:include page="/scripts.jsp"/>
    </head>

    <body class="ecommerce" >

        <!-- BEGIN NAVIGATION -->
        <jsp:include page="menuCliente.jsp"/>
        <!-- END NAVIGATION -->
        <div class="container">

            <div class="product-page">
                <div class="row">

                    <div class="col-md-6 col-sm-6">
                        <div class="product-main-image">
                            <img src="images/${requestScope.oferta.producto.urlImagen}" alt="Cool green dress with red bell" class="img-responsive" data-BigImgsrc="images/${requestScope.oferta.producto.urlImagen}">
                        </div>

                    </div>
                    <div class="col-md-6 col-sm-6">
                        <h1>${requestScope.oferta.producto.producto}</h1>
                        <div class="price-availability-block clearfix">
                            <div class="price">
                                <em>Precio: </em> <strong><span>$</span>${requestScope.oferta.totalDescuento}</strong>
                                <em>$<span>${requestScope.oferta.producto.precioRegular}</span></em>
                            </div>
                            <div class="availability">
                                Descuento: <strong>${requestScope.oferta.descuento}%</strong>
                            </div>
                        </div>
                        <div class="description">
                            <p>${requestScope.oferta.producto.descripcion}</p>
                        </div>
                        <div class="product-page-options">
                            <div class="pull-left"  style="display: flex;">
                                <label class="control-label">Cantidad disponible:</label><p>${requestScope.oferta.producto.cantidad}</p>  
                            </div>
                            <form class="product-page-cart" action="clientes.do">
                                <input type="hidden" value="agregarOferta" name="operacion">
                                <input type="hidden" value="${requestScope.oferta.idOferta}" name="idoferta">
                                <input type="hidden" value="${requestScope.oferta.producto.idProducto}" name="idproducto">
                                <div class="product-quantity">
                                    <input id="product-quantity" type="text" value="1" name="cantidad" readonly class="form-control input-sm">
                                </div>
                                <button class="btn btn-primary" type="submit">Agregar al carrito</button>
                            </form>
                            <div class="marca" >
                                <label class="control-label">Empresa proveedora:</label><p>${requestScope.oferta.producto.empresa.empresa}</p>  
                                <a href="images/${requestScope.oferta.producto.empresa.urlEmpresa}" class="fancybox-button" rel="photos-lib"><img alt="Berry Lace Dress" src="images/${requestScope.oferta.producto.empresa.urlEmpresa}" style="width: 35%;"></a>
                            </div>
                            <div class="review" style="display: flex;">
                                <p>Subcategoria: </p><a href=""> ${requestScope.oferta.producto.subCategoria.subCategoria}</a> 

                            </div>

                            <ul class="social-icons">
                                <li><a class="facebook social-button" data-original-title="facebook"  href="https://www.facebook.com/sharer/sharer.php?u=http://localhost:8080/ProyectoDSI/clientes.do?operacion=verProducto&idproduct=8"  target="_blank" rel="nofollow"></a></li>
                                <li><a class="twitter social-button"  data-original-title="twitter" href="https://twitter.com/share?url=http://localhost:8080/ProyectoDSI/clientes.do?operacion=verProducto&idproduct=8&text=Hola" target="_blank" rel="nofollow"></a></li>
                                <li><a class="googleplus social-button"  data-original-title="googleplus" href="https://plus.google.com/share?text=localhost:8080/ProyectoDSI/clientes.do?operacion=verProducto&idproduct=7"  target="_blan" rel="nofollow"></a></li>
                                <li><a class="evernote social-button" data-original-title="evernote" href="javascript:;"></a></li>
                                <li><a class="tumblr social-button"  data-original-title="tumblr" href="javascript:;"></a></li>
                            </ul>

                        </div>

                    </div>
                    <div class="sticker sticker-sale"></div>
                    <div class="product-page-content">
                        <ul id="myTab" class="nav nav-tabs">
                            <li><a href="#Description" data-toggle="tab">Ofertas relacionadas</a></li>                           
                            <li class="active"><a href="#Reviews" data-toggle="tab">Comentarios</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade" id="Description">
                                <div class="contenofer">
                                    <c:forEach var="ofertas" items="${requestScope.listaOfertas}">
                                        <div class="oferta" style="background-image: url('images/${ofertas.urlFoto}');">
                                            <div class="oferinfo">
                                                <h4 class="text-center">${ofertas.titulo}</h4>
                                                <hr>
                                                <p>${ofertas.descripcion}</p>
                                                <button class="btn btn-primary">Ver oferta</button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>

                            <div class="tab-pane fade in active" id="Reviews">
                                <!--<p>There are no reviews for this product.</p>-->
                                <c:if test="${not empty requestScope.comentarios}">
                                    <c:forEach var="comentarios" items="${requestScope.comentarios}">
                                        <div class="review-item clearfix">
                                            <c:if test="${comentarios.usuario.idUsuario eq sessionScope.usuario}"> 

                                                <div class="w3-dropdown-click"  id="biñeta">
                                                    <button onclick="myFunction${comentarios.idComentario}()" class="btn btn-primary">
                                                        <span class="glyphicon glyphicon-option-vertical"></span>
                                                    </button>
                                                    <div id="Demo${comentarios.idComentario}"   class="w3-dropdown-content w3-bar-block w3-border options">
                                                        <a class="w3-bar-item w3-button" onclick="javascript:eliminar2('${comentarios.idComentario}')"><span class="glyphicon glyphicon-trash"></span> Eliminar</a>
                                                        <a id="btnmodificar${comentarios.idComentario}" class="w3-bar-item w3-button"><span class="glyphicon glyphicon-edit"></span> Modificar</a>
                                                        <a id="btnmocanceldificar${comentarios.idComentario}" style="display:none;" class="w3-bar-item w3-button"><span class="glyphicon glyphicon-edit"></span> Modificar</a>
                                                    </div>
                                                </div>
                                                <script>
                                                    function myFunction${comentarios.idComentario}() {
                                                        var x = document.getElementById("Demo${comentarios.idComentario}");
                                                        if (x.className.indexOf("w3-show") == -1) {
                                                            x.className += " w3-show";
                                                        } else {
                                                            x.className = x.className.replace(" w3-show", "");
                                                        }
                                                    }
                                                </script>
                                            </c:if>
                                            <div class="review-item-submitted">
                                                <strong>${comentarios.usuario.nombre}</strong>
                                                <em>${comentarios.fechaComentario} - ${comentarios.horaComentario}</em>

                                            </div>                                              
                                            <div class="review-item-content">
                                                <p id="texto${comentarios.idComentario}">${comentarios.comentario}</p>
                                                <input type="text" id="textonuevo${comentarios.idComentario}"  onchange="modificarco${comentarios.idComentario}(this.value);" class="form-control" value="${comentarios.comentario}" style="display:none;">

                                                <script>
                                                    document.getElementById("btnmodificar${comentarios.idComentario}").addEventListener("click", function () {
                                                        document.getElementById("texto${comentarios.idComentario}").style.display = "none";
                                                        document.getElementById("textonuevo${comentarios.idComentario}").style.display = "block";
                                                        document.getElementById("btnmodificar${comentarios.idComentario}").style.display = "none";
                                                        document.getElementById("btnmocanceldificar${comentarios.idComentario}").style.display = "block";
                                                    });
                                                    document.getElementById("btnmocanceldificar${comentarios.idComentario}").addEventListener("click", function () {
                                                        document.getElementById("texto${comentarios.idComentario}").style.display = "block";
                                                        document.getElementById("textonuevo${comentarios.idComentario}").style.display = "none";
                                                        document.getElementById("btnmodificar${comentarios.idComentario}").style.display = "block";
                                                        document.getElementById("btnmocanceldificar${comentarios.idComentario}").style.display = "none";

                                                        document.getElementById("textonuevo${comentarios.idComentario}").value = "${comentarios.comentario}";
                                                    });
                                                    function modificarco${comentarios.idComentario}(valor) {
                                                        location.href = 'clientes.do?operacion=modificarComentario&idcomentario=' + ${comentarios.idComentario} + '&producto=' +${requestScope.oferta.producto.idProducto} + "&comentario=" + valor;
                                                    }
                                                </script>
                                            </div>

                                        </div>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty requestScope.comentarios}">
                                    <p>No se han agregado comentarios de este producto, se el primero en hacerlo.</p>
                                </c:if>
                                <!-- BEGIN FORM-->
                                <form action="${base}/clientes.do" class="reviews-form" role="form">
                                    <h2>Escribe un comentario:</h2>
                                    <input type="hidden" name="operacion" value="agregarComentario">
                                    <input type="hidden" name="idproducto" value="${requestScope.oferta.producto.idProducto}">
                                    <div class="form-group">
                                        <label for="review">Comentario: <span class="require">*</span></label>
                                        <textarea class="form-control" name="comentario" rows="8" placeholder="Ingresa tu comentario" id="review"></textarea>
                                    </div>

                                    <div class="padding-top-20">                  
                                        <button type="submit" class="btn btn-primary">Enviar</button>
                                    </div>
                                </form>
                                <!-- END FORM--> 
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>


        <jsp:include page="/scripts.jsp"/>
        <script type="text/javascript">
            jQuery(document).ready(function () {
                Layout.init();
                Layout.initOWL();
                Layout.initTwitter();
                Layout.initImageZoom();
                Layout.initTouchspin();
                Layout.initUniform();
            });
            var URLactual = window.location;
            var lba = document.getElementsByClassName("social-button");

            function myPopup() {
                window.open(this.href, 'mywin',
                        'left=20,top=20,width=500,height=500,toolbar=1,resizable=0');
                event.preventDefault();
                return false;
            }

            for (var i = 0; i < lba.length; i++) {
                lba[i].addEventListener("click", myPopup, false);
            }
        </script>
        <!-- END PAGE LEVEL JAVASCRIPTS -->
    </body>

    <script>
        <c:if test="${not empty  exito}">
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

        function eliminar2(id) {

            swal({
                title: '¿Seguro que quieres eliminar este comentario?',
                text: "Una vez eliminado, no habra vuelta atras.",
                icon: 'warning',
                buttons: true,
                dangerMode: true,
            }).then((willDelete) => {
                if (willDelete) {

                    location.href = 'clientes.do?operacion=eliminarComentario&idcomentario=' + id + '&producto=' +${requestScope.oferta.producto.idProducto};
                }
            });
        }
        ;
    </script>
    <jsp:include page="footer.jsp" />

    <!-- END BODY -->
</html>