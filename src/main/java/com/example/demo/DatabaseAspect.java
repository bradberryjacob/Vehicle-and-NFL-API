package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DatabaseAspect {
    @AfterReturning(pointcut = "execution(* VehicleDbo.*(..))", returning= "result")

    public void logReturn(JoinPoint jp,Object result)//it is advice (after returning advice)
    {
        Vehicle v = (Vehicle) result;
        if(v!=null) {
            System.out.println("CUSTOM VEHICLE RETURN " + v.getYear() + v.getMakeModel());
            System.out.println("END OF CUSTOM RETURN");
        }
    }

}
