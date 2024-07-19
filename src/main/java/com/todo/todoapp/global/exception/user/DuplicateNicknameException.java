package com.todo.todoapp.global.exception.user;

import com.todo.todoapp.global.exception.common.CustomException;
import com.todo.todoapp.global.exception.common.code.ErrorCode;

public class DuplicateNicknameException extends CustomException {
    public DuplicateNicknameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
