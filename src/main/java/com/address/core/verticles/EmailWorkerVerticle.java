/**
 * 
 */
package com.address.core.verticles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.address.base.BaseVerticle;

import io.vertx.core.Future;

/**
 * @author prabhu kvn
 *
 */
public class EmailWorkerVerticle extends BaseVerticle{

	Logger logger = LogManager.getLogger(EmailWorkerVerticle.class);
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
	
		logger.debug("Starting Email Verticle Asynchronously...");
		vertx.eventBus().consumer("email", handler->{
		String address = handler.address();
		Object message = handler.body();
		logger.debug("Message received from {} and message {}.",address,message);
		handler.reply("Email sending is in progress...");
	
			
			
		});
		
		startFuture.succeeded();
		startFuture.complete();
	
	}
	
	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		super.stop(stopFuture);
		stopFuture.succeeded();
		stopFuture.complete();
	}
}
