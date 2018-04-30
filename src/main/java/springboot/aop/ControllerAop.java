package springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${ lknny@163.com } on 2018/4/30.
 */
@Aspect
@Component
public class ControllerAop {

	@Pointcut("execution(* springboot.controller..*.*(..)))")
	public void home(){
		System.out.println("------------》AOP");
	}


	@Before("home()")
	public void doBefore(JoinPoint joinPoint) throws Throwable{
		System.out.println("我是前置通知!!!");
		//获取目标方法的参数信息
		Object[] obj = joinPoint.getArgs();
		//AOP代理类的信息
		joinPoint.getThis();
		//代理的目标对象
		joinPoint.getTarget();
		//用的最多 通知的签名
		Signature signature = joinPoint.getSignature();
		//代理的是哪一个方法
		System.out.println(signature.getName());
		//AOP代理类的名字
		System.out.println(signature.getDeclaringTypeName());
		//AOP代理类的类（class）信息
		signature.getDeclaringType();
		//获取RequestAttributes
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		//从获取RequestAttributes中获取HttpServletRequest的信息
		HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
		//如果要获取Session信息的话，可以这样写：
		//HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
		Enumeration<String> enumeration = request.getParameterNames();
		Map<String, String> parameterMap = new HashMap<String, String>();
		while (enumeration.hasMoreElements()){
			String parameter = enumeration.nextElement();
			parameterMap.put(parameter,request.getParameter(parameter));
		}
			System.out.println("请求的参数信息为："+parameterMap.toString());

	}
}
