/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Implementation class of the property set
 * 
 * @author brianf
 */
public class PropertySetImpl implements IPropertySet {

	// Properties for the property set
	private Map mPropertiesMap;

	// Name for the property set
	private String mName;

	// ID for the property set
	private String mID;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param id
	 */
	public PropertySetImpl(String name, String id) {
		this.mName = name;
		this.mID = id;
		this.mPropertiesMap = new HashMap();
	}

	/**
	 * Returns the name of the property set
	 */
	public String getName() {
		return this.mName;
	}

	/**
	 * Sets the name of the property set
	 */
	public void setName(String newName) {
		this.mName = newName;
	}

	/**
	 * Returns the id of the property set
	 */
	public String getID() {
		return this.mID;
	}

	/**
	 * Sets the id of the property set
	 */
	public void setID(String id) {
		// reset the key for the base properties if we change
		// the id
		if (getProperties(this.mID) != null) {
			Properties temp = getProperties(this.mID);
			this.mPropertiesMap.remove(getProperties(this.mID));
			setProperties(id, temp);
		}
		this.mID = id;
	}

	/**
	 * Returns the base properties of the property set
	 */
	public Properties getBaseProperties() {
		return getProperties(this.mID);
	}

	/**
	 * Sets the base properties of the property set
	 */
	public void setBaseProperties(Properties props) {
		setProperties(this.mID, props);
	}

	/**
	 * Gets general properties of the property set
	 */
	public Properties getProperties(String type) {
		return (Properties) this.mPropertiesMap.get(type);
	}

	/**
	 * Sets general properties of the property set
	 */
	public void setProperties(String type, Properties props) {
		this.mPropertiesMap.put(type, props);
	}

	/**
	 * Clones the property set and returns the clone object.
	 */
	public Object clone() {
		Properties cloneProps = (Properties) getBaseProperties().clone();

		PropertySetImpl cloned = new PropertySetImpl(this.mName, this.mID);
		cloned.setBaseProperties(cloneProps);

		return cloned;
	}

	public boolean equals(Object obj) {
		if (obj instanceof PropertySetImpl) {
			PropertySetImpl compare = (PropertySetImpl) obj;
			return this.mID.equals(compare.getID());
		}
		return super.equals(obj);
	}

	public int hashCode() {
		if (this.mID != null)
			return this.mID.hashCode();
		return super.hashCode();
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ID: " + mID + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append("Name: " + mName + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		Properties props = getBaseProperties();
		Iterator iter = props.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			buffer.append("Property name: " + (String)entry.getKey() + ", "); //$NON-NLS-1$ //$NON-NLS-2$
			buffer.append("Property value: " + (String) entry.getValue() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return buffer.toString();
	}

}
