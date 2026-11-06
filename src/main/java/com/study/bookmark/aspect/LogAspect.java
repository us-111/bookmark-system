package com.study.bookmark.aspect;

import com.study.bookmark.annottation.Log;
import com.study.bookmark.mapper.SysLogMapper;
import com.study.bookmark.pojo.SysLog;
import com.study.bookmark.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    private final SysLogMapper sysLogMapper;

    @AfterReturning(pointcut = "@annotation(logAnnotation)")
    public void recordLog(JoinPoint joinPoint, Log logAnnotation){

        String operation = logAnnotation.value();

        String methodName = joinPoint.getSignature().getName();

        Long userId = UserContext.getUserId();

        SysLog log = new SysLog();
        log.setUserId(userId);
        log.setOperation(operation);
        log.setMethod(methodName);
        log.setCreateTime(LocalDateTime.now());
        sysLogMapper.insert(log);
    }
}
