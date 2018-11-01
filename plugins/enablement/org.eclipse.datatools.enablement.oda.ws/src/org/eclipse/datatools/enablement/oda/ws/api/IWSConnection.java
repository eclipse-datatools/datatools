
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
package org.eclipse.datatools.enablement.oda.ws.api;

import java.util.Map;

/**
 * 
 */

public interface IWSConnection
{
	/**
	 * 
	 * @param queryText
	 * @param parameterValues
	 * @param queryProperties
	 * @return
	 */
	public Object executeQuery( String queryText, Map parameterValues,
			Map queryProperties );

	/**
	 * 
	 */
	public void close( );


}
