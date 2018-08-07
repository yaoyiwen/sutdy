package com.nio.niotest13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: 姚轶文
 * @date:2018年8月6日 下午2:31:45
 * @version :
 * 
 */
public class NioClient {

	public static void main(String[] args) throws IOException{
		
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		
		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT); //监听是否链接到服务端
		socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));
		
		while(true)
		{
			selector.select();
			Set<SelectionKey> keySet = selector.selectedKeys();
			
			for(SelectionKey selectionKey : keySet)
			{
				if(selectionKey.isConnectable()) //判断链接是否准备建立链接
				{
					SocketChannel client = (SocketChannel)selectionKey.channel();
					
					if(client.isConnectionPending()) //判断链接是否正在进行
					{
						client.finishConnect();//完成链接建立
						
						ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
						writeBuffer.put((LocalDateTime.now()+"链接成功").getBytes());
						
						writeBuffer.flip();
						client.write(writeBuffer);
						
						ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
						executorService.submit(()->{
							while(true)
							{
								writeBuffer.clear();
								InputStreamReader input = new InputStreamReader(System.in);
								BufferedReader br = new BufferedReader(input);
								String sendMessage = br.readLine();
								
								writeBuffer.put(sendMessage.getBytes());
								writeBuffer.flip();
								client.write(writeBuffer);
							}
						});
					}
					
					client.register(selector, SelectionKey.OP_READ);
				}
				else if(selectionKey.isReadable())
				{
					SocketChannel client = (SocketChannel)selectionKey.channel();
					
					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					int count = client.read(readBuffer);
					
					if(count > 0)
					{
						String receiveMessage = new String(readBuffer.array() , 0 , count);
						System.out.println(receiveMessage);
					}
				}
			}
			
			keySet.clear();
		}
		
	}
}
