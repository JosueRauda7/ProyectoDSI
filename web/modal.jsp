<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                    <form class="form-horizontal" method="post" style="width: 80%;margin-left: 10%;">
                        <div class="form-group">
                            <label for="nombre" class="text-center">Correo:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="nombre" id="nombre"  placeholder="Ingresa tu correo" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombre" class="text-center">Contraseña:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="nombre" id="nombre"  placeholder="Ingresa tu contraseña" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary btn-lg">Ingresar</button>
                            </div>
                        </div>
                        <a href="#" ><p style="margin-left: 65%;">¿Olvidaste tu contraseña?</p></a>
                    </form>
                </div>
                <div  id="Registro" class="tabcontent">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>

            </div>
        </div>
    </div>
</div>