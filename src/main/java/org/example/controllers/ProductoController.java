package org.example.controllers;

import io.javalin.http.Context;
import org.example.models.Producto;
import org.example.service.ProductoService;

import java.util.List;

public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void getAll(Context ctx) {
        try {
            List<Producto> productos = productoService.getAll();
            ctx.json(productos);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al obtener productos");
        }
    }

 
    public void create(Context ctx) {
        try {
            Producto producto = ctx.bodyAsClass(Producto.class);
            productoService.create(producto);
            ctx.status(201).result("Producto creado");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear producto");
        }
    }
}
