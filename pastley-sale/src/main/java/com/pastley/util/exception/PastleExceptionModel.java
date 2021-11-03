package com.pastley.util.exception;

import java.io.Serializable;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
public class PastleExceptionModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private String exception;
	private String path;
	private int statu;
	
	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public PastleExceptionModel() {
	}
	
	public PastleExceptionModel(String message, String exception, String path, int statu) {
		this.message = message;
		this.exception = exception;
		this.path = path;
		this.statu = statu;
	}

	///////////////////////////////////////////////////////
	// Getter and Setters
	///////////////////////////////////////////////////////
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
