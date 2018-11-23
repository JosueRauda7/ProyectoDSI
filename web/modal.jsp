<!-- Modal -->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="exampleModal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="exampleModalLabel">Logueate</h2>
                <button type="button" class="close" style="font-size: 30px; margin-top: -5%;" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" class="text-danger">&times;</span>
                </button>

                <div class="tab">

                    <button class="tablinks" onclick="openCity(event, 'Logueo')">Logueate</button>
                    <button class="tablinks" onclick="openCity(event, 'Registro')">Registrate</button>

                </div>
            </div>
            <div class="modal-body">
                <div  id="Logueo" class="tabcontent">
                    <c:if test="${not empty requestScope.listaErrores2}">
                        <div class="alert alert-danger">
                            <ul>
                                <c:forEach var="error" items="${requestScope.listaErrores2}">
                                    <li>${error}</li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    <form class="form-horizontal" action="${pageContext.request.contextPath}/usuarios.do" method="post" style="width: 80%;margin-left: 10%;">
                        <input type="hidden" name="operacion" value="login"/>
                        <input type="hidden" id="url" name="url">
                        <div class="form-group">
                            <label for="nombre" class="text-center">Correo:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="correo" id="nombre"  placeholder="Ingresa tu correo" pattern="^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$" required="" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombre" class="text-center">Contraseña:</label>
                       
                                <input type="password" class="form-control" name="password" id="nombre" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"  required=""  placeholder="Ingresa tu contraseña"  data-toggle="password" >
                               
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary btn-lg">Ingresar</button>
                            </div>
                        </div>
                        <a href="#" ><p style="margin-left: 65%;">¿Olvidaste tu contraseña?</p></a>
                    </form>
                </div>


                <div  id="Registro"  class="tabcontent">
                    <c:if test="${not empty requestScope.listaErrores}">
                        <div class="alert alert-danger">
                            <ul>
                                <c:forEach var="error" items="${requestScope.listaErrores}">
                                    <li>${error}</li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/usuarios.do" style="width: 80%;margin-left: 10%;">
                        <input type="hidden" name="operacion" value="registroCliente"/>
                        <input type="hidden" id="url" name="url">
                        <div class="form-group">
                            <label for="nombre" class="text-center">Correo:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" value="${usuario.correo}" pattern="^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$" required="" name="correo" id="correo"  placeholder="Ingresa tu correo" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombre" class="text-center">Nombres:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" required="" value="${usuario.nombre}" name="nombre" id="nombre1"  placeholder="Ingresa tus nombres" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombre" class="text-center">Apellidos</label>
                            <div class="input-group">
                                <input type="text" class="form-control" value="${usuario.apellido}" required="" name="apellido" id="apelldo"  placeholder="Ingresa tus apellidos" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombre" class="text-center">Dirección:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" value="${usuario.direccion}" required="" name="direccion" id="direccion"  placeholder="Ingresa tu dirección" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombre" class="text-center">Numero telefonico:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" ${usuario.telefono} name="telefono" id="telefono" onkeypress="validarTelefono();" required="" maxlength="9" placeholder="Ingresa tu numero de telefono" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-phone-alt"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombre" class="text-center">Dui:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" value="${usuario.dui}" name="dui" id="dui" onkeypress="validarDui();" pattern="[0-9]{8}-[0-9]{1}" required="" maxlength="10" class="perm"  placeholder="Ingresa tu dui" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-info-sign"></span></span>
                            </div>
                        </div>
                      
                        <div class="form-group">
                            <label for="nombre" class="text-center">Contraseña: (maximo 8 caracteres 1 mayuscula y un caracter especial)</label>                              
                            <input type="password" class="form-control" ${usuario.password} name="contrasena" id="contrasena" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"  required=""  placeholder="Ingresa tu contraseña"  data-toggle="password">                               
                        </div>
                        <div class="form-group">
                            <label for="nombre" class="text-center">Confirmar contraseña:</label>                              
                            <input type="password" class="form-control" ${usuario.password} name="contrasena2" id="contrasena" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"  required=""  placeholder="Debe coincidir exactamente"  data-toggle="password">                               
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary btn-lg">Ingresar</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var URLactual = window.location;
    document.getElementById("url").value = URLactual.toString().substring(34);
    <c:if test="${not empty requestScope.listaErrores}">
    $(document).ready(function () {
        $('#exampleModal').modal('show');
        document.getElementById("Registro").style.display = "block";
        document.getElementById("Logueo").style.display = "none";
        document.getElementById("url").value = "${requestScope.url}";
    });
    </c:if>
    <c:if test="${not empty requestScope.listaErrores2}">
    $(document).ready(function () {
        $('#exampleModal').modal('show');
        document.getElementById("Registro").style.display = "none";
        document.getElementById("Logueo").style.display = "block";
        document.getElementById("url").value = "${requestScope.url}";
    });
    </c:if>
    
    
        function validarDui(){
            
            var cant =  document.getElementById("dui").value.length;
            
            if(cant=="8"){
                document.getElementById("dui").value += "-";
            }
        
        }
        
        function validarTelefono(){
            
            var cant =  document.getElementById("telefono").value.length;
            
            if(cant=="4"){
                document.getElementById("telefono").value += "-";
            }
        
        }
        
        
    
</script>
