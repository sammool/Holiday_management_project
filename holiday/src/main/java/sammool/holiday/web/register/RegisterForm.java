package sammool.holiday.web.register;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterForm {

   @NotEmpty
   private String member_id; //군번,주요키
   @NotEmpty
   private String degree;
   @NotEmpty
   private String name;
   @NotEmpty
   private String password;
}
