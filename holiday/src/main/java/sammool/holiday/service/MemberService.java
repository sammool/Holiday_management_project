package sammool.holiday.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JpaMemberRepository;
import sammool.holiday.repository.MemberRepository;
import sammool.holiday.web.form.EditForm;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
   
    private final JpaMemberRepository memberRepository;

    public void save(Member member){
        memberRepository.save(member);
    }

    public Member findMember(String memberId){
        Optional<Member> member = memberRepository.findOne(memberId);
        if(member.isEmpty()){
            throw new IllegalArgumentException("찾는 멤버가 없습니다");
        }
        return member.get();
    }

    public void updateMember(String memberId, EditForm form){
        Optional<Member> findMember = memberRepository.findOne(memberId);
        Member member = findMember.get();
        
        member.setDegree(form.getDegree());
        member.setPoints(form.getPoints());
        member.setLeftover_days(form.getLeftover_days());
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
