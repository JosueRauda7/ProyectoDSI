<%-- 
    Document   : nuevaSubCategoria
    Created on : 21-sep-2018, 10:17:59
    Author     : Ferh
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
                    
                    <div class="margin-bottom-65"></div>
                    <div class="col-lg-3"></div>
                    <div class="content-page col-lg-6">
                        <h1>Nueva Sub Categoria</h1><br/>
                        <!-- BEGIN FORM-->
                        <form action="#" class="default-form" role="form">
                            <div class="form-group">
                                <label for="nombre">Nombre Sub Categoría:</label>
                                <input type="text" class="form-control" name="nombre" id="nombre">
                            </div>
                            <div class="form-group">
                                <label for="descripcion">Descripción:</label>
                                <textarea class="form-control" style="max-width: 100%;" name="descripcion" id="descripcion"></textarea>
                            </div>
                            
                            <div class="form-group">
                                <label for="categoria">Nombre Sub Categoría:</label>
                                <select id="categoria" class="form-control">
                                    <option>Muebles</option>
                                </select>
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
        <br><br><br><br><br>
        <div class="margin-bottom-65"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
