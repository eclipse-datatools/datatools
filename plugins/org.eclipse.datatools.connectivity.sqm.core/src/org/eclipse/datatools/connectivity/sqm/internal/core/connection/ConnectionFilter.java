/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

public interface ConnectionFilter extends org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionFilter {
	
	/**
	 * Change the filter expression.
	 * @param predicate - It cannot be null.
	 */
	public void setPredicate(String predicate);

}
