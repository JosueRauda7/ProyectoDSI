/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Detalle;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.EstadoProducto;
import sv.edu.udb.www.beans.Imagen;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.beans.Producto;
import sv.edu.udb.www.beans.SubCategoria;
import sv.edu.udb.www.beans.Usuario;

/**
 *
 * @author ivanm
 */
public class ProductosModel extends Conexion {

    public List<Producto> listarProducto(int usuario, int estadoProducto) throws SQLException {
        try {

            List<Producto> lista = new ArrayList<>();
            String sql = "Select * from producto p inner join estado_producto es on p.id_estado_producto= es.id_estado_producto "
                    + "inner join sub_categoria c on p.id_sub_categoria=c.id_sub_categoria "
                    + "inner JOIN empresa e on p.id_empresa=e.id_empresa "
                    + "INNER JOIN usuarios u on e.id_usuario=u.id_usuario where u.id_usuario=? and es.id_estado_producto=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, usuario);
            st.setInt(2, estadoProducto);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setSubCategoria(new SubCategoria(rs.getString("subcategoria")));
                producto.setEstadoProducto(new EstadoProducto(rs.getString("estado")));
                lista.add(producto);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<Imagen> listarImagenesProducto() throws SQLException {
        try {

            List<Imagen> lista = new ArrayList<>();
            String sql = "Select * from Imagen Group by id_producto";

            this.conectar();
            st = conexion.prepareStatement(sql);

            rs = st.executeQuery();
            while (rs.next()) {
                Imagen imagen = new Imagen();
                imagen.setIdImagenProducto(rs.getInt("id_imagen_producto"));
                imagen.setUrlimagen(rs.getString("Url_imagen"));
                imagen.setIdProducto(rs.getInt("id_producto"));
                lista.add(imagen);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public int insertarProducto(Producto producto, int usuario, Imagen imagen, List imagenes, List conceptos, List atributos) throws SQLException {
        try {
            int empresa = 0;
            int filasAfectadas = 0;
            int idproducto = 0;
            String sql = "Select id_empresa from empresa where id_usuario=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, usuario);
            rs = st.executeQuery();
            while (rs.next()) {
                empresa = rs.getInt("id_empresa");
            }

            sql = "Insert into producto VALUES(NULL,?,?,?,?,?,?,1)";
            this.conectar();
            st = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, producto.getProducto());
            st.setString(2, producto.getDescripcion());
            st.setDouble(3, Double.parseDouble(producto.getPrecioRegular()));
            st.setInt(4, Integer.parseInt(producto.getCantidad()));
            st.setInt(5, producto.getIdsubCategoria());
            st.setInt(6, empresa);
            filasAfectadas = st.executeUpdate();
            if (filasAfectadas != 0) {
                rs = st.getGeneratedKeys();
                rs.next();
                idproducto = rs.getInt(1);
                sql = "Insert into imagen VALUES (NULL,?,?)";
                st = conexion.prepareStatement(sql);
                st.setString(1, imagen.getUrlimagen());
                st.setInt(2, idproducto);
                filasAfectadas = st.executeUpdate();

                if (!imagenes.isEmpty()) {
                    sql = "Insert into imagen VALUES (NULL,?,?)";
                    st = conexion.prepareStatement(sql);
                    for (int i = 0; i < imagenes.size(); i++) {
                        st.setString(1, imagenes.get(i).toString());
                        st.setInt(2, idproducto);
                        filasAfectadas = st.executeUpdate();
                    }
                }
                if (conceptos.size() > 0) {
                    int longitud = conceptos.size();
                    for (int i = 0; i < longitud; i++) {
                        sql = "Insert into detalles VALUES(NULL,?,?,?)";
                        st = conexion.prepareStatement(sql);
                        st.setString(1, conceptos.get(i).toString());
                        st.setString(2, atributos.get(i).toString());
                        st.setInt(3, idproducto);
                        filasAfectadas = st.executeUpdate();
                    }
                }
            }

            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public Producto obtenerProducto(int usuario, int codigo) {
        try {
            String sql = "Select * from producto p inner join estado_producto es on p.id_estado_producto= es.id_estado_producto "
                    + "inner join sub_categoria c on p.id_sub_categoria=c.id_sub_categoria "
                    + "inner JOIN empresa e on p.id_empresa=e.id_empresa "
                    + "INNER JOIN usuarios u on e.id_usuario=u.id_usuario where p.id_producto=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, codigo);
            rs = st.executeQuery();
            if (rs.next()) {
                Producto producto = new Producto();
                producto.setIdEmpresa(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setCantidad(rs.getString("cantidad"));

                producto.setSubCategoria(new SubCategoria(rs.getString("subcategoria")));
                producto.setEstadoProducto(new EstadoProducto(rs.getString("estado")));
                this.desconectar();
                return producto;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            try {

                Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
                this.desconectar();
                return null;
            } catch (SQLException ex1) {
                Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex1);
                return null;
            }
        }
    }

    public int modificarProducto(Producto producto, int codigo) throws SQLException {
        try {
            int filasAfectadas = 0;
            String sql = "Update producto set producto=?,descripcion=?,precio_regular=?,cantidad=?,"
                    + " id_sub_categoria=?, id_estado_producto=1 where id_producto=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, producto.getProducto());
            st.setString(2, producto.getDescripcion());
            st.setDouble(3, Double.parseDouble(producto.getPrecioRegular()));
            st.setInt(4, Integer.parseInt(producto.getCantidad()));
            st.setInt(6, producto.getIdsubCategoria());
            st.setInt(7, codigo);
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public int actualizarExistencias(Producto producto) throws SQLException {
        try {
            int filasAfectadas = 0;
            if (Integer.parseInt(producto.getCantidad()) <= 0) {
                return 0;
            }
            String sql = "Update producto set cantidad=(cantidad + ?) where id_producto=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(producto.getCantidad()));
            st.setInt(2, producto.getIdProducto());
            filasAfectadas = st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public List<Producto> listarProducto(int estadoProducto) throws SQLException {
        try {

            List<Producto> lista = new ArrayList<>();
            String sql = "Select *,CONCAT(LEFT(p.descripcion,100), '...') as descripciona from producto p inner join estado_producto es on p.id_estado_producto= es.id_estado_producto "
                    + "inner join sub_categoria c on p.id_sub_categoria=c.id_sub_categoria "
                    + "inner JOIN empresa e on p.id_empresa=e.id_empresa "
                    + " where es.id_estado_producto=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, estadoProducto);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripciona"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setEmpresa(new Empresa(rs.getString("empresa")));
                producto.setSubCategoria(new SubCategoria(rs.getString("subcategoria")));
                producto.setEstadoProducto(new EstadoProducto(rs.getString("estado")));
                lista.add(producto);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public int rechazarAceptarProducto(int codigo, int estado) throws SQLException {
        try {
            String sql = "Update producto set id_estado_producto=? where id_producto=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, estado);
            st.setInt(2, codigo);
            return st.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public List<Producto> listaUltimosProductos() throws SQLException {
        try {
            String sql = "SELECT DISTINCT i.id_producto, p.producto,p.descripcion, p.precio_regular, p.cantidad, i.Url_imagen, o.id_oferta FROM producto p INNER JOIN empresa e on p.id_empresa = e.id_empresa INNER JOIN sub_categoria s on p.id_sub_categoria = s.id_sub_categoria INNER JOIN imagen i ON i.id_producto= p.id_producto LEFT JOIN ofertas o ON p.id_producto = o.id_producto WHERE id_estado_producto=2 AND o.id_estado_oferta is null GROUP by i.id_producto ORDER by p.id_producto DESC LIMIT 6";
            List<Producto> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setUrlImagen(rs.getString("Url_imagen"));
                lista.add(producto);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProductosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<Imagen> otrasImagenesProducto(int idproducto, String urlimg) throws SQLException {
        try {
            String sql = "SELECT * FROM imagen WHERE id_producto = ? AND Url_imagen <> ? ORDER BY id_imagen_producto";
            List<Imagen> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idproducto);
            st.setString(2, urlimg);
            rs = st.executeQuery();
            while (rs.next()) {
                Imagen imagen = new Imagen();
                imagen.setIdImagenProducto(rs.getInt("id_imagen_producto"));
                imagen.setUrlimagen(rs.getString("Url_imagen"));
                imagen.setIdProducto(rs.getInt("id_producto"));
                lista.add(imagen);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProductosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<Producto> busquedaProductos(String nombre, String idCategoria) throws SQLException {
        try {

            List<Producto> lista = new ArrayList<>();
            if (idCategoria.equals("0")) {
                String sql = "Select DISTINCT * from producto p inner join estado_producto es on p.id_estado_producto= es.id_estado_producto "
                        + "inner join sub_categoria sc on p.id_sub_categoria=sc.id_sub_categoria inner join categoria c ON c.id_categoria = sc.id_categoria "
                        + "inner JOIN empresa e on p.id_empresa=e.id_empresa INNER JOIN imagen i ON i.id_producto= p.id_producto "
                        + " where es.id_estado_producto=2 and (p.producto LIKE '" + nombre + "%' OR sc.subcategoria LIKE '" + nombre + "%' OR c.categoria LIKE '" + nombre + "%') GROUP BY p.id_producto";
                this.conectar();
                st = conexion.prepareStatement(sql);

            } else {
                String sql = "Select DISTINCT * from producto p inner join estado_producto es on p.id_estado_producto= es.id_estado_producto "
                        + "inner join sub_categoria sc on p.id_sub_categoria=sc.id_sub_categoria inner join categoria c ON c.id_categoria = sc.id_categoria "
                        + "inner JOIN empresa e on p.id_empresa=e.id_empresa INNER JOIN imagen i ON i.id_producto= p.id_producto"
                        + " where es.id_estado_producto=2 and (p.producto LIKE '" + nombre + "%' OR sc.subcategoria LIKE '" + nombre + "%' OR c.categoria LIKE '" + nombre + "%') and c.id_categoria = ? GROUP BY p.id_producto";
                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setString(1, idCategoria);
            }

            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setUrlImagen(rs.getString("Url_imagen"));
                producto.setEmpresa(new Empresa(rs.getString("empresa")));
                producto.setSubCategoria(new SubCategoria(rs.getString("subcategoria")));
                producto.setEstadoProducto(new EstadoProducto(rs.getString("estado")));
                lista.add(producto);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<Producto> busquedaProductos1(int filter, String nombre, String idCategoria) throws SQLException {
        try {

            List<Producto> lista = new ArrayList<>();
            if (idCategoria.equals("0")) {
                String sql = "Select DISTINCT * from producto p inner join estado_producto es on p.id_estado_producto= es.id_estado_producto "
                        + "inner join sub_categoria sc on p.id_sub_categoria=sc.id_sub_categoria inner join categoria c ON c.id_categoria = sc.id_categoria "
                        + "inner JOIN empresa e on p.id_empresa=e.id_empresa INNER JOIN imagen i ON i.id_producto= p.id_producto "
                        + " where es.id_estado_producto=2 and (p.producto LIKE '" + nombre + "%' OR sc.subcategoria LIKE '" + nombre + "%' OR c.categoria LIKE '" + nombre + "%') GROUP BY p.id_producto LIMIT ?,9";
                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setInt(1, filter);

            } else {
                String sql = "Select DISTINCT * from producto p inner join estado_producto es on p.id_estado_producto= es.id_estado_producto "
                        + "inner join sub_categoria sc on p.id_sub_categoria=sc.id_sub_categoria inner join categoria c ON c.id_categoria = sc.id_categoria "
                        + "inner JOIN empresa e on p.id_empresa=e.id_empresa INNER JOIN imagen i ON i.id_producto= p.id_producto"
                        + " where es.id_estado_producto=2 and (p.producto LIKE '" + nombre + "%' OR sc.subcategoria LIKE '" + nombre + "%' OR c.categoria LIKE '" + nombre + "%') and c.id_categoria = ? GROUP BY p.id_producto LIMIT ?,9";
                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setString(1, idCategoria);
                st.setInt(2, filter);
            }

            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setUrlImagen(rs.getString("Url_imagen"));
                producto.setEmpresa(new Empresa(rs.getString("empresa")));
                producto.setSubCategoria(new SubCategoria(rs.getString("subcategoria")));
                producto.setEstadoProducto(new EstadoProducto(rs.getString("estado")));
                lista.add(producto);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    //PARTE RAUDA: LISTAR OFERTAS SIN PUBLICAR
    public List<Oferta> listarOfertasSinPublicar() throws SQLException {
        try {
            List<Oferta> listaOfertas = new ArrayList<>();
            String sql = "SELECT o.id_oferta, o.titulo, o.descripcion, o.url_foto, p.producto FROM ofertas o inner join producto p on o.id_producto=p.id_producto WHERE id_estado_oferta=1 AND estado_publicado = 0";
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(rs.getInt("id_oferta"));
                oferta.setTitulo(rs.getString("titulo"));
                oferta.setDescripcion(rs.getString("descripcion"));
                oferta.setUrlFoto(rs.getString("url_foto"));
                oferta.setNombreProducto(rs.getString("producto"));
                listaOfertas.add(oferta);
            }
            this.desconectar();
            return listaOfertas;
        } catch (SQLException ex) {
            this.desconectar();
            return null;
        }
    }

    public Oferta obtenerOfertaSinPublicar(int id) throws SQLException {
        try {
            String sql = "SELECT o.id_oferta, o.titulo, o.descripcion, o.url_foto, p.producto, p.id_producto FROM ofertas o inner join producto p on o.id_producto=p.id_producto WHERE id_estado_oferta=1 AND estado_publicado = 0 AND o.id_oferta = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Oferta oferta = new Oferta();
                Producto producto = new Producto();
                oferta.setIdOferta(rs.getInt("id_oferta"));
                oferta.setTitulo(rs.getString("titulo"));
                oferta.setDescripcion(rs.getString("descripcion"));
                oferta.setUrlFoto(rs.getString("url_foto"));

                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));

                oferta.setProducto(producto);

                this.desconectar();
                return oferta;
            }
            this.desconectar();
            return null;
        } catch (SQLException ex) {
            this.desconectar();
            return null;
        }
    }

    public int publicarOferta(int id) throws SQLException {
        try {
            String sql = "UPDATE ofertas SET estado_publicado = 1 WHERE id_oferta = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();

            this.desconectar();
            return 1;
        } catch (SQLException ex) {
            this.desconectar();
            return 0;
        }
    }

    public Producto obtenerProducto(int id) throws SQLException {
        try {
            String sql = "select u.correo, p.id_producto, p.producto, p.descripcion, p.cantidad, p.precio_regular, c.subcategoria, e.empresa, ep.estado, i.Url_imagen from producto p "
                    + "inner join sub_categoria c on p.id_sub_categoria=c.id_sub_categoria "
                    + "inner join estado_producto ep on p.id_estado_producto=ep.id_estado_producto "
                    + "inner join imagen i on i.id_producto=p.id_producto "
                    + "inner JOIN empresa e on p.id_empresa=e.id_empresa "
                    + "inner join usuarios u on e.id_usuario=u.id_usuario "
                    + "where p.id_producto=? limit 1";
            EstadoProducto estado = new EstadoProducto();
            SubCategoria subC = new SubCategoria();
            Empresa empresa = new Empresa();
            Usuario contacto = new Usuario();
            Producto producto = new Producto();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                producto.setIdProducto(Integer.parseInt(rs.getString("p.id_producto")));
                contacto.setCorreo(rs.getString("u.correo"));
                empresa.setEmpresa(rs.getString("e.empresa"));
                empresa.setUsuario(contacto);
                producto.setEmpresa(empresa);
                producto.setProducto(rs.getString("p.producto"));
                producto.setDescripcion(rs.getString("p.descripcion"));
                producto.setPrecioRegular(rs.getString("p.precio_regular"));
                producto.setCantidad(rs.getString("p.cantidad"));
                producto.setUrlImagen(rs.getString("i.Url_imagen"));
                producto.setSubCategoria(new SubCategoria(rs.getString("c.subcategoria")));
                producto.setEstadoProducto(new EstadoProducto(rs.getString("ep.estado")));
            }
            this.desconectar();
            return producto;
        } catch (SQLException ex) {
            try {
                Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
                this.desconectar();
                return null;
            } catch (SQLException ex1) {
                Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex1);
                this.desconectar();
                return null;
            }
        }
    }

    public List<Detalle> listarDetalles(int id) throws SQLException {
        try {
            String sql = "select * from detalles where idProducto=?";
            List<Detalle> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Detalle detalle = new Detalle();
                detalle.setIdDetalle(Integer.parseInt(rs.getString("idDetalle")));
                detalle.setDetalle(rs.getString("detalle"));
                detalle.setDetalleAtributo(rs.getString("detalleAtributo"));
                lista.add(detalle);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProductosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    //FIN PARTE RAUDA
}
