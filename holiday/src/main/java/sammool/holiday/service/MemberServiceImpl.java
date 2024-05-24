package sammool.holiday.service;

import java.util.Optional;

import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository;

public class MemberServiceImpl implements MemberService {
    
    private final MemberRepository repository;
    
    public MemberServiceImpl(MemberRepository repository){
        this.repository = repository;
    }

    @Override
    public Member useHoliday(String id, int days){
        Optional<Member> optionalMember = repository.findById(id);

        optionalMember.ifPresent(member -> {
            member.setLeftover_days(member.getLeftover_days() - days);
            repository.update(member);
        });

        return optionalMember.orElse(null);
    }

    @Override
    public Member minusPoint(String id, int point){
        Optional<Member> optionalMember = repository.findById(id);
        optionalMember.ifPresent(member -> {
            member.setLeftover_days(member.getPoints() - point);
            repository.update(member);
        });
        
        //비어있지 않으면 들어있는 객체 리턴 비어있으면 null 리턴
        return optionalMember.orElse(null);
    }

    @Override
    public Member pointToHoliday(String id){
        Optional<Member> optionalMember = repository.findById(id);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            while(member.getPoints() >= 20){
                member.setLeftover_days(member.getLeftover_days() + 1);
                member.setPoints(member.getPoints() - 20);
                repository.update(member);
            }

            repository.update(member);
        }
        return optionalMember.orElse(null);
    }

    @Override
    public Member plusPoint(String id, int point){
        Optional<Member> optionalMember = repository.findById(id);
        optionalMember.ifPresent(member -> {
            member.setPoints(member.getPoints() + point);
            repository.update(member);
        });
        
        return optionalMember.orElse(null);
    }
}
