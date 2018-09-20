<%-- 
    Document   : verCategorias
    Created on : 09-19-2018, 11:51:13 PM
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
                    <h1>Lista de Categorias</h1>
                    <a class="btn btn-primary">Nueva Categoria</a>
                    <br><br>
                    <div class="goods-page">
                        <div class="goods-data clearfix">
                            <div class="table-wrapper-responsive">
                                <table summary="Shopping cart">
                                    <tr>
                                        <th class="goods-page-image">Categoria</th>
                                        <th class="goods-page-description">Descripción</th>
                                        <th class="goods-page-total" colspan="2">Operaciones</th>
                                    </tr>
                                    <tr>
                                        <td class="goods-page-image">
                                            Ropa
                                        </td>
                                        <td class="goods-page-description">
                                            Vestimenta para hombres, mujeres y niños.
                                        </td>
                                        <td class="goods-page-total">
                                            <a class="btn btn-default">Editar</a>
                                            <a style="margin-left: 2%; color:white" class="btn btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="goods-page-image">
                                            Muebles
                                        </td>
                                        <td class="goods-page-description">
                                            Todo tipo de muebles para guardar y almacenar objetos, de igual forma, decorar la casa
                                        </td>
                                        <td class="goods-page-total">
                                            <a class="btn btn-default">Editar</a>
                                            <a style="margin-left: 2%; color:white" class="btn btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="goods-page-image">
                                            Electrodomésticos
                                        </td>
                                        <td class="goods-page-description">
                                            Herramientas de la cocina
                                        </td>
                                        <td class="goods-page-total">
                                            <a class="btn btn-default">Editar</a>
                                            <a style="margin-left: 2%; color:white" class="btn btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="goods-page-image">
                                            Dispositivos Electrónicos
                                        </td>
                                        <td class="goods-page-description">
                                            Herramientas tecnológicas del día a día como celular, tablets, computadoras, entre otros
                                        </td>
                                        <td class="goods-page-total">
                                            <a class="btn btn-default">Editar</a>
                                            <a style="margin-left: 2%; color:white" class="btn btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                </table>
                            </div>

                        </div>
                        
                    </div>
                </div>
                <!-- END CONTENT -->
            </div>
        </div>
        <div class="margin-bottom-40"></div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
