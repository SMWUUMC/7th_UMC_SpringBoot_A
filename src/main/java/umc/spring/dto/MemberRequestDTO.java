package umc.spring.dto;

import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {
    @Getter
    private static class JoinDTO{
        String name;
        Integer gender;
        Integer birthYear;
        Integer birthMonth;
        Integer birthday;
        String address;
        String specAddress;
        List<Long> preferCategory;
    }
}
