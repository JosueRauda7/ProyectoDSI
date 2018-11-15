

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html lang="en">
    <!--<![endif]-->

    <!-- Head BEGIN -->
    <head>
        <title>Carrito de compra</title>
        <jsp:include page="/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.debug.js" integrity="sha384-THVO/sM0mFD9h7dfSndI6TS0PgAGavwKvB5hAxRRvc0o9cPLohB0wb/PTA7LdUHs" crossorigin="anonymous"></script>
    </head>
    <!-- Head END -->

    <jsp:include page="menuCliente.jsp"/>
    <!-- Body BEGIN -->
    <body class="ecommerce">


        <div class="main" style="margin-bottom: 2%;">
            <div class="container">


                <div class="row margin-bottom-40">
                    <!-- BEGIN CONTENT -->
                    <div class="col-md-12 col-sm-12">
                        <h1>Pago de pedido</h1>
                        <!-- BEGIN CHECKOUT PAGE -->
                        <div class="panel-group checkout-page accordion scrollable" id="checkout-page">

                            <!-- BEGIN CHECKOUT -->
                            <div id="checkout" class="panel panel-default">
                                <div class="panel-heading">
                                    <h2 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#checkout-page" href="#checkout-content" class="accordion-toggle">
                                            Paso 1: Datos personales
                                        </a>
                                    </h2>
                                </div>
                                <div id="checkout-content" class="panel-collapse collapse in">
                                    <div class="panel-body row">
                                        <div class="col-md-6 col-sm-6">
                                            <h3>Tus datos personales</h3>
                                            <div class="form-group">
                                                <label for="firstname">Nombres <span class="require">*</span></label>
                                                <input type="text" id="firstname" value="${requestScope.clienteInfo.nombre}" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="lastname">Apellidos <span class="require">*</span></label>
                                                <input type="text" id="lastname" value="${requestScope.clienteInfo.apellido}" class="form-control">
                                            </div>

                                            <div class="form-group">
                                                <label for="telephone">Telefono <span class="require">*</span></label>
                                                <input type="text" id="telephone" value="${requestScope.clienteInfo.telefono}" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="telephone">Dirección <span class="require">*</span></label>
                                                <textarea class="form-control">${requestScope.clienteInfo.direccion}</textarea>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-sm-6">
                                            <h3>Tu cuenta</h3>
                                            <div class="form-group">
                                                <label for="company">Correo electrónico</label>
                                                <input type="text" id="company" value="${requestScope.clienteInfo.correo}" class="form-control">
                                            </div>

                                        </div>
                                        <hr>
                                        <div class="col-md-12">                      

                                            <button class="btn btn-primary  pull-right" type="submit" data-toggle="collapse" data-parent="#checkout-page" data-target="#payment-address-content" id="button-payment-address">Continue</button>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- END CHECKOUT -->

                            <!-- BEGIN PAYMENT ADDRESS -->
                            <div id="payment-address" class="panel panel-default">
                                <div class="panel-heading">
                                    <h2 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#checkout-page" href="#payment-address-content" class="accordion-toggle">
                                            Paso 2: verificar información y Detalles de entrega
                                        </a>
                                    </h2>
                                </div>
                                <div id="payment-address-content" class="panel-collapse collapse">
                                    <div class="panel-body row">
                                        <div class="col-md-6 col-sm-6">

                                            <div class="form-group">
                                                <label for="firstname">Nombres <span class="require">*</span></label>
                                                <p>${requestScope.clienteInfo.nombre}</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="lastname">Apellidos <span class="require">*</span></label>
                                                <p>${requestScope.clienteInfo.apellido} </p>
                                            </div>

                                            <div class="form-group">
                                                <label for="telephone">Telefono <span class="require">*</span></label>
                                                <p>${requestScope.clienteInfo.telefono}</p>
                                            </div>
                                            <div class="form-group">
                                                <label for="telephone">Dirección <span class="require">*</span></label>
                                                <p>${requestScope.clienteInfo.direccion}</p>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-sm-6">
                                            <h3>Tu cuenta</h3>
                                            <div class="form-group">
                                                <label for="company">Correo electrónico</label>
                                                <p>${requestScope.clienteInfo.correo} </p>
                                            </div>

                                        </div>
                                        <hr>
                                        <div class="col-md-12">                      

                                            <button class="btn btn-primary  pull-right" type="submit" data-toggle="collapse" data-parent="#checkout-page" data-target="#payment-method-content" id="button-payment-address">Continue</button>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- END PAYMENT ADDRESS -->


                            <!-- END SHIPPING METHOD -->

                            <!-- BEGIN PAYMENT METHOD -->
                            <div id="payment-method" class="panel panel-default">
                                <div class="panel-heading">
                                    <h2 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#checkout-page" href="#payment-method-content" class="accordion-toggle">
                                            Paso 3: Metodo de pago
                                        </a>
                                    </h2>
                                </div>
                                <div id="payment-method-content" class="panel-collapse collapse">
                                    <div class="panel-body row">
                                        <div class="col-md-12">
                                            <p>Seleccione el método de pago preferido para utilizar en este pedido.</p>
                                            <div class="radio-list">
                                                <form>

                                                    <input type="radio" id="metodo1" name="val" value="1" ><label for="metodo1">Contra reembolso</label>
                                                    <input type="radio" id="metodo2" name="val" value="2" ><label for="metodo2">Tarjeta de credito o debito</label>
                                                    <section id="entrega" style="display:none;">El pago sera efectuado con nuestro equipo de reparto.</section>
                                                    <section id="tarjeta"  style="display: none">
                                                        <div class="col-md-6 col-sm-6">

                                                            <div class="form-group">
                                                                <label for="firstname">Nombre segun la tarjeta <span class="require">*</span></label>
                                                                <input type="text" id="company" class="form-control">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="lastname">Dui <span class="require">*</span></label>
                                                                <input type="text" id="company" value="${requestScope.clienteInfo.dui}" class="form-control"> 
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="telephone">Tipo tarjeta <span class="require">*</span></label>
                                                                <select class="form-control input-sm" style="color:black;">
                                                                    <option value="#?limit=24" selected="selected">Visa</option>
                                                                    <option value="#?limit=25">Master Card</option>
                                                                    <option value="#?limit=50">American Express</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="telephone">Numero tarjeta <span class="require">*</span></label>
                                                                <input type="text" id="company"  class="form-control"> 
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 col-sm-6">

                                                            <div class="form-group">
                                                                <label for="company">Codigo verificacion</label>
                                                                <input type="text" id="company"  class="form-control">
                                                            </div>

                                                        </div>
                                                    </section>
                                                </form>
                                            </div>

                                            <button class="btn btn-primary  pull-right" type="submit" id="button-payment-method" data-toggle="collapse" data-parent="#checkout-page" data-target="#confirm-content">Continue</button>
                                            <div class="checkbox pull-right">
                                                <label>
                                                    <input type="checkbox">He leído y acepto los <a title="Terms & Conditions" href="javascript:;">Terminos & Condiciones </a> &nbsp;&nbsp;&nbsp; 
                                                </label>
                                            </div>  
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- END PAYMENT METHOD -->

                            <!-- BEGIN CONFIRM -->
                            <div id="confirm" class="panel panel-default">
                                <div class="panel-heading">
                                    <h2 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#checkout-page" href="#confirm-content" class="accordion-toggle">
                                            Paso 4: Confirmar pedido
                                        </a>
                                    </h2>
                                </div>
                                <div id="confirm-content" class="panel-collapse collapse">
                                    <div class="panel-body row">
                                        <div class="col-md-12 clearfix">
                                            <div class="table-wrapper-responsive" style="height: 400px; overflow-y: scroll;" >
                                                <table summary="Shopping cart">
                                                    <tr>
                                                        <td colspan="7"><h4 class="text-center">Productos</h4></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="goods-page-image">Imagen</th>
                                                        <th class="goods-page-description">Descripción</th>
                                                        <th class="goods-page-ref-no">Empresa</th>
                                                        <th class="goods-page-quantity">Cantidad</th>
                                                        <th class="goods-page-price">Precio unitario</th>
                                                        <th class="goods-page-total">Total</th>

                                                    </tr>
                                                    <c:if test="${not empty sessionScope.pedidosProduc}">
                                                        <c:forEach var="productos" varStatus="loop"  items="${sessionScope.pedidosProduc}" >
                                                            <tr>
                                                                <td class="goods-page-image">
                                                                    <a href="javascript:;"><img src="images/${productos.producto.urlImagen}" alt="${productos.producto.producto}"></a>
                                                                </td>
                                                                <td class="checkout-description" style="width: 35%;">
                                                                    <h3><a href="${base}/clientes.do?operacion=verProducto&idproduct=${productos.producto.idProducto}">${productos.producto.producto}</a></h3>
                                                                    <p><strong>Item ${loop.index +1}</strong> -${productos.producto.descripcion}</p>

                                                                </td>
                                                                <td class="goods-page-ref-no">
                                                                    ${productos.producto.empresa.empresa}
                                                                    <br>
                                                                    <a href="javascript:;"><img src="images/${productos.producto.empresa.urlEmpresa}" alt="${productos.producto.empresa.empresa}" width="100"></a>
                                                                </td>
                                                                <td class="goods-page-quantity">
                                                                    <div class="product-quantity">
                                                                        <p>${productos.cantidad}</p>

                                                                    </div>
                                                                </td>
                                                                <td class="goods-page-price">
                                                                    <strong><span>$</span>${productos.producto.precioRegular}</strong>
                                                                </td>
                                                                <td class="goods-page-total">
                                                                    <strong><span>$</span>${productos.producto.precioRegular * productos.cantidad}</strong>
                                                                </td>

                                                            </tr>
                                                        </c:forEach> 
                                                    </c:if>
                                                    <c:if test="${empty sessionScope.pedidosProduc}">
                                                        <tr>
                                                            <td colspan="7"><h4 class="text-center">No hay productos que mostrar</h4></td>
                                                        </tr>
                                                    </c:if>
                                                    <tr>
                                                        <td colspan="7"><h4 class="text-center">Ofertas</h4></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="goods-page-image">Imagen</th>
                                                        <th class="goods-page-description">Descripción</th>
                                                        <th class="goods-page-ref-no">Descuento</th>
                                                        <th class="goods-page-quantity">Cantidad</th>
                                                        <th class="goods-page-price">Precio unitario</th>
                                                        <th class="goods-page-total">Total</th>
                                                    </tr>
                                                    <c:if test="${not empty sessionScope.pedidosOfert}">
                                                        <c:forEach var="ofertas" varStatus="loop" items="${sessionScope.pedidosOfert}">
                                                            <tr>
                                                                <td class="goods-page-image">
                                                                    <a href="javascript:;"><img src="images/${ofertas.oferta.urlFoto}" alt="${ofertas.oferta.titulo}"></a>
                                                                </td>
                                                                <td class="checkout-description" style="width: 35%;">
                                                                    <h3><a href="${base}/clientes.do?operacion=verProducto&idproduct=${ofertas.oferta.producto.idProducto}">${ofertas.oferta.titulo}</a></h3>
                                                                    <p><strong>Item ${loop.index +1}</strong> -${ofertas.oferta.descripcion}</p>

                                                                </td>
                                                                <td class="goods-page-ref-no">
                                                                    <p>%${ofertas.oferta.descuento}</p>
                                                                </td>
                                                                <td class="goods-page-quantity">
                                                                    <div class="product-quantity">
                                                                        <p>${ofertas.cantidad}</p>

                                                                    </div>
                                                                </td>
                                                                <td class="goods-page-price">
                                                                    <strong><span>$</span>${ofertas.oferta.totalDescuento}</strong>
                                                                </td>
                                                                <td class="goods-page-total">
                                                                    <strong><span>$</span>${ofertas.oferta.totalDescuento * ofertas.cantidad}</strong>
                                                                </td>

                                                            </tr>
                                                        </c:forEach> 
                                                    </c:if>
                                                    <c:if test="${empty sessionScope.pedidosOfert}">
                                                        <tr>
                                                            <td colspan="7"><h4 class="text-center">No hay ofertas que mostrar</h4></td>
                                                        </tr>
                                                    </c:if>
                                                </table>
                                            </div>

                                               
                                            <div id="testdiv" style="display: none;" >
                                                <div>
                                                    <h1>Pedido:</h1>
                                                    <p>PED${requestScope.idPedido}</p>
                                                    <h1>Dirección:</h1>
                                                    <p>Avenida Albert Einstein 233, Antiguo Cuscatlán</p>
                                                    <h1>Cliente:</h1>
                                                    <p>${sessionScope.nombreUser}</p>
                                                    <h1>Telefono:</h1>
                                                    <p>2233-4455</p>
                                                    <h1>Fecha pedido</h1>
                                                    <p>${requestScope.fechaPedido}</p>
                                                </div>
                                                <h1>Productos</h1>
                                                <table style="font-size: 11px;">
                                                    <thead>
                                                        <tr>
                                                            <th>producto</th>
                                                            <th>Empresa</th>
                                                            <th>Cantidad</th>
                                                            <th>Precio unitario</th>
                                                            <th>Total</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="productos" varStatus="loop"  items="${sessionScope.pedidosProduc}" >
                                                            <tr>
                                                                <td>${productos.producto.producto}</td>
                                                                <td>${productos.producto.empresa.empresa}</td>
                                                                <td>${productos.cantidad}</td>
                                                                <td>${productos.producto.precioRegular} </td>
                                                                <td>${productos.producto.precioRegular * productos.cantidad}</td>
                                                            </tr>
                                                        </c:forEach> 
                                                    </tbody>
                                                </table>
                                                <h1>Ofertas</h1>
                                                <table style="font-size: 11px;">
                                                    <thead>
                                                        <tr>
                                                            <th>Oferta</th>
                                                            <th>Descuento</th>
                                                            <th>Cantidad</th>
                                                            <th>Precio unitario</th>
                                                            <th>Total</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="ofertas" varStatus="loop" items="${sessionScope.pedidosOfert}">
                                                            <tr>
                                                                <td>${ofertas.oferta.titulo}</td>
                                                                <td>%${ofertas.oferta.descuento}</td>
                                                                <td>${ofertas.cantidad}</td>
                                                                <td>$${ofertas.oferta.totalDescuento}</td>
                                                                <td>$${ofertas.oferta.totalDescuento * ofertas.cantidad}</td>
                                                                
                                                            </tr>
                                                        </c:forEach> 
                                                    </tbody>
                                                </table>

                                                <table>
                                                    <thead>
                                                        <tr>
                                                            <th>Sub total</th>
                                                            <th>Costo de envio</th>
                                                            <th>Total</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>$${sessionScope.totalPedido}</td>
                                                            <td>$<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${sessionScope.totalPedido*0.05}"/></td>
                                                            <td>$<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${(sessionScope.totalPedido*0.05) +sessionScope.totalPedido}"/></td>
                                                        </tr>
                                                    </tbody>
                                                </table>

                                            </div>
                                                       
                                            <div class="shopping-total">
                                                <ul>
                                                    <li>
                                                        <em>Sub total</em>
                                                        <strong class="price"><span>$</span>${sessionScope.totalPedido}</strong>
                                                    </li>
                                                    <li>
                                                        <em>Costo de envio</em>
                                                        <strong class="price"><span>$</span><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${sessionScope.totalPedido*0.05}"/></strong>
                                                    </li>
                                                    <li class="shopping-total-price">
                                                        <em>Total</em>
                                                        <strong class="price"><span>$</span><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${(sessionScope.totalPedido*0.05) +sessionScope.totalPedido}"/></strong>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="clearfix"></div>
                                            <a href="javascript:genPDF()">Download PDF</a>
                                            <a class="btn btn-primary pull-right" onclick="javascript:confirmar()" id="button-confirm" style="color:white;">Confirmar pedido</a>
                                            <a class="btn btn-default pull-right margin-right-20" onclick="javascript:eliminar()" style="color:black;">Cancelar pedido</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- END CONFIRM -->
                        </div>
                        <!-- END CHECKOUT PAGE -->
                    </div>
                    <!-- END CONTENT -->
                </div>
                <!-- END SIDEBAR & CONTENT -->
            </div>
        </div>

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


            document.getElementById("metodo1").addEventListener("change", function () {
                document.getElementById("tarjeta").style.display = "none";
                document.getElementById("entrega").style.display = "block";
            });
            document.getElementById("metodo2").addEventListener("change", function () {
                document.getElementById("tarjeta").style.display = "block";
                document.getElementById("entrega").style.display = "none";
            });

            function eliminar(id) {

                var URLactual = window.location;
                var url = URLactual.toString().substring(34);
                swal({
                    title: '¿Seguro que quieres eliminar este articulo del carrito?',
                    text: "Una vez eliminado, no habra vuelta atras.",
                    icon: 'warning',
                    buttons: true,
                    dangerMode: true,
                }).then((willDelete) => {
                    if (willDelete) {

                        location.href = '${base}/clientes.do?operacion=cancelarPedido';
                    }
                });
            }
            ;

            function confirmar() {

                swal({
                    title: '¿Estas seguro de confirmar este pedido?',
                    text: "Una vez confirmado, no habra vuelta atras.",
                    icon: 'warning',
                    buttons: true,
                    dangerMode: true,
                }).then((willDelete) => {
                    if (willDelete) {
                        document.getElementById("testdiv").style.display = "block";
                        var doc = new jsPDF('l', 'mm', [297, 210]);
                        var logo = new Image();
                        logo.src = '${base}/assets/logoMenuBigShop.png';
                        doc.addImage(logo, 'PNG', 15, 40, 148, 210);
                        var specialElementHandlers = {
                            '#hidediv': function (element, render) {
                                return true;
                            }
                        };

                        doc.fromHTML($('#testdiv').get(0), 20, 12, {
                            'width': 500,
                            'elementHandlers': specialElementHandlers
                        });


                        doc.save('Factura.pdf');
                        location.href = '${base}/clientes.do?operacion=confirmarPedido';
                    }
                });
            }
            ;

            function genPDF() {
                document.getElementById("testdiv").style.display = "block";
                var doc = new jsPDF();
                var logo = new Image();
                logo.src = '${base}/assets/logoMenuBigShop.png';
                doc.addImage(logo, 'PNG', 130, 30, 45, 15);
                var specialElementHandlers = {
                    '#hidediv': function (element, render) {
                        return true;
                    }
                };

                doc.fromHTML($('#testdiv').get(0), 25, 20, {
                    'width': 500,
                    'elementHandlers': specialElementHandlers
                });

               
                doc.save('Factura.pdf');
            }

        </script>

    </body>
    <jsp:include page="footer.jsp" />
    <!-- END BODY -->
</html>