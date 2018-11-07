<%-- 
    Document   : estadisticaEmpresa
    Created on : Nov 4, 2018, 2:20:13 PM
    Author     : ivanm
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estadisticas empresa</title>
        <jsp:include page="head.jsp"/>   
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    </head>
    <body class="ecommerce">
        <jsp:include page="menuEmpresa.jsp"/>
        <div class="container">
            <input type="hidden" id="ventas" value="${ventas}" />
            <div id="grafica" style="width: 100%; height: 500px;">

            </div>
            <div style="margin-top: 3%;">
                <form method="POST" action="${base}/empresas.do" role="form">
                    <input type="hidden" name="operacion" value="grafica" />
                    <div class="col-md-2 text-center">
                        Selecciona un año
                    </div>
                    <div class="col-md-3">                                    
                        <select class="form-control" id="anio" name="anio">
                            <option value="2018">2018</option>
                            <option value="2017">2017</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <input type="submit" value="Actualizar" class="btn btn-primary"/>
                    </div>
                </form>
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
                    url: 'empresas.do?operacion=ventaanual',
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
