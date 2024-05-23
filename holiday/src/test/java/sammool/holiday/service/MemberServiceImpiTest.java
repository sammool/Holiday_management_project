package sammool.holiday.service;

import static sammool.holiday.connection.ConnectionConst.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcMemberRepository;
import sammool.holiday.repository.MemberRepository;

import com.zaxxer.hikari.HikariDataSource;

public class MemberServiceImpiTest {
   

   private JdbcMemberRepository memberRepository;
   private MemberServiceImpl memberService;

   @BeforeEach
   void before(){
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);

    memberRepository = new JdbcMemberRepository(dataSource);
    memberService = new MemberServiceImpl(memberRepository);
    memberRepository.createTable();
   }

    @Test
    void testUseHoliday() {
        
    }
}
