package sammool.holiday.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import sammool.holiday.domain.Leader;
import sammool.holiday.domain.Member;

@Controller
public class HomeController {
    @GetMapping("/")
    //세션을 생성하지 않으므로 세션을 찾아올 때 사용한다
    public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember, 
                            @SessionAttribute(name = SessionConst.LOGIN_LEADER,required = false) Leader loginLeader, Model model){
        if(loginMember != null){
            model.addAttribute("member", loginMember);
            return "home/loginHome";
        }

        else if(loginLeader != null){
            model.addAttribute("leader", loginLeader);
            return "home/leaderLoginHome";
        }
        
        return "home/home";
   
    }
}
