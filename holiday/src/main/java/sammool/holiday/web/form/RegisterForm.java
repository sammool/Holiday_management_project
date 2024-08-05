package sammool.holiday.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterForm {

   @NotBlank
   @Pattern(regexp = "[0-9]{2}-[0-9]{8}", message = "00-00000000 형식으로 입력해주세요")
   private String member_id; //군번,주요키
   
   @NotBlank
   @Pattern(regexp = "^[가-힣]*$", message = "한글로 작성해주세요")
   private String degree;
   
   @NotBlank
   @Pattern(regexp = "^[가-힣]*$", message = "한글로 작성해주세요")
   private String name;

   @NotBlank
   @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9!@#$%^&*()]{8,}$", message = "영대소문자, 숫자를 포함하여 8자 이상 입력해주세요" )
   private String password;
}


