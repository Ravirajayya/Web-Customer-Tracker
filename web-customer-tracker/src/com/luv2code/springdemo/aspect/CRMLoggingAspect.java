package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect
{
	// Setup Logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// Setup pointcut expression
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {};
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {};
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {};
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {};
	
	// Add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint)
	{
		// Display the method we are calling
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("===>>> in @Before: calling method:" + method);
		
		// Display the arguments to the end
		
		
		// Get the arguments
		Object args[] = theJoinPoint.getArgs();
		
		// Loop thru and display arguments
		for(Object tempArg: args)
		{
			myLogger.info("===>>> Arguments: " + tempArg);
		}
	}
	
	// Add @AfterReturning advice
	@AfterReturning(
					pointcut = "forAppFlow()",
					returning = "theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult )
	{
		// Display the method we are calling
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("===>>> in @AfterReturning: calling method:" + method);
		
		myLogger.info("===>>> Result: " + theResult);
	}
}
