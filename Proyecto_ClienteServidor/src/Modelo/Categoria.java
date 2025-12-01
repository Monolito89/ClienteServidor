package Modelo;

/**
 *
 * @author melic
 */
public class Categoria {
    
    private int idCategoria;
    private String nomCategoria;

    public Categoria() {
        this.idCategoria = 0;
        this.nomCategoria = "";
    }

    public Categoria(int idCategoria, String nomCategoria) {
        this.idCategoria = idCategoria;
        this.nomCategoria = nomCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }
    
    
    
}
