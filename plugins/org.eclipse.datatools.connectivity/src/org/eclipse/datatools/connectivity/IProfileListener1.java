/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
