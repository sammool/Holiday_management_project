package sammool.holiday.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository;


public interface MemberService {
    Member useHoliday(String id, int days);
    Member minusPoint(String id, int point);
    Member pointToHoliday(String id);
    Member plusPoint(String id, int point);
    
}


//필요한 메서드
//휴가 사용, 벌점 처리, 상점을 휴가로 전환, 휴가 추가, 상점 추가