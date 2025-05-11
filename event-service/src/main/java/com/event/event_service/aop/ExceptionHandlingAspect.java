package com.event.event_service.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    // تعريف Pointcut
    @Pointcut("execution(* com.event.event_service.service.*.*(..))") // بيشمل كل الميتودز في الـ Service
    public void serviceMethods() {}

    // التعامل مع الاستثناءات
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logException(Exception exception) {
        // هنا بنسجل الاستثناء في اللوج
        logger.error("Exception occurred: {}", exception.getMessage(), exception);
    }
}
