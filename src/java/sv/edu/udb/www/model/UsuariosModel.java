package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Categoria;
import sv.edu.udb.www.beans.DetallePedido;
import sv.edu.udb.www.beans.Pedido;
import sv.edu.udb.www.beans.Producto;
import sv.edu.udb.www.beans.SubCategoria;
import sv.edu.udb.www.beans.Usuario;
import static sv.edu.udb.www.model.Conexion.conexion;

public class UsuariosModel extends Conexion {

    public int insertarUsuario(Usuario usuario, String idConfirmacion) throws SQLException {
        try {
            int filasAfectadas = 0;
            String sql = "INSERT INTO usuarios(Nombre,Apellido,Telefono,direccion,DUI,Correo,password,id_confirmacion,confirmado,id_tipo_usuario) VALUES(?,?,?,?,?,?,SHA2(?,256),?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getApellido());
            st.setString(3, usuario.getTelefono());
            st.setString(4, usuario.getDireccion());
            st.setString(5, usuario.getDui());
            st.setString(6, usuario.getCorreo());
            st.setString(7, usuario.getPassword());
            st.setString(8, idConfirmacion);
            st.setInt(9, 0);
            st.setInt(10, usuario.getTipoUser());
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public void confirmarCuenta(String id) throws SQLException {
        try {
            sql = "UPDATE usuarios SET confirmado=1 WHERE id_confirmacion =  ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        this.desconectar();
    }

    public List<Usuario> listarClientes() throws SQLException {
        try {
            List<Usuario> lista = new ArrayList<>();
            sql = "SELECT * FROM usuarios WHERE id_tipo_usuario = 2 AND confirmado = 1";
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Usuario cliente = new Usuario();
                cliente.setIdUsuario(rs.getInt("id_usuario"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDui(rs.getString("dui"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                lista.add(cliente);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<DetallePedido> listarProductosCliente(int idCliente) throws SQLException {
        try {
            List<DetallePedido> lista = new ArrayList<>();
            sql = "SELECT t3.producto, t3.descripcion, t1.cantidad, t5.categoria, t4.subcategoria, t2.id_pedido FROM detalle_pedidos t1 INNER JOIN pedidos t2 ON t1.id_pedido = t2.id_pedido INNER JOIN producto t3 ON t1.id_producto = t3.id_producto INNER JOIN sub_categoria t4 ON t3.id_sub_categoria = t4.id_sub_categoria INNER JOIN categoria t5 ON t4.id_categoria = t5.id_categoria WHERE t2.id_estado_compra=2 AND t2.id_usuario = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idCliente);
            rs = st.executeQuery();
            while (rs.next()) {

                DetallePedido detallePedido = new DetallePedido();
                Pedido pedido = new Pedido();
                Producto producto = new Producto();
                Categoria categoria = new Categoria();
                SubCategoria subcategoria = new SubCategoria();

                categoria.setCategoria(rs.getString("categoria"));

                subcategoria.setSubCategoria(rs.getString("subcategoria"));
                subcategoria.setCategoria(categoria);

                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setSubCategoria(subcategoria);

                pedido.setIdPedido(rs.getInt("id_pedido"));

                detallePedido.setProducto(producto);
                detallePedido.setPedido(pedido);
                detallePedido.setCantidad(rs.getInt("cantidad"));

                lista.add(detallePedido);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<Pedido> listarPedidos(int idCliente) throws SQLException {
        try {
            List<Pedido> lista = new ArrayList<>();
            sql = "SELECT * FROM pedidos WHERE id_estado_compra = 2 AND id_usuario = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idCliente);
            rs = st.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setFechaCompra(rs.getDate("fecha_compra"));
                pedido.setMontoTotal(rs.getDouble("monto_total"));
                lista.add(pedido);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public Usuario obtenerCliente(int idCliente) throws SQLException {
        try {
            Usuario usuario = new Usuario();
            sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idCliente);
            rs = st.executeQuery();
            if (rs.next()) {
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));

                this.desconectar();
                return usuario;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
}
