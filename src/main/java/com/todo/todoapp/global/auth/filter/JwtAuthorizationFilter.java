package com.todo.todoapp.global.auth.filter;

import com.fasterxml.jackson.core.JsonParseException;
import com.todo.todoapp.domain.user.vo.Role;
import com.todo.todoapp.infrastructure.jwt.JwtUtil;
import com.todo.todoapp.presentation.user.dto.response.AuthenticatedUserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

import static com.todo.todoapp.global.exception.auth.code.AuthErrorCode.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter implements Filter {

    private static final String[] whiteUrlList = new String[]{"/users/login", "/users/register", "*/h2-console*"};
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (httpServletRequest.getMethod().equals("GET") || checkWhiteList(httpServletRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        if (!jwtUtil.containsToken(httpServletRequest)) {
            httpServletResponse.sendError(AUTHORIZE_FAILED.getHttpStatus().value(), AUTHORIZE_FAILED.getMessage());
            return;
        }

        try {
            String accessToken = httpServletRequest.getHeader("Authorization").substring(7);
            AuthenticatedUserResponse verifyUser = jwtUtil.getAuthenticateUser(accessToken);
            chain.doFilter(request, response);
        } catch (JsonParseException e) {
            log.error("JSON 파싱 실패");
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "JSON 파싱 싫패");
        } catch (MalformedJwtException | UnsupportedJwtException | SignatureException e) {
            log.error("JWT 인증 실패");
            httpServletResponse.sendError(JWT_INVALID_FAILED.getHttpStatus().value(), JWT_INVALID_FAILED.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT 인증 기한 만료");
            httpServletResponse.sendError(TOKEN_HAS_EXPIRED.getHttpStatus().value(), TOKEN_HAS_EXPIRED.getMessage());
        }
    }


    private boolean checkWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(whiteUrlList, requestURI);
    }

}
