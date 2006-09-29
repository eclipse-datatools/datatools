/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.Arrays;

import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The dialog to choose or edit the column mapping information
 */

public class ColumnMappingDialog extends TrayDialog
{

	private String title;
	private Combo typeCombo;

	private String columnName;
	private String xpath;
	private String type;
	
	private Text columnNameText ;
	private Text xPathExpressionText;
	
    private static String[] dataTypeDisplayNames = new String[] {
        Messages.getString("datatypes.dateTime"), //$NON-NLS-1$
        Messages.getString("datatypes.decimal"), //$NON-NLS-1$
        Messages.getString("datatypes.float"), //$NON-NLS-1$
        Messages.getString("datatypes.integer"), //$NON-NLS-1$
        Messages.getString("datatypes.date"),
        Messages.getString("datatypes.time"),
        Messages.getString("datatypes.string")        
    };

	public ColumnMappingDialog( Shell parent, String title, String selectedItem, String xpath, int dataType )
	{
		super( parent );
		this.title = title;
		this.columnName = ( selectedItem == null ? "" : selectedItem );
		this.xpath = ( xpath == null ? "" : xpath );
		this.type = DataTypeUtil.getDataTypeDisplayName( dataType );
		Arrays.sort( dataTypeDisplayNames );
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
		layout.numColumns = 4;
		composite.setLayout( layout );
		Label label = new Label( composite, SWT.NONE );
		label.setText( Messages.getString( "ColumnMappingDialog.info.columnName" ) ); //$NON-NLS-1$

		columnNameText = new Text( composite, SWT.BORDER );
		GridData data = new GridData( GridData.FILL_BOTH );
		data.horizontalSpan = 3;
		data.widthHint = 300;
		columnNameText.setLayoutData( data );
		columnNameText.setText( columnName );

		Label label2 = new Label( composite, SWT.NONE );
		label2.setText( Messages.getString( "ColumnMappingDialog.info.xPath" ) ); //$NON-NLS-1$

		data = new GridData( GridData.FILL_BOTH );
		data.horizontalSpan = 3;
		xPathExpressionText = new Text( composite, SWT.BORDER );
		xPathExpressionText.setLayoutData( data );
		xPathExpressionText.setText(xpath);

		Label label3 = new Label( composite, SWT.NONE );
		label3.setText( Messages.getString( "ColumnMappingDialog.info.dataType" ) ); //$NON-NLS-1$
		
		data = new GridData( GridData.FILL_BOTH );
		data.horizontalSpan = 3;
		typeCombo = new Combo( composite, SWT.DROP_DOWN|SWT.READ_ONLY );
		
		int typeIndex = -1;
		int stringIndex = -1;
		
		for ( int i = 0; i < dataTypeDisplayNames.length; i++ )
		{
			typeCombo.add( dataTypeDisplayNames[i] );
			if ( dataTypeDisplayNames[i].equals( this.type ) )
			{
				typeIndex = i;

			}
			else if ( dataTypeDisplayNames[i].equals( Messages.getString( "datatypes.string" ) ) )
			{
				stringIndex = i;
			}
			
		}
		if( typeIndex == -1 )
			typeIndex = stringIndex;
		
		typeCombo.setLayoutData( data );
		
		typeCombo.select( typeIndex );
		
		typeCombo.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				final int index = typeCombo.getSelectionIndex( );
				columnName = typeCombo.getItem( index );
			}
		} );
		
		XMLRelationInfoUtil.setSystemHelp( composite,
				IHelpConstants.CONEXT_ID_DATASET_XML_COLUMNMAPPING_DIALOG );
		
		return composite;
	}

	/**
	 * get the new column mapping element
	 * @return
	 */
	ColumnMappingElement getColumnMapping()
	{
		ColumnMappingElement columnEle = new ColumnMappingElement();
		columnEle.setColumnName( this.columnName);
		columnEle.setXPath( this.xpath );
		columnEle.setType( this.type );
		return columnEle;		
	}
	
    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void buttonPressed(int buttonId) {
        if ( buttonId == IDialogConstants.OK_ID )
		{
			this.columnName = columnNameText.getText( );
			this.xpath = xPathExpressionText.getText( );
			this.type = typeCombo.getItem(typeCombo.getSelectionIndex());
		}
        super.buttonPressed(buttonId);
    }

}
