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
public class EstadoEmpresa {
    private int idEstadoEmpresa;
    private String estadoEmpresa;
    public EstadoEmpresa(){
        this.idEstadoEmpresa=0;
        this.estadoEmpresa="";
    }

    public int getIdEstadoEmpresa() {
        return idEstadoEmpresa;
    }

    public void setIdEstadoEmpresa(int idEstadoEmpresa) {
        this.idEstadoEmpresa = idEstadoEmpresa;
    }

    public String getEstadoEmpresa() {
        return estadoEmpresa;
    }

    public void setEstadoEmpresa(String estadoEmpresa) {
        this.estadoEmpresa = estadoEmpresa;
    }
    
}
