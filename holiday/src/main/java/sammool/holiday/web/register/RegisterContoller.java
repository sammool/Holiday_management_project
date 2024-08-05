package sammool.holiday.web.register;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JpaMemberRepository;
import sammool.holiday.repository.MemberRepository;
import sammool.holiday.service.MemberService;
import sammool.holiday.web.form.RegisterForm;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterContoller {

    private final JpaMemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("form") RegisterForm form){
        return "register/registerForm";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("form") RegisterForm form, BindingResult bindingResult){
        
        Optional<Member> findMember = memberRepository.findOne(form.getMember_id());
        if(findMember.isPresent()){
           //application.yml은 errors,messages 등록 X
            bindingResult.rejectValue("member_id",null,null,"중복된 군번입니다. 다시 입력해주세요.");
        }

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "register/registerForm";
        }

        memberService.register(form.getMember_id(), form.getDegree(), form.getName(), form.getPassword());
        

        log.info("회원가입 완료 member={}",form);
        return "home/home";
    }
}
