package sammool.holiday.web.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcMemberRepository;
import sammool.holiday.repository.MemberRepository;
import sammool.holiday.web.form.EditForm;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberController(JdbcMemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "member/members";
    }

    @GetMapping("/{member_id}")
    public String member(Model model, @PathVariable String member_id){
        Optional<Member> optionalMember = memberRepository.findById(member_id);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            model.addAttribute("member", member);
            return "member/member";
        } else{
            return "member/member";
        }
        
    }

    @GetMapping("/{member_id}/edit")
    public String editForm(@ModelAttribute("form") EditForm form){
        return "member/editForm";
    }

    @PostMapping("/{member_id}/edit")
    public String edit(@PathVariable String member_id, @Validated @ModelAttribute("form") EditForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "member/editForm";
        }
        Member findMember = memberRepository.findById(member_id).get();

        findMember.setDegree(form.getDegree());
        findMember.setLeftover_days(form.getLeftover_days());
        findMember.setPoints(form.getPoints());

        memberRepository.update(findMember);
        return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/members/{member_id}";
    }

    @GetMapping("/{member_id}/apply")
    public String applyForm(@PathVariable String member_id){
        return "member/applyForm";
    }
    
}
