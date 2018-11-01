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
package org.eclipse.datatools.sqltools.result.internal.preference;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.internal.export.OutputterConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.UIUtil;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page for export format options
 * 
 * @author Dafan Yang 
 */
public class ExportFormatPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, IContextProvider
{
    // Resource bundle strings used in this page
    private static String XML_EXPORT_FORMAT;
    private static String ADD_XML_HEADER;
    private static String XML_HEADER;
    private static String ADD_XML_ROOT_TAG;
    private static String XML_ROOT_TAG;

    private static String DEFAULT_COLUMN_DELIMITER;
    private static String OUTPUT_FORMAT;
    private static String DELIMINTER;

    private static String COLUMN_ALIGNED;
    private static String COMMA_SEPARATED;
    private static String TAB_DELIMITED;
    private static String USER_DEFINED;

    private static String DEFAULT_FILE_ENCODING;
    private static String DEFAULT_ENCODING;
    private static String DEFAULT_ENCODING_STR = "UTF-8";
    private static String OTHER_ENCODING;
    
    private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName());
    
    public IContext getContext(Object target)
    {
        return contextProviderDelegate.getContext(target);
    }

    public int getContextChangeMask()
    {
        return contextProviderDelegate.getContextChangeMask();
    }

    public String getSearchExpression(Object target)
    {
        return contextProviderDelegate.getSearchExpression(target);
    }

    // Combo box items
    private String[]      OUTPUT_FORMATS     =
    {
        COLUMN_ALIGNED, COMMA_SEPARATED, TAB_DELIMITED, USER_DEFINED
    };

    Button                _addXMLHeader      = null;
    Label                 _xmlHeaderLabel    = null;
    Text                  _xmlHeader         = null;
    Label                 _xmlRootTagLabel   = null;
    Button                _addXMLRootTag     = null;
    Text                  _xmlRootTag        = null;
    Combo                 _outputFormat      = null;
    Label                 _outputFormatLabel = null;
    Text                  _delimiter         = null;
    Label                 _delimiterLabel    = null;

    Button                _defaultEncoding   = null;
    Button                _otherEncoding     = null;
    Combo                 _fileEncoding      = null;

    static
    {
        // Get strings from resource bundle
        XML_EXPORT_FORMAT = Messages.ExportFormatPage_xmlexportformat_group; 
        ADD_XML_HEADER = Messages.ExportFormatPage_xmlexportformat_addxmlheader; 
        XML_HEADER = Messages.ExportFormatPage_xmlexportformat_xmlheader; 
        ADD_XML_ROOT_TAG = Messages.ExportFormatPage_xmlexportformat_addxmlroottag; 
        XML_ROOT_TAG = Messages.ExportFormatPage_xmlexportformat_xmlroottag; 

        DEFAULT_COLUMN_DELIMITER = Messages.ExportFormatPage_columndelimiter_group; 
        OUTPUT_FORMAT = Messages.ExportFormatPage_columndelimiter_outputformat; 
        DELIMINTER = Messages.ExportFormatPage_columndelimiter_delimiter; 

        COLUMN_ALIGNED = Messages.ExportFormatPage_columndelimiter_items_columnaligned; 
        COMMA_SEPARATED = Messages.ExportFormatPage_columndelimiter_items_commaseparated; 
        TAB_DELIMITED = Messages.ExportFormatPage_columndelimiter_items_tabdelimited; 
        USER_DEFINED = Messages.ExportFormatPage_columndelimiter_items_userdefined; 

        DEFAULT_FILE_ENCODING = Messages.ExportFormatPage_fileencoding_group; 
        DEFAULT_ENCODING = Messages.ExportFormatPage_fileencoding_defaultencoding; 
        OTHER_ENCODING = Messages.ExportFormatPage_fileencoding_otherencoding; 
    }

    public ExportFormatPreferencePage()
    {
        super(Messages.ExportFormatPage_title); 
        setPreferenceStore(ResultsViewUIPlugin.getDefault().getPreferenceStore());
    }

    protected Control createContents(Composite parent)
    {
        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(parent, HelpUtil.getContextId(IHelpConstants.PREFERENCE_PAGE_EXPORT_FORMAT, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
        
        // xml export format
        createXMLExportFormat(parent);

        // column delimiter
        createDelimiterGroup(parent);

        // file encoding
        createEncodingGroup(parent);

        // init
        initializeValues();
        updateEnablement();

        return new Composite(parent, SWT.NULL);
    }

    private void createXMLExportFormat(Composite parent)
    {
        Group xmlGroup = UIUtil.createGroup(parent, XML_EXPORT_FORMAT, 2);

        _addXMLHeader = UIUtil.createCheckBox(xmlGroup, ADD_XML_HEADER, 2);
        _addXMLHeader.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateXMLExportFormatEnablement();
            }
        });
        _xmlHeaderLabel = UIUtil.createLabel(xmlGroup, XML_HEADER, 1, 20);
        _xmlHeader = UIUtil.createTextBox(xmlGroup, 1);
        _xmlHeader.setEditable(false);
        _xmlHeader.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        _addXMLRootTag = UIUtil.createCheckBox(xmlGroup, ADD_XML_ROOT_TAG, 2);
        _addXMLRootTag.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateXMLExportFormatEnablement();
            }
        });
        _xmlRootTagLabel = UIUtil.createLabel(xmlGroup, XML_ROOT_TAG, 1, 20);
        _xmlRootTag = UIUtil.createTextBox(xmlGroup, 1);
        _xmlRootTag.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    private void createDelimiterGroup(Composite parent)
    {
        Group delimiterGroup = UIUtil.createGroup(parent, DEFAULT_COLUMN_DELIMITER, 2);

        _outputFormatLabel = UIUtil.createLabel(delimiterGroup, OUTPUT_FORMAT, 1);
        _outputFormat = UIUtil.createCombo(delimiterGroup, OUTPUT_FORMATS, 1, 90);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        _outputFormat.setLayoutData(gd);
        _outputFormat.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateDelimiter();
            }
        });

        _delimiterLabel = UIUtil.createLabel(delimiterGroup, DELIMINTER, 1);
        _delimiter = UIUtil.createTextBox(delimiterGroup, 1);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        _delimiter.setLayoutData(gd);
    }

    private void createEncodingGroup(Composite parent)
    {
        Font font = parent.getFont();
        Group group = new Group(parent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        group.setLayoutData(data);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        group.setLayout(layout);
        group.setText(DEFAULT_FILE_ENCODING); //$NON-NLS-1$
        group.setFont(font);

        SelectionAdapter buttonListener = new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateEncodingState(_defaultEncoding.getSelection());
                // updateValidState();
            }
        };

        _defaultEncoding = new Button(group, SWT.RADIO);
        
        //FIX BZ 130887, use 'UTF-8' as the default encoding
        String defaultEnc = DEFAULT_ENCODING_STR;

        _defaultEncoding.setText(NLS.bind(DEFAULT_ENCODING, new String[]
        {
            defaultEnc
        }));

        data = new GridData();
        data.horizontalSpan = 2;
        _defaultEncoding.setLayoutData(data);
        _defaultEncoding.addSelectionListener(buttonListener);
        _defaultEncoding.setFont(font);

        _otherEncoding = new Button(group, SWT.RADIO);
        _otherEncoding.setText(OTHER_ENCODING); //$NON-NLS-1$
        _otherEncoding.addSelectionListener(buttonListener);
        _otherEncoding.setFont(font);

        _fileEncoding = new Combo(group, SWT.NONE);
        data = new GridData();
        data.widthHint = convertWidthInCharsToPixels(15);
        _fileEncoding.setFont(font);
        _fileEncoding.setLayoutData(data);

        _fileEncoding.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                updateValidState();
                if(!_defaultEncoding.getSelection())
                {
                    String xmlHeaderFormat = Messages.ExportFormatPage_xmlexportformat_xmlheader_text; 
                    String xmlHeader = NLS.bind(xmlHeaderFormat, new String[]
                    {
                        _fileEncoding.getText()
                    });
                    _xmlHeader.setText(xmlHeader);
                }
            }
        });

        _fileEncoding.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                String xmlHeaderFormat = Messages.ExportFormatPage_xmlexportformat_xmlheader_text; 
                String xmlHeader = NLS.bind(xmlHeaderFormat, new String[]
                {
                    _fileEncoding.getText()
                });
                _xmlHeader.setText(xmlHeader);
            }
        });

        ArrayList encodings = new ArrayList();
        int n = 0;
        try
        {
            n = Integer.parseInt(Messages.ExportFormatPage_fileencoding_numDefaultEncodings); 
        }
        catch (NumberFormatException e)
        {
            // Ignore;
        }
        for (int i = 0; i < n; ++i)
        {
            String enc = Messages.getString("ExportFormatPage_fileencoding_defaultEncoding" + (i + 1)); //$NON-NLS-1$
            if (enc != null)
            {
                encodings.add(enc);
            }
        }

        if (!encodings.contains(defaultEnc))
        {
            encodings.add(defaultEnc);
        }

        String enc = getPreferenceStore().getString(PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING);
        boolean isDefault = ((enc == null) || (enc.length() == 0));

        if (!isDefault && !encodings.contains(enc))
        {
            encodings.add(enc);
        }
        Collections.sort(encodings);
        for (int i = 0; i < encodings.size(); ++i)
        {
            _fileEncoding.add((String) encodings.get(i));
        }

        _fileEncoding.setText(isDefault ? defaultEnc : enc);
        updateEncodingState(isDefault);
    }

    protected IPreferenceStore doGetPreferenceStore()
    {
        return ResultsViewUIPlugin.getDefault().getPreferenceStore();
    }

    protected void performDefaults()
    {
        super.performDefaults();
        IPreferenceStore store = getPreferenceStore();
        _addXMLHeader.setSelection(store.getDefaultBoolean(PreferenceConstants.EXPORT_FORMAT_ADD_XML_HEADER));
        _xmlHeader.setText(store.getDefaultString(PreferenceConstants.EXPORT_FORMAT_XML_HEADER));
        _addXMLRootTag.setSelection(store.getDefaultBoolean(PreferenceConstants.EXPORT_FORMAT_ADD_XML_ROOT_TAG));
        _xmlRootTag.setText(store.getDefaultString(PreferenceConstants.EXPORT_FORMAT_XML_ROOT_TAG));
        _outputFormat.select(store.getDefaultInt(PreferenceConstants.EXPORT_FORMAT_OUTPUT_FORMAT));
        _delimiter.setText(store.getDefaultString(PreferenceConstants.EXPORT_FORMAT_DELIMITER));
        _defaultEncoding.setSelection(store.getDefaultBoolean(PreferenceConstants.EXPORT_FORMAT_DEFAULT_ENCODEING));
        _otherEncoding.setSelection(store.getDefaultBoolean(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING));
        _fileEncoding.select(store.getDefaultInt(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING_SELECTION));
        store.setValue(PreferenceConstants.EXPORT_FORMAT_DELIMITER, OutputterConstants.DEFAULT_USER_DEFINED_DEMILITER);

        updateEnablement();
    }

    private void updateEnablement()
    {
        updateXMLExportFormatEnablement();
        updateDelimiter();
        updateEncodingEnablement();
    }

    private void updateXMLExportFormatEnablement()
    {
        boolean enabled = _addXMLHeader.getSelection();
        _xmlHeader.setEnabled(enabled);
        _xmlHeaderLabel.setEnabled(enabled);

        enabled = _addXMLRootTag.getSelection();
        _xmlRootTag.setEnabled(enabled);
        _xmlRootTagLabel.setEnabled(enabled);
    }

    private void updateDelimiter()
    {
        int delimiterIndex = _outputFormat.getSelectionIndex();
        switch (delimiterIndex)
        {
            case 0:
                _delimiter.setEditable(false);
                _delimiter.setTextLimit(OutputterConstants.USER_DEFINED_DELIMITER_COUNT * 4);
                _delimiter.setText(OutputterConstants.DELIMITER_COLUMN);
                break;
            case 1:
                _delimiter.setText(OutputterConstants.DELIMITER_COMMA);
                _delimiter.setEditable(false);
                break;
            case 2:
                _delimiter.setEditable(false);
                _delimiter.setTextLimit(OutputterConstants.USER_DEFINED_DELIMITER_COUNT * 4);
                _delimiter.setText(OutputterConstants.DELIMITER_TAB);
                break;
            case 3:
                _delimiter.setEditable(true);
                _delimiter.setTextLimit(OutputterConstants.USER_DEFINED_DELIMITER_COUNT);
                _delimiter.setText(ResultsViewUIPlugin.getDefault().getPreferenceStore().getString(
                        PreferenceConstants.EXPORT_FORMAT_DELIMITER));
                break;
            default:
                _delimiter.setEditable(true);
                _delimiter.setTextLimit(OutputterConstants.USER_DEFINED_DELIMITER_COUNT * 4);
                _delimiter.setText(OutputterConstants.DELIMITER_COLUMN);
        }
    }

    private void updateEncodingEnablement()
    {
        boolean enabled = _defaultEncoding.getSelection();
        _fileEncoding.setEnabled(!enabled);
        updateValidState();
    }

    private void updateEncodingState(boolean useDefault)
    {
        _defaultEncoding.setSelection(useDefault);
        _otherEncoding.setSelection(!useDefault);
        _fileEncoding.setEnabled(!useDefault);
        updateValidState();
        if (useDefault)
        {
            String xmlHeaderFormat = Messages.ExportFormatPage_xmlexportformat_xmlheader_text; 
            String xmlHeader = NLS.bind(xmlHeaderFormat, new String[]
            {
                DEFAULT_ENCODING_STR
            });
            _xmlHeader.setText(xmlHeader);
        }
        else
        {
            String xmlHeaderFormat = Messages.ExportFormatPage_xmlexportformat_xmlheader_text; 
            String xmlHeader = NLS.bind(xmlHeaderFormat, new String[]
            {
                _fileEncoding.getText()
            });
            _xmlHeader.setText(xmlHeader);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench)
    {
    }

    /**
     * initialize values
     */
    private void initializeValues()
    {
        IPreferenceStore store = getPreferenceStore();
        _addXMLHeader.setSelection(store.getBoolean(PreferenceConstants.EXPORT_FORMAT_ADD_XML_HEADER));
        _xmlHeader.setText(store.getString(PreferenceConstants.EXPORT_FORMAT_XML_HEADER));
        _addXMLRootTag.setSelection(store.getBoolean(PreferenceConstants.EXPORT_FORMAT_ADD_XML_ROOT_TAG));
        _xmlRootTag.setText(store.getString(PreferenceConstants.EXPORT_FORMAT_XML_ROOT_TAG));
        _outputFormat.select(store.getInt(PreferenceConstants.EXPORT_FORMAT_OUTPUT_FORMAT));
        _delimiter.setText(store.getString(PreferenceConstants.EXPORT_FORMAT_DELIMITER));
        _otherEncoding.setSelection(store.getBoolean(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING));
        _fileEncoding.select(store.getInt(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING_SELECTION));
        _defaultEncoding.setSelection(store.getBoolean(PreferenceConstants.EXPORT_FORMAT_DEFAULT_ENCODEING));
        _xmlHeader.setText(store.getString(PreferenceConstants.EXPORT_FORMAT_XML_HEADER));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.IPreferencePage#performOk()
     */
    public boolean performOk()
    {
        IPreferenceStore store = getPreferenceStore();
        store.setValue(PreferenceConstants.EXPORT_FORMAT_ADD_XML_HEADER, _addXMLHeader.getSelection());
        store.setValue(PreferenceConstants.EXPORT_FORMAT_XML_HEADER, _xmlHeader.getText());
        store.setValue(PreferenceConstants.EXPORT_FORMAT_ADD_XML_ROOT_TAG, _addXMLRootTag.getSelection());
        store.setValue(PreferenceConstants.EXPORT_FORMAT_XML_ROOT_TAG, _xmlRootTag.getText());
        store.setValue(PreferenceConstants.EXPORT_FORMAT_OUTPUT_FORMAT, _outputFormat.getSelectionIndex());
        if (_outputFormat.getText().equals(USER_DEFINED))
        {
            store.setValue(PreferenceConstants.EXPORT_FORMAT_DELIMITER, _delimiter.getText());
        }
        store.setValue(PreferenceConstants.EXPORT_FORMAT_DEFAULT_ENCODEING, _defaultEncoding.getSelection());

        store.setValue(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING, _otherEncoding.getSelection());
        store.setValue(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING_SELECTION, _fileEncoding.getSelectionIndex());
        store.setValue(PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING, _fileEncoding.getText());
        if (_defaultEncoding.getSelection())
        {
            store.setValue(PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING, DEFAULT_ENCODING_STR);
        }

        return super.performOk();
    }

    protected void updateValidState()
    {
        if (!isEncodingValid())
        {
            setErrorMessage(Messages.ExportFormatPage_fileencoding_unsupportedEncoding); 
            setValid(false);
        }
        else
        {
            setErrorMessage(null);
            setValid(true);
        }
    }

    private boolean isEncodingValid()
    {
        return _defaultEncoding.getSelection() || isValidEncoding(_fileEncoding.getText());
    }

    private boolean isValidEncoding(String enc)
    {
        try
        {
            new String(new byte[0], enc);
            return true;
        }
        catch (UnsupportedEncodingException e)
        {
            return false;
        }
    }
}
