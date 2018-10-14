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
public class Categoria {
    private int idCategoria;
    private String categoria;
    private EstadoCategoria estadoCategoria;
    private String urlCategoria;
    
    public Categoria(){
        this.idCategoria=0;
        this.categoria="";
        this.estadoCategoria=null;
        this.urlCategoria="";
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public EstadoCategoria getEstadoCategoria() {
        return estadoCategoria;
    }

    public void setEstadoCategoria(EstadoCategoria estadoCategoria) {
        this.estadoCategoria = estadoCategoria;
    }

    public String getUrlCategoria() {
        return urlCategoria;
    }

    public void setUrlCategoria(String urlCategoria) {
        this.urlCategoria = urlCategoria;
    }
    
    
}
