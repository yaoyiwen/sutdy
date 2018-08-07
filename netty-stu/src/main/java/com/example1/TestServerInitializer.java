package com.example1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 第二步，自定义的初始化器
 * @author Windows User
 *
 */

//当客户端和服务器端的链接创建后，此类就会被创建。接着执行initChannel方法。
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("httpServerCodec",new HttpServerCodec()); //NETTY自带的处理器
		pipeline.addLast("testHttpServerHandler",new TestHttpServerHandler()); //在这里添加自定义的处理器Hanler
	}

}
