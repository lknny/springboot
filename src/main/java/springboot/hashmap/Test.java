package springboot.hashmap;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ${ lknny@163.com } on 2018/5/6.
 */
public class Test extends Thread{

	private static AtomicInteger index = new AtomicInteger(0);

	private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	@Override
	public void run() {
		while (index.get()<100000) {
			map.put(index.get(), index.get());
			index.incrementAndGet();
		}
	}

	public static void main(String[] args) {
		System.out.println(index.get());
		Executor executor = Executors.newFixedThreadPool(5);
		executor.execute(new Test());
		executor.execute(new Test());
		executor.execute(new Test());
		executor.execute(new Test());
	}
}
