package sammool.holiday.web;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository;

@Component
@RequiredArgsConstructor
public class Testdata {
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init(){
        Member member = new Member();
        member.setMember_id("23-00000000");
        member.setDegree("상병");
        member.setName("박찬규");
        member.setPassword("sa2003");

        memberRepository.save(member);
    }
}
