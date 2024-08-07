package sammool.holiday.web.holiday;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Holiday;
import sammool.holiday.domain.Member;
import sammool.holiday.service.HolidayService;
import sammool.holiday.service.LeaderService;
import sammool.holiday.service.MemberService;
import sammool.holiday.web.exception.NegativeDayException;
import sammool.holiday.web.form.HolidayApplyForm;
import sammool.holiday.web.validation.HolidayValidator;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HolidayContoller {
    
    private final MemberService memberService;
    private final HolidayService holidayService;
    private final LeaderService leaderService;
    private final HolidayValidator holidayValidator;

    
    //휴가 신청 폼
    @GetMapping("/members/{memberId}/apply")
    public String applyForm(@PathVariable("memberId") String memberId, 
                            @ModelAttribute("form") HolidayApplyForm form, Model model){
            
            Member member = memberService.findMember(memberId);
            model.addAttribute("member", member);

            return "member/applyForm";
    }

    @PostMapping("/members/{memberId}/apply")
    public String apply(@PathVariable("memberId") String memberId, 
                       @Validated @ModelAttribute("form") HolidayApplyForm form, BindingResult bindingResult, Model model){
            
            if(holidayValidator.supports(form.getClass())){
                holidayValidator.validate(form, bindingResult);
            }  

            if(bindingResult.hasErrors()){
                log.info("휴가 신청 오류={}",bindingResult);
                Member member = memberService.findMember(memberId);
                model.addAttribute("member", member);
                return "member/applyForm";
            }

            holidayService.applyHoliday(memberId, "23-11111111", form);
            log.info("휴가 신청이 성공적으로 완료되었습니다");
            return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/members/"+memberId;
    }

    //리더의 휴가 확인
    @GetMapping("/leader/holidayList")
    public String holidayList(Model model){
        List<Holiday> holidays = leaderService.findHolidays();
        model.addAttribute("holidays", holidays);
        return "leader/holidayList";
    }

    @PostMapping("/leader/holidayList/{holidayId}/approve")
    public String approveHoliday(@PathVariable("holidayId") Long holidayId){
        holidayService.approve(holidayId);
        return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/leader/holidayList";
    }

    @PostMapping("/leader/holidayList/{holidayId}/cancel")
    public String cancelHoliday(@PathVariable("holidayId") Long holidayId){
        holidayService.cancle(holidayId);
        return "redirect:https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/leader/holidayList";
    }

}
