package com.rahul.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup loggers
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
		// controller pointcut
		@Pointcut("execution(* com.rahul.springdemo.controller.*.*(..))")
		private void forControllerPackage() {}
		
		// service pointcut
		@Pointcut("execution(* com.rahul.springdemo.service.*.*(..))")
		private void forServicePackage() {}
		
		// DAO pointcut
		@Pointcut("execution(* com.rahul.springdemo.dao.*.*(..))")
		private void forDaoPackage() {}
		
		// Combining all pointcut declarations
		@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() ")
		public void forAppFlow() {}
	
	// add @Before advice
		
		@Before("forAppFlow()")
		public void before(JoinPoint theJoinPoint) {
			
			// display the method we are calling
			String theMethod = theJoinPoint.getSignature().toShortString();
			myLogger.info("========>>> In @Before Advice from method : " + theMethod );
			
			// display the arguments of the method
			
			// get the arguments
			Object[] args = theJoinPoint.getArgs();
			
			// loop through and display arguments
			for (Object tempArg : args) {
				myLogger.info("========>>> Arguments : " + tempArg);
			}
		}
			
	// add @AfterReturning advice
		
		@AfterReturning(
				pointcut = "forAppFlow()",
				returning = "theResult"
				)
		public void afterReturning(JoinPoint theJoinPoint , Object theResult) {
			
			// display the method we are returning from
			String theMethod = theJoinPoint.getSignature().toShortString();
			myLogger.info("========>>> In @AfterReturning Advice from method : " + theMethod );
			
			// display the data returned
			myLogger.info("========>>> Result : " + theResult );	
			
		}
		
}