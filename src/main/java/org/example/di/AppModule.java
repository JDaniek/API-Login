package org.example.di;

import io.javalin.Javalin;
import org.example.controllers.UsuarioController;
import org.example.routes.UsuarioRoutes;

public class AppModule {

    // ✅ Inicializa y devuelve UsuarioRoutes con todas sus dependencias
    public static UsuarioRoutes initUsuario() {
        UsuarioController usuarioController = new UsuarioController();
        return new UsuarioRoutes(usuarioController);
    }

    // ✅ Registra todas las rutas de la aplicación
    public static void registerAll(Javalin app) {
        initUsuario().register(app);
    }
}
