<%-- 
    Document   : estadisticaEmpresa
    Created on : Nov 4, 2018, 2:20:13 PM
    Author     : ivanm
--%>

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
            <input type="hidden" id="ventas" value="${ventas}"/>
            <div id="grafica">

            </div>
        </div>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(grafica);

            function grafica(){
                //Se declaran desde aqui para evitar problemas de acceso a las variables
                var fecha;
                var monto;
                
                //Obtengo datos de la BDD usando AJAX, ajax convierte los resultados a tipo Json y por ende a tipos String
                $.ajax({
                    type: "GET",
                    //En el controller EmpresaController esta este metodo
                    url: 'empresas.do?operacion=ventadiaria',                    
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
                        var j = 0;
                        var dat = document.getElementById("ventas");
                        fecha = new Array(datos.length);
                        monto = new Array(datos.length);
                        
                        for (j ; j < datos.length - 1; j++) {                            
                            var fechaactual = datos[j].split("-");                            
                            fecha[j] = mes(fechaactual[1]) + ' ' + fechaactual[2].split(",")[0];
                            monto[j] = datos[j].split(",")[1];
                            
                            }
                            
                        var data = google.visualization.arrayToDataTable([
                            
                            ['Dia', 'ventas'],
                            <%
                                int valor = Integer.parseInt(request.getAttribute("ventas").toString());
                                System.out.println(valor);
                            for(int i=0; i<= valor - 1; i++ ){
                                if( i == valor - 1 ){
                            %>
                                    [fecha[<%= i %>],Number(monto[<%= i %>])]
                            
                            <%
                                }else{
                            %>
                                    [fecha[<%= i %>],Number(monto[<%= i %>])],
                            <%
                                }
                            }
                            %>
                            
                        ]);                        
                        
                        var options = {
                            title: 'Ventas diarias',
                            hAxis: {title: 'Dias', titleTextStyle: {color: '#333'}},
                            vAxis: {title: 'Monto($)' ,minValue: 0}
                        };

                        var chart = new google.visualization.AreaChart(document.getElementById('grafica'));
                            chart.draw(data, options);
                        }
                });
                
                
            }
            function mes (mes){
                if(mes === "12"){
                    return 'Diciembre';
                }
                if(mes === "11"){
                    return 'Noviembre';
                }
                if(mes === "10"){
                    return 'Octubre';
                }
                if(mes === "09"){
                    return 'Septiembre';
                }
                if(mes === "08"){
                    return 'Agosto';
                }if(mes === "07"){
                    return 'Julio';
                }if(mes === "06"){
                    return 'Junio';
                }if(mes === "05"){
                    return 'Mayo';
                }if(mes === "04"){
                    return 'Abril';
                }               
                if(mes === "03"){
                    return 'Marzo';
                }
                if(mes === "02"){
                    return 'Febrero';
                }
                if(mes === "01"){
                    return 'Enero';
                }
            }
        </script>
    </body>
</html>
