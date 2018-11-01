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
package org.eclipse.datatools.sqltools.result.internal.ui.export;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.XMLResultSetObject;
import org.eclipse.datatools.sqltools.result.internal.export.IOutputterDescriptor;
import org.eclipse.datatools.sqltools.result.internal.export.OutputterRegistryReader;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.export.component.EncodingDelimiterOptionsSection;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.UIUtil;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * The wizard page for export result set(s) wizard
 * 
 * @author Dafan Yang
 */
public class ResultFormatWizardPage extends WizardPage
{
    private Text                            _txtFileName;
    private List                            _lstFormat;
    private EncodingDelimiterOptionsSection _options;
    private boolean                         _isXMLResult = false;
    
    private Map                             _outputters;
    private Listener         _listener      = new Listener()
    {

        public void handleEvent(Event event)
        {
            if(_options != null)
            {
                IStatus status = _options.getStatus();
                if(status.getSeverity() == IStatus.ERROR)
                {
                    ResultFormatWizardPage.this.setMessage(status.getMessage(), IMessageProvider.ERROR);
                    setPageComplete(false);
                }
                else
                {
                    ResultFormatWizardPage.this.setMessage(null);
                    setPageComplete(true);
                }
            }
        }
    };
    /**
     * Will be used to save a result set object to an external file.
     * 
     * @param result the result set object
     */
    public ResultFormatWizardPage(IResultSetObject result)
    {
        super(Messages.ResultFormatWizardPage_title); 
        this.setTitle(Messages.ResultFormatWizardPage_title); 
        this.setDescription(Messages.ResultFormatWizardPage_description); 

        if (result instanceof XMLResultSetObject)
        {
            _isXMLResult = true;
        }
        _outputters = new HashMap();
    }

    /**
     * Will be used to save all result set objects in a result instance to an external file.
     * 
     * @param result the result instance
     */
    public ResultFormatWizardPage(IResultInstance result)
    {
        super(Messages.ResultFormatWizardPage_title); 
        this.setTitle(Messages.ResultFormatWizardPage_title); 
        this.setDescription(Messages.ResultFormatWizardPage_description); 
        for (int i = 0; i < result.getItemCount(); i++)
        {
            ResultItem ri = result.getItem(i);
            if (ri != null)
            {
                if (ri.getResultObject() instanceof IResultSetObject)
                {
                    IResultSetObject rs = (IResultSetObject) ri.getResultObject();
                    if (rs instanceof XMLResultSetObject)
                    {
                        _isXMLResult = true;
                        break;
                    }
                }
            }
        }
        _outputters = new HashMap();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent)
    {
        Composite container = new Composite(parent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_BOTH);
        container.setLayoutData(data);

        GridLayout layout = new GridLayout();
        int numColumns = 3;
        layout.numColumns = numColumns;
        container.setLayout(layout);

        createFileSelectionArea(container, numColumns);
        createFormatSelectionArea(container, numColumns);


        this.setControl(container);
        // because no file name input yet.
        this.setPageComplete(false);

    }

