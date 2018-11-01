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

package org.eclipse.datatools.sqltools.data.internal.ui;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BidiSegmentEvent;
import org.eclipse.swt.custom.BidiSegmentListener;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;


public class FileFormatWizardPage extends WizardPage
{

    // Widgets
    protected StyledText fileText;
    protected Combo columnDelimiterCombo;
    protected Combo stringDelimiterCombo;
//    protected Combo localeCombo;
    
    // True for data extraction, false for data loading
    protected boolean extract;

    // Delimiters
    protected String endl = System.getProperty("line.separator"); //$NON-NLS-1$
    protected String[][] COL_DELIMS = new String[][] { {Messages.getString("FileFormatWizardPage.Comma"),","}, {Messages.getString("FileFormatWizardPage.Semicolon"),";"},  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            {Messages.getString("FileFormatWizardPage.Space")," "}, {Messages.getString("FileFormatWizardPage.Tab"),"\t"},  {Messages.getString("FileFormatWizardPage.Pipe"),"|"} };   //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
    protected String[][] STRING_DELIMS = new String[][] { {Messages.getString("FileFormatWizardPage.DoubleQuote"), "\""}, {Messages.getString("FileFormatWizardPage.SingleQuote"), "'"}, {Messages.getString("FileFormatWizardPage.None"), ""} }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
    
    // Dialog settings
    protected static final String SETTINGS_SECTION_NAME = "Extract/Load file format wizard page"; //$NON-NLS-1$
    protected static final String SETTING_FILE_PATH = "File path"; //$NON-NLS-1$
    protected static final String SETTING_COL_DELIM = "Column delimiter";     //$NON-NLS-1$
    protected static final String SETTING_STRING_DELIM = "String delimiter"; //$NON-NLS-1$
//    protected static final String SETTING_LOCALE = "Locale";
    
    public FileFormatWizardPage(String pageName, boolean extract)
    {
        super(pageName);
        setTitle(Messages.getString("FileFormatWizardPage.Title")); //$NON-NLS-1$
        this.extract = extract;
    }
    
    public void createControl(Composite parent)
    {
        createControl1(parent);
        
        loadSettings();
        validateInput();
        setErrorMessage(null);        
    }
    
    protected void createControl1(Composite parent)
    {
        Composite c = new Composite(parent, SWT.NONE);
        c.setLayout(new GridLayout(3, false));
        if (getHelpID()!=null)
        	PlatformUI.getWorkbench().getHelpSystem().setHelp(c, getHelpID());
        
        Label l = new Label(c, SWT.NONE);
        if (extract)
            l.setText(Messages.getString("FileFormatWizardPage.OutputFile")); //$NON-NLS-1$
        else 
            l.setText(Messages.getString("FileFormatWizardPage.InputFile")); //$NON-NLS-1$
        
        fileText = new StyledText(c, SWT.SINGLE | SWT.BORDER);
        fileText.addBidiSegmentListener(new BidiSegmentListener() {
        	public void lineGetSegments(BidiSegmentEvent evt) {
        		String separator = System.getProperty("file.separator"); //$NON-NLS-1$
        		ArrayList list = new ArrayList();
        		int beginSearchIndex = 0;
        		boolean done = false;
        		String lineText = evt.lineText;
        		while (lineText != null && !lineText.equals("") && !done)
        		{
        			int index = lineText.indexOf(separator, beginSearchIndex);
        			if (index != -1)
        			{
        				// found file separtor
        				if (index > 0)
        				{
        					list.add(new Integer(index));
        				}
        				beginSearchIndex = index + 1;
        			}
        			else
        			{
        				// no more file separators
        				done = true;
        			}
        		}
        		// create segment based on the number of file separators
        		if (list.size() > 0)
        		{
        			int segments[] = new int[list.size() + 1];
        			segments [0] = 0;
        			for (int i=0;i<list.size();i++)
        			{
        				segments[i + 1] = ((Integer)list.get(i)).intValue();
        			}
        			evt.segments = segments;
        		}
        	}
        });
        fileText.setLayoutData( new GridData(GridData.FILL_HORIZONTAL) );
        fileText.addModifyListener( new ModifyListener () {
            public void modifyText(ModifyEvent e) { validateInput(); }
        });
        
        Button fileButton = new Button(c, SWT.PUSH);
        fileButton.setText(Messages.getString("FileFormatWizardPage.Browse"));   //$NON-NLS-1$
        fileButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) { handleBrowse(); }
            public void widgetDefaultSelected(SelectionEvent e) { handleBrowse(); }
        });
        
        Group g = new Group(c, SWT.NONE);
        GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gd.horizontalSpan = 3;
        g.setLayoutData(gd);
        g.setText(Messages.getString("FileFormatWizardPage.FileFormat")); //$NON-NLS-1$
        g.setLayout(new GridLayout(2, false));
        
        l = new Label(g, SWT.NONE);
        l.setText(Messages.getString("FileFormatWizardPage.ColumnDelimiter")); //$NON-NLS-1$
        
        columnDelimiterCombo = new Combo(g, SWT.BORDER);
        columnDelimiterCombo.setLayoutData( new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.GRAB_HORIZONTAL) );
        initCombo(columnDelimiterCombo, COL_DELIMS);
        columnDelimiterCombo.addModifyListener( new ModifyListener () {
            public void modifyText(ModifyEvent e) { validateInput(); }
        });
        
        l = new Label(g, SWT.NONE);
        l.setText(Messages.getString("FileFormatWizardPage.StringDelimiter")); //$NON-NLS-1$
        
        stringDelimiterCombo = new Combo(g, SWT.BORDER);
        stringDelimiterCombo.setLayoutData( new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.GRAB_HORIZONTAL) );
        initCombo(stringDelimiterCombo, STRING_DELIMS);
        stringDelimiterCombo.addModifyListener( new ModifyListener () {
            public void modifyText(ModifyEvent e) { validateInput(); }
        });
        
