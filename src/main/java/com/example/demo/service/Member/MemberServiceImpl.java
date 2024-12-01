package com.example.demo.service.Member;

import com.example.demo.base.ApiResponse;
import com.example.demo.converter.MemberConverter;
import com.example.demo.converter.MemberPreferConverter;
import com.example.demo.domain.FoodCategory;
import com.example.demo.domain.Member;
import com.example.demo.domain.mapping.MemberPrefer;
import com.example.demo.dto.MemberRequestDTO;
import com.example.demo.dto.MemberResponseDTO;
import com.example.demo.repository.FoodCategoryRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public MemberResponseDTO.JoinResultDTO joinMember(MemberRequestDTO.JoinDto request) {
        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new RuntimeException("Category not found"));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> memberPrefer.setMember(newMember));

        Member savedMember = memberRepository.save(newMember);

        // Convert savedMember to JoinResultDTO
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(savedMember.getId())
                // Add other fields as needed
                .build();
    }
}
