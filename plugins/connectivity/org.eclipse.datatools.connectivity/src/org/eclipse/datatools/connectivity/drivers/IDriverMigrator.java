/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers;


/**
 * Interface used by the driver framework which allows providers to
 * hook into driver loading for migration purposes. Extenders may use this
 * facility to update existing driver definitions to conform with updated driver
 * implementations.
 * 
 * This interface should not be directly implemented.  Instead, adopters should
 * extend {@link DriverMigratorBase}.
 * 
 * This API is provisional.
 * 
 * @author brianf
 * 
 * Created on October 10, 2007
 */
public interface IDriverMigrator {

	/**
	 * This method is invoked by the framework when a connection profile of the
	 * migration extension's sourceProfile type has been loaded. At this point,
	 * only the profile's provider ID has been updated (to the value in
	 * targetProfile).
	 * 
	 * @param profile the connection profile to migrate.
	 */
	public boolean performMigration(DriverInstance driver);

	/**
	 * @return the ID of the new provider; null if the provider has not changed.
	 */
	public String getNewDriverTemplateID();

}
