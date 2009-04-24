/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.storage;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.LabelFormEntry;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.ASATableSchemaEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.CollapseableSection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * Storage page for ASA table schema editor
 * 
 * @author Idull
 */
public class ASAStorageEditorPage extends ASATableSchemaEditorPage implements ISchemaObjectEditorPage
{
    Button                    _defaultButton;
    Button                    _userDefinedButton;
    Spinner                   _percentageSpinner;
    Label                     _tipLabel;
    private SelectionListener _defaultpctSelectionListener = new SelectionListener()
                                                           {
                                                               public void widgetDefaultSelected(SelectionEvent e)
                                                               {

                                                               }

                                                               public void widgetSelected(SelectionEvent e)
                                                               {
                                                                   if (_defaultButton.getSelection())
                                                                   {
                                                                       _percentageSpinner.setEnabled(false);
                                                                       ((SybaseASATable) _table).setPctfree(-1);
                                                                       ASAStorageEditorPage.this.markDirty();
                                                                   }
                                                                   else
                                                                   {
                                                                       _percentageSpinner.setEnabled(true);
                                                                   }
                                                               }
                                                           };

    private SelectionListener _udpctfreeSelectionListener  = new SelectionListener()
                                                           {
                                                               public void widgetDefaultSelected(SelectionEvent e)
                                                               {

                                                               }

                                                               public void widgetSelected(SelectionEvent e)
                                                               {
                                                                   if (_userDefinedButton.getSelection())
                                                                   {
                                                                       _percentageSpinner.setEnabled(true);
                                                                       ((SybaseASATable) _table)
                                                                               .setPctfree(_percentageSpinner
                                                                                       .getSelection());
                                                                       ASAStorageEditorPage.this.markDirty();
                                                                   }
                                                                   else
                                                                   {
                                                                       _percentageSpinner.setEnabled(false);
                                                                   }
                                                               }
                                                           };

    private SelectionListener _percentageSpinnerListener   = new SelectionListener()
                                                           {
                                                               public void widgetDefaultSelected(SelectionEvent e)
                                                               {
                                                               }

                                                               public void widgetSelected(SelectionEvent e)
                                                               {
                                                                   int percentage = _percentageSpinner.getSelection();
                                                                   String info = "%  ("; //$NON-NLS-1$
                                                                   int reservedBytes = (percentage * 2048) / 100;
                                                                   info = info
                                                                           + reservedBytes
                                                                           + Messages.ASAStorageEditorPage_reserved_bytes;
                                                                   _tipLabel.setText(info);
                                                                   ((SybaseASATable) _table)
                                                                           .setPctfree(_percentageSpinner
                                                                                   .getSelection());
                                                                   ASAStorageEditorPage.this.markDirty();
                                                               }
                                                           };

    /**
     * "Physical Storage" section on "Storage" page
     * 
     * @author Idull
     */
    class PhysicalStorageSection extends CollapseableSection
    {

        public PhysicalStorageSection(FormToolkit toolkit, String title, Display display, boolean isCollapseable,
                boolean isCollapsed, int estyle)
        {
            super(toolkit, title, display, isCollapseable, isCollapsed, estyle);
        }

