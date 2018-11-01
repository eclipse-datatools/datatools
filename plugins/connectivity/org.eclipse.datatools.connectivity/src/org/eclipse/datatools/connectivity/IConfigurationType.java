/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

/**
 * This interface is used for accessing functionality specific to configuration
 * types.
 * 
 * @deprecated
 * @author rcernich
 * 
 * Created on Jan 15, 2004
 */
public interface IConfigurationType {

	/**
	 * @return the id of this configuration type
	 */
	String getId();

	/**
	 * @return the name of this configuration type
	 */
	String getName();

	/**
	 * @param obj
	 * 
	 * @return getId().equals(obj.getId())
	 */
	boolean equals(Object obj);

	/**
	 * @return getId().hashCode()
	 */
	int hashCode();

}
