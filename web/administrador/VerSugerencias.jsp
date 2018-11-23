<%-- 
    Document   : VerSugerencias
    Created on : 17-nov-2018, 16:05:36
    Author     : Ferh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>BigShop | Administrador</title>
        <jsp:include page="head.jsp"/>        
    </head>
    <body class="ecommerce">
        <jsp:include page="menuAdmin.jsp"/>
        <div class="main">
            <div class="container">
                
                
                <div class="col-md-12 col-sm-12">
                    
                    <h1>Lista de Sugerencias</h1>
                            
                    <div class="goods-page">
                        
                        <div class="goods-data clearfix">
                            
                            
                            <div class="table-wrapper-responsive">
                                
                                <c:if test="${empty requestScope.listaSugerencias}">
                                    <h2>No hay sugerencias actualmente</h2>
                                </c:if>
                                <c:if test="${not empty requestScope.listaSugerencias}">
                                <table summary="Shopping cart">

                                    <tr>
                                        <th class="goods-page-image">ID</th>
                                        <th class="goods-page-description">Asunto</th>
                                        <th class="goods-page-ref-no">Descripción</th>
                                        <th class="goods-page-ref-no">Operaciones</th>

                                    </tr>
                                    

                                    <c:forEach items="${requestScope.listaSugerencias}" var="sugerencias">

                                        <tr>
                                            
                                            <td>${sugerencias.idSugerencia}</td>
                                            <td>${sugerencias.asunto}</td>
                                            
                                            <td>${sugerencias.detalle}</td>
                                            

                                            <td>
                                                <a title="Verificar" class="btn btn-success" style="color:white;" href="${pageContext.request.contextPath}/administrador.do?operacion=verificarSugerencia&id=${sugerencias.idSugerencia}"><span class="glyphicon glyphicon-check"></span></a>
                                                
                                            </td>
                                        </tr>

                                    </c:forEach>

                                </table>
                                </c:if>
                            </div>

                        </div>

                    </div>
                </div>
                <!-- END CONTENT -->
            </div>
        </div>
        <div class="margin-bottom-40" style="margin-top: 150px;"></div>
        <div class="footer text-center" style="position:absolute; bottom: 0; width: 100%;">
    <div class="container">
        <div class="row">
            <!-- BEGIN COPYRIGHT -->
            <div class="col-md-12 padding-top-10">
                2018 © Universidad Don Bosco. Derechos reservados. 
                <a href="${base}/ProyectoDSI/administrador.do?operacion=terminosLegales">Términos Legales</a>
            </div>
        </div>
    </div>
</div>
    </body>
    
    <script>
    <c:if test="${not empty exito}">
    swal({
                title: "Bien!",
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
        
    
 </script>
</html>

