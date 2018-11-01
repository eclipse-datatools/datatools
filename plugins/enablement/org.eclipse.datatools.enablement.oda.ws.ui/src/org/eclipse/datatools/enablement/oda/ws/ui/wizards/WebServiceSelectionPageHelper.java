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

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.ui.nls.TextProcessorWrapper;
import org.eclipse.datatools.enablement.oda.ws.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.IHelpConstants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSUIUtil;
import org.eclipse.datatools.enablement.oda.ws.util.PropertyValueUtil;
import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * 
 */

public class WebServiceSelectionPageHelper
{

	private WizardPage wizardPage;
	private PreferencePage propertyPage;

	private transient Text wsdlURI;
	private transient Text soapEndPoint;
	private transient Text customClass;
	private transient Text driverClassPath;

	static final String DEFAULT_MESSAGE = Messages.getString( "webServiceSelectionPage.message.default" ); //$NON-NLS-1$

	/**
	 * 
	 * @param page
	 */
	WebServiceSelectionPageHelper( WizardPage page )
	{
		wizardPage = page;
	}

	/**
	 * 
	 * @param page
	 */
	WebServiceSelectionPageHelper( PreferencePage page )
	{
		propertyPage = page;
	}

	/**
	 * 
	 * @param parent
	 */
	void createCustomControl( Composite parent )
	{
		ScrolledComposite scrolledContent = new ScrolledComposite( parent,
				SWT.V_SCROLL | SWT.H_SCROLL );

		scrolledContent.setLayout( new FillLayout( ) );

		Composite composite = new Composite( scrolledContent, SWT.NONE );
		GridLayout layout = new GridLayout( 1, false );
		composite.setLayout( layout );

		setupWSDLGroup( composite );
		setupEndPointGroup( composite );
		setupCustomClassGroup( composite );
		setupClassPathGroup( composite );

		scrolledContent.setContent( composite );
		scrolledContent.setAlwaysShowScrollBars( false );
		scrolledContent.setExpandHorizontal( true );
		scrolledContent.setExpandVertical( true );

		Point size = composite.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		scrolledContent.setMinWidth( size.x + 20 );
		scrolledContent.setMinHeight( size.y + 20 );

		WSUIUtil.setSystemHelp( getControl( ),
				IHelpConstants.CONEXT_ID_WS_DATASOURCE );
	}

