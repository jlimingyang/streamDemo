package com.demo.spring.log;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AdvanceLogAop {
    @Pointcut("@annotation(com.demo.spring.log.PrintLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        String className = point.getTarget().getClass().getName();
        log.debug("执行方法:{},耗时:{},入参:{}",className,time,new Gson().toJson(point.getArgs()));
        return result;
    }

}