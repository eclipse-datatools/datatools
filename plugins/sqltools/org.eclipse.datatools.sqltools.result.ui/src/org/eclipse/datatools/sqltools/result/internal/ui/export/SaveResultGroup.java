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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.XMLResultSetObject;
import org.eclipse.datatools.sqltools.result.export.AbstractOutputter;
import org.eclipse.datatools.sqltools.result.internal.export.IOutputterDescriptor;
import org.eclipse.datatools.sqltools.result.internal.export.OutputterRegistryReader;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.export.component.EncodingDelimiterOptionsSection;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * A resource an container group with encoding and delimiter options.
 * 
 * @author Dafan Yang 
 */
public class SaveResultGroup extends ResourceAndContainerGroup
{
    private Combo                           _resourceTypeField;
    private Composite                       _typeGroup;
    private EncodingDelimiterOptionsSection _options;
    private boolean                         _isXMLResult = false;

    /**
     * Creates a save result group to save the given result set object
     * 
     * @param parent the parent composite
     * @param client the client 
     * @param resourceFieldLabel the resource field label
     * @param resourceType the resource type
     * @param resultset the result set object
     */
    public SaveResultGroup(Composite parent, Listener client, String resourceFieldLabel, String resourceType,
            IResultSetObject resultset, Listener listener)
    {
        super(parent, client, resourceFieldLabel, resourceType, listener);
//        if (resultset != null && resultset instanceof XMLResultSetObject)
//        {
//            _resourceTypeField.setItems(IResultConstants.FORMATSNOHTML);
//        }
//        else if (resultset != null)
//        {
//            _resourceTypeField.setItems(IResultConstants.FORMATS);
//        }
//        else
//        {
//            _resourceTypeField.setItems(IResultConstants.FORMATTEXTONLY);
//        }
        _isXMLResult = resultset != null && resultset instanceof XMLResultSetObject;
        _resourceTypeField.setItems(OutputterRegistryReader.getInstance().getOutputterDspStrings(_isXMLResult));
        _resourceTypeField.select(0);
    }

    /**
     * Creates a save result group to save all the result set objects in a result instance
     * 
     * @param parent the parent composite
     * @param client the client 
     * @param resourceFieldLabel the resource field label
     * @param resourceType the resource type
     * @param resultInstance the result instance
     */
    public SaveResultGroup(Composite parent, Listener client, String resourceFieldLabel, String resourceType,
            IResultInstance resultInstance, Listener listener)
    {
        super(parent, client, resourceFieldLabel, resourceType, listener);
        for (int i = 0; i < resultInstance.getItemCount(); i++)
        {
            ResultItem ri = resultInstance.getItem(i);
            if (ri != null)
            {
                if (ri.getResultObject() instanceof IResultSetObject)
                {
                    IResultSetObject rs = (IResultSetObject) ri.getResultObject();
                    if (rs instanceof XMLResultSetObject)
                    {
                        _isXMLResult = true;
                    }
                }
            }
        }

        _resourceTypeField.setItems(OutputterRegistryReader.getInstance().getOutputterDspStrings(_isXMLResult));
//        // is an xml resultset
//        if (isResultSetObject && !isMutilRecords)
//        {
//            _resourceTypeField.setItems(IResultConstants.FORMATSNOHTML);
//        }
//        else if (isResultSetObject)
//        {
//            _resourceTypeField.setItems(IResultConstants.FORMATS);
//        }
//        else
//        {
//            _resourceTypeField.setItems(IResultConstants.FORMATTEXTONLY);
//        }
        _resourceTypeField.select(0);
    }

    /*
     *  (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.result.internal.ui.export.ResourceAndContainerGroup#createExtraContents(org.eclipse.swt.widgets.Composite, org.eclipse.swt.layout.GridLayout, org.eclipse.swt.graphics.Font, org.eclipse.swt.layout.GridData)
     */
    protected void createExtraContents(Composite composite, GridLayout layout, Font font, GridData data)
    {
        super.createExtraContents(composite, layout, font, data);
        // resource type group
        _typeGroup = new Composite(composite, SWT.NONE);

        layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 0;
        _typeGroup.setLayout(layout);
        _typeGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
        _typeGroup.setFont(font);

        Label typeLabel = new Label(_typeGroup, SWT.NONE);
        typeLabel.setText(Messages.SaveResultSetDialog_filetype_label); 
        typeLabel.setFont(font);

        // resource type entry field
        _resourceTypeField = new Combo(_typeGroup, SWT.READ_ONLY);

        _resourceTypeField.setLayoutData(data);
        _resourceTypeField.setFont(font);
        _resourceTypeField.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                resourceTypeFieldSelected();
            }
        });
        _resourceTypeField.select(0);
        _options = new EncodingDelimiterOptionsSection(_typeGroup, 2, _listener);
    }

    private void resourceTypeFieldSelected()
    {
        String value = this.getResource();
        if (value != null && !"".equals(value.trim())) //$NON-NLS-1$
        {
            String name = value;
            int sep = value.lastIndexOf(".");
            if (sep > 0)
            {
                name = value.substring(0, sep);
            }
            
            int selIndex = _resourceTypeField.getSelectionIndex();
            value = name + "." + OutputterRegistryReader.getInstance().getOutputterDesciptor(_isXMLResult, selIndex).getFileExtension();
            
            _options.setSupportDelimiter(OutputterRegistryReader.getInstance().getOutputterDesciptor(_isXMLResult, selIndex).supportDelimiter());
            
            _options.updateOptionsSection();
            
//            int selIndex = IResultConstants.FORMAT_IDS[_resourceTypeField.getSelectionIndex()];
//            switch (selIndex)
//            {
//                case IResultConstants.ID_PLAINTEXT:
//                    value = name + ".txt";
//                    if (!_saveDiasplayResults)
//                    {
//                        _options.updateTxtExportControls();
//                    }
//                    break;
//                case IResultConstants.ID_CSV:
//                    value = name + ".csv";
//                    _options.updateXMLExportControls();
//                    break;
//                case IResultConstants.ID_XML:
//                    value = name + ".xml";
//                    _options.updateXMLExportControls();
//                    break;
//                case IResultConstants.ID_HTML:
//                    value = name + ".html";
//                    _options.updateXMLExportControls();
//                    break;
//                default:
//                    break;
//            }
            this.setResource(value);
        }
    }

    /**
     * Returns the delimiter
     * 
     * @return delimiter
     */
    public String getDelimiter()
    {
        return _options.getDelimiter();
//        
//        if (IResultConstants.FORMAT_CSV.equals(_resourceTypeField.getItem(_resourceTypeField.getSelectionIndex())))
//        {
//            return IResultConstants.CSV_SEPARATED;
//        }
//        else
//        {
//            return _options.getDelimiter();
//        }
    }

    /**
     * Returns the user-defined delimiter
     * 
     * @return the user-defined delimiter
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

    /**
     * Disables the delimiter configuration function
     *
     */
    public void disableDelimiters()
    {
        _options.updateXMLExportControls();
    }

    /**
     * Returns the resourceTypeField
     * 
     * @return the resourceTypeField
     */
    public Combo getResourceTypeField()
    {
        return _resourceTypeField;
    }
    
    /**
     * Returns the status of this UI section
     * 
     * @return the status
     */
    public IStatus getStatus()
    {
        return _options.getStatus();
    }

    public boolean isXMLResult()
    {
        return _isXMLResult;
    }
    
    public IOutputterDescriptor getOutputterDesp()
    {
        return OutputterRegistryReader.getInstance().getOutputterDesciptor(_isXMLResult,
                _resourceTypeField.getSelectionIndex());
    }
}
