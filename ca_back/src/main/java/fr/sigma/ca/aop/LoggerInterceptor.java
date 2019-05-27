package fr.sigma.ca.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component("loggerInterceptor")
@Aspect
public class LoggerInterceptor {
 
 @Before("execution(* fr.sigma.ca.service.*.*(..))")
 public void logBefore(JoinPoint joinPoint) {
  Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
  if (logger.isInfoEnabled()) 
   logger.info(joinPoint.getSignature().toString());
 }
}