package sammool.holiday.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;



public class DBConnection {
    public static final String URL = "jdbc:h2:mem:testdb";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";

    @Test
    Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return connection;
        }catch(SQLException e){
           throw new IllegalStateException();
            
        }
    }
}
