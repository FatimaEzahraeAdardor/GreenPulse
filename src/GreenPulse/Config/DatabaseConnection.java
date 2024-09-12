package GreenPulse.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static Connection connection;
    private String url ="jdbc:postgresql://localhost:5432/postgres";
    private String user = "GreenPulse";
    private String password = "";
     private   DatabaseConnection(){
         try {
             connection = DriverManager.getConnection(url , user , password);
             System.out.println("Connected to the database.");
         }catch (SQLException e){
             System.out.println("Failed to make connection" + e.getMessage());
         }
     }
     public  static DatabaseConnection getInstance(){
         if(instance == null){
             instance = new DatabaseConnection();
         }
         return instance;

     }
     public static Connection getConnection(){
         return connection;
     }
}
