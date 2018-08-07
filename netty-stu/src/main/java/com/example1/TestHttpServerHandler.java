package com.example1;

import java.net.URI;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * 第三步，编写处理器
 * @author Windows User
 *
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

	/**
	 * 处理服务器端接受到的请求
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		// TODO Auto-generated method stub
		
		if(msg instanceof HttpRequest)  //判断接受到的数据是否为http请求
		{
			HttpRequest httpRequest = (HttpRequest)msg;
			URI uri = new URI(httpRequest.getUri());
			if((uri.getPath()).equals("/favicon.ico"))  //部分浏览器会单独请求一次ICO图标
			{
				System.out.println("请求favicon.ico文件");
				return;
			}
			
			ByteBuf content = Unpooled.copiedBuffer("Hello World",CharsetUtil.UTF_8); //返回的内容和字符集编码格式
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);//设置http协议版本，返回响应状态，内容
			response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain"); //设置返回的http协议格式
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());//设置返回内容的长度
			
			ctx.writeAndFlush(response); //立即将数据返回
			ctx.channel().close(); //关闭通道
		}
	}

}
