package com.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;



/**
 * @author: 姚轶文
 * @date:2018年7月30日 上午11:37:52
 * @version :
 * 读文件
 */
public class NioTest2 {

	public static void main(String[] args) throws Exception {
	
		FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
		FileChannel fileChannel = fileInputStream.getChannel();
		
		ByteBuffer buffer =  ByteBuffer.allocate(512); 
		fileChannel.read(buffer);
		
		buffer.flip();
		
		while (buffer.remaining()>0) {
			
			byte b = buffer.get();
			System.out.println((char)b);
		}
		
		fileInputStream.close();
		fileChannel.close();
	}
}
