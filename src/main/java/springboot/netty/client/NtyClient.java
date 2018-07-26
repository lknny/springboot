package springboot.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;


/**
 * Created by ${ lknny@163.com } on 2018/7/26.
 */
public class NtyClient extends Thread {

	private final String host;
	private final int port;
	private final String id = UUID.randomUUID().toString();
	public NtyClient(String host, int port) {
		this.port = port;
		this.host = host;
	}

	@Override
	public void run()  {
		EventLoopGroup eventExecutors = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventExecutors)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(new TimeClientChildHandler());
						}
					});

			ChannelFuture future = null;
			try {
				future = bootstrap.connect(host, port).sync();
				future.channel().closeFuture().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			eventExecutors.shutdownGracefully();
		}
	}




	/*其实用这两个抽象类是有讲究的，在客户端的业务Handler继承的是SimpleChannelInboundHandler，而在服务器端继承的是ChannelInboundHandlerAdapter。
	最主要的区别就是SimpleChannelInboundHandler在接收到数据后会自动release掉数据占用的Bytebuffer资源(自动调用Bytebuffer.release())。
	而为何服务器端不能用呢，因为我们想让服务器把客户端请求的数据发送回去，而服务器端有可能在channelRead方法返回前还没有写完数据，因此不能让它自动release。*/
	private class TimeClientChildHandler extends SimpleChannelInboundHandler {
		private final ByteBuf firstMsg;

		public TimeClientChildHandler() {
			byte[] bytes = "query time".getBytes();
			firstMsg = Unpooled.buffer(bytes.length);
			firstMsg.writeBytes(bytes);
		}
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			ctx.writeAndFlush(firstMsg);
		}
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
			ByteBuf byteBuf = (ByteBuf) o;
			byte[] req = new byte[byteBuf.readableBytes()];
			byteBuf.readBytes(req);
			String msg = new String(req, "UTF-8");
			System.out.println("Time client(ID="+id+") get time: "+msg);
		}
	}
}
