/**
 * 
 */
package com.address.base;

import java.util.Arrays;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.Jedis;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseRepository.
 *
 * @author prabhu kvn
 * @param <T> the generic type
 */
public class BaseRepository<T extends BaseEntity<T>> {

	/** The Constant logger. */
	public static final Logger logger = LogManager.getLogger();
	
	/** Database Index. */
	public int DATABASE_INDEX = 0;
	

	/**
	 * Instantiates a new base repository.
	 */
	public BaseRepository() {
	}
	
	/**
	 * Instantiates a new base repository.
	 *
	 * @param DB_INDEX the db index
	 */
	public BaseRepository(int DB_INDEX) {
		this.DATABASE_INDEX=DB_INDEX;
	}

	/**
	 * $$$$$$$$$$$$$$$$$ String methods.
	 *
	 * @param entity the entity
	 * @return the string
	 */
	/**
	 * 
	 * @param address
	 * @return
	 */
	public String setAsString(T entity) {

		String value = null;
		try(Jedis jedis = getConnection()){
			value= jedis.set(entity.getKey(), entity.toJson());
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;

	}
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	private Jedis getConnection() {
		Jedis jedis = JedisConnection.getConnection();
		logger.debug("Connecting to database index {}",DATABASE_INDEX);
		jedis.select(DATABASE_INDEX);
		return jedis;
	}

	/**
	 * Gets the.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String get(String key) {
		String value = null;
		try(Jedis jedis = getConnection()){
			value= jedis.get(key);
			logger.debug("Value {}", value);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
		

	}

	/**
	 * Del string.
	 *
	 * @param key the key
	 * @return the long
	 */
	public Long delString(String key) {
		Long value = null;
		try(Jedis jedis = getConnection()){
			value= jedis.del(key);
			logger.debug("Deleting key:{}", key);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
		
	}

	/**
	 * Del strings.
	 *
	 * @param keys the keys
	 * @return the long
	 */
	public Long delStrings(String... keys) {
		
		
		Long value = null;
		try(Jedis jedis = getConnection()){
			value= jedis.del(keys);
			logger.debug("Deleting keys:{}", Arrays.asList(keys));
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
	}

	/**
	 * HSetMethods &&&&&&&&&&&&.
	 *
	 * @param key the key
	 * @param field the field
	 * @param data the data
	 */

	/**
	 * Use this method for adding address for first time.
	 * 
	 * @param entity
	 * @return
	 */

	public void setInHset(String key, String field, String data) {
		
		Long value = null;
		try(Jedis jedis = getConnection()){
			value= jedis.hset(key, field, data);
			logger.debug("Incoming Request:{} field {} and data {}",key,field,data);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		logger.debug("Number of records deleted {}", value);

	}
	
	/**
	 * Gets the all from H set.
	 *
	 * @param key the key
	 * @return the all from H set
	 */
	public Map<String, String> getAllFromHSet(String key){
	
		
		Map<String, String> value = null;
		try(Jedis jedis = getConnection()){
			value= jedis.hgetAll(key);
			logger.debug("Incoming Key {}",key);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		logger.debug("Number of records deleted {}", value);
		
		return value;
	}
	
	/**
	 * Gets the from H set.
	 *
	 * @param key the key
	 * @param field the field
	 * @return the from H set
	 */
	public String getFromHSet(String key, String field){
		
		String value = null;
		try(Jedis jedis = getConnection()){
			value= jedis.hget(key, field);
			logger.debug("From Hset:{}",value);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
		
	}
	
	/**
	 * Del from H set.
	 *
	 * @param key the key
	 * @return the long
	 */
	public Long delFromHSet(String key) {

		
		Long value = null;
		try(Jedis jedis = getConnection()){
			value= jedis.del(key);
			logger.debug("Deleting keys:{}",key);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
		
	}

	/**
	 * Del from H set.
	 *
	 * @param key the key
	 * @param field the field
	 * @return the long
	 */
	public Long delFromHSet(String key, String field) {

		Long value = null;
		try(Jedis jedis = getConnection()){
			value= jedis.hdel(key,field);
			logger.debug("Deleting keys:{} and field {}",key, field);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return value;
		
	}

	/**
	 * Sets the in H set.
	 *
	 * @param key the key
	 * @param field the field
	 * @param value the value
	 * @return the long
	 */
	public Long setInHSet(String key, String field, String value) {

		Long result = null;
		try(Jedis jedis = getConnection()){
			result= jedis.hset(key, field, value);
			logger.debug("Deleting keys:{} and field {} value {}",key, field, value);
		}catch (Exception e) {
			logger.error("Error in Redis Connectivity.", e);
		}
		return result;
	}


}
