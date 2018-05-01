package springboot.cglib;

/**
 * Created by ${ lknny@163.com } on 2018/4/28.
 */
public class RealSubject  {
	public void hello(String name) {
		System.out.println("原始方法：hello "+ name+",This is "+this.getClass().getName());
	}
}
