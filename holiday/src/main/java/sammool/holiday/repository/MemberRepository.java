package sammool.holiday.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sammool.holiday.domain.Member;

public interface MemberRepository {
    public Member save(Member member);
    public List<Member> findAll();
    public Member findById(String id);
}
