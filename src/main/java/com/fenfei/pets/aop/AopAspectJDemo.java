package com.fenfei.pets.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Objects;

//@Aspect
//@Component
public class AopAspectJDemo {

    @Pointcut("@within(com.fenfei.pets.controller.*)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Objects doAround(ProceedingJoinPoint joinPoint) {
        return null;
    }
}
