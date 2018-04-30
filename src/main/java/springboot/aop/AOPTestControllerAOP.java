package springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
		System.out.println("AOPTest-----------before-----------2--------->");
	}

	@Around("@annotation(aopTest)")
	public Object around(ProceedingJoinPoint pjp, AOPTest aopTest) {
		//获取注解里的值
		System.out.println("AOPTest-------@Around(\"@annotation(AOPTest)\")-----1----->" + aopTest.hello());

		try {


			return pjp.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			return null;
		}
	}

	//后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
	@After("access()")
	public void after(JoinPoint jp){
		// 处理完请求，返回内容
		System.out.println("AOPTest-------@After(\"access()\")-----4----->");
	}

	@AfterReturning(returning = "ret", pointcut = "access()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容  
		System.out.println("AOPTest-------@AfterReturning(returning = \"ret\")-----6----->ret="+ret.toString());
	}

	//后置异常通知  
	@AfterThrowing("access()")
	public void throwss(JoinPoint jp){
		// 处理完请求，返回内容
		System.out.println("AOPTest-------@AfterThrowing(\"access()\")-----5----->");
	}




}
