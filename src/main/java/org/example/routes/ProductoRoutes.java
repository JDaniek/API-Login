package org.example.routes;

import io.javalin.Javalin;
import org.example.controllers.ProductoController;

public class ProductoRoutes {

    private final ProductoController productoController;

    public ProductoRoutes(ProductoController productoController) {
        this.productoController = productoController;
    }

    public void register(Javalin app) {
        app.get("/productos", productoController::getAll);
        app.post("/productos", productoController::create);
    }
}
