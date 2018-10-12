package sv.edu.udb.www.model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Usuario;
import static sv.edu.udb.www.model.Conexion.conexion;

public class UsuariosModel extends Conexion {

    public int insertarUsuario(Usuario usuario, String idConfirmacion) throws SQLException {
        try {
            int filasAfectadas = 0;
            String sql = "INSERT INTO usuarios(Nombre,Apellido,Telefono,direccion,DUI,Correo,password,id_confirmacion,confirmado,id_tipo_usuario) VALUES(?,?,?,?,?,?,SHA2(?,256),?,?,?)";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getApellido());
            st.setString(3, usuario.getTelefono());
            st.setString(4, usuario.getDireccion());
            st.setString(5, usuario.getDui());
            st.setString(6, usuario.getCorreo());
            st.setString(7, usuario.getPassword());
            st.setString(8, idConfirmacion);
            st.setInt(9, 0);
            st.setInt(10, usuario.getTipoUser());
            filasAfectadas = st.executeUpdate();
            this.desconectar();
           return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public void confirmarCuenta(String id) throws SQLException {
        try {
            sql = "UPDATE usuarios SET confirmado=1 WHERE id_confirmacion =  ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        this.desconectar();
    }
    
    public Usuario verificarCuenta(){
    String sql = "SELECT * FROM usuarios u WHERE u.correo ='emerson.torres0308@gmail.com' AND u.password = SHA2( ?, 256) AND confirmado =?";
    }
}
