package sammool.holiday.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.HolidayApplication;
import sammool.holiday.domain.Leader;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JpaLeaderRepository;
import sammool.holiday.repository.JpaMemberRepository;
import sammool.holiday.service.LeaderService;
import sammool.holiday.service.MemberService;

@Slf4j
@Component
public class Testdata {
    private final MemberService memberService;
    private final LeaderService leaderService;

    @Autowired
    public Testdata(MemberService memberService, LeaderService leaderService) {
        this.memberService = memberService;
        this.leaderService = leaderService;
    }

    @PostConstruct
    @Transactional
    public void init(){
        Member member = new Member();
        member.setMember_id("23-00000000");
        member.setDegree("상병");
        member.setName("박찬규");
        member.setPassword("sa2003");

        memberService.save(member);

        Leader leader = new Leader();
        leader.setLeader_id("23-11111111");
        leader.setPassword("test");
        leader.setDegree("병장");
        leader.setName("이후");
        leaderService.save(leader);
        log.info("@PostContruct");
    }
}
