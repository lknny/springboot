package springboot.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by ${ lknny@163.com } on 2018/5/27.
 */
public class SocketChannelTest {

	private static final int port = 8001;
	public static void main(String[] args) {
		for (int i=0;i<10;i++) {
			new SocketChannelImpl(port,i).start();
		}
	}
	private static class SocketChannelImpl extends Thread {
		private  int count = 0;
		private int port;
		public SocketChannelImpl(int port,int count){
			this.port = port;
			this.count = count;
		}
		@Override
		public void run() {

			try {
				SocketChannel socketChannel = SocketChannel.open();
				/*非阻塞*/
				socketChannel.configureBlocking(false);
				socketChannel.connect(new InetSocketAddress(port));

				for (;!socketChannel.finishConnect();) {
					System.out.println("connectting....");
					Thread.sleep(50);
				}
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				String content = "hello, i am client--------->" + count;
				byteBuffer.put(content.getBytes());
				byteBuffer.flip();
				socketChannel.write(byteBuffer);

				byteBuffer.clear();
				socketChannel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
