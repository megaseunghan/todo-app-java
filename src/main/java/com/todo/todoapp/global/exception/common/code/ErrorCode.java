package com.todo.todoapp.global.exception.common.code;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getHttpStatus();

    void setHttpStatus(HttpStatus httpStatus);

    String getMessage();

    void setMessage(String message);
}
