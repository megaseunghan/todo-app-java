package com.todo.todoapp.global.exception.handler.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        HttpStatus httpStatus,
        String message,
        @Nullable
        List<ValidError> validError
) {
    @Builder
    record ValidError(
            String field,
            String value,
            String message
    ) {

        static List<ValidError> of(BindingResult bindingResult) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            return errors.stream().map((error) -> ValidError.builder()
                    .field(error.getField())
                    .value(String.valueOf(error.getRejectedValue()))
                    .message(error.getDefaultMessage())
                    .build()
            ).toList();
        }
    }

    static ErrorResponse of(HttpStatus httpStatus, String message) {
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }

    static ErrorResponse of(HttpStatus httpStatus, String message, BindingResult bindingResult) {
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .message(message)
                .validError(ValidError.of(bindingResult))
                .build();
    }
}
