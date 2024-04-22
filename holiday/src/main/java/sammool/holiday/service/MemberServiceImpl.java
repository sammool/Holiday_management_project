package sammool.holiday.service;

import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository;

public class MemberServiceImpl implements MemberService {
    
    private final MemberRepository repository;
    
    public MemberServiceImpl(MemberRepository repository){
        this.repository = repository;
    }

    @Override
    public void useHoliday(String id, int days){
        Member member = repository.findById(id);
        member.setLeftover_days(member.getLeftover_days()-days);
        repository.update(member);
    }

    @Override
    public void minusPoint(String id, int point){
        Member member = repository.findById(id);
        member.setPoints(member.getPoints() - point);
        repository.update(member);
    }

    @Override
    public void pointToHoliday(String id){
        Member member = repository.findById(id);
        while(member.getPoints() >= 20){
            member.setLeftover_days(member.getLeftover_days() + 1);
            member.setPoints(member.getPoints() - 20);
            repository.update(member);
        }
        repository.update(member);
    
    }

    @Override
    public Member plusPoint(String id, int point){
        Member member = repository.findById(id);
        member.setPoints(member.getPoints() + point);
        repository.update(member);
        
        // if(member.getPoints() >= 20){
        //     pointToHoliday(id);
        //     return;
        // }
       
        return member;
    }
}
