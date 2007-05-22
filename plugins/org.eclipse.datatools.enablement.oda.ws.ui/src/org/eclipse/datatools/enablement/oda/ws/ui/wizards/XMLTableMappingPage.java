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
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSConsole;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSUIUtil;
import org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage;
import org.eclipse.jface.dialogs.IMessageProvider;

/**
 * 
 */

public class XMLTableMappingPage extends XPathChoosePage
{

	/**
	 * 
	 * @param pageName
	 */
	public XMLTableMappingPage( String pageName )
	{
		super( pageName );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#updateDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void updateDesign( DataSetDesign dataSetDesign )
	{
		WSUIUtil.savePage( dataSetDesign );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#getQueryText(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected String getQueryText( DataSetDesign dataSetDesign )
	{
		return WSUIUtil.getNonNullString( dataSetDesign.getPrivateProperties( )
				.findProperty( Constants.XML_QUERYTEXT )
				.getValue( ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#setQueryText(org.eclipse.datatools.connectivity.oda.design.DataSetDesign,
	 *      java.lang.String)
	 */
	protected void setQueryText( DataSetDesign dataSetDesign, String queryText )
	{
		dataSetDesign.getPrivateProperties( )
				.setProperty( Constants.XML_QUERYTEXT, queryText );
		WSConsole.getInstance( ).setPropertyValue( Constants.XML_QUERYTEXT,
				queryText );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#getXSDFileURI()
	 */
	protected String getXSDFileURI( )
	{
		return WSUIUtil.getNonNullString( WSConsole.getInstance( )
				.getPropertyValue( Constants.XSD_FILE_URI ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#getInitXMLFileURI()
	 */
	protected String getInitXMLFileURI( )
	{
		return WSUIUtil.getNonNullString( WSConsole.getInstance( )
				.getPropertyValue( Constants.XML_FILE_URI ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#getXMLFileURI()
	 */
	protected String getXMLFileURI( )
	{
		String xmlFileURI = WSUIUtil.EMPTY_STRING;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#refresh()
	 */
	protected void refresh( )
	{
		super.refresh( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#getInitQueryText()
	 */
	protected String getInitQueryText( )
	{
		return WSUIUtil.getNonNullString( WSConsole.getInstance( )
				.getPropertyValue( Constants.XML_QUERYTEXT ) );
	}

}
