package org.example.service;

import org.example.models.Usuario;
import org.example.repositories.UsuarioRepository;

import java.sql.SQLException;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    // Lógica de registro
    public boolean registrarUsuario(Usuario usuario) throws SQLException {
        // Verificar si ya existe el correo
        if (usuarioRepository.existeCorreo(usuario.getCorreo())) {
            return false; // Correo duplicado
        }

        usuarioRepository.save(usuario); // Guardar nuevo usuario
        return true;
    }

    // Lógica de login
    public Usuario autenticar(String correo, String contrasena) throws SQLException {
        Usuario usuario = usuarioRepository.findByCorreo(correo);

        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }

        return null; // Credenciales inválidas
    }
}
