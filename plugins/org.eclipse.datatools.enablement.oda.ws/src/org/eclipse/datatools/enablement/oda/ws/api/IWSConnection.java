
/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