        public void createSectionContent(Composite parent)
        {
            if (!(_table instanceof SybaseASATable))
            {
                return;
            }
            getSection().setLayoutData(new GridData(GridData.FILL_BOTH));
            getSection().setLayout(new GridLayout());

            GridLayout layout = new GridLayout();
            parent.setLayout(layout);
            parent.setLayoutData(new GridData(GridData.FILL_BOTH));

            Composite dbSpaceComp = _toolkit.createComposite(parent);
            layout = new GridLayout();
            layout.numColumns = 2;
            dbSpaceComp.setLayout(layout);

            LabelFormEntry spaceName = new LabelFormEntry(dbSpaceComp, _toolkit, SWT.NONE,
                    Messages.ASAStorageEditorPage_dbspace_name, 2);
            spaceName
                    .getValueLabel()
                    .setText(
                            ((SybaseASATable) _table).getDbSpace() == null ? "" : ((SybaseASATable) _table).getDbSpace().getName()); //$NON-NLS-1$

            LabelFormEntry spaceFile = new LabelFormEntry(dbSpaceComp, _toolkit, SWT.NONE,
                    Messages.ASAStorageEditorPage_file, 2);
            spaceFile
                    .getValueLabel()
                    .setText(
                            ((SybaseASATable) _table).getDbSpace() == null ? "" : ((SybaseASATable) _table).getDbSpace().getFileName()); //$NON-NLS-1$

            Composite pctFreeComp = _toolkit.createComposite(parent);
            layout = new GridLayout();
            layout.numColumns = 2;
            pctFreeComp.setLayout(layout);

            LabelFormEntry pctFreeLabel = new LabelFormEntry(pctFreeComp, _toolkit, SWT.NONE,
                    Messages.ASAStorageEditorPage_bytes_reserved_label, 2);
            pctFreeLabel.getValueLabel().setText(" "); //$NON-NLS-1$

            Composite pctFreeValueComp = _toolkit.createComposite(parent);
            layout = new GridLayout();
            layout.numColumns = 4;
            pctFreeValueComp.setLayout(layout);

            _defaultButton = _toolkit.createButton(pctFreeValueComp, Messages.ASAStorageEditorPage_default, SWT.RADIO);
            _defaultButton.setToolTipText(Messages.ASAStorageEditorPage_default_tip);
            _defaultButton.addSelectionListener(_defaultpctSelectionListener);
            _userDefinedButton = _toolkit.createButton(pctFreeValueComp, Messages.ASAStorageEditorPage_percentage,
                    SWT.RADIO);
            _userDefinedButton.setToolTipText(Messages.ASAStorageEditorPage_percentage_tip);
            _percentageSpinner = new Spinner(pctFreeValueComp, SWT.BORDER);
            _percentageSpinner.setToolTipText(Messages.ASAStorageEditorPage_percentage_tip);
            // By default, the percentage is 10
            _percentageSpinner.setSelection(10);
            _tipLabel = _toolkit.createLabel(pctFreeValueComp, Messages.ASAStorageEditorPage_204_default);
            GridData gd = new GridData(GridData.FILL_HORIZONTAL);
            _tipLabel.setLayoutData(gd);

            // -1 means default
            if (((SybaseASATable) _table).getPctfree() == -1)
            {
                _defaultButton.setSelection(true);
                _percentageSpinner.setEnabled(false);
            }
            else
            {
                _userDefinedButton.setSelection(true);
                _percentageSpinner.setSelection(((SybaseASATable) _table).getPctfree());
                int percentage = _percentageSpinner.getSelection();
                String info = "%  ("; //$NON-NLS-1$
                int reservedBytes = (percentage * 2048) / 100;
                info = info + reservedBytes + Messages.ASAStorageEditorPage_byte_reserved_for_table;
                _tipLabel.setText(info);
                ((SybaseASATable) _table).setPctfree(_percentageSpinner.getSelection());
            }

            _userDefinedButton.addSelectionListener(_udpctfreeSelectionListener);

            _percentageSpinner.addSelectionListener(_percentageSpinnerListener);

        }
    }

    public ASAStorageEditorPage()
    {
    }

    public ASAStorageEditorPage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
    }

    public ASAStorageEditorPage(String id, String title)
    {
        super(id, title);
    }

    protected void createFormContent(IManagedForm managedForm)
    {
        super.createFormContent(managedForm);
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) getEditor().getEditorInput();
        _table = (SybaseASATable) input.getEditModelObject().getMainSQLObject();

        managedForm.getForm().setText(Messages.ASAStorageEditorPage_storage);
        Composite parent = managedForm.getForm().getBody();
        parent.setLayout(new GridLayout());
        FormToolkit toolKit = managedForm.getToolkit();

        PhysicalStorageSection pSection = new PhysicalStorageSection(toolKit,
                Messages.ASAStorageEditorPage_physical_storage, parent.getDisplay(), false, false, SWT.NONE);
        pSection.createControl(parent, 1, null);

        managedForm.getForm().reflow(true);
    }

    public void modelRegenerated()
    {
        super.modelRegenerated();
    }

    public void refresh()
    {
        if (!isPageOpened())
        {
            return;
        }
        super.refresh();
        _defaultButton.removeSelectionListener(_defaultpctSelectionListener);
        _userDefinedButton.removeSelectionListener(_udpctfreeSelectionListener);
        _percentageSpinner.removeSelectionListener(_percentageSpinnerListener);

        // -1 means default
        if (((SybaseASATable) _table).getPctfree() == -1)
        {
            _defaultButton.setSelection(true);
            _userDefinedButton.setSelection(false);
            _percentageSpinner.setEnabled(false);
            _defaultButton.notifyListeners(SWT.Selection, new Event());
        }
        else
        {
            _userDefinedButton.setSelection(true);
            _defaultButton.setSelection(false);
            _percentageSpinner.setEnabled(true);
            int percentage = _percentageSpinner.getSelection();
            String info = "%  ("; //$NON-NLS-1$
            int reservedBytes = (percentage * 2048) / 100;
            info = info + reservedBytes + Messages.ASAStorageEditorPage_bytes_reserved;
            _tipLabel.setText(info);
            ((SybaseASATable) _table).setPctfree(_percentageSpinner.getSelection());
        }

        _defaultButton.addSelectionListener(_defaultpctSelectionListener);
        _userDefinedButton.addSelectionListener(_udpctfreeSelectionListener);
        _percentageSpinner.addSelectionListener(_percentageSpinnerListener);
    }
}
