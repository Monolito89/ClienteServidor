package Modelo;

/**
 *
 * @author melic
 */
public class CarritoProducto {
    
    private int idCarrito;
    private int idProducto;
    private int cantidad;

    public CarritoProducto() {
        this.idCarrito = 0;
        this.idProducto = 0;
        this.cantidad = 0;
    }

    public CarritoProducto(int idCarrito, int idProducto, int cantidad) {
        this.idCarrito = idCarrito;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
