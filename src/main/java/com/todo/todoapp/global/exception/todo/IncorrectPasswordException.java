package com.todo.todoapp.global.exception.todo;

import com.todo.todoapp.global.exception.common.CustomException;
import com.todo.todoapp.global.exception.common.code.ErrorCode;

public class IncorrectPasswordException extends CustomException {
    public IncorrectPasswordException(ErrorCode errorCode) { super(errorCode); }
}
