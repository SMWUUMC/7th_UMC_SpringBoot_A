package study.converter;

import study.domain.Member;
import study.domain.enums.Gender;
import study.web.dto.MemberRequestDTO;
import study.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.*;
public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {
        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.FEMALE;
            case 2 -> Gender.MALE;
            default -> Gender.NONE;
        };

        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())   // 추가된 코드
                .password(request.getPassword())   // 추가된 코드
                .gender(gender)
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .role(request.getRole())   // 추가된 코드
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
