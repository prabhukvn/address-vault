/**
 * 
 */
package com.address;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.address.controllers.BasicController;
import com.address.core.verticles.EmailWorkerVerticle;
import com.hazelcast.config.Config;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

// TODO: Auto-generated Javadoc
/**
 * The Class StartApp.
 *
 * @author prabhu kvn
 */
public class StartApp {

	/** The Constant logger. */
	public static final Logger logger = LogManager.getLogger(StartApp.class);

	/**
	 * Empty Constructor.
	 */
	public StartApp() {
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)throws Exception {

		logger.info("#########Starting Main Application...#############");
		ClassLoader classLoader = StartApp.class.getClassLoader();
        URL resource = classLoader.getResource("hazelcast.xml");
		File f = new File(resource.toURI());
		Config fconfig = new Config().setConfigurationFile(f);
		fconfig.getNetworkConfig().setPublicAddress("192.168.0.108");
		fconfig.getNetworkConfig().getJoin().getMulticastConfig().isEnabled();
		fconfig.getNetworkConfig().getJoin().getMulticastConfig().setMulticastGroup("224.2.2.3");
		fconfig.getNetworkConfig().getJoin().getMulticastConfig().setMulticastPort(54327);
		ClusterManager clusterManager = new HazelcastClusterManager(fconfig);
		
		VertxOptions options = new VertxOptions();
		options.setClustered(true);
		
		options.setClusterManager(clusterManager);
		

		logger.debug("Event Pool Size: {}", options.getEventLoopPoolSize());
		logger.debug("Worker Thread Pool Size:{}", options.getWorkerPoolSize());
	

		Vertx.clusteredVertx(options, cluster -> {
			try {

				int port = 8999;
				if (args != null && args.length > 0) {
					port = Integer.parseInt(args[0]);
				}
				Vertx vertx = cluster.result();
				JsonObject config = vertx.getOrCreateContext().config();
				config.put("port", port);
				DeploymentOptions dOptions = new DeploymentOptions();
				dOptions.setInstances(8);
				dOptions.setConfig(config);
				vertx.deployVerticle(BasicController.class.getName(), dOptions);
				vertx.deployVerticle(EmailWorkerVerticle.class.getName(), dOptions);

			} catch (Exception e) {
				logger.error("Starting problem...",e);
			}
		});

		logger.info("#########Starting Main Application completed!#############");

	}

}
