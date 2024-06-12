package sammool.holiday.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static sammool.holiday.connection.ConnectionConst.*;
import com.zaxxer.hikari.HikariDataSource;

import sammool.holiday.domain.Leader;

public class JdbcLeaderRepositoryTest {
    
    JdbcLeaderRepository repository;

    @BeforeEach
    void beforeEach(){
        
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new JdbcLeaderRepository(dataSource);
        repository.createTable();
    }
    

    @Test
    void save() {
        Leader leader = new Leader();
        leader.setLeader_id("23-11111111");
        leader.setPassword("test");
        leader.setDegree("병장");
        leader.setName("이후");
        repository.save(leader);
    }
}
