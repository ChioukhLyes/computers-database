package com.excilys.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class LocalDateAdapter.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public LocalDate unmarshal(String dateString) throws Exception {
		return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public String marshal(LocalDate localDate) throws Exception {
		return DateTimeFormatter.ISO_DATE.format(localDate);
	}
}