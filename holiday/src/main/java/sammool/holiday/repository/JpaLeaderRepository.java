package sammool.holiday.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sammool.holiday.domain.Leader;

@Repository
public class JpaLeaderRepository {
    
    @PersistenceContext
    private EntityManager em;

    public void save(Leader leader){
        em.persist(leader);
    }

    public Leader findLeader(String leaderId){
        return em.find(Leader.class, leaderId);
    }
}
