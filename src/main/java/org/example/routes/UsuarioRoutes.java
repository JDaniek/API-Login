package org.example.routes;

import io.javalin.Javalin;
import org.example.controllers.UsuarioController;

public class UsuarioRoutes {

    private final UsuarioController usuarioController;

    public UsuarioRoutes(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void register(Javalin app) {
        app.post("/api/usuario", usuarioController::handleRegistro); // Registro
        app.post("/api/login", usuarioController::handleLogin);       // Login
    }
}
