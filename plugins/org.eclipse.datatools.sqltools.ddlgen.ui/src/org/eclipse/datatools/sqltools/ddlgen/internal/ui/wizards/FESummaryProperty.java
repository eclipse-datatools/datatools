/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.ddlgen.internal.ui.wizards;

public class FESummaryProperty {

    private String property;
    private String value;

    public FESummaryProperty(String property, String value) {
		this.property = property;
		this.value = value;
    }
    
    /**
     * @return Returns the property.
     */
    public String getPropertyName() {
    	return property;
    }
    /**
     * @param property The property to set.
     */
    public void setPropertyName(String property) {
    	this.property = property;
    }
    /**
     * @return Returns the value.
     */
    public String getValue() {
    	return value;
    }
    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
    	this.value = value;
    }
    

}
