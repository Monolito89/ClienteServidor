package Controlador;

import Modelo.Usuario;
import Modelo.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
/**
 *
 * @author roman
 */
public class CtrlUsuarios {
 
    //Aqui se trabajara todo lo relacionado con el login y el registro
    private final Conexion conexion = new Conexion();
    private Usuario usuarioActual = null;
    
    //metodo que valida que el correo tenga un @ y un .
    private boolean validarCorreo(String correo) {
        return correo != null && correo.contains("@") && correo.contains(".");
    }
    
    //metodo que valida que el correo existe
    private boolean correoExiste(String correo) {
       String sql = "SELECT 1 FROM clientes WHERE correo = ? LIMIT 1";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
    
      // Método que valida si el correo ya existe en la tabla colaboradores
    private boolean correoColaboradorExiste(String correo) {
        String sql = "SELECT 1 FROM colaboradores WHERE correo = ? LIMIT 1";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // En caso de error, devolvemos true para no permitir el registro
            return true;
        }
    }
  
        // Método para guardar un administrador en la tabla colaboradores
    private boolean guardarAdminEnBD(String nombre, String correo, String passwordHash) {
        String sql = "INSERT INTO colaboradores (nombre, correo, password, rol) VALUES (?, ?, ?, ?)";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, passwordHash);
            ps.setString(4, "admin"); // rol fijo para administradores

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //metodo para guardar el usuario en la base de datos
    private boolean guardarUsuarioEnBD(String nombre, String correo, String passwordHash) {
        String sql = "INSERT INTO clientes (nombre, correo, password) VALUES (?, ?, ?)";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, passwordHash);

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

