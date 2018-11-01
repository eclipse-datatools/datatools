
/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.util;

/**
 * 
 */

public interface IXMLPathNode
{
	/**
	 * the name of XML node corresponding with this path node
	 * @return
	 */
	String getName( );
	
	/**
	 * the path string of this path node
	 * @return
	 */
	String getPathString( );
}
