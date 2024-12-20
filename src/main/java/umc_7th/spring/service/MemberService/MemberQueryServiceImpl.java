package umc_7th.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc_7th.spring.domain.Member;
import umc_7th.spring.domain.Mission;
import umc_7th.spring.domain.Review;
import umc_7th.spring.domain.mapping.MemberMission;
import umc_7th.spring.repository.MemberMissionRepository;
import umc_7th.spring.repository.MemberRepository;
import umc_7th.spring.repository.ReviewRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }
    @Override
    public Page<Review> getMyReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        Page<Review> MemberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));

        return MemberPage;
    }
    @Override
    public Page<MemberMission> getMyMissionList(Long memberId, Integer page) { // 3. 내가 진행 중인 미션 목록 조회하기 API
        Member member = memberRepository.findById(memberId).get();
        Page<MemberMission> MissionPage = memberMissionRepository.findByMemberId(memberId, PageRequest.of(page, 10));

        return MissionPage;
    }
}
