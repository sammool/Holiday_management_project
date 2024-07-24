package sammool.holiday.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Leader {
    @Id
    @Column
    private String leader_id;

    private String degree;

    private String name;

    private String password;

    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
    private List<Holiday> holiday = new ArrayList<>();

}
