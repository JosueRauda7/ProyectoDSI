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
public class Empresa {
    private int idEmpresa;
    private String empresa;
    private double comision;
    private Usuario usuario;
    private EstadoEmpresa estadoEmpresa;
    public Empresa(){
        this.idEmpresa=0;
        this.empresa="";
        this.comision=0;
        this.usuario=null;
        this.estadoEmpresa=null;
    }
    
    public Empresa(String empresa){
        this.empresa=empresa;
    }
    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoEmpresa getEstadoEmpresa() {
        return estadoEmpresa;
    }

    public void setEstadoEmpresa(EstadoEmpresa estadoEmpresa) {
        this.estadoEmpresa = estadoEmpresa;
    }
    
    
}
