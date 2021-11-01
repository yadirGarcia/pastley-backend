package com.pastley.model;

import java.io.Serializable;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
public class StatisticModel<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private T entity;
	private Long count;
	
	///////////////////////////////////////////////////////
	// Builder
	///////////////////////////////////////////////////////
	public StatisticModel() {
	}
	
	public StatisticModel(T entity, Long count) {
		this.entity = entity;
		this.count = count;
	}
	
	///////////////////////////////////////////////////////
	// Getter and Setters
	///////////////////////////////////////////////////////
	public Long getCount() {
		return count;
	}
	
	public void setCount(Long count) {
		this.count = count;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
