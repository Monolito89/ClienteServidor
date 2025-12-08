/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Vista.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Dilan
 */

public class CtrlVista {
    private Modelo.Producto proTemp = new Modelo.Producto();
    private CtrlAdmin ctrlAdmin = new CtrlAdmin();
    
    private MenuInicio menuInicio = new MenuInicio(this);
    private Carrito carrito = new Carrito(this);
    private Perfil perfil = new Perfil(this);
    private Registro registro = new Registro(this);
    private Oferta oferta = new Oferta(this);
    private Categoria categoria = new Categoria(this);
    private Administrar administrar = new Administrar(this);
    private AgregarAdmin agregarAdmin = new AgregarAdmin(this);
    private Inventario inventario = new Inventario(this);
    private JFrame frameAnterior = menuInicio;
    private boolean sesion = false;
    
    private boolean esAdmin = false;
    
    private CtrlUsuarios ctrlUsuarios = new CtrlUsuarios();
   

    
    
    
    public CtrlVista() {
        menuInicio.setVisible(true);
        menuInicio.setLocationRelativeTo(null);
    }


    public void setCtrlAdmin(CtrlAdmin ctrlAdmin) {
        this.ctrlAdmin = ctrlAdmin;
    }

    public MenuInicio getMenuInicio() {
        return menuInicio;
    }

    public void setMenuInicio(MenuInicio menuInicio) {
        this.menuInicio = menuInicio;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public Administrar getAdministrar() {
        return administrar;
    }

    public void setAdministrar(Administrar administrar) {
        this.administrar = administrar;
    }
    
    public AgregarAdmin getAgregarAdmin() {
        return agregarAdmin;
    }

    public void setAgregarAdmin(AgregarAdmin agregarAdmin) {
        this.agregarAdmin = agregarAdmin;
    }
    
    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
    
    public CtrlUsuarios getCtrlUsuarios() {
        return ctrlUsuarios;
    }
    
    public CtrlAdmin getCtrlAdmin() {
        return ctrlAdmin;
    }  
    public JFrame getFrameAnterior() {
        return frameAnterior;
    }

    public void setFrameAnterior(JFrame frameAnterior) {
        this.frameAnterior = frameAnterior;
    }
    
    public boolean getSesion() {
        return sesion;
    }

    
    public void setSesion(boolean sesion) {
        this.sesion = sesion;
    }
    
  // getter y setter para el rol admin/cliente
    public boolean isAdmin() {
        return esAdmin;
    }

    public void setAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }  
    
    public void btnAtras(JFrame actual){
        frameAnterior.setLocationRelativeTo(null);
        frameAnterior.setVisible(true);
        actual.dispose();
        setFrameAnterior(actual);
    }
    
    public void btnLogo(JFrame actual){
        menuInicio.setLocationRelativeTo(null);
        menuInicio.setVisible(true);
        actual.dispose();
        setFrameAnterior(actual);
    }

    public void btnCarrito(JFrame actual){
        carrito.setLocationRelativeTo(null);
        carrito.setVisible(true);
        actual.dispose();
        setFrameAnterior(actual);
    }
    
    public void btnOferta(JFrame actual){
        oferta.setLocationRelativeTo(null);
        oferta.setVisible(true);
        actual.dispose();
        setFrameAnterior(actual);
    }
    
    public void btnCategoria(JFrame actual){
        categoria.setLocationRelativeTo(null);
        categoria.setVisible(true);
        actual.dispose();
        setFrameAnterior(actual);
    }
    
    public void btnPerfil(JFrame actual){
        if (sesion == true){
                perfil.setVisible(true);
                perfil.setLocationRelativeTo(actual);
        } else {
                registro.setVisible(true);
                registro.setLocationRelativeTo(actual);
        } 
        actual.dispose();
        setFrameAnterior(actual);
    }
    
    public void btnBuscar(JFrame actual){
        menuInicio.setLocationRelativeTo(null);
        menuInicio.setVisible(true);
        actual.dispose();
        setFrameAnterior(actual);
    }
    
    public void btnAdministrar(JFrame actual){
        // Solo si hay sesión y es admin
        if (!sesion || !esAdmin) {
            javax.swing.JOptionPane.showMessageDialog(actual,
                    "Solo los administradores pueden acceder a esta sección.");
            return;
        }
        
        administrar.setLocationRelativeTo(null);
        administrar.setVisible(true);
        actual.dispose();
        setFrameAnterior(actual);
    }

    public void btnAgregarAdmin(JFrame actual){
        // Solo admins pueden entrar aquí también
        if (!sesion || !isAdmin()) {
            javax.swing.JOptionPane.showMessageDialog(actual,
                    "Solo los administradores pueden gestionar otros administradores.");
            return;
        }
        
        agregarAdmin.setLocationRelativeTo(null);
        agregarAdmin.setVisible(true);
        actual.dispose();
        setFrameAnterior(actual);
    }
    
    public void btnInventario(JFrame actual){
        inventario.setLocationRelativeTo(null);
        inventario.setVisible(true);
        actual.dispose();
        setFrameAnterior(actual);
    }
    
    public void btnBuscarID(int id){
        proTemp.setIdProducto(id);
        boolean busqueda = ctrlAdmin.buscar(proTemp);
        if (busqueda == true){
            JOptionPane.showMessageDialog(null, "Producto Encontrado");
            administrar.setProducto(proTemp);
            
        } else {
            JOptionPane.showMessageDialog(null, "Error: ID no coincide con "
                    + "ningún Producto Registrado");
            administrar.setProducto(null);
        }
    }
    
    public void btnAgregarProducto(Modelo.Producto producto){
        boolean registro = ctrlAdmin.registrar(producto);
        if (registro== true){
            JOptionPane.showMessageDialog(null, "Producto Agregado a la Base de Datos");
            administrar.setProducto(proTemp);
            
        } else {
            JOptionPane.showMessageDialog(null, "Error: El Producto no pudo ser Registrado");
            administrar.setProducto(null);
        }
    }
    
    public void btnActualizarProducto(Modelo.Producto producto){
        boolean registro = ctrlAdmin.modificar(producto);
        if (registro== true){
            JOptionPane.showMessageDialog(null, "Producto fue Actualizado "
                    + "Exitosamente");
            administrar.setProducto(proTemp);
            
        } else {
            JOptionPane.showMessageDialog(null, "Error: El Producto no pudo "
                    + "ser Actualizado");
            administrar.setProducto(null);
        }
    }
    
    public void btnEliminarProducto(Modelo.Producto producto){
        boolean registro = ctrlAdmin.eliminar(producto);
        if (registro== true){
            JOptionPane.showMessageDialog(null, "El Producto Fue Eliminado "
                    + "Exitosamente");
            administrar.setProducto(proTemp);
            
        } else {
            JOptionPane.showMessageDialog(null, "Error: El Producto no pudo "
                    + "ser Eliminado");
            administrar.setProducto(null);
        }
    }
}
