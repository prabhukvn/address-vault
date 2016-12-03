/**
 * 
 */
package com.address.controllers.routes;

import java.util.Objects;

import com.address.base.BasicRoutes;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;

/**
 * @author prabhu kvn
 *
 */
public class TestScenarios extends BasicRoutes {

	public TestScenarios(Router router, Vertx vertx) {
		this.router = router;
		this.vertx = vertx;

	}

	@Override
	public void startRoutes() {

		logger.debug("Starting Test Scenario routes...");
		if (Objects.nonNull(router)) {

			router.route("/test/ha").method(HttpMethod.GET).handler(requestHandler -> {

				logger.debug("testing high availability scenario..");
				System.out.println(1/0);

			});

		}
	}

}
