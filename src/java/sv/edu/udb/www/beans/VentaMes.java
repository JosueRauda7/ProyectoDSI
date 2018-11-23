
package sv.edu.udb.www.beans;

public class VentaMes {
    
private String nombreEmpresa;
private String total;
private String ganancia;
public VentaMes(){
    this.nombreEmpresa="";
    this.total="";
    this.ganancia = "";
}

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getGanancia() {
        return ganancia;
    }

    public void setGanancia(String ganancia) {
        this.ganancia = ganancia;
    }

    
}
