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
import sv.edu.udb.www.beans.Sugerencia;
import sv.edu.udb.www.beans.Usuario;
import static sv.edu.udb.www.model.Conexion.conexion;

/**
 *
 * @author Ferh
 */
public class SugerenciasModel extends Conexion {

    public List<Sugerencia> listarSugerencias() throws SQLException {
        try {
            List<Sugerencia> lista = new ArrayList<>();
            sql = "SELECT t1.*, t2.* FROM sugerencias t1 INNER JOIN usuarios t2 ON t1.id_usuario = t2.id_usuario";
            this.conectar();
            st = conexion.prepareStatement(sql);
            
            rs = st.executeQuery();
            while (rs.next()) {
                Sugerencia sugerencia = new Sugerencia();
                Usuario usuario  = new Usuario();
                sugerencia.setIdSugerencia(rs.getInt("id_sugerencia"));
                sugerencia.setAsunto(rs.getString("Asunto"));
                sugerencia.setDetalle(rs.getString("Detalle"));
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                sugerencia.setUsuario(usuario);
                lista.add(sugerencia);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(SugerenciasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }

    }

    
    public int insertarSugerencia(Sugerencia sugerencia) throws SQLException {

        try {
            int filasAfectadas = 0;
            sql = "INSERT INTO sugerencias VALUES(null,?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, sugerencia.getAsunto());
            st.setString(2, sugerencia.getDetalle());
            st.setInt(3, sugerencia.getUsuario().getIdUsuario());
            filasAfectadas = st.executeUpdate();

            this.desconectar();

            return filasAfectadas;

        } catch (SQLException ex) {
            Logger.getLogger(SugerenciasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

    }

    public int eliminarSugerencia(int id) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "DELETE FROM sugerencias WHERE id_sugerencia = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, id);

            filasAfectadas = st.executeUpdate();

            this.desconectar();

            return filasAfectadas;

        } catch (SQLException ex) {
            Logger.getLogger(SugerenciasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }

    }

}
