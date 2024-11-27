package study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.domain.Member;
import study.web.dto.MemberRequestDTO;


public interface MemberCommandService {
    public Member joinMember(MemberRequestDTO.JoinDto request);
}
