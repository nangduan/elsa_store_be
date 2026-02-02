package com.example.elsa_store.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    boolean success;
    String message;
    int code;
    T data;

    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(null)
                .code(1000)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> fail(String message, int code) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .code(code)
                .data(null)
                .build();
    }
}
