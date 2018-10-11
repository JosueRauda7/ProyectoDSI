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
import sv.edu.udb.www.beans.EstadoProducto;
import sv.edu.udb.www.beans.Producto;
import sv.edu.udb.www.beans.SubCategoria;

/**
 *
 * @author ivanm
 */
public class ProductosModel extends Conexion {
    public List<Producto> listarProducto(int usuario) throws SQLException{
        try {
            List<Producto> lista=new ArrayList<>();
            String sql="Select * from producto p inner join estado_producto es on p.id_estado_producto= es.id_estado_producto "
                    + "inner join sub_categoria c on p.id_sub_categoria=c.id_sub_categoria "
                    + "inner JOIN empresa e on p.id_empresa=e.id_empresa "
                     + "INNER JOIN usuarios u on e.id_usuario=u.id_usuario where u.id_usuario=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setInt(1, usuario);
            rs=st.executeQuery();
            while(rs.next()){
                Producto producto=new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setProducto(rs.getString("producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioRegular(rs.getDouble("precio_regular"));
                producto.setCantidad(rs.getInt("cantidad"));
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
}
