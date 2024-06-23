package sammool.holiday.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository;

@Slf4j
@Service
@Transactional
public class MemberService {
    
    private final MemberRepository repository;
    
    public MemberService(MemberRepository repository){
        this.repository = repository;
    }

    public void applyHoliday(String member_id, int days){
        Optional<Member> optionalMember = repository.findById(member_id);
        
        if(!optionalMember.isPresent()){
            throw new IllegalArgumentException("해당 회원을 찾을 수 없습니다: " + member_id);
        }
        
        Member member = optionalMember.get();
        if(member.getLeftover_days()>=days){
             member.setLeftover_days(member.getLeftover_days() - days);
             log.info("{}님이 휴가를 {}일 신청하셨습니다.", member.getName(), days);
             repository.addApplyMember(member);
             repository.update(member);
        }
        else{
            throw new IllegalArgumentException("휴가 일수가 남은 일수보다 많습니다.");
        }
    }
}
