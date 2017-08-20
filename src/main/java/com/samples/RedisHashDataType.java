/**
 * 
 */
package com.samples;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import redis.clients.jedis.DebugParams;
import redis.clients.jedis.Jedis;

/**
 * @author prabhu kvn
 *
 */
public class RedisHashDataType {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis jedis = RedisConnectionPool.getJedisConnectionPool().getResource();
		
		hashingFeatures(jedis);
		RedisConnectionPool.getJedisConnectionPool().returnResource(jedis);

	}
	
	private static void hashingFeatures(Jedis jedis) {
		// TODO Auto-generated method stub
		jedis.hset("hash", "key1", "one");
		jedis.hset("hash", "key2", "two");
		jedis.hset("hash", "3key", "three");
		jedis.hset("hash", "4key", "four");
		String json = new Gson().toJson(new ValueObject("prabhukvn", "prabhukvn@gmail.com"));
		jedis.hset("hash", "json", json);

		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("a1", "v1");
		attributes.put("a2","v2");
		attributes.put("a3", "v3");
		jedis.hmset("hash", attributes);
		
		System.out.println(jedis.hget("hash", "key1"));
		System.out.println(jedis.hgetAll("hash"));
		
		System.out.println(jedis.hmget("hash", "key1","key2"));
		System.out.println("length of hset: "+jedis.hlen("hash"));
	
		

	}


}

class ValueObject {
	String name = "";
	String email = "";

	public ValueObject() {
		// TODO Auto-generated constructor stub
	}

	ValueObject(String name, String email) {
		this.name = name;
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}