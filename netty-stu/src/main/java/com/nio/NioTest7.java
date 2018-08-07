package com.nio;

import java.nio.ByteBuffer;

/**
 * @author: 姚轶文
 * @date:2018年8月1日 下午6:19:54
 * @version :
 * 只读BUFFER的创建。  
 */
public class NioTest7 {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(512);
		
		for(int i=0 ; i<buffer.capacity();i++)
		{
			buffer.put((byte)i);
		}
		
		ByteBuffer readonlyBuffer = buffer.asReadOnlyBuffer(); //通过装载完成的普通buffer获取只读buffer
		
		//......如果对readonlyBuffer进行写入操作，将跑出异常 
	}
}
