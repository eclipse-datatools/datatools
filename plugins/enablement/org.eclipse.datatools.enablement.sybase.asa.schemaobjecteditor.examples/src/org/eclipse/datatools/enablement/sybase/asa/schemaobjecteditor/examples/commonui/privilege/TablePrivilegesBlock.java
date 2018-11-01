/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;

/**
 * Privileges block for table privileges.
 * <p>
 * 
 * @author Idull
 */
public abstract class TablePrivilegesBlock extends PrivilegesBlock
{
    protected CCombo     _columnsCombo;

    public TablePrivilegesBlock(Composite parent, ISchemaObjectEditModel model, SchemaObjectEditorPage page)
    {
        super(parent, model, page);
    }

    /**
     * Subclass can override this method to create controls under "Columns" group
     * 
     * @param toolkit
     * @param parent
     */
    protected void createAdditionalPart(FormToolkit toolkit, Composite parent)
    {

    }

    protected void createMasterPart(IManagedForm managedForm, Composite parent)
    {
        _managedForm = managedForm;

        FormToolkit toolkit = managedForm.getToolkit();
        ScrolledPageBook pageBook = toolkit.createPageBook(parent, SWT.H_SCROLL);
        pageBook.setLayoutData(new GridData(GridData.FILL_BOTH));
        pageBook.setLayout(new GridLayout());
        Composite comp = pageBook.createPage("masterpart");
        comp.setLayout(new GridLayout());
        comp.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        Composite masterComp = toolkit.createComposite(comp);
        masterComp.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        masterComp.setLayout(layout);
        //scrolledComp.setContent(masterComp);
        
        Composite columnsComp = toolkit.createComposite(masterComp);
        layout = new GridLayout();
        columnsComp.setLayout(layout);
        GridData gd = new GridData();
        gd.verticalAlignment=SWT.TOP;
        columnsComp.setLayoutData(gd);
        
        toolkit.createLabel(columnsComp, Messages.TablePrivilegesBlock_columns);
        
        _columnsCombo = new CCombo(columnsComp, SWT.READ_ONLY);
        toolkit.adapt(columnsComp);
        toolkit.paintBordersFor(columnsComp);
        gd = new GridData();
        gd.widthHint = 150;
        _columnsCombo.setLayoutData(gd);
        _columnsCombo.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                ISelection sel = new StructuredSelection(new Object[]
                {
                    _columnsCombo.getData(_columnsCombo.getText())
                });
                _managedForm.fireSelectionChanged(_masterPart, sel);
            }
        });
        Composite additionalComp = toolkit.createComposite(masterComp);
        layout = new GridLayout();
        layout.marginWidth = 0;
        additionalComp.setLayout(layout);
        gd = new GridData(GridData.FILL_BOTH);
        gd.verticalAlignment = SWT.TOP;
        
        additionalComp.setLayoutData(gd);
        
        createAdditionalPart(toolkit, additionalComp);
        refresh();
        pageBook.showPage("masterpart");
        super.createMasterPart(managedForm, parent);
        sashForm.addControlListener(new ControlListener(){

            public void controlMoved(ControlEvent e)
            {
                
            }

            //Workaround to FIX469806-1, the tree can not refresh itself after resizing
            //Since it's a Eclipse's defect, this should be removed in the future
            public void controlResized(ControlEvent e)
            {
                detailsPart.refresh();
            }});
    }

    protected void createToolBarActions(IManagedForm managedForm)
    {
        super.createToolBarActions(managedForm);

        // Notify the listener to show the detail part
        _columnsCombo.notifyListeners(SWT.Selection, new Event());
    }

    public void refresh()
    {
        super.refresh();
        int selection = _columnsCombo.getSelectionIndex();
        _columnsCombo.removeAll();
        _columnsCombo.add(Messages.TablePrivilegesBlock_all_columns);
        _columnsCombo.setData(Messages.TablePrivilegesBlock_all_columns, _model.getMainSQLObject());
        Iterator iter = ((Table)_model.getMainSQLObject()).getColumns().iterator();
        while (iter.hasNext())
        {
            Column col = (Column) iter.next();
            if(col.getName()==null || col.getName().length()==0)
            {
                continue;
            }
            String colDisplayName = col.getName();
            if(col.getDataType() != null)
            {
                colDisplayName = colDisplayName + " [" + IDataToolsUIServiceManager.INSTANCE.getColumnHelperService().getDataType(col) + "]"; //$NON-NLS-1$ //$NON-NLS-2$
            }

            _columnsCombo.add(colDisplayName);
            _columnsCombo.setData(colDisplayName, col);
        }
        
        //Sets visible item count
        if (_columnsCombo.getItemCount() > 10)
        {
            _columnsCombo.setVisibleItemCount(10);
        }
        else
        {
            _columnsCombo.setVisibleItemCount(_columnsCombo.getItemCount());
        }
        
        //Set to the previous selection
        if (selection > 0 && selection < _columnsCombo.getItemCount())
        {
            _columnsCombo.select(selection);
        }
        else
        {
        _columnsCombo.select(0);
        }
        _columnsCombo.notifyListeners(SWT.Selection, new Event());
    }

    public void notifyChanged(Notification msg)
    {
        super.notifyChanged(msg);
    }

	/**
	 * All the detail pages will be recreated since all the SQL object is re-generated.
	 * 
	 */
	public void refreshDetailPages() 
	{
		int currentSelection = _columnsCombo.getSelectionIndex();
        refresh();
        if (currentSelection >= _columnsCombo.getItemCount())
        {
            currentSelection = 0;
        }
        _columnsCombo.select(currentSelection);
        ISelection sel = new StructuredSelection(new Object[]
        {
            _columnsCombo.getData(_columnsCombo.getText())
        });
        _managedForm.fireSelectionChanged(_masterPart, sel);
	}
    
    
}
