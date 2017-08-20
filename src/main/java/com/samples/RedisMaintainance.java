/**
 * 
 */
package com.samples;

import redis.clients.jedis.Jedis;

/**
 * @author pkoppu
 *
 */
public class RedisMaintainance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Jedis jedis = RedisConnectionPool.getJedisConnectionPool().getResource();
		// remove all data the database entires.
		jedis.flushAll();

	}

}
