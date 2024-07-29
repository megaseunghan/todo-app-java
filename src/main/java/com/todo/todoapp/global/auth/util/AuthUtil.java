package com.todo.todoapp.global.auth.util;


import com.todo.todoapp.domain.user.model.User;
import com.todo.todoapp.global.exception.auth.UnAuthorizeException;
import com.todo.todoapp.global.exception.auth.code.AuthErrorCode;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    public static void isAuthorize(User owner, User entered) {
        if (!owner.getUserName().equals(entered.getUserName())) {
            throw new UnAuthorizeException(AuthErrorCode.AUTHORIZE_FAILED);
        }
    }
}
