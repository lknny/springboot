package springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by ${ lknny@163.com } on 2018/4/30.
 */
@Component
@Aspect
public class AOPTestControllerAOP {

	@Pointcut(value="@annotation(springboot.aop.AOPTest)")
	public void access() {

	}


	@Before("access()")
	public void deBefore(JoinPoint joinPoint) throws Throwable {
		System.out.println("AOPTest-----------before-------------------->");
	}

	@Around("@annotation(aopTest)")
	public Object around(ProceedingJoinPoint pjp, AOPTest aopTest) {
		//获取注解里的值
		System.out.println("AOPTest-------@Around(\"@annotation(AOPTest)\")---------->" + aopTest.hello());
		try {
			return pjp.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			return null;
		}
	}
}
