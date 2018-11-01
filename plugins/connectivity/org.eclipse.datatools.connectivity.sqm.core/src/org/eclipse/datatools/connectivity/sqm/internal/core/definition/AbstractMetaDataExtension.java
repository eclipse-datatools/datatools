/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.definition;

import org.eclipse.datatools.connectivity.sqm.core.definition.MetaDataExtension;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EClass;

public abstract class AbstractMetaDataExtension implements MetaDataExtension {
	static int MAX_IDENTIFIER = 128;
	public int getMaximumIdentifierLength(SQLObject sqlObject) {
		return MAX_IDENTIFIER;
	}
	
	public EClass getMetaClass(String metaClassName){
		return null;
	}

}
