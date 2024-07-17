package com.todo.todoapp.global.exception.todo;

import com.todo.todoapp.global.exception.common.CustomException;
import com.todo.todoapp.global.exception.common.code.ErrorCode;

public class NoSuchTodoException extends CustomException {
    public NoSuchTodoException(ErrorCode errorCode) {
        super(errorCode);
    }
}
