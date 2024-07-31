package sammool.holiday.web;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Leader;
import sammool.holiday.domain.Member;
import sammool.holiday.service.HolidayService;
import sammool.holiday.service.LeaderService;
import sammool.holiday.service.MemberService;
import sammool.holiday.web.form.HolidayApplyForm;

@Slf4j
@Component
public class Testdata {
    private final MemberService memberService;
    private final LeaderService leaderService;
    private final HolidayService holidayService;

    @Autowired
    public Testdata(MemberService memberService, LeaderService leaderService, HolidayService holidayService) {
        this.memberService = memberService;
        this.leaderService = leaderService;
        this.holidayService = holidayService;
    }

    @PostConstruct
    @Transactional
    public void init(){
        //멤버 생성
        Member member = new Member();
        member.setMember_id("23-00000000");
        member.setDegree("상병");
        member.setName("박찬규");
        member.setPassword("sa2003");
        member.setLeftover_days(10);

        memberService.save(member);

        //리더 생성
        Leader leader = new Leader();
        leader.setLeader_id("23-11111111");
        leader.setPassword("test");
        leader.setDegree("병장");
        leader.setName("이후");

        leaderService.save(leader);

        //휴가 신청
        HolidayApplyForm form = new HolidayApplyForm();
        form.setHolidayDays(4);
        form.setHolidayKind("PRIZE");
        form.setStartDate( LocalDate.of(2024, 7, 22));
        form.setEndDate(LocalDate.of(2024, 7, 25));

        holidayService.applyHoliday(member.getMember_id(), leader.getLeader_id(), form);

        log.info("@PostContruct");
    }
}
