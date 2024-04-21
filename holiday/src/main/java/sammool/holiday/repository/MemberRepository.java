package sammool.holiday.repository;

import sammool.holiday.domain.Member;
import java.util.List;

public interface MemberRepository {
    
    void createTable();
    Member save(Member member);
    List<Member> findAll();
    Member findById(String id);
    void update(Member member);
}
