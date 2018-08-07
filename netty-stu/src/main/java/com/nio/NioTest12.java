package com.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: 姚轶文
 * @date:2018年8月5日 下午3:50:13
 * @version :
 * NIO中selector 选择器的作用
 */
public class NioTest12 {

	public static void main(String[] args) throws Exception{
		int ports[] = new int[5];
		
		ports[0] = 5000;
		ports[1] = 5001;
		ports[2] = 5002;
		ports[3] = 5003;
		ports[4] = 5004;
		
		Selector selector = Selector.open();
		
		for(int i=0 ; i<ports.length ; i++)
		{
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);//true=阻塞 false=非阻塞
			ServerSocket serverSocket = serverSocketChannel.socket(); //获取Socket
			InetSocketAddress address = new InetSocketAddress(ports[i]); 
			serverSocket.bind(address); //对Socket绑定端口
			
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //把channel注册到selector上 等待客户端接入
			
			System.out.println("监听端口:"+ports[i]);
		}
		
		while (true) 
		{
			int numbers = selector.select();
			System.out.println("numbers:"+numbers);
			
			Set<SelectionKey> selectionKeys = selector.selectedKeys(); 
			System.out.println("selectionKeys:"+selectionKeys);
			Iterator<SelectionKey> iter = selectionKeys.iterator();
			
			while(iter.hasNext())
			{
				SelectionKey selectionKey = iter.next();
				if(selectionKey.isAcceptable()) //当有客户端发起链接
				{
					ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false);
					
					socketChannel.register(selector, SelectionKey.OP_READ);//监听读
					
					iter.remove(); //当链接建立好之后，将此selectionKey删除，否则一直监听链接事件
					
					System.out.println("获取客户端链接："+socketChannel);
				}
				else if(selectionKey.isReadable())//盘点客户端是否可读
				{
					SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
					int byteRead = 0;
					while(true)
					{
						ByteBuffer byteBuffer = ByteBuffer.allocate(512);
						byteBuffer.clear();
						int red = socketChannel.read(byteBuffer);
						if(red <= 0)
						{
							break;
						}
						
						byteBuffer.flip();
						socketChannel.write(byteBuffer);
						
						byteRead+=red;
					}
					System.out.println("读取："+byteRead+",来自于："+socketChannel);
					iter.remove();
				}
			}
		}
	}
}
