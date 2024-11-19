package umc_7th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc_7th.spring.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
