package com;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
目前为止，我们虽然接收到了数据，但没有做任何的响应。然而一个服务端通常会对一个请求作出响应。
让我们学习怎样在 ECHO 协议的实现下编写一个响应消息给客户端，这个协议针对任何接收的数据都会返回一个响应。
 
和 discard server 唯一不同的是把在此之前我们实现的 channelRead() 方法，返回所有的数据替代打印接收数据到控制台上的逻辑。
因此，需要把 channelRead() 方法修改如下：
*/

public class DiscardServerHandler extends ChannelInboundHandlerAdapter{

	 @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		 System.out.println("Demo2.DiscardServerHandler.channelRead");
		 ctx.write(msg); // (1)
	     ctx.flush(); // (2)
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
	        // 当出现异常就关闭连接
	        cause.printStackTrace();
	        ctx.close();
	    }
}


/**

1.ChannelHandlerContext 对象提供了许多操作，使你能够触发各种各样的 I/O 事件和操作。
这里我们调用了 write(Object) 方法来逐字地把接受到的消息写入。请注意不同于 DISCARD 的例子我们并没有释放接受到的消息，
这是因为当写入的时候 Netty 已经帮我们释放了。

2.ctx.write(Object) 方法不会使消息写入到通道上，他被缓冲在了内部，你需要调用 ctx.flush() 方法来把缓冲区中数据强行输出。
或者你可以用更简洁的 cxt.writeAndFlush(msg) 以达到同样的目的
*/
