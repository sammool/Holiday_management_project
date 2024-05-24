package sammool.holiday.web.register;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterContoller {

    private final MemberRepository memberRepository;

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("member") Member member){
        return "register/registerForm";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult){
        
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "register/registerForm";
        }

        memberRepository.save(member);
        log.info("회원가입 완료 member={}",member);
        return "home/home";
    }
}
