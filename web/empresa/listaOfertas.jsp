<%-- 
    Document   : listaOfertas
    Created on : Nov 9, 2018, 12:13:18 PM
    Author     : ivanm
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Empresa</title>

        <jsp:include page="head.jsp"/>   
    </head>
    <body class="commerce">
        <jsp:include page="menuEmpresa.jsp"/>
        <div class="container" >          
            <a style="margin-bottom: 2%;" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=agregarOfertas">Nueva oferta</a>

            <div class="tab-pane fade in active" id="activos">

                <div class="goods-page">
                    <div class="goods-data clearfix">
                        <div class="table-wrapper-responsive">
                            <table  id="tabla">
                                <thead>
                                    <tr>
                                        <th class="goods-page-description text-center">Imagen</th>
                                        <th class="goods-page-quantity text-center">Titulo</th>
                                        <th class="goods-page-description text-center">Descripción</th>                                            
                                        <th class="goods-page text-center">precio de oferta</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.listarOfertas}" var="oferta">
                                        <tr class="text-center">
                                            <td class="goods-page-description">
                                                <img height="100px" src="${base}/images/${oferta.urlFoto}"/>
                                            </td>
                                            <td class="goods-page-quantity">
                                                ${oferta.titulo}
                                            </td>
                                            <td class="goods-page-description">
                                                ${oferta.descripcion}
                                            </td>
                                            <td class="goods-page-image">
                                                $${oferta.totalDescuento}
                                            </td>                                            
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer text-center" style="position: absolute; bottom: auto; width: 100%;">
            <div class="container">
                <div class="row">
                    <!-- BEGIN COPYRIGHT -->
                    <div class="col-md-12 padding-top-10">
                        2018 © Universidad Don Bosco. Derechos reservados. 
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
