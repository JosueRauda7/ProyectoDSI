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
import sv.edu.udb.www.beans.Categoria;
import sv.edu.udb.www.beans.SubCategoria;
import sv.edu.udb.www.beans.EstadoCategoria;
import static sv.edu.udb.www.model.Conexion.conexion;

/**
 *
 * @author Ferh
 */
public class SubCategoriasModel extends Conexion {

    public List<SubCategoria> listarSubCategorias() throws SQLException {
        try {
            List<SubCategoria> lista = new ArrayList<>();
            sql = "SELECT t1.*, t2.*, t3.* FROM sub_categoria t1 INNER JOIN estado_sub_categoria t2 ON t1.id_estado_sub_categoria = t2.id_estado_sub_categoria  INNER JOIN categoria t3 ON t1.id_categoria=t3.id_categoria";
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                SubCategoria subCategoria = new SubCategoria();
                EstadoCategoria estado = new EstadoCategoria();
                Categoria categoria = new Categoria();
                subCategoria.setIdSubCategoria(rs.getInt("id_sub_categoria"));
                subCategoria.setSubCategoria(rs.getString("subcategoria"));
                estado.setIdEstadoCategoria(rs.getInt("id_estado_sub_categoria"));
                estado.setEstadoCategoria(rs.getString("estado_sub_categoria"));
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setCategoria(rs.getString("categoria"));
                subCategoria.setCategoria(categoria);
                subCategoria.setEstadoCategoria(estado);
                lista.add(subCategoria);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(SubCategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }

    }

    
    public int insertarSubCategoria(SubCategoria subCategoria) throws SQLException {

        try {
            int filasAfectadas = 0;
            sql = "INSERT INTO sub_categoria VALUES(null,?,?,1,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, subCategoria.getSubCategoria());
            st.setInt(2, subCategoria.getCategoria().getIdCategoria());
            st.setString(3, subCategoria.getUrlSubcategoria());
            filasAfectadas = st.executeUpdate();

            this.desconectar();

            return filasAfectadas;

        } catch (SQLException ex) {
            Logger.getLogger(SubCategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

    }

    public SubCategoria obtenerSubCategoria(String id) throws SQLException {
        try {
            sql = "SELECT t1.*, t2.*, t3.* FROM sub_categoria t1 INNER JOIN estado_sub_categoria t2 ON t1.id_estado_sub_categoria = t2.id_estado_sub_categoria  INNER JOIN categoria t3 ON t1.id_categoria=t3.id_categoria WHERE t1.id_sub_categoria=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                SubCategoria subCategoria = new SubCategoria();
                EstadoCategoria estado = new EstadoCategoria();
                Categoria categoria = new Categoria();
                subCategoria.setIdSubCategoria(rs.getInt("id_sub_categoria"));
                subCategoria.setSubCategoria(rs.getString("subcategoria"));
                subCategoria.setUrlSubcategoria(rs.getString("Urlsubcategoria"));
                estado.setIdEstadoCategoria(rs.getInt("id_estado_sub_categoria"));
                estado.setEstadoCategoria(rs.getString("estado_sub_categoria"));
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setCategoria(rs.getString("categoria"));
                subCategoria.setCategoria(categoria);
                subCategoria.setEstadoCategoria(estado);

                this.desconectar();
                return subCategoria;
            }

            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(SubCategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }

    }

    public int modificarSubCategoria(SubCategoria subCategoria) throws SQLException {
        try {
            int filasAfectadas = 0;
            if (subCategoria.getUrlSubcategoria() == null) {
                sql = "UPDATE sub_categoria SET subcategoria=?, id_categoria=? WHERE id_sub_categoria = ?";
                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setString(1, subCategoria.getSubCategoria());
                st.setInt(2, subCategoria.getCategoria().getIdCategoria());
                st.setInt(3, subCategoria.getIdSubCategoria());
            } else {
                sql = "UPDATE sub_categoria SET subcategoria=?, id_categoria=?, Urlsubcategoria = ? WHERE id_sub_categoria = ?";
                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setString(1, subCategoria.getSubCategoria());
                st.setInt(2, subCategoria.getCategoria().getIdCategoria());
                st.setString(3, subCategoria.getUrlSubcategoria());
                st.setInt(4, subCategoria.getIdSubCategoria());
            }

            filasAfectadas = st.executeUpdate();

            this.desconectar();

            return filasAfectadas;

        } catch (SQLException ex) {
            Logger.getLogger(SubCategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

    }

    public int deshabilitarSubCategoria(int id) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "UPDATE sub_categoria SET id_estado_sub_categoria = 2 WHERE id_sub_categoria = ?";
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

    public int habilitarSubCategoria(int id) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "UPDATE sub_categoria SET id_estado_sub_categoria = 1 WHERE id_sub_categoria = ?";
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

    public List<Categoria> listarCategorias() throws SQLException {
        try {
            List<Categoria> lista = new ArrayList<>();
            sql = "SELECT t1.*, t2.* FROM categoria t1 INNER JOIN estado_categoria t2 ON t2.id_estado_categoria=t1.id_estado_categoria WHERE t1.id_estado_categoria = 1";
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                EstadoCategoria estado = new EstadoCategoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setCategoria(rs.getString("categoria"));
                categoria.setUrlCategoria(rs.getString("Urlcategoria"));
                estado.setIdEstadoCategoria(rs.getInt("id_estado_categoria"));
                estado.setEstadoCategoria(rs.getString("estado_categoria"));

                categoria.setEstadoCategoria(estado);
                lista.add(categoria);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

     public List<SubCategoria> listarporCategoria(int valor) throws SQLException {
        try {
            List<SubCategoria> lista = new ArrayList<>();
            sql = "SELECT * FROM sub_categoria WHERE id_categoria =?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, valor);
            rs = st.executeQuery();
            while (rs.next()) {
                SubCategoria subCategoria = new SubCategoria();
                subCategoria.setIdSubCategoria(rs.getInt("id_sub_categoria"));
                subCategoria.setSubCategoria(rs.getString("subcategoria"));
                subCategoria.setUrlSubcategoria(rs.getString("Urlsubcategoria"));
                lista.add(subCategoria);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(SubCategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }

    }
}
