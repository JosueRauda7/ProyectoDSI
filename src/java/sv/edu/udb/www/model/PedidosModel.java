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
import sv.edu.udb.www.beans.VentaHoy;
import sv.edu.udb.www.beans.VentaMes;

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
            Logger.getLogger(PedidosModel.class.getName()).log(Level.SEVERE, null, ex);
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
                    sql = "SELECT pd.fecha_compra ,ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total FROM detalle_pedidos "
                            + "dp LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on "
                            + "dp.id_pedido = pd.id_pedido WHERE Month(pd.fecha_compra) = ? and Year(pd.fecha_compra) = ? AND p.id_estado_producto = 2 GROUP by pd.fecha_compra";
                } else {
                    sql = "SELECT pd.fecha_compra ,ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total FROM detalle_pedidos "
                            + "dp LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on "
                            + "dp.id_pedido = pd.id_pedido WHERE Month(pd.fecha_compra) = ? and Year(pd.fecha_compra) = Year(Now()) AND p.id_estado_producto = 2 GROUP by pd.fecha_compra";
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
                    pedido.setMontoTotal(rs.getString("total"));
                    pedido.setFechaCompra("" + i + "-" + rs.getString("fecha_compra"));
                } else {
                    if (anio == 0) {
                        sql = "Select Year(NOW()) as anio";
                        st = conexion.prepareStatement(sql);
                        rs = st.executeQuery();
                        rs.next();
                        pedido.setFechaCompra("" + i + "-" + rs.getString("anio"));
                    } else {
                        pedido.setFechaCompra("" + i + "-" + anio);
                    }

                    pedido.setMontoTotal("0");
                }

                lista.add(pedido);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PedidosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<Pedido> ventaAnual(int anio, int usuario) throws SQLException {
        try {
            List<Pedido> lista = new ArrayList<>();
            int empresa = 0;
            String sql = "";

            sql = "Select id_empresa from empresa where id_usuario=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, usuario);
            rs = st.executeQuery();
            while (rs.next()) {
                empresa = rs.getInt("id_empresa");
            }
            for (int i = 1; i <= 12; i++) {

                if (anio != 0) {
                    sql = "SELECT pd.fecha_compra ,ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total FROM detalle_pedidos "
                            + "dp LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on "
                            + "dp.id_pedido = pd.id_pedido WHERE p.id_empresa = ? and Month(pd.fecha_compra) = ? and Year(pd.fecha_compra) = ? AND p.id_estado_producto = 2 GROUP by pd.fecha_compra";
                } else {
                    sql = "SELECT pd.fecha_compra ,ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total FROM detalle_pedidos "
                            + "dp LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on "
                            + "dp.id_pedido = pd.id_pedido WHERE p.id_empresa = ? and Month(pd.fecha_compra) = ? and Year(pd.fecha_compra) = Year(Now()) AND p.id_estado_producto = 2 GROUP by pd.fecha_compra";
                }

                this.conectar();
                st = conexion.prepareStatement(sql);
                st.setInt(1, empresa);
                st.setInt(2, i);
                if (anio != 0) {
                    st.setInt(3, anio);
                }
                rs = st.executeQuery();

                Pedido pedido = new Pedido();

                if (rs.next()) {
                    pedido.setMontoTotal(rs.getString("total"));
                    pedido.setFechaCompra("" + i + "-" + rs.getString("fecha_compra"));
                } else {
                    if (anio == 0) {
                        sql = "Select Year(NOW()) as anio";
                        st = conexion.prepareStatement(sql);
                        rs = st.executeQuery();
                        rs.next();
                        pedido.setFechaCompra("" + i + "-" + rs.getString("anio"));
                    } else {
                        pedido.setFechaCompra("" + i + "-" + anio);
                    }

                    pedido.setMontoTotal("0");
                }

                lista.add(pedido);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PedidosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<VentaHoy> ventaHoy(int usuario) throws SQLException {
        try {
            List<VentaHoy> lista = new ArrayList<>();
            int empresa = 0;
            String sql = "";

            sql = "Select id_empresa from empresa where id_usuario=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, usuario);
            rs = st.executeQuery();
            while (rs.next()) {
                empresa = rs.getInt("id_empresa");
            }

            sql = "SELECT p.producto, SUM(dp.cantidad) as cantidad, ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total, "
                    + "ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2)*0.8 as totalComision FROM detalle_pedidos dp "
                    + "LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on "
                    + "dp.id_pedido = pd.id_pedido WHERE p.id_empresa = ? AND fecha_compra = NOW() AND p.id_estado_producto = 2 GROUP by p.producto";

            st = conexion.prepareStatement(sql);
            st.setInt(1, empresa);
            rs = st.executeQuery();
            while (rs.next()) {
                VentaHoy ventas = new VentaHoy();
                ventas.setProducto(rs.getString("producto"));
                ventas.setCantidad(rs.getString("cantidad"));
                ventas.setTotalVendido(rs.getString("total"));
                ventas.setTotalGanancias(rs.getString("totalComision"));

                lista.add(ventas);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PedidosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<VentaHoy> ventaHoy() throws SQLException {
        try {
            List<VentaHoy> lista = new ArrayList<>();
            int empresa = 0;
            String sql = "";

            sql = "SELECT e.empresa, SUM(dp.cantidad) as cantidad, ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total, "
                    + "ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2)*0.8 as totalComision FROM detalle_pedidos dp "
                    + "LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on "
                    + "dp.id_pedido = pd.id_pedido INNER JOIN empresa e on e.id_empresa = p.id_empresa WHERE fecha_compra = NOW() AND p.id_estado_producto = 2 GROUP by e.empresa";
            this.conectar();
            st = conexion.prepareStatement(sql);
            //st.setInt(1, empresa);
            rs = st.executeQuery();
            while (rs.next()) {
                VentaHoy ventas = new VentaHoy();
                ventas.setProducto(rs.getString("empresa"));
                ventas.setCantidad(rs.getString("cantidad"));
                ventas.setTotalVendido(rs.getString("total"));
                ventas.setTotalGanancias(rs.getString("totalComision"));

                lista.add(ventas);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PedidosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<VentaMes> ventasDelmes() throws SQLException {
        try {
            String sql = "SELECT e.empresa, ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total, ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2)*0.10 as totalComision FROM detalle_pedidos dp LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on dp.id_pedido = pd.id_pedido INNER JOIN empresa e on e.id_empresa=p.id_empresa WHERE MONTH(pd.fecha_compra)= MONTH(NOW()) GROUP by e.empresa";
            List<VentaMes> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                VentaMes ventames = new VentaMes();
                ventames.setNombreEmpresa(rs.getString("empresa"));
                ventames.setTotal(rs.getString("total"));
                ventames.setGanancia(rs.getString("totalComision"));
                lista.add(ventames);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PedidosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<VentaMes> ventasDelmes(int usuario) throws SQLException {
        try {
            int idEmpresa = 0;
            String sql = "";

            sql = "Select id_empresa from empresa where id_usuario=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, usuario);
            rs = st.executeQuery();
            while (rs.next()) {
                idEmpresa = rs.getInt("id_empresa");
            }

            sql = "SELECT e.empresa, ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total, ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2)*0.10 as totalComision FROM detalle_pedidos dp LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on dp.id_pedido = pd.id_pedido INNER JOIN empresa e on e.id_empresa=p.id_empresa WHERE e.id_empresa=? AND MONTH(pd.fecha_compra)= MONTH(NOW()) GROUP by e.empresa";
            List<VentaMes> lista = new ArrayList<>();
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idEmpresa);
            rs = st.executeQuery();
            while (rs.next()) {
                VentaMes ventames = new VentaMes();
                ventames.setNombreEmpresa(rs.getString("empresa"));
                ventames.setTotal(rs.getString("total"));
                ventames.setGanancia(rs.getString("totalComision"));
                lista.add(ventames);
            }
            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PedidosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<VentaHoy> ventaHoy2(int idEmpresa) throws SQLException {
        try {
            List<VentaHoy> lista = new ArrayList<>();
            int empresa = 0;
            String sql = "";

            sql="SELECT CURRENT_DATE() as fecha";
            this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();
            
            String fecha="";
            rs.next();
            fecha=rs.getString("fecha");
            
            sql = "SELECT p.producto as producto, SUM(dp.cantidad) as cantidad, ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total, "
                    + "ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2)*0.10 as totalComision FROM detalle_pedidos dp "
                    + "LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on "
                    + "dp.id_pedido = pd.id_pedido WHERE p.id_empresa = ? AND pd.fecha_compra = ? AND p.id_estado_producto = 2 GROUP by p.producto";
            
            st = conexion.prepareStatement(sql);
            st.setInt(1, idEmpresa);
            st.setString(2,fecha);
            rs = st.executeQuery();
            while (rs.next()) {
                VentaHoy ventas = new VentaHoy();
                ventas.setProducto(rs.getString("producto"));
                ventas.setCantidad(rs.getString("cantidad"));
                ventas.setTotalVendido(rs.getString("total"));
                ventas.setTotalGanancias(rs.getString("totalComision"));

                lista.add(ventas);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PedidosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

    public List<VentaHoy> ventaHoy3(int usuario) throws SQLException {
        try {
            List<VentaHoy> lista = new ArrayList<>();
            int idEmpresa = 0;
            String sql = "";

            sql = "Select id_empresa from empresa where id_usuario=?";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, usuario);
            rs = st.executeQuery();
            while (rs.next()) {
                idEmpresa = rs.getInt("id_empresa");
            }

            sql = "SELECT p.producto as producto, SUM(dp.cantidad) as cantidad, ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2) as total, "
                    + "ROUND(SUM(CONCAT_WS('', (p.precio_regular* dp.cantidad),(o.total_descuento*dp.cantidad))),2)*0.10 as totalComision FROM detalle_pedidos dp "
                    + "LEFT JOIN producto p on dp.id_producto = p.id_producto LEFT JOIN ofertas o ON dp.id_oferta = o.id_oferta INNER JOIN pedidos pd on "
                    + "dp.id_pedido = pd.id_pedido WHERE p.id_empresa = ? AND pd.fecha_compra = CURRENT_DATE() AND p.id_estado_producto = 2 GROUP by p.producto";
            this.conectar();
            st = conexion.prepareStatement(sql);
            st.setInt(1, idEmpresa);
            rs = st.executeQuery();
            while (rs.next()) {
                VentaHoy ventas = new VentaHoy();
                ventas.setProducto(rs.getString("producto"));
                ventas.setCantidad(rs.getString("cantidad"));
                ventas.setTotalVendido(rs.getString("total"));
                ventas.setTotalGanancias(rs.getString("totalComision"));

                lista.add(ventas);
            }

            this.desconectar();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PedidosModel.class.getName()).log(Level.SEVERE, null, ex);
            this.desconectar();
            return null;
        }
    }

}
