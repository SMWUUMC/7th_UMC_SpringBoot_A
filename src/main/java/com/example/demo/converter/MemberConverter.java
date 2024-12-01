package com.example.demo.converter;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberRequestDTO;

public class MemberConverter {
    public static Member toMember(MemberRequestDTO.JoinDto request) {
        // Conversion logic here
        Member member = new Member();
        // Populate member fields from request
        member.setName(request.getName()); // Example
        // Set other fields as necessary
        return member;
    }
}
