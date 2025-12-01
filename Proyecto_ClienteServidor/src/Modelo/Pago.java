package Modelo;

/**
 *
 * @author melic
 */
public class Pago {
    
    private int idPago;
    private int idOrden;
    private String metodo;
    private String estado;

    public Pago() {
        this.idPago = 0;
        this.idOrden = 0;
        this.metodo = "";
        this.estado = "";
    }

    public Pago(int idPago, int idOrden, String metodo, String estado) {
        this.idPago = idPago;
        this.idOrden = idOrden;
        this.metodo = metodo;
        this.estado = estado;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
