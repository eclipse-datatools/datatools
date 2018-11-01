/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.preferences;

import java.text.MessageFormat;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.RDBCorePluginConstants;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference for the Output tab in the Data perspective
 * @author Quy V. On, quyon@us.ibm.com
 */
public class OutputPreference extends PreferencePage
        implements IWorkbenchPreferencePage, ModifyListener
{ 	
    private static final String DESCRIPTION = 
            ResourceLoader.getResourceLoader().queryString(
            "DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.DESCRIPTION"); //$NON-NLS-1$
    private static final String LIMIT_ROWS = 
            ResourceLoader.getResourceLoader().queryString(
            "DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.LIMIT_ROWS_RETRIEVED"); //$NON-NLS-1$
    private static final String MAX_ROWS = 
            ResourceLoader.getResourceLoader().queryString(
            "DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.MAX_ROW_RETRIEVED"); //$NON-NLS-1$
    private static final String MAX_LOB_LENGTH = 
            ResourceLoader.getResourceLoader().queryString(
            "DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.MAX_LOB_LENGTH"); //$NON-NLS-1$
    private static final String ERROR_INVALID_INT = 
            ResourceLoader.getResourceLoader().queryString(
            "DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.ERROR_INVALID_INT");    //$NON-NLS-1$
    
    protected Button bLimitRows;
    protected Text tMaxRows;
    protected Text tMaxLobLength;
        
    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(Composite)
     */
    protected Control createContents(Composite arg0)
    {
    	Composite cmp = new Composite(arg0, SWT.NONE);
        int INDENT = 25;
        GridLayout gl = new GridLayout();
        gl.numColumns = 2;
        cmp.setLayout(gl);        
        
        GridData gd01 = new GridData();
        gd01.horizontalSpan=2;
        gd01.horizontalAlignment = GridData.FILL;
        Label lblDescr = new Label(cmp, SWT.WRAP);
        lblDescr.setText(DESCRIPTION);
        lblDescr.setLayoutData(gd01);
        
        GridData gd02 = new GridData();
        gd02.horizontalSpan =2;
        Label placeholder = new Label(cmp, SWT.NONE);
        placeholder.setText(""); //$NON-NLS-1$
        placeholder.setLayoutData(gd02);
        
        GridData gd11 = new GridData();
        gd11.horizontalSpan=2;
        bLimitRows = new Button(cmp, SWT.CHECK);
        bLimitRows.setText(LIMIT_ROWS);
        bLimitRows.setLayoutData(gd11);
        
        GridData gd12 = new GridData();
        gd12.horizontalIndent = INDENT;
        Label lMaxRows = new Label(cmp, SWT.NONE);
        lMaxRows.setText(MAX_ROWS);
        lMaxRows.setLayoutData(gd12);
        
        GridData gd13 = new GridData();
        gd13.horizontalAlignment = GridData.FILL;
        gd13.grabExcessHorizontalSpace = true;
        gd13.widthHint=50;
        tMaxRows = new Text(cmp, SWT.BORDER);
        tMaxRows.setLayoutData(gd13);
        
        // create an empty row
        GridData gd14 = new GridData();
        gd14.horizontalSpan = 2;
        Label lblFill1 = new Label(cmp, SWT.NONE);
        lblFill1.setText(" "); //$NON-NLS-1$
        lblFill1.setLayoutData(gd14);
        
        GridData gd22 = new GridData();
        Label lMaxLobLength = new Label(cmp, SWT.NONE);
        lMaxLobLength.setText(MAX_LOB_LENGTH);
        lMaxLobLength.setLayoutData(gd22);
        
        GridData gd23 = new GridData();
        gd23.horizontalAlignment = GridData.FILL;
        gd23.widthHint=50;
        gd23.grabExcessHorizontalSpace = true;
        tMaxLobLength = new Text(cmp, SWT.BORDER);
        tMaxLobLength.setLayoutData(gd23);
        
        // set values from preferenceStore
        bLimitRows.setSelection(RDBCorePlugin.getDefault().getPluginPreferences().getBoolean(
                RDBCorePluginConstants.LIMIT_ROWS_RETRIEVED));
        // enable and disable max rows as appropriate
        bLimitRows.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent e)
            {
                Object source = e.getSource();
                if (source == bLimitRows)
                {
                	tMaxRows.setEnabled(bLimitRows.getSelection());
                    if (!bLimitRows.getSelection())
                    {
                    	// no limit on rows returned, only need to wory
                        // about lob length
                        setValid(isValueValid(tMaxLobLength.getText()));
                    }
                    else
                    {
                    	// both fields need to be valid
                        setValid(isValueValid(tMaxLobLength.getText()) &&
                                isValueValid(tMaxRows.getText()));  
                    }
                }
            }
            });
        
        // check for validity of max row values
        tMaxRows.addModifyListener(this);
        tMaxLobLength.addModifyListener(this);
        
        tMaxRows.setText(Integer.toString(RDBCorePlugin.getDefault().getPluginPreferences().getInt(
                RDBCorePluginConstants.MAX_ROW_RETRIEVED)));
        tMaxLobLength.setText(Integer.toString(RDBCorePlugin.getDefault().getPluginPreferences().getInt(
        		RDBCorePluginConstants.MAX_LOB_LENGTH)));
        
        tMaxRows.setEnabled(bLimitRows.getSelection());        
        
        return cmp;
        
    }
    
    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(IWorkbench)
     */
    public void init(IWorkbench workbench) {     
    }
    
    /**
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    protected void performDefaults()
    {
        bLimitRows.setSelection(RDBCorePlugin.getDefault().getPluginPreferences().getDefaultBoolean(
                RDBCorePluginConstants.LIMIT_ROWS_RETRIEVED));
        tMaxRows.setText(Integer.toString(RDBCorePlugin.getDefault().getPluginPreferences().getDefaultInt(
                RDBCorePluginConstants.MAX_ROW_RETRIEVED)));
        tMaxLobLength.setText(Integer.toString(RDBCorePlugin.getDefault().getPluginPreferences().getDefaultInt(
                RDBCorePluginConstants.MAX_LOB_LENGTH)));
        tMaxRows.setEnabled(bLimitRows.getSelection());
    }
    
    /**
     * @see org.eclipse.jface.preference.IPreferencePage#performOk()
     */
    public boolean performOk()
    {
        storePreferences();
        return super.performOk();   
    }
    
    /**
     * @see org.eclipse.jface.preference.PreferencePage#performApply()
     */
    protected void performApply()
    {
        storePreferences();
        super.performApply();
    }
    
    /**
     * Calls when text is modified
     * @param event the ModifyEvent
     */
    public void modifyText(ModifyEvent event)
    {
    	Object source = event.getSource();
        if (source == tMaxRows)
        {
        	String numberString = tMaxRows.getText();
            setValid(isValueValid(numberString) &&
                    isValueValid(tMaxLobLength.getText()));
        }
        else if (source == tMaxLobLength)
        {
        	String numberString = tMaxLobLength.getText();
            if (bLimitRows.getSelection())
            {
            	// both fields need to be valid
                setValid(isValueValid(numberString) &&
                        isValueValid(tMaxRows.getText()));
            }
            else
            {
            	// only lob needs to be valid
                setValid(isValueValid(numberString));
            }
        }
    }
    
    /**
     * Checks whether or not the value entered is a valid integer value
     * @param aNumberString the value as a String object
     * @return true if value is valid, false if not
     */
    private boolean isValueValid(String aNumberString)
    {
    	Object[] obj = new Object[]{new Integer(1), new Integer(Integer.MAX_VALUE)};
        boolean valid = false;
        try
        {
        	int number = Integer.valueOf(aNumberString).intValue();            
            if (number < 1 || number > Integer.MAX_VALUE)
            {
            	String errorMessage = MessageFormat.format(ERROR_INVALID_INT, obj);
                setErrorMessage(errorMessage);                                
            }
            else
            {
            	setErrorMessage(null);  
                valid = true;
            }
        }
        catch (NumberFormatException ex)
        {
            String errorMessage = MessageFormat.format(ERROR_INVALID_INT, obj);
            setErrorMessage(errorMessage);            
        }
        return valid;
    }
    
    /**
     * Stores user preferences to PreferenceStore
     */
    private void storePreferences()
    {
    	// Whether or not to limit rows retrieved
        RDBCorePlugin.getDefault().getPluginPreferences().setValue(RDBCorePluginConstants.LIMIT_ROWS_RETRIEVED,
                bLimitRows.getSelection());
        // Max rows retrieved
        int pint;
        pint = RDBCorePlugin.getDefault().getPluginPreferences().getDefaultInt(RDBCorePluginConstants.MAX_ROW_RETRIEVED);
        try
        {
        	pint = Integer.parseInt(tMaxRows.getText());
        }
        catch (Exception ex)
        {
        }
        RDBCorePlugin.getDefault().getPluginPreferences().setValue(RDBCorePluginConstants.MAX_ROW_RETRIEVED, pint);
        // Max LOB rows retrieved
        pint = RDBCorePlugin.getDefault().getPluginPreferences().getDefaultInt(RDBCorePluginConstants.MAX_LOB_LENGTH);
        try
        {
        	pint = Integer.parseInt(tMaxLobLength.getText());
        }
        catch (Exception ex)
        {
        }
        RDBCorePlugin.getDefault().getPluginPreferences().setValue(RDBCorePluginConstants.MAX_LOB_LENGTH, pint);
    }
}