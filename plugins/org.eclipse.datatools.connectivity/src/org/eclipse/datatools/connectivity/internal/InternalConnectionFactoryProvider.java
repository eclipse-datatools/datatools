/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IOfflineConnectionFactory;

/**
 * Internal interface which consolidates offline functionality into connection
 * factory provider wrappers for use by the framework
 * 
 * @author rcernich
 * 
 * Created on May 2, 2007
 */
public interface InternalConnectionFactoryProvider extends
		IConnectionFactoryProvider, IOfflineConnectionFactory {

	/**
	 * Convenience method.
	 * 
	 * @return true if the underlying connection factory implementation is an
	 *         IOfflineConnectionFactory
	 */
	boolean supportsWorkOfflineMode();

}
