package sammool.holiday.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Leader;
import sammool.holiday.domain.Member;
import sammool.holiday.service.LoginService;
import sammool.holiday.web.SessionConst;
import sammool.holiday.web.form.LoginForm;

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
                        @RequestParam(defaultValue = "/") String requestURI,
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
        log.info("멤버 세션 정보={}", session.getId());

        return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev" + requestURI;
    }

    //리더 로그인
    @GetMapping("/leader-login")
    public String leaderLoginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }

    @PostMapping("/leader-login")
    public String leaderLogin(@Validated @ModelAttribute("loginForm") LoginForm form, 
                                BindingResult bindingResult,  
                                @RequestParam(defaultValue = "/") String redirectURI, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "login/loginForm";
        }

        Leader loginLeader = loginService.leaderLogin(form.getMember_id(), form.getPassword());
        if(loginLeader == null){
            bindingResult.reject("loginFail", "군번 또는 비밀번호가 일치하지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_LEADER, loginLeader);
        
        log.info("리더 세션 정보={}", session.getId());    
        return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev" + redirectURI;
    }
    

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            log.info("로그아웃 정보={}",session);
            session.invalidate();
            log.info("로그아웃 되었습니다.");
        }

         return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/";
    }
}
