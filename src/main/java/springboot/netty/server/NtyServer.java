package springboot.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.time.LocalDateTime;

/**
 * Created by ${ lknny@163.com } on 2018/7/25.
 */
public class NtyServer {

	public static void main(String[] args) {
		try {
			new NtyServer().start(8001);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void start(int port) throws InterruptedException {
		EventLoopGroup bossEventloopGroup = new NioEventLoopGroup();
		EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
		try {

			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossEventloopGroup, workerEventLoopGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new ChildChannelHandler());

			ChannelFuture future = bootstrap.bind(port).sync();
			future.channel().closeFuture().sync();

		} finally {

			bossEventloopGroup.shutdownGracefully();
			workerEventLoopGroup.shutdownGracefully();
		}

	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel socketChannel) throws Exception {
			socketChannel.pipeline().addLast(new TimeServerHandler());
		}
	}

	private class TimeServerHandler extends ChannelInboundHandlerAdapter {

		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			ByteBuf byteBuf = (ByteBuf) msg;
			byte[] req = new byte[byteBuf.readableBytes()];
			byteBuf.readBytes(req);

			String msgContent = new String(req, "UTF-8");

			System.out.println("Time Server receive: " + msgContent);

			if ("query time".equalsIgnoreCase(msgContent)) {
				String time = LocalDateTime.now().toString();
				ByteBuf buf = Unpooled.copiedBuffer(time.getBytes());
				ctx.writeAndFlush(buf);
			} else {
				String errMsg = "error command!";
				ByteBuf buf = Unpooled.copiedBuffer(errMsg.getBytes());
				ctx.writeAndFlush(buf);
			}


		}
	}

}
