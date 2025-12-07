/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;

public class ConsultasProducto extends Conexion {

    // MÉTODO 1: REGISTRAR (INSERTAR) PRODUCTO - IGUAL que el ejemplo de la profe
    public boolean registrar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        // SQL 
        String sql = "INSERT INTO productos (codigo, nombre, descripcion, precio, descuento, stock, id_categoria, id_proveedor) VALUES (?,?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getDescripcion());
            ps.setDouble(4, pro.getPrecio());
            ps.setDouble(5, pro.getDescuento());
            ps.setInt(6, pro.getStock());
            ps.setInt(7, pro.getIdCategoria());
            ps.setInt(8, pro.getIdProveedor());
            
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

    // MÉTODO 2: MODIFICAR (ACTUALIZAR) PRODUCTO 
    public boolean modificar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        // SQL
        String sql = "UPDATE productos SET codigo=?, nombre=?, descripcion=?, precio=?, descuento=?, stock=?, id_categoria=?, id_proveedor=? WHERE id_producto=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getDescripcion());
            ps.setDouble(4, pro.getPrecio());
            ps.setDouble(5, pro.getDescuento());
            ps.setInt(6, pro.getStock());
            ps.setInt(7, pro.getIdCategoria());
            ps.setInt(8, pro.getIdProveedor());
            ps.setInt(9, pro.getIdProducto());
            
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
        Connection con = getConexion();

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
        Connection con = getConexion();

        // SQL 
        String sql = "SELECT * FROM productos WHERE codigo=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();

            if (rs.next()) {
                // Llenar el objeto Producto con datos de la BD - IGUAL que el ejemplo
                pro.setIdProducto(rs.getInt("id_producto"));
                pro.setCodigo(rs.getString("codigo"));
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
}