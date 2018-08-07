package com.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author: 姚轶文
 * @date:2018年8月6日 下午3:41:48
 * @version :
 * 
 */
public class NioTest14 {

	public static void main(String[] args) throws Exception {
		
		String inputFile = "NioTest14_in.txt";
		String outputFile = "NioTest14_out.txt";
		
		RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile,"r");
		RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile,"rw");
		
		Long inputLength = new File(inputFile).length();
		
		FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
		FileChannel outputFileChannel = outputRandomAccessFile.getChannel();
		
		MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);
		
		Charset charset = Charset.forName("utf-8");
		CharsetDecoder decoder = charset.newDecoder(); //解码
		CharsetEncoder encoder = charset.newEncoder(); //编码
		
		CharBuffer charBuffer = decoder.decode(inputData);
		ByteBuffer outputData = encoder.encode(charBuffer);
		
		outputFileChannel.write(outputData);
		
		
		inputRandomAccessFile.close();
		outputRandomAccessFile.close();
	}
}
