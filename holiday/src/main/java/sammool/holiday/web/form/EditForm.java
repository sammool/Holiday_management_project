package sammool.holiday.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditForm {

    private String member_id;

    @NotBlank
    private String degree;
    
    private int leftover_days;
    private int points;
}
