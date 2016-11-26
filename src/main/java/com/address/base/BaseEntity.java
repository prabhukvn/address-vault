/**
 * 
 */
package com.address.base;

import com.google.gson.Gson;

/**
 * @author prabhu kvn
 *
 */
public interface BaseEntity<T> {
	
	/**
	 * 
	 * @return key
	 */
	public String getKey();

	
	public default String toJson() {
		// TODO Auto-generated method stub
		return new Gson().toJson(this);
	}
	
	public default T fromJson(String value){
		
		return new Gson().fromJson(value, getClass());
	}

}
