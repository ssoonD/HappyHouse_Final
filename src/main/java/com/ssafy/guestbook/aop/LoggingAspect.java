package com.ssafy.guestbook.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

// AOP
// 관점 제한 프로그램
// 언제 메소드를 실행할 것인지

// @component
// 모든 녀석 autowired해라
// 별로 좋은 방법은 아니다
@Component
@Aspect
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	// 메소드 실행 전에
	@Before(value = "execution(* com.ssafy.guestbook.model..GuestBook*.*(..))")
	public void loggin(JoinPoint joinPoint) {
		logger.debug("메서드 선언부 : {} 전달 파라미터 : {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
	}

	// 다시한번 메소드를 실행해서 결과를 가져와라?
	@Around(value = "execution(* com.ssafy.guestbook.model..GuestBook*.list*(..))")
	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object proceed = joinPoint.proceed();

		stopWatch.stop();
		logger.debug("summary : {}", stopWatch.shortSummary());
		logger.debug("totalTime : {}", stopWatch.getTotalTimeMillis());
		logger.debug("pertty : {}", stopWatch.prettyPrint());

		return proceed;
	}

	// 정상적으로 실행 - try
	@AfterReturning(value = "execution(* com.ssafy.guestbook.model..GuestBook*.list*(..))", returning = "obj")
	public void afterReturningMethod(JoinPoint joinPoint, Object obj) {
		logger.debug("afterReturning call method : {} ", joinPoint.getSignature());
		logger.debug("return value : {}", obj);
	}

	// 실행할 때 에러 발생 - catch
	@AfterThrowing(value = "execution(* com.ssafy.guestbook.model..GuestBook*.list*(..))", throwing = "exception")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception exception) {
		logger.debug("afterThrowing call method : {}", joinPoint.getSignature());
		logger.debug("exception : {}", exception);
	}

	// 무조건 실행 - finally
	@After(value = "execution(* com.ssafy.guestbook.model..GuestBook*.list*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		logger.debug("after call method : {}", joinPoint.getSignature());
	}

}
