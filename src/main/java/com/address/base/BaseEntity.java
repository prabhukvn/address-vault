/**
 * 
 */
package com.address.base;

import com.google.gson.Gson;

// TODO: Auto-generated Javadoc
/**
 * The Interface BaseEntity.
 *
 * @author prabhu kvn
 * @param <T> the generic type
 */
public interface BaseEntity<T> {

	/**
	 * Gets the key.
	 *
	 * @return key
	 */
	public String getKey();

	/**
	 * To json.
	 *
	 * @return the string
	 */
	public default String toJson() {
		return new Gson().toJson(this);
	}

	/**
	 * From json.
	 *
	 * @param value the value
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public default T fromJson(String value) {

		return (T) new Gson().fromJson(value, this.getClass());
	}

}
