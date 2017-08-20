package com.samples;

import redis.clients.jedis.Jedis;

public class RedisClientOne {
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println(jedis.ping());
		
		
	}

}
