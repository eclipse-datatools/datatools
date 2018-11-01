/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.definition;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class DefaultDataModelElementFactory	implements DataModelElementFactory {
	public static DataModelElementFactory INSTANCE = new DefaultDataModelElementFactory();
	 
	public EObject create(EClass metaclass) {
		return metaclass.getEPackage().getEFactoryInstance().create(metaclass);
	}
	
	protected DefaultDataModelElementFactory() {
	}
}
