<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BigShop | Marketing</title>
        <jsp:include page="/head.jsp"/>        
        <jsp:include page="/scripts.jsp"/>
    </head>
    <body class="ecommerce">
        <jsp:include page="/empleadoMarketing/menuEmpleadoMarketing.jsp"/>
        <div class="main" style="margin-top: 20px;">
            <div class="container">
                <div class="col-md-12 col-sm-12">
                    
                    <div class="col-lg-3"></div>
                    <div class="content-page col-lg-6">
                        <h1>Nuevo Correo</h1>
                        <c:if test="${not empty requestScope.listaErrores}">
                            <div class="row col-lg-12">
                                <div class="alert alert-danger">
                                    <ul>
                                        <c:forEach items="${requestScope.listaErrores}" var="error">
                                            <li>${error}</li>
                                            </c:forEach> 
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        
                        <!-- BEGIN FORM-->
                        <form action="${pageContext.request.contextPath}/empleadoMarketing.do" method="POST" class="default-form" role="form">
                            <input type="hidden" name="operacion" value="enviarCorreos"/>
                            <div class="form-group">
                                <label for="asunto">Asunto:</label>
                                <input type="text" value="${requestScope.usuario.nombre}" class="form-control" name="asunto" id="asunto">
                            </div>
                            <div class="form-group">
                                <label for="mensaje">Mensaje:</label>
                                <input type="text" value="${requestScope.usuario.apellido}" class="form-control" name="mensaje" id="mensaje">
                            </div>
                            <div class="form-group">
                                <label for="producto">Producto u oferta:</label>
                                <input type="text" value="${requestScope.usuario.correo}" class="form-control" name="producto" id="producto">
                            </div>
                            
                            <div class="padding-top-20">                  
                                <button type="submit" class="btn btn-primary">Enviar</button>
                                <button type="submit" style="background-color: #F3565D;" class="btn btn-primary">Cancelar</button>
                            </div>
                        </form>
                        <!-- END FORM--> 
                    </div>
                </div>
            </div>
        </div>
        <div class="margin-bottom-40"></div>
        <jsp:include page="/empleadoMarketing/footer.jsp"/>
    </body>
</html>
