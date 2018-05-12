package springboot.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * Created by ${ lknny@163.com } on 2018/5/12.
 */
public class BufferTest {

	public static void main(String[] args) {
		//初始化
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//数据填充
		buffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');

		System.out.println(buffer.toString());//java.nio.HeapByteBuffer[pos=5 lim=1024 cap=1024]
		System.out.println((char) (buffer.get(0)));//H

		//数据修改
		buffer.put(0, (byte) 'M').put((byte) 'w');

		System.out.println(buffer.toString());//java.nio.HeapByteBuffer[pos=6 lim=1024 cap=1024]
		System.out.println((char) (buffer.get(0)));//M

		//flip将position归0，将一个待填充状态的缓冲区翻转成准备读出状态
		System.out.println(buffer);//java.nio.HeapByteBuffer[pos=6 lim=1024 cap=1024]
		buffer.flip();
		//buffer.rewind();//类似flip，但是不影响limit属性。java.nio.HeapByteBuffer[pos=0 lim=1024 cap=1024]
		System.out.println(buffer);//java.nio.HeapByteBuffer[pos=0 lim=6 cap=1024]

		//使用hasRemaining()判断是否达到缓冲区上界
		while (buffer.hasRemaining()) {
			System.out.print((char) (buffer.get()));
		}//Mellow
		System.out.println("");

		//压缩，compact丢弃已释放数据，保留未释放数据
		buffer.compact();
		//mark，在某个位置标记，reset( )函数将位置设为当前的标记值。
		// 如果标记值未定义，调 用 reset( )将导致 InvalidMarkException 异常。
		// 一些缓冲区函数会抛弃已经设定的标记 (rewind( )，clear( )，以及 flip( )总是抛弃标记)。
		buffer.position(2).mark();


		//批量取
		byte[] myArray = new byte[1000];
		buffer.get(myArray);
		//等价于:
		buffer.get(myArray, 0, myArray.length);

		//批量存
		byte[] myString = new byte[1000];
		buffer.put(myString);
		//等价于:
		buffer.put(myString, 0, myString.length);

		CharBuffer buffer2 = CharBuffer.allocate(8);
		buffer2.position(3).limit(6).mark().position(5);
		CharBuffer dupeBuffer = buffer2.duplicate();
		buffer2.clear();

		//视图缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate (7).order (ByteOrder.BIG_ENDIAN);
		CharBuffer charBuffer = byteBuffer.asCharBuffer( );

	}

}
