package org.atravieso.java.jdbc.repository;

import java.util.List;

public interface Repository<T> {

    List<T> listar();

    T porId(Long id);

    // Este lo usaremos para guardar y actualizar
    void guardar(T t);

    void eliminar(Long id);

}
