package sammool.holiday.web.form;

import java.time.LocalDate;
import lombok.Data;

@Data
public class HolidayApplyForm {
    
    private String holidayKind;

    private int holidayDays;

    private LocalDate startDate;

    private LocalDate endDate;
   
}