   // Método para registrar administradores (usa la misma lógica que registrarUsuarios)
public boolean registrarAdmin(String nombre, String correo, char[] contrasena, char[] confirmar) {

    // Validación de campos vacíos
    if (nombre == null || nombre.isBlank()
            || correo == null || correo.isBlank()
            || contrasena == null || contrasena.length == 0
            || confirmar == null || confirmar.length == 0) {
        System.out.println("Error: Los campos se encuentran vacíos");
        return false;
    }

    // Validación de coincidencia de contraseñas
    if (!Arrays.equals(contrasena, confirmar)) {
        System.out.println("Error: Las contraseñas no coinciden");
        Arrays.fill(contrasena, '\0');
        Arrays.fill(confirmar, '\0');
        return false;
    }

    // Validación de formato de correo
    if (!validarCorreo(correo)) {
        System.out.println("Error: Correo inválido");
        Arrays.fill(contrasena, '\0');
        Arrays.fill(confirmar, '\0');
        return false;
    }

    // NUEVO: validar que el correo sea corporativo @tienda.com
    String correoLower = correo.toLowerCase();
    if (!correoLower.endsWith("@tienda.com")) {
        System.out.println("Error: Solo se permiten correos @tienda.com para administradores");
        Arrays.fill(contrasena, '\0');
        Arrays.fill(confirmar, '\0');
        return false;
    }

    // Validación de correo ya registrado como colaborador
    if (correoColaboradorExiste(correo)) {
        System.out.println("Error: El correo de administrador ya se encuentra registrado");
        Arrays.fill(contrasena, '\0');
        Arrays.fill(confirmar, '\0');
        return false;
    }

    try {
        // Generación del hash de la contraseña
        String hashHex = hashPassword(contrasena);

        // Limpiar contraseñas en memoria
        Arrays.fill(contrasena, '\0');
        Arrays.fill(confirmar, '\0');

        // Guardar admin en base de datos
        return guardarAdminEnBD(nombre, correo, hashHex);
    } catch (Exception e) {
        e.printStackTrace();
        Arrays.fill(contrasena, '\0');
        Arrays.fill(confirmar, '\0');
        return false;
    }
}
    //metodo para calcular el hash en SHA-256
    private String hashPassword(char[] password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] pwdBytes = new String(password).getBytes(StandardCharsets.UTF_8);
            byte[] digest = md.digest(pwdBytes);
            return bytesToHex(digest);
        } catch (Exception e) {
            throw new RuntimeException("Error al hashear", e);
        }
    }

    //metodo que a partir del hash lo convierte en hexadecimal, se ocupa para la Base de Datos en esta forma
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    //metodo que compara dos hashes diferentes
    private static boolean constantTimeEquals(String aHex, String bHex) {
        if (aHex == null || bHex == null) return false;
        if (aHex.length() != bHex.length()) return false;
        int result = 0;
        for (int i = 0; i < aHex.length(); i++) {
            result |= aHex.charAt(i) ^ bHex.charAt(i);
        }
        return result == 0;
    }
    
    public boolean registrarUsuarios(String nombre, String correo, char[] contrasena, char[] confirmar){
        
        //validacion para que no este vacio
        if (nombre == null || nombre.isBlank()||
                correo == null || correo.isBlank()||
                contrasena == null || contrasena.length == 0 
                ||confirmar == null || confirmar.length == 0){
            System.out.println("Error: Los campos se encuentran vacíos");
            return false;
        }
        
        //Validacion para que coincidan las contraseñas
        if (!Arrays.equals(contrasena, confirmar)){
            System.out.println("Error: Las contraseñas no coinciden");
            Arrays.fill(contrasena, '\0');
            Arrays.fill(confirmar, '\0');
            return false;
        }
        
        //Validacion para el correo electronico
        if(!(validarCorreo(correo))){
            System.out.println("Erro: Correo Invalido");
            Arrays.fill(contrasena, '\0');
            Arrays.fill(confirmar, '\0');
            return false;
        }
        
       //validacion para saber si el correo ya esta registrado 
       if(correoExiste(correo)){
           System.out.println("Error: El correo ya se encuentra registrado");
           Arrays.fill(contrasena, '\0');
           Arrays.fill(confirmar, '\0');
           return false;
       }
       
       try {
           //Generacion del hash para la contraseña
           String hashHex = hashPassword(contrasena);
           
           //Limpiar la contraseña que se encuentre en memoria
           Arrays.fill(contrasena, '\0');
           Arrays.fill(confirmar, '\0');
           
           //Finalmente, se guarda en la base de datos
           return guardarUsuarioEnBD(nombre, correo, hashHex);
       } catch (Exception e) {
           e.printStackTrace();
           Arrays.fill(contrasena, '\0');
           Arrays.fill(confirmar, '\0');
           return false;
       }
    }
    
    public Usuario iniciarSesion(String correo, char[] contrasena){
    // Validación de campos vacíos
    if (correo == null || correo.isBlank() || contrasena == null || contrasena.length == 0) {
        System.out.println("Error: correo o contraseña vacíos");
        Arrays.fill(contrasena, '\0');
        return null;
    }

    String sql = "SELECT id_cliente, nombre, password FROM clientes WHERE correo = ?";
    try (Connection con = conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, correo);

        try (ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) {
                System.out.println("Error: correo no encontrado");
                Arrays.fill(contrasena, '\0');
                return null;
            }

            String storedHashHex = rs.getString("password");
            if (storedHashHex == null || storedHashHex.isBlank()) {
                Arrays.fill(contrasena, '\0');
                return null;
            }

            // Calcula el hash de la contraseña ingresada
            String computedHashHex = hashPassword(contrasena);

            // Limpiar contraseña en memoria
            Arrays.fill(contrasena, '\0');

            // Comparar en tiempo constante
            if (constantTimeEquals(storedHashHex, computedHashHex)) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_cliente"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(correo);

                // 🔹 Marcamos el rol como CLIENTE
                u.setRol("cliente");

                this.usuarioActual = u;
                System.out.println("Login exitoso para CLIENTE: " + u.getNombre());
                return u;
            } else {
                System.out.println("Error: contraseña incorrecta");
                return null;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        Arrays.fill(contrasena, '\0');
        return null;
    } catch (Exception ex) {
        ex.printStackTrace();
        Arrays.fill(contrasena, '\0');
        return null;
    }
}
    
    //metodo para iniciar sesion por nombre de usuario
    
    
    public Usuario iniciarSesionPorUsuario(String nombreUsuario, char[] contrasena) {
    // Validación de campos vacíos
    if (nombreUsuario == null || nombreUsuario.isBlank() || contrasena == null || contrasena.length == 0) {
        System.out.println("Error: nombre de usuario o contraseña vacíos");
        Arrays.fill(contrasena, '\0');
        return null;
    }

    String sql = "SELECT id_cliente, nombre, correo, password FROM clientes WHERE nombre = ?";
    try (Connection con = conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombreUsuario);

        try (ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) {
                System.out.println("Error: usuario no encontrado");
                Arrays.fill(contrasena, '\0');
                return null;
            }

            String storedHashHex = rs.getString("password");
            if (storedHashHex == null || storedHashHex.isBlank()) {
                Arrays.fill(contrasena, '\0');
                return null;
            }

            // Calcula el hash de la contraseña ingresada
            String computedHashHex = hashPassword(contrasena);

            // Limpiar memoria
            Arrays.fill(contrasena, '\0');

            // Comparación segura
            if (constantTimeEquals(storedHashHex, computedHashHex)) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_cliente"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));

                // 🔹 Asigna el rol CLIENTE
                u.setRol("cliente");

                this.usuarioActual = u;
                System.out.println("Login exitoso para CLIENTE (usuario): " + u.getNombre());
                return u;
            } else {
                System.out.println("Error: contraseña incorrecta");
                return null;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        Arrays.fill(contrasena, '\0');
        return null;
    } catch (Exception ex) {
        ex.printStackTrace();
        Arrays.fill(contrasena, '\0');
        return null;
    }
}

    
     // --- INICIO DE SESIÓN PARA ADMIN (colaboradores) POR CORREO ---
    public Usuario iniciarSesionAdminPorCorreo(String correo, char[] contrasena) {
    // Validar campos vacíos
    if (correo == null || correo.isBlank() || contrasena == null || contrasena.length == 0) {
        System.out.println("Error: correo o contraseña vacíos (admin)");
        Arrays.fill(contrasena, '\0');
        return null;
    }

    String sql = "SELECT id_colaborador, nombre, correo, password FROM colaboradores WHERE correo = ?";
    try (Connection con = conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, correo);

        try (ResultSet rs = ps.executeQuery()) {

            if (!rs.next()) {
                System.out.println("Error: admin no encontrado");
                Arrays.fill(contrasena, '\0');
                return null;
            }

            String storedHashHex = rs.getString("password");
            if (storedHashHex == null || storedHashHex.isBlank()) {
                Arrays.fill(contrasena, '\0');
                return null;
            }

            // Hash de la contraseña digitada
            String computedHashHex = hashPassword(contrasena);

            // Limpiar contraseña de memoria
            Arrays.fill(contrasena, '\0');

            // Comparación segura de hashes
            if (constantTimeEquals(storedHashHex, computedHashHex)) {

                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_colaborador"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));

                // 🔹 Asignar el rol ADMIN
                u.setRol("admin");

                this.usuarioActual = u;
                System.out.println("Login admin exitoso para: " + u.getNombre());

                return u;

            } else {
                System.out.println("Error: contraseña incorrecta (admin)");
                return null;
            }
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        Arrays.fill(contrasena, '\0');
        return null;

    } catch (Exception ex) {
        ex.printStackTrace();
        Arrays.fill(contrasena, '\0');
        return null;
    }
}


    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    
}
