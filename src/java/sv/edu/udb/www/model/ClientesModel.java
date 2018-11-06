package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Comentario;
import sv.edu.udb.www.beans.DetallePedido;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.EstadoProducto;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.beans.Pedido;
import sv.edu.udb.www.beans.Producto;
import sv.edu.udb.www.beans.SubCategoria;
import sv.edu.udb.www.beans.Usuario;

public class ClientesModel extends Conexion {

    public int crearCarrito(Pedido pedido) throws SQLException {
        try {
            int filasAfectadas = 0;
            String sql = "INSERT INTO pedidos (fecha_compra,hora_compra, id_usuario,id_estado_compra) VALUES(?,?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, pedido.getFechaCompra());
            st.setString(2, pedido.getHoraCompra());
            st.setInt(3, pedido.getIdUsuario());
            st.setInt(4, pedido.getIdEstadoCompra());
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
            String sql = "SELECT MAX(id_pedido), id_estado_compra AS estado FROM pedidos WHERE id_usuario = ?";
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

    public List<Oferta> ultimasOfertas() throws SQLException {
        try {
            String sql = "SELECT id_oferta,titulo,id_producto, descripcion, total_descuento,descuento, Url_foto FROM ofertas WHERE id_estado_oferta = 1 ORDER BY id_oferta DESC LIMIT 6";
            List<Oferta> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                Producto producto = new Producto();
                oferta.setIdOferta(rs.getInt("id_oferta"));
                oferta.setTitulo(rs.getString("titulo"));
                oferta.setDescripcion(rs.getString("descripcion"));
                oferta.setTotalDescuento(rs.getDouble("total_descuento"));
                oferta.setDescuento(rs.getInt("descuento"));
                oferta.setUrlFoto(rs.getString("Url_foto"));
                producto.setIdProducto(rs.getInt("id_producto"));
                oferta.setProducto(producto);
                lista.add(oferta);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProductosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        } finally {
            this.desconectar();
        }
    }

    public List<Producto> productosRelacionados(int idproducto) throws SQLException {
        try {
            String sql = "SELECT id_sub_categoria FROM producto WHERE id_producto = ?";
            this.conectar();
            int idsubcat = 0;
            st = conexion.prepareStatement(sql);
            st.setInt(1, idproducto);
            rs = st.executeQuery();
            if (rs.next()) {
                idsubcat = rs.getInt("id_sub_categoria");
            }
            String sql2 = "SELECT DISTINCT p.id_producto ,p.producto, CONCAT(LEFT(p.descripcion,100), '...') AS descripcion, i.Url_imagen FROM producto p INNER JOIN imagen i ON p.id_producto = i.id_producto WHERE id_sub_categoria = ? AND p.id_producto <> ? GROUP by i.id_producto";
            List<Producto> lista = new ArrayList<>();
            st = conexion.prepareStatement(sql2);
            st.setInt(1, idsubcat);
            st.setInt(2, idproducto);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setUrlImagen(rs.getString("Url_imagen"));
                lista.add(producto);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public Producto verProducto(int idpro) throws SQLException {
        try {
            this.conectar();
            String sql = "SELECT DISTINCT * FROM producto p INNER JOIN sub_categoria sc on p.id_sub_categoria = sc.id_sub_categoria INNER JOIN empresa em on p.id_empresa = em.id_empresa INNER JOIN estado_producto es on p.id_estado_producto = es.id_estado_producto INNER JOIN imagen i on i.id_producto = p.id_producto WHERE p.id_producto = ? GROUP by i.id_producto";
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

    public Oferta ofertaProducto(int idpro) throws SQLException {
        try {
            String sql2 = "SELECT DISTINCT o.id_oferta,o.total_descuento,o.descuento,p.id_producto,p.producto,p.descripcion,p.precio_regular,p.cantidad,sc.subcategoria,i.Url_imagen,em.Urlempresa,em.empresa FROM ofertas o INNER JOIN producto p ON o.id_producto = p.id_producto INNER JOIN sub_categoria sc ON p.id_sub_categoria = sc.id_sub_categoria INNER JOIN empresa em ON p.id_empresa = em.id_empresa INNER JOIN imagen i on i.id_producto = p.id_producto WHERE p.id_producto = ? AND o.id_estado_oferta =1 GROUP by i.id_producto";
            this.conectar();
            st = conexion.prepareStatement(sql2);
            st.setInt(1, idpro);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
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
                oferta.setIdOferta(rs.getInt("id_oferta"));
                oferta.setTotalDescuento(rs.getDouble("total_descuento"));
                oferta.setDescuento(rs.getInt("descuento"));
                oferta.setProducto(producto);
                this.desconectar();
                return oferta;
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
            int countproduc = 0;
            int existencias = 0;
            int dpcantidad = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, iduser);
            rs = st.executeQuery();
            if (rs.next()) {
                pedido = rs.getInt("pedido");
            }
            String sql2 = "SELECT p.cantidad, COUNT(dp.id_producto) AS countproduct, dp.cantidad as dpcantidad FROM detalle_pedidos dp INNER JOIN producto p ON dp.id_producto = p.id_producto WHERE id_pedido =? AND dp.id_producto =?";
            st = conexion.prepareStatement(sql2);
            st.setInt(1, pedido);
            st.setInt(2, idproduct);
            rs = st.executeQuery();
            if (rs.next()) {
                countproduc = rs.getInt("countproduct");
                existencias = rs.getInt("cantidad");
                dpcantidad = rs.getInt("dpcantidad");
            }
            if (existencias <= 0 || existencias < cantidad) {
                return 0;
            } else {
                if (countproduc == 0) {
                    String sql3 = "INSERT INTO detalle_pedidos(id_pedido, id_producto,cantidad) VALUES(?,?,?)";
                    st = conexion.prepareStatement(sql3);
                    st.setInt(1, pedido);
                    st.setInt(2, idproduct);
                    st.setInt(3, cantidad);
                    st.executeUpdate();
                    String sql4 = "UPDATE producto SET cantidad = ? WHERE id_producto =?";
                    st = conexion.prepareStatement(sql4);
                    st.setInt(1, existencias - cantidad);
                    st.setInt(2, idproduct);
                    filasAfectadas = st.executeUpdate();
                    return filasAfectadas;
                } else {
                    String sql3 = "UPDATE detalle_pedidos SET cantidad = ? WHERE id_pedido = ? AND id_producto = ?";
                    st = conexion.prepareStatement(sql3);
                    st.setInt(1, dpcantidad + cantidad);
                    st.setInt(2, pedido);
                    st.setInt(3, idproduct);
                    st.executeUpdate();
                    String sql4 = "UPDATE producto SET cantidad = ? WHERE id_producto =?";
                    st = conexion.prepareStatement(sql4);
                    st.setInt(1, existencias - cantidad);
                    st.setInt(2, idproduct);
                    return filasAfectadas = st.executeUpdate();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        } finally {
            this.desconectar();
        }
    }

    public int agregarOferta(int iduser, int idoferta, int cantidad) throws SQLException {
        try {
            String sql = "SELECT MAX(id_pedido) AS pedido FROM pedidos WHERE id_usuario = ?";
            int pedido = 0;
            int filasAfectadas = 0;
            int countofert = 0;
            int existencias = 0;
            int dpcantidad = 0;
            int producto = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, iduser);
            rs = st.executeQuery();
            if (rs.next()) {
                pedido = rs.getInt("pedido");
            }
            String sql2 = "SELECT p.cantidad, COUNT(dp.id_oferta) AS countofert, dp.cantidad as dpcantidad, p.id_producto FROM detalle_pedidos dp INNER JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN producto p ON o.id_producto = p.id_producto WHERE id_pedido =? AND dp.id_oferta = ?";
            st = conexion.prepareStatement(sql2);
            st.setInt(1, pedido);
            st.setInt(2, idoferta);
            rs = st.executeQuery();
            if (rs.next()) {
                countofert = rs.getInt("countofert");
                existencias = rs.getInt("cantidad");
                dpcantidad = rs.getInt("dpcantidad");
                producto = rs.getInt("id_producto");
            }
            if (existencias <= 0 || existencias < cantidad) {
                return 0;
            } else {
                if (countofert == 0) {
                    String sql3 = "INSERT INTO detalle_pedidos(id_pedido, id_oferta,cantidad) VALUES(?,?,?)";
                    st = conexion.prepareStatement(sql3);
                    st.setInt(1, pedido);
                    st.setInt(2, idoferta);
                    st.setInt(3, cantidad);
                    st.executeUpdate();
                    String sql4 = "UPDATE producto SET cantidad = ? WHERE id_producto =?";
                    st = conexion.prepareStatement(sql4);
                    st.setInt(1, existencias - cantidad);
                    st.setInt(2, producto);
                    filasAfectadas = st.executeUpdate();
                    return filasAfectadas;
                } else {
                    String sql3 = "UPDATE detalle_pedidos SET cantidad = ? WHERE id_pedido = ? AND id_oferta = ?";
                    st = conexion.prepareStatement(sql3);
                    st.setInt(1, dpcantidad + cantidad);
                    st.setInt(2, pedido);
                    st.setInt(3, idoferta);
                    st.executeUpdate();
                    String sql4 = "UPDATE producto SET cantidad = ? WHERE id_producto =?";
                    st = conexion.prepareStatement(sql4);
                    st.setInt(1, existencias - cantidad);
                    st.setInt(2, producto);
                    return filasAfectadas = st.executeUpdate();
                }
            }
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
            String sql2 = "SELECT DISTINCT i.id_producto, dp.cantidad,dp.id_detalle_pedido,p.id_producto, p.producto, i.Url_imagen,p.precio_regular, p.descripcion, sc.subcategoria, em.empresa,em.Urlempresa FROM detalle_pedidos dp INNER JOIN producto p ON dp.id_producto = p.id_producto INNER JOIN pedidos pd ON dp.id_pedido = pd.id_pedido INNER JOIN imagen i ON i.id_producto = p.id_producto INNER JOIN sub_categoria sc on p.id_sub_categoria = sc.id_sub_categoria INNER JOIN empresa em ON p.id_empresa = em.id_empresa WHERE pd.id_pedido=? AND pd.id_estado_compra = 1 GROUP by i.id_producto ORDER BY dp.id_detalle_pedido DESC";
            st = conexion.prepareStatement(sql2);
            st.setInt(1, pedido);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                Empresa empresa = new Empresa();
                DetallePedido detalle = new DetallePedido();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setUrlImagen(rs.getString("Url_imagen"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setSubCategoria(new SubCategoria(rs.getString("subcategoria")));
                empresa.setEmpresa(rs.getString("empresa"));
                empresa.setUrlEmpresa(rs.getString("Urlempresa"));
                producto.setEmpresa(empresa);
                detalle.setProducto(producto);
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setIdDetallePedido(rs.getInt("id_detalle_pedido"));
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
            String sql2 = "SELECT o.*, dp.cantidad,dp.id_detalle_pedido FROM detalle_pedidos dp INNER JOIN ofertas o on dp.id_oferta = o.id_oferta INNER JOIN pedidos pd ON dp.id_pedido = pd.id_pedido WHERE dp.id_pedido=? AND o.id_estado_oferta = 1 AND pd.id_estado_compra =1 ORDER BY dp.id_detalle_pedido DESC";
            st = conexion.prepareStatement(sql2);
            st.setInt(1, pedido);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                DetallePedido detalle = new DetallePedido();
                Producto producto = new Producto();
                oferta.setIdOferta(rs.getInt("id_oferta"));
                oferta.setTitulo(rs.getString("titulo"));
                producto.setIdProducto(rs.getInt("id_producto"));
                oferta.setProducto(producto);
                oferta.setDescripcion(rs.getString("descripcion"));
                oferta.setFechaInicio(rs.getString("fecha_inicio"));
                oferta.setFechaFin(rs.getString("fecha_fin"));
                oferta.setDescuento(rs.getInt("descuento"));
                oferta.setTotalDescuento(rs.getDouble("total_descuento"));
                oferta.setUrlFoto(rs.getString("Url_foto"));
                detalle.setOferta(oferta);
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setIdDetallePedido(rs.getInt("id_detalle_pedido"));
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
            String sql2 = "SELECT COUNT(id_detalle_pedido) AS cantidad FROM detalle_pedidos dp INNER JOIN pedidos pd ON dp.id_pedido = pd.id_pedido WHERE pd.id_pedido=? AND pd.id_estado_compra = 1";
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

    public int eliminarProductoCarrito(int iddetalle) throws SQLException {
        try {
            String sql2 = "SELECT dp.id_producto, dp.cantidad, p.cantidad AS cantidadpro FROM detalle_pedidos dp INNER JOIN producto p ON dp.id_producto = p.id_producto WHERE dp.id_detalle_pedido = ?";
            int cantidad = 0;
            int producto = 0;
            int cantidadpro = 0;
            this.conectar();
            st = conexion.prepareStatement(sql2);
            st.setInt(1, iddetalle);
            rs = st.executeQuery();
            if (rs.next()) {
                cantidad = rs.getInt("cantidad");
                producto = rs.getInt("id_producto");
                cantidadpro = rs.getInt("cantidadpro");
            }
            String sql3 = "UPDATE producto SET cantidad =? WHERE id_producto = ?";
            st = conexion.prepareStatement(sql3);
            st.setInt(1, cantidadpro + cantidad);
            st.setInt(2, producto);
            st.executeUpdate();
            String sql = "DELETE FROM detalle_pedidos WHERE id_detalle_pedido = ?";
            int filasAfectadas = 0;
            st = conexion.prepareStatement(sql);
            st.setInt(1, iddetalle);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public int eliminarOferta(int iddetalle) throws SQLException {
        try {
            String sql = "SELECT p.id_producto, dp.cantidad, p.cantidad AS cantidadpro FROM detalle_pedidos dp INNER JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN producto p ON o.id_producto= p.id_producto WHERE dp.id_detalle_pedido = ?";
            int cantidad = 0;
            int producto = 0;
            int cantidadpro = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, iddetalle);
            rs = st.executeQuery();
            if (rs.next()) {
                cantidad = rs.getInt("cantidad");
                producto = rs.getInt("id_producto");
                cantidadpro = rs.getInt("cantidadpro");
            }
            String sql3 = "UPDATE producto SET cantidad =? WHERE id_producto = ?";
            st = conexion.prepareStatement(sql3);
            st.setInt(1, cantidadpro + cantidad);
            st.setInt(2, producto);
            st.executeUpdate();
            String sql4 = "DELETE FROM detalle_pedidos WHERE id_detalle_pedido = ?";
            int filasAfectadas = 0;
            st = conexion.prepareStatement(sql4);
            st.setInt(1, iddetalle);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public String totalPedido(int iduser) {
        try {
            String sql = "SELECT MAX(id_pedido) AS pedido FROM pedidos WHERE id_usuario = ?";
            int pedido = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, iduser);
            rs = st.executeQuery();
            if (rs.next()) {
                pedido = rs.getInt("pedido");
            }
            String sql2 = "SELECT ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total FROM detalle_pedidos dp LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on dp.id_pedido = pd.id_pedido WHERE dp.id_pedido = ? AND pd.id_estado_compra =1";
            this.conectar();
            String total = "";
            st = conexion.prepareStatement(sql2);
            st.setInt(1, pedido);
            rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getString("total");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            return "0";
        }
    }

    public int cantidadProducto(int cantidad, int iddetalle, int idproduct) throws SQLException {
        try {
            String sql2 = "SELECT cantidad FROM detalle_pedidos WHERE id_detalle_pedido = ?";
            int cantidadact = 0;
            int cantidadpro = 0;
            int diferencia = 0;
            int validador = 0;
            this.conectar();
            st = conexion.prepareStatement(sql2);
            st.setInt(1, iddetalle);
            rs = st.executeQuery();
            if (rs.next()) {
                cantidadact = rs.getInt("cantidad");
            }
            diferencia = cantidadact - cantidad;
            String sql3 = "SELECT cantidad FROM producto WHERE id_producto =?";
            st = conexion.prepareStatement(sql3);
            st.setInt(1, idproduct);
            rs = st.executeQuery();
            if (rs.next()) {
                cantidadpro = rs.getInt("cantidad");
            }
            validador = diferencia + (cantidadpro);
            if (validador < 0) {
                return 0;
            } else {
                String sql4 = "UPDATE producto SET cantidad =? WHERE id_producto = ?";
                st = conexion.prepareStatement(sql4);
                st.setInt(1, diferencia + (cantidadpro));
                st.setInt(2, idproduct);
                st.executeUpdate();
                String sql = "UPDATE detalle_pedidos SET cantidad = ? WHERE id_detalle_pedido = ?";
                int filasAfectadas = 0;
                st = conexion.prepareStatement(sql);
                st.setInt(1, cantidad);
                st.setInt(2, iddetalle);
                filasAfectadas = st.executeUpdate();
                this.desconectar();
                return filasAfectadas;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        } finally {
            this.desconectar();
        }
    }

    public int cantidadOferta(int cantidad, int iddetalle, int idproduct) throws SQLException {
           try {
            String sql2 = "SELECT cantidad FROM detalle_pedidos WHERE id_detalle_pedido = ?";
            int cantidadact = 0;
            int cantidadpro = 0;
            int diferencia = 0;
            int validador = 0;
            this.conectar();
            st = conexion.prepareStatement(sql2);
            st.setInt(1, iddetalle);
            rs = st.executeQuery();
            if (rs.next()) {
                cantidadact = rs.getInt("cantidad");
            }
            diferencia = cantidadact - cantidad;
            String sql3 = "SELECT cantidad FROM producto WHERE id_producto =?";
            st = conexion.prepareStatement(sql3);
            st.setInt(1, idproduct);
            rs = st.executeQuery();
            if (rs.next()) {
                cantidadpro = rs.getInt("cantidad");
            }
            validador = diferencia + (cantidadpro);
            if (validador < 0) {
                return 0;
            } else {
                String sql4 = "UPDATE producto SET cantidad =? WHERE id_producto = ?";
                st = conexion.prepareStatement(sql4);
                st.setInt(1, diferencia + (cantidadpro));
                st.setInt(2, idproduct);
                st.executeUpdate();
                String sql = "UPDATE detalle_pedidos SET cantidad = ? WHERE id_detalle_pedido = ?";
                int filasAfectadas = 0;
                st = conexion.prepareStatement(sql);
                st.setInt(1, cantidad);
                st.setInt(2, iddetalle);
                filasAfectadas = st.executeUpdate();
                this.desconectar();
                return filasAfectadas;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        } finally {
            this.desconectar();
        }
    }

    public int agregarComentario(Comentario comentario) throws SQLException {
        try {
            String sql = "INSERT INTO comentarios(hora_comentario, fecha_comentario, Comentario, id_producto,id_usuario) VALUES(?,?,?,?,?)";
            int filasAfectadas = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, comentario.getHoraComentario());
            st.setString(2, comentario.getFechaComentario());
            st.setString(3, comentario.getComentario());
            st.setInt(4, comentario.getIdProducto());
            st.setInt(5, comentario.getIdUsuario());
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public List<Comentario> listaComentarios(int idproduct) throws SQLException {
        try {
            String sql = "SELECT c.id_comentario,c.fecha_comentario,c.Comentario,c.id_producto,time_format(c.hora_comentario, \"%H:%i\") AS hora, CONCAT(SUBSTRING_INDEX(u.Nombre, ' ', 1),' ',SUBSTRING_INDEX(u.Apellido, ' ', 1)) as Nombre, u.id_usuario FROM comentarios c INNER JOIN usuarios u ON c.id_usuario = u.id_usuario WHERE id_producto = ?";
            List<Comentario> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idproduct);
            rs = st.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                Comentario comentario = new Comentario();
                comentario.setIdComentario(rs.getInt("id_comentario"));
                comentario.setComentario(rs.getString("Comentario"));
                comentario.setHoraComentario(rs.getString("hora"));
                comentario.setFechaComentario(rs.getString("fecha_comentario"));
                comentario.setIdProducto(rs.getInt("id_producto"));
                comentario.setUsuario(usuario);
                lista.add(comentario);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public int eliminarComentario(int idcomentario) throws SQLException {
        try {
            String sql = "DELETE FROM comentarios WHERE id_comentario = ?";
            int filasAfectadas = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idcomentario);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public int modificarComentario(int idcomentario, String comentario) throws SQLException {
        try {
            String sql = "UPDATE comentarios SET Comentario = ?  WHERE id_comentario = ?";
            int filasAfectadas = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, comentario);
            st.setInt(2, idcomentario);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public int countProducto(int idsucat,double precio1, double precio2) throws SQLException {
        try {
            String sql = "SELECT COUNT(p.id_producto) as filas FROM producto p LEFT JOIN ofertas o on o.id_producto = p.id_producto WHERE id_sub_categoria = ? AND o.id_oferta is null AND p.precio_regular BETWEEN ? AND ?";
            int filas = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idsucat);
            st.setDouble(2, precio1);
            st.setDouble(3, precio2);
            rs = st.executeQuery();
            if (rs.next()) {
                filas = rs.getInt("filas");
            }
            return filas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
    
      public int countOferta(int idsucat,double precio1, double precio2) throws SQLException {
        try {
            String sql = "SELECT COUNT(o.id_oferta) AS filas FROM ofertas o INNER JOIN producto p on o.id_producto = p.id_producto WHERE o.id_estado_oferta= 1 AND p.id_sub_categoria = ? AND o.total_descuento BETWEEN ? AND ?";
            int filas = 0;
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idsucat);
            st.setDouble(2, precio1);
            st.setDouble(3, precio2);
            rs = st.executeQuery();
            if (rs.next()) {
                filas = rs.getInt("filas");
            }
            return filas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public List<Producto> listaProductosSubCat(int filter, int idsubcat,double precio1, double precio2) throws SQLException {
        try {
            String sql = "SELECT DISTINCT i.id_producto, p.producto, p.precio_regular, p.cantidad, i.Url_imagen, o.id_oferta FROM producto p INNER JOIN imagen i ON i.id_producto= p.id_producto LEFT JOIN ofertas o ON p.id_producto = o.id_producto WHERE id_estado_producto=2 AND o.id_estado_oferta is null AND p.id_sub_categoria = ? AND p.precio_regular BETWEEN ? AND ? GROUP by i.id_producto ORDER by p.id_producto LIMIT ?,9";
            List<Producto> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idsubcat);
            st.setDouble(2, precio1);
            st.setDouble(3, precio2);
            st.setInt(4, filter);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setUrlImagen(rs.getString("Url_imagen"));
                lista.add(producto);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<Oferta> listaOfertasSubCat(int filter, int idsubcat,double precio1, double precio2) throws SQLException {
        try {
            String sql = "SELECT o.id_oferta, o.id_producto, o.titulo, o.Url_foto, o.total_descuento FROM ofertas o INNER JOIN producto p on o.id_producto = p.id_producto WHERE o.id_estado_oferta= 1 AND p.id_sub_categoria = ? AND o.total_descuento BETWEEN ? AND ? LIMIT ?,9";
            List<Oferta> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idsubcat);
             st.setDouble(2, precio1);
            st.setDouble(3, precio2);
            st.setInt(4, filter);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                Producto producto = new Producto();
                oferta.setIdOferta(rs.getInt("id_oferta"));
                oferta.setTitulo(rs.getString("titulo"));
                oferta.setTotalDescuento(rs.getDouble("total_descuento"));
                oferta.setUrlFoto(rs.getString("Url_foto"));
                producto.setIdProducto(rs.getInt("id_producto"));
                oferta.setProducto(producto);
                lista.add(oferta);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
}
