/**
 * 
 */
package com.address.controllers.routes;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import com.address.base.BasicRoutes;
import com.address.entities.ProfileEntity;
import com.address.managers.ProfileManager;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * @author prabhu kvn
 *
 */
public class ProfileRoutes extends BasicRoutes {

	public static Logger logger = LogManager.getLogger(ProfileRoutes.class);
	ProfileManager profileManager = new ProfileManager();

	public ProfileRoutes() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param router
	 */
	public ProfileRoutes(Router router) {
		this.router = router;
	}

	@Override
	public void startRoutes() {
		// TODO Auto-generated method stub
		super.startRoutes();

		logger.debug("Starting Profile Routes...");
		if (Objects.nonNull(router)) {

			// add the profile
			router.route("/profile/add").method(HttpMethod.POST).handler(requestHandler -> {

				HttpServerRequest request = requestHandler.request();
				HttpServerResponse response = requestHandler.response();
				response.putHeader(CONTENT_TYPE, APPLICATION_JSON);
				logRequest(request);
				request.bodyHandler(bodyHandler->{

					Buffer jsonInput = bodyHandler.copy();
					logBody(jsonInput);
					String inputString = jsonInput.toString();
					ProfileEntity profileEntity = new ProfileEntity();
					profileEntity = profileEntity.fromJson(inputString);
					String result= profileManager.addProfile(profileEntity);
					if(Strings.isNotEmpty(result)){
						response.end("Profile Added "+result);
					}else {
						response.end("Not Able to add profile "+inputString);
					}
					
					
					
				});

			});

		}
	}

}