//        l = new Label(g, SWT.NONE);
//        l.setText("Locale: ");
        
//        localeCombo = new Combo(g, SWT.BORDER);
//        localeCombo.setLayoutData( new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.GRAB_HORIZONTAL) );
//        localeCombo.addModifyListener( new ModifyListener () {
//            public void modifyText(ModifyEvent e) { validateInput(); }
//        });
        
        setControl(c);
    }
    
    protected void validateInput()
    {
        File f = new File(fileText.getText());
		if (!extract && !f.isFile()) {
			setErrorMessage(Messages.getString("FileFormatWizardPage.InvalidPath")); //$NON-NLS-1$
			setPageComplete(false);
			return;
		}
		
		if (extract && fileText.getText().length()==0) {
			setPageComplete(false);
			return;
		}
		
		if (getColumnDelimiter().length()!=1) {
			setErrorMessage(Messages.getString("FileFormatWizardPage.IncorectColumnDelimiter")); //$NON-NLS-1$
			setPageComplete(false);
			return;
		}
		
		if (getStringDelimiter().length()>1) {
			setErrorMessage(Messages.getString("FileFormatWizardPage.IncorectColumnDelimiter")); //$NON-NLS-1$
			setPageComplete(false);
			return;
		}
		
		setErrorMessage(null);
		setPageComplete(true);
    }
    
    protected void loadSettings()
    {
        IDialogSettings settings = DataUIPlugin.getDefault().getDialogSettings().getSection(SETTINGS_SECTION_NAME);
        
        if (settings!=null) {
            if (settings.get(SETTING_FILE_PATH)!=null)
                fileText.setText( settings.get(SETTING_FILE_PATH) );
            if (settings.get(SETTING_COL_DELIM)!=null)
                columnDelimiterCombo.setText( settings.get(SETTING_COL_DELIM) );
//            if (settings.get(SETTING_LOCALE)!=null)
//                localeCombo.setText( settings.get(SETTING_LOCALE) );
            if (settings.get(SETTING_STRING_DELIM)!=null)
                stringDelimiterCombo.setText( settings.get(SETTING_STRING_DELIM) );
        }
    }
    
    public void saveSettings()
    {
        IDialogSettings settings = DataUIPlugin.getDefault().getDialogSettings();
        if (settings.getSection(SETTINGS_SECTION_NAME)!=null)
            settings = settings.getSection(SETTINGS_SECTION_NAME);
        else
            settings = settings.addNewSection(SETTINGS_SECTION_NAME);
        
        settings.put(SETTING_FILE_PATH, fileText.getText());
        settings.put(SETTING_COL_DELIM, columnDelimiterCombo.getText());
//        settings.put(SETTING_LOCALE, localeCombo.getText());
        settings.put(SETTING_STRING_DELIM, stringDelimiterCombo.getText());
    }
    
    protected void handleBrowse()
    {
        int style = (extract) ? SWT.SAVE : SWT.OPEN;        
        FileDialog dilaog = new FileDialog(Display.getCurrent().getActiveShell(), style);
        dilaog.setFilterExtensions( new String[] { "*.data", "*.csv" , "*.*" } ); //$NON-NLS-1$
        dilaog.setFilterNames( null );
        dilaog.setFilterPath(fileText.getText());
		String s = dilaog.open();
		if (s!=null && s.length()>0) {
			fileText.setText(s);
		}
    }
    
    protected static void initCombo(Combo c, String[][] delims)
    {
        for (int i=0; i<delims.length; ++i) {
            c.add(delims[i][0]);
            c.setData(delims[i][0], delims[i][1]);
        }
        c.setText(delims[0][0]);
    }
    
    public String getFilePath()
    {
        return fileText.getText();
    }
    
    public String getColumnDelimiter()
    {
        String s = columnDelimiterCombo.getText();
        if (columnDelimiterCombo.getData(s)!=null)
            return (String)columnDelimiterCombo.getData(s);
        else
            return s;
    }
    
//    public String getLocale()
//    {
//        String s = localeCombo.getText();
//        if (localeCombo.getData(s)!=null)
//            return (String)localeCombo.getData(s);
//        else
//            return s;
//    }
    
    public String getStringDelimiter()
    {
        String s = stringDelimiterCombo.getText();
        if (stringDelimiterCombo.getData(s)!=null)
            return (String)stringDelimiterCombo.getData(s);
        else
            return s;
    }
    
    public String getHelpID()
    {
    	return null;
    }

}
