package com.example2.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyClientInitializer  extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipline = ch.pipeline();
		pipline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4,0,4)); //解码器
		pipline.addLast(new LengthFieldPrepender(4)); 
		pipline.addLast(new StringDecoder(CharsetUtil.UTF_8));
		pipline.addLast(new StringEncoder(CharsetUtil.UTF_8));
		pipline.addLast(new MyClientHandler());
	}

}
