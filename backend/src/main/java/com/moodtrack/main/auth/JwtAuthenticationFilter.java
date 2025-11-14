package com.moodtrack.main.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moodtrack.main.dto.ApiResponse;
import com.moodtrack.main.error.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter implements AuthenticationEntryPoint {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // 여기서 “토큰 없음/유효하지 않음” 응답 내려주기
        ApiResponse<?> body =
                ApiResponse.error(ErrorCode.UNAUTHORIZED, "토큰이 없거나 유효하지 않습니다. (로그인이 필요합니다.)");

        response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 401
        response.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(response.getWriter(), body);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractTokenFromRequest(request);
        if (token != null && jwtUtil.validateToken(token, extractEmail(token))) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(extractEmail(token), null, new ArrayList<>()));
        }
        filterChain.doFilter(request, response);
    }

    // HTTP 요청에서 JWT 토큰을 추출하는 메소드
    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private String extractEmail(String token) {
        return jwtUtil.extractEmail(token);
    }
}
