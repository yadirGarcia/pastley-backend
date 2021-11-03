package com.pastley.util.exception;

import org.springframework.http.HttpStatus;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
public class PastleyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public PastleyException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	///////////////////////////////////////////////////////
	// Getter and Setetrs
	///////////////////////////////////////////////////////
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
