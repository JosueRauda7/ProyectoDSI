/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

/**
 *
 * @author ivanm
 */
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.Oferta;
import static sv.edu.udb.www.model.Conexion.conexion;

public class OfertasModel extends Conexion {

    public int insertarOferta(Oferta oferta) throws SQLException {
        try {
            int filasAfectadas = 0;
            double precioRegular = 0.0;
            // precio re - (precio re * descuento)

            String sql = "Select precio_regular from producto where id_producto = ?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, oferta.getIdProducto());
            rs = st.executeQuery();
            if (rs.next()) {
                precioRegular = rs.getDouble("precio_regular");
                double descuento = oferta.getDescuento();
                double precioOferta = (precioRegular - (precioRegular * (descuento * 0.01)));

                sql = "Insert into ofertas VALUES (null,?,?,?,?,?,?,?,?,1,0) ";
                st = conexion.prepareStatement(sql);
                st.setString(1, oferta.getTitulo());
                st.setString(2, oferta.getDescripcion());
                st.setString(3, oferta.getFechaInicio());
                st.setString(4, oferta.getFechaFin());
                st.setInt(5, oferta.getDescuento());
                st.setDouble(6, precioOferta);
                st.setInt(7, oferta.getIdProducto());
                st.setString(8, oferta.getUrlFoto());

                filasAfectadas = st.executeUpdate();
                if (filasAfectadas != 0) {
                    this.desconectar();
                    return 1;
                } else {
                    this.desconectar();
                    return 0;
                }
            }

            this.desconectar();
            return 0;

        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return 0;
        }
    }

    public List<Oferta> listaOfertasEmpresa(int usuario) throws SQLException {
        try {
            int filasAfectadas = 0;
            int idEmpresa = 0;
            
            List<Oferta> lista = new ArrayList<>();
            String sql = "Select id_empresa from empresa where id_usuario=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, usuario);
            rs = st.executeQuery();

            if (rs.next()) {
                idEmpresa = rs.getInt("id_empresa");
                
                sql = "Select o.* from ofertas o Inner join producto p on o.id_producto=p.id_producto INNER JOIN empresa e on e.id_empresa=p.id_empresa Where p.id_empresa = ?";
                st = conexion.prepareStatement(sql);
                st.setInt(1, idEmpresa);
                rs = st.executeQuery();
                while (rs.next()) {
                    Oferta oferta = new Oferta();
                    oferta.setIdOferta(rs.getInt("id_oferta"));
                    oferta.setTitulo(rs.getString("titulo"));
                    oferta.setDescripcion(rs.getString("descripcion"));
                    oferta.setFechaInicio(rs.getString("fecha_inicio"));
                    oferta.setFechaFin(rs.getString("fecha_fin"));
                    oferta.setDescuento(rs.getInt("descuento"));
                    oferta.setTotalDescuento(rs.getDouble("total_descuento"));
                    oferta.setUrlFoto(rs.getString("Url_foto"));
                    oferta.setIdProducto(rs.getInt("id_producto"));
                    oferta.setIdEstadoOferta(rs.getInt("id_estado_oferta"));
                    oferta.setEstadoPublicado(rs.getInt("estado_publicado"));
                    lista.add(oferta);
                }
            }

            this.desconectar();
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

}
