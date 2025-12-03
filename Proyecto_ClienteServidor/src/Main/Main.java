package Main; 

import Vista.MenuInicio;

public class Main {

    public static void main(String[] args) {

        // Mostrar la interfaz principal
        MenuInicio menuInicio = new MenuInicio();
        menuInicio.setVisible(true);
        menuInicio.setLocationRelativeTo(null);
    }
}