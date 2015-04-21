/*
 * 
 */
package com.excilys.webservice.impl;

import org.glassfish.jersey.server.ResourceConfig;

// TODO: Auto-generated Javadoc

/**
 * The Class ComputerDatabaseApplication.
 */
public class ComputerDatabaseApplication extends ResourceConfig {
	/**
	 * Register JAX-RS application components.
	 */
	public ComputerDatabaseApplication() {
		packages("com.excilys.webservice.impl");
	}
}