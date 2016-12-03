/**
 * 
 */
package com.address;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.address.controllers.BasicController;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;

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

		logger.debug("Event Pool Size: {}", options.getEventLoopPoolSize());
		logger.debug("Worker Thread Pool Size:{}", options.getWorkerPoolSize());
	

		Vertx.clusteredVertx(options, resultHandler -> {
			try {

				int port = 8999;
				if (args != null && args.length > 0) {
					port = Integer.parseInt(args[0]);
				}
				JsonObject config = Vertx.factory.vertx().getOrCreateContext().config();
				config.put("port", port);
				DeploymentOptions dOptions = new DeploymentOptions();
				dOptions.setInstances(1);
				dOptions.setConfig(config);
				dOptions.setHa(true);
				Vertx.factory.vertx().deployVerticle(BasicController.class.getName(), dOptions);

			} catch (Exception e) {
				logger.error("Starting problem...",e);
			}
		});

		logger.info("#########Starting Main Application completed!#############");

	}

}
