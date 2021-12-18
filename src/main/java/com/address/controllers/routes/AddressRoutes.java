/**
 * 
 */
package com.address.controllers.routes;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import com.address.base.BasicRoutes;
import com.address.entities.AddressEntity;
import com.address.entities.AddressEntity.AddressData;
import com.address.managers.AddressManager;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * @author prabhu kvn
 *
 */
public class AddressRoutes extends BasicRoutes {

	private static final String ADDRESS_NAME_FIELD = "addressName";

	private static final String EMAIL_FIELD = "email";
	
	private static final AtomicInteger counter = new AtomicInteger();
	

	
	public static final Logger logger = LogManager.getLogger(AddressRoutes.class);

	AddressManager addressManger = new AddressManager();

	
	/**
	 * Default constructor
	 */
	public AddressRoutes() {

	}

	public AddressRoutes(Router router, Vertx vertx) {
		this.router = router;
		this.vertx=vertx;
	}

	/**
	 * Start the routes of address
	 */
	public void startRoutes() {

		logger.debug("Starting Address Routes...");
		if (router != null) {
			/**
			 * Get All addresses for a given profile
			 */
			
			router.route("/address/getall/:email").method(HttpMethod.GET).handler(requestContext -> {
				HttpServerResponse response = requestContext.response();
				response.putHeader(CONTENT_TYPE, APPLICATION_JSON);
				HttpServerRequest request = requestContext.request();
				super.logRequest(request);
				String email = request.getParam(EMAIL_FIELD);
				/*
				 * try { Thread.sleep(3000); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 */
				logger.debug("Get the addresses for email {}", email);
				String value = addressManger.getAllAddressesForEmail(email);
				if (Objects.isNull(value)) {
					response.end("Address not found for " + email);
				}
				logger.debug("Response \n {}", value);
				response.end(value);
			});

			/**
			 * Add an address to existing profile
			 */
			router.route("/address/add/:email").method(HttpMethod.POST).handler(requestContext -> {
				HttpServerResponse response = requestContext.response();
				HttpServerRequest request = requestContext.request();
				response.putHeader(CONTENT_TYPE, APPLICATION_JSON);
				logRequest(request);
				String email = request.getParam(EMAIL_FIELD);
				request.bodyHandler(bodyHandler -> {
					AddressEntity address = new AddressEntity();
					address.setEmail(email);
					AddressData data = address.new AddressData();
					Buffer incomingJson = bodyHandler.copy();
					super.logBody(incomingJson);
					String bodyJson = incomingJson.toString();
					logger.debug("Incoming address {}", bodyJson);
					data = data.toJson(bodyJson);
					address.getAddresses().add(data);

					if (addressManger.addAddress(address,vertx)) {

						response.end("Address added to Account:" + email);
					} else {
						response.end("We are not able to add the address to :" + email);

					}
				});

			});
			/**
			 * Get An Address using address name from a profile
			 */
			router.route("/address/get/:email/:addressName").handler(requestContext -> {
				HttpServerResponse response = requestContext.response();
				HttpServerRequest request = requestContext.request();
				response.putHeader(CONTENT_TYPE, APPLICATION_JSON);
				super.logRequest(request);
				String email = request.getParam(EMAIL_FIELD);
				String addressName = request.getParam(ADDRESS_NAME_FIELD);
				AddressEntity address = addressManger.getAddress(email, addressName);
				if (Objects.isNull(address)) {
					response.end("Address Not found for " + email + " and " + addressName);
				} else {
					response.end(address.toJson());
				}

			});

			/**
			 * Add All addresses to a profile
			 */
			router.route("/address/add").method(HttpMethod.POST).handler(requestHandler -> {
				HttpServerRequest request = requestHandler.request();
				HttpServerResponse response = requestHandler.response();
				response.putHeader(CONTENT_TYPE, APPLICATION_JSON);
				super.logRequest(request);
				counter.incrementAndGet();
				request.bodyHandler(bodyHandler -> {
					Buffer buffer = bodyHandler.copy();
					String bodyJson = buffer.toString();
				//	logger.debug("Incoming Address Data:{}", bodyJson);
					AddressEntity address = new AddressEntity();

					address = address.fromJson(bodyJson);
					boolean status = this.addressManger.addAddress(address,vertx);
					if (status) {
						response.end("Address Added Suuccessfully.");
					} else {
						response.end("Failed to add Address.");
					}
				});
			});

			/**
			 * Delete addresses for a profile
			 */
			router.route("/address/deleteall/:email").method(HttpMethod.DELETE).handler(requestHandler -> {
				HttpServerRequest request = requestHandler.request();
				HttpServerResponse response = requestHandler.response();
				response.putHeader(CONTENT_TYPE, APPLICATION_JSON);
				super.logRequest(request);
				String email = request.getParam(EMAIL_FIELD);
				if (Strings.isNotEmpty(email)) {
					Long noOfAddDeleted = addressManger.deleteAddress(email);
					response.end("Deleted " + noOfAddDeleted + " from " + email);
				} else {
					response.end("Not able to delete the address." + email);
				}
			});

			/**
			 * Delete a particular address from email
			 */
			router.route("/address/delete/:email/:addressName").method(HttpMethod.DELETE)
					.handler(requestHandler -> {

						HttpServerRequest request = requestHandler.request();
						HttpServerResponse response = requestHandler.response();
						response.putHeader(CONTENT_TYPE, APPLICATION_JSON);
						super.logRequest(request);
						String email = request.getParam(EMAIL_FIELD);
						String addressName = request.getParam(ADDRESS_NAME_FIELD);
						if (Strings.isNotEmpty(email) && Strings.isNotEmpty(addressName)) {
							Long deletedNos = this.addressManger.deleteAddress(email, addressName);
							response.end(deletedNos + " address(es) deleted from " + email);
						} else {
							response.end("Not able to delete the address.");
						}

					});
		}

	}

	public void startRoutes(Router router) {
		this.router = router;
		startRoutes();
	}

	/**
	 * 
	 * @return router
	 */
	public Router getRouter() {
		return router;
	}

	/**
	 * 
	 * @param router
	 */
	public void setRouter(Router router) {
		this.router = router;
	}

}
