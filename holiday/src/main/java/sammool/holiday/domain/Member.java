package sammool.holiday.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
public class Member {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private String id;

   private String name;
   private int leftover_days;
   private int points;

   public int getLeftover_days() {
       return leftover_days;
   }
   public String getName() {
       return name;
   }
   public int getPoints() {
       return points;
   }
   public String getId() {
       return id;
   }
   public void setLeftover_days(int leftover_days) {
       this.leftover_days = leftover_days;
   }
   public void setId(String id) {
       this.id = id;
   }
   public void setName(String name) {
       this.name = name;
   }
   public void setPoints(int points) {
       this.points = points;
   }

    
}
