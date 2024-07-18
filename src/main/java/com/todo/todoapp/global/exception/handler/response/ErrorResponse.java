package com.todo.todoapp.global.exception.handler.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.todo.todoapp.global.exception.common.code.ErrorCode;
import jakarta.annotation.Nullable;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private record ValidError(
            String field,
            String value,
            String message
    ) {

        private static List<ValidError> of(BindingResult bindingResult) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            return errors.stream().map((error) -> ValidError.builder()
                    .field(error.getField())
                    .value(String.valueOf(error.getRejectedValue()))
                    .message(error.getDefaultMessage())
                    .build()
            ).toList();
        }
    }

    public static ResponseEntity<ErrorResponse> of(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .httpStatus(errorCode.getHttpStatus())
                        .message(errorCode.getMessage())
                        .build());
    }

    public static ResponseEntity<ErrorResponse> of(ErrorCode errorCode, BindingResult bindingResult) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .httpStatus(errorCode.getHttpStatus())
                        .message(errorCode.getMessage())
                        .validError(ValidError.of(bindingResult))
                        .build());
    }
}
