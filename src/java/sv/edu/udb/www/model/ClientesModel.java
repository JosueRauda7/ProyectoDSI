package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Pedido;

public class ClientesModel extends Conexion {

    public int crearCarrito(Pedido pedido) throws SQLException {
        try {
            int filasAfectadas = 0;
            String sql = "INSERT INTO pedidos (fecha_compra, id_usuario,id_estado_compra) VALUES(?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, pedido.getFechaCompra());
            st.setInt(2, pedido.getIdUsuario());
            st.setInt(3, pedido.getIdEstadoCompra());
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public int estadoPedido(int iduser) throws SQLException {
        try {
            String sql = "SELECT MAX(id_estado_compra) AS estado FROM pedidos WHERE id_usuario = ?";
            int estado =0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, iduser);
            rs = st.executeQuery();
            if (rs.next()) {
                estado = rs.getInt("estado");
                this.desconectar();
                return estado;
            }
            this.desconectar();
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

}
