package com.todo.todoapp.global.exception.user.code;

import com.todo.todoapp.global.exception.common.code.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 사용중인 닉네임입니다."),
    FIND_FAILED(HttpStatus.BAD_REQUEST, "해당 유저는 존재하지 않습니다."),
    
    ;

    private HttpStatus httpStatus;
    private String message;

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
