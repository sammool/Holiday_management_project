package sammool.holiday.web.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
    
    @NotEmpty
    private final String member_id;

    @NotEmpty
    private final String password;
}
