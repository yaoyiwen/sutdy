package com.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: 姚轶文
 * @date:2018年8月3日 上午10:23:44
 * @version :
 * NIO零拷贝
 */
public class NioTest8 {

	public static void main(String[] args)throws Exception {
		FileInputStream inputStream = new FileInputStream("input.txt");
		FileOutputStream outputStream = new FileOutputStream("output.txt");
		
		FileChannel inputChannel = inputStream.getChannel();
		FileChannel outputChannel = outputStream.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);//使用direct直接缓冲获取buffer
		
		while(true)
		{
			buffer.clear();
			int read = inputChannel.read(buffer);
			if(read == -1)
			{
				break;
			}
			
			buffer.flip();
			outputChannel.write(buffer);
		}
		
		inputChannel.close();
		outputChannel.close();
		inputStream.close();
		outputStream.close();
	}
}
