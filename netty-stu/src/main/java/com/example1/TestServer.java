package com.example1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 一个简单的HTTP服务端例子
 * 第一步，编写服务器端
 * @author Windows User
 *
 */
public class TestServer {

	public static void main(String[] args) {
		//创建2个工作循环组，bossGroup负责接收请求，workerGroup负责处理接收的请求
		EventLoopGroup bossGroup = new NioEventLoopGroup();   
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap(); //NETTY提供的一个简化类，方便启动服务。
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new TestServerInitializer());//TestServerInitializer自定义的初始化器
			
			ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//优雅关闭工作循环组,测试代码，一般情况下不用显示的调用次代码
		//bossGroup.shutdownGracefully();
		//workerGroup.shutdownGracefully();
	}
}
