package com.pastley.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PastleyDate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ZoneId zone;
	private DateTimeFormatter format;
	
	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public PastleyDate() {
		initZoneId(null);
		initFormat(null);
	}
	
	///////////////////////////////////////////////////////
	// Init
	///////////////////////////////////////////////////////
	/**
	 * Method that initializes the zone.
	 * 
	 * @param zone - Represents the time zone.
	 */
	public void initZoneId(String zone) {
		this.zone = ZoneId.of((PastleyValidate.isChain(zone)) ? zone : PastleyVariable.PASTLEY_DATE_ZONA_ID);
	}

	/**
	 * Method that allows you to format the dates.
	 * 
	 * @param format - Represents the format to use.
	 */
	public void initFormat(String format) {
		this.format = DateTimeFormatter.ofPattern((PastleyValidate.isChain(format)) ? format : PastleyVariable.PASTLEY_DATE_TIME_FORMAT);
	}
	
	///////////////////////////////////////////////////////
	// Method - Format
	///////////////////////////////////////////////////////
	public String toFormatDateTime(Date date, String format) {
		if(!PastleyValidate.isChain(format)) {
			format = PastleyVariable.PASTLEY_DATE_TIME_FORMAT;
		}
		DateFormat f = new SimpleDateFormat(format);
		return f.format(date);
	}
	
	///////////////////////////////////////////////////////
	// Getter and Setter
	///////////////////////////////////////////////////////
	public ZoneId getZone() {
		return zone;
	}

	public void setZone(ZoneId zone) {
		this.zone = zone;
	}

	public DateTimeFormatter getFormat() {
		return format;
	}

	public void setFormat(DateTimeFormatter format) {
		this.format = format;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
