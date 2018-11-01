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
public interface IProfileListener1 extends IProfileListener {

	// Note this api will override the one defined in the IProfileListener
	public void profileChanged(IConnectionProfile profile, String oldName,
			String oldDesc, Boolean oldAutoConnect);
}
