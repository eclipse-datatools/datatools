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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPParameter;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPRequest;
import org.eclipse.datatools.enablement.oda.ws.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSConsole;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSUIUtil;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * 
 */

public class SOAPParametersPage extends DataSetWizardPage
{

	private static final String COLUMN_NAME = Messages.getString( "parameterInputDialog.column.name" );//$NON-NLS-1$ 
	private static final String COLUMN_DATATYPE = Messages.getString( "parameterInputDialog.column.type" );//$NON-NLS-1$ 
	private static final String COLUMN_DEFAULTVALUE = Messages.getString( "parameterInputDialog.column.defaultValue" );//$NON-NLS-1$ 

	private CheckboxTableViewer viewer;
	private SOAPRequest soapRequest;
	private String wsQueryText;
	// parameters for page, different from soapParameters
	private SOAPParameter[] parameters;
	private static String DEFAULT_MESSAGE = Messages.getString( "soapParametersPage.message.default" );//$NON-NLS-1$

	public SOAPParametersPage( String pageName )
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
		setControl( createPageControl( parent ) );
		initializeControl( );
	}

	/**
	 * Creates custom control for user-defined query text.
	 */
	private Control createPageControl( Composite parent )
	{
		Composite composite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 1, false );
		composite.setLayout( layout );
		GridData layoutData = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		composite.setLayoutData( layoutData );

		setupParametersComposite( composite );

		return composite;
	}

	private void setupParametersComposite( Composite parent )
	{
		createCheckboxTable( parent );
		setupEditors( );
	}

	private void createCheckboxTable( Composite parent )
	{
		final Table table = new Table( parent, SWT.CHECK
				| SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL
				| SWT.V_SCROLL );
		TableLayout tableLayout = new TableLayout( );
		table.setLayout( tableLayout );
		GridData layouData = new GridData( GridData.FILL_BOTH );
		table.setLayoutData( layouData );

		table.setHeaderVisible( true );
		table.setLinesVisible( true );

		TableColumn column0 = new TableColumn( table, SWT.NONE );
		column0.setText( COLUMN_NAME );
		column0.setWidth( 200 );

		TableColumn column1 = new TableColumn( table, SWT.NONE );
		column1.setText( COLUMN_DATATYPE );
		column1.setWidth( 200 );

		TableColumn column2 = new TableColumn( table, SWT.NONE );
		column2.setText( COLUMN_DEFAULTVALUE );
		column2.setWidth( 200 );

		viewer = new CheckboxTableViewer( table );
		viewer.setContentProvider( new IStructuredContentProvider( ) {

			public Object[] getElements( Object inputElement )
			{
				if ( inputElement == null
						|| !( inputElement instanceof SOAPParameter[] ) )
					return new Object[0];

				return (SOAPParameter[]) inputElement;
			}

			public void inputChanged( Viewer viewer, Object oldInput,
					Object newInput )
			{
				
			}

			public void dispose( )
			{
			}
		} );
		viewer.setLabelProvider( new ITableLabelProvider( ) {

			public Image getColumnImage( Object element, int columnIndex )
			{
				return null;
			}

			public String getColumnText( Object element, int columnIndex )
			{
				SOAPParameter param = ( (SOAPParameter) element );
				String value = WSUIUtil.EMPTY_STRING;
				switch ( columnIndex )
				{
					case 0 :
						value = param.getName( );
						break;
					case 1 :
						value = WSUIUtil.EMPTY_STRING;
						break;
					case 2 :
						value = param.getDefaultValue( );
						break;
				}

				return WSUIUtil.getNonNullString( value );
			}

			public void addListener( ILabelProviderListener listener )
			{
			}

			public void dispose( )
			{
			}

			public boolean isLabelProperty( Object element, String property )
			{
				return false;
			}

			public void removeListener( ILabelProviderListener listener )
			{
			}
		} );
	}

	private void setupEditors( )
	{
		CellEditor[] editors = new CellEditor[3];
		for ( int i = 0; i < editors.length; i++ )
		{
			editors[i] = new TextCellEditor( viewer.getTable( ), SWT.NONE );
		}

		viewer.setCellEditors( editors );
		viewer.setColumnProperties( new String[]{
				COLUMN_NAME, COLUMN_DATATYPE, COLUMN_DEFAULTVALUE
		} );

		viewer.setCellModifier( new ICellModifier( ) {

			public boolean canModify( Object element, String property )
			{
				if ( viewer.getChecked( element )
						&& property.equals( COLUMN_DEFAULTVALUE ) )
					return true;

				return false;
			}

			public Object getValue( Object element, String property )
			{
				return WSUIUtil.getNonNullString( ( (SOAPParameter) element ).getDefaultValue( ) );
			}

			public void modify( Object element, String property, Object value )
			{
				SOAPParameter soapParameter = (SOAPParameter) ( (TableItem) element ).getData( );
				soapParameter.setDefaultValue( value.toString( ) );

				viewer.refresh( ); 
			}
		} );
	}

	/**
	 * Initializes the page control with the last edited data set design.
	 */
	private void initializeControl( )
	{
		initWSConsole( );
		initFromModel( );
		initViewer( );
	}

	private void initWSConsole( )
	{
		if ( !WSConsole.getInstance( ).isSessionOK( ) )
			WSConsole.getInstance( ).start( getInitializationDesign( ) );
	}

	private void initFromModel( )
	{
		wsQueryText = WSConsole.getInstance( )
				.getPropertyValue( Constants.WS_QUERYTEXT );
		parameters = WSConsole.getInstance( ).getParameters( );
	}

	private void initViewer( )
	{
		if ( WSUIUtil.isNull( wsQueryText ) )
			return;

		soapRequest = new SOAPRequest( );
		soapRequest.setQueryText( wsQueryText );
		soapRequest.init( );
		mergeParameters( );
		SOAPParameter[] soapParameters = soapRequest.getParameters( );
		if ( !WSUIUtil.isNull( soapParameters ) )
		{
			viewer.setInput( soapParameters );
			for ( int i = 0; i < soapParameters.length; i++ )
			{
				if ( !WSUIUtil.isNull( soapParameters[i] ) )
					viewer.setChecked( soapParameters[i], true );
			}
		}
	}

	private void mergeParameters( )
	{
		if ( !canMerge( ) )
			return;

		SOAPParameter[] soapParameters = soapRequest.getParameters( );
		for ( int i = 0; i < parameters.length; i++ )
		{
			soapParameters[i].setDefaultValue( parameters[i].getDefaultValue( ) );
		}
	}

	private boolean canMerge( )
	{
		SOAPParameter[] soapParameters = soapRequest.getParameters( );
		if ( parameters == null || soapParameters == null )
			return false;

		if ( parameters.length != soapParameters.length )
			return false;

		for ( int i = 0; i < parameters.length; i++ )
		{
			if ( !parameters[i].getName( )
					.equals( soapParameters[i].getName( ) ) )
				return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		savePage( design );
		WSUIUtil.savePage( design );
		return design;
	}

	private void savePage( DataSetDesign dataSetDesign )
	{
		// TODO nothing to save
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#refresh(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void refresh( DataSetDesign dataSetDesign )
	{
		initFromModel( );
		initViewer( );
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
		saveToModel( );

		IWizardPage page = super.getNextPage( );
		if ( page instanceof SOAPRequestPage )
			( (SOAPRequestPage) page ).refresh( );

		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#canLeave()
	 */
	protected boolean canLeave( )
	{
		viewer.refresh( );
		saveToModel( );
		return super.canLeave( );
	}

	private void saveToModel( )
	{
		WSConsole.getInstance( ).setPropertyValue( Constants.WS_QUERYTEXT,
				wsQueryText );
		WSConsole.getInstance( ).setParameters( getSOAPParameters( ) );
	}

	private SOAPParameter[] getSOAPParameters( )
	{
		Object[] candidates = (Object[]) viewer.getCheckedElements( );
		List manipulated = getManipulatedIndexList( candidates );

		SOAPParameter[] targets = soapRequest.getParameters( );
		if ( WSUIUtil.isNull( targets ) || manipulated.isEmpty( ) )
			return targets;

		List result = new ArrayList();
		for ( int i = 0; i < targets.length; i++ )
		{
			if ( ! (targets[i] != null && !manipulated.contains( new Integer( targets[i].getId( ) ) ) ))
				result.add( targets[i] );
		}
		SOAPParameter[] r = new SOAPParameter[result.size( )];
		for( int i = 0; i < result.size( ); i++ )
		{
			r[i] = (SOAPParameter)result.get( i );
		}
		
		return r;
	}

	private List getManipulatedIndexList( Object[] soapParameters )
	{
		if ( WSUIUtil.isNull( soapParameters ) || soapParameters.length == 0 )
			return Collections.EMPTY_LIST;

		List manipulated = new ArrayList( );
		for ( int i = 0; i < soapParameters.length; i++ )
			manipulated.add( new Integer( ( (SOAPParameter) soapParameters[i] ).getId( ) ) );

		return manipulated;
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

	void refresh( )
	{
		wsQueryText = WSConsole.getInstance( ).getTemplate( );
		initViewer( );
	}

}
