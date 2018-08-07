package com.example2.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<String>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("我是服务端----客户端地址："+ctx.channel().remoteAddress()+", 收到的消息："+msg);
		
		ctx.channel().writeAndFlush("服务端已经收到消息");
	}
	
	
	//当出现异常，执行此方法
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace(); //打印异常信息
		ctx.close();//关闭链接
	}

}
