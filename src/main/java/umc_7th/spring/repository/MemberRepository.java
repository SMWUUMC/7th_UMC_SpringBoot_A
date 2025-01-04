package umc_7th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc_7th.spring.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
