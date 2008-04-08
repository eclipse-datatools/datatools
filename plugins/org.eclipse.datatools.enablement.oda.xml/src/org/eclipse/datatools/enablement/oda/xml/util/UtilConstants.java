/*******************************************************************************
* Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.util;

/**
 * The class server as a collection of Constants.
 */
public final class UtilConstants
{
	private UtilConstants()
	{}
	
	public static final String XPATH_WITH_ATTR_PATTERN = ".*\\Q[@\\E.*\\Q]\\E.*";              //$NON-NLS-1$
	public static final String XPATH_ELEM_WITH_INDEX_REF_PATTERN = ".*\\Q[\\E\\d+\\Q]\\E.*";   //$NON-NLS-1$
	public static final String XPATH_ELEM_WITH_INDEX_WILDCARD = ".*\\Q[*]\\E.*";               //$NON-NLS-1$
	public static final String XPATH_ELEM_INDEX_WILDCARD_PATTERN = "\\Q[*]\\E";                //$NON-NLS-1$
	public static final String XPATH_ELEM_INDEX_PATTERN = "\\Q[\\E\\d+\\Q]\\E";                //$NON-NLS-1$
	public static final String XPATH_DOUBLE_SLASH = "//";                                      //$NON-NLS-1$
	public static final String XPATH_SLASH = "/";                                              //$NON-NLS-1$

	public static final int COLUMN_REFNUMBER_RELATIVE = -1;
}
