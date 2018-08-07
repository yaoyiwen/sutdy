package com.nio;

import java.nio.ByteBuffer;

/**
 * @author: 姚轶文
 * @date:2018年8月1日 下午5:52:30
 * @version :
 * 实验得出结论，buffer 和 sliceBuffer共享内存区域，当sliceBuffer里的数据发生变化，等同于buffer也发生改变
 */
public class NioTest6 {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);
		
		for(int i=0 ; i<buffer.capacity() ; i++)
		{
			buffer.put((byte)i);
		}
		
		buffer.position(2);
		buffer.limit(6);
		
		ByteBuffer sliceBuffer = buffer.slice(); //通过设置buffer的position和limit值，获取一个新的buffer
		for(int i=0 ; i<sliceBuffer.capacity() ; i++)
		{
			byte b = sliceBuffer.get(i);
			b *=2;
			sliceBuffer.put(i, b);
		}
		
		buffer.position(0);
		buffer.limit(buffer.capacity());
		
		while(buffer.hasRemaining())
		{
			System.out.println(buffer.get());
		}
	}
}
