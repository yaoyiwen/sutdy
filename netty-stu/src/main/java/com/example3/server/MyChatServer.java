package com.example3.server;

import com.example2.server.MyServerInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 模拟聊天程序，假设ABC三个客户端，当某一个客户端上线后，控制台打印X上线，并通知另外已经在线的用户X已经上线
 * @author Windows User
 *
 */
public class MyChatServer {

	public static void main(String[] args) {
		
		//创建2个工作循环组，bossGroup负责接收请求，workerGroup负责处理接收的请求
		EventLoopGroup bossGroup = new NioEventLoopGroup();   
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap(); //NETTY提供的一个简化类，方便启动服务。
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new MyChatServerInitializer());//自定义的初始化器
			
			ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
