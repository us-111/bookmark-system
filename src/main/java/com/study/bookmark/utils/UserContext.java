package com.study.bookmark.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class UserContext {

    public static Long getUserId(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes!=null){
            HttpServletRequest request = attributes.getRequest();
            Object userId = request.getAttribute("userId");
            if (userId!=null) return Long.valueOf(userId.toString());
        }
        return null;
    }
}
