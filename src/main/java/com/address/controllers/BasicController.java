/**
 * 
 */
package com.address.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.address.base.BaseVerticle;
import com.address.controllers.routes.AddressRoutes;
import com.address.controllers.routes.ProfileRoutes;
import com.address.controllers.routes.TestScenarios;
import com.address.core.verticles.EmailWorkerVerticle;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * @author prabhu kvn
 *
 */
public class BasicController extends BaseVerticle {

	public static final Logger logger = LogManager.getLogger(BasicController.class);
	private String emailVerticleDeploymentId = null;

	/**
	 * 
	 */
	public BasicController() {
	}

	@Override
	public void init(Vertx vertx, Context context) {
		// Call super class constructor
		super.init(vertx, context);

		// 1. deploy email verticle
		// deployEmailVerticle(vertx);

	}

	/**
	 * @param vertx
	 */
	private void deployEmailVerticle(Vertx vertx) {
		DeploymentOptions dOptions = new DeploymentOptions();
		dOptions.setWorker(false);
		dOptions.setInstances(1);
		vertx.deployVerticle(EmailWorkerVerticle.class.getName(), dOptions, new Handler<AsyncResult<String>>() {

			@Override
			public void handle(AsyncResult<String> arg0) {
				emailVerticleDeploymentId = arg0.result();
				System.out.println("Email Verticle Deployed:" + arg0.result());

			}
		});
	}

	/**
	 * Start the web routing
	 */
	@Override
	public void start() throws Exception {
		HttpServerOptions sOptions = new HttpServerOptions();
		sOptions.setLogActivity(true);
		// sOptions.setSsl(true);
		sOptions.addEnabledSecureTransportProtocol("https");

		HttpServer server = vertx.createHttpServer(sOptions);
		Router router = Router.router(vertx);

		int port = config().getInteger("port");
		// logger.debug("Initializing the server on {}", port);
		// Add Address routes
		AddressRoutes addressRoutes = new AddressRoutes(router, vertx);
		addressRoutes.startRoutes();

		// profile routes
		ProfileRoutes profileRoutes = new ProfileRoutes(router);
		profileRoutes.startRoutes();

		// test scenarios
		TestScenarios testScenarios = new TestScenarios(router, vertx);
		testScenarios.startRoutes();

		server.requestHandler(router::accept).listen(port, messae -> {
			// logger.debug("Server started on {}...", port);
			List<Route> listOfRoutes = router.getRoutes();
			// logger.debug("##############Total Routes in the system ###############");
			for (Route route : listOfRoutes) {
				// logger.debug(route.getPath());
			}
			// logger.debug("Event bus details ....");
			EventBus eventBus = vertx.eventBus();
			// event bus interceptor
			/*
			 * eventBus.addInterceptor(interceptor -> {
			 * logger.debug("Message address:{} and message {}",
			 * interceptor.message().address(), interceptor.message().body());
			 * 
			 * });
			 */			vertx.isMetricsEnabled();
		});

	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		// 1. undeploy email verticle
		vertx.undeploy(emailVerticleDeploymentId);
	}
}
