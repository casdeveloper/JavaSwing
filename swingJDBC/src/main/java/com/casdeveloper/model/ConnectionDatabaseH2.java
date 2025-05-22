package com.casdeveloper.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabaseH2 implements ConnectionDatabase {

    private static final String url = "jdbc:h2:file:./data/dataDb";

    private static final String driver = "org.h2.Driver";

    private static final String user = "sa";

    private static final String password = "123456";

    @Override
    public Connection getConnection() {
        System.out.println("Connecting to database...");
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load JDBC driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Could not connect to database");
            throw new RuntimeException(e);
        }

        return null;
    }

}
