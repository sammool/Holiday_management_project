package sammool.holiday;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository;

@SpringBootTest
public class MemberRepositoryTest {
    
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception{
        Member member = new Member();
        member.setNumber("23-76030904");
        memberRepository.save(member);
        
    }

}
