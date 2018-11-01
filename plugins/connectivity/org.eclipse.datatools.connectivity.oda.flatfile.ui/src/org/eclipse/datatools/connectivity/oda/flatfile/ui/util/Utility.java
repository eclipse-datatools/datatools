/*******************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.oda.flatfile.ui.util;

import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

/**
 * 
 */

public class Utility
{

	/**
	 * Set context-sensitive help
	 * 
	 * @param control
	 * @param contextId
	 */
	public static void setSystemHelp( Control control, String contextId )
	{
		PlatformUI.getWorkbench( )
				.getHelpSystem( )
				.setHelp( control, contextId );
	}

}
