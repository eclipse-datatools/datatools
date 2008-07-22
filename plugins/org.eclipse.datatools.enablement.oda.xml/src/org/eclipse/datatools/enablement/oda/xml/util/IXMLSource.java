/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.util;

import java.io.InputStream;

import org.eclipse.datatools.connectivity.oda.OdaException;


public interface IXMLSource
{
	InputStream openInputStream( ) throws OdaException;
	String getEncoding( );
	void release( ) throws OdaException;
}
