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
import sv.edu.udb.www.beans.Pedido;

/**
 *
 * @author ivanm
 */
public class PedidosModel extends Conexion {

    public List<Pedido> ventasDiarias() throws SQLException {
        try {
            List<Pedido> lista = new ArrayList<>();
            String sql = "Select fecha_compra, Sum(monto_total) as monto_total from pedidos where id_estado_compra=2 and fecha_compra BETWEEN CAST('2018-10-01' AS DATE)"
                    + " AND CAST(NOW() AS DATE) group BY fecha_compra  ORDER by fecha_compra LIMIT 7";
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setFechaCompra(rs.getString("fecha_compra"));
                pedido.setMontoTotal(rs.getString("monto_total"));
                lista.add(pedido);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<Pedido> ventaAnual(int anio) throws SQLException {
        try {
            List<Pedido> lista = new ArrayList<>();

            String sql = "";

            for (int i = 1; i <= 12; i++) {

                if (anio != 0) {
                    sql = "SELECT fecha_compra, SUM(monto_total) as monto_total from pedidos where Month(fecha_compra) = ? and Year(fecha_compra) = ? Group by Month(fecha_compra)";
                } else {
                    sql = "SELECT fecha_compra, SUM(monto_total) as monto_total from pedidos where Month(fecha_compra) = ? and Year(fecha_compra) = Year(NOW()) Group by Month(fecha_compra)";
                }

                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setInt(1, i);
                if (anio != 0) {
                    st.setInt(2, anio);
                }
                rs = st.executeQuery();

                Pedido pedido = new Pedido();

                if (rs.next()) {
                    pedido.setMontoTotal(rs.getString("monto_total"));
                    pedido.setFechaCompra("" + i + "-" + rs.getString("fecha_compra"));
                } else {
                    if (anio == 0) {
                        sql = "Select Year(NOW()) as anio";
                        st = conexion.prepareStatement(sql);
                        rs = st.executeQuery();
                        rs.next();
                        pedido.setFechaCompra("" + i + "-" + rs.getString("anio"));
                    }else{
                        pedido.setFechaCompra("" + i + "-" + anio);
                    }

                    pedido.setMontoTotal("0");
                }

                lista.add(pedido);
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
