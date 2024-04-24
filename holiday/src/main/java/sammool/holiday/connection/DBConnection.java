package sammool.holiday.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import static sammool.holiday.connection.ConnectionConst.*;

@Slf4j
public class DBConnection {
    public static Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            log.info("get connection = {}, class = {}", connection,connection.getClass());
            return connection;
        }catch(SQLException e){
           throw new IllegalStateException();
            
        }
        }
}
