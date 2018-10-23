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
    private String descripcion;
    private String fechaInicio;
    private Date fechaFin;
    private double descuento;
    private double totalDescuento;
    private String urlFoto;
    private Producto producto;
    private EstadoOferta estadoOferta;

    public Oferta() {
        this.idOferta = 0;
        this.titulo = "";
        this.descripcion = "";
        this.fechaInicio = "";
        this.fechaFin = new Date();
        this.descuento = 0;
        this.totalDescuento = 0;
        this.urlFoto = "";
        this.producto = null;
        this.estadoOferta = null;
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

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
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

    public void setEstadoOferta(EstadoOferta estadoOferta) {
        this.estadoOferta = estadoOferta;
    }
    
}
