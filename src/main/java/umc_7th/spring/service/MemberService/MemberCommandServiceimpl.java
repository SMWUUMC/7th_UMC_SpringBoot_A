package umc_7th.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc_7th.spring.apiPayload.code.status.ErrorStatus;
import umc_7th.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc_7th.spring.converter.MemberConverter;
import umc_7th.spring.converter.MemberPreferConverter;
import umc_7th.spring.domain.FoodCategory;
import umc_7th.spring.domain.Member;
import umc_7th.spring.domain.mapping.MemberPrefer;
import umc_7th.spring.repository.FoodCategoryRepository;
import umc_7th.spring.repository.MemberRepository;
import umc_7th.spring.web.dto.MemberDTO.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceimpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);

        newMember.encodePassword(passwordEncoder.encode(request.getPassword())); // 비밀번호 암호화하여 저장

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}
