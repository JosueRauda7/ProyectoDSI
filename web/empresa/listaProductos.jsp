<%-- 
    Document   : comprasCliente
    Created on : 18-sep-2018, 18:24:00
    Author     : Ferh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Empresa</title>

        <jsp:include page="head.jsp"/>        


        <!-- Theme styles END -->
    </head>
    <!-- Head END -->

    <body class="ecommerce">


        <jsp:include page="menuEmpresa.jsp"/>


        <div class="container" >          
            <a style="margin-bottom: 2%;" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/empresas.do?operacion=nuevo">Nuevo producto</a>
            <ul id="myTab" class="nav nav-tabs">                    
                <li <c:if test="${requestScope.tab eq 1}"> class="active"</c:if> ><a href="${base}/empresas.do?operacion=listar&estado=1" data-toggle="activos">Listar en espera</a></li>
                <li <c:if test="${requestScope.tab eq 2}"> class="active"</c:if> ><a href="${base}/empresas.do?operacion=listar&estado=2" data-toggle="activos">Listar activos</a></li>
                <li <c:if test="${requestScope.tab eq 3}"> class="active"</c:if> ><a href="${base}/empresas.do?operacion=listar&estado=3" data-toggle="activos">Listar rechazados</a></li>                    
                </ul>

                <div class="tab-pane fade in active" id="activos">

                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <div class="table-wrapper-responsive">
                                <table  id="tabla">
                                    <thead>
                                        <tr>
                                            <th class="goods-page-description text-center">Producto</th>
                                            <th class="goods-page-quantity text-center">Precio</th>
                                            <th class="goods-page-description text-center">Disponibles</th>                                            
                                            <th class="goods-page-image text-center">Imagen</th>                                                                                       
                                            <th class="goods-page text-center">Operacion</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.listarProducto}" var="productos">
                                        <tr class="text-center">
                                            <td class="goods-page-description">
                                                ${productos.producto}
                                            </td>
                                            <td class="goods-page-quantity">
                                                ${productos.precioRegular}
                                            </td>
                                            <td class="goods-page-description">
                                                <c:if test="${productos.cantidad eq 0}">
                                                    <p style="color: red;">No hay existencias</p>${productos.cantidad}
                                                </c:if>
                                                <c:if test="${productos.cantidad ne 0}">
                                                    <p style="color: #00cc00;">Hay existencias</p> (${productos.cantidad})
                                                </c:if>
                                            </td>
                                            <td class="goods-page-image">
                                                <c:forEach items="${requestScope.listarImagenes}" var="imagenes">
                                                    <c:if test="${productos.idProducto eq imagenes.idProducto}">
                                                        <img height="100px" src="${base}/images/${imagenes.urlimagen}"/>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td class="goods-page text-center">
                                                <c:choose>
                                                    <c:when test="${productos.estadoProducto.estado eq 'Activo'}">
                                                        <a class="btn btn-secondary glyphicon glyphicon-plus" title="agregar" style="padding: 5%;" data-toggle="modal" href="#exampleModal${productos.idProducto}"></a>

                                                        <div class="modal fade" id="exampleModal${productos.idProducto}">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                            <span aria-hidden="true">&times;</span>
                                                                        </button>
                                                                        <h5 class="modal-title" id="exampleModalLabel">Añadir existencias</h5>                                                                            
                                                                    </div>
                                                                    <form method="POST" action="${base}/empresas.do">
                                                                        <input type="hidden" name="operacion" value="existencias" />
                                                                        <input type="hidden" name="id" value="${productos.idProducto}" />
                                                                        <div class="modal-body">
                                                                            <div class="text-center">
                                                                                <c:forEach items="${requestScope.listarImagenes}" var="imagenes">
                                                                                    <c:if test="${productos.idProducto eq imagenes.idProducto}">
                                                                                        <img height="100px" src="${base}/images/${imagenes.urlimagen}"/>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </div><br>
                                                                            <p class="text-center">${productos.producto}</p><br>
                                                                            <p class="text-center">Existencias: ${productos.cantidad}</p><br>
                                                                            <div class="form-group" style="display: inline-block" >                                                                               
                                                                                <label for="existencias" >Cantidad a añadir</label>                                                                                
                                                                                <input type="number" class="form-control" min="1" max="99" id="existencias" name="existencias" value="0" />                                                                                
                                                                            </div>
                                                                            <br>    
                                                                        </div>
                                                                        <div class="modal-footer" style="display: inline-block">
                                                                            <button type="button" class="btn btn-secondary " data-dismiss="modal">Cancelar</button>
                                                                            <button type="submit" class="btn btn-primary">Añadir</button>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${productos.estadoProducto.estado eq 'Rechazado'}">
                                                                <a class="btn btn-info" title="Reenviar" style="padding: 5%;"
                                                                   href="${base}/empresas.do?operacion=obtener&id=${productos.idProducto}">Reenviar</a>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:otherwise>
                                                </c:choose>                                                    
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
        <br>
        <!-- Modal -->




        <jsp:include page="footer.jsp"/>
    </body>
    <script type="text/javascript">
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