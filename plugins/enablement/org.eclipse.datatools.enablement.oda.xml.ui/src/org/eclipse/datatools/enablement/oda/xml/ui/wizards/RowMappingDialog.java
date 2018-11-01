/*******************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.List;

import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;

/**
 * This dialog provide the path hints to allow user to select the xPath
 * expression.
 * 
 */
public class RowMappingDialog extends TrayDialog
{

	private String title;
	private List pathList;
	private String selectStr;
	private Button absolutePathButton;
	private Button anyLocationButton;
	private Button customButton;
	private StyledCCombo xmlPathField;
	private TreeItem selectedItem;
	private Menu quickFixMenu;

	private String rootPath;
	private int selectRadioIndex;
	private static final String PATH_SEPERATOR = "/";  //$NON-NLS-1$
	
	private boolean supportsXMLParameter;

	/**
	 * 
	 * @param parent
	 * @param title
	 * @param selectedItem
	 * @param selectRadioIndex
	 * @param selectStr
	 */
	public RowMappingDialog( Shell parent, String title, TreeItem selectedItem,
			int selectRadioIndex, String selectStr, boolean supportsXMLParameter )
	{
		super( parent );
		this.title = title;
		this.selectedItem = selectedItem;
		this.pathList = this.getSelectedXPathList( );
		this.selectRadioIndex = selectRadioIndex;
		this.selectStr = selectStr;
		this.supportsXMLParameter = supportsXMLParameter;
	}

