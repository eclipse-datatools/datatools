/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.templates;

/**
 * Wrapper for driver property instances
 * 
 * @author brianf
 *
 */
public class PropertyObject {
	
	private static final String DEFAULT_PROPERTY_VALUE = 
		Messages.getString("PropertyObject.DefaultPropertyValue"); //$NON-NLS-1$
	private static final String DEFAULT_PROPERTY_NAME = 
		Messages.getString("PropertyObject.DefaultPropertyName"); //$NON-NLS-1$
	private static final String DEFAULT_PROPERTY_ID = 
		Messages.getString("PropertyObject.DefaultPropertyID"); //$NON-NLS-1$
	private static final String DEFAULT_PROPERTY_VISIBLE = 
		Messages.getString("PropertyObject.DefaultPropertyVisible"); //$NON-NLS-1$
	private static final String DEFAULT_PROPERTY_REQUIRED = 
		Messages.getString("PropertyObject.DefaultPropertyRequired"); //$NON-NLS-1$

	private String propertyID = null;
	private String propertyName = null;
	private String propertyDescription = null;
	private String propertyValue = null;
	private String propertyVisible = null;
	private String propertyRequired = null;
	private String propertyCategory = null;
	
	/**
	 * Constructor
	 */
	public PropertyObject() {
		initialize();
	}
	
	/**
	 * Initialize to defaults
	 */
	private void initialize() {
		propertyID = DEFAULT_PROPERTY_ID;
		propertyName = DEFAULT_PROPERTY_NAME;
		propertyValue = DEFAULT_PROPERTY_VALUE;
		propertyVisible = DEFAULT_PROPERTY_VISIBLE;
		propertyRequired = DEFAULT_PROPERTY_REQUIRED;
	}

	/**
	 * Getter for propertyID
	 */
	public String getPropertyID() {
		return propertyID;
	}

	/**
	 * Setter for propertyID
	 */
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

	/**
	 * Getter for property name
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Setter for property name
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Getter for property description
	 */
	public String getPropertyDescription() {
		return propertyDescription;
	}

	/**
	 * Setter for property description
	 */
	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	/**
	 * Getter for property value
	 */
	public String getPropertyValue() {
		return propertyValue;
	}

	/**
	 * Setter for property value
	 */
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * Getter for property visible
	 */
	public String getPropertyVisible() {
		return propertyVisible;
	}

	/**
	 * Setter for property visible
	 */
	public void setPropertyVisible(String propertyVisible) {
		this.propertyVisible = propertyVisible;
	}

	/**
	 * Getter for property required
	 */
	public String getPropertyRequired() {
		return propertyRequired;
	}

	/**
	 * Setter for property required
	 */
	public void setPropertyRequired(String propertyRequired) {
		this.propertyRequired = propertyRequired;
	}
	
	/**
	 * Getter for property category
	 */
	public String getPropertyCategory() {
		return propertyCategory;
	}

	/**
	 * Setter for property category
	 */
	public void setPropertyCategory(String propertyCategory) {
		this.propertyCategory = propertyCategory;
	}
}
