
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author barre
 */
public class Auxiliar {
    
    // Metodo que valida que la entrada no este vacia retornando un String
    public static String excepString(String mensaje) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, mensaje);

            if (input == null) {
                return null;
            }

            if (input.trim().isEmpty()) {
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "El campo no puede estar vacío. ¿Desea intentarlo de nuevo?",
                        "Campo vacío", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (respuesta == JOptionPane.NO_OPTION || respuesta == JOptionPane.CLOSED_OPTION) {
                    return null;
                }

            } else {
                return input.trim();
            }
        }
    }
    
    // Metodo para el manejo de excepciones de Int retirna -1 si es nulo
    public static int exceptInt(String mensaje) {
        int num = 0;
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(mensaje);
                if (input == null){
                    return -1;
                }
                num = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Debe ingresar un valor numérico válido");
            }
        }
        return num;
    }
    
    // Metodo para el manejo de excepciones de Double retirna -1 si es nulo
    public static double exceptDouble(String mensaje) {
        double num = 0;
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(mensaje);
                if (input == null){
                    return -1;
                }
                num = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Debe ingresar un valor numérico válido");
            }
        }
        return num;
    }
    
}
