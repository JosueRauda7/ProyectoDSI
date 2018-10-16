
package sv.edu.udb.www.model;

import sv.edu.udb.www.beans.Pedido;

public class ClientesModel extends Conexion {
    public int crearCarrito(Pedido pedido){
    String sql = "INSERT INTO pedidos (fecha_compra, id_usuario,id_estado_compra) VALUES(?,?,?)";
    this.conectar();
    st = conexion.prepareStatement(sql);
    st.setString(1, pedido.getFechaCompra());
    st.setInt(2,);
    }
}
