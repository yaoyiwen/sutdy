package com.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: 姚轶文
 * @date:2018年8月3日 上午11:15:49
 * @version :
 * NIO内存映射文件
 */
public class NioTest9 {

	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt", "rw"); //rw表示可读可写
		FileChannel fileChannel = randomAccessFile.getChannel(); //获取channel
		MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5); //设置可读写，缓冲区从0-4,5个字节。
		
		mappedByteBuffer.put(0,(byte)'a');
		mappedByteBuffer.put(1,(byte)'b');
		mappedByteBuffer.put(2,(byte)'c');
		mappedByteBuffer.put(3,(byte)'d');
		mappedByteBuffer.put(4,(byte)'e');

		
		randomAccessFile.close();
	}
}
