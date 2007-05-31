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

import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPParameter;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPRequest;
import org.eclipse.datatools.enablement.oda.ws.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSConsole;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSUIUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * 
 */

public class SOAPRequestPage extends DataSetWizardPage
{

	private transient Text queryText;
	// parameters for page, different from soapParameters
	private SOAPParameter[] parameters;
	private static String DEFAULT_MESSAGE = Messages.getString( "soapRequestPage.message.default" );//$NON-NLS-1$

	public SOAPRequestPage( String pageName )
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
		GridLayout layout = new GridLayout( 2, false );
		composite.setLayout( layout );
		GridData layoutData = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		composite.setLayoutData( layoutData );

		setupQueryTextComposite( composite );
		setupButtonComposite( composite );

		return composite;
	}

	private void setupQueryTextComposite( Composite parent )
	{
		queryText = new Text( parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		queryText.setLayoutData( layoutData );
	}

	private void setupButtonComposite( Composite parent )
	{
		Composite composite = new Composite( parent, SWT.NONE );
		composite.setLayout( new GridLayout( 1, false ) );
		GridData layoutData = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		composite.setLayoutData( layoutData );

		Button button = new Button( composite, SWT.NONE );
		button.setText( Messages.getString( "soapRequestPage.button.regenerateTemplate" ) ); //$NON-NLS-1$
		layoutData = new GridData( );
		layoutData.widthHint = 120;
		button.setLayoutData( layoutData );
		button.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( MessageDialog.openConfirm( null,
						Messages.getString( "soapRequestPage.title.regenerateTemplate" ), //$NON-NLS-1$
						Messages.getString( "soapRequestPage.message.regenerateTemplate" ) ) ) //$NON-NLS-1$
					regenerateTemplate( );
			}

		} );

		button = new Button( composite, SWT.NONE );
		button.setText( Messages.getString( "soapRequestPage.button.insertParameter" ) );//$NON-NLS-1$
		layoutData = new GridData( );
		layoutData.widthHint = 120;
		button.setLayoutData( layoutData );

		button.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				ParameterInputDialog dlg = new ParameterInputDialog( );
				if ( dlg.open( ) == Window.OK )
					parameters = dlg.getSOAPParameters( );
			}

		} );

		button = new Button( composite, SWT.NONE );
		button.setText( Messages.getString( "soapRequestPage.button.clear" ) ); //$NON-NLS-1$
		layoutData = new GridData( );
		layoutData.widthHint = 120;
		button.setLayoutData( layoutData );
		button.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				queryText.setText( WSUIUtil.EMPTY_STRING );
			}

		} );
	}

	private void regenerateTemplate( )
	{
		queryText.setText( WSUIUtil.getNonNullString( WSConsole.getInstance( )
				.getTemplate( ) ) );
	}

	/**
	 * Initializes the page control with the last edited data set design.
	 */
	private void initializeControl( )
	{
		initWSConsole( );
		initFromModel( );
	}

	private void initWSConsole( )
	{
		if ( !WSConsole.getInstance( ).isSessionOK( ) )
			WSConsole.getInstance( ).start( getInitializationDesign( ) );
	}

	private void initFromModel( )
	{
		String wsQueryText = WSConsole.getInstance( )
				.getPropertyValue( Constants.WS_QUERYTEXT );
		queryText.setText( WSUIUtil.getNonNullString( wsQueryText ) );
		parameters = WSConsole.getInstance( ).getParameters( );
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

	/**
	 * Saves the user-defined value in this page, and updates the specified
	 * dataSetDesign with the latest design definition.
	 */
	private void savePage( DataSetDesign dataSetDesign )
	{
		if ( !WSConsole.getInstance( ).isSessionOK( ) )
			return;

		dataSetDesign.setQueryText( WSConsole.getInstance( )
				.getPropertyValue( Constants.WS_QUERYTEXT ) );
		// parametes at this point has already be applied to model which will in
		// turn be applied to dataSetDesign later together with xmlQueryText
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#refresh(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void refresh( DataSetDesign dataSetDesign )
	{
		super.refresh( dataSetDesign );

		initFromModel( );
	}

	void refresh( )
	{
		queryText.setText( WSUIUtil.getNonNullString( WSConsole.getInstance( )
				.manipulateTemplate( ) ) );
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
		return super.getNextPage( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#canLeave()
	 */
	protected boolean canLeave( )
	{
		saveToModel( );
		return super.canLeave( );
	}

	private void saveToModel( )
	{
		WSConsole.getInstance( ).setPropertyValue( Constants.WS_QUERYTEXT,
				queryText.getText( ) );
		WSConsole.getInstance( ).setParameters( parameters );
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

	class ParameterInputDialog extends StatusDialog
	{

		private TableViewer viewer;
		private SOAPRequest soapRequest;

		private final String COLUMN_NAME = Messages.getString( "parameterInputDialog.column.name" );//$NON-NLS-1$ 
		private final String COLUMN_DATATYPE = Messages.getString( "parameterInputDialog.column.type" );//$NON-NLS-1$ 
		private final String COLUMN_DEFAULTVALUE = Messages.getString( "parameterInputDialog.column.defaultValue" );//$NON-NLS-1$ 

		/**
		 * 
		 */
		public ParameterInputDialog( )
		{
			super( PlatformUI.getWorkbench( ).getDisplay( ).getActiveShell( ) );
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.dialogs.StatusDialog#create()
		 */
		public void create( )
		{
			super.create( );

			Point pt = getShell( ).computeSize( -1, -1 );
			pt.x = Math.max( pt.x, 400 );
			pt.y = Math.max( pt.y, 200 );
			getShell( ).setSize( pt );
			getShell( ).setText( getTitle( ) );
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
		 */
		protected Control createDialogArea( Composite parent )
		{
			Composite composite = (Composite) super.createDialogArea( parent );
			GridLayout layout = new GridLayout( 1, false );
			layout.marginHeight = 10;
			layout.marginWidth = 10;
			composite.setLayout( layout );
			composite.setLayout( layout );

			createCustomControls( composite );
			initParameters( );

			return composite;
		}

		private void createCustomControls( Composite parent )
		{
			final Table table = new Table( parent, SWT.BORDER
					| SWT.FULL_SELECTION );
			TableLayout tableLayout = new TableLayout( );
			table.setLayout( tableLayout );
			GridData layouData = new GridData( GridData.FILL_BOTH );
			table.setLayoutData( layouData );

			table.setHeaderVisible( true );
			table.setLinesVisible( true );

			TableColumn column0 = new TableColumn( table, SWT.NONE );
			column0.setText( COLUMN_NAME );
			column0.setWidth( 150 );

			TableColumn column1 = new TableColumn( table, SWT.NONE );
			column1.setText( COLUMN_DATATYPE );
			column1.setWidth( 100 );

			TableColumn column2 = new TableColumn( table, SWT.NONE );
			column2.setText( COLUMN_DEFAULTVALUE );
			column2.setWidth( 150 );

			viewer = new TableViewer( table );
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
			setupEditors( );
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
					if ( property.equals( COLUMN_DEFAULTVALUE ) )
						return true;

					return false;
				}

				public Object getValue( Object element, String property )
				{
					return WSUIUtil.getNonNullString( ( (SOAPParameter) element ).getDefaultValue( ) );
				}

				public void modify( Object element, String property,
						Object value )
				{
					SOAPParameter soapParameter = (SOAPParameter) ( (TableItem) element ).getData( );
					soapParameter.setDefaultValue( value.toString( ) );
					viewer.refresh( );
				}
			} );
		}

		private void initParameters( )
		{
			soapRequest = new SOAPRequest( );
			soapRequest.setQueryText( queryText.getText( ) );
			soapRequest.init( );
			mergeParameters( );
			viewer.setInput( soapRequest.getParameters( ) );
		}

		private void mergeParameters( )
		{
			if ( !canMerge( ) )
				return;

			SOAPParameter[] soapParameters = soapRequest.getParameters( );
			for ( int i = 0; i < parameters.length; i++ )
			{
				if ( !WSUIUtil.isNull( parameters[i] ) )
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
				if ( !WSUIUtil.isNull( parameters[i] )
						&& !parameters[i].getName( )
								.equals( soapParameters[i].getName( ) ) )
					return false;
			}

			return true;
		}

		SOAPParameter[] getSOAPParameters( )
		{
			return soapRequest.getParameters( );
		}

	}

}
