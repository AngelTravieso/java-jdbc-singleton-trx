package org.atravieso.java.jdbc.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    List<T> listar() throws SQLException;

    T porId(Long id) throws SQLException;

    // Este lo usaremos para guardar y actualizar
    void guardar(T t) throws SQLException;

    void eliminar(Long id) throws SQLException;

}
