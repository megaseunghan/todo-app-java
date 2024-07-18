package com.todo.todoapp.global.exception.comment;

import com.todo.todoapp.global.exception.common.CustomException;
import com.todo.todoapp.global.exception.common.code.ErrorCode;

public class NoSuchCommentException extends CustomException {
    public NoSuchCommentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
