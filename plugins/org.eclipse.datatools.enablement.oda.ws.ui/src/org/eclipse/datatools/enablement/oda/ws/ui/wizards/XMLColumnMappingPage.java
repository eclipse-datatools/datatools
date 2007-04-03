/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.ws.ui.wizards;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSConsole;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSUIUtil;
import org.eclipse.datatools.enablement.oda.ws.util.Constants;
import org.eclipse.jface.dialogs.IMessageProvider;

/**
 * 
 */

public class XMLColumnMappingPage
		extends
			org.eclipse.datatools.enablement.oda.xml.ui.wizards.ColumnMappingPage
{

	protected boolean needsPopulate( String xsdFile, String xmlFile )
	{
		return !( WSUIUtil.isNull( xsdFile ) && WSUIUtil.isNull( xmlFile ) );
	}

	public XMLColumnMappingPage( String pageName )
	{
		super( pageName );
	}

	protected void updateDesign( DataSetDesign dataSetDesign )
	{
		WSUIUtil.savePage( dataSetDesign );
	}

	protected String getQueryText( DataSetDesign dataSetDesign )
	{
		return WSUIUtil.getNonNullString( dataSetDesign.getPrivateProperties( )
				.findProperty( Constants.XML_QUERYTEXT )
				.getValue( ) );
	}

	protected void setQueryText( DataSetDesign dataSetDesign, String queryText )
	{
		dataSetDesign.getPrivateProperties( )
				.setProperty( Constants.XML_QUERYTEXT, queryText );
	}

	protected String getXSDFileURI( )
	{
		return WSUIUtil.getNonNullString( WSConsole.getInstance( )
				.getPropertyValue( Constants.XSD_FILE_URI ) );
	}

	protected String getInitXMLFileURI( )
	{
		return WSUIUtil.getNonNullString( WSConsole.getInstance( )
				.getPropertyValue( Constants.XML_FILE_URI ) );
	}

	protected String getXMLFileURI( )
	{
		String xmlFileURI = "";
		try
		{
			xmlFileURI = WSConsole.getInstance( ).getXMLFileURI( );
		}
		catch ( OdaException e )
		{
			setMessage( e.getMessage( ), IMessageProvider.ERROR );
		}

		return xmlFileURI;
	}

	protected String getInitQueryText( )
	{
		return WSUIUtil.getNonNullString( WSConsole.getInstance( )
				.getPropertyValue( Constants.XML_QUERYTEXT ) );
	}

}
