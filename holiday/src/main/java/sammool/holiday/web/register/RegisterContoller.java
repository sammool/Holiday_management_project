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
import sammool.holiday.repository.MemberRepository;
import java.util.Optional;

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
        
        Optional<Member> findMember = memberRepository.findById(member.getMember_id());
        if(findMember.isPresent()){
           //application.yml은 errors,messages 등록 X
            bindingResult.rejectValue("member_id",null,null,"중복된 군번입니다. 다시 입력해주세요.");
        }

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "register/registerForm";
        }

        memberRepository.save(member);
        log.info("회원가입 완료 member={}",member);
        return "home/home";
    }
}
