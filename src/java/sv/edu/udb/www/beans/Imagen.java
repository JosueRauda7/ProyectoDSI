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
public class Imagen {
    private int idImagenProducto;
    private String Urlimagen;
    private Producto producto;
    private int idProducto;
    
    public Imagen(){
        this.idImagenProducto = 0;
        this.Urlimagen ="";
        this.producto=null;
        this.idProducto=0;
    }

    public int getIdImagenProducto() {
        return idImagenProducto;
    }

    public void setIdImagenProducto(int idImagenProducto) {
        this.idImagenProducto = idImagenProducto;
    }

    public String getUrlimagen() {
        return Urlimagen;
    }

    public void setUrlimagen(String Urlimagen) {
        this.Urlimagen = Urlimagen;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    
}
