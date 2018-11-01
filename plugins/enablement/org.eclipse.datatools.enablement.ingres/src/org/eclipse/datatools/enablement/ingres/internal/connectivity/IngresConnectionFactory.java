/*******************************************************************************
 * Copyright (c) 2006, 2007, 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.connectivity;

import java.io.File;
import java.io.IOException;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionProfile;

/**
 * Ingres connection factory implementation.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresConnectionFactory implements IConnectionFactory {

	public static final String INGRES_TRACE_LOG = "ingres.jdbc.trace.log";

	public static final String INGRES_TRACE_DRV = "ingres.jdbc.trace.drv";

	/**
	 * This method creates a connection to an Ingres DBMS.
	 * 
	 * Driver logging is activated. The log file is retrieved from the system
	 * property "ingres.jdbc.trace.log". If no filename is found, a new
	 * temporary log file is used. The following logging parameter is set:
	 * 
	 * ingres.jdbc.trace.drv = 1 (if not passed as system property)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	public IConnection createConnection(final IConnectionProfile profile) {
		if (System.getProperty(INGRES_TRACE_LOG) == null) {
			try {
				final File log = File.createTempFile("ingres_jdbc_trace_",
						".log");
				log.deleteOnExit();
				System.setProperty(INGRES_TRACE_LOG, log.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (System.getProperty(INGRES_TRACE_DRV) == null) {
			System.setProperty(INGRES_TRACE_DRV, "1");
		}

		final IngresJDBCConnection connection = new IngresJDBCConnection(profile,
				getClass());
		connection.open();
		return connection;
	}

	/**
	 * This method creates a connection to an Ingres DBMS.
	 * 
	 * Driver logging is activated. The log file is retrieved from the system
	 * property "ingres.jdbc.trace.log". If no filename is found, a new
	 * temporary log file is used. The following logging parameter is set:
	 * 
	 * ingres.jdbc.trace.drv = 1 (if not passed as system property)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile,
	 *      java.lang.String, java.lang.String)
	 */
	public IConnection createConnection(final IConnectionProfile profile,
			final String uid, String pwd) {
		return createConnection(profile);
	}

}
