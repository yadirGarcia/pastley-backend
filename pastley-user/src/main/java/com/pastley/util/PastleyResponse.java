package com.pastley.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
public class PastleyResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> map;
	private HttpStatus status;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public PastleyResponse() {
		this(null, null, null);
	}
	
	public PastleyResponse(String type, Object object) {
		this(type, object, null);
	}

	public PastleyResponse(String type, Object object, HttpStatus status) {
		this.map = new HashMap<>();
		this.status = status;
		this.add(type, object, status);
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	public Map<String, Object> add(String type, Object object) {
		if (type != null && object != null) {
			this.map.put(type, object);
		}
		return this.map;
	}
	
	public Map<String, Object> add(String type, Object object, HttpStatus status) {
		if (type != null && object != null) {
			this.add(type, object);
		}
		if(status != null) {
			this.status = status;
			this.add("http", this.status.value());
		}
		return this.map;
	}

	///////////////////////////////////////////////////////
	// Getter and Setters
	///////////////////////////////////////////////////////
	public Map<String, Object> getMap() {
		return map;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