    /**
     * Creates file selection group
     * 
     * @param container the container of this group
     * @param numColumns the number of columns
     */
    private void createFileSelectionArea(Composite container, int numColumns)
    {
        UIUtil.createLabel(container, Messages.ResultFormatWizardPage_label_filename, 1); 
        int width = this.convertWidthInCharsToPixels(40);
        _txtFileName = new Text(container, SWT.SINGLE | SWT.BORDER);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.grabExcessHorizontalSpace = true;
        data.widthHint = width;
        data.horizontalSpan = numColumns - 2;
        _txtFileName.setLayoutData(data);

        Button b = new Button(container, SWT.PUSH);
        b.setText(Messages.ResultFormatWizardPage_button_browse);
        
        b.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
                dialog.setFileName("result"); //$NON-NLS-1$
                IOutputterDescriptor descriptor = OutputterRegistryReader.getInstance().getOutputterDesciptor(_isXMLResult, getOutputterIndex());
                
                //Set current selected file type and all files as the filter
                String[] exts = new String[2];
                exts[0] = descriptor.getExtFilterString();
                exts[1] = "*.*"; //$NON-NLS-1$
                
                String[] names = new String[2];
                names[0] = descriptor.getExtensionFilterDisplayString();
                names[1] = Messages.ResultFormatWizardPage_all_files;
                
                dialog.setFilterExtensions(exts);
                dialog.setFilterNames(names);
                dialog.setText(Messages.ResultFormatWizardPage_dialog_text); 
                String filename = dialog.open();
                if (filename != null)
                {
                    _txtFileName.setText(filename);
                }
            }
        });

        _txtFileName.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                String txt = _txtFileName.getText();
                if (txt.trim().length() == 0)
                {
                    setErrorMessage(Messages.ResultFormatWizardPage_errormessage_choosefile); 
                    setPageComplete(false);
                }
                else
                {
                    setErrorMessage(null);
                    setPageComplete(true);
                }
            }
        });
    }

    /**
     * Creates the format selection group
     *  
     * @param container the container of this group
     * @param numColumns the number of columns
     */
    private void createFormatSelectionArea(Composite container, int numColumns)
    {
        UIUtil.createLabel(container, Messages.ResultFormatWizardPage_label_format, 1); 

        _lstFormat = new List(container, SWT.SINGLE | SWT.BORDER);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        data.horizontalSpan = numColumns - 2;
        _lstFormat.setLayoutData(data);
        _lstFormat.setItems(OutputterRegistryReader.getInstance().getOutputterDspStrings(_isXMLResult));

        _lstFormat.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                resourceTypeFieldSelected();
            }
        });
        _lstFormat.setSelection(0);

        UIUtil.createLabel(container, "", 1); // empty area //$NON-NLS-1$
        UIUtil.createLabel(container, "", 1); // empty area //$NON-NLS-1$
        _options = new EncodingDelimiterOptionsSection(container, 1, _listener);
    }

    private void resourceTypeFieldSelected()
    {
        String value = this.getFileName();

        String name = value;
        int sep = value.lastIndexOf("."); //$NON-NLS-1$
        if (sep > 0)
        {
            name = value.substring(0, sep);
        }
        int selIndex = getOutputterIndex();
        value = name + "." + OutputterRegistryReader.getInstance().getOutputterDesciptor(_isXMLResult, selIndex).getFileExtension(); //$NON-NLS-1$
        
        _options.setSupportDelimiter(OutputterRegistryReader.getInstance().getOutputterDesciptor(_isXMLResult, selIndex).supportDelimiter());
        
        _options.updateOptionsSection();

        if (name != null && !"".equals(name.trim())) //$NON-NLS-1$
        {
            this.setFileName(value);
        }

    }

    /**
     * Returns the file name
     * 
     * @return the file name
     */
    public String getFileName()
    {
        return _txtFileName.getText();
    }

    private void setFileName(String fileName)
    {
        _txtFileName.setText(fileName);
    }

    /**
     * Returns the index of the outputter.<br>
     * The index may vary in different setion, but in a section, the index for a specific outputter wont change
     */
    public int getOutputterIndex()
    {
        return _lstFormat.getSelectionIndex();
    }

    /**
     * Returns the delimiter
     * 
     * @return delimiter the delimiter
     */
    public String getDelimiter()
    {
        return _options.getDelimiter();
    }

    /**
     * Returns the user defined delimiter
     * 
     * @return the user defined delimiter
     */
    public String getUserDefinedDelimiter()
    {
        return _options.getUserDefinedDelimiter();
    }

    /**
     * Returns the encoding
     * 
     * @return the encoding
     */
    public String getEncoding()
    {
        return _options.getEncoding();
    }

    public boolean isXMLResult()
    {
        return _isXMLResult;
    }
   
    public IOutputterDescriptor getOutputterDesp()
    {
        return OutputterRegistryReader.getInstance().getOutputterDesciptor(_isXMLResult, getOutputterIndex());
    }
}
