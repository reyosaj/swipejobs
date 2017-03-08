/**
 * 
 */
package com.swipejobs.services.rest;

import org.springframework.http.HttpStatus;

/**
 * Wrapper for result from <code>RestTemplate</code>. Either holds the response
 * body or the error from <code>RestTemplate</code>
 * 
 * @author reyo
 *
 */
public final class RestResult<E> {

	/**
	 * Http Response status code
	 */
	private HttpStatus httpStatus;
	
	/**
	 * Http response error message
	 */
	private String errorMessage;
	
	/**
	 * true if response doesnot contain the expected result.
	 */
	private boolean isError;

	/**
	 * expected result
	 */
	E value;

	public RestResult(E value) {
		this.value = value;
	}

	public RestResult(HttpStatus status, String message) {
		this.httpStatus = status;
		this.errorMessage = message;
		this.isError = true;
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @return the isError
	 */
	public boolean isError() {
		return isError;
	}

	/**
	 * @return the value
	 */
	public E getValue() {
		return value;
	}

}
