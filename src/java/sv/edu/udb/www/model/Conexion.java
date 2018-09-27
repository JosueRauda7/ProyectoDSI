/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ivanm
 */
public class Conexion {
    protected static Connection conexion=null;
    protected PreparedStatement st;
    protected ResultSet rs;
    protected PreparedStatement st2;
    protected ResultSet rs2;
    protected String sql;
    
    public Conexion() {
        this.st=null;
        this.rs=null;
        this.sql="";
    }
    
    public void conectar(){        
            try {
                if(conexion==null || conexion.isClosed()){
                Context init = new InitialContext();
                Context context = (Context) init.lookup("java:comp/env");
                DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
                conexion = dataSource.getConnection();
                }
            } catch (NamingException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public void desconectar() throws SQLException{
        if(rs != null){
            rs.close();
        }     
        if(st != null){
            st.close();
        }
        if(conexion!=null){
            conexion.close();
        }
    }
}
