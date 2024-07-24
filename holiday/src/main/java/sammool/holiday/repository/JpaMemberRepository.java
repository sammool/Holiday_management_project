package sammool.holiday.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sammool.holiday.domain.Member;

@Repository
public class JpaMemberRepository {
    
    @PersistenceContext
     EntityManager em;

    public String save(Member member){
        em.persist(member);
        return member.getMember_id();
    }

    public Optional<Member> findOne(String id){
        Member member = em.find(Member.class, id);
        return Optional.of(member);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
