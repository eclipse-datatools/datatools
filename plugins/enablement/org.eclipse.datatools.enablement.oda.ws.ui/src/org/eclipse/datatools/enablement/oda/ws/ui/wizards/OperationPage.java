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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.WSDLException;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.ws.ui.Activator;
import org.eclipse.datatools.enablement.oda.ws.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.IHelpConstants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSConsole;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSUIUtil;
import org.eclipse.datatools.enablement.oda.ws.util.WSDLAdvisor;
import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * 
 */

public class OperationPage extends DataSetWizardPage
{

	private transient Tree operationTree;
	private transient Label operationName;
	private transient Text operationDescription;

	private String operationTrace = WSUtil.EMPTY_STRING;
	private String initOperationTrace = WSUtil.EMPTY_STRING;
	private String wsdlURI = WSUtil.EMPTY_STRING;
	private String wsQuery = WSUtil.EMPTY_STRING;

	private Image wsdlImage;
	private Image serviceImage;
	private Image portImage;
	private Image operationImage;
	
	private boolean selectionChanged = false;

	private static String DEFAULT_MESSAGE = Messages.getString( "operationPage.message.default" );//$NON-NLS-1$

	/**
	 * 
	 * @param pageName
	 */
	public OperationPage( String pageName )
	{
		super( pageName );
		setMessage( DEFAULT_MESSAGE );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		initialImages( );
		
		ScrolledComposite sComposite = new ScrolledComposite( parent, SWT.H_SCROLL | SWT.V_SCROLL );
		sComposite.setLayout( new GridLayout( ) );
		sComposite.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		sComposite.setMinWidth( 600 );
		sComposite.setExpandHorizontal( true );

		Control control = createPageControl( sComposite );
		initializeControl( );

		int x = java.awt.Toolkit.getDefaultToolkit( ).getScreenSize( ).width
				- 800;
		int y = java.awt.Toolkit.getDefaultToolkit( ).getScreenSize( ).height
				- 700;
		if ( getShell( ) != null )
		{
			getShell( ).setLocation( x / 2, y / 2 );
		}
		else
		{
			parent.getShell( ).setLocation( x / 2, y / 2 );
		}
		
		Point size = control.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		control.setSize( size.x, size.y );

		sComposite.setContent( control );
		setControl( sComposite );

		WSUIUtil.setSystemHelp( getControl( ), IHelpConstants.CONEXT_ID_WS_OPERATION );
	}

	private void initialImages( )
	{
		wsdlImage = Activator.getDefault( )
				.getImageRegistry( )
				.get( Activator.ICON_WSDL );
		serviceImage = Activator.getDefault( )
				.getImageRegistry( )
				.get( Activator.ICON_SERVICE );
		portImage = Activator.getDefault( )
				.getImageRegistry( )
				.get( Activator.ICON_PORT );
		operationImage = Activator.getDefault( )
				.getImageRegistry( )
				.get( Activator.ICON_OPERATION );
	}

	private Control createPageControl( Composite parent )
	{
		Composite composite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 1, false );
		layout.verticalSpacing = 30;
		composite.setLayout( layout );
		GridData layoutData = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		composite.setLayoutData( layoutData );

		setupTreeComposite( composite );
		setupTextComposite( composite );

