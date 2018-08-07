package com.nio.niotest13;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author: 姚轶文
 * @date:2018年8月6日 下午12:24:49
 * @version :
 * 
 */
public class NioServer {

	private static Map<String,SocketChannel> clientMap = new HashMap<String,SocketChannel>();
	
	public static void main(String[] args)throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false); //配置成非阻塞IO
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(8899));
		
		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true)
		{
			selector.select();
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			selectionKeys.forEach(selectionKey ->{
				SocketChannel client;
				if(selectionKey.isAcceptable())
				{
					ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
					try {
						client = server.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ);
						
						String key = "["+UUID.randomUUID().toString()+"]";
						clientMap.put(key, client);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(selectionKey.isReadable())
				{
					try {
					client = (SocketChannel)selectionKey.channel();
					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					int count = client.read(readBuffer);
					if(count > 0)
					{
						readBuffer.flip();
						Charset charset = Charset.forName("utf-8");
						String receivedMessage = String.valueOf(charset.decode(readBuffer).array());
						System.out.println(client +","+receivedMessage);
						
						String senderKey = null;
						
						for(Map.Entry<String, SocketChannel> entry : clientMap.entrySet())
						{
							if(client == entry.getValue())
							{
								senderKey = entry.getKey();
								break;
							}
						}
						for(Map.Entry<String, SocketChannel> entry : clientMap.entrySet())
						{
							SocketChannel value = entry.getValue();
							
							ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
							writeBuffer.put((senderKey + ": "+receivedMessage).getBytes());
							
							writeBuffer.flip();
							value.write(writeBuffer);
						}
						
					}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			selectionKeys.clear();
		}
	}
}
