package com.example.demo.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final boolean success;
    private final T data;

    public static <T> ApiResponse<T> onSuccess(T data) {
        return new ApiResponse<>(true, data);
    }

    public static <T> ApiResponse<T> onFailure(String errorMessage) {
        return new ApiResponse<>(false, null);
    }
}
