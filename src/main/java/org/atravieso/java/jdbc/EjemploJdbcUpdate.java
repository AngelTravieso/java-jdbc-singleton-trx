package org.atravieso.java.jdbc;

import org.atravieso.java.jdbc.models.Categoria;
import org.atravieso.java.jdbc.models.Producto;
import org.atravieso.java.jdbc.repository.ProductoRepositoryImpl;
import org.atravieso.java.jdbc.repository.Repository;
import org.atravieso.java.jdbc.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcUpdate {
    public static void main(String[] args) {

        try (
             // Conexión a la BD (clase personalizada
             Connection conn = ConexionBD.getInstance()) {

            Repository<Producto> repository = new ProductoRepositoryImpl();
            System.out.println("========================== LISTAR ==================");

            repository.listar().forEach(System.out::println);

            System.out.println("=================== OBTENER POR ID ==================");
            System.out.println(repository.porId(12L));

            System.out.println("================= ACTUALIZAR PRODUCTO ===========");
            Producto producto = new Producto();
            producto.setId(12L);
            producto.setNombre("Teclado Corsair mecánico");
            producto.setPrecio(785);

            Categoria categoria = new Categoria();
            categoria.setId(2L);

            producto.setCategoria(categoria);

            repository.guardar(producto);
            System.out.println("Producto editado con éxito");
            repository.listar().forEach(System.out::println);

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
}