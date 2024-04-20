package sammool.holiday.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;
import static sammool.ConnectionConst.*;
import sammool.holiday.domain.Member;

public class JdbcMemberRepositoryTest {

    JdbcMemberRepository repository;
    
    @BeforeEach
    void beforeEach(){
        
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        repository = new JdbcMemberRepository(dataSource);
    }
    

    @Test
    void FindAll() {
        Member member = new Member("23-76030904","상병","박찬규",25,94);
        repository.save(member);
    }


   
}
