/*******************************************************************************
 * Copyright (c) 2011 Teradata Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Teradata Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.core;

/**
 * This interface is used to identify a database object. Extends the
 * original ProcIdentifier by including the procedure's specific name.
 * 
 * @author Charles Eutsler
 */
public interface ProcIdentifier2 extends ProcIdentifier
{
	/*
	 * This interface was added to support overloaded routines whose specificName
	 * distinguishes routines with the same name.
	 * See BZ 171718.
	 */
    public static final String PROP_SPECIFIC_NAME = "SPECIFIC_NAME"; //$NON-NLS-1$

    /**
     * Gets the database routine's specific name.
     */
    public String getProcSpecificName();
}
