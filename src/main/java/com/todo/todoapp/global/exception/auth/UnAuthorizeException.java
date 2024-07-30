package com.todo.todoapp.global.exception.auth;

import com.todo.todoapp.global.exception.common.CustomException;
import com.todo.todoapp.global.exception.common.code.ErrorCode;

public class UnAuthorizeException  extends CustomException {
    public UnAuthorizeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
