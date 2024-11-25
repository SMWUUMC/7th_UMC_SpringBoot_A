package study.web.dto;

import lombok.Getter;
import study.validation.annotation.ExistCategories;

import java.util.*;

public class MemberRequestDTO {
    @Getter
    public static class JoinDto{
        String name;
        Integer gender;
        String address;
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
    }
}
