package Modelo;


public class Producto {
    private int idProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private int stock;
    private Double precio;
    private double descuento;
    private int idCategoria;    // lo cambie  a int para que coincida con la BD
    private int idProveedor;    // Nuevo campo para la BD

    public Producto() {
        this.idProducto = 0;
        this.codigo = "";
        this.nombre = "";
        this.descripcion = "";
        this.stock = 0;
        this.precio = 0.0d;
        this.descuento = 0.0d;
        this.idCategoria = 1;     
        this.idProveedor = 1;    
    }
    
    public Producto(int idProducto, String codigo, String nombre, String descripcion, 
                   int stock, Double precio, Double descuento, int idCategoria, int idProveedor) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.descuento = descuento;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
    }

    // GETTERS Y SETTERS 
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
    
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
}
