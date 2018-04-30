package springboot.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by ${ lknny@163.com } on 2018/4/28.
 */
public class Main {

	public static void main(String[] args) {

//		被代理的对象
		Subject subject = new RealSubject();

//		代理类，被代理对象构造器注入
		InvocationHandler invocationHandler = new DynamicProxy(subject);



//		1. 参数1使用invocationHandler的ClassLoader对象，加载代理对象
//		2. 参数2为代理对象提供的接口是真实对象所实现的接口，表示我要代理真实对象，这样就可以调用这组接口方法了
//		3. 参数3将代理对象关联到了上方的invocation
		Subject subject1 =
				(Subject) Proxy.newProxyInstance(invocationHandler.getClass().getClassLoader(), subject.getClass().getInterfaces(), invocationHandler);

		subject1.hello("LK");
	}
}
