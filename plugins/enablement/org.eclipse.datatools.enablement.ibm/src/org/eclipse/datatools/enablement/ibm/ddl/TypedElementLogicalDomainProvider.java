/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.ddl;

import org.eclipse.datatools.modelbase.sql.schema.TypedElement;

public interface TypedElementLogicalDomainProvider {

	public boolean hasDomain(TypedElement typeElement);
	public String getDomainBaseType(TypedElement typeElement);
	public String getCCSID(TypedElement typeElement);
}
