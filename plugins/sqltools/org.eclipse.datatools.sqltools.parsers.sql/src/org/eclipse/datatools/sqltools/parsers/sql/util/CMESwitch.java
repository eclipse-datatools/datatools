/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.util;

import org.eclipse.emf.ecore.EObject;
/**
 * @author jruggles
 *
 * Generic visitor interface
 */
public interface CMESwitch {
    public boolean visited(EObject theEObject);
	public void setLevel(int p_level);
	public Object doSwitch(EObject theEObject);
}
