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
    private String comision;
    private Usuario usuario;
    private int idUsuario;
    private EstadoEmpresa estadoEmpresa;
    private int idEstadoEmpresa;
    private String urlEmpresa;
    public Empresa(){
        this.idEmpresa=0;
        this.empresa="";
        this.comision="";
        this.usuario=null;
        this.estadoEmpresa=null;
        this.urlEmpresa ="";
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

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
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

    public String getUrlEmpresa() {
        return urlEmpresa;
    }

    public void setUrlEmpresa(String urlEmpresa) {
        this.urlEmpresa = urlEmpresa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEstadoEmpresa() {
        return idEstadoEmpresa;
    }

    public void setIdEstadoEmpresa(int idEstadoEmpresa) {
        this.idEstadoEmpresa = idEstadoEmpresa;
    }
    
    
    
}
