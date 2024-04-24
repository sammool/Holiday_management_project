package sammool.holiday.repository;

import static sammool.holiday.connection.ConnectionConst.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcMemberRepository;

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
    

    @Test
    void FindAll() {
        Member member = new Member("23-76030904","상병","박찬규",25,94);
        Member savedMember = repository.save(member);
        Assertions.assertThat(savedMember.getMember_id()).isEqualTo("23-76030904");
    }


   
}
