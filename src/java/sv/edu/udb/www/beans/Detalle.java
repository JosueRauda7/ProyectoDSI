
package sv.edu.udb.www.beans;


public class Detalle {
    
    private int idDetalle;
    private String detalle;
    private String detalleAtributo;
    private Producto producto;
    
    public Detalle(){
        this.idDetalle = 0;
        this.detalle = "";
        this.detalleAtributo="";
        this.producto = null;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalleAtributo() {
        return detalleAtributo;
    }

    public void setDetalleAtributo(String detalleAtributo) {
        this.detalleAtributo = detalleAtributo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
