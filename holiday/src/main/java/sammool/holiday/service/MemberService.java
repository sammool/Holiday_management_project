package sammool.holiday.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JpaMemberRepository;
import sammool.holiday.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
   
    private final JpaMemberRepository memberRepository;

    public void save(Member member){
        memberRepository.save(member);
    }

    public void register(String memberId, String degree, String name, String password){
        Member member = new Member();
        member.setMember_id(memberId);
        member.setDegree(degree);
        member.setName(name);
        member.setPassword(password);

        memberRepository.save(member);
        
    }
   
}
