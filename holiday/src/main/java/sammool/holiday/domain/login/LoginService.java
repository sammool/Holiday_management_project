package sammool.holiday.domain.login;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcMemberRepository;
import sammool.holiday.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /*
     * null 리턴하면 실패
     */
    public Member login(String memberId, String password){
        Member member = memberRepository.findById(memberId);
        if(member.getPassword().equals(password)){
            return member;
        }
        else
            return null;
    }
}
