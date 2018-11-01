/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers;

import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;

public interface IDriverValuesProvider {
	
	/* 
	 * If overriding specific main attributes of a driver template,
	 * use one of the following constants. If the value key coming in
	 * is not one of the known keys, it will look for it in the 
	 * properties for the driver template.
	 */
	public static String VALUE_NAME = "name"; //$NON-NLS-1$
	public static String VALUE_JARLIST = "jarList"; //$NON-NLS-1$
	public static String VALUE_CREATE_DEFAULT = "createDefault"; //$NON-NLS-1$
	public static String VALUE_DEFAULT_DEFINITION_NAME = "defaultDefinitionName"; //$NON-NLS-1$
	
	public String createDefaultValue(String key);
	
	public void setDriverTemplate ( TemplateDescriptor template );
	
	public TemplateDescriptor getDriverTemplate ( );

}
