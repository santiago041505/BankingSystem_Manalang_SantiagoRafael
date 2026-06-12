/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author easterPC
 */
public class DBConnection {
     private static Connection connection = null;

    public static Connection getConnection() {
        try {
            
            if (connection == null || connection.isClosed()) {
                
                               
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/banking",
                    "root",
                    "kissmuna123"
                );
            }
        } catch (SQLException e) {
            System.err.println("CRITICAL: Failed to connect to the database!");
            e.printStackTrace();
        }
        return connection; 
    }
}
