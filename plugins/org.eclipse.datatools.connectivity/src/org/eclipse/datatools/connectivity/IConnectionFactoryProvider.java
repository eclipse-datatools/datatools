/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

/**
 * This interface is used to access functionality provided by a connection
 * factory extension.
 * 
 * @author rcernich
 * 
 * Created on Jan 5, 2004
 */
public interface IConnectionFactoryProvider extends IConnectionFactory {

	/**
	 * @return the id of the provided connection factory
	 */
	String getId();

	/**
	 * @return the name of the provided connection factory
	 */
	String getName();

	/**
	 * @return the class specified by the extension point.
	 */
	Class getConnectionFactoryClass();

}
