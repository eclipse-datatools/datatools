/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml;

import java.net.URI;
import java.net.URL;

import junit.framework.TestCase;

/**
 * 
 */
public abstract class BaseTest extends TestCase
{
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		URL url = this.getClass( ).getProtectionDomain( ).getCodeSource( ).getLocation( );
		String pathBase = url.getFile();
		if( !pathBase.matches( ".*\\Q \\E.*" ))
			pathBase = new URI( pathBase ).getPath( );
		if ( pathBase.endsWith( "bin/" ) ) //$NON-NLS-1$
			pathBase = pathBase.substring( 0,
					pathBase.length( ) - 4 );
		if ( pathBase.endsWith( "bin" ) ) //$NON-NLS-1$
			pathBase = pathBase.substring( 0,
					pathBase.length( ) - 3 );
		
		System.setProperty( "xml.home", pathBase );
	}
}
