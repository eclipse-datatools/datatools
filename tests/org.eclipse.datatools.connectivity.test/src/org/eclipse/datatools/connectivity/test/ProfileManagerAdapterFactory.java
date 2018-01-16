/*******************************************************************************
 * Copyright (c) 2004-2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.test;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.datatools.connectivity.ProfileManager;

/**
 * This class helps tests the getAdapter() method of the ProfileManager class.
 * 
 * @author brianf
 *
 */
public class ProfileManagerAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType.equals(String.class)) {
			if (adaptableObject instanceof ProfileManager) {
				ProfileManager pm = (ProfileManager) adaptableObject;
				return pm.toString();
			}
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] {String.class};
	}

}
