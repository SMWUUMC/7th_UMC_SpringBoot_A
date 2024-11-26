package study.service.MemberService;

import study.domain.Member;
import java.util.*;

public interface MemberQueryService {
    Optional<Member> findMemberDetails(Long id);

    boolean isValid(Long id);
}
