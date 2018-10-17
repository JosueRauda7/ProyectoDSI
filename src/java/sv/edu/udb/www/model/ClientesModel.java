package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.EstadoProducto;
import sv.edu.udb.www.beans.Pedido;
import sv.edu.udb.www.beans.Producto;
import sv.edu.udb.www.beans.SubCategoria;

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
            int estado = 0;
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

    public Producto verProducto(int idpro) throws SQLException {
        try {
            String sql = "SELECT * FROM producto p INNER JOIN sub_categoria sc on p.id_sub_categoria = sc.id_sub_categoria INNER JOIN empresa em on p.id_empresa = em.id_empresa INNER JOIN estado_producto es on p.id_estado_producto = es.id_estado_producto WHERE p.id_producto = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idpro);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setUrlImagen(rs.getString("url_imagen"));
                producto.setEmpresa(new Empresa(rs.getString("empresa")));
                producto.setSubCategoria(new SubCategoria(rs.getString("subcategoria")));
                producto.setEstadoProducto(new EstadoProducto(rs.getString("estado")));
                this.desconectar();
                return producto;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

}
