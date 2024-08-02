package sammool.holiday.service;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import sammool.holiday.domain.Holiday;
import sammool.holiday.domain.HolidayKind;
import sammool.holiday.domain.HolidayStatus;
import sammool.holiday.domain.Leader;
import sammool.holiday.domain.LeaderConst;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JpaLeaderRepository;
import sammool.holiday.repository.JpaMemberRepository;
import sammool.holiday.web.form.HolidayApplyForm;

@SpringBootTest
@Transactional
public class HolidayServiceTest {
    
    @Autowired
     HolidayService holidayService;

    @Autowired
     JpaMemberRepository memberRepository;

    @Autowired
     JpaLeaderRepository leaderRepository;
    
    private HolidayApplyForm form;

    @Test
    void apply() {
        //given
        Member member = setMember("23-00000000");
        Leader leader = setLeader();

        HolidayApplyForm form = new HolidayApplyForm();
        setHolidayForm(form);

        memberRepository.save(member);
        leaderRepository.save(leader);

        //when
        Holiday holiday = holidayService.applyHoliday(member.getMember_id(), leader.getLeader_id(),form);
        
        //then
        Assertions.assertThat(holiday.getMember().getName()).isEqualTo("sammool");
        Assertions.assertThat(member.getLeftover_days()).isEqualTo(6);
        Assertions.assertThat(member.getHoliday().get(0)).isEqualTo(holiday);
    }

    @Test
    void getHoliday(){
        Member member1 = setMember("23-22222222");
        Member member2 = setMember("23-12121212");
        Leader leader = setLeader();

        HolidayApplyForm form = new HolidayApplyForm();
        setHolidayForm(form);

        memberRepository.save(member1);
        memberRepository.save(member2);

        holidayService.applyHoliday(member1.getMember_id(), LeaderConst.leaderId,form);
        holidayService.applyHoliday(member2.getMember_id(), LeaderConst.leaderId,form);

        List<Holiday> holidays = leaderRepository.findHolidays();
        Assertions.assertThat(holidays.size()).isEqualTo(2);

    }

    @Test
    void approve(){
        Member member = setMember("23-00000000");
        Leader leader = setLeader();

        HolidayApplyForm form = new HolidayApplyForm();
        setHolidayForm(form);

        memberRepository.save(member);
        leaderRepository.save(leader);

        //when
        Holiday holiday = holidayService.applyHoliday(member.getMember_id(), leader.getLeader_id(),form);
        holidayService.approve(holiday.getId());
        Holiday findHoliday = holidayService.findHoliday(holiday.getId());

        //then
        //DB에 값이 잘 저장되었는가
        Assertions.assertThat(holiday.getStatus()).isEqualTo(findHoliday.getStatus());
        //Assertions.assertThat(holiday.getStatus()).isEqualTo(HolidayStatus.APPROVE);

        List<Holiday> holiday2 = member.getHoliday();
    }

    public void setHolidayForm(HolidayApplyForm form){
        form.setHolidayDays(4);
        form.setHolidayKind("PRIZE");
        form.setStartDate( LocalDate.of(2024, 7, 22));
        form.setEndDate(LocalDate.of(2024, 7, 25));
    }

    public Member setMember(String memberId){
        Member member = new Member();
        member.setName("sammool");
        member.setMember_id(memberId);
        member.setLeftover_days(10);
        return member;
    }
    public Leader setLeader(){
        Leader leader = new Leader();
        leader.setLeader_id("23-11111111");
        return leader;
    }
}
