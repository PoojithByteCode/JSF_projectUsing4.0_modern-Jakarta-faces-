package com.poojithkalyan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * -------------------------------------------------------
 * DBConnection Utility Class
 * -------------------------------------------------------
 * Responsible for creating a connection with MySQL.
 * All DAO classes will use this class.
 * -------------------------------------------------------
 */
public class Dbconnection{
	
    // Database URL
     private static final String URL =
            "jdbc:mysql://localhost:3306/user_portalbase";

    private static final String USERNAME = "root";

    // Change this to your MySQL password
    private static final String PASSWORD = "root$$44";

    /**
     * Returns a database connection.
     */
    public static Connection getConnection() {

        Connection connection = null;

        try {

            System.out.println("-----------------------------------");
            System.out.println("Loading MySQL JDBC Driver...");

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Driver Loaded Successfully.");

            System.out.println("Connecting to Database...");

            connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD);

            System.out.println("Database Connected Successfully.");
            System.out.println("-----------------------------------");

        } catch (ClassNotFoundException e) {

            System.out.println("JDBC Driver Not Found!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return connection;
    }
}
