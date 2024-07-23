package sammool.holiday.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Holiday;
import sammool.holiday.domain.HolidayKind;
import sammool.holiday.domain.Leader;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JpaLeaderRepository;
import sammool.holiday.repository.JpaMemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HolidayService {
    
    private final JpaMemberRepository memberRepository;
    private final JpaLeaderRepository leaderRepository;

    @Transactional
    public Holiday applyHoliday(String memberId, String leaderId, int days, 
                    HolidayKind kind, LocalDate startDate, LocalDate endDate){

        Optional<Member> member = memberRepository.findOne(memberId);
        Leader leader = leaderRepository.findLeader(leaderId);
        
        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with ID: " + memberId);
        }

        Member findMember = member.get();
    
        Holiday holiday = Holiday.createHoliday(findMember, leader);

        holiday.setHolidayDays(days);
        holiday.setKind(kind);
        holiday.setStartDate(startDate);
        holiday.setEndDate(endDate);
        return holiday;
    }
}
