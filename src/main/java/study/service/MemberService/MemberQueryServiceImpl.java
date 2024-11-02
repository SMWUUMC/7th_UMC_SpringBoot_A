package study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.domain.Member;
import study.repository.MemberRepository.MemberRepositoryImpl;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepositoryImpl memberRepository;

    @Override
    public Optional<Member> findMemberDetails(Long id){
        Optional<Member> member = memberRepository.findMemberDetails(id);
        System.out.println("Member 회원정보 : " + member.orElseThrow().getName());
        return member;
    }
}
