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
    
    public int insertarEmpresa (Empresa empresa) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql="Insert into empresa VALUES(NULL,?,?,?,?,?)";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1,empresa.getEmpresa());
            st.setDouble(2,Double.parseDouble(empresa.getComision()));
            st.setInt(3,empresa.getIdUsuario());
            st.setString(4, empresa.getUrlEmpresa());
            st.setInt(5,empresa.getIdEstadoEmpresa());            
            filasAfectadas=st.executeUpdate();
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
    
    public int modificarEmpresa(Empresa empresa, int codigo) throws SQLException{
        try {
            int filasAfectadas=0;
            String sql="Update empresa set empresa=?,comision=?,id_usuario=?,Urlempresa=?"
                       +" where id_empresa=?";
            this.conectar();
            st=conexion.prepareStatement(sql);
            st.setString(1, empresa.getEmpresa());
            st.setDouble(2, Double.parseDouble(empresa.getComision()));
            st.setInt(3, empresa.getIdUsuario());
            st.setString(4, empresa.getUrlEmpresa());
            st.setInt(5, codigo);
            filasAfectadas=st.executeUpdate();
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
