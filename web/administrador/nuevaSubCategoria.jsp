<%-- 
    Document   : nuevaSubCategoria
    Created on : 21-sep-2018, 10:17:59
    Author     : Ferh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Nombre empresa | Administrador</title>
        <jsp:include page="head.jsp"/>        
    </head>
    <body class="ecommerce">
        <jsp:include page="menuAdmin.jsp"/>
        <div class="main">
            <div class="container">
                <div class="col-md-12 col-sm-12">

                    <div class="margin-bottom-65"></div>
                    <div class="col-lg-3"></div>
                    <div class="content-page col-lg-6">
                        <h1>Nueva Sub Categoria</h1><br/>
                        <!-- BEGIN FORM-->
                        <form action="${pageContext.request.contextPath}/SubCategoria.do" class="default-form" role="form">
                            <input type="hidden" name="operacion" value="insertar"/>
                            <div class="form-group">
                                <label for="nombre">Sub Categoría:</label>
                                <input type="text" class="form-control" name="subcategoria" value="${subCategoria.subCategoria}" id="nombre">
                            </div>
                            <div class="form-group">
                                <label for="categoria">Categoria:</label>
                                <select name="categoria" id="categoria" class="form-control">
                                    <c:forEach items="${requestScope.listaCategorias}" var="categorias">

                                        <c:choose>
                                            <c:when test="${categorias.idCategoria eq subCategoria.categoria.idCategoria}">
                                                <option value="${categorias.idCategoria}" selected="true">${categorias.categoria}</option>
                                            </c:when>

                                            <c:otherwise>
                                                <option value="${categorias.idCategoria}">${categorias.categoria}</option>
                                            </c:otherwise>  
                                        </c:choose>

                                        

                                    </c:forEach>
                                </select>
                            </div>



                            <div class="padding-top-20">                  
                                <button type="submit" class="btn btn-primary">Registrar</button>
                                <button type="submit" style="background-color: #F3565D;" class="btn btn-primary">Cancelar</button>
                            </div>
                        </form>
                        <!-- END FORM--> 
                    </div>
                </div>
            </div>
        </div>
        <br><br><br><br><br>
        <div class="margin-bottom-65"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
