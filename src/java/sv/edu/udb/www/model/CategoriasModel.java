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
import sv.edu.udb.www.beans.Oferta;

/**
 *
 * @author Ferh
 */
public class CategoriasModel extends Conexion {

    public List<Categoria> listarCategorias() throws SQLException {
        try {
            List<Categoria> lista = new ArrayList<>();
            sql = "SELECT t1.*, t2.* FROM categoria t1 INNER JOIN estado_categoria t2 ON t2.id_estado_categoria=t1.id_estado_categoria";
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

    public int insertarCategoria(Categoria categoria) throws SQLException {

        try {
            int filasAfectadas = 0;
            sql = "INSERT INTO categoria VALUES(null,?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, categoria.getCategoria());
            st.setInt(2, categoria.getEstadoCategoria().getIdEstadoCategoria());
            st.setString(3, categoria.getUrlCategoria());
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
            sql = "SELECT t1.*, t2.* FROM categoria t1 INNER JOIN estado_categoria t2 ON t2.id_estado_categoria=t1.id_estado_categoria WHERE t1.id_categoria=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Categoria categoria = new Categoria();
                EstadoCategoria estado = new EstadoCategoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setCategoria(rs.getString("categoria"));
                categoria.setUrlCategoria(rs.getString("Urlcategoria"));
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
            if (categoria.getUrlCategoria() == null) {
                sql = "UPDATE categoria SET categoria=?, id_estado_categoria = ? WHERE id_categoria = ?";
                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setString(1, categoria.getCategoria());
                st.setInt(2, categoria.getEstadoCategoria().getIdEstadoCategoria());
                st.setInt(3, categoria.getIdCategoria());
                filasAfectadas = st.executeUpdate();
            } else {
                sql = "UPDATE categoria SET categoria=?, id_estado_categoria = ?, Urlcategoria=? WHERE id_categoria = ?";
                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setString(1, categoria.getCategoria());
                st.setInt(2, categoria.getEstadoCategoria().getIdEstadoCategoria());
                st.setString(3, categoria.getUrlCategoria());
                st.setInt(4, categoria.getIdCategoria());
                filasAfectadas = st.executeUpdate();
            }

            this.desconectar();

            return filasAfectadas;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

    }

    public int deshabilitarCategoria(int id) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "UPDATE categoria SET id_estado_categoria = 2 WHERE id_categoria = ?";
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

    public int habilitarCategoria(int id) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "UPDATE categoria SET id_estado_categoria = 1 WHERE id_categoria = ?";
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

    public String nombreCaterogia(int valor) throws SQLException {
        try {
            String nombre = "";
            String sql = "SELECT c.categoria FROM categoria c WHERE id_categoria =?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, valor);
            rs = st.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("categoria");
            }
            this.desconectar();
            return nombre;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return "";
        }
    }

    public void updateOfertas(String fechaactual) throws SQLException {
        try {
            String sql = "SELECT * FROM ofertas WHERE id_estado_oferta = 1";
            List<Oferta> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setIdOferta(rs.getInt("id_oferta"));
                lista.add(oferta);
            }
            for (Oferta o : lista) {
                String sql2 = "UPDATE ofertas SET id_estado_oferta = 2 WHERE fecha_fin < ?";
                st = conexion.prepareStatement(sql2);
                st.setString(1, fechaactual);
                st.executeUpdate();
            }
            this.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
        }
    }
}
