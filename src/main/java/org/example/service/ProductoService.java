package org.example.service;

import org.example.config.Database;
import org.example.models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    public List<Producto> getAll() throws Exception {
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = Database.getDataSource().getConnection()) {
            String sql = "SELECT * FROM producto";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();
                    p.setId(rs.getInt("idproducto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setStock(rs.getInt("stock"));
                    p.setPrecio(rs.getFloat("precio"));
                    productos.add(p);
                }
            }
        }
        return productos;
    }

    public void create(Producto producto) throws Exception {
        try (Connection conn = Database.getDataSource().getConnection()) {
            String sql = "INSERT INTO producto(nombre, descripcion, stock, precio) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, producto.getNombre());
                ps.setString(2, producto.getDescripcion());
                ps.setInt(3, producto.getStock());
                ps.setFloat(4, producto.getPrecio());
                ps.executeUpdate();
            }
        }
    }
}
