package com.todo.todoapp.infrastructure.jwt;

import com.todo.todoapp.domain.user.vo.Role;
import com.todo.todoapp.infrastructure.jwt.config.JwtProperties;
import com.todo.todoapp.infrastructure.jwt.vo.response.Jwt;
import com.todo.todoapp.presentation.user.dto.response.AuthenticateUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final JwtProperties jwtProperties;
    private final Key key;

    public static final String AUTHORIZATION = "Authorization";

    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(jwtProperties.getKey().getBytes());
    }

    public Jwt createJwt(Map<String, Object> claims) {
        Date accessTokenExpiredDate = parseDate(jwtProperties.getAccessTokenExpiredDeadLine());
        String accessToken = createToken(claims, accessTokenExpiredDate);

        return Jwt.builder()
                .accessToken(accessToken)
                .build();
    }

    public String createToken(Map<String, Object> claims, Date accessTokenExpiredDeadLine) {
        return Jwts.builder()
                .claims(claims)
                .expiration(accessTokenExpiredDeadLine)
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String accessToken) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();
    }

    private Date parseDate(String accessTokenExpiredDeadLine) {
        long expireTimeMils = 1000 * 60 * Long.parseLong(accessTokenExpiredDeadLine); // 60분
        return new Date(System.currentTimeMillis() + expireTimeMils);
    }

    public AuthenticateUser getAuthenticateUser(String accessToken) {
        Claims claims = getClaims(accessToken);
        String userName = (String) claims.get("userName");
        Role role = Role.valueOf((String) claims.get("role"));
        String password = (String) claims.get("password");

        return AuthenticateUser.builder()
                .userName(userName)
                .role(role)
                .password(password)
                .build();
    }

    public boolean containsToken(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader(AUTHORIZATION);
        return authorization != null && authorization.startsWith("Bearer ");
    }
}
