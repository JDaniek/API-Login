package org.example;

import io.javalin.Javalin;
import org.example.di.AppModule;

public class Main {
    public static void main(String[] args) {

        // Configuración del servidor
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> rule.anyHost());
            });
        }).start( 7000);

        // Ruta de prueba
        app.get("/", ctx -> ctx.result("Hola, mundo!"));

        // ✅ Registrar todas las rutas desde AppModule
        AppModule.registerAll(app);

        System.out.println("Servidor corriendo en http://0.0.0.0:7000");
    }
}
