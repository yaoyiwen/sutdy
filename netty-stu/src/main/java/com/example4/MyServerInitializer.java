package com.example4;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author: 姚轶文
 * @date:2018年7月17日 上午10:25:46
 * @version :
 * 
 */
public class MyServerInitializer   extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipline = ch.pipeline();
		
		int readerIdleTimeSeconds = 5;
		int writerIdleTimeSeconds = 7;
		int allIdleTimeSeconds = 10;
		
		//IdleStateHandler处理服务器端与客户端心跳
		//TimeUnit.SECONDS 为时间单位，此参数可以不写。默认时间单位=秒
		pipline.addLast(new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds,TimeUnit.SECONDS));
		pipline.addLast(new com.example4.MyServerHandler());
	}

}
