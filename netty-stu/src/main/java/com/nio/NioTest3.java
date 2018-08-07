package com.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;



/**
 * @author: 姚轶文
 * @date:2018年7月30日 上午11:56:41
 * @version :
 * 写文件
 */
public class NioTest3 {

	public static void main(String[] args)  throws Exception{
		FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
		FileChannel fileChannel = fileOutputStream.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(512);
		
		byte message[] = "hello nio".getBytes();
		for(int i=0 ; i<message.length ; i++)
		{
			buffer.put(message[i]);
		}
		
		buffer.flip();
		
		fileChannel.write(buffer);
		fileOutputStream.close();
	}
}
