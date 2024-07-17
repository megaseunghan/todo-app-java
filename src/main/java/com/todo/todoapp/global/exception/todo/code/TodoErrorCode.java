package com.todo.todoapp.global.exception.todo.code;

import com.todo.todoapp.global.exception.common.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TodoErrorCode implements ErrorCode {

    // 수정 / 삭제 시, 비밀번호가 일치하지 않을 때
    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    // 선택한 일정 정보가 이미 삭제되어 조회할 수 없을 때
    FIND_FAILED(HttpStatus.NO_CONTENT, "선택하신 일정은 조회할 수 없습니다."),
    // 삭제하려는 일정 정보가 이미 삭제 상태일 때
    ALREADY_DELETED(HttpStatus.NOT_FOUND, "해당 일정은 존재하지 않거나 이미 삭제되었습니다.");

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
