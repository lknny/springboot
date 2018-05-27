package springboot.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by ${ lknny@163.com } on 2018/5/19.
 */
public class FileChannelTest {
	public static void main(String[] args) throws Exception {
		String filePath=ClassLoader.getSystemResource("file_channel").getFile();
		write(filePath);
		read(filePath);
	}


	public static void read(String filePath) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(new File(filePath));
		/*一个FileChannel对象却只能通过
		在一个打开的RandomAccessFile、FileInputStream或FileOutputStream对象上调用getChannel()方法来获取，
		开发者不能直接创建一个FileChannel*/
		FileChannel fileChannel = fileInputStream.getChannel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(500);
		//将文件channel读入缓冲区
		fileChannel.read(byteBuffer);

		byteBuffer.flip();

		while (byteBuffer.hasRemaining()){
			System.out.print((char)byteBuffer.get());
		}

		byteBuffer.clear();
		fileChannel.close();
	}

	public static void write(String filePath) throws Exception {
		/*写文件，使用FileOutputStream，RandomAccessFile都可以。*/
/*		RandomAccessFile file = new RandomAccessFile(filePath,"rw");*/
		FileOutputStream file = new FileOutputStream(new File(filePath));
		ByteBuffer byteBuffer = ByteBuffer.allocate(500);
		String str = "hello LK";
		/*数据写入缓冲区*/
		byteBuffer.put(str.getBytes());
		byteBuffer.flip();

		FileChannel fileChannel = file.getChannel();

		//将缓冲区数据写入文件通道
		fileChannel.write(byteBuffer);

		byteBuffer.clear();
		fileChannel.close();
	}
}
