package org.example.repositories;

import org.example.config.Database;
import org.example.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {

    // Guarda un nuevo usuario en la base de datos
    public void save(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuario (nombre, correo, contrasena) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getContrasena());

            stmt.executeUpdate();
        }
    }

    // Busca un usuario por su correo
    public Usuario findByCorreo(String correo) throws SQLException {
        String query = "SELECT * FROM usuario WHERE correo = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("idusuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                return usuario;
            }
        }

        return null; // Usuario no encontrado
    }

    // Verifica si un correo ya está registrado (reutiliza findByCorreo)
    public boolean existeCorreo(String correo) throws SQLException {
        return findByCorreo(correo) != null;
    }
}
