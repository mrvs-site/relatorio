package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/jaspercurso?serverTimeZone=America/Sao_Paulo";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection connection() {

        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.getException();
        } catch (SQLException s) {
            s.printStackTrace();
        }

        return connection;

    }


}
