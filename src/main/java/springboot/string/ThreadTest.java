package springboot.string;

import java.lang.management.ManagementFactory;

/**
 * Created by ${ lknny@163.com } on 2018/9/30.
 */
public class ThreadTest {

	public static void main(String[] args) {
		Thread thread=new Thread("thread1-lk") {
		};
		thread.setDaemon(true);
		thread.start();

		// get name representing the running Java virtual machine.
		String name = ManagementFactory.getRuntimeMXBean().getName();
		System.out.println(name);
// get pid
//		String pid = name.split("@")[0];
		System.out.println("Pid is:" + name);




		try {
			Thread.sleep(600000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
