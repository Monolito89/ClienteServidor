package Modelo;

/**
 *
 * @author melic
 */
public class Orden {
    
    private int idOrden;
    private int idUsuario;
    private String fecha;
    private Double total;

    public Orden() {
        this.idOrden = 0;
        this.idUsuario = 0;
        this.fecha = "";
        this.total = 0.0d;
    }

    public Orden(int idOrden, int idUsuario, String fecha, Double total) {
        this.idOrden = idOrden;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.total = total;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
    
}
