package springboot.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by ${ lknny@163.com } on 2018/6/6.
 *
 *
 * 启动一个serverSocket，通过selector处理
 */
public class SelectorDemo {

	private static final int port = 8002;
	private static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

	public static void main(String[] args) {

		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
//			serverSocketChannel.bind(new InetSocketAddress(port));
			serverSocketChannel.configureBlocking(false);

			Selector selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				int n = selector.select();
				if (n == 0) {
					continue;
				}
				Iterator iterator = selector.selectedKeys().iterator();
				for (;iterator.hasNext();) {
					SelectionKey selectionKey = (SelectionKey) iterator.next();
					if (selectionKey.isAcceptable()) {
						ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
						SocketChannel socketChannel = serverSocketChannel.accept();
						registChannel(selector, socketChannel,SelectionKey.OP_READ);
					}
					if (selectionKey.isReadable()) {
						readData(selectionKey);
					}
					iterator.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void readData(SelectionKey selectionKey) throws IOException {
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
		byteBuffer.clear();
		while (socketChannel.read(byteBuffer) > 0) {
			byteBuffer.flip();
			while (byteBuffer.hasRemaining()) {
				System.out.print((char) byteBuffer.get());
			}
			System.out.println();
			byteBuffer.clear();
		}
	}

	private static void registChannel(Selector selector, SelectableChannel selectableChannel, int opt) throws IOException {
		if (null == selectableChannel) {
			return;
		}
		selectableChannel.configureBlocking(false);
		selectableChannel.register(selector, opt);
	}

}
