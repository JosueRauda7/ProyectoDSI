

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
    </head>
    <!-- Head END -->

    <!-- Body BEGIN -->
    <body class="ecommerce">
        <!-- BEGIN STYLE CUSTOMIZER -->

        <!-- END BEGIN STYLE CUSTOMIZER --> 


        <jsp:include page="menuCliente.jsp"/>
        <!-- END SLIDER -->

        <div class="main" style="margin-bottom: 5%;">
            <div class="container">
                <!-- BEGIN SIDEBAR & CONTENT -->
                <div class="row margin-bottom-40">
                    <!-- BEGIN CONTENT -->
                    <div class="col-md-12 col-sm-12">
                        <h1>Lista de pedidos</h1>
                        <div class="goods-page">
                            <div class="goods-data clearfix">
                                <div class="table-wrapper-responsive" style="height: 400px; overflow-y: scroll;">
                                    <table >
                                        
                                        <tr>
                                            <th>Pedido</th>
                                            <th>Fecha pedido</th>
                                            <th>Hora pedido</th>
                                            <th>Estado pedido</th>
                                            <th>Total compra</th>
                                        </tr>
                                        <c:forEach var="pedidos" varStatus="loop" items="${requestScope.listaPedidos}">
                                            <c:if test="${pedidos.estadoCompra.idEstadoCompra != 1}"> 
                                            <tr>
                                                <td>${loop.index+1}</td>
                                                <td>${pedidos.fechaCompra}</td>                                                
                                                <td>${pedidos.horaCompra}</td>
                                                <td>${pedidos.estadoCompra.estado}</td>
                                                <td>$${pedidos.montoTotal}<a class="btn btn-primary" href="${base}/clientes.do?operacion=carritoPasado&idpedido=${pedidos.idPedido}" style="color:white;"><span class="glyphicon glyphicon-folder-open" style=" margin-right: 4%;"></span> Ir a detalle</a></td>
                                            </tr>
                                            </c:if>
                                        </c:forEach>
                                    </table>
                                </div>


                            </div>
                            <a href="${base}/clientes.do?operacion=publicIndex"><button class="btn btn-default" >Continuar comprando <i class="fa fa-shopping-cart"></i></button></a>  
                    </div>
                    <!-- END CONTENT -->
                </div>
                <!-- END SIDEBAR & CONTENT -->

                <!-- BEGIN SIMILAR PRODUCTS -->

                <!-- END SIMILAR PRODUCTS -->
            </div>
        </div>

        <!-- END CONTENT -->
        <!-- END SIDEBAR & CONTENT -->

        <!-- BEGIN FOOTER -->



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

                        location.href = 'clientes.do?operacion=eliminarArticulo&iddetalle=' + id + '&url=' + url;
                    }
                });
            }
            ;

            function eliminarOfer(id) {

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

                        location.href = 'clientes.do?operacion=eliminarOferta&iddetalle=' + id + '&url=' + url;
                    }
                });
            }
            ;

        </script>

    </body>
    <jsp:include page="footer.jsp" />
    <!-- END BODY -->
</html>