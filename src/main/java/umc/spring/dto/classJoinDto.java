package umc.spring.dto;

import lombok.Getter;

import java.util.List;

@Getter
public static classJoinDto {
    String name;
    Integer gender;
    Integer birthYear;
    Integer birthMonth;
    Integer birthDay;
    String address;
    String specAddress;
    List<Long> preferCategory;
}
