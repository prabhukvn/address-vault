/**
 * 
 */
package com.address.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;

/**
 * @author prabhu kvn
 *
 */
public class BasicRoutes {
	
	public final Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * Log the Request Object
	 * @param request
	 */
	public void logRequest(HttpServerRequest request) {

		logger.debug("Request URI {}.",request.absoluteURI());
		// for headers
		MultiMap headers = request.headers();
		StringBuilder headerList = new StringBuilder();
		for(String key: headers.names()){
			headerList.append(key+"="+headers.get(key));
			headerList.append("\n");
		}
		logger.debug("Headers:{}\n",headerList);
		
		// form attributes
		MultiMap formAttributes = request.formAttributes();
		StringBuilder attributes = new StringBuilder();
		for(String attributeName: formAttributes.names()){
			attributes.append(attributeName+"="+formAttributes.get(attributeName));
			attributes.append("\n");
		}
		logger.debug("Form Attributes:{}\n",attributes);

		//Form Parameters
		MultiMap params = request.params();
		StringBuilder paramList = new StringBuilder();
		for(String param: params.names()){
			paramList.append(param+"="+params.get(param));
			paramList.append("\n");
		}
		logger.debug("Params:{}\n",paramList);
		
		
	
		
	}

	public void logBody(Buffer incomingJson) {
		// TODO Auto-generated method stub
		logger.debug("Request Body: \n {}",incomingJson);
		
	}

}
