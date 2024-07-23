package sammool.holiday.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Leader {
    @Id @GeneratedValue
    @Column
    private String leader_id;

    private String degree;

    private String name;

    private String password;

    @OneToMany(mappedBy = "leader")
    private List<Holiday> holiday = new ArrayList<>();

}
