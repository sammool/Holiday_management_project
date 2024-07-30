package sammool.holiday.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Holiday;
import sammool.holiday.domain.Leader;
import sammool.holiday.repository.JpaLeaderRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LeaderService {

    private final JpaLeaderRepository leaderRepository;

    @Transactional
    public void save(Leader leader){
        leaderRepository.save(leader);
    }

    public List<Holiday> findHolidays(){
        return leaderRepository.findHolidays();
    }

}
