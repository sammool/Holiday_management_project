package sammool.holiday.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Leader;
import sammool.holiday.repository.JpaLeaderRepository;

@Service
@RequiredArgsConstructor
public class LeaderService {

    private final JpaLeaderRepository leaderRepository;

    @Transactional
    public void save(Leader leader){
        leaderRepository.save(leader);
    }

}
