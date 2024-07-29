package com.todo.todoapp.global.auth.resolver;

import com.todo.todoapp.global.auth.annotation.Authenticate;
import com.todo.todoapp.infrastructure.jwt.JwtUtil;
import com.todo.todoapp.presentation.user.dto.response.AuthenticatedUserResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.todo.todoapp.infrastructure.jwt.JwtUtil.AUTHORIZATION;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtUtil jwtUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Authenticate.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        try {
            String accessToken = httpServletRequest.getHeader(AUTHORIZATION);
            AuthenticatedUserResponse authenticateUser = jwtUtil.getAuthenticateUser(accessToken.substring(7));
            httpServletRequest.setAttribute("AuthenticateUser", authenticateUser);

            return authenticateUser;
        } catch (RuntimeException e) {
            log.error("AuthenticateArgumentResolver 에러 발생");
        }
        return null;
    }
}
