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
    public static void main(String[] args) {

        try (
             // Conexión a la BD (clase personalizada
             Connection conn = ConexionBD.getInstance()) {

            Repository<Producto> repository = new ProductoRepositoryImpl();

            System.out.println("=================== OBTENER POR ID ==================");
            System.out.println(repository.porId(2L));

            System.out.println("================= INSERTAR NUEVO PRODUCTO ===========");
            Producto producto = new Producto();
            producto.setNombre("Balón de basketbol");
            producto.setPrecio(112);
            producto.setFechaRegistro(new Date());
            Categoria categoria = new Categoria();
            categoria.setId(3L); // categoría => computación
            producto.setCategoria(categoria);
            producto.setSku("abcd12345");
            repository.guardar(producto);
            System.out.println("Producto guardado con éxito");

            System.out.println("================= ACTUALIZAR PRODUCTO ===========");
            producto = new Producto();
            producto.setId(12L);
            producto.setNombre("Teclado Corsair mecánico");
            producto.setPrecio(1000);
            producto.setSku("abcd12345");
            categoria = new Categoria();
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