package com.study.bookmark.interceptor;

import com.study.bookmark.exception.BusinessException;
import com.study.bookmark.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) return true;
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) throw new BusinessException("pls login");
        token = token.substring(7);
        try {
            Claims claims = jwtUtils.parseToken(token);
            request.setAttribute("userId", claims.get("userId"));
            return true;
        } catch (Exception e) {
            throw new BusinessException("timeout pls relogin");
        }
    }
}
