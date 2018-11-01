/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

/**
 * Profile Change Listener
 * 
 * @author shongxum
 */
public interface IProfileListener {

	/**
	 * The specified profile has been added.
	 * 
	 * @param profile
	 */
	public void profileAdded(IConnectionProfile profile);

	/**
	 * The specified profile has been deleted.
	 * 
	 * @param profile
	 */
	public void profileDeleted(IConnectionProfile profile);

	/**
	 * The specified profile has been modified.  Modification includes
	 * changes to any properties, the name, auto-connect flag, etc.
	 * 
	 * @param profile
	 */
	public void profileChanged(IConnectionProfile profile);
}
