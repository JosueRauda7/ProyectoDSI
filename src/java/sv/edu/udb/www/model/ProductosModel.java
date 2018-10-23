/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.EstadoProducto;
import sv.edu.udb.www.beans.Producto;
import sv.edu.udb.www.beans.SubCategoria;

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
                producto.setUrlImagen(rs.getString("url_imagen"));

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

    public int insertarProducto(Producto producto, int usuario) throws SQLException {
        try {
            int empresa=0;
            int filasAfectadas = 0;
            
            String sql = "Select id_empresa from empresa where id_usuario=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, usuario);
            rs = st.executeQuery();
            while(rs.next()){
                empresa = rs.getInt("id_empresa");
            }
            
            sql = "Insert into producto VALUES(NULL,?,?,?,?,?,?,?,1)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, producto.getProducto());
            st.setString(2, producto.getDescripcion());
            st.setDouble(3, Double.parseDouble(producto.getPrecioRegular()));
            st.setInt(4, Integer.parseInt(producto.getCantidad()));
            st.setString(5, producto.getUrlImagen());
            st.setInt(6, producto.getIdsubCategoria());
            st.setInt(7, empresa);
            filasAfectadas = st.executeUpdate();
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
                producto.setUrlImagen(rs.getString("url_imagen"));

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
                    + "url_imagen=?, id_sub_categoria=?, id_estado_producto=1 where id_producto=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, producto.getProducto());
            st.setString(2, producto.getDescripcion());
            st.setDouble(3, Double.parseDouble(producto.getPrecioRegular()));
            st.setInt(4, Integer.parseInt(producto.getCantidad()));
            st.setString(5, producto.getUrlImagen());
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

    public List<Producto> listarProducto(int estadoProducto) throws SQLException {
        try {

            List<Producto> lista = new ArrayList<>();
            String sql = "Select * from producto p inner join estado_producto es on p.id_estado_producto= es.id_estado_producto "
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
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioRegular(rs.getString("precio_regular"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setUrlImagen(rs.getString("url_imagen"));
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
            String sql = "SELECT DISTINCT i.id_producto, p.producto,p.descripcion, p.precio_regular, p.cantidad, i.Url_imagen FROM producto p INNER JOIN empresa e on p.id_empresa = e.id_empresa INNER JOIN sub_categoria s on p.id_sub_categoria = s.id_sub_categoria INNER JOIN imagen i ON i.id_producto= p.id_producto WHERE id_estado_producto=2 GROUP by i.id_producto ORDER by p.id_producto DESC LIMIT 6";
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
    
    public List<Producto> busquedaProductos(String nombre) throws SQLException {
        try {

            List<Producto> lista = new ArrayList<>();
            String sql = "Select * from producto p inner join estado_producto es on p.id_estado_producto= es.id_estado_producto "
                    + "inner join sub_categoria c on p.id_sub_categoria=c.id_sub_categoria "
                    + "inner JOIN empresa e on p.id_empresa=e.id_empresa "
                    + " where es.id_estado_producto=2 and p.producto LIKE '"+nombre+"%'";
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
                producto.setUrlImagen(rs.getString("url_imagen"));
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
}
