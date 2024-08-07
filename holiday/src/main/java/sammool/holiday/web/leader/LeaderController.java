package sammool.holiday.web.leader;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcMemberRepository;
import sammool.holiday.repository.JpaMemberRepository;
import sammool.holiday.service.MemberService;
import sammool.holiday.web.form.EditForm;

@Controller
@Slf4j
@RequestMapping("/leader")
@RequiredArgsConstructor
public class LeaderController {
    private final JpaMemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/members")
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "leader/members";
    }

    @GetMapping("/members/{member_id}")
    public String member(Model model, @PathVariable String member_id){
        Optional<Member> optionalMember = memberRepository.findOne(member_id);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            model.addAttribute("member", member);
            return "leader/member";
        } else{
            return "leader/member";
        }
    }

    @GetMapping("/members/{member_id}/edit")
    public String editForm(@ModelAttribute("form") EditForm form){
        return "leader/editForm";
    }

    @PostMapping("/members/{member_id}/edit")
    public String edit(@PathVariable String member_id, @Validated @ModelAttribute("form") EditForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "leader/editForm";
        }

        memberService.updateMember(member_id,form);
        return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/leader/members/{member_id}";
    }


}
