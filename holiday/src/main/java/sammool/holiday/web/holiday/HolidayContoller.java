package sammool.holiday.web.holiday;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import sammool.holiday.web.form.HolidayApplyForm;

@Controller
public class HolidayContoller {
    
    //휴가 신청 폼
    @GetMapping("members/${memberId}/apply")
    public String applyForm(@RequestParam("memberId") String memberId, 
                            @ModelAttribute("form") HolidayApplyForm form){
            return "member/applyForm";

    }

}
