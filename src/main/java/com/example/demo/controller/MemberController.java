package com.example.demo.controller;

import com.example.demo.base.ApiResponse;
import com.example.demo.dto.MemberRequestDTO;
import com.example.demo.dto.MemberResponseDTO;
import com.example.demo.service.Member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberCommandService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        MemberResponseDTO.JoinResultDTO response = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(response);
    }
}
