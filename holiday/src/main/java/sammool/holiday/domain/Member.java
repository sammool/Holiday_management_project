package sammool.holiday.domain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import lombok.extern.slf4j.Slf4j;

@Data
public class Member {
   
   @NotEmpty
   private String password;

   @NotEmpty
   private String member_id; //군번,주요키
   
   @NotBlank
   private String degree;//계급
   
   @NotBlank
   private String name;//이름
   
   private int leftover_days; //잔여 휴가 일 수
   private int points;//상점


   public Member(){

   }


}
