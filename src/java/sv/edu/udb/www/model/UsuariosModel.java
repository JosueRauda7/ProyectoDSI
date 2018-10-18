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

    public Usuario verificarCuenta(Usuario usuario) throws SQLException {
        try {
            String sql = "SELECT * FROM usuarios u WHERE u.correo = ? AND u.password = SHA2( ?, 256)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, usuario.getCorreo());
            st.setString(2, usuario.getPassword());
            rs = st.executeQuery();
            if(rs.next()){
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setNombre(rs.getString("Nombre"));
                user.setApellido(rs.getString("Apellido"));
                user.setTelefono(rs.getString("Telefono"));
                user.setDireccion(rs.getString("direccion"));
                user.setDui(rs.getString("DUI"));
                user.setCorreo(rs.getString("correo"));
                user.setIdConfirmacion(rs.getInt("confirmado"));
                user.setTipoUser(rs.getInt("id_tipo_usuario"));
                this.desconectar();
                return user;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
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
                pedido.setFechaCompra(rs.getString("fecha_compra"));
                pedido.setMontoTotal(rs.getString("monto_total"));
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

    public String obtenerNombreUsuario(int iduser) throws SQLException{
        try {
            String sql = "SELECT CONCAT(SUBSTRING_INDEX(Nombre, ' ', 1),' ',SUBSTRING_INDEX(Apellido, ' ', 1)) as Nombre FROM usuarios WHERE id_usuario = ?";
            String nombreuser="";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, iduser);
            rs = st.executeQuery();
            if(rs.next()){
                nombreuser= rs.getString("Nombre");
                this.desconectar();
                return nombreuser;
            }
            this.desconectar();
            return "";
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return "";
        }
    }
    
    //PARTE RAUDA
    public List<Usuario> listarUsuarios() throws SQLException {
        try {
            List<Usuario> listaUsuario = new ArrayList<>();
            this.conectar();
            String sql = "select * from usuarios where id_tipo_usuario=1 or id_tipo_usuario=3 or id_tipo_usuario=4 or id_tipo_usuario=5";
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setDui(rs.getString("DUI"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTipoUser(rs.getInt("id_tipo_usuario"));
                usuario.setConfirmado(rs.getString("confirmado"));
                listaUsuario.add(usuario);
            }
            this.desconectar();
            return listaUsuario;
        } catch (SQLException ex) {
            this.desconectar();
            return null;
        }
    }
    
    public Usuario obtenerUsuario(int id) throws SQLException{
        try{
            Usuario usuario = new Usuario();
            this.conectar();
            String sql = "select * from usuarios where id_usuario=?";
            st=conexion.prepareStatement(sql);
            st.setInt(1, id);
            rs=st.executeQuery();
            while(rs.next()){
                usuario.setIdUsuario(id);
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTipoUser(rs.getInt("id_tipo_usuario"));
            }
            this.desconectar();
            return usuario;
        } catch (SQLException ex) {
            this.desconectar();
            return null;
        }
    }
    
    public int modificarUsuario(Usuario usuario) throws SQLException{
        try{
            int filasAfectadas = 0;
            String sql = "update usuarios set Nombre=?,Apellido=?,Telefono=?,direccion=?,correo=?,id_tipo_usuario=? where id_usuario=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getApellido());
            st.setString(3, usuario.getTelefono());
            st.setString(4, usuario.getDireccion());
            st.setString(5, usuario.getCorreo());
            st.setInt(6, usuario.getTipoUser());
            st.setInt(7, usuario.getIdUsuario());
            filasAfectadas=st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            this.desconectar();
            return 0;
        }
    }
    
    public int deshabilitarUsuario(int id) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "UPDATE usuarios SET confirmado = 2 WHERE id_usuario = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, id);

            filasAfectadas = st.executeUpdate();

            this.desconectar();

            return filasAfectadas;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

    }
    
    public int habilitarUsuario(int id) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "UPDATE usuarios SET confirmado = 1 WHERE id_usuario = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, id);

            filasAfectadas = st.executeUpdate();

            this.desconectar();

            return filasAfectadas;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

    }
    //FIN PARTE RAUDA
    
    public List<Usuario> listaContactoDisponible() throws SQLException{
        try{
            List<Usuario> listaUsuario = new ArrayList<>();
            String sql="Select * from usuarios u left join empresa e on u.id_usuario=e.id_usuario where u.id_tipo_usuario = 5 and e.id_usuario is null";
            this.conectar();
            st = conexion.prepareStatement(sql);
            
            rs = st.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setDui(rs.getString("DUI"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTipoUser(rs.getInt("id_tipo_usuario"));
                usuario.setConfirmado(rs.getString("confirmado"));
                listaUsuario.add(usuario);
            }
            this.desconectar();
            return listaUsuario;          
            
        } catch (SQLException ex) {
            this.desconectar();
            return null;
        }
    }
}
