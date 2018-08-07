package com.example4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author: 姚轶文
 * @date:2018年7月17日 上午10:47:24
 * @version :
 * 
 */
public class MyServerHandler  extends ChannelInboundHandlerAdapter{

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		if(evt instanceof IdleStateEvent)
		{
			IdleStateEvent event = (IdleStateEvent)evt;
			
			String eventType = null;
			//event.state返回三个枚举。分别对应的是这个Channel的读空闲、写空闲、读写空闲
			switch (event.state()) {
			case READER_IDLE:
				eventType = "读空闲";
				break;
				
			case WRITER_IDLE:
				eventType = "写空闲";
				break;
				
			case ALL_IDLE:
				eventType = "读写空闲";
				break;
			}
			
			System.out.println(ctx.channel().remoteAddress() +"超时："+eventType);
			ctx.channel().close();
		}
	}

}
