/*
 * Created on 2005-4-28
 * 
 * Copyright (c) Sybase, Inc. 2004 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import java.io.IOException;
import java.util.ResourceBundle;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.services.SQLUIService;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.sqleditor.IPageUpdate;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.datatools.sqltools.sqleditor.internal.templates.dialog.EditTemplateDialog;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.persistence.TemplatePersistenceData;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.TextEditorAction;

/**
 * Add the selected text as a template in the template store.
 * @author Hui Cao
 *  
 */
public class AddTemplateAction extends TextEditorAction implements IPageUpdate
{
    private SQLEditor      _sqlEditor;
    private boolean _isSourcePage = true;

    /**
     *  
     */
    public AddTemplateAction(ResourceBundle bundle, String prefix, SQLEditor targetEditor)
    {
        super(bundle, prefix, targetEditor);
        setImageDescriptor(SQLEditorResources.getImageDescriptor("template_obj"));
        setActionDefinitionId(ISQLEditorActionConstants.SAVE_AS_TEMPLATE_ACTION_ID);
        setId(ISQLEditorActionConstants.SAVE_AS_TEMPLATE_ACTION_ID);
        _sqlEditor = targetEditor;
        update();

        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpContextIds.SAVE_AS_TEMPLATE_ACTION, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void update()
    {
        setEnabled(_isSourcePage && _sqlEditor != null && _sqlEditor.getSelectedText() != null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run()
    {
        Template template = new Template();
        template.setPattern(_sqlEditor.getSelectedText());
        
        SQLDevToolsUIConfiguration config = SQLToolsUIFacade.getConfigurationByVendorIdentifier(_sqlEditor.getConnectionInfo().getDatabaseVendorDefinitionId());
		SQLUIService sqlService = config.getSQLUIService();
        template.setContextTypeId(sqlService.getSQLContextType().getSQLContextId());

        Dialog dialog = new EditTemplateDialog(null, template, false, true,
            SQLEditorPlugin.getDefault().getTemplateContextTypeRegistry());
        if (dialog.open() == Window.OK)
        {
            TemplatePersistenceData data = new TemplatePersistenceData(template, true);
            SQLEditorPlugin.getDefault().getTemplateStore().add(data);
            try
            {
                SQLEditorPlugin.getDefault().getTemplateStore().save();
            }
            catch (IOException e)
            {
                openWriteErrorDialog(e);
            }
        }
    }

    private void openWriteErrorDialog(Exception e)
    {
        String title = Messages.AddTemplateAction_error_write_title; 
        String message = Messages.AddTemplateAction_error_write_message; 
        MessageDialog.openError(null, title, message);
    }

    public void update(boolean isSQLEditorPage)
    {
        _isSourcePage  = isSQLEditorPage;
        update();
    }

}
