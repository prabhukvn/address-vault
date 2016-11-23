/**
 * 
 */
package com.address.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.address.controllers.routes.AddressRoutes;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * @author prabhu kvn
 *
 */
public class BasicController extends AbstractVerticle {

	public static final Logger logger = LogManager.getLogger(BasicController.class);

	/**
	 * 
	 */
	public BasicController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Start the web routing
	 */
	@Override
	public void start() throws Exception {
		
		logger.debug("Initializing the server on 8999");
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);
		
		// Add Address routes
		AddressRoutes addressRoutes = new AddressRoutes(router);
		addressRoutes.startRoutes();
		
		
		server.requestHandler(router::accept).listen(8999, messae->{
			logger.debug("Server started on 8999...");
			List<Route> listOfRoutes = router.getRoutes(); 
			logger.debug("##############Total Routes in the system ###############");
			for(Route route: listOfRoutes){
				logger.debug(route.getPath());
			}
		});
			
	}
}
