/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

/**
 * Root xpath choose page. It expands the xml tree list to choose the preferred
 * root path
 */

public class XPathChoosePage extends DataSetWizardPage
{
    private static String DEFAULT_MESSAGE = Messages.getString( "wizard.defaultMessage.selectXPath" );  //$NON-NLS-1$
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	
	private transient XMLTreeViewer availableXmlTree;
	private transient Text xmlPathText;
	private transient Group rightGroup;
	
	private ATreeNode treeNode;
	private TreeItem selectedItem;
	private String xsdFileName;
	private String xmlFileName;
	private String xmlEncoding;
	private String initRootPath;
	private String rootPath;
	private int selectRadioIndex = 1; // default selection is 3

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
		this.setMessage( DEFAULT_MESSAGE );
		this.setPageComplete( false );
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		setControl( createPageControl( parent ) );
		
		if( XMLInformationHolder.hasDestroyed() )
			XMLInformationHolder.start( this.getInitializationDesign( ) );
		initializeControl( );
		populateXMLTree( );
		
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
		DEFAULT_MESSAGE = Messages.getString( "xPathChoosePage.messages.elementSelection.label" );     //$NON-NLS-1$
		if ( XMLInformationHolder.hasDestroyed( ) )
			XMLInformationHolder.start( dataSetDesign );
		
		refresh( );
	}
	
	protected void refresh( )
	{
		xmlFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
		xsdFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
		xmlEncoding = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST );
		populateXMLTree( );
		backupRootPath( );
		this.availableXmlTree.getSingleButton( ).setEnabled( false );
		setMessage( DEFAULT_MESSAGE );
	}
	
    /**
	 * 
	 * @param parent
	 * @return
	 */
	public Control createPageControl(Composite parent) 
	{
		DEFAULT_MESSAGE = Messages.getString( "wizard.defaultMessage.selectXPath" );   //$NON-NLS-1$
		this.setMessage( DEFAULT_MESSAGE );
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
						selectedItem, selectRadioIndex, rootPath );
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
		availableXmlTree.getTree( ).addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				TreeItem items[] = availableXmlTree.getTree( ).getSelection( );
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
		xmlPathText = new Text( rightGroup, SWT.BORDER );
		xmlPathText.setLayoutData( data );

		xmlPathText.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				rootPath = xmlPathText.getText( );
				setPageStatus( );
			}
		} );
	}


	/**
	 * populate xml tree
	 * 
	 */
	private void populateXMLTree( )
	{
		if ( ( xsdFileName == null || xsdFileName.trim( ).length( ) == 0 )
				&& ( xmlFileName == null || xmlFileName.trim( ).length( ) == 0 ) )
			return;
		
		try
		{
			this.availableXmlTree.getTree( ).removeAll( );
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
					numberOfElement );
			if ( treeNode == null
					|| treeNode.getChildren( ) == null
					|| treeNode.getChildren( ).length == 0 )
			{
				OdaException ex = new OdaException( Messages.getString( "dataset.error.populateXMLTree" ) ); //$NON-NLS-1$
				ExceptionHandler.showException( getShell( ),
						Messages.getString( "error.label" ), //$NON-NLS-1$
						ex.getMessage( ),
						ex );
			}
			else
			{
				availableXmlTree.populateTree( treeNode, this.rootPath, false );
			}
		}
		catch ( Exception e )
		{
			ExceptionHandler.showException( getShell( ),
					Messages.getString( "error.label" ),  //$NON-NLS-1$
					e.getMessage( ),
					e );
		}
	}

	/**
	 * set page status based on row number
	 * 
	 */
	private void setPageStatus( )
	{
		if ( !isRootPathValid( ) )
		{
			setPageComplete( false );
			this.setMessage( Messages.getFormattedString( "error.invalidXpath",       //$NON-NLS-1$
					new Object[]{
						rootPath == null ? EMPTY_STRING : rootPath
					} ), IMessageProvider.ERROR );
		}
		else
		{
			if ( initRootPath != null
					&& !initRootPath.equals( EMPTY_STRING )
					&& !initRootPath.equals( rootPath ) )
			{
				setMessage( Messages.getString( "xPathChoosePage.messages.xpathChange" ),  //$NON-NLS-1$
						INFORMATION );
			}
			else
			{
				setMessage( DEFAULT_MESSAGE );
			}
			setPageComplete( true );
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean isRootPathValid( )
	{
		return !(rootPath == null
				|| rootPath.trim( ).length( ) == 0);
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
			this.setMessage( Messages.getFormattedString( "error.invalidXpath",  //$NON-NLS-1$
					new Object[]{
						rootPath == null ? EMPTY_STRING : rootPath
					} ), IMessageProvider.ERROR );
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
		DataSetDesignPopulator.populateResultSet( dataSetDesign );
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
