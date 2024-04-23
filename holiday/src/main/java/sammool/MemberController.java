package sammool;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository;

@Controller
@RequestMapping("/home")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members")
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "home/members";
    }

    @GetMapping("/add")
    public String add(){
        return "home/addForm";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute("member") Member member, Model model){
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "home/members";
    }
    
}