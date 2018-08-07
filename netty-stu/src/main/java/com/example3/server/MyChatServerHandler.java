package com.example3.server;

import java.util.Iterator;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler  extends SimpleChannelInboundHandler<String>{

	//创建Channel组，用来保存客户端与服务器端连接的channel
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override //建立连接的回调方法
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("channelGroupSize = "+channelGroup.size());
		Channel channel = ctx.channel();
		
		channelGroup.writeAndFlush("[服务器]---->"+channel.remoteAddress()+" 上线"); //通知所有客户端，欢迎X

		channelGroup.add(channel);
	}
	
	@Override //链接断开的回调方法
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("[服务器]---->"+channel.remoteAddress()+" 离线"); //通知所有客户端，X离线
		
		//不需要显示的调用channelGroup.romve方法删除这个channel链接。 NETTY会自动处理。
	}
	
	@Override //链接处于活动状态的回调方法
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Channel channel = ctx.channel();
		System.out.println("[控制台打印]---->"+channel.remoteAddress()+" 上线");
	}
	
	@Override //链接处于非活动状态的回调方法
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Channel channel = ctx.channel();
		System.out.println("[控制台打印]---->"+channel.remoteAddress()+" 下线");
	}
	
	@Override //收到消息的回调方法
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// TODO Auto-generated method stub
		
		Channel channel = ctx.channel();
		Iterator<Channel> it = channelGroup.iterator();
		while(it.hasNext())
		{
			Channel ch = it.next();
			if(channel != ch)
			{
				ch.writeAndFlush(channel.remoteAddress()+"发送消息："+ msg+"\n");
			}
			else
			{
				ch.writeAndFlush("[自己]："+ msg+"\n");
			}
		}

	}
	
	
	@Override //出现异常的回调方法
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			// TODO Auto-generated method stub
		cause.printStackTrace(); //打印异常信息
		ctx.close();//关闭链接
	}	
	
	
	
}
