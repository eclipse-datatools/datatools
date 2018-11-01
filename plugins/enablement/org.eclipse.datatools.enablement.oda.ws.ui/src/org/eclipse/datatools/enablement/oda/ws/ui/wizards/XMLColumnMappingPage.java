/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.datatools.enablement.oda.xml.ui.wizards.XMLDataPreviewDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */

public class XMLColumnMappingPage
		extends
			org.eclipse.datatools.enablement.oda.xml.ui.wizards.ColumnMappingPage
{

	/**
	 * 
	 * @param pageName
	 */
	public XMLColumnMappingPage( String pageName )
	{
		super( pageName );
		supportsXMLParameter = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.ColumnMappingPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		initWSConsole( );
		super.createPageCustomControl( parent );
	}
	
	/*
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.ColumnMappingPage#getPreviewButtonAdapter()
	 */
	protected SelectionAdapter getPreviewButtonAdapter( )
	{
		return new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
	
				try
				{
					String sampleXMLFile = WSConsole.getInstance( )
							.getPropertyValue( Constants.XML_FILE_URI );
					if ( sampleXMLFile != null
							&& sampleXMLFile.trim( ).length( ) > 0 )
					{
						WSConsole.getInstance( ).setXMLPropertyValue( Constants.CONST_PROP_SAMPLE_XML, sampleXMLFile );
					}
					else
					{
						WSConsole.getInstance( ).createSampleXMLFile( );
					}
				}
				catch ( Exception ex )
				{
					// ignore it
				}

				XMLDataPreviewDialog previewDialog = new XMLDataPreviewDialog( getShell( ) );
				if ( previewDialog.open( ) == IDialogConstants.CLOSE_ID )
				{
					previewDialog.close( );
				}
			}
		};
	}

	private void initWSConsole( )
	{
		if ( !WSConsole.getInstance( ).isSessionOK( ) )
			WSConsole.getInstance( ).start( getInitializationDesign( ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.ColumnMappingPage#updateDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void updateDesign( DataSetDesign dataSetDesign )
	{
		WSUIUtil.savePage( dataSetDesign );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.enablement.oda.xml.ui.wizards.ColumnMappingPage
	 * #getQueryText
	 * (org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected String getQueryText( DataSetDesign dataSetDesign )
	{
		String queryTx = dataSetDesign.getPrivateProperties( )
				.findProperty( Constants.XML_QUERYTEXT )
				.getValue( );
		return queryTx == null ? WSUtil.EMPTY_STRING : queryTx;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.ColumnMappingPage#setQueryText(org.eclipse.datatools.connectivity.oda.design.DataSetDesign,
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
	 * @see org.eclipse.datatools.enablement.oda.xml.ui.wizards.ColumnMappingPage#refresh()
	 */
	public void refresh( )
	{
		updateXMLFileURI( );
		super.refresh( );
	}

	private void updateXMLFileURI( )
	{
		try
		{
			WSConsole.getInstance( ).updateXMLFileURI( );
		}
		catch ( OdaException e )
		{
			setMessage( e.getMessage( ), IMessageProvider.ERROR );
		}
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
