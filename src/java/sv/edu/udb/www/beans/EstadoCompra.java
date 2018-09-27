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
public class EstadoCompra {
    private int idEstadoCompra;
    private String estado;
    public EstadoCompra(){
        this.idEstadoCompra=0;
        this.estado="";
    }

    public int getIdEstadoCompra() {
        return idEstadoCompra;
    }

    public void setIdEstadoCompra(int idEstadoCompra) {
        this.idEstadoCompra = idEstadoCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
