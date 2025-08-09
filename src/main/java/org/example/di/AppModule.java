package org.example.di;

import io.javalin.Javalin;
import org.example.controllers.ProductoController;
import org.example.routes.ProductoRoutes;
import org.example.service.ProductoService;

public class AppModule {

    public static ProductoRoutes initProducto() {
        ProductoService productoService = new ProductoService();
        ProductoController productoController = new ProductoController(productoService);
        return new ProductoRoutes(productoController);
    }

    public static void registerAll(Javalin app) {
        initProducto().register(app);
    }
}