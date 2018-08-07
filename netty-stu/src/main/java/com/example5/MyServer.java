package com.example5;

import com.example4.MyServerInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: 姚轶文
 * @date:2018年7月17日 下午2:19:31
 * @version :
 * 
 */
public class MyServer {

	public static void main(String[] args) {
		
		//创建2个工作循环组，bossGroup负责接收请求，workerGroup负责处理接收的请求
		EventLoopGroup bossGroup = new NioEventLoopGroup();   
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap(); //NETTY提供的一个简化类，方便启动服务。
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO)) // handler是正对于bossGroup的处理类
					.childHandler(new WebSocketChannelInitializer());//自定义的初始化器  childHandler是针对workerGroup的处理类
			
			ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
