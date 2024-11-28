package study.validation.validator;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import study.validation.annotation.CheckPage;

@Component
public class CheckPageResolver implements HandlerMethodArgumentResolver {

    private final PageableHandlerMethodArgumentResolver delegate = new PageableHandlerMethodArgumentResolver();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CheckPage.class)
                && Pageable.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        // 기본 Pageable 객체 생성
        Pageable pageable = delegate.resolveArgument(parameter, mavContainer, webRequest, binderFactory);

        int page = pageable.getPageNumber();
        System.out.println("page 수는 "+page);
        if (page <= 0) {
            throw new IllegalArgumentException("page는 1 이상의 값이어야 합니다.");
        }

        // page를 0부터 시작하도록 변환
        return PageRequest.of(page - 1, pageable.getPageSize(), pageable.getSort());
    }
}