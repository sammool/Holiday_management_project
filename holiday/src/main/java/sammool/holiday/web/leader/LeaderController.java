package sammool.holiday.web.leader;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcMemberRepository;

@Controller
@RequiredArgsConstructor
public class LeaderController {
    private final JdbcMemberRepository memberRepository;

    @GetMapping("/members/applyInfo")
    public String applyMembers(Model model){
        List<Member> applyMembers = memberRepository.findApplyMember();
        model.addAttribute("applyMembers", applyMembers);
        return "leader/applyMembers";
    }

}
