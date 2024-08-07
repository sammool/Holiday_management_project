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
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import sammool.holiday.domain.Holiday;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.JdbcMemberRepository;
import sammool.holiday.repository.JpaMemberRepository;
import sammool.holiday.repository.MemberRepository;
import sammool.holiday.service.HolidayService;
import sammool.holiday.service.MemberService;
import sammool.holiday.web.form.EditForm;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final JpaMemberRepository memberRepository;
    private final MemberService memberService;
    private final HolidayService holidayService;


    @GetMapping("/{member_id}")
    public String member(Model model, @PathVariable String member_id){
        Optional<Member> optionalMember = memberRepository.findOne(member_id);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            model.addAttribute("member", member);
            return "member/member";
        } else{
            return "member/member";
        }
    }

    @GetMapping("/{member_id}/holidayList")
    public String holidayList(@PathVariable("member_id") String member_id, Model model){
        List<Holiday> holidays = memberService.getHolidayList(member_id);
        model.addAttribute("holidays", holidays);
        return "member/holidayList";
    }

    @PostMapping("/{member_id}/{holiday_id}/cancel")
    public String cancelHoliday(@PathVariable("holiday_id") Long holiday_id){
        holidayService.cancle(holiday_id);
        return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/members/{member_id}/holidayList";
    }
}
