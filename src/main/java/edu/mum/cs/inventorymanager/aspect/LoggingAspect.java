package edu.mum.cs.inventorymanager.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@Order(1)
public class LoggingAspect {
//
//	// setup logger
//	private Logger myLogger = Logger.getLogger(getClass().getName());
//
//	// add @Before advice
    @Before("execution(* edu.mum.cs.inventorymanager.controller.ui..*(..))")
	public void before(JoinPoint joinPoint) {
    	System.out.println(("Current navigation info..."));
    	
		System.out.println(Arrays.toString(joinPoint.getArgs()));
	}
//
//	

	@Around("execution(* edu.mum.cs.inventorymanager.controller.ui..*(..))")	
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		
		System.out.println("\n=====>>> Executing @Around on method: " + method);
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// now, let's execute the method
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			System.out.println(e.getMessage());
			
			// rethrow exception
			throw e;
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end - begin;
		
		System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");
		return result;
	}
//
//	
//
}
