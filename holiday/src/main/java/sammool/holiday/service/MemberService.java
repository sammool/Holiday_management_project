package sammool.holiday.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(Member member){
        memberRepository.save(member);
    }

    public Optional<Member> findById(String id){
        Optional<Member> findMember = memberRepository.findById(id);
        /*
         * null일 경우 대처 코드
         */
        return findMember;
    }
    
}
