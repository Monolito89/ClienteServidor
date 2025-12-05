package Modelo;
/**
 *
 * @author barre
 */
public class Producto {
    private int idProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private int stock;
    private Double precio;
    private double descuento;
    private String categoria;

    public Producto() {
        this.idProducto = 0;
        this.codigo = "";
        this.nombre = "";
        this.descripcion = "";
        this.stock = 0;
        this.precio = 0.0d;
        this.descuento = 0.0d;
        this.categoria = "";
    }
    
    public Producto(int idProducto, String codigo, String nombre, String descripcion, int stock, Double precio, Double descuento, String categoria) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.descuento = descuento;
        this.categoria = categoria;
        }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    } 
}
