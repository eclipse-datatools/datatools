/*******************************************************************************
 * Copyright (c) 2004-2005, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 *          IBM Corporation - fix for defect 222818
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
	
	/**
	 * @return the priority of the provided connection factory
	 */
	String getPriority();

}
