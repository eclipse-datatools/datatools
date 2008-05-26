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

import java.util.Arrays;
import java.util.List;

import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The dialog to choose or edit the column mapping information
 */

public class ColumnMappingDialog extends TrayDialog
{

	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
    private String title;
	private Combo typeCombo;

	private String columnName;
	private String xpath;
	private String type;

	private Text columnNameText;

	private Button absolutePathButton;
	private Button anyLocationButton;
	private Button customButton;

	private Label absoluteLabel;
	private Label anyLocationLabel;

	private Combo xmlPathCombo;
	private Text xmlPathText;
	private List xpathList;
	private boolean isMappingMode;
	
	private static String[] dataTypeDisplayNames = new String[]{
			Messages.getString( "datatypes.dateTime" ),           //$NON-NLS-1$
			Messages.getString( "datatypes.decimal" ),            //$NON-NLS-1$
			Messages.getString( "datatypes.float" ),              //$NON-NLS-1$
			Messages.getString( "datatypes.integer" ),            //$NON-NLS-1$
			Messages.getString( "datatypes.date" ),               //$NON-NLS-1$
			Messages.getString( "datatypes.time" ),               //$NON-NLS-1$
			Messages.getString( "datatypes.string" ),             //$NON-NLS-1$
			Messages.getString( "datatypes.boolean" )             //$NON-NLS-1$
	};

	public ColumnMappingDialog( Shell parent, String title,
			String selectedItem, String xpath, int dataType, boolean isMappingMode )
	{
		super( parent );
		initializeDialogInfos( title, selectedItem, xpath, dataType, isMappingMode );
	}

	private void initializeDialogInfos( String title, String selectedItem,
			String xpath, int dataType, boolean isMappingMode )
	{
		this.title = title;
		this.columnName = ( selectedItem == null ? EMPTY_STRING : selectedItem );
		this.xpath = ( xpath == null ? EMPTY_STRING : xpath );
		this.type = DataTypeUtil.getDataTypeDisplayName( dataType );
		this.isMappingMode = isMappingMode;
		if( isMappingMode )
		{
			this.xpathList = XPathPopulationUtil.getPathList( xpath );
		}
		else
		{
			this.xpathList = null;
		}
		Arrays.sort( dataTypeDisplayNames );
	}

	protected boolean isResizable( )
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea( Composite parent )
	{
		getShell( ).setText( title );
		Composite composite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( );
		composite.setLayout( layout );

		setupTopComposite( composite );
		if ( isMappingMode )
		{
			setupButtonComposite( composite );
		}
		else
		{
			setupCustomExprArea( composite );
		}

		return composite;
	}

