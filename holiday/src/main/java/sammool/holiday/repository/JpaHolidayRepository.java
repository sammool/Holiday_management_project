package sammool.holiday.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sammool.holiday.domain.Holiday;

@Repository
public class JpaHolidayRepository {
    @PersistenceContext
    EntityManager em;

    public void save(Holiday holiday){
        em.persist(holiday);
    }

    public Holiday findHoliday(Long id){
        Holiday holiday = em.find(Holiday.class, id);
        return holiday;
    }
}
