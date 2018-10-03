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
public class EstadoProducto {
    private int idEstadoProducto;
    private String estado;
    
    public EstadoProducto(){
        this.idEstadoProducto=0;
        this.estado="";
    }
    
    public EstadoProducto(String estado){
        this.estado=estado;
    }

    public int getIdEstadoProducto() {
        return idEstadoProducto;
    }

    public void setIdEstadoProducto(int idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
