package sammool.holiday.domain;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Member {
   
   @Id
   @Column(name = "member_id")
   private String member_id; //군번,주요키

   private String degree;

   private String name;

   private String password;

   private int leftover_days; //잔여 휴가 일 수
   
   private int points;//상점
      
   @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
   private List<Holiday> holiday = new ArrayList<>();

}
