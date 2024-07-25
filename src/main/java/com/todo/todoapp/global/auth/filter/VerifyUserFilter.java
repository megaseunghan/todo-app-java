package com.todo.todoapp.global.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.todoapp.application.user.UserService;
import com.todo.todoapp.presentation.user.dto.request.LoginRequest;
import com.todo.todoapp.presentation.user.dto.response.AuthenticatedUserResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class VerifyUserFilter implements Filter {
    public static final String AUTHENTICATE_USER = "authenticateUser";
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (httpServletRequest.getMethod().equals("POST")) {
            try {
                LoginRequest loginRequest = objectMapper.readValue(httpServletRequest.getReader(), LoginRequest.class);
                AuthenticatedUserResponse authenticatedUserResponse = userService.verifyUser(loginRequest);
                request.setAttribute(AUTHENTICATE_USER, authenticatedUserResponse);
                chain.doFilter(request, response);
            } catch (Exception e) {
                log.error("유저 인증에 실패했습니다");
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
            }
        }
    }
}
