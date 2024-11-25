package umc.spring.study.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import umc.spring.study.validation.annotation.ExistCategories;

import java.util.*;

public class MemberRequestDTO {
    @Getter
    public static class JoinDto{
        @NotBlank(message = "이름은 필수입니다.")
        String name;
        @NotNull(message = "성별은 필수입니다.")
        Integer gender;
        @NotNull(message = "생년월일은 필수입니다.")
        Integer birthYear;
        @NotNull(message = "생년월일은 필수입니다.")
        Integer birthMonth;
        @NotNull(message = "성년월일은 필수입니다.")
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
    }
}
