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
public class SubCategoria {
    private int idSubCategoria;
    private String subCategoria;
    private Categoria categoria;
    private EstadoCategoria estadoCategoria;
    
    public SubCategoria(){
        this.idSubCategoria=0;
        this.subCategoria="";
        this.categoria=null;
        this.estadoCategoria=null;
    }
    
    public SubCategoria(String subCategoria){
        this.subCategoria=subCategoria;
    }

    public int getIdSubCategoria() {
        return idSubCategoria;
    }

    public void setIdSubCategoria(int idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public EstadoCategoria getEstadoCategoria() {
        return estadoCategoria;
    }

    public void setEstadoCategoria(EstadoCategoria estadoCategoria) {
        this.estadoCategoria = estadoCategoria;
    }
    
}
