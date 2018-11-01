/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;

public class PropertySetChangeEvent implements
		IPropertySetChangeEvent {

	private IConnectionProfile mConnectionProfile;
	private String mPropertiesType;
	private Map mChangedProperties;

	private PropertySetChangeEvent(IConnectionProfile profile,
										String propertiesType) {
		super();
		mConnectionProfile = profile;
		mPropertiesType = propertiesType;
	}

	public PropertySetChangeEvent(IConnectionProfile profile,
										String propertiesType, String property,
										String oldValue, String newValue) {
		this(profile, propertiesType);
		mChangedProperties = new HashMap(1);
		mChangedProperties.put(property, new ChangedProperty(property,
                oldValue, newValue));
	}

	public PropertySetChangeEvent(IConnectionProfile profile,
										String propertiesType,
										Properties oldProperties,
										Properties newProperties) {
		this(profile, propertiesType);

		Set keys = new HashSet(newProperties.keySet());
		keys.addAll(oldProperties.keySet());
		mChangedProperties = new HashMap(keys.size());
		for (Iterator it = keys.iterator(); it.hasNext();) {
			String key = (String) it.next();
			String oldValue = oldProperties.getProperty(key);
			String newValue = newProperties.getProperty(key);
			if ((oldValue == null && newValue != null)
					|| (oldValue != null && !oldValue.equals(newValue))) {
				mChangedProperties.put(key, new ChangedProperty(key, oldValue,
						newValue));
			}
		}
		mChangedProperties = Collections.unmodifiableMap(mChangedProperties);
	}

	public Map getChangedProperties() {
		return mChangedProperties;
	}

	public IChangedProperty getChangedProperty(String key) {
		if (mChangedProperties.containsKey(key)) {
			return (IChangedProperty) mChangedProperties.get(key);
		}
		return null;
	}

	public IConnectionProfile getConnectionProfile() {
		return mConnectionProfile;
	}

	public String getPropertySetType() {
		return mPropertiesType;
	}

	private static class ChangedProperty implements IChangedProperty {

		private String mKey;
		private String mNewValue;
		private String mOldValue;

		private ChangedProperty(String key, String oldValue, String newValue) {
			mKey = key;
			mNewValue = newValue;
			mOldValue = oldValue;
		}

		public String getNewValue() {
			return mNewValue;
		}

		public String getOldValue() {
			return mOldValue;
		}

		public String getID() {
			return mKey;
		}

	}

}
