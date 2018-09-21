<%-- 
    Document   : listaSubCategorias
    Created on : 21-sep-2018, 9:38:14
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
                    
                    <div class="goods-page">
                        
                        <div class="goods-data clearfix">
                            <h1>Lista de Sub Categorias</h1><br/>
                            <div class="table-wrapper-responsive">
                                <table summary="Shopping cart">
                                    <tr>
                                        <th class="goods-page-image">Nombre</th>
                                        <th class="goods-page-description">Descripcion</th>
                                        <th class="goods-page-ref-no">Categoria</th>
                                        <th class="goods-page-ref-no">Operaciones</th>
                                        
                                    </tr>
                                    <tr>
                                        <td class="goods-page-image">
                                            Sofá
                                        </td>
                                        <td class="goods-page-description">
                                            Variedad de sofá para tener en su casa
                                        </td>
                                        <td class="goods-page-ref-no">
                                            Muebles
                                        </td>
                                        
                                        <td class="goods-page-ref-no">
                                            <a class="btn btn-default add2cart" style="color:white; float: none;">Editar</a>
                                            <a class="btn btn-danger add2cart" style="color:white; float: none;">Borrar</a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="goods-page-image">
                                            Sofá
                                        </td>
                                        <td class="goods-page-description">
                                            Variedad de sofá para tener en su casa
                                        </td>
                                        <td class="goods-page-ref-no">
                                            Muebles
                                        </td>
                                        
                                        <td class="goods-page-ref-no">
                                            <a class="btn btn-default add2cart" style="color:white; float: none;">Editar</a>
                                            <a class="btn btn-danger add2cart" style="color:white; float: none;">Borrar</a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="goods-page-image">
                                            Sofá
                                        </td>
                                        <td class="goods-page-description">
                                            Variedad de sofá para tener en su casa
                                        </td>
                                        <td class="goods-page-ref-no">
                                            Muebles
                                        </td>
                                        
                                        <td class="goods-page-ref-no">
                                            <a class="btn btn-default add2cart" style="color:white; float: none;">Editar</a>
                                            <a class="btn btn-danger add2cart" style="color:white; float: none;">Borrar</a>
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
        <jsp:include page="footer.jsp"/>
    </body>
</html>