	/**
	 * @param parent
	 * @return
	 */
	private void setupTopComposite( Composite parent )
	{
		Composite topComposite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 2;
		layout.marginWidth = 20;
		layout.marginBottom = 10;
		layout.verticalSpacing = 8;
		topComposite.setLayout( layout );

		GridData gridData = new GridData( );
		topComposite.setLayoutData( gridData );

		GridData comboData = new GridData( );
		comboData.widthHint = 300;

		GridData labelData = new GridData( );
		Label label = new Label( topComposite, SWT.NONE );
		label.setText( Messages.getString( "ColumnMappingDialog.info.columnName" ) ); //$NON-NLS-1$
		label.setLayoutData( labelData );
		labelData.widthHint = label.getSize().x > 100? label.getSize().x:100;

		columnNameText = new Text( topComposite, SWT.BORDER );
		columnNameText.setLayoutData( comboData );
		columnNameText.setText( columnName );
		columnNameText.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				columnName = columnNameText.getText( );
				updateOKbuttonState( );
			}

		} );

		labelData = new GridData();
		Label label2 = new Label( topComposite, SWT.NONE );
		label2.setText( Messages.getString( "ColumnMappingDialog.info.dataType" ) ); //$NON-NLS-1$
		label2.setLayoutData( labelData );
		labelData.widthHint = label2.getSize().x > 100? label2.getSize().x:100;
		
		typeCombo = new Combo( topComposite, SWT.DROP_DOWN | SWT.READ_ONLY );

		int typeIndex = -1;
		int stringIndex = -1;

		for ( int i = 0; i < dataTypeDisplayNames.length; i++ )
		{
			typeCombo.add( dataTypeDisplayNames[i] );
			if ( dataTypeDisplayNames[i].equals( this.type ) )
			{
				typeIndex = i;
			}
			else if ( dataTypeDisplayNames[i].equals( Messages.getString( "datatypes.string" ) ) ) //$NON-NLS-1$
			{
				stringIndex = i;
			}

		}
		if ( typeIndex == -1 )
			typeIndex = stringIndex;

		columnNameText.setLayoutData( comboData );

		typeCombo.setLayoutData( comboData );

		typeCombo.select( typeIndex );

		typeCombo.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				final int index = typeCombo.getSelectionIndex( );
				columnName = typeCombo.getItem( index );
			}
		} );

		XMLRelationInfoUtil.setSystemHelp( topComposite,
				IHelpConstants.CONEXT_ID_DATASET_XML_COLUMNMAPPING_DIALOG );
	}

	/**
	 * if it's edit mode, use this method to create a Text field to let the user
	 * modify the xpath's value
	 * 
	 * @param parent
	 */
	private void setupCustomExprArea( Composite parent )
	{
		Composite customComposite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.marginWidth = 20;
		layout.marginBottom = 10;
		layout.verticalSpacing = 8;
		customComposite.setLayout( layout );

		GridData gridData = new GridData( );
		customComposite.setLayoutData( gridData );

		GridData labelData = new GridData( );
		labelData.horizontalAlignment = 10;
		Label customLabel = new Label( customComposite, SWT.WRAP );
		customLabel.setText( Messages.getString( "ColumnMappingDialog.info.xPath" ) ); //$NON-NLS-1$
		customLabel.setLayoutData( labelData );
		GridData textData = new GridData( );
		textData.widthHint = 300;
		textData.horizontalAlignment = 10;
		xmlPathText = new Text( customComposite, SWT.BORDER );
		xmlPathText.setText( TextProcessor.process( xpath, "//") );
		xmlPathText.setLayoutData( textData );
		xmlPathText.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				xpath = xmlPathText.getText( );
			}
		} );
	}

	/**
	 * get the new column mapping element
	 * 
	 * @return
	 */
	ColumnMappingElement getColumnMapping( )
	{
		ColumnMappingElement columnEle = new ColumnMappingElement( );
		columnEle.setColumnName( this.columnName );
		columnEle.setXPath( this.xpath );
		columnEle.setType( this.type );
		return columnEle;
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	protected void buttonPressed( int buttonId )
	{
		if ( buttonId == IDialogConstants.OK_ID )
		{
			this.columnName = columnNameText.getText( );
			this.type = typeCombo.getItem( typeCombo.getSelectionIndex( ) );
		}
		super.buttonPressed( buttonId );
	}

	/**
	 * Setup the radio button composite
	 * 
	 * @param composite
	 */
	private void setupButtonComposite( Composite composite )
	{
		Composite exprBtnGroup = new Composite( composite, SWT.NONE );

		GridData gridData = new GridData( GridData.FILL_HORIZONTAL );
		exprBtnGroup.setLayoutData( gridData );

		GridLayout layout = new GridLayout( );
		layout.numColumns = 2;
		layout.marginLeft = 20;
		layout.verticalSpacing = 8;
		exprBtnGroup.setLayout( layout );

		GridData buttonGd = new GridData( GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.VERTICAL_ALIGN_BEGINNING );

		GridData labelData = new GridData( GridData.FILL_HORIZONTAL );

		absolutePathButton = new Button( exprBtnGroup, SWT.RADIO );
		absolutePathButton.setLayoutData( buttonGd );
		absolutePathButton.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( absolutePathButton.getSelection( ) )
				{
					xpath = xpathList.get( 0 ).toString( );
					absolutePathButton.setSelection( true );
					anyLocationButton.setSelection( false );
					customButton.setSelection( false );
					xmlPathCombo.setEnabled( false );
					xmlPathCombo.setText( TextProcessor.process(xpathList.get(0).toString(), "//"));
				}
			}
		} );
		
		absoluteLabel = new Label( exprBtnGroup, SWT.WRAP );
		absoluteLabel.setLayoutData( labelData );
		
		absoluteLabel.addListener( SWT.MouseDown, new Listener( ) {

			public void handleEvent( Event event )
			{
				xpath = xpathList.get( 0 ).toString( );
				absolutePathButton.setSelection( true );
				anyLocationButton.setSelection( false );
				customButton.setSelection( false );
				xmlPathCombo.setEnabled( false );
			}
		} );

		anyLocationButton = new Button( exprBtnGroup, SWT.RADIO );
		anyLocationButton.setLayoutData( buttonGd );
		anyLocationButton.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( anyLocationButton.getSelection( ) )
				{
					xpath = xpathList.get( 1 ).toString( );
					anyLocationButton.setSelection( true );
					absolutePathButton.setSelection( false );
					customButton.setSelection( false );
					xmlPathCombo.setEnabled( false );
					xmlPathCombo.setText( TextProcessor.process( xpathList.get(1).toString(), "//"));
				}
			}
		} );
		anyLocationLabel = new Label( exprBtnGroup, SWT.WRAP );
		anyLocationLabel.setLayoutData( labelData );
		anyLocationLabel.addListener( SWT.MouseDown, new Listener( ) {

			public void handleEvent( Event event )
			{
				xpath = xpathList.get( 1 ).toString( );
				anyLocationButton.setSelection( true );
				absolutePathButton.setSelection( false );
				customButton.setSelection( false );
				xmlPathCombo.setEnabled( false );
			}
		} );
		setLabelValues( exprBtnGroup );

		GridData customData = new GridData( GridData.FILL_HORIZONTAL );
		customData.horizontalSpan = 2;
		customData.widthHint = 400;
		customButton = new Button( exprBtnGroup, SWT.RADIO );
		customButton.setLayoutData( customData );
		customButton.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.item.custom" ) ); //$NON-NLS-1$
		customButton.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( customButton.getSelection( ) )
				{
					xmlPathCombo.setEnabled( true );
				}
			}
		} );

		GridData blankButtonGd = new GridData( );
		blankButtonGd.verticalAlignment = SWT.BEGINNING;
		blankButtonGd.horizontalSpan = 1;

		GridData blankButtonData = new GridData( );
		blankButtonData.horizontalSpan = 1;
		Button blankButton = new Button( exprBtnGroup, SWT.NONE );
		blankButton.setData( blankButtonData );
		blankButton.setVisible( false );

		GridData txtGridData = new GridData( );
		txtGridData.horizontalSpan = 1;
		txtGridData.widthHint = 260;
		xmlPathCombo = new Combo( exprBtnGroup, SWT.DROP_DOWN );
		xmlPathCombo.setLayoutData( txtGridData );
		xmlPathCombo.setVisible( true );
		setupCustomXMLPathField( );
		xmlPathCombo.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
			}
		} );
		xmlPathCombo.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				xpath = xmlPathCombo.getText( );
			}
		} );
		
		if ( xpathList != null && xpathList.size( ) > 0 )
		{
			absolutePathButton.setSelection( true );
			xmlPathCombo.setEnabled( false );
		}
		else
		{
			customButton.setSelection( true );
		}
	}

	/**
	 * 
	 */
	private void setLabelValues( Composite composite )
	{
		if ( xpathList == null )
		{
			absoluteLabel.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.absolutePath" ) ); //$NON-NLS-1$
			anyLocationLabel.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.anyLocation" ) ); //$NON-NLS-1$
			setButtonsEnabled( false );
			return;
		}

		if ( xpathList.size( ) >= 2 )
		{
			absoluteLabel.setText( Messages.getFormattedString( "xPathChoosePage.messages.elementSelection.item.absolutePath", //$NON-NLS-1$
					new String[]{
							columnName
					} ));
			anyLocationLabel.setText( Messages.getFormattedString( "xPathChoosePage.messages.elementSelection.item.anyLocation", //$NON-NLS-1$
					new String[]{
							columnName
					} ) );
			setButtonsEnabled( true );
		}
		else
		{
			absoluteLabel.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.absolutePath" ) ); //$NON-NLS-1$
			anyLocationLabel.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.anyLocation" ) ); //$NON-NLS-1$
			setButtonsEnabled( false );
		}
		composite.layout( );
	}

	/**
	 * Enable or disable the buttons
	 * 
	 */
	private void setButtonsEnabled( boolean enabled )
	{
		absolutePathButton.setEnabled( enabled );
		absoluteLabel.setEnabled( enabled );
		anyLocationButton.setEnabled( enabled );
		anyLocationLabel.setEnabled( enabled );
	}

	/**
	 * Setup custom XML path field
	 */
	private void setupCustomXMLPathField( )
	{
		if ( xpathList != null && xpathList.size( ) >= 2 )
		{
			xmlPathCombo.setText( TextProcessor.process(xpathList.get( 0 ).toString( ),"//") );
			xmlPathCombo.add( TextProcessor.process(xpathList.get( 0 ).toString( ),"//") );
			xmlPathCombo.add( TextProcessor.process(xpathList.get( 1 ).toString( ),"//") );
		}
		else
		{
			xmlPathCombo.setText( EMPTY_STRING );
			setButtonsEnabled( false );
			return;
		}
		xpath = xmlPathCombo.getText( );
	}

	/**
	 * Update the OK button's state
	 * 
	 */
	private void updateOKbuttonState( )
	{
		if ( columnName == null
				|| columnName.trim( ).length( ) == 0 )
		{
			enableOKButton( false );
		}
		else
		{
			enableOKButton( true );
		}
	}
	
	/**
	 * Enable or disable the OK button
	 * 
	 * @param enabled
	 */
	private void enableOKButton( boolean enabled )
	{
		Button okBtn = getButton( Window.OK );
		if ( okBtn != null )
		{
			okBtn.setEnabled( enabled );
		}
	}

	protected Control createButtonBar( Composite parent )
	{
		Control control = super.createButtonBar( parent );
		updateOKbuttonState( );
		return control;
	}

}
