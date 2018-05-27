package springboot.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by ${ lknny@163.com } on 2018/5/13.
 */
public class ChannelTest2 {

	public static void main(String[] args) throws IOException {

		//一个读通道，一个写通道
		ReadableByteChannel source = Channels.newChannel(System.in);
		WritableByteChannel dest = Channels.newChannel(System.out);

		channelCopy(source,dest);

		source.close();
		dest.close();
	}

	private static void channelCopy(ReadableByteChannel source, WritableByteChannel dest) throws IOException{

		ByteBuffer byteBuffer = ByteBuffer.allocate(16 * 1024);
		ByteBuffer flag = ByteBuffer.allocate(4);
		while (source.read(byteBuffer) != -1) {
			byteBuffer.flip();
			//输出标记
			flag.put((byte)'-').put((byte)'-').put((byte)'-').put((byte) '>');
			flag.flip();
			dest.write(flag);
			dest.write(byteBuffer);
			flag.clear();

			byteBuffer.compact();
		}
		byteBuffer.flip();
		//确保缓冲区排干净
		while (byteBuffer.hasRemaining()) {
			flag.putChar('-').putChar('-').putChar('-');
			flag.flip();
			dest.write(byteBuffer);
			flag.clear();
		}

	}
}
