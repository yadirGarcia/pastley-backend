package com.pastley.util;

import java.io.Serializable;

public class PastleyVariable implements Serializable {

	private static final long serialVersionUID = 1L;

	///////////////////////////////////////////////////////
	// Date
	///////////////////////////////////////////////////////
	public static final String PASTLEY_DATE_ZONA_ID = "America/Bogota";
	public static final String PASTLEY_DATE_FORMAT_DATE = "yyyy/MM/dd";
	public static final String PASTLEY_DATE_FORMAT_HOUR = "HH:mm:ss";
	public static final String PASTLEY_DATE_TIME_FORMAT = PASTLEY_DATE_FORMAT_DATE + " " + PASTLEY_DATE_FORMAT_HOUR;
	
	
	public static final Long PASTLEY_USER_CUSTOMER_ID = 1L;
}