	private void setupWSDLGroup( Composite parent )
	{
		Group group = new Group( parent, SWT.SHADOW_ETCHED_IN );
		group.setLayout( new GridLayout( 3, false ) );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		group.setLayoutData( layoutData );
		group.setText( Messages.getString( "webServiceSelectionPage.group.wsdl" ) );//$NON-NLS-1$

		Label label = new Label( group, SWT.WRAP );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		label.setLayoutData( layoutData );
		label.setText( Messages.getString( "webServiceSelectionPage.label.wsdl" ) );//$NON-NLS-1$

		label = new Label( group, SWT.NONE );
		layoutData = new GridData( );
		label.setLayoutData( layoutData );
		label.setText( Messages.getString( "webServiceSelectionPage.label.wsdlURI" ) );//$NON-NLS-1$

		wsdlURI = new Text( group, SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		wsdlURI.setLayoutData( layoutData );
		wsdlURI.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				verifyPage( );
			}
		} );

		Button button = new Button( group, SWT.NONE );
		layoutData = new GridData( );
		button.setLayoutData( layoutData );
		button.setText( Messages.getString( "webServiceSelectionPage.button.browse" ) ); //$NON-NLS-1$
		button.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				FileDialog dialog = new FileDialog( PlatformUI.getWorkbench( )

				.getDisplay( ).getActiveShell( ), SWT.OPEN );
				dialog.setFilterExtensions( new String[]{
						"*.wsdl", "*.*" //$NON-NLS-1$ //$NON-NLS-2$
				} );
				if ( wsdlURI.getText( ) != null
						&& wsdlURI.getText( ).trim( ).length( ) > 0 )
				{
					dialog.setFilterPath( getWsdlURIString( ) );
				}

				String selectedLocation = dialog.open( );
				if ( selectedLocation != null )
				{
					setWsdlURIString( selectedLocation );
				}
			}

		} );
	}

	private void setupEndPointGroup( Composite parent )
	{
		Group group = new Group( parent, SWT.SHADOW_ETCHED_IN );
		group.setLayout( new GridLayout( 1, false ) );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		group.setLayoutData( layoutData );
		group.setText( Messages.getString( "webServiceSelectionPage.group.endPoint" ) );//$NON-NLS-1$

		Label label = new Label( group, SWT.WRAP );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		label.setLayoutData( layoutData );
		label.setText( Messages.getString( "webServiceSelectionPage.label.endPoint" ) );//$NON-NLS-1$

		soapEndPoint = new Text( group, SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		soapEndPoint.setLayoutData( layoutData );
		soapEndPoint.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				verifyPage( );
			}
		} );
	}

	private void setupCustomClassGroup( Composite parent )
	{
		Group group = new Group( parent, SWT.SHADOW_ETCHED_IN );
		group.setLayout( new GridLayout( 1, false ) );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		group.setLayoutData( layoutData );
		group.setText( Messages.getString( "webServiceSelectionPage.group.customClass" ) );//$NON-NLS-1$

		Label label = new Label( group, SWT.WRAP );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		label.setLayoutData( layoutData );
		label.setText( Messages.getString( "webServiceSelectionPage.label.customClass" ) );//$NON-NLS-1$

		customClass = new Text( group, SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		customClass.setLayoutData( layoutData );
	}
	
	private void setupClassPathGroup( Composite parent )
	{
		Group group = new Group( parent, SWT.SHADOW_ETCHED_IN );
		group.setLayout( new GridLayout( 1, false ) );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		group.setLayoutData( layoutData );
		group.setText( Messages.getString( "webServiceSelectionPage.group.classPath" ) );//$NON-NLS-1$

		Label label = new Label( group, SWT.WRAP );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		label.setLayoutData( layoutData );
		label.setText( Messages.getString( "webServiceSelectionPage.label.classPath" ) );//$NON-NLS-1$

		driverClassPath = new Text( group, SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		driverClassPath.setLayoutData( layoutData );
	}

	/**
	 * SoapEndPoint or at least a wsdl file with a workable soapEndpoint is
	 * expected here
	 * 
	 * @param props
	 * @return
	 */
	Properties collectCustomProperties( Properties props )
	{
		if ( props == null )
			props = new Properties( );

		String value = getSoapEndPointString( );
		if ( value != null )
			props.setProperty( Constants.SOAP_ENDPOINT, value );
		else
			props.remove( Constants.SOAP_ENDPOINT );
		
		value = getCustomClassString( );
		if ( value != null )
			props.setProperty( Constants.CUSTOM_CONNECTION_CLASS, value );
		else
			props.remove( Constants.CUSTOM_CONNECTION_CLASS );
		
		value = getWsdlURIString( );
		if ( value != null )
			props.setProperty( Constants.WSDL_URI, value );
		else
			props.remove( Constants.WSDL_URI );

		value = getDriverClassPathString( );
		if ( value != null )
			props.setProperty( Constants.CUSTOM_DRIVER_CLASS_PATH, value );
		else
			props.remove( Constants.CUSTOM_DRIVER_CLASS_PATH );
		return props;
	}

	/**
	 * 
	 * @param profileProps
	 */
	void initCustomControl( Properties profileProps )
	{
		if ( profileProps == null || profileProps.isEmpty( ) )
		{
			setPageComplete( false );
			setMessage( DEFAULT_MESSAGE, IMessageProvider.NONE );
			return; // nothing to initialize
		}

		setWsdlURIString( profileProps.getProperty( Constants.WSDL_URI ) );
		setSoapEndPointString( profileProps.getProperty( Constants.SOAP_ENDPOINT ) );
		setCustomClassString( profileProps.getProperty( Constants.CUSTOM_CONNECTION_CLASS ) );
		setDriverClassPathString( profileProps.getProperty( Constants.CUSTOM_DRIVER_CLASS_PATH ) );
	}

	private void verifyPage( )
	{
		if ( WSUtil.isNull( soapEndPoint.getText( ) )
				&& WSUtil.isNull( wsdlURI.getText( ) ) )
		{
			setPageComplete( false );
			setMessage( Messages.getString( "webServiceSelectionPage.message.error" ), //$NON-NLS-1$
					IMessageProvider.ERROR );
		}
		else
		{
			setPageComplete( true );
			setMessage( DEFAULT_MESSAGE, IMessageProvider.NONE );
		}
	}

	/**
	 * 
	 * @param complete
	 */
	private void setPageComplete( boolean complete )
	{
		if ( wizardPage != null )
			wizardPage.setPageComplete( complete );
		else if ( propertyPage != null )
			propertyPage.setValid( complete );
	}

	/**
	 * 
	 * @param newMessage
	 * @param newType
	 */
	private void setMessage( String newMessage, int newType )
	{
		if ( wizardPage != null )
			wizardPage.setMessage( newMessage, newType );
		else if ( propertyPage != null )
			propertyPage.setMessage( newMessage, newType );
	}

	void setWsdlURIString( String text )
	{
		if ( text != null )
			wsdlURI.setText( TextProcessorWrapper.process( text ) );
	}
	
	private String getDriverClassPathString( )
	{
		return TextProcessorWrapper.deprocess( PropertyValueUtil.getQualifiedValueForDataSource( driverClassPath.getText( ),
				Constants.CUSTOM_DRIVER_CLASS_PATH ) );
	}
	private void setDriverClassPathString( String text )
	{
		if ( text != null )
			driverClassPath.setText( TextProcessorWrapper.process( text ) );
	}

	private String getSoapEndPointString( )
	{
		return TextProcessorWrapper.deprocess( PropertyValueUtil.getQualifiedValueForDataSource( soapEndPoint.getText( ),
				Constants.SOAP_ENDPOINT ) );
	}

	private String getCustomClassString( )
	{
		return TextProcessorWrapper.deprocess( PropertyValueUtil.getQualifiedValueForDataSource( customClass.getText( ),
				Constants.CUSTOM_CONNECTION_CLASS ) );
	}
	
	private void setSoapEndPointString( String text )
	{
		if ( text != null )
			soapEndPoint.setText( TextProcessorWrapper.process( text ) );
	}

	private String getWsdlURIString( )
	{
		return TextProcessorWrapper.deprocess( PropertyValueUtil.getQualifiedValueForDataSource( wsdlURI.getText( ),
				Constants.WSDL_URI ) );
	}
	
	private void setCustomClassString( String text )
	{
		if ( text != null )
			customClass.setText( TextProcessorWrapper.process( text ) );
	}
	
    private Control getControl()
    {
        if ( wizardPage != null )
            return wizardPage.getControl();
        assert( propertyPage != null );
        return propertyPage.getControl();
    }
}
