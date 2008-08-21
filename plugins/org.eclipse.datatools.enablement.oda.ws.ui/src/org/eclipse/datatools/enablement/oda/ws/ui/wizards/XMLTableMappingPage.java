/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.ws.ui.wizards;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSConsole;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSUIUtil;
import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;
import org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Composite;

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
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		initWSConsole( );
		super.createPageCustomControl( parent );
	}

	private void initWSConsole( )
	{
		if ( !WSConsole.getInstance( ).isSessionOK( ) )
			WSConsole.getInstance( ).start( getInitializationDesign( ) );
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
		String queryText = dataSetDesign.getPrivateProperties( )
				.findProperty( Constants.XML_QUERYTEXT )
				.getValue( );
		return queryText == null ? WSUtil.EMPTY_STRING : queryText;
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
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#refresh()
	 */
	protected void refresh( )
	{
		try
		{
			updateXMLProps( );
			super.refresh( );
		}
		catch ( OdaException e )
		{
			setMessage( e.getMessage( ), IMessageProvider.ERROR );
		}
	}

	private void updateXMLProps( ) throws OdaException
	{
		WSConsole.getInstance( ).updateXSDFileURI( );
		WSConsole.getInstance( ).updateXMLFileURI( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.XPathChoosePage#cleanup()
	 */
	protected void cleanup( )
	{
		WSConsole.getInstance( ).terminateSession( );
	}

}
