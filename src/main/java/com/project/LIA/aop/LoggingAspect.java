package com.project.LIA.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.project.LIA.service..*(..))")
    public void logBefore() {
        logger.info("메소드 시행 전 ");
    }

    @AfterReturning("execution(* com.project.LIA.service..*(..))")
    public void logAfterReturning(){
        logger.info("메소드 정상 실행 후 ");
    }

    @AfterThrowing("execution(* com.project.LIA.service..*(..))")
    public void logAfterThrowing(){
        logger.info("");
    }

    @After("execution(* com.project.LIA.service..*(..))")
    public void logAfter(){
        logger.info("메소드 종료 후 성공 여부와 상관 없이 항상 실행 ");
    }

    @Around("execution(* com.project.LIA.service..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("메소드 실행 전");
        Object result;
        try {
            result = joinPoint.proceed(); // 실제 메소드 실행
        }catch (Exception e){
            logger.error("메소드 실행 중 예외 발생"+e.getMessage());
            throw e;
        }
        logger.info("메소드 실행 후 ");
        return result;
    }

}
