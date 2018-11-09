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
        <title>BigShop | Marketing</title>
        <jsp:include page="/empleadoMarketing/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
    </head>
    <body class="ecommerce">
        <jsp:include page="/empleadoMarketing/menuEmpleadoMarketing.jsp"/>
        <div class="main">
            <div class="container">
                
                
                <div class="col-md-12 col-sm-12">
                    
                    <h1>Lista de Ofertas</h1>
                    <div class="goods-page">
                        
                        <div class="goods-data clearfix">
                            
                            
                            
                            <div class="table-wrapper-responsive">
                                <table summary="Shopping cart">

                                    <tr>
                                        <th class="goods-page-image">Imagen</th>
                                        <th class="goods-page-image">Titulo</th>
                                        <th class="goods-page-description">Descripción</th>
                                        <th class="goods-page-ref-no">Producto</th>
                                        <th class="goods-page-ref-no">Operaciones</th>

                                    </tr>
                                    

                                    <c:forEach items="${requestScope.listaOfertas}" var="oferta">

                                        <tr>
                                            <td><img height="100px" src="${pageContext.request.contextPath}/images/${oferta.urlFoto}"></td>
                                            <td>${oferta.titulo}</td>
                                            <td>${oferta.descripcion}</td>
                                            
                                            <td>${oferta.nombreProducto}</td>
                                            

                                            <td>
                                                <a title="Publicar" class="btn btn-default" style="color:white;" href="javascript: publicar('${oferta.idOferta}')">Publicar</a>
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
        <div class="margin-bottom-40" style="margin-top: 150px;"></div>
        <jsp:include page="/empleadoMarketing/footer.jsp" />
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
        
    function publicar(id) {


            swal({
                title: '¿Desea enviar está oferta?',
                text: "Aceptar para enviar correos",
                icon: 'warning',
                buttons: true,
                dangerMode: true,
            })
                    .then((willDelete) => {
                        if (willDelete) {
                            location.href = "empleadoMarketing.do?operacion=enviarOfertas&id=" + id
                        }
                    })


        }

 </script>
</html>
