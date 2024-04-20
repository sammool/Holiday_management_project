package sammool.holiday.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter @Setter
public class Member {
   
   
   private String member_id; //군번,주요키
   private String degree;//계급
   private String name;//이름
   private int leftover_days; //잔여 휴가 일 수
   private int points;//상점

   public Member(){

   }

   public Member(String member_id, String degree, String name, int leftover_days, int points) {
      this.member_id = member_id;
      this.degree = degree;
      this.name = name;
      this.leftover_days = leftover_days;
      this.points = points;
   }

}
