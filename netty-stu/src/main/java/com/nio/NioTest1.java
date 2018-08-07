package com.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;



/**
 * @author: 姚轶文
 * @date:2018年7月30日 上午10:53:02
 * @version :
 * 
 */
public class NioTest1 {

	public static void main(String[] args) {
		
		IntBuffer buffer = IntBuffer.allocate(10); //设置一个长度为10的缓冲区，内容只能放入INT类型
		
		for(int i=0 ; i<buffer.capacity() ; i++)
		{
			int radomNumber = new SecureRandom().nextInt(20); //生成20以内的随机数
			buffer.put(radomNumber); //往缓冲区写数据
		}
		
		buffer.flip();  //在buffer读写状态切换的时候 必须调用filip方法，此方法会设置该缓冲区的状态
		
		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());  //从缓冲区读数据
		}
	}
}
