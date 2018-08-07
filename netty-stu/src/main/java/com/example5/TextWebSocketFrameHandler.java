package com.example5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author: 姚轶文
 * @date:2018年7月17日 下午2:46:51
 * @version :
 *用于处理文本数据类型的Handler 
 */
public class TextWebSocketFrameHandler   extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("[服务器]"+msg.text());
		
		ctx.channel().writeAndFlush(new TextWebSocketFrame("收到消息"));
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handlerAdded :"+ctx.channel().id().asLongText());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handlerRemoved :"+ctx.channel().id().asLongText());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("链接发生异常");
	}

}
