package study.repository.MemberRepository;
import study.domain.Member;
import java.util.*;
public interface MemberRepositoryCustom {
    Optional<Member> findMemberDetails(Long memberId);
}
