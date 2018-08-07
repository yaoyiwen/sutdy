package com.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author: 姚轶文
 * @date:2018年8月3日 上午11:38:12
 * @version :
 * Buffer的Scattering和Gathering
 * 服務器端示例代碼，客戶端可以用telnet命令模擬
 */
public class NioTest11 {

	public static void main(String[] args) throws Exception{
		
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress address = new InetSocketAddress(8899);
		serverSocketChannel.socket().bind(address);
		
		int messageLength = 2+3+4;
		
		ByteBuffer[] buffers = new ByteBuffer[3]; //定义个buffer数组，里面有3个BUFFER
		
		//初始化3个BUFFER
		buffers[0] = ByteBuffer.allocate(2);
		buffers[1] = ByteBuffer.allocate(3);
		buffers[2] = ByteBuffer.allocate(4);
		SocketChannel socketChannel = serverSocketChannel.accept();
		
		while(true)
		{
			int bytesRead = 0;
			while(bytesRead < messageLength)
			{
				long r = socketChannel.read(buffers);
				bytesRead += r;
				System.out.println("bytesRead:"+bytesRead);
				
				Arrays.asList(buffers).stream()
						.map(buffer -> "position:" + buffer.position() + ", limit:" + buffer.limit())
						.forEach(System.out::println);
			}
			
			Arrays.asList(buffers).forEach(buffer -> 
			{
				buffer.flip(); //將所有buffer翻轉
			});
			
			long bytesWritten = 0;
			while(bytesWritten < messageLength)
			{
				long r = socketChannel.write(buffers);
				bytesWritten+= r;
			}
			Arrays.asList(buffers).forEach(buffer -> {
				buffer.clear();
			});
			
			System.out.println("bytesRead:"+bytesRead + ", bytesWritten:"+bytesWritten);
		}
	}
}
