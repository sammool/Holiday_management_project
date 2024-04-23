package sammool.holiday.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcMemberRepository;
import sammool.holiday.repository.MemberRepository;

import com.zaxxer.hikari.HikariDataSource;
import static sammool.ConnectionConst.*;

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
        Member member = new Member("23-76030904","상병","박찬규",25,94);
        memberRepository.save(member);
        Member updatedMember = memberService.plusPoint(member.getMember_id(), 5);
        Assertions.assertThat(updatedMember.getPoints()).isEqualTo(99);

        Member member2 = memberService.pointToHoliday(updatedMember.getMember_id());
        Assertions.assertThat(member2.getPoints()).isEqualTo(19);
        Assertions.assertThat(member2.getLeftover_days()).isEqualTo(29);
        
    }
}
