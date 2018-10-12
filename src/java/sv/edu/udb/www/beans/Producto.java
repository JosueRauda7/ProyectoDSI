/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.beans;

/**
 *
 * @author admi
 */
public class Producto {
    private int idProducto;
    private String producto;
    private String descripcion;
    private String precioRegular;
    private String cantidad;
    private String urlImagen;
    private int idsubCategoria;
    private SubCategoria subCategoria;
    private int idEmpresa;
    private Empresa empresa;
    private int idestadoProducto;
    private EstadoProducto estadoProducto;

    public Producto() {
        this.idProducto = 0;
        this.producto = "";
        this.descripcion = "";
        this.precioRegular = "";
        this.cantidad = "";
        this.urlImagen = "";
        this.subCategoria = null;
        this.empresa = null;
        this.estadoProducto = null;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecioRegular() {
        return precioRegular;
    }

    public void setPrecioRegular(String precioRegular) {
        this.precioRegular = precioRegular;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public EstadoProducto getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public int getIdsubCategoria() {
        return idsubCategoria;
    }

    public void setIdsubCategoria(int idsubCategoria) {
        this.idsubCategoria = idsubCategoria;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdestadoProducto() {
        return idestadoProducto;
    }

    public void setIdestadoProducto(int idestadoProducto) {
        this.idestadoProducto = idestadoProducto;
    }
    
    
}
