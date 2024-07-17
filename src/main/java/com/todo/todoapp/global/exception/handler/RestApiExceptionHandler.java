package com.todo.todoapp.global.exception.handler;

import com.todo.todoapp.global.exception.common.CustomException;
import com.todo.todoapp.global.exception.common.code.CommonErrorCode;
import com.todo.todoapp.global.exception.handler.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler {

    // @Valid 처리
    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleInvalidArgumentException(MethodArgumentNotValidException exception) {
        logInfo(exception);
        return ErrorResponse.of(CommonErrorCode.INVALID_ARGS, exception.getBindingResult());
    }

    // 사용자 정의 예외 처리
    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        logInfo(exception);
        return ErrorResponse.of(exception.getErrorCode());
    }

    // 그 외 런타임 예외
    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
        logInfo(exception);
        return ErrorResponse.of(CommonErrorCode.UNKNOWN_ERR);
    }

    private void logInfo(Exception exception) {
        log.info(exception.getStackTrace().toString());
    }
}
