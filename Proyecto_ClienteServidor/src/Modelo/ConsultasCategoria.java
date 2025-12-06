/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Categoria;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ...
 */
public class ConsultasCategoria extends Conexion {
    
    private static final String INSERT_SQL =
            "INSERT INTO categorias (nombre) VALUES (?)";
    
    private static final String UPDATE_SQL =
            "UPDATE categorias SET nombre = ? WHERE id_categoria = ?";
    
    private static final String DELETE_SQL =
            "DELETE FROM categorias WHERE id_categoria = ?";
    
    private static final String SELECT_BY_ID_SQL =
            "SELECT id_categoria, nombre FROM categorias WHERE id_categoria = ?";
    
    private static final String SELECT_ALL_SQL =
            "SELECT id_categoria, nombre FROM categorias";
    
    // Registrar categoría
    public boolean registrar(Categoria c) {
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            
            ps.setString(1, c.getNombre());
            
            int filas = ps.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al registrar categoría: " + e.getMessage());
            return false;
        }
    }
    
    // Modificar categoría
    public boolean modificar(Categoria c) {
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getIdCategoria());
            
            int filas = ps.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al modificar categoría: " + e.getMessage());
            return false;
        }
    }
    
    // Eliminar categoría
    public boolean eliminar(int idCategoria) {
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            
            ps.setInt(1, idCategoria);
            
            int filas = ps.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }
    
    // Buscar categoría por ID
    public Categoria buscar(int idCategoria) {
        Categoria c = null;
        
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {
            
            ps.setInt(1, idCategoria);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Categoria();
                    c.setIdCategoria(rs.getInt("id_categoria"));
                    c.setNombre(rs.getString("nombre"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error al buscar categoría: " + e.getMessage());
        }
        
        return c;
    }
    
    // 🔹 Listar todas las categorías (lo que necesitamos para la vista)
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }
        
        return lista;
    }
}
