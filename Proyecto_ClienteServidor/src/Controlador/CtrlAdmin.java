/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;
import java.sql.*;

/**
 *
 * @author Dilan
 */
public class CtrlAdmin {

    private Producto proTemp = new Producto();
    private Conexion conexion = new Conexion();

    public CtrlAdmin() {
    }

    public Producto getProTemp() {
        return proTemp;
    }

    public void setProTemp(Producto proTemp) {
        this.proTemp = proTemp;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }
    
    // MÉTODO 1: Registrar PRODUCTO 
    public boolean registrar(Producto pro) {

        String sql = "INSERT INTO productos (nombre, descripcion, precio, descuento, stock, id_categoria, id_proveedor) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pro.getNombre());
            ps.setString(2, pro.getDescripcion());
            ps.setDouble(3, pro.getPrecio());
            ps.setDouble(4, pro.getDescuento());
            ps.setInt(5, pro.getStock());
            ps.setInt(6, pro.getIdCategoria());
            ps.setInt(7, pro.getIdProveedor());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();    // Te muestra el error real
            return false;
        }
    }

    
    // MÉTODO 2: MODIFICAR (ACTUALIZAR) PRODUCTO 
    public boolean modificar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();

        // SQL
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, descuento=?, stock=?, id_categoria=?, id_proveedor=? WHERE id_producto=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setString(2, pro.getDescripcion());
            ps.setDouble(3, pro.getPrecio());
            ps.setDouble(4, pro.getDescuento());
            ps.setInt(5, pro.getStock());
            ps.setInt(6, pro.getIdCategoria());
            ps.setInt(7, pro.getIdProveedor());
            ps.setInt(8, pro.getIdProducto());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // MÉTODO 3: ELIMINAR PRODUCTO 
    public boolean eliminar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = conexion.getConexion();

        // SQL 
        String sql = "DELETE FROM productos WHERE id_producto=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getIdProducto());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // MÉTODO 4: BUSCAR PRODUCTO POR CÓDIGO 
    public boolean buscar(Producto pro) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conexion.getConexion();

        // SQL 
        String sql = "SELECT * FROM productos WHERE id_producto=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getIdProducto());
            rs = ps.executeQuery();

            if (rs.next()) {
                // Llenar el objeto Producto con datos de la BD - IGUAL que el ejemplo
                pro.setIdProducto(rs.getInt("id_producto"));
                pro.setNombre(rs.getString("nombre"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio(rs.getDouble("precio"));
                pro.setDescuento(rs.getDouble("descuento"));
                pro.setStock(rs.getInt("stock"));
                pro.setIdCategoria(rs.getInt("id_categoria"));
                pro.setIdProveedor(rs.getInt("id_proveedor"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    // MÉTODO 5: LISTAR TODOS LOS PRODUCTOS (para tabla de inventario)
    public java.util.List<Producto> listarProductos() {
        java.util.List<Producto> lista = new java.util.ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conexion.getConexion();

        String sql = "SELECT id_producto, nombre, stock, precio FROM productos";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto pro = new Producto();
                pro.setIdProducto(rs.getInt("id_producto"));
                pro.setNombre(rs.getString("nombre"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
                lista.add(pro);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e);
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return lista;
    }
}
