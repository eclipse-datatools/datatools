/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.ui.UiPlugin;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.preference.DataSetPreferencePage;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.ExceptionHandler;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.datatools.enablement.oda.xml.util.ui.SchemaPopulationUtil;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

/**
 * Root xpath choose page. It expands the xml tree list to choose the preferred
 * root path
 */

public class XPathChoosePage extends DataSetWizardPage
{
    private String pageDefaultMessage = Messages.getString( "wizard.defaultMessage.selectXPath" );  //$NON-NLS-1$
    
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	
	private transient XMLTreeViewer availableXmlTree;
	private transient StyledText xmlPathText;
	private transient Group rightGroup;
	private Menu quickFixMenu;
	
	private ATreeNode treeNode;
	private TreeItem selectedItem;
	private String xsdFileName;
	private String xmlFileName;
	private String xmlEncoding;
	private String initRootPath;
	private String rootPath;
	private int selectRadioIndex = 1; // default selection is 3
	
	protected boolean supportsXMLParameter = true;

	/**
	 * @param string
	 */
	public XPathChoosePage( )
	{
		this( Messages.getString( "wizard.title.newDataSet" ) ); //$NON-NLS-1$
	}

	/**
	 * @param pageName
	 */
	public XPathChoosePage( String pageName )
	{
		super( pageName );
		this.setTitle( pageName );
		this.setMessage( pageDefaultMessage );
		this.setPageComplete( false );
		this.supportsXMLParameter = true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		ScrolledComposite sComposite = new ScrolledComposite( parent,
				SWT.H_SCROLL | SWT.V_SCROLL );
		sComposite.setLayout( new GridLayout( ) );
		sComposite.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		sComposite.setMinWidth( 600 );
		sComposite.setExpandHorizontal( true );

		Control control = createPageControl( sComposite );
		
		if( XMLInformationHolder.hasDestroyed() )
			XMLInformationHolder.start( this.getInitializationDesign( ) );
		initializeControl( );
		populateXMLTree( );
		
		Point size = control.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		control.setSize( size.x, size.y );

		sComposite.setContent( control );
		setControl( sComposite );

		XMLRelationInfoUtil.setSystemHelp( getControl( ),
				IHelpConstants.CONEXT_ID_DATASET_XML_XPATH );
	}
	
	/**
	 * initial the info after create the control
	 * 
	 */
	private void initializeControl( )
	{
		xsdFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
		xmlFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
		xmlEncoding = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST );
	
