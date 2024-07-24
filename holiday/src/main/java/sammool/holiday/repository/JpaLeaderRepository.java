package sammool.holiday.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sammool.holiday.domain.Leader;

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
}
