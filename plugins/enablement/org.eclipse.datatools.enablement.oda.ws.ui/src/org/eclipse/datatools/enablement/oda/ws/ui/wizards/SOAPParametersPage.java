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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPParameter;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPRequest;
import org.eclipse.datatools.enablement.oda.ws.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.IHelpConstants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSConsole;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSUIUtil;
import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * This class is NOT intended to be used in edit mode
 */

public class SOAPParametersPage extends DataSetWizardPage
{

	private static final String COLUMN_NAME = Messages.getString( "soapParametersPage.column.paramName" );//$NON-NLS-1$ 
	private static final String COLUMN_DATATYPE = Messages.getString( "soapParametersPage.column.dataType" );//$NON-NLS-1$ 

	private CheckboxTableViewer viewer;
	private SOAPRequest soapRequest;
	private String wsQueryText;
	
	private boolean modelChanged;

	private static String DEFAULT_MESSAGE = Messages.getString( "soapParametersPage.message.default" );//$NON-NLS-1$

	/**
	 * 
	 * @param pageName
	 */
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
		WSUIUtil.setSystemHelp( getControl( ), IHelpConstants.CONEXT_ID_WS_SOAP_PARAMETER );
	}

	/**
	 * Creates custom control for user-defined query text.
	 */
	private Control createPageControl( Composite parent )
	{
		ScrolledComposite sComposite = new ScrolledComposite( parent, SWT.H_SCROLL | SWT.V_SCROLL );
		sComposite.setLayout( new GridLayout( ) );
		sComposite.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		sComposite.setMinWidth( 600 );
		sComposite.setExpandHorizontal( true );

		Composite composite = new Composite( sComposite, SWT.NONE );
		GridLayout layout = new GridLayout( 1, false );
		layout.verticalSpacing = 20;
		composite.setLayout( layout );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		composite.setLayoutData( layoutData );

		setupParametersComposite( composite );
		setupSelectionButtons( composite );
		
		Point size = composite.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		composite.setSize( size.x, size.y );

		sComposite.setContent( composite );
		setControl( sComposite );

		modelChanged = true;
		
		return sComposite;
	}

	private void setupParametersComposite( Composite parent )
	{
		createCheckboxTable( parent );
		setupEditors( );
	}
	
	/**
	 * Create the "select all" and "deselect all" buttons
	 * 
	 * @param parent
	 */
	private void setupSelectionButtons( Composite parent )
	{
		GridLayout layout = new GridLayout( );
		Composite btnComposite = new Composite( parent, SWT.NONE );
		btnComposite.setLayout( layout );
		layout.numColumns = 3;
		btnComposite.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

		Label label = new Label( btnComposite, SWT.NONE );
		label.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

		Button selectAllBtn = new Button( btnComposite, SWT.NONE );
		selectAllBtn.setText( Messages.getString( "soapParametersPage.button.selectAll" ) ); //$NON-NLS-1$
		selectAllBtn.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if( viewer.getCheckedElements( ).length < viewer.getTable( ).getItemCount( ) )
				{
					viewer.setAllChecked( true );
					saveToModel( );
					modelChanged = true;
				}
			}

		} );

		Button deselectAllBtn = new Button( btnComposite, SWT.NONE );
		deselectAllBtn.setText( Messages.getString( "soapParametersPage.button.deselectAll" ) ); //$NON-NLS-1$
		deselectAllBtn.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( viewer.getCheckedElements( ).length > 0 )
				{
					viewer.setAllChecked( false );
					saveToModel( );
					modelChanged = true;
				}
			}

		} );

		GridData btnGd = new GridData( GridData.CENTER );
		btnGd.horizontalIndent = 10;
		btnGd.widthHint = getMaxWidth( deselectAllBtn,
				getMaxWidth( selectAllBtn, 100 ) );
		
		selectAllBtn.setLayoutData( btnGd );
		deselectAllBtn.setLayoutData( btnGd );

	}
	
	private int getMaxWidth( Button button, int size )
	{
		int width = button.computeSize( -1, -1 ).x;
		return width > size ? width : size;
	}

	private void createCheckboxTable( Composite parent )
	{
		final Table table = new Table( parent, SWT.CHECK
				| SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL
				| SWT.V_SCROLL );
		TableLayout tableLayout = new TableLayout( );
		table.setLayout( tableLayout );
		GridData layouData = new GridData( GridData.FILL_BOTH );
		layouData.heightHint = 400;
		table.setLayoutData( layouData );

		table.setHeaderVisible( true );
		table.setLinesVisible( true );

		TableColumn nameColumn = new TableColumn( table, SWT.NONE );
		nameColumn.setText( COLUMN_NAME );
		nameColumn.setWidth( 200 );
		nameColumn.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				sortParametersTable( );
				modelChanged = true;
			}

		} );

		TableColumn dataTypeColumn = new TableColumn( table, SWT.NONE );
		dataTypeColumn.setText( COLUMN_DATATYPE );
		dataTypeColumn.setWidth( 200 );

		createCheckBoxTableViewer( table );
	}

	private void createCheckBoxTableViewer( final Table table )
	{
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
				String value = WSUtil.EMPTY_STRING;
				switch ( columnIndex )
				{
					case 0 :
						value = param.getName( );
						break;
					case 1 :
						value = WSUtil.EMPTY_STRING;
						break;
					case 2 :
						value = param.getDefaultValue( );
						break;
				}

				return value;
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
		viewer.addCheckStateListener( new ICheckStateListener( ) {

			public void checkStateChanged( CheckStateChangedEvent event )
			{
				if ( !event.getChecked( )
						&& event.getElement( ) instanceof SOAPParameter )
				{
					( (SOAPParameter) event.getElement( ) ).setDefaultValue( WSUtil.EMPTY_STRING );
					viewer.refresh( );
					saveToModel( );
				}
				modelChanged = true;
			}

		} );
	}
	
	/**
	 * Lexicographically sort the parameters in the table
	 * 
	 */
	private void sortParametersTable( )
	{
		if ( viewer.getTable( ).getSortDirection( ) == SWT.UP )
		{
			viewer.setSorter( new ParametersViewerSorter( true ) );
			viewer.getTable( ).setSortDirection( SWT.DOWN );
		}
		else
		{
			viewer.setSorter( new ParametersViewerSorter( false ) );
			viewer.getTable( ).setSortDirection( SWT.UP );
		}
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
				COLUMN_NAME, COLUMN_DATATYPE
		} );

	}

	/**
	 * Initializes the page control with the last edited data set design.
	 */
	private void initializeControl( )
	{
		initWSConsole( );
		initFromModel( );
		initViewer( true );
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
	}

	private void initViewer( boolean refreshParameters )
	{
		if ( WSUtil.isNull( wsQueryText ) )
			return;

		soapRequest = new SOAPRequest( wsQueryText );
		SOAPParameter[] soapParameters = null;
		
		if( refreshParameters || WSConsole.getInstance( ).getParameters( ) == null )
		{
			soapParameters = soapRequest.getParameters( );
		}
		else
		{
			soapParameters = WSConsole.getInstance( ).getParameters( );
		}
			
		if ( !WSUtil.isNull( soapParameters ) )
		{
			viewer.setInput( soapParameters );
			for ( int i = 0; i < soapParameters.length; i++ )
			{
				viewer.setChecked( soapParameters[i],
						soapParameters[i].isUsed( ) );
			}
		}
		else
		{
			viewer.setInput( new SOAPParameter[0] );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		savePage( design );
		return design;
	}

	private void savePage( DataSetDesign dataSetDesign )
	{
		// TODO nothing to save
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

		IWizardPage page = super.getNextPage( );
		if ( modelChanged )
		{
			saveToModel( );
			if ( page instanceof SOAPRequestPage )
			{
				try
				{
					( (SOAPRequestPage) page ).refresh( );
					modelChanged = false;
				}
				catch ( OdaException e )
				{
					this.setErrorMessage( e.getMessage( ) );
				}
			}
		}

		return page;
	}

	private void saveToModel( )
	{
		WSConsole.getInstance( ).setPropertyValue( Constants.WS_QUERYTEXT,
				wsQueryText );
		WSConsole.getInstance( ).setParameters( getSOAPParameters( ) );
	}

	private SOAPParameter[] getSOAPParameters( )
	{
		SOAPParameter[] targets = soapRequest.getParameters( );
		if ( WSUtil.isNull( targets ) )
			return targets;

		Object[] candidates = (Object[]) viewer.getCheckedElements( );
		List manipulated = getManipulatedIndexList( candidates );

		for ( int i = 0; i < targets.length; i++ )
		{
			targets[i].setUsed( manipulated.contains( new Integer( targets[i].getId( ) ) ) );
		}

		return targets;
	}

	private List getManipulatedIndexList( Object[] soapParameters )
	{
		if ( WSUtil.isNull( soapParameters ) || soapParameters.length == 0 )
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

	void refresh( boolean refreshParameters ) throws OdaException
	{
		wsQueryText = WSConsole.getInstance( ).getTemplate( );
		initViewer( refreshParameters );
	}
	
	private class ParametersViewerSorter extends ViewerSorter
	{
		private boolean descent;
		
		public ParametersViewerSorter( boolean descent )
		{
			this.descent = descent;
		}
		
		public int compare( Viewer viewer, Object o1, Object o2 )
		{
			assert !( o1 instanceof SOAPParameter ) || !( o2 instanceof SOAPParameter );

			int result = ( (SOAPParameter) o1 ).getName( )
					.compareTo( ( (SOAPParameter) o2 ).getName( ) );
			if ( result == 0 )
				return 0;
			
			if ( result > 0 )
			{
				if ( descent )
					return -1;
				else
					return 1;
			}
			else
			{
				if ( descent )
					return 1;
				else
					return -1;
			}
		}
	}

}
