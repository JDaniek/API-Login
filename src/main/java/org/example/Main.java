package org.example;

import io.javalin.Javalin;
import org.example.controllers.UsuarioController; // <-- ✅ IMPORTANTE
import org.example.routes.UsuarioRoutes;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> rule.anyHost()); // permite peticiones desde cualquier origen (útil para pruebas)
            });
        }).start("0.0.0.0", 7000);

        app.get("/", ctx -> ctx.result("Hola, mundo!"));

        // ✅ Instanciamos el controlador y las rutas
        UsuarioController usuarioController = new UsuarioController();
        UsuarioRoutes usuarioRoutes = new UsuarioRoutes(usuarioController);
        usuarioRoutes.register(app);

        System.out.println("Servidor corriendo en http://0.0.0.0:7000");
    }
}