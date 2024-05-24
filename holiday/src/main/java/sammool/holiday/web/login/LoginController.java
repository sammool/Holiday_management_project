package sammool.holiday.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Member;
import sammool.holiday.domain.login.LoginService;
import sammool.holiday.repository.MemberRepository;
import sammool.holiday.web.SessionConst;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult,
                        HttpServletRequest request){
        //글로벌오류
        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getMember_id(), form.getPassword());
        log.info("loginMember={}", loginMember);

        if(loginMember == null){
            bindingResult.reject("loginFail", "군번 또는 비밀번호가 일치하지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:http://zany-guide-xgj9p74gqg7f6g9r-8080.app.github.dev/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

         return "redirect:http://zany-guide-xgj9p74gqg7f6g9r-8080.app.github.dev/";
    }
}
