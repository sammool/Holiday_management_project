package sammool.holiday.web.validation;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sammool.holiday.web.form.HolidayApplyForm;

@Component
public class HolidayValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return HolidayApplyForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HolidayApplyForm form = (HolidayApplyForm)target;

        LocalDate startDate = form.getStartDate();
        LocalDate endDate = form.getEndDate();
        int holidayDays = form.getHolidayDays();

        //시작 날짜가 종료 날짜보다 크면 안됨
        if(!startDate.isBefore(endDate)){
            errors.reject("startDateBigger");
        }

        //선택한 휴가 일 수가 휴가 날짜랑 일치해야함
        if(!(endDate.minusDays(holidayDays-1)).isEqual(startDate)){
            errors.reject("daysNotEqualDate");
        }

    }
    
}
