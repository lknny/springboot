package springboot.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ${ lknny@163.com } on 2018/4/28.
 */
public class DynamicProxy implements InvocationHandler {
	private Subject subject;
	//构造器注入
	public DynamicProxy(Subject subject) {
		this.subject = subject;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("动态代理开始。。。。");

		System.out.println("Object="+proxy.getClass().getName()+", method="+method.getName()+", args="+ Arrays.asList(args).toString());


		//调用真实对象
		method.invoke(subject, args);

		System.out.println("动态代理结束。。。");
		return null;
	}
}
