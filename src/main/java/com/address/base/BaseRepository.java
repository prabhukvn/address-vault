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

		String value = null;
		try(Jedis jedis = JedisConnection.getConnection()){
			value= jedis.set(entity.getKey(), entity.getJson());
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;

	}

	public String get(String key) {
		String value = null;
		try(Jedis jedis = JedisConnection.getConnection()){
			value= jedis.get(key);
			logger.debug("Value {}", value);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
		

	}

	public Long delString(String key) {
		Long value = null;
		try(Jedis jedis = JedisConnection.getConnection()){
			value= jedis.del(key);
			logger.debug("Deleting key:{}", key);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
		
	}

	public Long delStrings(String... keys) {
		
		
		Long value = null;
		try(Jedis jedis = JedisConnection.getConnection()){
			value= jedis.del(keys);
			logger.debug("Deleting keys:{}", Arrays.asList(keys));
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
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
		
		Long value = null;
		try(Jedis jedis = JedisConnection.getConnection()){
			value= jedis.hset(key, field, data);
			logger.debug("Incoming Request:{} field {} and data {}",key,field,data);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		logger.debug("Number of records deleted {}", value);

	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> getAllFromHSet(String key){
	
		
		Map<String, String> value = null;
		try(Jedis jedis = JedisConnection.getConnection()){
			value= jedis.hgetAll(key);
			logger.debug("Incoming Key {}",key);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		logger.debug("Number of records deleted {}", value);
		
		return value;
	}
	/**
	 * 
	 * @param key
	 * @param field
	 */
	public String getFromHSet(String key, String field){
		
		String value = null;
		try(Jedis jedis = JedisConnection.getConnection()){
			value= jedis.hget(key, field);
			logger.debug("From Hset:{}",value);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
		
	}
	
	public Long delFromHSet(String key) {

		
		Long value = null;
		try(Jedis jedis = JedisConnection.getConnection()){
			value= jedis.del(key);
			logger.debug("Deleting keys:{}",key);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
		
	}

	public Long delFromHSet(String key, String field) {

		Long value = null;
		try(Jedis jedis = JedisConnection.getConnection()){
			value= jedis.hdel(key,field);
			logger.debug("Deleting keys:{} and field {}",key, field);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
		
	}

	public Long setInHSet(String key, String field, String value) {

		Long result = null;
		try(Jedis jedis = JedisConnection.getConnection()){
			result= jedis.hset(key, field, value);
			logger.debug("Deleting keys:{} and field {} value {}",key, field, value);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return result;
	}


}
