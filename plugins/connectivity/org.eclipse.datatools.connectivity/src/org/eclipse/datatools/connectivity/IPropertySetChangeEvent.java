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
package org.eclipse.datatools.connectivity;

import java.util.Map;

/**
 * Represents changes made to a property set associated with a connection
 * profile.
 * 
 * @author rcernich
 * 
 * Created on Jun 1, 2006
 */
public interface IPropertySetChangeEvent {

	/**
	 * @return the connection profile on which the changes occurred
	 */
	public IConnectionProfile getConnectionProfile();

	/**
	 * Returns the ID of the property set that was changed. Changes to the base
	 * property set can be identified by
	 * getPropertySetType().equals(getConnectionProfile().getProviderId()).
	 * 
	 * @return the ID of the property set that was changed.
	 */
	public String getPropertySetType();

	/**
	 * Returns a Map object representing the properties that have been changed.
	 * The map is of the form:
	 * key == property ID
	 * value == IChangedProperty
	 * 
	 * @return the properties that were changed.
	 */
	public Map getChangedProperties();

	/**
	 * Returns the property changes for the specified ID; null if no changes
	 * were made.
	 * 
	 * @param id the property ID
	 * 
	 * @return the property changes for the specified ID; null if no changes
	 * were made.
	 */
	public IChangedProperty getChangedProperty(String id);

	/**
	 * Used to present changes made to a particular property to the consumer.
	 */
	public interface IChangedProperty {

		/**
		 * @return the ID of the property that changed
		 */
		public String getID();

		/**
		 * @return the original value of the property; may be null.
		 */
		public String getOldValue();

		/**
		 * @return the new value of the property; may be null.
		 */
		public String getNewValue();
	}
}
