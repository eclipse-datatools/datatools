/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.ddl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.sqltools.common.ui.sqlstatementarea.SQLStatementArea;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.IDDLProvider;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.ui.SQLSourceViewerService;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.utils.Images;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Util;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * The DDL page
 * 
 * @author Idull
 */
public class DDLPage extends SchemaObjectEditorPage implements ISchemaObjectEditorPage
{
    private SQLStatementArea _sta;
    private Map              _actions = new HashMap();
    private IDDLProvider     _provider;
    private Color            _bgColor;

    public DDLPage()
    {

    }

    public DDLPage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
    }

    public DDLPage(String id, String title)
    {
        super(id, title);
    }

    protected void createFormContent(IManagedForm managedForm)
    {
        super.createFormContent(managedForm);

        // set the ddl provider
        initDDLProvider();

        Composite comp = managedForm.getForm().getBody();
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        comp.setLayout(layout);

        createSQLArea(comp);
    }

    private void initDDLProvider()
    {
        ISchemaObjectEditor editor = (ISchemaObjectEditor) getEditor();
        IEditorPart part = (IEditorPart) editor;
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) part.getEditorInput();
        if (input.getEditModelObject().getAdapter(IDDLProvider.class) != null)
        {
            _provider = (IDDLProvider) input.getEditModelObject().getAdapter(IDDLProvider.class);
        }
    }

    private void initializeActions()
    {
        CommonAction action = new CommonAction(_sta.getViewer().getTextOperationTarget(), ITextOperationTarget.COPY,
                Messages.DDLPage_copy);
        action.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
                ISharedImages.IMG_TOOL_COPY));
        _actions.put(ITextEditorActionConstants.COPY, action);

        action = new CommonAction(_sta.getViewer().getTextOperationTarget(), ITextOperationTarget.SELECT_ALL,
                Messages.DDLPage_selectall);
        _actions.put(ITextEditorActionConstants.SELECT_ALL, action);
    }

    private void createSQLArea(Composite comp)
    {
        ISchemaObjectEditor editor = (ISchemaObjectEditor) getEditor();
        String dbType = editor.getEditorDescriptor().getVendorName() + "_" + editor.getEditorDescriptor().getVersion(); //$NON-NLS-1$
        _sta = new SQLStatementArea(comp, SWT.NONE, new SQLSourceViewerService(), true);
        String ddlContent = "";
        if (_provider != null)
        {
            ddlContent = _provider.getDDL();
        }
        _sta.setInput(ddlContent, dbType);
        _sta.setEditable(false);
        _sta.setEnabled(true);
        _sta.configureViewer(new SQLSourceViewerConfiguration(dbType));
        _sta.setLayoutData(new GridData(GridData.FILL_BOTH));
        _bgColor = Util.getBackGroundColor(_sta.getViewer().getControl().getDisplay());
        if (_bgColor != null)
        {
            _sta.getViewer().getTextWidget().setBackground(_bgColor);
        }

        initializeActions();
        createContextMenu();
    }

    private void createContextMenu()
    {
        final MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                // update items' status
                Iterator iter = _actions.values().iterator();
                while (iter.hasNext())
                {
                    Object obj = iter.next();
                    if (obj instanceof IUpdate)
                    {
                        IUpdate action = (IUpdate) obj;
                        action.update();
                    }
                }
                menuMgr.add((IAction) _actions.get(ITextEditorActionConstants.COPY));
                menuMgr.add((IAction) _actions.get(ITextEditorActionConstants.SELECT_ALL));
                menuMgr.add(new Separator());
                String ddlContent = "";
                if (_provider != null)
                {
                    ddlContent = _provider.getDDL();
                }

                SaveAsAction saveAs = new SaveAsAction(ddlContent, (ISchemaObjectEditor) getEditor(),
                        getDatabaseIdentifier());
                saveAs.setText(Messages.DDLPage_saveas);
                saveAs.setImageDescriptor(Images.DESC_SAVEAS_ACTION);
                saveAs.setEnabled(ddlContent != null && !ddlContent.trim().equals(""));
                menuMgr.add(saveAs);
            }
        });
        Menu menu = menuMgr.createContextMenu(_sta.getViewer().getTextWidget());
        _sta.getViewer().getTextWidget().setMenu(menu);
        _sta.getViewer().getTextWidget().addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent event)
            {
                if (event.keyCode == 'a' && (event.stateMask & SWT.CTRL) != 0)
                {
                    CommonAction action = (CommonAction) _actions.get(ITextEditorActionConstants.SELECT_ALL);
                    if (action != null)
                    {
                        action.run();
                    }
                }
            }
        });
    }

    /**
     * Refresh to display to new DDL
     */
    public void refresh()
    {
        super.refresh();
        if (!isPageOpened())
        {
            return;
        }
        initDDLProvider();
        updateDDL();
    }

    private void updateDDL()
    {
        String ddlContent = "";
        if (_provider != null)
        {
            ddlContent = _provider.getDDL();
        }
        ISchemaObjectEditor editor = (ISchemaObjectEditor) getEditor();
        String dbType = editor.getEditorDescriptor().getVendorName() + "_" + editor.getEditorDescriptor().getVersion(); //$NON-NLS-1$
        // The SQL statement area may not be created
        if (_sta != null)
        {
            _sta.setInput(ddlContent, dbType);
        }
    }

    public void setActive(boolean active)
    {
        super.setActive(active);
        if (active)
        {
            updateDDL();
        }
    }

    public void dispose()
    {
        super.dispose();
        if (_bgColor != null && !_bgColor.isDisposed())
        {
            _bgColor.dispose();
        }
    }
}

class CommonAction extends Action implements IUpdate
{
    private int                  _operationCode;
    private ITextOperationTarget _target;

    /**
     * 
     */
    public CommonAction(ITextOperationTarget target, int operationCode, String itemName)
    {
        super();
        _operationCode = operationCode;
        _target = target;
        this.setText(itemName);
    }

    /**
     * delegate the call to ITextOperationTarget instance
     */
    public void run()
    {
        _target.doOperation(_operationCode);
    }

    public void update()
    {
        boolean wasEnabled = isEnabled();
        boolean isEnabled = (_target != null && _target.canDoOperation(_operationCode));
        setEnabled(isEnabled);

        if (wasEnabled != isEnabled)
        {
            firePropertyChange(ENABLED, wasEnabled ? Boolean.TRUE : Boolean.FALSE, isEnabled ? Boolean.TRUE
                    : Boolean.FALSE);
        }
    }
}
