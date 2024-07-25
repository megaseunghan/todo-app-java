package com.todo.todoapp.global.auth;

import com.todo.todoapp.application.user.UserService;
import com.todo.todoapp.infrastructure.jwt.JwtUtil;
import com.todo.todoapp.infrastructure.jwt.vo.response.Jwt;
import com.todo.todoapp.presentation.user.dto.response.AuthenticatedUserResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.todo.todoapp.global.auth.VerifyUserFilter.AUTHENTICATE_USER;

@Component
@RequiredArgsConstructor
public class JwtLoginFilter implements Filter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Object attribute = request.getAttribute(AUTHENTICATE_USER);

        if (attribute instanceof AuthenticatedUserResponse) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userName", ((AuthenticatedUserResponse) attribute).userName());
            claims.put("role", ((AuthenticatedUserResponse) attribute).role());
            Jwt jwt = jwtUtil.createJwt(claims);
            userService.updateAccessToken(((AuthenticatedUserResponse) attribute).userName(), jwt.accessToken());
            httpServletResponse.setHeader("Authorization", "Bearer " + jwt.accessToken());
            return;
        }

        httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
