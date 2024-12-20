package umc.spring.validation.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPage {
    String message() default "페이지 번호는 1 이상이어야 합니다.";
}

