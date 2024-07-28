package sammool.holiday.service;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import sammool.holiday.domain.Holiday;
import sammool.holiday.domain.HolidayKind;
import sammool.holiday.domain.Leader;
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
        Member member = new Member();
        member.setName("sammool");
        member.setMember_id("23-76030904");
        member.setLeftover_days(10);
        Leader leader = new Leader();
        leader.setLeader_id("23-71111111");

        HolidayApplyForm form = new HolidayApplyForm();
        form.setHolidayDays(4);
        form.setHolidayKind("연가");
        form.setStartDate( LocalDate.of(2024, 7, 22));
        form.setEndDate(LocalDate.of(2024, 7, 25));

        memberRepository.save(member);
        leaderRepository.save(leader);

        //when
        Holiday holiday = holidayService.applyHoliday(member.getMember_id(), leader.getLeader_id(),form);
        
        //then
        Assertions.assertThat(holiday.getMember().getName()).isEqualTo("sammool");
        Assertions.assertThat(member.getLeftover_days()).isEqualTo(6);
    }
}
