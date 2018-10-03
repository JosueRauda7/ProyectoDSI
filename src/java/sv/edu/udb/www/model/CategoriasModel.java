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
import sv.edu.udb.www.beans.EstadoCategoria;

/**
 *
 * @author Ferh
 */
public class CategoriasModel extends Conexion{
    
    public List<Categoria> listarCategorias() throws SQLException {
        try {
            List<Categoria> lista = new ArrayList<>();
            sql = "SELECT t1.*, t2.* FROM categoria t1 INNER JOIN estado_categoria t2";
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                EstadoCategoria estado = new EstadoCategoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setCategoria(rs.getString("categoria"));
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

    public int insertarCategoria(Categoria categoria) throws SQLException {

        try {
            int filasAfectadas = 0;
            sql = "INSERT INTO categoria VALUES(null,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, categoria.getCategoria());
            st.setInt(2, categoria.getEstadoCategoria().getIdEstadoCategoria());
            filasAfectadas = st.executeUpdate();

            this.desconectar();

            return filasAfectadas;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

    }

    public Categoria obtenerCategoria(String id) throws SQLException {
        try {
            sql = "SELECT t1.*, t2.* FROM categoria t1 INNER JOIN estado_categoria t2 WHERE t1.id_categoria=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Categoria categoria = new Categoria();
                EstadoCategoria estado = new EstadoCategoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setCategoria(rs.getString("categoria"));
                estado.setIdEstadoCategoria(rs.getInt("id_estado_categoria"));
                estado.setEstadoCategoria(rs.getString("estado_categoria"));
                categoria.setEstadoCategoria(estado);                
                
                this.desconectar();
                return categoria;
            }

            this.desconectar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }

    }

    public int modificarCategoria(Categoria categoria) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "UPDATE categoria SET categoria=? AND id_estado_categoria = ? WHERE id_categoria = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, categoria.getCategoria());
            st.setInt(2, categoria.getEstadoCategoria().getIdEstadoCategoria());
            st.setInt(3, categoria.getIdCategoria());
            filasAfectadas = st.executeUpdate();

            this.desconectar();

            return filasAfectadas;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

    }
    
    public int eliminarCategoria(int id) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "DELETE FROM categoria WHERE id_categoria = ?";
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
    
}
