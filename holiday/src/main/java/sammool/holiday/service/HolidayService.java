package sammool.holiday.service;

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
import sammool.holiday.web.exception.NegativeDayException;
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

        Member findMember = findMember(memberId);
        Leader findLeader = findLeader(leaderId);
    
        Holiday holiday = createAndSetHoliday(findMember, findLeader, form);
        
        int afterMinus = findMember.getLeftover_days()-form.getHolidayDays();
        if(afterMinus<0){ throw new NegativeDayException("잔여 휴가보다 큰 숫자를 선택하였습니다.");}
        
        findMember.setLeftover_days(findMember.getLeftover_days()-form.getHolidayDays());

        holidayRepository.save(holiday);
        return holiday;
    }

    public Member findMember(String memberId){
       return  memberRepository.findOne(memberId).
            orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with ID: " + memberId));
    }

    public Leader findLeader(String leaderId){
        return  leaderRepository.findOne(leaderId).
             orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leader not found with ID: " + leaderId));
    }

    public Holiday createAndSetHoliday(Member member, Leader leader, HolidayApplyForm form){
        Holiday holiday = Holiday.createHoliday(member, leader);

        holiday.setHolidayDays(form.getHolidayDays());
        holiday.setKind(HolidayKind.fromString(form.getHolidayKind()));
        holiday.setStartDate(form.getStartDate());
        holiday.setEndDate(form.getEndDate());

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
        log.info("cancel 실행 후={}", holiday.getStatus());
    }

    

   
}