		String queryText = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION );

		String tableName = XMLRelationInfoUtil.getTableName( queryText );
		if ( tableName != null )
			rootPath = XMLRelationInfoUtil.getXPathExpression( queryText,
					tableName );
		else
			rootPath = EMPTY_STRING;

		backupRootPath( );
		if ( rootPath != null && rootPath.length( ) > 0 )
			xmlPathText.setText( rootPath );
	}
	
	private void backupRootPath( )
	{
		initRootPath = rootPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#refresh(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void refresh( DataSetDesign dataSetDesign )
	{
		pageDefaultMessage = Messages.getString( "xPathChoosePage.messages.elementSelection.label" );     //$NON-NLS-1$
		if ( XMLInformationHolder.hasDestroyed( ) )
			XMLInformationHolder.start( dataSetDesign );		
		
		refresh( );
		if ( getMessageType( ) == IMessageProvider.NONE )
		{
			setMessage( pageDefaultMessage );
		}
	}
	
	protected void refresh( )
	{
		boolean needsRefresh = false;
		if( !valueEquals( xsdFileName, XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST ) ) )
		{
			xsdFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
			needsRefresh = true;
		}
		if( !valueEquals( xmlFileName, XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST ) ) )
		{
			xmlFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
			needsRefresh = true;
		}
		if( !valueEquals( xmlEncoding, XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST ) ) )
		{
			xmlEncoding = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST );
			needsRefresh = true;
		}

		if ( needsRefresh )
		{
			refreshControls( );
		}
	}

	private void refreshControls( )
	{
		populateXMLTree( );
		backupRootPath( );
		this.availableXmlTree.getSingleButton( ).setEnabled( false );
		setMessage( pageDefaultMessage );
	}
	
	private boolean valueEquals( String value1, String value2 )
	{
		if ( value1 == null )
		{
			return value2 == null;
		}
		return value1.equals( value2 );
	}
	
    /**
	 * 
	 * @param parent
	 * @return
	 */
	public Control createPageControl(Composite parent) 
	{
		pageDefaultMessage = Messages.getString( "wizard.defaultMessage.selectXPath" );   //$NON-NLS-1$
		this.setMessage( pageDefaultMessage );
		Composite composite = new Composite( parent, SWT.NONE );

		FormLayout layout = new FormLayout( );
		composite.setLayout( layout );

		createLeftGroup( composite );
		availableXmlTree.getSingleButton( )
				.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{

				RowMappingDialog dialog = new RowMappingDialog( PlatformUI.getWorkbench( )
						.getDisplay( )
						.getActiveShell( ),
						Messages.getString( "RowMappingDialog.title" ), //$NON-NLS-1$
						selectedItem, selectRadioIndex, rootPath, supportsXMLParameter );
				if ( dialog.open( ) == Window.OK )
				{
					rootPath = dialog.getSelectedPath( );
					selectRadioIndex = dialog.getSelectIndex( );
					xmlPathText.setText( rootPath );
				}
			}
		} );

		createRightGroup( composite );
		return composite;
	}
	
	/**
	 * create left group composite
	 * @param composite2
	 */
	private void createLeftGroup( Composite composite2 )
	{
		availableXmlTree = new XMLTreeViewer( composite2, false );
		availableXmlTree.getSingleButton( ).setToolTipText( Messages.getString( "xPathChoosePage.messages.btnAdd.tooltip" ) ); //$NON-NLS-1$
		availableXmlTree.getTree( )
				.addSelectionListener( new SelectionAdapter( ) {

					public void widgetSelected( SelectionEvent e )
					{
						TreeItem items[] = availableXmlTree.getTree( )
								.getSelection( );
						selectedItem = null;
						for ( int i = 0; i < items.length; i++ )
						{
							selectedItem = items[0];
							if ( items[i].getGrayed( ) )
							{
								availableXmlTree.getTree( ).setRedraw( false );
								availableXmlTree.getTree( ).deselectAll( );
								availableXmlTree.getTree( ).setRedraw( true );
								availableXmlTree.getTree( ).redraw( );
							}
						}
						if ( selectedItem != null )
							availableXmlTree.getSingleButton( )
									.setEnabled( true );
						else
							availableXmlTree.getSingleButton( )
									.setEnabled( false );
					}
				} );
	}
	
	/**
	 * create right group composite
	 * @param composite2
	 */
	private void createRightGroup( Composite composite2 )
	{
		FormData data = new FormData( );
		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( availableXmlTree.getBtnComposite( ), 5 );
		data.right = new FormAttachment( 100, -5 );
		data.bottom = new FormAttachment( 100, -5 );
		rightGroup = new Group( composite2, SWT.NONE );

		rightGroup.setLayout( new FormLayout( ) );
		rightGroup.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.label" ) );  //$NON-NLS-1$
		rightGroup.setLayoutData( data );

		data = new FormData( );
		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 100, -5 );

		final Label label = new Label( rightGroup, SWT.NONE );
		label.setText( Messages.getString( "xPathChoosePage.messages.xPathExpression" ) );  //$NON-NLS-1$
		label.setLayoutData( data );

		data = new FormData( );
		data.top = new FormAttachment( 0, 25 );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 100, -5 );
		xmlPathText = new StyledText( rightGroup, SWT.BORDER );
		xmlPathText.setLayoutData( data );
		xmlPathText.setOrientation( SWT.LEFT_TO_RIGHT );
		xmlPathText.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				rootPath = xmlPathText.getText( ).trim( );
				updatePageStatus( );
			}
		} );

		if ( supportsXMLParameter )
		{
			createQuickFixMenu( );

			xmlPathText.addMenuDetectListener( new MenuDetectListener( ) {

				public void menuDetected( MenuDetectEvent event )
				{
					quickFixMenu.setLocation( event.x, event.y );
					quickFixMenu.setVisible( true );

					updateMenuItemStatus( xmlPathText );
				}
			} );
		}

	}

	private void createQuickFixMenu( )
	{
		quickFixMenu = new Menu( xmlPathText );
		MenuItem createItem = new MenuItem( quickFixMenu, SWT.PUSH );
		createItem.setText( Messages.getString( "ColumnMappingDialog.MenuItem.CreateParameter" ) ); //$NON-NLS-1$
		createItem.addSelectionListener( new SelectionListener( ) {

			public void widgetSelected( SelectionEvent event )
			{
				createXMLParameter( xmlPathText );
			}

			public void widgetDefaultSelected( SelectionEvent event )
			{

			}
		} );

		MenuItem deleteItem = new MenuItem( quickFixMenu, SWT.PUSH );
		deleteItem.setText( Messages.getString( "ColumnMappingDialog.MenuItem.DeleteParameter" ) ); //$NON-NLS-1$
		deleteItem.addSelectionListener( new SelectionListener( ) {

			public void widgetSelected( SelectionEvent event )
			{
				deleteXMLParameter( xmlPathText );
			}

			public void widgetDefaultSelected( SelectionEvent event )
			{

			}
		} );

		updateMenuItemStatus( xmlPathText );

	}

	private void updateMenuItemStatus( StyledText text )
	{
		String selectionText = text.getSelectionText( ).trim( );

		boolean deleteEnabled = selectionText.length( ) > 0
				&& selectionText.startsWith( Constants.CONST_PARAMETER_START )
				&& selectionText.endsWith( Constants.CONST_PARAMETER_END );

		quickFixMenu.getItem( 0 ).setEnabled( selectionText.length( ) > 0
				&& !deleteEnabled );
		quickFixMenu.getItem( 1 ).setEnabled( deleteEnabled );

	}

	private void createXMLParameter( StyledText text )
	{
		String selectedValue = text.getSelectionText( );
		String changedValue = Constants.CONST_PARAMETER_START
				+ selectedValue + Constants.CONST_PARAMETER_END;
		resetXPathText( text, changedValue );
	}

	private void resetXPathText( StyledText text, String changedValue )
	{
		String xpathString = text.getText( ).trim( );
		String result = xpathString.substring( 0, text.getSelection( ).x )
				+ changedValue + xpathString.substring( text.getSelection( ).y );
		text.setText( result );
	}

	private void deleteXMLParameter( StyledText text )
	{
		String selectedValue = text.getSelectionText( );
		String changedValue = selectedValue.substring( 2,
				selectedValue.length( ) - 2 );

		resetXPathText( text, changedValue );
	}

	/**
	 * populate xml tree
	 * 
	 */
	private void populateXMLTree( )
	{
		try
		{
			this.availableXmlTree.getTree( ).removeAll( );
			if ( ( xsdFileName == null || xsdFileName.trim( ).length( ) == 0 )
					&& ( xmlFileName == null || xmlFileName.trim( ).length( ) == 0 ) )
			{
				return;
			}

			int numberOfElement = 0;
			Preferences preferences = UiPlugin.getDefault( )
					.getPluginPreferences( );
			if ( preferences.contains( DataSetPreferencePage.USER_MAX_NUM_OF_ELEMENT_PASSED ) )
			{
				numberOfElement = preferences.getInt( DataSetPreferencePage.USER_MAX_NUM_OF_ELEMENT_PASSED );
			}
			else
			{
				numberOfElement = DataSetPreferencePage.DEFAULT_MAX_NUM_OF_ELEMENT_PARSED;
				preferences.setValue( DataSetPreferencePage.USER_MAX_NUM_OF_ELEMENT_PASSED,
						numberOfElement );
			}
			// TODO for migrate into ODA3.0, the relative path is not
			// supported
			// Object url = this.dataSetHandle.getModuleHandle(
			// ).findResource( fileName,IResourceLocator.LIBRARY );
			//				
			// if( url != null )
			treeNode = SchemaPopulationUtil.getSchemaTree( xsdFileName,
					xmlFileName,
					xmlEncoding,
					numberOfElement,
					DesignSessionUtil.createRuntimeResourceIdentifiers(getHostResourceIdentifiers()));
			if ( treeNode == null
					|| treeNode.getChildren( ) == null
					|| treeNode.getChildren( ).length == 0 )
			{
				OdaException ex = new OdaException( Messages.getString( "dataset.error.populateXMLTree" ) ); //$NON-NLS-1$
				ExceptionHandler.showException( getShell( ),
						Messages.getString( "error.label" ), //$NON-NLS-1$
						ex.getMessage( ),
						ex );
				this.setErrorMessage( Messages.getString( "dataset.error.CannotPopulateXMLTree" ) ); //$NON-NLS-1$
				setPageComplete( false );
			}
			else
			{
				availableXmlTree.populateTree( treeNode, this.rootPath, false, false );
			}
		}
		catch ( Exception e )
		{
			ExceptionHandler.showException( getShell( ),
					Messages.getString( "error.label" ),  //$NON-NLS-1$
					e.getMessage( ),
					e );
			this.setErrorMessage( Messages.getString( "dataset.error.CannotPopulateXMLTree" ) ); //$NON-NLS-1$
			setPageComplete( false );
		}
	}

	/**
	 * set page status based on row number
	 * 
	 */
	private void updatePageStatus( )
	{
		if ( ( xsdFileName == null || xsdFileName.trim( ).length( ) == 0 )
				&& ( xmlFileName == null || xmlFileName.trim( ).length( ) == 0 ) )
		{
			this.setErrorMessage( Messages.getString( "xPathChoosePage.messages.noURLOfSourceOrSchema" ) ); //$NON-NLS-1$
			setPageComplete( false );
		}
		else if ( !isRootPathValid( ) )
		{
			this.setMessage( Messages.getFormattedString( "error.invalidXpath", //$NON-NLS-1$
					new Object[]{
						rootPath == null ? EMPTY_STRING : rootPath
					} ), IMessageProvider.ERROR );
			setPageComplete( false );
		}
		else
		{
			if ( initRootPath != null
					&& !initRootPath.equals( EMPTY_STRING )
					&& !initRootPath.equals( rootPath ) )
			{
				setMessage( Messages.getString( "xPathChoosePage.messages.xpathChange" ), //$NON-NLS-1$
						INFORMATION );
			}
			else
			{
				setMessage( pageDefaultMessage );
			}
			setPageComplete( true );
		}
	}

	private boolean isRootPathValid( )
	{
		return rootPath != null && rootPath.trim( ).length( ) > 0;
	}

	/**
	 * when XPath text has changed, reset the dataSetHandle.CONST_PROP_XPATH
	 * 
	 */
	private void resetXPathPropInHandle( String pathStr )
	{
		if ( pathStr != null && pathStr.trim( ).length( ) > 0 )
		{
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_XPATH,
					pathStr );
			// The relation information should be changed
			if ( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_TABLE_NAME ) != null )
			{
				String relationInfo = (String) XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION );

				if ( relationInfo == null
						|| relationInfo.trim( ).length( ) == 0 )
					return;
				String tableName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_TABLE_NAME );
				String infoStr = XMLRelationInfoUtil.replaceXpathExpression( tableName,
						relationInfo,
						pathStr );

				XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
						infoStr );
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizardPage#getNextPage()
	 */
	public IWizardPage getNextPage( )
	{
		if ( isValid( ) )
		{
			IWizardPage page = super.getNextPage( );
			if ( page instanceof ColumnMappingPage )
			{
				( (ColumnMappingPage) page ).refresh( );
			}
			return page;
		}
		else
			return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage( )
	{
		if ( rootPath == null || rootPath.trim( ).length( ) == 0 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
    
	/**
	 * the xpath expression is valid or not
	 * @return
	 */
	private boolean isValid( )
	{
		if ( this.getControl( ) == null || this.getControl( ).isDisposed( ) )
			return true;
		
		if ( !isRootPathValid( ) )
		{
			this.setMessage( Messages.getString( "error.invalidXpath" ), IMessageProvider.ERROR ); //$NON-NLS-1$
			return false;
		}
		else
		{
			resetXPathPropInHandle( rootPath );
			return true;
		}
	}
	
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
    protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		try
		{
			resetXPathPropInHandle( rootPath );
			savePage( design );
		}
		catch ( OdaException e )
		{
		}
		return design;
	}
    
	/**
     * Updates the given dataSetDesign with the query and its metadata defined
     * in this page.
     * 
     * @param dataSetDesign
     * @throws OdaException 
     */
    private void savePage( DataSetDesign dataSetDesign ) throws OdaException
	{
    	if( XMLInformationHolder.hasDestroyed( ) )
    		return;
		if ( dataSetDesign != null
				&& getQueryText( dataSetDesign ) != null
				&& !getQueryText( dataSetDesign )
						.equals( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) ) )
		{
			setQueryText( dataSetDesign,
					XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
			updateDesign( dataSetDesign );
		}
	}
    
	protected void updateDesign( DataSetDesign dataSetDesign )
	{
		DataSetDesignPopulator.populateMetaData( dataSetDesign );
	}

	protected String getQueryText( DataSetDesign dataSetDesign )
	{
		return dataSetDesign.getQueryText( );
	}

	protected void setQueryText( DataSetDesign dataSetDesign, String queryText )
	{
		dataSetDesign.setQueryText( queryText );
	}
    
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#canLeave()
	 */
    protected boolean canLeave( )
	{
		return isValid( );
	}

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	public void setVisible( boolean visible )
	{
		super.setVisible( visible );
		getControl( ).setFocus( );
	}
	
	/*
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#cleanup()
	 */
    protected void cleanup()
    {
    	XMLInformationHolder.destory( );
    }	

}
