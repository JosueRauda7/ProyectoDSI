/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.beans;

/**
 *
 * @author ivanm
 */
public class VentaHoy {

    private String producto;
    private String cantidad;
    private String totalVendido;
    private String totalGanancias;

    public VentaHoy() {
        String producto = "";
        String cantidad = "";
        String totalVendido = "";
        String totalGanancias = "";
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(String totalVendido) {
        this.totalVendido = totalVendido;
    }

    public String getTotalGanancias() {
        return totalGanancias;
    }

    public void setTotalGanancias(String totalGanancias) {
        this.totalGanancias = totalGanancias;
    }
    
    
}
