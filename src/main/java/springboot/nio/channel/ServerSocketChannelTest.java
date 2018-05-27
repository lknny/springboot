package springboot.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * Created by ${ lknny@163.com } on 2018/5/27.
 */
public class ServerSocketChannelTest {

	private static final int port = 8001;

	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		/*非阻塞*/
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(port));
		System.out.println("ServerSocketChannel is OK，waiting @[" + LocalDateTime.now() + "]");

		for (; ; ) {
			SocketChannel socketChannel = serverSocketChannel.accept();
			if (socketChannel == null) {
				Thread.sleep(1000);
				System.out.println("ServerSocketChannel sleep 1000ms.");
				continue;
			}
			String connectIP = socketChannel.socket().getRemoteSocketAddress().toString();
			System.out.println("客户端已有数据到来，客户端ip为:" + connectIP + ", 时间为" + LocalDateTime.now());
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			socketChannel.read(byteBuffer);
			byteBuffer.flip();
			while (byteBuffer.hasRemaining()) {
				System.out.print((char) byteBuffer.get());
			}
			socketChannel.close();
		}
	}
}
