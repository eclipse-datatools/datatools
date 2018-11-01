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

package org.eclipse.datatools.enablement.oda.xml.ui.preference;

import org.eclipse.datatools.enablement.oda.xml.ui.UiPlugin;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 *  This class represents a preference page that is contributed to the
 * 	Preferences dialog.
 * 	This page is used to modify dataset preview preferences only. They are stored 
 *  in the preference store that belongs to the main plug-in class. 
 *  That way, preferences can be accessed directly via the preference store.
 */
public class DataSetPreferencePage extends PreferencePage
		implements IWorkbenchPreferencePage
{

	private IntegerFieldEditor maxNumberOfElementPassed;
		
	/** default value of max schema number*/
	public static final int DEFAULT_MAX_NUM_OF_ELEMENT_PARSED = 100000;
	
	
	/** max Row preference name */
	public static final String USER_MAX_NUM_OF_ELEMENT_PASSED = "user_max_num_of_element_passed"; //$NON-NLS-1$
	
	private static final int MAX_INTEGER = Integer.MAX_VALUE;
	/*
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents( Composite parent )
	{
		Composite mainComposite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
		mainComposite.setLayout( layout );

		Group columnMappingPageGroup = new Group( mainComposite, SWT.NONE );
		columnMappingPageGroup.setLayout( layout );
        columnMappingPageGroup.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
		columnMappingPageGroup.setText( 
		        Messages.getString( "designer.preview.preference.columnMappingPage.group.title" ) ); //$NON-NLS-1$

		//Set up the maximum number of schemas to be fetched in SQLDataSetPage.
		maxNumberOfElementPassed = new IntegerFieldEditor( USER_MAX_NUM_OF_ELEMENT_PASSED, 
		        Messages.getString( "designer.preview.preference.numberOfElementPassedToGetSchema" ),  //$NON-NLS-1$
		        columnMappingPageGroup ); 
		// adjust the parent layout margin set by the field editor
		GridLayout groupLayout = (GridLayout) columnMappingPageGroup.getLayout();
		groupLayout.marginWidth = 5;
        groupLayout.marginHeight = 5;
		
		maxNumberOfElementPassed.setPage(this);
		maxNumberOfElementPassed.setTextLimit( Integer.toString( MAX_INTEGER ).length( ) );
		
		maxNumberOfElementPassed.setValidateStrategy( StringFieldEditor.VALIDATE_ON_KEY_STROKE );
		maxNumberOfElementPassed.setValidRange(0, MAX_INTEGER);
		
		maxNumberOfElementPassed.setPropertyChangeListener(new IPropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getProperty().equals(FieldEditor.IS_VALID))
                        setValid(maxNumberOfElementPassed.isValid());
            }
        });
		
		maxNumberOfElementPassed.setErrorMessage( Messages.getFormattedString( "designer.preview.preference.numberOfElementPassedToGetSchema.errormessage", //$NON-NLS-1$
				new Object[]{new Integer( MAX_INTEGER )	} ) );
		
		String defaultMaxSchema = UiPlugin.getDefault( )
				.getPluginPreferences( )
				.getString( USER_MAX_NUM_OF_ELEMENT_PASSED );
		if ( defaultMaxSchema == null || defaultMaxSchema.trim( ).length( ) <= 0 )
		{
			defaultMaxSchema = String.valueOf( DEFAULT_MAX_NUM_OF_ELEMENT_PARSED );
		}
		maxNumberOfElementPassed.setStringValue( defaultMaxSchema );
		
		XMLRelationInfoUtil.setSystemHelp( getControl( ),
				IHelpConstants.CONEXT_ID_PREFERENCE_DATASET_XML );
		
		return mainComposite;
	}

	/*
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init( IWorkbench workbench )
	{
		// Do nothing
	}

	/*
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	protected void performDefaults( )
	{
		maxNumberOfElementPassed.setStringValue( String.valueOf( DEFAULT_MAX_NUM_OF_ELEMENT_PARSED ));
	
		super.performDefaults( );
	}

	/*
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	public boolean performOk( )
	{
		UiPlugin.getDefault( )
				.getPluginPreferences( )
				.setValue( USER_MAX_NUM_OF_ELEMENT_PASSED, maxNumberOfElementPassed.getStringValue( ) );
		UiPlugin.getDefault( ).savePluginPreferences( );
		
		return true;
	}
	
}
