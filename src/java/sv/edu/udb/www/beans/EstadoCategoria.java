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
public class EstadoCategoria {
    private int idEstadoCategoria;
    private String estadoCategoria;
    public EstadoCategoria(){
        this.idEstadoCategoria=0;
        this.estadoCategoria="";
    }

    public int getIdEstadoCategoria() {
        return idEstadoCategoria;
    }

    public void setIdEstadoCategoria(int idEstadoCategoria) {
        this.idEstadoCategoria = idEstadoCategoria;
    }

    public String getEstadoCategoria() {
        return estadoCategoria;
    }

    public void setEstadoCategoria(String estadoCategoria) {
        this.estadoCategoria = estadoCategoria;
    }
    
}
