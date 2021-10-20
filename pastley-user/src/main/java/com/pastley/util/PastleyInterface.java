package com.pastley.util;

import java.util.List;

/**
 * @project Pastley-User.
 * @author Leyner Jose Ortega Arias.
 * @Github https://github.com/leynerjoseoa.
 * @contributors soleimygomez, serbuitrago, jhonatanbeltran.
 * @version 1.0.0.
 */
public interface PastleyInterface<T, O> {
	
	public O findById(T id);
	public List<O> findAll();
	public O save(O entity);
	public boolean delete(T id);
}
