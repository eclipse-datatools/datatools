/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.enablement.oda.xml.ui.control;

public interface IMenuActionHandler
{
	String getBaseFolder( );
	
	String getFilePath( );
	
	String[] getExtensionsFilter( );
	
	void setPath( String path );
}
