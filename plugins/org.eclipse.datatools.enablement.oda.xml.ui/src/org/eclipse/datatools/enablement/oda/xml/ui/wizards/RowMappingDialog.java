/*******************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.List;

import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
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
	private Label absolutePathLabel;
	private Label anyLocationLabel;
	private Label customPathLabel;
	private Combo xmlPathField;
	private TreeItem selectedItem;

	private String rootPath;
	private int selectRadioIndex;
	private static final String PATH_SEPERATOR = "/";  //$NON-NLS-1$

	/**
	 * 
	 * @param parent
	 * @param title
	 * @param selectedItem
	 * @param selectRadioIndex
	 * @param selectStr
	 */
	public RowMappingDialog( Shell parent, String title, TreeItem selectedItem,
			int selectRadioIndex, String selectStr )
	{
		super( parent );
		this.title = title;
		this.selectedItem = selectedItem;
		this.pathList = this.getSelectedXPathList( );
		this.selectRadioIndex = selectRadioIndex;
		this.selectStr = selectStr;
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
		
		GridData buttonGd = new GridData( );
		buttonGd.verticalAlignment = SWT.BEGINNING;
		buttonGd.verticalIndent = 5;
	
		GridData labelGd = new GridData( GridData.FILL_HORIZONTAL );
		labelGd.verticalIndent = 5;

		absolutePathButton = new Button( composite, SWT.RADIO | SWT.WRAP );
		absolutePathButton.setLayoutData( buttonGd );
		absolutePathLabel = new Label( composite, SWT.WRAP );
		absolutePathLabel.setLayoutData( labelGd );
		anyLocationButton = new Button( composite, SWT.RADIO | SWT.WRAP );
		anyLocationButton.setLayoutData( buttonGd );
		anyLocationLabel = new Label( composite, SWT.WRAP );
		anyLocationLabel.setLayoutData( labelGd );
		customButton = new Button( composite, SWT.RADIO | SWT.WRAP );
		customButton.setLayoutData( buttonGd );
		customPathLabel = new Label( composite, SWT.WRAP );
		customPathLabel.setLayoutData( labelGd );
		customPathLabel.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.item.custom" ) );  //$NON-NLS-1$

		new Label( composite, SWT.NONE );
		GridData txtGridData = new GridData( GridData.FILL_HORIZONTAL  );
		xmlPathField = new Combo( composite, SWT.DROP_DOWN );
		xmlPathField.setLayoutData( txtGridData );
		for ( int i = 0; i < pathList.size( ); i++ )
		{
			xmlPathField.setText( TextProcessor.process( pathList.get( i ).toString( ), "//") );
			xmlPathField.add( TextProcessor.process( pathList.get( i ).toString( ), "//" ));
		}
		
		setLabelValuesAndListeners( composite );

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
	
	
	/**
	 * 
	 */
	private void setLabelValuesAndListeners( Composite composite )
	{
		if ( this.selectedItem != null )
		{
			if ( pathList.size( ) < 2 )
			{
				return;
			}
			resetButtonsAndLabels( true );
		}
		else
		{
			resetButtonsAndLabels( false );
		}
		absolutePathLabel.addListener( SWT.MouseDown, new Listener( ) {

			public void handleEvent( Event event )
			{
				absolutePathButton.setSelection( true );
				anyLocationButton.setSelection( false );
				customButton.setSelection( false );
				doSelectAbsolutePathButton( );
			}
		} );
		anyLocationLabel.addListener( SWT.MouseDown, new Listener(){

			public void handleEvent( Event event )
			{
				anyLocationButton.setSelection( true );
				absolutePathButton.setSelection( false );	
				customButton.setSelection( false );
				doSelectAnyLocationButton( );
			}			
		});
		customPathLabel.addListener( SWT.MouseDown, new Listener( ) {

			public void handleEvent( Event event )
			{
				customButton.setSelection( true );
				xmlPathField.setEnabled( true );
				absolutePathButton.setSelection( false );
				anyLocationButton.setSelection( false );
				selectRadioIndex = 3;
			}
		} );
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
	private void resetButtonsAndLabels( boolean visible )
	{
		if ( visible )
		{
			absolutePathLabel.setText( Messages.getFormattedString( "xPathChoosePage.messages.elementSelection.item.absolutePath", //$NON-NLS-1$
					new String[]{
							selectedItem.getText( )
					} ) );
			anyLocationLabel.setText( Messages.getFormattedString( "xPathChoosePage.messages.elementSelection.item.anyLocation", //$NON-NLS-1$
					new String[]{
							selectedItem.getText( )
					} ) );
		}
		else
		{
			absolutePathLabel.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.absolutePath" ) ); //$NON-NLS-1$
			anyLocationLabel.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.anyLocation" ) ); //$NON-NLS-1$
		}
		absolutePathButton.setEnabled( visible );
		absolutePathLabel.setEnabled( visible );
		anyLocationButton.setEnabled( visible );
		anyLocationLabel.setEnabled( visible );
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
