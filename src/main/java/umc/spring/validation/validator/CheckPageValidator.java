package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import umc.spring.validation.annotation.CheckPage;

@Component
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value <= 0) {
            return false; // 페이지 번호가 0 이하라면 유효하지 않음
        }
        return true;
    }

    public static int toZeroBasedPage(Integer value) {
        return value - 1; // 페이지를 1에서 0 기반으로 변환
    }
}

