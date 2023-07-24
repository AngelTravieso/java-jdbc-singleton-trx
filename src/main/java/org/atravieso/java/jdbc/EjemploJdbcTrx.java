package org.atravieso.java.jdbc;

import org.atravieso.java.jdbc.models.Categoria;
import org.atravieso.java.jdbc.models.Producto;
import org.atravieso.java.jdbc.repository.ProductoRepositoryImpl;
import org.atravieso.java.jdbc.repository.Repository;
import org.atravieso.java.jdbc.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcTrx {
    public static void main(String[] args) throws SQLException {

        try (
             // Conexión a la BD (clase personalizada
             Connection conn = ConexionBD.getInstance()) {

            // Verificar si la propiedad getAutocommit es true
            if (conn.getAutoCommit()) {
                // Si es true, lo deshabilitamos, esto para que en caso de que haya un error en la transacción no guarda nada, son todas las operaciones o nada
                conn.setAutoCommit(false);
            }

            try {
                Repository<Producto> repository = new ProductoRepositoryImpl();
                System.out.println("================= LISTAR ===========");
                repository.listar().forEach(System.out::println);

                System.out.println("=================== OBTENER POR ID ==================");
                System.out.println(repository.porId(11L));

                System.out.println("================= INSERTAR NUEVO PRODUCTO ===========");
                Producto producto = new Producto();
                producto.setNombre("Balón de basketbol");
                producto.setPrecio(112);
                producto.setFechaRegistro(new Date());
                Categoria categoria = new Categoria();
                categoria.setId(3L); // categoría => computación
                producto.setCategoria(categoria);
                producto.setSku("abcde12344");
                repository.guardar(producto);
                System.out.println("Producto guardado con éxito");

                System.out.println("================= ACTUALIZAR PRODUCTO ===========");
                producto = new Producto();
                producto.setId(12L);
                producto.setNombre("Teclado Corsair mecánico");
                producto.setPrecio(1000);
                producto.setSku("abcdef1846");
                categoria = new Categoria();
                categoria.setId(2L);
                producto.setCategoria(categoria);
                repository.guardar(producto);
                System.out.println("Producto editado con éxito");

                repository.listar().forEach(System.out::println);

                // Si sale bien hacemos el commit de la transacción
                conn.commit();
            } catch(SQLException e) {
                // si hay algún error hacemos rollback de toda la transacción
                conn.rollback(); // => los rollbacks son para sentencias DML
                e.printStackTrace();
            }
        }
    }
}