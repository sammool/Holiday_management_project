package sammool.holiday.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
    
    @NotBlank
    private final String member_id;

    @NotBlank
    private final String password;
}