		return composite;
	}

	private void setupTreeComposite( Composite parent )
	{
		operationTree = new Tree( parent, SWT.BORDER
				| SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL );
		operationTree.setLayout( new GridLayout( ) );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.heightHint = 200;
		operationTree.setLayoutData( layoutData );

		operationTree.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent event )
			{
				TreeItem item = operationTree.getSelection( )[0];

				if ( item.getData( ) instanceof Operation )
				{
					Operation operation = (Operation) item.getData( );
					operationTrace = toOperationTrace( item );
					if ( operation.getName( ) != null )
						operationName.setText( operation.getName( ) );
					operationDescription.setText( WSDLAdvisor.retrieveDocument( operation ) );
					setPageComplete( true );
				}
				else
				{
					operationName.setText( WSUtil.EMPTY_STRING );
					operationDescription.setText( WSUtil.EMPTY_STRING );
					setPageComplete( false );
				}
				
				selectionChanged = true;
			}
			
		} );
	}

	// TODO refine me
	private String toOperationTrace( TreeItem item )
	{
		Service service = (Service) item.getParentItem( )
				.getParentItem( )
				.getData( );
		Port port = (Port) item.getParentItem( ).getData( );
		Operation operation = (Operation) item.getData( );
		return service.getQName( ).getLocalPart( )
				+ Constants.DELIMITER_OPEARTION + port.getName( )
				+ Constants.DELIMITER_OPEARTION + operation.getName( );
	}

	private void setupTextComposite( Composite parent )
	{
		Composite composite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 3, false );
		layout.verticalSpacing = 20;
		layout.horizontalSpacing = 30;
		composite.setLayout( layout );
		GridData layoutData = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		composite.setLayoutData( layoutData );

		Label label = new Label( composite, SWT.NONE );
		layoutData = new GridData( );
		label.setLayoutData( layoutData );
		label.setText( Messages.getString( "operationPage.label.selectOpearation" ) );//$NON-NLS-1$
		if ( label.computeSize( -1, -1 ).x < 100 )
			layoutData.widthHint = 100;

		operationName = new Label( composite, SWT.BORDER );
		GridData gd = new GridData( GridData.FILL_HORIZONTAL );
		gd.horizontalSpan = 2;
		operationName.setLayoutData( gd );

		Label label2 = new Label( composite, SWT.NONE );
		GridData data = new GridData( );
		label2.setLayoutData( data );
		label2.setText( Messages.getString( "operationPage.label.document" ) ); //$NON-NLS-1$
		if ( label2.computeSize( -1, -1 ).x < 100 )
			data.widthHint = 100;

		Composite container = new Composite( composite, SWT.NONE );
		GridLayout gLayout = new GridLayout( );
		gLayout.marginWidth = gLayout.marginHeight = 0;
		container.setLayout( gLayout );
		layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = 2;
		container.setLayoutData( layoutData );
		
		operationDescription = new Text( container, SWT.BORDER
				| SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY );
		GridData layoutData2 = new GridData( GridData.FILL_BOTH );
		layoutData2.heightHint = 60;
		layoutData2.widthHint = 50;
		operationDescription.setLayoutData( layoutData2 );
	}

	/**
	 * Initializes the page control with the last edited data set design.
	 */
	private void initializeControl( )
	{
		initWSConsole( );
		initFromModel( );

		populateTree( );
		setPageComplete( false );
	}

	private void initWSConsole( )
	{
		if ( !WSConsole.getInstance( ).isSessionOK( ) )
			WSConsole.getInstance( ).start( getInitializationDesign( ) );
	}

	private void initFromModel( )
	{
		wsdlURI = WSConsole.getInstance( )
				.getPropertyValue( Constants.WSDL_URI );
		operationTrace = WSConsole.getInstance( )
				.getPropertyValue( Constants.OPERATION_TRACE );
		initOperationTrace = WSConsole.getInstance( )
				.getPropertyValue( Constants.OPERATION_TRACE );
	}

	// TODO refine me
	private void populateTree( )
	{
		if ( WSUtil.isNull( wsdlURI ) )
			return;

		operationTree.removeAll( );

		TreeItem root = new TreeItem( operationTree, SWT.NONE );
		root.setText( wsdlURI );
		root.setImage( wsdlImage );
		Definition definition = null;
		try
		{
			definition = WSDLAdvisor.getDefinition( wsdlURI );
		}
		catch ( WSDLException e )
		{
			this.setErrorMessage( e.getMessage( ) );
		}
		if ( definition == null )
			return;

		Map services = definition.getServices( );
		Iterator srcIT = services.keySet( ).iterator( );
		while ( srcIT.hasNext( ) )
		{
			Service service = (Service) services.get( srcIT.next( ) );
			TreeItem srcTI = populateTreeItem( root,
					service,
					service.getQName( ).getLocalPart( ),
					serviceImage );// TI: treeItem
			Map ports = service.getPorts( );
			Iterator prtIT = ports.keySet( ).iterator( );// IT:iterator
			while ( prtIT.hasNext( ) )
			{
				Port port = (Port) ports.get( prtIT.next( ) );
				TreeItem prtTI = populateTreeItem( srcTI,
						port,
						port.getName( ),
						portImage );
				List operations = port.getBinding( )
						.getPortType( )
						.getOperations( );
				for ( int i = 0; i < operations.size( ); i++ )
				{
					Operation operation = (Operation) operations.get( i );
					TreeItem treeItem = populateTreeItem( prtTI,
							operations.get( i ),
							operation.getName( ),
							operationImage );
					if ( !WSUtil.isNull( operationTrace )
							&& operationTrace.equals( toOperationTrace( treeItem ) ) )

					{
						highlight( treeItem );
						if ( operation.getName( ) != null )
							operationName.setText( operation.getName( ) );
						operationDescription.setText( WSDLAdvisor.retrieveDocument( operation ) );
					}
				}
			}
		}
	}

	// TODO lazy load
	private TreeItem populateTreeItem( TreeItem parent, Object child,
			String text, Image image )
	{
		TreeItem item = new TreeItem( parent, SWT.NONE );
		item.setData( child );
		item.setText( text );
		item.setImage( image );

		return item;
	}

	private void highlight( TreeItem treeItem )
	{
		if ( WSUtil.isNull( operationTrace ) )
			return;

		FontData fontData = new FontData( WSUtil.EMPTY_STRING, 8, SWT.BOLD );
		treeItem.setFont( new Font( null, fontData ) );

		operationTree.setSelection( new TreeItem[]{
			treeItem
		} );
		operationTree.setFocus( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		WSUIUtil.checkExisted( design );
		savePage( design );

		return design;
	}

	private void savePage( DataSetDesign design )
	{
		if ( !WSConsole.getInstance( ).isSessionOK( ) )
			return;

		design.getPrivateProperties( ).setProperty( Constants.OPERATION_TRACE,
				WSConsole.getInstance( )
						.getPropertyValue( Constants.OPERATION_TRACE ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#refresh(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void refresh( DataSetDesign dataSetDesign )
	{
		super.refresh( dataSetDesign );

		refresh( );
	}

	private void refresh( )
	{
		initFromModel( );
		populateTree( );

		setMessage( DEFAULT_MESSAGE );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#canLeave()
	 */
	protected boolean canLeave( )
	{
		saveToModle( );
		try
		{
			testDirty( );
		}
		catch ( OdaException e )
		{
			this.setErrorMessage( e.getMessage( ) );
		}

		return super.canLeave( );
	}

	private void testDirty( ) throws OdaException
	{
		if ( !WSUtil.isNull( initOperationTrace )
				&& !initOperationTrace.equals( operationTrace ) )
			if ( MessageDialog.openQuestion( null,
					Messages.getString( "operationPage.title.operationChanged" ), Messages.getString( "operationPage.message.regenerate.SOAPRequest" ) ) )//$NON-NLS-1$ //$NON-NLS-2$
			{
				regenerateTemplate( );
				try
				{
					WSConsole.getInstance( ).createXMLTempFileURI( );
				}
				catch ( OdaException e )
				{
					setMessage( e.getMessage( ), IMessageProvider.ERROR );
				}
			}
	}

	private void regenerateTemplate( ) throws OdaException
	{
		wsQuery = WSConsole.getInstance( ).getTemplate( );
		if ( !WSUtil.isNull( wsQuery ) )
			WSConsole.getInstance( ).setPropertyValue( Constants.WS_QUERYTEXT,
					wsQuery );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage( )
	{
		return isPageComplete( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage( )
	{
		saveToModle( );

		IWizardPage page = super.getNextPage( );
		if ( page instanceof SOAPParametersPage )
			try
			{
				( (SOAPParametersPage) page ).refresh( selectionChanged );
			}
			catch ( OdaException e )
			{
				this.setErrorMessage( e.getMessage( ) );
			}

		selectionChanged = false;		
		return page;
	}

	private void saveToModle( )
	{
		if ( operationTrace != null
				&& !operationTrace.equals( WSConsole.getInstance( )
						.getPropertyValue( Constants.OPERATION_TRACE ) ) )
		{
			WSConsole.getInstance( )
					.setPropertyValue( Constants.OPERATION_TRACE,
							operationTrace );
			WSConsole.getInstance( ).setRefreshTempFile( true );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#cleanup()
	 */
	protected void cleanup( )
	{
		WSConsole.getInstance( ).terminateSession( );
	}

}
