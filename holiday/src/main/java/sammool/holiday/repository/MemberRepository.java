package sammool.holiday.repository;

import sammool.holiday.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    
    void createTable();
    Optional<Member> save(Member member);
    List<Member> findAll();
    Optional<Member> findById(String id);
    Optional<Member> update(Member member);
    List<Member> findApplyMember();
    void addApplyMember(Member member);
}
