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
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.EstadoEmpresa;
import sv.edu.udb.www.beans.EstadoProducto;
import sv.edu.udb.www.beans.Producto;
import sv.edu.udb.www.beans.SubCategoria;
import sv.edu.udb.www.beans.Usuario;
import static sv.edu.udb.www.model.Conexion.conexion;

/**
 *
 * @author ivanm
 */
public class EmpresasModel extends Conexion {
    public List<Empresa> listarEmpresas() throws SQLException{
        try {
            List<Empresa> lista=new ArrayList<>();
            String sql="Select * from empresa e inner join usuarios u on "
                     + "e.id_usuario=u.id_usuario inner join estado_empresa es on e.id_estado_empresa = es.id_estado_empresa";
            this.conectar();
            st=conexion.prepareStatement(sql);
            rs=st.executeQuery();
            while(rs.next()){
                Empresa empresa=new Empresa();
                empresa.setIdEmpresa(rs.getInt("id_empresa"));
                empresa.setEmpresa(rs.getString("empresa"));
                empresa.setComision(rs.getString("comision"));                
                empresa.setUrlEmpresa(rs.getString("Urlempresa"));
                empresa.setUsuario(new Usuario(rs.getString("nombre"),rs.getString("apellido")));
                empresa.setEstadoEmpresa(new EstadoEmpresa(rs.getString("estado_empresa")));
                lista.add(empresa);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }
    
    public int insertarEmpresa (Empresa empresa, Usuario usuario) throws SQLException{
        try {
            int filasAfectadas=0;
            int idUsuario=0;
            String sql="Insert into Usuarios VALUES(NULL,?,?,?,?,?,?,SHA2(?,256),?,1,5)";
            this.conectar();
            st=conexion.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setString(1,usuario.getNombre());
            st.setString(2,usuario.getApellido());
            st.setString(3,usuario.getTelefono());
            st.setString(4, usuario.getDireccion());
            st.setString(5,usuario.getDui());            
            st.setString(6,usuario.getCorreo());            
            st.setString(7,usuario.getPassword());            
            st.setString(8,usuario.getId_confirmacion());                                    
            filasAfectadas=st.executeUpdate();
            if(filasAfectadas!=0){
                rs = st.getGeneratedKeys();
                rs.next();
                idUsuario=rs.getInt(1);
                sql = "Insert into empresa VALUES (NULL,?,80.00,?,?,1)";
                st = conexion.prepareStatement(sql);
                st.setString(1, empresa.getEmpresa());
                st.setInt(2, idUsuario);
                st.setString(3, empresa.getUrlEmpresa());
                filasAfectadas = st.executeUpdate();
            }
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }        
    
    public Empresa obtenerEmpresa(int codigo){
        try {
            String sql ="Select * from empresa where id_empresa=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setInt(1,codigo);
            rs=st.executeQuery();
            if(rs.next()){
                Empresa empresa = new Empresa();
                
                empresa.setIdEmpresa(rs.getInt("id_empresa"));
                empresa.setEmpresa(rs.getString("empresa"));
                empresa.setComision(rs.getString("comision"));
                empresa.setIdUsuario(rs.getInt("id_usuario"));
                empresa.setUrlEmpresa(rs.getString("Urlempresa"));
                empresa.setIdEstadoEmpresa(rs.getInt("id_estado_empresa"));
                this.desconectar();
                return empresa;
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
    
    public Usuario obtenerUsuario(int codigoEmpresa) {
        try {
            String sql ="Select * from usuarios u inner join empresa e on u.id_usuario=e.id_usuario where e.id_empresa=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setInt(1,codigoEmpresa);
            rs=st.executeQuery();
            if(rs.next()){
                Usuario user = new Usuario();                
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setNombre(rs.getString("Nombre"));
                user.setApellido(rs.getString("Apellido"));
                user.setTelefono(rs.getString("Telefono"));
                user.setDireccion(rs.getString("direccion"));
                user.setDui(rs.getString("DUI"));
                user.setCorreo(rs.getString("correo"));
                user.setIdConfirmacion(rs.getInt("confirmado"));
                user.setTipoUser(rs.getInt("id_tipo_usuario"));
                this.desconectar();
                return user;
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
    
    public int modificarEmpresa(Empresa empresa,Usuario usuario, int codigo) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql="Update empresa set empresa=?,Urlempresa=?"
                       +" where id_empresa=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1, empresa.getEmpresa());                        
            st.setString(2, empresa.getUrlEmpresa());
            st.setInt(3, codigo);
            filasAfectadas=st.executeUpdate();
            if(filasAfectadas!=0){
                sql = "Select id_usuario from empresa where id_empresa=?";
                st = conexion.prepareStatement(sql);
                st.setInt(1,codigo);
                rs = st.executeQuery();
                rs.next();
                int idUsuario = rs.getInt("id_usuario");
                
                sql = "Update usuarios set Nombre=?,Apellido=?, Telefono=?, direccion=?,DUI=?, correo=? where id_usuario=?";
                st= conexion.prepareStatement(sql);
                st.setString(1,usuario.getNombre());
                st.setString(2,usuario.getApellido());
                st.setString(3, usuario.getTelefono());
                st.setString(4,usuario.getDireccion());
                st.setString(5,usuario.getDui());
                st.setString(6,usuario.getCorreo());
                st.setInt(7,idUsuario);
                filasAfectadas = st.executeUpdate();
            }
            this.desconectar();
            return filasAfectadas;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }
    
    public int deshabilitarEmpresa(int id) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "UPDATE empresa SET id_estado_empresa = 2 WHERE id_empresa = ?";
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
    
    public int habilitarEmpresa(int id) throws SQLException {
        try {
            int filasAfectadas = 0;
            sql = "UPDATE empresa SET id_estado_empresa = 1 WHERE id_empresa = ?";
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
