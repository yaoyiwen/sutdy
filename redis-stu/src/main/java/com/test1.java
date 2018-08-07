package com;

import redis.clients.jedis.Jedis;

/**
 * @author: 姚轶文
 * @date:2018年7月24日 下午2:58:59
 * @version :
 * 
 */
public class test1 {

	public static void main(String[] args) {
		 Jedis jedis = new Jedis("192.168.1.131",6379);
		 
		 System.out.println(jedis.ping());
		 
		 
		 jedis.append("myTest", "working");
		 String str = jedis.get("myTest");
		 System.out.println(str);
	}
}
