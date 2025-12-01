package Modelo;

/**
 *
 * @author melic
 */
public class Colaborador {
    
    private int idColaborador;
    private String nombre;
    private String correo;
    private String rol;

    public Colaborador() {
        this.idColaborador = 0;
        this.nombre = "";
        this.correo = "";
        this.rol = "";
    }

    public Colaborador(int idColaborador, String nombre, String correo, String rol) {
        this.idColaborador = idColaborador;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
    
}
