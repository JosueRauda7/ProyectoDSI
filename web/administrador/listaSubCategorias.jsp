<%-- 
    Document   : listaSubCategorias
    Created on : 21-sep-2018, 9:38:14
    Author     : Ferh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    
                    <h1>Lista de Sub Categorías de ${categoria.categoria}</h1>
                    <a class="btn btn-primary" style="color:white;" href="${pageContext.request.contextPath}/administrador.do?operacion=nuevaSubCategoria&idCategoria=${pageContext.request.getParameter("id")}">Nueva sub categoría</a><br/><br/>
                            
                    <div class="goods-page">
                        
                        <div class="goods-data clearfix">
                            
                            
                            
                            <div class="table-wrapper-responsive">
                                <table summary="Shopping cart">

                                    <tr>
                                        <th class="goods-page-image">Imagen</th>
                                        <th class="goods-page-image">ID</th>
                                        <th class="goods-page-description">Sub Categoría</th>
                                        <th class="goods-page-ref-no">Estado</th>
                                        <th class="goods-page-ref-no">Operaciones</th>

                                    </tr>
                                    

                                    <c:forEach items="${requestScope.listaSubCategorias}" var="subCategoria">

                                        <tr>
                                            <td><img height="100px" src="${pageContext.request.contextPath}/images/${subCategoria.urlSubcategoria}"></td>
                                            <td>${subCategoria.idSubCategoria}</td>
                                            <td>${subCategoria.subCategoria}</td>
                                            
                                            <td>${subCategoria.estadoCategoria.estadoCategoria}</td>
                                            

                                            <td>
                                                <a title="Editar" class="btn btn-default" style="color:white;" href="${pageContext.request.contextPath}/administrador.do?operacion=obtenerSubCategoria&id=${subCategoria.idSubCategoria}&idCategoria=${pageContext.request.getParameter("id")}"><span class="glyphicon glyphicon-edit"></span></a>
                                                <c:if test="${subCategoria.estadoCategoria.estadoCategoria == 'activo'}">
                                                <a title="Deshabilitar" class="btn btn-danger" style="color:white;" href="javascript:deshabilitar('${subCategoria.idSubCategoria}')"><span class="glyphicon glyphicon-trash"></span></a>
                                                </c:if>
                                                <c:if test="${subCategoria.estadoCategoria.estadoCategoria eq 'inactivo'}">
                                                <a title="Habilitar" class="btn btn-info" style="color:white;" href="${pageContext.request.contextPath}/administrador.do?operacion=habilitarSubCategoria&id=${subCategoria.idSubCategoria}"><span class="glyphicon glyphicon-check"></span></a>
                                                </c:if>
                                            </td>
                                        </tr>

                                    </c:forEach>

                                </table>
                            </div>

                        </div>

                    </div>
                </div>
                <!-- END CONTENT -->
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
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
        
    function deshabilitar(id) {


            swal({
                title: '¿Seguro que lo deseas deshabilitar?',
                text: "Si aceptas, lo puedes volver habilitar!",
                icon: 'warning',
                buttons: true,
                dangerMode: true,
            })
                    .then((willDelete) => {
                        if (willDelete) {
                            location.href = "administrador.do?operacion=deshabilitarSubCategoria&id=" + id
                        }
                    })


        }

 </script>
</html>
