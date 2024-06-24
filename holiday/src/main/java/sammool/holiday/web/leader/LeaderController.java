package sammool.holiday.web.leader;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcMemberRepository;

@Controller
@RequiredArgsConstructor
public class LeaderController {
    private final JdbcMemberRepository memberRepository;

    @GetMapping("leader/members/applyInfo")
    public String applyMembers(Model model){
        List<Member> applyMembers = memberRepository.findApplyMember();
        model.addAttribute("applyMembers", applyMembers);
        return "leader/applyMembers";
    }

    @PostMapping("leader/members/applyInfo")
    public String approve(@ModelAttribute Member member){

        
        return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/members";
    }

}
