/**
 * 
 */
package com.address.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.Jedis;

/**
 * @author prabhu kvn
 *
 */
public class JedisConnection implements BaseConnection<Jedis> {

	public static final Logger logger = LogManager.getLogger(JedisConnection.class);
	/**
	 * 
	 */
	private static Jedis jedisConnection = null;

	private static String hostName = "localhost";
	private static int port = 6379;

	/**
	 * 
	 */
	private JedisConnection() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get the connection using jedis client
	 * 
	 * @return
	 */

	public static Jedis getConnection() {

		if (jedisConnection == null) {
			logger.debug("Creating Redis connection with Host:{} and port:{}", hostName, port);
			jedisConnection = new Jedis(hostName, port);
		}
		return jedisConnection;

	}

	/**
	 * Get the jedis connection using hostname ans port
	 * 
	 * @param hostName
	 * @param port
	 * @return
	 */

	public static Jedis getConnection(String hostName, int port) {

		if (jedisConnection == null) {
			logger.debug("Creating Redis connection with Host:{} and port:{}", hostName, port);
			jedisConnection = new Jedis(hostName, port);
		}
		return jedisConnection;
	}

	/**
	 * Check the connection status of redis server
	 * 
	 * @return
	 */

	public boolean isConnected() {
		boolean status = false;

		if (jedisConnection != null) {
			String pong = jedisConnection.ping();
			if (pong != null) {
				status = true;
			}
		}
		return status;
	}

}
