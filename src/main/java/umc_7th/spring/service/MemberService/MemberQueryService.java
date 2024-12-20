package umc_7th.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc_7th.spring.domain.Member;
import umc_7th.spring.domain.Review;
import umc_7th.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
    Page<Review> getMyReviewList(Long memberId, Integer page);
    Page<MemberMission> getMyMissionList(Long memberId, Integer page); // 3. 내가 진행 중인 미션 목록 조회하기 API
}
