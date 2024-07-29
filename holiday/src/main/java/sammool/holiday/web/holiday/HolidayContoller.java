package sammool.holiday.web.holiday;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Member;
import sammool.holiday.service.HolidayService;
import sammool.holiday.service.MemberService;
import sammool.holiday.web.form.HolidayApplyForm;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HolidayContoller {
    
    private final MemberService memberService;
    private final HolidayService holidayService;
    //휴가 신청 폼
    @GetMapping("members/{memberId}/apply")
    public String applyForm(@PathVariable("memberId") String memberId, 
                            @ModelAttribute("form") HolidayApplyForm form, Model model){
            
            Member member = memberService.findMember(memberId);
            model.addAttribute("member", member);

            return "member/applyForm";
    }

    @PostMapping("members/{memberId}/apply")
    public String apply(@PathVariable("memberId") String memberId, 
                        @ModelAttribute("form") HolidayApplyForm form){
            
            holidayService.applyHoliday(memberId, "23-11111111", form);
            log.info("휴가 신청이 성공적으로 완료되었습니다");
            return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/members/"+memberId;
    }

}