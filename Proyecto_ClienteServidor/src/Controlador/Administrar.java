package Controlador;

import Controlador.CtrlVista;
import javax.swing.JFrame;

public class Administrar extends JFrame {

    private CtrlVista ctrl;

    public Administrar(CtrlVista ctrl) {
        this.ctrl = ctrl;
        initComponents();
    }

    // Por ahora un initComponents vacío para que compile.
    // Más adelante se puede convertir esto en un Form real.
    private void initComponents() {
     
        setTitle("Administrar Plataforma");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

