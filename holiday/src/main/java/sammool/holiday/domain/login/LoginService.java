package sammool.holiday.domain.login;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Leader;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcLeaderRepository;
import sammool.holiday.repository.JdbcMemberRepository;
import sammool.holiday.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final JdbcLeaderRepository leaderRepository;

    /*
     * null 리턴하면 실패
     */
    public Member login(String memberId, String password){
        return memberRepository.findById(memberId)
            .filter(m->m.getPassword().equals(password))
            .orElse(null);
    }

    public Leader leaderLogin(String leaderId, String password){
        return leaderRepository.findById(leaderId)
                .filter(m->m.getPassword().equals(password))
                .orElse(null);
    }
}
