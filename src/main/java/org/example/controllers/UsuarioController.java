package org.example.controllers;

import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTO.LoginRequestDTO;
import org.example.DTO.UsuarioRegistroDTO;
import org.example.DTO.UsuarioResponseDTO;
import org.example.models.Usuario;
import org.example.service.UsuarioService;

public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
        this.objectMapper = new ObjectMapper(); // para convertir JSON <-> objetos
    }

    // POST /api/usuarios → Registro
    public void handleRegistro(Context ctx) {
        try {
            // Leer JSON recibido como DTO
            UsuarioRegistroDTO dto = objectMapper.readValue(ctx.body(), UsuarioRegistroDTO.class);

            // Convertir a modelo
            Usuario nuevoUsuario = new Usuario(dto.getNombre(), dto.getCorreo(), dto.getContrasena());

            boolean registrado = usuarioService.registrarUsuario(nuevoUsuario);

            if (registrado) {
                UsuarioResponseDTO response = new UsuarioResponseDTO(
                    nuevoUsuario.getId(), nuevoUsuario.getNombre(), nuevoUsuario.getCorreo()
                );
                ctx.status(201).json(response);
            } else {
                ctx.status(409).result("El correo ya está registrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al registrar usuario.");
        }
    }

    // POST /api/login → Login
    public void handleLogin(Context ctx) {
        try {
            // Leer JSON recibido como DTO
            LoginRequestDTO dto = objectMapper.readValue(ctx.body(), LoginRequestDTO.class);

            Usuario usuario = usuarioService.autenticar(dto.getCorreo(), dto.getContrasena());

            if (usuario != null) {
                UsuarioResponseDTO response = new UsuarioResponseDTO(
                    usuario.getId(), usuario.getNombre(), usuario.getCorreo()
                );
                ctx.status(200).json(response);
            } else {
                ctx.status(401).result("Credenciales inválidas.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al procesar login.");
        }
    }
}
