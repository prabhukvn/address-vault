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
	
	@SuppressWarnings("unchecked")
	public default T fromJson(String value){
		
		return (T)new Gson().fromJson(value, this.getClass());
	}

}
