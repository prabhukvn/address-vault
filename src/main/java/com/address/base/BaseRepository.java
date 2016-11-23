/**
 * 
 */
package com.address.base;

import java.util.Arrays;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.Jedis;

/**
 * @author prabhu kvn
 *
 */
public class BaseRepository<T extends BaseEntity> {

	public static final Logger logger = LogManager.getLogger();

	Jedis jedis = JedisConnection.getConnection();

	/**
	 * 
	 */
	public BaseRepository() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * $$$$$$$$$$$$$$$$$ String methods
	 */
	/**
	 * 
	 * @param address
	 * @return
	 */
	public String setAsString(T entity) {

		return jedis.set(entity.getKey(), entity.getJson());

	}

	public String get(String key) {
		String value = jedis.get(key);
		logger.debug("Value {}", value);
		return value;

	}

	public Long delString(String key) {
		logger.debug("Deleting key:{}", key);
		return jedis.del(key);
	}

	public Long delStrings(String... keys) {
		logger.debug("Deleting keys:{}", Arrays.asList(keys));

		return jedis.del(keys);
	}

	/**
	 * HSetMethods &&&&&&&&&&&&
	 */

	/**
	 * Use this method for adding address for first time.
	 * 
	 * @param entity
	 * @return
	 */

	public void setInHset(String key, String field, String data) {

		logger.debug("Incoming Request:{} field {} and data {}",key,field,data);
		this.jedis.hset(key, field, data);

	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> getAllFromHSet(String key){
		logger.debug("Incoming Key {}",key);
		return this.jedis.hgetAll(key);
	}
	/**
	 * 
	 * @param key
	 * @param field
	 */
	public String getFromHSet(String key, String field){
		String value = this.jedis.hget(key, field);
		logger.debug("From Hset:{}",value);
		return value;
	}
	
	public Long delFromHSet(String key) {
		// TODO Auto-generated method stub
		return this.jedis.del(key);
	}

	public Long delFromHSet(String key, String field) {
		// TODO Auto-generated method stub
		return this.jedis.hdel(key,field);
		
	}

	public Long setInHSet(String key, String field, String value) {
		// TODO Auto-generated method stub
		return this.jedis.hset(key, field, value);
	}


}
