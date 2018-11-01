/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.export.component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.core.internal.preferences.EclipsePreferences;
import org.eclipse.core.internal.preferences.PreferencesService;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.datatools.sqltools.result.export.IResultConstants;
import org.eclipse.datatools.sqltools.result.internal.export.OutputterConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * The encoding and delimiter options UI area.
 * 
 * @author Dafan Yang
 */
public class EncodingDelimiterOptionsSection
{
    Composite        _parent;
    Group            _exportFormatGroup;
    Combo            _encodingCombo;
    Combo            _delimiterCombo;
    Label            _encodingLabel;
    Label            _outputFormat;
    Label            _delimiterLabel;
    Text             _delimiterText;
    boolean          _supportDelimiter = false;
    int              _numColumns;
    IStatus          _status;
    private Listener _listener;
    
    public EncodingDelimiterOptionsSection(Composite parent, int numColumns, Listener listener)
    {
        _parent = parent;
        _numColumns = numColumns;
        _listener = listener;
        createControl(_parent);
    }

    /**
     * Creates the control
     * 
     * @param parent the parent composite
     */
    private void createControl(Composite parent)
    {
        Composite container = new Composite(parent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = _numColumns;
        container.setLayoutData(data);

        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        container.setLayout(layout);

        createExportControls(container);
        initializeValues();
    }

    /**
     * Creates controls for export options group
     * 
     * @param container the container of this group
     */
    private void createExportControls(Composite container)
    {
        _exportFormatGroup = new Group(container, SWT.SHADOW_ETCHED_IN);
        _exportFormatGroup.setText(Messages.ExportOptions_title); 

        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.verticalSpacing = 0;
        _exportFormatGroup.setLayout(layout);

        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        _exportFormatGroup.setLayoutData(data);

        _encodingLabel = new Label(_exportFormatGroup, SWT.NONE);
        _encodingLabel.setText(Messages.ExportFormatPage_fileencoding_outputencoding); 
        _encodingLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        _encodingCombo = new Combo(_exportFormatGroup, SWT.NONE);
        _encodingCombo.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                updateValidState();
                _listener.handleEvent(new Event());
            }
        });
        data = new GridData(SWT.FILL, SWT.CENTER, true, false);
        _encodingCombo.setLayoutData(data);

        loadEncodings();

        _outputFormat = new Label(_exportFormatGroup, SWT.NONE);
        _outputFormat.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        _outputFormat.setText(Messages.ExportFormatPage_columndelimiter_outputformat); 
        _delimiterCombo = new Combo(_exportFormatGroup, SWT.READ_ONLY);
        _delimiterCombo.setItems(OutputterConstants.OUTPUT_FORMATS);
        _delimiterCombo.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateDelimter();
            }
        });
        data = new GridData(SWT.FILL, SWT.CENTER, true, false);
        _delimiterCombo.setLayoutData(data);

        _delimiterLabel = new Label(_exportFormatGroup, SWT.NONE);
        _delimiterLabel.setText(Messages.ExportFormatPage_columndelimiter_delimiter); 

        _delimiterText = new Text(_exportFormatGroup, SWT.BORDER);
        GridData griddata1 = new GridData(GridData.FILL_HORIZONTAL);
        _delimiterText.setLayoutData(griddata1);
    }

    /**
     * Loads available encodings into the encodings combobox and sets the default encoding selection.
     */
    private void loadEncodings()
    {
        ArrayList encodings = new ArrayList();
        int n = 0;
        try
        {
            n = Integer.parseInt(Messages.ExportFormatPage_fileencoding_numDefaultEncodings); 
        }
        catch (NumberFormatException e)
        {
            // ignore;
        }
        for (int i = 0; i < n; ++i)
        {
            String enc = Messages.getString("ExportFormatPage_fileencoding_defaultEncoding" + (i + 1)); //$NON-NLS-1$
            if (enc != null)
            {
                encodings.add(enc);
            }
        }
        
        String defaultEnc = "UTF-8"; //$NON-NLS-1$
        
		if (!encodings.contains(defaultEnc))
		{
			encodings.add(defaultEnc);
		}
        String enc = ResultsViewUIPlugin.getDefault().getPreferenceStore().getString(
                PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING);
        boolean isDefault = ((enc == null) || (enc.length() == 0));

        if (!isDefault && !encodings.contains(enc))
        {
            encodings.add(enc);
        }
        Collections.sort(encodings);
        
        for (int i = 0; i < encodings.size(); i++)
        {
            _encodingCombo.add((String) encodings.get(i));
        }
        _encodingCombo.setText(isDefault ? defaultEnc : enc);
    }

    private void updateDelimter()
    {
        int delimiterIndex = _delimiterCombo.getSelectionIndex();
        switch (delimiterIndex)
        {
            case 0:
                _delimiterText.setEditable(false);
                _delimiterText.setTextLimit(OutputterConstants.USER_DEFINED_DELIMITER_COUNT * 4);
                _delimiterText.setText(OutputterConstants.DELIMITER_COLUMN);
                break;
            case 1:
                _delimiterText.setText(OutputterConstants.DELIMITER_COMMA);
                _delimiterText.setEditable(false);
                break;
            case 2:
                _delimiterText.setEditable(false);
                _delimiterText.setTextLimit(OutputterConstants.USER_DEFINED_DELIMITER_COUNT * 4);
                _delimiterText.setText(OutputterConstants.DELIMITER_TAB);
                break;
            case 3:
                _delimiterText.setEditable(true);
                _delimiterText.setTextLimit(OutputterConstants.USER_DEFINED_DELIMITER_COUNT);
                _delimiterText.setText(ResultsViewUIPlugin.getDefault().getPreferenceStore().getString(
                        PreferenceConstants.EXPORT_FORMAT_DELIMITER));
                break;
            default:
                _delimiterText.setEditable(true);
                _delimiterText.setTextLimit(OutputterConstants.USER_DEFINED_DELIMITER_COUNT * 4);
                _delimiterText.setText(OutputterConstants.DELIMITER_COLUMN);
        }
    }

    /**
     * Initializes values of export options
     * 
     */
    private void initializeValues()
    {
        IPreferenceStore _store = ResultsViewUIPlugin.getDefault().getPreferenceStore();

        String prefEncoding = _store.getString(PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING);
        int selected = _encodingCombo.indexOf(prefEncoding);
        _encodingCombo.select(selected);

        selected = _store.getInt(PreferenceConstants.EXPORT_FORMAT_OUTPUT_FORMAT);
        _delimiterCombo.select(selected);
        updateDelimter();
    }

    public void updateOptionsSection()
    {
        _outputFormat.setEnabled(_supportDelimiter);
        _delimiterCombo.setEnabled(_supportDelimiter);
        _delimiterLabel.setEnabled(_supportDelimiter);
        _delimiterText.setEnabled(_supportDelimiter);
    }
    
    public void updateTxtExportControls()
    {
        _outputFormat.setEnabled(true);
        _delimiterCombo.setEnabled(true);
        _delimiterLabel.setEnabled(true);
        _delimiterText.setEnabled(true);
    }

    public void updateXMLExportControls()
    {
        _outputFormat.setEnabled(false);
        _delimiterCombo.setEnabled(false);
        _delimiterLabel.setEnabled(false);
        _delimiterText.setEnabled(false);
    }

    /**
     * Returns the delimiter
     * 
     * @return delimiter the delimiter
     */
    public String getDelimiter()
    {
        return _delimiterCombo.getItem(_delimiterCombo.getSelectionIndex());
    }

    /**
     * Returns the user defined delimiter
     * 
     * @return the user defined delimiter
     */
    public String getUserDefinedDelimiter()
    {
        return _delimiterText.getText().trim();
    }

    /**
     * Returns the encoding
     * 
     * @return the encoding
     */
    public String getEncoding()
    {
        return _encodingCombo.getText();
    }
    
    /**
     * Returns the status of this UI section
     * 
     * @return the status
     */
    public IStatus getStatus()
    {
        return _status;
    }
    
    private void updateValidState()
    {
        if (!isEncodingValid())
        {
            _status = new Status(IStatus.ERROR, ResultsViewUIPlugin.getPluginId(), IStatus.OK, Messages.ExportFormatPage_fileencoding_unsupportedEncoding, null); //$NON-NLS-1$
        }
        else
        {
            _status = Status.OK_STATUS;
        }
    }
    
    private boolean isEncodingValid()
    {
        try
        {
            new String(new byte[0], _encodingCombo.getText());
            return true;
        }
        catch (UnsupportedEncodingException e)
        {
            return false;
        }
    }

    public boolean supportDelimiter()
    {
        return _supportDelimiter;
    }

    public void setSupportDelimiter(boolean delimiter)
    {
        _supportDelimiter = delimiter;
    }
}
