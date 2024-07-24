package com.todo.todoapp.global.exception.user;

import com.todo.todoapp.global.exception.common.CustomException;
import com.todo.todoapp.global.exception.common.code.ErrorCode;

public class NoSuchUserException extends CustomException {
    public NoSuchUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
