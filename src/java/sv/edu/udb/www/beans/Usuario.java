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
public class Usuario {
    private int idUsuario;
    private String correo;
    private String password;
    private String nombre;
    private String apellido;
    private String direccion;
    private String dui;
    private String id_confirmacion;
    private String confirmado;
    private TipoUsuario tipoUsaurio;

    public Usuario() {
        this.idUsuario = 0;
        this.correo = "";
        this.password = "";
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.dui = "";
        this.id_confirmacion = "";
        this.confirmado = "";
        this.tipoUsaurio = null;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getId_confirmacion() {
        return id_confirmacion;
    }

    public void setId_confirmacion(String id_confirmacion) {
        this.id_confirmacion = id_confirmacion;
    }

    public String getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(String confirmado) {
        this.confirmado = confirmado;
    }

    public TipoUsuario getTipoUsaurio() {
        return tipoUsaurio;
    }

    public void setTipoUsaurio(TipoUsuario tipoUsaurio) {
        this.tipoUsaurio = tipoUsaurio;
    }
    
}
