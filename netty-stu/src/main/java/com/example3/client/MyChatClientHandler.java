package com.example3.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: 姚轶文
 * @date:2018年7月13日 上午10:50:17
 * @version :
 * 
 */
public class MyChatClientHandler extends SimpleChannelInboundHandler<String>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}

}
