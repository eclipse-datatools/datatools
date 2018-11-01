/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder;

/**
 * This exception is thrown when parse errors are encountered when
 * passing some SQL to the SQLBuilder as input.
 * 
 * @author Jeremy Lindop
 */
public class ParseException  extends Exception {

    /**
	 * Generated serialVersionUID 
	 */
	private static final long serialVersionUID = 4827573597693069441L;

	public ParseException(String message) 
    {
        super(message);
    }

}
