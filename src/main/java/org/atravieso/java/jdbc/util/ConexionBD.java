package org.atravieso.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // url de conexión (serverTimeZone? opcional)
    private static final String url = "jdbc:mysql://localhost:3306/java_curso?serverTimeZone=UTC";
    private static final String username = "root"; // usuario
    private static final String password = "root"; // password

    private static Connection connection;

    // Para manejar Singleton de conexión
    public static Connection getInstance() throws SQLException {
        if(connection == null) {
            connection = DriverManager.getConnection(url, username,password);
        }
        return connection;
    }

}
