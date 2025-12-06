/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Vista.Categoria;
import Vista.*;
import javax.swing.JFrame;

/**
 *
 * @author Dilan
 */

public class CtrlVista {
    private MenuInicio menuInicio = new MenuInicio(this);
    private Carrito carrito = new Carrito(this);
    private Perfil perfil = new Perfil(this);
    private Registro registro = new Registro(this);
    private Oferta oferta = new Oferta(this);
    private Categoria categoria = new Categoria(this);
    private Administrar administrar = new Administrar(this);
    private JFrame frameAnterior = menuInicio;
    private boolean sesion = false;
    
    public CtrlVista() {
        menuInicio.setVisible(true);
        menuInicio.setLocationRelativeTo(null);
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
        administrar.setLocationRelativeTo(null);
        administrar.setVisible(true);
        actual.dispose();
        setFrameAnterior(actual);
    }
}
