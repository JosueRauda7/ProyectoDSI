package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.DetallePedido;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.EstadoProducto;
import sv.edu.udb.www.beans.Oferta;
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
            String sql = "SELECT DISTINCT * FROM producto p INNER JOIN sub_categoria sc on p.id_sub_categoria = sc.id_sub_categoria INNER JOIN empresa em on p.id_empresa = em.id_empresa INNER JOIN estado_producto es on p.id_estado_producto = es.id_estado_producto INNER JOIN imagen i on i.id_producto = p.id_producto WHERE p.id_producto = ? GROUP by i.id_producto";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idpro);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                Empresa empresa = new Empresa();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setUrlImagen(rs.getString("Url_imagen"));
                empresa.setEmpresa(rs.getString("empresa"));
                empresa.setUrlEmpresa(rs.getString("Urlempresa"));
                producto.setEmpresa(empresa);
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

    public int agregarProducto(int iduser, int idproduct, int cantidad) throws SQLException {
        try {
            String sql = "SELECT MAX(id_pedido) AS pedido FROM pedidos WHERE id_usuario = ?";
            int pedido = 0;
            int filasAfectadas = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, iduser);
            rs = st.executeQuery();
            if (rs.next()) {
                pedido = rs.getInt("pedido");
            }
            String sql2 = "INSERT INTO detalle_pedidos(id_pedido, id_producto,cantidad) VALUES(?,?,?)";
            st = conexion.prepareStatement(sql2);
            st.setInt(1, pedido);
            st.setInt(2, idproduct);
            st.setInt(3, cantidad);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public List<DetallePedido> listaCarrito(int iduser) throws SQLException {
        try {
            List<DetallePedido> lista = new ArrayList<>();
            String sql = "SELECT MAX(id_pedido) AS pedido FROM pedidos WHERE id_usuario = ?";
            int pedido = 0;
            int filasAfectadas = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, iduser);
            rs = st.executeQuery();
            if (rs.next()) {
                pedido = rs.getInt("pedido");
            }
            String sql2 = "SELECT DISTINCT i.id_producto, dp.cantidad,p.id_producto, p.producto, i.Url_imagen,p.precio_regular FROM detalle_pedidos dp INNER JOIN producto p ON dp.id_producto = p.id_producto INNER JOIN pedidos pd ON dp.id_pedido = pd.id_pedido INNER JOIN imagen i ON i.id_producto = p.id_producto WHERE pd.id_pedido=? AND pd.id_estado_compra = 1 GROUP by i.id_producto ORDER BY dp.id_detalle_pedido DESC";
            st = conexion.prepareStatement(sql2);
            st.setInt(1, pedido);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                DetallePedido detalle = new DetallePedido();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setUrlImagen(rs.getString("Url_imagen"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                detalle.setProducto(producto);
                detalle.setCantidad(rs.getInt("cantidad"));
                lista.add(detalle);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
    public List<DetallePedido> listaCarritoOfertas(int iduser) throws SQLException {
        try {
            List<DetallePedido> lista = new ArrayList<>();
            String sql = "SELECT MAX(id_pedido) AS pedido FROM pedidos WHERE id_usuario = ?";
            int pedido = 0;
            int filasAfectadas = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, iduser);
            rs = st.executeQuery();
            if (rs.next()) {
                pedido = rs.getInt("pedido");
            }
            String sql2 = "SELECT o.*, dp.cantidad FROM detalle_pedidos dp INNER JOIN ofertas o on dp.id_oferta = o.id_oferta WHERE dp.id_pedido=? AND o.id_estado_oferta = 1 ORDER BY dp.id_detalle_pedido DESC";
            st = conexion.prepareStatement(sql2);
            st.setInt(1, pedido);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                DetallePedido detalle = new DetallePedido();
                oferta.setTitulo(rs.getString("titulo"));
                oferta.setDescripcion(rs.getString("descripcion"));
                
                detalle.setProducto(oferta);
                detalle.setCantidad(rs.getInt("cantidad"));
                lista.add(detalle);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public int cantidadProduct(int iduser) throws SQLException {
        try {
            String sql = "SELECT MAX(id_pedido) AS pedido FROM pedidos WHERE id_usuario = ?";
            int pedido = 0;
            int cantidad = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, iduser);
            rs = st.executeQuery();
            if (rs.next()) {
                pedido = rs.getInt("pedido");
            }
            String sql2 = "SELECT COUNT(id_detalle_pedido) AS cantidad FROM detalle_pedidos WHERE id_pedido=?";
            st = conexion.prepareStatement(sql2);
            st.setInt(1, pedido);
            rs = st.executeQuery();
            if (rs.next()) {
                cantidad = rs.getInt("cantidad");
            }
            this.desconectar();
            return cantidad;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
}
