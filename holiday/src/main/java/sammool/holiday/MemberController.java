package sammool.holiday;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcMemberRepository;
import sammool.holiday.repository.MemberRepository;

@Controller
@RequestMapping("/home")
public class MemberController {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberController(JdbcMemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members")
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "home/members";
    }

    @GetMapping("/members/{member_id}")
    public String member(Model model, @PathVariable String member_id){
        
        return "";
    }

    @GetMapping("/add")
    public String add(){
        return "home/addForm";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute("member") Member member, Model model){
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "home/members";
    }
    
}
