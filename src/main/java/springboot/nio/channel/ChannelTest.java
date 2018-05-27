package springboot.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by ${ lknny@163.com } on 2018/5/13.
 */
public class ChannelTest {
	private static final int port = 8123;

	public static void main(String[] args) {
		try {
			//打开SocketChannel
			SocketChannel sc = SocketChannel.open( );
			sc.connect (new InetSocketAddress("somehost", port));

			//打开ServerSocketChannel
			ServerSocketChannel ssc = ServerSocketChannel.open( );
			ssc.socket( ).bind (new InetSocketAddress (port));

			DatagramChannel dc = DatagramChannel.open( );

			//FileChannel只能通过 RandomAccessFile、FileInputStream 或 FileOutputStream 对象上调用 getChannel( )方法来获取
			RandomAccessFile raf = new RandomAccessFile ("somefile", "r");
			FileChannel fc = raf.getChannel( );

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
