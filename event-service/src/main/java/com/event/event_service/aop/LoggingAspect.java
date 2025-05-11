package com.event.event_service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.event.event_service.service.*.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long duration = end - start;

        String message = "[" + LocalDateTime.now() + "] Method " + joinPoint.getSignature().getName()
                + " executed successfully in " + duration + " ms.\n";

        // write log to file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("event-service-logs.txt", true))) {
            writer.write(message);
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }

        return result;
    }
}
