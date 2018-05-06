package springboot.gc;

/**
 * Created by ${ lknny@163.com } on 2018/5/5.
 * <p>
 * 启动参数：
 * -verbose:gc
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -Xloggc:C:\Users\ligj\Downloads\gc.log
 * <p>
 * <p>
 * -XX:+PrintGC 与 -verbose:gc 是一样的，可以认为-verbose:gc 是 -XX:+PrintGC的别名.
 * -XX:+PrintGCDetails 在启动脚本可以自动开启-XX:+PrintGC , 如果在命令行使用jinfo开启的话，不会自动开启-XX:+PrintGC
 *
 *
 * -XX:+PrintGC 输出GC日志
 -XX:+PrintGCDetails 输出GC的详细日志
 -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
 -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
 -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
 *
 */
public class GCTest {

	public Object instance = null;
	private static final int _1MB = 1024 * 1024;
	private byte[] bigSize = new byte[2 * _1MB];

	public static void testGC() {
		GCTest obja = new GCTest();
		GCTest objb = new GCTest();
		obja.instance = objb;
		objb.instance = obja;
		obja = null;
		objb = null;
		System.gc();
	}

	public static void main(String[] args) {
		testGC();
	}
}
