package com.nio;

import java.nio.ByteBuffer;

/**
 * @author: 姚轶文
 * @date:2018年8月1日 下午5:39:32
 * @version :
 * 从BUFFER中读取数据，可以直接GET，但是要跟PUT进去的顺序和类型一致
 */
public class NioTest5 {

	public static void main(String[] args) {
		
		ByteBuffer buffer = ByteBuffer.allocate(512);
		
		buffer.putInt(123);
		buffer.putLong(123456789L);
		buffer.putDouble(3.1415926);
		buffer.putChar('你');
		
		buffer.flip();
		
		int i = buffer.getInt();
		long l = buffer.getLong();
		double d = buffer.getDouble();
		char c = buffer.getChar();
	}
}
