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
import static sv.edu.udb.www.model.Conexion.conexion;

/**
 *
 * @author ivanm
 */
public class EmpresasModel extends Conexion {
    public List<Producto> listarLibros() throws SQLException{
        try {
            List<Producto> lista=new ArrayList<>();
            String sql="Select * from producto p inner join sub_categoria sc on p.id_sub_categoria=sc.id_sub_categoria "
                     + "inner join estado_producto ep on p.id_estado_producto=ep.id_estado_producto";
            this.conectar();
            st=conexion.prepareStatement(sql);
            rs=st.executeQuery();
            while(rs.next()){
                Producto producto=new Producto();
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
    
    public int insertarProducto (Producto producto, int empresa) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql="Insert into producto VALUES(NULL,?,?,?,?,?,?,?,1)";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1,producto.getProducto());
            st.setString(2,producto.getDescripcion());
            st.setDouble(3,Double.parseDouble(producto.getPrecioRegular()));
            st.setInt(4, Integer.parseInt(producto.getCantidad()));
            st.setString(5,producto.getUrlImagen());
            st.setInt(6,empresa);
            st.setInt(7,producto.getIdestadoProducto());            
            filasAfectadas=st.executeUpdate();
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
    
    public Producto obtenerProducto(int codigo){
        try {
            String sql ="Select * from producto where id_producto=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setInt(1,codigo);
            rs=st.executeQuery();
            if(rs.next()){
                Producto producto = new Producto();
                /*producto.setCodigoLibro(rs.getString("codigo_libro"));
                producto.setNombreLibro(rs.getString("nombre_libro"));
                producto.setExistencias(rs.getInt("existencias"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCodigoAutor(rs.getString("codigo_autor"));
                producto.setCodigoEditorial(rs.getString("codigo_editorial"));
                producto.setidGenero(rs.getInt("id_genero"));
                producto.setDescripcion(rs.getString("descripcion")); */
                
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
    
    public int modificarProducto(Producto producto) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql="Update libros set nombre_libro=?,existencias=?,precio=?,codigo_autor=?,"
                       +"codigo_editorial=?, id_genero=?, descripcion=? where codigo_libro=?";
            this.conectar();/*
            st=conexion.prepareCall(sql);
            st.setString(1, libro.getNombreLibro());
            st.setInt(2, libro.getExistencias());
            st.setDouble(3, libro.getPrecio());
            st.setString(4, libro.getCodigoAutor());
            st.setString(5, libro.getCodigoEditorial());
            st.setInt(6, libro.getidGenero());
            st.setString(7, libro.getDescripcion());
            st.setString(8, libro.getCodigoLibro());
            filasAfectadas=st.executeUpdate();*/
            this.desconectar();
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
}
