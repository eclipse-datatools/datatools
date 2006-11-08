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
package org.eclipse.datatools.connectivity.sqm.core.rte;



public interface ICatalogObjectListener {

	public void notifyChanged(ICatalogObject dmElement, int eventType);
	
	public interface EventTypeEnumeration {
		public final static byte ELEMENT_REFRESH = 0;
		public final static byte ENUMERATION_LENGTH = 1;
	}	
}
