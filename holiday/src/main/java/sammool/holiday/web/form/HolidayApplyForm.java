package sammool.holiday.web.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class HolidayApplyForm {
    

    private String holidayKind;

    @NotNull
    @Max(15)
    private Integer holidayDays;

    @Future
    private LocalDate startDate;

    @Future
    private LocalDate endDate;
}
