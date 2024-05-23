package sammool.holiday.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import sammool.holiday.repository.MemberRepository;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return " ";
    }
}
