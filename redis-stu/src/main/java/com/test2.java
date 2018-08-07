package com;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: 姚轶文
 * @date:2018年7月24日 下午4:25:55
 * @version :
 * 
 */
public class test2 {

	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(100); //最大连接数
		config.setMaxIdle(10); //空闲连接数
		
		JedisPool jedisPool = new JedisPool(config,"192.168.1.131",6379);
		
		Jedis  jedis  = jedisPool.getResource();
		jedis.append("myTest2", "working2");
		String str = jedis.get("myTest2");
		System.out.println(str);
		
		if(jedis!=null)
		{
			jedis.close();
		}
		if(jedisPool!=null)
		{
			jedisPool.close();
		}
	}
}
