package com.example.demo.service.Member;

import com.example.demo.dto.MemberRequestDTO;
import com.example.demo.dto.MemberResponseDTO;

public interface MemberService {
    MemberResponseDTO.JoinResultDTO joinMember(MemberRequestDTO.JoinDto request);
}