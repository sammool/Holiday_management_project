package sammool.holiday.domain;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Holiday {
    @Id @GeneratedValue
    @Column(name = "holiday_id")
    private Long id;

    private int HolidayDays;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private HolidayKind kind;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private Leader leader;

    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getHoliday().add(this);
    }

    public void setLeader(Leader leader){
        this.leader = leader;
        leader.getHoliday().add(this);
    }

    public static Holiday createHoliday(Member member, Leader leader){
        Holiday holiday = new Holiday();
        holiday.setMember(member);
        holiday.setLeader(leader);
        return holiday;
    }

}
