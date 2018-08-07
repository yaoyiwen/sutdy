package com.example2.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {

	public static void main(String[] args) {
		
		EventLoopGroup eventLoopGroup  = new NioEventLoopGroup();  //客户端只需要一个事件循环组
		
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyClientInitializer());
			
			ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",8899).sync();
			channelFuture.channel().closeFuture().sync();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
