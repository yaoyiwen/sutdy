package com.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: 姚轶文
 * @date:2018年8月1日 上午10:05:14
 * @version :
 * 从input.txt文件中读取内容，写入到output.txt文件中
 */
public class NioTest4 {

	public static void main(String[] args)throws Exception {
		FileInputStream inputStream = new FileInputStream("input.txt");
		FileOutputStream outputStream = new FileOutputStream("output.txt");
		
		FileChannel inputChannel = inputStream.getChannel();
		FileChannel outputChannel = outputStream.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
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
