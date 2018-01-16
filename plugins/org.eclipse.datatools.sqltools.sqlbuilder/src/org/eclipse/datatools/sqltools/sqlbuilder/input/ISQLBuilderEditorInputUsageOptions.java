/**************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 **************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.input;

/**
 * This interface defines the usage options of ISQLBuilderEditorInput.
 * An option may be specified to ignore a specific input info even if exists.
 */

public interface ISQLBuilderEditorInputUsageOptions
{
	/**
     * Indicates whether to restore/save the window state information.
     */
	public boolean useWindowState();

	/**
     * Specifies whether to restore/save the window state information.
	 * @param useState true to use the input's window state if exists; 
	 *                 false to ignore the window state info even if it exists
	 */
	public void setUseWindowState( boolean useState );
	
	/**
	 * Encodes the given <code>ISQLBuilderEditorInputUsageOptions</code> object for persistence.
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInputUsageOptions#decode(String)
	 * @return encoded String
	 */
	public String encode();
}
