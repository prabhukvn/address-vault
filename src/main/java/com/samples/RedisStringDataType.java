/**
 * 
 */
package com.samples;

import redis.clients.jedis.Jedis;

/**
 * @author pkoppu
 *
 */
public class RedisStringDataType {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedisStringDataType obj = new RedisStringDataType();
		obj.redidString();

	}
	
	public void redidString(){
		
		Jedis jedis = RedisConnectionPool.getJedisConnectionPool().getResource();
		// String operations
		jedis.set("StringKey", "String value");
		System.out.println(jedis.get("StringKey"));
		//jedis.del("StringKey");
		jedis.setnx("StringKey", "new value");
		System.out.println(jedis.getSet("StringKey", "Another Value"));
		// all corresponding values of a key
		System.out.println(jedis.mget("StringKey"));
		jedis.setex("time-based", 10, "Time Based Value");
		
		// append values to existing string
		jedis.append("time-based", " hehehe");
		System.out.println(jedis.get("time-based"));
		System.out.println(jedis.strlen("time-based"));
		RedisConnectionPool.getJedisConnectionPool().returnResource(jedis);
		
		
	}

}