	/*
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea( Composite parent )
	{
		getShell( ).setText( title );
		Composite panel = (Composite) super.createDialogArea( parent );
		Label label = new Label( panel, SWT.NONE );
		label.setText( Messages.getString( "RowMappingDialog.info" ) ); //$NON-NLS-1$

		GridLayout layout = new GridLayout( );
		layout.numColumns = 2;
		GridData data = new GridData( GridData.FILL_BOTH );
		data.heightHint = 250;
		Group composite = new Group( panel, SWT.NONE );
		composite.setLayout( layout );
		composite.setLayoutData( data );
		
		GridData buttonGd = new GridData( GridData.FILL_HORIZONTAL );
		buttonGd.verticalAlignment = SWT.BEGINNING;
		buttonGd.verticalIndent = 5;
		buttonGd.horizontalSpan = 2;
	
		absolutePathButton = new Button( composite, SWT.RADIO | SWT.WRAP );
		absolutePathButton.setLayoutData( buttonGd );
		anyLocationButton = new Button( composite, SWT.RADIO | SWT.WRAP );
		anyLocationButton.setLayoutData( buttonGd );
		customButton = new Button( composite, SWT.RADIO | SWT.WRAP );
		customButton.setLayoutData( buttonGd );
		customButton.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.item.custom" ) );  //$NON-NLS-1$

		Label dummy = new Label( composite, SWT.NONE );
		GridData labelGd = new GridData( );
		labelGd.verticalIndent = 5;
		labelGd.widthHint = 10;
		dummy.setLayoutData( labelGd );
		
		GridData txtGridData = new GridData( GridData.FILL_HORIZONTAL  );
		xmlPathField = new StyledCCombo( composite, SWT.DROP_DOWN
				| SWT.BORDER );
		xmlPathField.setLayoutData( txtGridData );
		xmlPathField.setVisible( true );
		for ( int i = 0; i < pathList.size( ); i++ )
		{
			xmlPathField.setText( TextProcessor.process( pathList.get( i ).toString( ), "//") );
			xmlPathField.add( TextProcessor.process( pathList.get( i ).toString( ), "//" ));
		}
		
		if ( supportsXMLParameter )
		{
			createQuickFixMenu( xmlPathField.getStyledText( ) );
			xmlPathField.getStyledText( )
					.addMenuDetectListener( new MenuDetectListener( ) {

						public void menuDetected( MenuDetectEvent event )
						{
							quickFixMenu.setLocation( event.x, event.y );
							quickFixMenu.setVisible( true );

							updateMenuItemStatus( xmlPathField.getStyledText( ) );
						}
					} );
		}

		setButtonTextAndListeners( composite );

		XMLRelationInfoUtil.setSystemHelp( composite,
				IHelpConstants.CONEXT_ID_DATASET_XML_XPATH_EXPRESSION );
		if ( this.selectRadioIndex == 1 )
		{
			absolutePathButton.setSelection( true );
			xmlPathField.setEnabled( false );
			if( pathList.size() > 0 )
			{
				xmlPathField.setText(TextProcessor.process( pathList.get( 0 ).toString( ), "//"));
			}
		}
		else if ( this.selectRadioIndex == 2 )
		{
			anyLocationButton.setSelection( true );
			xmlPathField.setEnabled( false );
			if( pathList.size() > 1 )
			{
				xmlPathField.setText(TextProcessor.process( pathList.get( 1 ).toString( ), "//"));
			}
		}
		else
		{
			customButton.setSelection( true );
			xmlPathField.setText( TextProcessor.process( this.selectStr, "//" ));
		}
    	return composite;
	}
	
	private void createQuickFixMenu( final StyledText text )
	{
		quickFixMenu = new Menu( text );
		MenuItem createItem = new MenuItem( quickFixMenu, SWT.PUSH );
		createItem.setText( Messages.getString( "ColumnMappingDialog.MenuItem.CreateParameter" ) ); //$NON-NLS-1$
		createItem.addSelectionListener( new SelectionListener( ) {

			public void widgetSelected( SelectionEvent event )
			{
				createXMLParameter( text );
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
				deleteXMLParameter( text );
			}

			public void widgetDefaultSelected( SelectionEvent event )
			{

			}
		} );

		updateMenuItemStatus( text );

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

	protected boolean isResizable( )
	{
		return true;
	}

	
	/**
	 * 
	 */
	private void setButtonTextAndListeners( Composite composite )
	{
		if ( this.selectedItem != null )
		{
			if ( pathList.size( ) < 2 )
			{
				return;
			}
			resetButtonsAndTextValues( true );
		}
		else
		{
			resetButtonsAndTextValues( false );
		}
		absolutePathButton.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				doSelectAbsolutePathButton( );
			}
		} );
		anyLocationButton.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				doSelectAnyLocationButton( );
			}
		} );
		customButton.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( customButton.getSelection( ) )
				{
					selectRadioIndex = 3;
					xmlPathField.setEnabled( true );
				}
			}
		} );
		composite.layout( );
	}

	/**
	 * Reset the rootPath value after selecting the "Absolute Path" button
	 * 
	 */
	private void doSelectAbsolutePathButton( )
	{
		if ( absolutePathButton.getSelection( )
				&& ( pathList != null && pathList.size( ) > 0 ) )

		{
			selectRadioIndex = 1;
			rootPath = pathList.get( 0 ).toString( );
			xmlPathField.setEnabled( false );
			xmlPathField.setText( TextProcessor.process( rootPath, "//"));
		}
	}

	/**
	 * Reset the rootPath value after selecting the "Any Location Path" button
	 * 
	 */
	private void doSelectAnyLocationButton( )
	{
		if ( anyLocationButton.getSelection( )
				&& ( pathList != null && pathList.size( ) > 1 ) )
		{
			selectRadioIndex = 2;
			rootPath = pathList.get( 1 ).toString( );
			xmlPathField.setEnabled( false );
			xmlPathField.setText( TextProcessor.process( rootPath, "//"));
		}
	}
	
	/**
	 * 
	 */
	private void resetButtonsAndTextValues( boolean visible )
	{
		if ( visible )
		{
			absolutePathButton.setText( Messages.getFormattedString( "xPathChoosePage.messages.elementSelection.item.absolutePath", //$NON-NLS-1$
					new String[]{
							selectedItem.getText( )
					} ) );
			anyLocationButton.setText( Messages.getFormattedString( "xPathChoosePage.messages.elementSelection.item.anyLocation", //$NON-NLS-1$
					new String[]{
							selectedItem.getText( )
					} ) );
		}
		else
		{
			absolutePathButton.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.absolutePath" ) ); //$NON-NLS-1$
			anyLocationButton.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.anyLocation" ) ); //$NON-NLS-1$
		}
		absolutePathButton.setEnabled( visible );
		anyLocationButton.setEnabled( visible );
	}
	
	/**
	 * get the standby XPath expression
	 * 
	 * @return
	 */
	private List getSelectedXPathList( )
	{
		String path;
		if ( this.selectedItem == null )
		{
			path = PATH_SEPERATOR + rootPath;
		}
		else
		{
			path = getRootPath( );
		}
		return XPathPopulationUtil.getPathList( path );
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	protected void buttonPressed( int buttonId )
	{
		if ( buttonId == IDialogConstants.OK_ID )
		{
			if ( this.absolutePathButton.getSelection( ) )
			{
				this.selectStr = this.pathList.get( 0 ).toString( );
			}
			else if ( this.anyLocationButton.getSelection( ) )
			{
				this.selectStr = this.pathList.get( 1 ).toString( );
			}
			else
				this.selectStr = xmlPathField.getText( );
		}
		super.buttonPressed( buttonId );
	}


	/**
	 * @return root path string
	 */
	private String getRootPath( )
	{
		TreeItem selected = this.selectedItem;

		if ( selected.getData( ) instanceof TreeNodeData )
		{
			ATreeNode node = ( (TreeNodeData) selected.getData( ) ).getTreeNode( );
			if ( node.getType( ) == ATreeNode.ATTRIBUTE_TYPE )
			{
				return null;
			}
			else
			{
				rootPath = PATH_SEPERATOR + selected.getText( );
			}
		}

		while ( selected.getParentItem( ) != null )
		{
			selected = selected.getParentItem( );
			if ( selected.getData( ) instanceof TreeNodeData )
			{
				ATreeNode node = ( (TreeNodeData) selected.getData( ) ).getTreeNode( );
				if ( node.getType( ) == ATreeNode.ELEMENT_TYPE )
				{
					rootPath = PATH_SEPERATOR + selected.getText( ) + rootPath;
				}
			}
		}
		return rootPath;
	}
	
	String getSelectedPath( )
	{
		return selectStr;
	}
	
	int getSelectIndex( )
	{
		return this.selectRadioIndex;
	}

}
