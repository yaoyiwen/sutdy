package com.example3.client;

import com.example3.server.MyChatServerHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author: 姚轶文
 * @date:2018年7月13日 上午10:47:14
 * @version :
 * 
 */
public class MyChatClientInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipline = ch.pipeline();
		pipline.addLast(new DelimiterBasedFrameDecoder(4096,Delimiters.lineDelimiter())); //解码器
		pipline.addLast(new StringDecoder(CharsetUtil.UTF_8));
		pipline.addLast(new StringEncoder(CharsetUtil.UTF_8));
		pipline.addLast(new MyChatClientHandler());
	}

}
