package com.example3.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: 姚轶文
 * @date:2018年7月13日 上午10:43:56
 * @version :
 * 
 */
public class MyChatClient {

	public static void main(String[] args) {
		
		EventLoopGroup eventLoopGroup  = new NioEventLoopGroup();  //客户端只需要一个事件循环组
		
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());
			
			Channel channel = bootstrap.connect("127.0.0.1",8899).sync().channel();
			//channelFuture.channel().closeFuture().sync();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); //从控制台键盘输入
			
			while (true) {
				channel.writeAndFlush(bufferedReader.readLine()+"\r\n");
			}
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
