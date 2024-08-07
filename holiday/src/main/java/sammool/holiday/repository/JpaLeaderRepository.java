package sammool.holiday.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sammool.holiday.domain.Holiday;
import sammool.holiday.domain.Leader;
import sammool.holiday.domain.LeaderConst;

@Repository
public class JpaLeaderRepository {
    
    @PersistenceContext
     EntityManager em;

    public void save(Leader leader){
        em.persist(leader);
    }

    public Optional<Leader> findOne(String leaderId){
       Leader leader = em.find(Leader.class, leaderId);
       return Optional.of(leader);
    }

    public List<Holiday> findHolidays(){
        return em.createQuery("SELECT h FROM Holiday h WHERE h.leader.leader_id = :leaderId", Holiday.class)
          .setParameter("leaderId", LeaderConst.leaderId)
          .getResultList();
    }
}
