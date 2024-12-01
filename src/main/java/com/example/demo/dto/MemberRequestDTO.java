package com.example.demo.dto;

import lombok.Getter;

import java.util.*;

public class MemberRequestDTO {
    @Getter
    public static class JoinDto{
        String name;
        Integer gender;
        String address;
        String specAddress;
        List<Long> preferCategory;
    }
}
