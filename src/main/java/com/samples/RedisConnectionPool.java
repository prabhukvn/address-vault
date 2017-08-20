package com.samples;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectionPool {

	public static JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");

	public static void main(String[] args) {

		RedisConnectionPool obj = new RedisConnectionPool();
		obj.executeUsingPool();
	}

	public void executeUsingPool() {
		try {
			Jedis jedis = pool.getResource();

			System.out.println(jedis.ping());
			pool.returnResourceObject(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.close();
		}
	}

	public static JedisPool getJedisConnectionPool() {
		return pool;
	}

	public void closeConnectionPool() {
		pool.close();
	}
}
