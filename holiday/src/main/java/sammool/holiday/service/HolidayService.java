package sammool.holiday.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Holiday;
import sammool.holiday.domain.HolidayKind;
import sammool.holiday.domain.Leader;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JpaHolidayRepository;
import sammool.holiday.repository.JpaLeaderRepository;
import sammool.holiday.repository.JpaMemberRepository;
import sammool.holiday.web.form.HolidayApplyForm;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HolidayService {
    
    private final JpaMemberRepository memberRepository;
    private final JpaLeaderRepository leaderRepository;
    private final JpaHolidayRepository holidayRepository;

    @Transactional
    public void save(Holiday holiday){
        holidayRepository.save(holiday);
    }

    public Holiday findHoliday(Long holidayId){
        return holidayRepository.findHoliday(holidayId);
    }

    @Transactional
    public Holiday applyHoliday(String memberId, String leaderId, HolidayApplyForm form){

        Optional<Member> member = memberRepository.findOne(memberId);
        Optional<Leader> leader = leaderRepository.findOne(leaderId);
        
        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with ID: " + memberId);
        }

        Member findMember = member.get();
        Leader findLeader = leader.get();
    
        Holiday holiday = Holiday.createHoliday(findMember, findLeader);

        holiday.setHolidayDays(form.getHolidayDays());
        holiday.setKind(HolidayKind.fromString(form.getHolidayKind()));
        holiday.setStartDate(form.getStartDate());
        holiday.setEndDate(form.getEndDate());

        findMember.setLeftover_days(findMember.getLeftover_days()-form.getHolidayDays());

        holidayRepository.save(holiday);
        return holiday;
    }

    @Transactional
    public void approve(Long holidayId){
        Holiday holiday = holidayRepository.findHoliday(holidayId);
        log.info("approve 실행 전={}", holiday.getStatus());
        holiday.approve();
        log.info("approve 실행 후={}", holiday.getStatus());
    }
    
    @Transactional
    public void cancle(Long holidayId){
        Holiday holiday = holidayRepository.findHoliday(holidayId);
        log.info("cancel 실행 전={}", holiday.getStatus());
        holiday.cancle();
        log.info("cancel 실행 gn={}", holiday.getStatus());
    }

    

   
}
