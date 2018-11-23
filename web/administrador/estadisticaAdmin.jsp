<%-- 
    Document   : estadisticaAdmin
    Created on : Nov 8, 2018, 10:36:54 PM
    Author     : ivanm
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<c:set var="gananciasT" value="0" scope="page" />
<c:set var="gananciasv" value="0" scope="page" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="head.jsp"/>   
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    </head>
    <body>
    <body class="ecommerce">
        <jsp:include page="menuAdmin.jsp"/>
        <div class="container">
            <input type="hidden" id="ventas" value="${ventas}" />
            <div id="grafica" style="width: 100%; height: 500px;">

            </div>
            <div style="margin-top: 3%;">
                <form method="POST" action="${base}/administrador.do" role="form">
                    <input type="hidden" name="operacion" value="grafica" />
                    <div class="col-md-2 text-center">
                        Selecciona un año
                    </div>
                    <div class="col-md-3">                                    
                        <select class="form-control" id="anio" name="anio">
                            <option value="2018">2018</option>
                            <option value="2017">2017</option>
                            <option value="2017">2016</option>
                            <option value="2017">2015</option>
                            <option value="2017">2014</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <input type="submit" value="Actualizar gráfica" class="btn btn-primary"/>
                    </div>
                </form>
            </div><br>
            <div class="table-wrapper-responsive col-md-12" style="margin-top: 5%; margin-bottom: 2%;">
                <p class="text-center" style="font-size: 15px; font-weight: bold;">Ventas de hoy</p>
                <div class="tab-pane fade in active" id="activos">
                    <table class="table table-hover" >
                        <thead style="border-top-color: #e6400c; border: 2px;">
                            <tr>
                                <th class="goods-page-ref-no text-center">Producto</th>
                                <th class="goods-page-quantity text-center">Cantidad vendida</th>
                                <th class="goods-page-description text-center">Total vendido</th>                                            
                                <th class="goods-page-ref-no text-center">Ganancias obtenidas</th>                                                                                                                   
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${requestScope.ventasHoy}" var="venta">
                                <tr>
                                    <td class="text-center">${venta.producto}</td>                                      
                                    <td class="text-center">${venta.cantidad}</td>
                                    <td class="text-center">$${venta.totalVendido}</td>
                                    <td class="text-center">$${venta.totalGanancias}</td>
                                    <c:set var="gananciasT" value="${gananciasT + venta.totalGanancias}" scope="page"/>
                                    <c:set var="gananciasV" value="${gananciasV + venta.totalVendido}" scope="page"/>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td class="text-center">Ventas del día: $<c:out value="${gananciasV}" /></td>
                                <td class="text-center">Ganancia diaria: $<c:out value="${gananciasT}" /></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="table-wrapper-responsive col-md-12" style="margin-top: 5%; margin-bottom: 2%;">
                <p class="text-center" style="font-size: 15px; font-weight: bold;">Ventas del mes</p>
                <div class="tab-pane fade in active" id="activos2">
                    <table class="table table-hover" >
                        <thead style="border-top-color: #e6400c; border: 2px;">
                            <tr>
                                <th class="goods-page-ref-no text-center">#</th>
                                <th class="goods-page-quantity text-center">Empresa</th>
                                <th class="goods-page-description text-center">Total vendido</th>                                            
                                <th class="goods-page-ref-no text-center">Ganancias obtenidas</th>                                                                                                                   
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${requestScope.ventaMes}" varStatus="loop" var="ventames">
                                <tr>
                                    <td class="text-center">${loop.index +1}</td>                                      
                                    <td class="text-center">${ventames.nombreEmpresa}</td>
                                    <td class="text-center">$${ventames.total}</td>
                                    <td class="text-center">$${ventames.ganancia}</td>
                                  
                                </tr>
                            </c:forEach>
                        
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="footer text-center" style="bottom: auto;">
            <div class="container">
                <div class="row">
                    <!-- BEGIN COPYRIGHT -->
                    <div class="col-md-12 padding-top-10">
                        2018 © Universidad Don Bosco. Derechos reservados. 
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(grafica);

            function grafica() {
                //Se declaran desde aqui para evitar problemas de acceso a las variables
                var fecha;
                var monto;

                //Obtengo datos de la BDD usando AJAX, ajax convierte los resultados a tipo Json y por ende a tipos String
                $.ajax({
                    type: "GET",
                    //En el controller EmpresaController esta este metodo
                    url: 'administrador.do?operacion=ventaanual',
                    statusCode: {
                        404: function () {
                            alert('Pagina no encontrada');
                        },
                        500: function () {
                            alert('Error servidor');
                        }
                    },
                    success: function (dados) {
                        var datos = dados.split(":");
                        var anio = "";
                        var j = 0;
                        var dat = document.getElementById("ventas");
                        fecha = new Array(12);
                        monto = new Array(12);

                        for (j; j < 12; j++) {
                            var fechaactual = datos[j].split("-")[0];
                            fecha[j] = mes(datos[j].split("-")[0]);
                            anio = datos[j].split("-")[1].split(",")[0];
                            monto[j] = datos[j].split(",")[1];

                        }

                        var data = google.visualization.arrayToDataTable([

                            ['Dia', 'ventas($)'],
            <%
                int valor = Integer.parseInt(request.getAttribute("ventas").toString());
                System.out.println(valor);
                for (int i = 0; i <= 11; i++) {
                    if (i == 11) {
            %>
                            [fecha[<%= i%>], Number(monto[<%= i%>])]

            <%
            } else {
            %>
                            [fecha[<%= i%>], Number(monto[<%= i%>])],
            <%
                    }
                }
            %>

                        ]);

                        var options = {
                            title: 'Ventas del año ' + anio + '',
                            hAxis: {title: 'Meses', titleTextStyle: {color: '#333'}},
                            vAxis: {title: 'Monto($)', minValue: 0}
                        };

                        var chart = new google.visualization.AreaChart(document.getElementById('grafica'));
                        chart.draw(data, options);
                    }
                });


            }
            function mes(mes) {
                if (mes === "12") {
                    return 'Diciembre';
                }
                if (mes === "11") {
                    return 'Noviembre';
                }
                if (mes === "10") {
                    return 'Octubre';
                }
                if (mes === "9") {
                    return 'Septiembre';
                }
                if (mes === "8") {
                    return 'Agosto';
                }
                if (mes === "7") {
                    return 'Julio';
                }
                if (mes === "6") {
                    return 'Junio';
                }
                if (mes === "5") {
                    return 'Mayo';
                }
                if (mes === "4") {
                    return 'Abril';
                }
                if (mes === "3") {
                    return 'Marzo';
                }
                if (mes === "2") {
                    return 'Febrero';
                }
                if (mes === "1") {
                    return 'Enero';
                }
            }
        </script>
    </body>
</html>
