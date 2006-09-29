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

import java.util.List;

import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 */

public class RowMappingDialog extends TrayDialog
{

	private String title;
	private CCombo cCombo;
	private List pathList;
	private String selectStr;

	public RowMappingDialog( Shell parent, String title, List list )
	{
		super( parent );
		this.title = title;
		this.pathList = list;
		// TODO Auto-generated constructor stub
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
		layout.numColumns = 1;
		composite.setLayout( layout );
		Label label = new Label( composite, SWT.NONE );
		label.setText( Messages.getString( "RowMappingDialog.info" ) ); //$NON-NLS-1$

		cCombo = new CCombo( composite, SWT.BORDER );

		for ( int i = 0; i < pathList.size( ); i++ )
		{
			cCombo.setText( pathList.get( 0 ).toString( ) );
			cCombo.add( pathList.get( i ).toString( ) );
		}

		selectStr = cCombo.getText( );
		cCombo.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.GRAB_HORIZONTAL ) );
		
		XMLRelationInfoUtil.setSystemHelp( composite,
				IHelpConstants.CONEXT_ID_DATASET_XML_XPATH_EXPRESSION );
		
    	return composite;
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	protected void buttonPressed( int buttonId )
	{
		if ( buttonId == IDialogConstants.OK_ID )
		{
			this.selectStr = cCombo.getText( );
		}
		super.buttonPressed( buttonId );
	}

	String getSelectedPath( )
	{
		return selectStr;
	}

}
