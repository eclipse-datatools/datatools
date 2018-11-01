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

import java.util.Arrays;
import java.util.List;

import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
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

	private StyledCCombo xmlPathCombo;
	private StyledText xmlPathText;
	private Menu quickFixMenu;

	private List xpathList;
	private boolean isMappingMode;
	private boolean supportsXMLParameter;

	private static String[] dataTypeDisplayNames = new String[]{
			Messages.getString( "datatypes.dateTime" ), //$NON-NLS-1$
			Messages.getString( "datatypes.decimal" ), //$NON-NLS-1$
			Messages.getString( "datatypes.float" ), //$NON-NLS-1$
			Messages.getString( "datatypes.integer" ), //$NON-NLS-1$
			Messages.getString( "datatypes.date" ), //$NON-NLS-1$
			Messages.getString( "datatypes.time" ), //$NON-NLS-1$
			Messages.getString( "datatypes.string" ), //$NON-NLS-1$
			Messages.getString( "datatypes.boolean" ) //$NON-NLS-1$
	};

	public ColumnMappingDialog( Shell parent, String title,
			String selectedItem, String xpath, int dataType,
			boolean isMappingMode, boolean supportsXMLParameter )
	{
		super( parent );
		initializeDialogInfos( title,
				selectedItem,
				xpath,
				dataType,
				isMappingMode,
				supportsXMLParameter );
	}
	

	private void initializeDialogInfos( String title, String selectedItem,
			String xpath, int dataType, boolean isMappingMode,
			boolean supportsXMLParameter )
	{
		this.title = title;
		this.columnName = ( selectedItem == null ? EMPTY_STRING : selectedItem );
		this.xpath = ( xpath == null ? EMPTY_STRING : xpath );
		this.type = DataTypeUtil.getDataTypeDisplayName( dataType );
		this.isMappingMode = isMappingMode;
		this.supportsXMLParameter = supportsXMLParameter;

		if ( isMappingMode )
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
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets
	 * .Composite)
	 */
	protected Control createDialogArea( Composite parent )
	{
		getShell( ).setText( title );
		Composite panel = (Composite) super.createDialogArea( parent );

		Composite labelComposite = new Composite( panel, SWT.NONE );
		GridLayout labelLayout = new GridLayout( );
		labelLayout.marginLeft = 15;
		labelLayout.marginRight = 10;
		labelLayout.marginBottom = 0;
		labelLayout.marginTop = 0;
		labelComposite.setLayout( labelLayout );

		Label label = new Label( labelComposite, SWT.NONE );
		label.setText( Messages.getString( "ColumnMappingDialog.dialog.info" ) ); //$NON-NLS-1$
		GridData labelGd = new GridData( GridData.FILL_HORIZONTAL );
		label.setLayoutData( labelGd );

		Composite composite = new Composite( panel, SWT.NONE );
		GridData gd = new GridData( GridData.FILL_BOTH );
		composite.setLayoutData( gd );

		GridLayout layout = new GridLayout( );
		composite.setLayout( layout );

		Composite topComposite = new Composite( composite, SWT.NONE );
		GridLayout topLayout = new GridLayout( );
		topLayout.numColumns = 2;
		topLayout.marginWidth = 15;
		topLayout.marginBottom = 5;
		topLayout.verticalSpacing = 8;
		topComposite.setLayout( topLayout );

		GridData gridData = new GridData( GridData.FILL_HORIZONTAL );
		topComposite.setLayoutData( gridData );

		setupTopComposite( topComposite );
		if ( isMappingMode )
		{
			setupButtonComposite( composite );
		}
		else
		{
			setupCustomExprArea( topComposite );
		}

		return composite;
	}

	/**
	 * @param parent
	 * @return
	 */
	private void setupTopComposite( Composite topComposite )
	{
		GridData comboData = new GridData( GridData.FILL_HORIZONTAL );
		comboData.widthHint = 320;

		GridData labelData = new GridData( );
		Label label = new Label( topComposite, SWT.NONE );
		label.setText( Messages.getString( "ColumnMappingDialog.info.columnName" ) ); //$NON-NLS-1$
		label.setLayoutData( labelData );
		labelData.widthHint = label.getSize( ).x > 100 ? label.getSize( ).x
				: 100;

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

		labelData = new GridData( );
		Label label2 = new Label( topComposite, SWT.NONE );
		label2.setText( Messages.getString( "ColumnMappingDialog.info.dataType" ) ); //$NON-NLS-1$
		label2.setLayoutData( labelData );
		labelData.widthHint = label2.getSize( ).x > 100 ? label2.getSize( ).x
				: 100;

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
	private void setupCustomExprArea( final Composite parent )
	{

		GridData labelData = new GridData( );
		Label customLabel = new Label( parent, SWT.WRAP );
		customLabel.setText( Messages.getString( "ColumnMappingDialog.info.xPath" ) ); //$NON-NLS-1$
		customLabel.setLayoutData( labelData );
		GridData textData = new GridData( GridData.FILL_HORIZONTAL );
		textData.widthHint = 320;
		xmlPathText = new StyledText( parent, SWT.BORDER | SWT.SINGLE );
		xmlPathText.setText( TextProcessor.process( xpath, "//" ) ); //$NON-NLS-1$
		xmlPathText.setLayoutData( textData );

		if ( supportsXMLParameter )
		{
			createQuickFixMenu( xmlPathText );

			xmlPathText.addMenuDetectListener( new MenuDetectListener( ) {

				public void menuDetected( MenuDetectEvent event )
				{
					quickFixMenu.setLocation( event.x, event.y );
					quickFixMenu.setVisible( true );

					updateMenuItemStatus( xmlPathText );
				}
			} );
		}

		xmlPathText.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				xpath = xmlPathText.getText( );
			}
		} );

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
		Composite wrap = new Composite( composite, SWT.NONE );
		GridLayout wrapLayout = new GridLayout( );
		wrapLayout.marginLeft = 10;
		wrapLayout.marginRight = 15;
		wrap.setLayout( wrapLayout );
		wrap.setLayoutData(new GridData( GridData.FILL_BOTH ) );

		Group exprBtnGroup = new Group( wrap, SWT.NONE );
		exprBtnGroup.setText( Messages.getString( "ColumnMappingDialog.group.message" ) );

		GridData gridData = new GridData( GridData.FILL_BOTH );
		gridData.heightHint = 200;
		exprBtnGroup.setLayoutData( gridData );

		GridLayout layout = new GridLayout( );
		layout.numColumns = 2;
		layout.marginLeft = 5;
		layout.verticalSpacing = 8;
		exprBtnGroup.setLayout( layout );

		GridData buttonGd = new GridData( GridData.FILL_HORIZONTAL );
		buttonGd.horizontalSpan = 2;

		absolutePathButton = new Button( exprBtnGroup, SWT.RADIO | SWT.WRAP );
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
					xmlPathCombo.setText( TextProcessor.process( xpathList.get( 0 )
							.toString( ),
							"//" ) );
				}
			}
		} );

		anyLocationButton = new Button( exprBtnGroup, SWT.RADIO | SWT.WRAP );
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
					xmlPathCombo.setText( TextProcessor.process( xpathList.get( 1 )
							.toString( ),
							"//" ) );
				}
			}
		} );

		setBtnTextValues( exprBtnGroup );

		customButton = new Button( exprBtnGroup, SWT.RADIO | SWT.WRAP );
		customButton.setLayoutData( buttonGd );
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

		GridData blankButtonData = new GridData( );
		blankButtonData.horizontalSpan = 1;
		Button blankButton = new Button( exprBtnGroup, SWT.NONE );
		blankButton.setData( blankButtonData );
		blankButton.setVisible( false );

		GridData txtGridData = new GridData( GridData.FILL_HORIZONTAL );
		txtGridData.horizontalSpan = 1;
		txtGridData.widthHint = 420;
		xmlPathCombo = new StyledCCombo( exprBtnGroup, SWT.DROP_DOWN | SWT.BORDER );
		xmlPathCombo.setLayoutData( txtGridData );
		xmlPathCombo.setVisible( true );
		setupCustomXMLPathField( );
		
		createQuickFixMenu( xmlPathCombo.getStyledText( ) );

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
		
		if ( supportsXMLParameter )
		{
			xmlPathCombo.getStyledText( )
					.addMenuDetectListener( new MenuDetectListener( ) {

						public void menuDetected( MenuDetectEvent event )
						{
							quickFixMenu.setLocation( event.x, event.y );
							quickFixMenu.setVisible( true );

							updateMenuItemStatus( xmlPathCombo.getStyledText( ) );
						}
					} );
		}

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
	private void setBtnTextValues( Composite composite )
	{
		if ( xpathList == null )
		{
			absolutePathButton.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.absolutePath" ) ); //$NON-NLS-1$
			anyLocationButton.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.anyLocation" ) ); //$NON-NLS-1$
			setButtonsEnabled( false );
			return;
		}

		if ( xpathList.size( ) >= 2 )
		{
			absolutePathButton.setText( Messages.getFormattedString( "xPathChoosePage.messages.elementSelection.disable.absolutePath", //$NON-NLS-1$
					new String[]{
						columnName
					} ) );
			anyLocationButton.setText( Messages.getFormattedString( "xPathChoosePage.messages.elementSelection.item.anyLocation", //$NON-NLS-1$
					new String[]{
						columnName
					} ) );
			setButtonsEnabled( true );
		}
		else
		{
			absolutePathButton.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.absolutePath" ) ); //$NON-NLS-1$
			anyLocationButton.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.anyLocation" ) ); //$NON-NLS-1$
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
		anyLocationButton.setEnabled( enabled );
	}

	/**
	 * Setup custom XML path field
	 */
	private void setupCustomXMLPathField( )
	{
		if ( xpathList != null && xpathList.size( ) >= 2 )
		{
			xmlPathCombo.setText( TextProcessor.process( xpathList.get( 0 )
					.toString( ), "//" ) );
			xmlPathCombo.add( TextProcessor.process( xpathList.get( 0 )
					.toString( ), "//" ) );
			xmlPathCombo.add( TextProcessor.process( xpathList.get( 1 )
					.toString( ), "//" ) );
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
		if ( columnName == null || columnName.trim( ).length( ) == 0 )
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

}
