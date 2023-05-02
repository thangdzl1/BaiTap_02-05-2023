package config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConfig {
    public static final String url = "jdbc:mysql://localhost:3308/crm_app";
    public static final String username = "root";
    public static final String password = "admin123";

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,username,password);
        }catch (ClassNotFoundException e){
            System.out.println("Không tìm thấy Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Không tìm thấy db");
            e.printStackTrace();
        }
        return null;
    }
}
