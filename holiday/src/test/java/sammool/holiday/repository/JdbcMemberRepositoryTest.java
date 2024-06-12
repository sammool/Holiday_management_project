package sammool.holiday.repository;

import static sammool.holiday.connection.ConnectionConst.*;

import org.junit.jupiter.api.BeforeEach;


import com.zaxxer.hikari.HikariDataSource;


public class JdbcMemberRepositoryTest {

    JdbcMemberRepository repository;
    
    @BeforeEach
    void beforeEach(){
        
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new JdbcMemberRepository(dataSource);
        repository.createTable();
    }
    

 

}
