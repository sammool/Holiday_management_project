package sammool.holiday.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Leader;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JpaLeaderRepository;
import sammool.holiday.repository.JdbcMemberRepository;
import sammool.holiday.repository.JpaMemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JpaMemberRepository memberRepository;
    private final JpaLeaderRepository leaderRepository;

    /*
     * null 리턴하면 실패
     */
    public Member login(String memberId, String password){
        return memberRepository.findOne(memberId)
            .filter(m->m.getPassword().equals(password))
            .orElse(null);
    }

    public Leader leaderLogin(String leaderId, String password){
        return leaderRepository.findOne(leaderId)
                .filter(m->m.getPassword().equals(password))
                .orElse(null);
    }
}
