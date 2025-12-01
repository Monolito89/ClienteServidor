package Modelo;

/**
 *
 * @author melic
 */
public class Carrito {
    private int idCarrito;
    private int idUsuario;

    public Carrito() {
        this.idCarrito = 0;
        this.idUsuario = 0;
    }

    public Carrito(int idCarrito, int idUsuario) {
        this.idCarrito = idCarrito;
        this.idUsuario = idUsuario;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
