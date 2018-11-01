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
package org.eclipse.datatools.sqltools.ddlgen.internal.ui.wizards;

import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;

public class FEConfigurationData
{
	private EngineeringOption [] options;

	public FEConfigurationData (EngineeringOption [] options)
	{
		this.options = options;
	}
	
	/**
	 * Will return the option used for this FE
	 * @return The set of options to use
	 */
	public EngineeringOption [] getOptions ()
	{
		return options;
	}
}
