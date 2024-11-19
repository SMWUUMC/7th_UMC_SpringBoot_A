package umc_7th.spring.service.MemberService;

import umc_7th.spring.domain.Member;
import umc_7th.spring.web.dto.MemberDTO.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
