package springboot.netty.client;

/**
 * Created by ${ lknny@163.com } on 2018/7/26.
 */
public class NtyClientApp {
	private static final String host = "127.0.0.1";
	private static final int port = 8001;

	public static void main(String[] args) {
		for (int i=0;i<10;i++) {
			new NtyClient(host,port).start();
		}
	}
}
