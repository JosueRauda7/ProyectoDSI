<%-- 
    Document   : nuevoAdministrador
    Created on : 09-19-2018, 08:19:24 PM
    Author     : admi
--%>

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
                    <h1>Nuevo Administrador</h1>
                    <div class="col-lg-3"></div>
                    <div class="content-page col-lg-6">
                        <!-- BEGIN FORM-->
                        <form action="#" class="default-form" role="form">
                            <div class="form-group">
                                <label for="nombre">Nombres:</label>
                                <input type="text" class="form-control" name="nombre" id="nombre">
                            </div>
                            <div class="form-group">
                                <label for="apellido">Apellidos:</label>
                                <input type="text" class="form-control" name="apellido" id="apellido">
                            </div>
                            <div class="form-group">
                                <label for="correo">Correo:</label>
                                <input type="text" class="form-control" name="correo" id="correo">
                            </div>
                            <div class="form-group">
                                <label for="telefono">Teléfono:</label>
                                <input type="text" class="form-control" name="telefono" id="telefono">
                            </div>
                            <div class="form-group">
                                <label for="dui">DUI:</label>
                                <input type="text" class="form-control" name="dui" id="dui">
                            </div>
                            <div class="form-group">
                                <label for="direccion">Dirección:</label>
                                <textarea class="form-control" style="max-width: 100%;" name="direccion" id="direccion"></textarea>
                            </div>
                            <div class="padding-top-20">                  
                                <button type="submit" class="btn btn-primary">Aceptar</button>
                                <button type="submit" style="background-color: #F3565D;" class="btn btn-primary">Cancelar</button>
                            </div>
                        </form>
                        <!-- END FORM--> 
                    </div>
                </div>
            </div>
        </div>
        <div class="margin-bottom-40"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
