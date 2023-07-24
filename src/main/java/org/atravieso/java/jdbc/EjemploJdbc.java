package org.atravieso.java.jdbc;

import org.atravieso.java.jdbc.models.Categoria;
import org.atravieso.java.jdbc.models.Producto;
import org.atravieso.java.jdbc.repository.ProductoRepositoryImpl;
import org.atravieso.java.jdbc.repository.Repository;
import org.atravieso.java.jdbc.util.ConexionBD;

import java.sql.*;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) {

        try (
             // Conexión a la BD (clase personalizada
             Connection conn = ConexionBD.getInstance()) {

            Repository<Producto> repository = new ProductoRepositoryImpl();
            System.out.println("========================== LISTAR ==================");

            repository.listar().forEach(System.out::println);

            System.out.println("=================== OBTENER POR ID ==================");
            System.out.println(repository.porId(2L));

            System.out.println("================= INSERTAR NUEVO PRODUCTO ===========");
            Producto producto = new Producto();
            producto.setNombre("Balón");
            producto.setPrecio(112);
            producto.setFechaRegistro(new Date());

            Categoria categoria = new Categoria();
            categoria.setId(3L); // categoría => computación
            producto.setCategoria(categoria);

            repository.guardar(producto);
            System.out.println("Producto guardado con éxito");
            repository.listar().forEach(System.out::println);

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
}