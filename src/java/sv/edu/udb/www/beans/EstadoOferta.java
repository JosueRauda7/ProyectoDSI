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
public class EstadoOferta {
    private int idEstadoOferta;
    private String estadoOferta;
    public EstadoOferta(){
        this.idEstadoOferta=0;
        this.estadoOferta="";
    }

    public int getIdEstadoOferta() {
        return idEstadoOferta;
    }

    public void setIdEstadoOferta(int idEstadoOferta) {
        this.idEstadoOferta = idEstadoOferta;
    }

    public String getEstadoOferta() {
        return estadoOferta;
    }

    public void setEstadoOferta(String estadoOferta) {
        this.estadoOferta = estadoOferta;
    }
    
}
