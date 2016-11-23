/**
 * 
 */
package com.address;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.address.controllers.BasicController;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @author prabhu kvn
 *
 */
public class StartApp {

	public static final Logger logger = LogManager.getLogger(StartApp.class);

	/**
	 * Empty Constructor
	 */
	public StartApp() {
	}

	public static void main(String[] args) {

		logger.info("#########Starting Main Application...#############");
		VertxOptions options = new VertxOptions();
		options.setClustered(true);
		
		logger.debug("Event Pool Size: {}",options.getEventLoopPoolSize());
		logger.debug("Worker Thread Pool Size:{}",options.getWorkerPoolSize());
		/*Vertx vertx = Vertx.vertx(options);
		vertx.deployVerticle(BasicController.class.getName());
	*/
		
	
		Vertx.clusteredVertx(options, resultHandler->{
			try {
				Vertx.factory.vertx().deployVerticle(BasicController.class.getName());
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		logger.info("#########Starting Main Application completed!#############");

	}

}
