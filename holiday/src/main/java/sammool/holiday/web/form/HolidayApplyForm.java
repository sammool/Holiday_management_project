package sammool.holiday.web.form;

import java.time.LocalDate;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import sammool.holiday.domain.HolidayKind;
import sammool.holiday.domain.HolidayStatus;

public class HolidayApplyForm {
    
    private String holidayKind;

    private int HolidayDays;

    private LocalDate startDate;

    private LocalDate endDate;
   
}
