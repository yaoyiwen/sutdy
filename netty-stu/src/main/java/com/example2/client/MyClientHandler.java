package com.example2.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<String>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("我是客户端-----服务器端地址："+ctx.channel().remoteAddress()+", 收到的消息："+msg);
		
		ctx.channel().writeAndFlush("客户端已经收到服务端消息");
	}

	

	//当出现异常，执行此方法
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			// TODO Auto-generated method stub
		cause.printStackTrace(); //打印异常信息
		ctx.close();//关闭链接
	}	
		
	//当链接建立好之后，会触发此方法	
	@Override
	public void channelActive(final ChannelHandlerContext ctx) { // (1)

		ctx.channel().writeAndFlush("我是客户端，问候服务端。。。。。。"); 
	}
}
