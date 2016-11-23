/**
 * 
 */
package com.address.base;

/**
 * @author prabhu kvn
 *
 */
public interface BaseEntity {
	
	/**
	 * 
	 * @return key
	 */
	public String getKey();
	
	public String getJson();
	
	public BaseEntity toJson(String value);

}
