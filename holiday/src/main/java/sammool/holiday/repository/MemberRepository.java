package sammool.holiday.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sammool.holiday.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
