/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.beans;

import java.util.Date;

/**
 *
 * @author admi
 */
public class Oferta {
    private int idOferta;
    private String titulo;
    private String titulocorto;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private int descuento;
    private double totalDescuento;
    private String urlFoto;
    private int idProducto; 
    private Producto producto;
    private int idEstadoOferta;
    private EstadoOferta estadoOferta;
    private int estadoPublicado;
    private String nombreProducto;

    public Oferta() {
        this.idOferta = 0;
        this.titulo = "";
        this.descripcion = "";
        this.fechaInicio = "";
        this.fechaFin = "";
        this.descuento = 0;
        this.totalDescuento = 0;
        this.urlFoto = "";
        this.idProducto = 0;
        this.producto = null;
        this.idEstadoOferta=0;
        this.estadoOferta = null;
        this.estadoPublicado = 0;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public EstadoOferta getEstadoOferta() {
        return estadoOferta;
    }

    public int getEstadoPublicado() {
        return estadoPublicado;
    }

    public String getTitulocorto() {
        return titulocorto;
    }

    public void setTitulocorto(String titulocorto) {
        this.titulocorto = titulocorto;
    }
    
    public void setEstadoPublicado(int estadoPublicado) {
        this.estadoPublicado = estadoPublicado;
    }

    public void setEstadoOferta(EstadoOferta estadoOferta) {
        this.estadoOferta = estadoOferta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdEstadoOferta() {
        return idEstadoOferta;
    }

    public void setIdEstadoOferta(int idEstadoOferta) {
        this.idEstadoOferta = idEstadoOferta;
    }
    
    
}
