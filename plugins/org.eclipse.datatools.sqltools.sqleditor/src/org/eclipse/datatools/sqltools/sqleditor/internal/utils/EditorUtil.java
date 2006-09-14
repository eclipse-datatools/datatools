/**
 * Created on 2005-11-30 Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.utils;

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * @author Hui Cao
 *
 */
public class EditorUtil
{

    public static void setEditorsProfileStatus(String profileName, int status)
    {
        IEditorReference[] ht = SQLEditorPlugin.getActiveWorkbenchPage().getEditorReferences();
        
        if (ht == null || ht.length == 0)
        {
            return;
        }
        for (int i = 0; i < ht.length; i++) {
        	IEditorReference ref = ht[i];
        	IEditorInput input = null;
			try {
				input = ref.getEditorInput();
			} catch (PartInitException e) {
				SQLEditorPlugin.getDefault().log(e);
			}
            if (input instanceof ISQLEditorInput)
            {
                ISQLEditorInput sqlEditorInput = (ISQLEditorInput) input;
                ISQLEditorConnectionInfo info = sqlEditorInput.getConnectionInfo();
                //FIXME: 
//                // Because now DMP support multi-db, profile can be used for whole database server(multi-db) or just
//                // one database.
//                // So, we still need to check the validation of database name used in SqlEditor.
//                // But for RepServer, we do not do this check
//                // @hpgu
//                List databaseList = new ArrayList();
//
//                try
//                {
//                    databaseList = ProfileUtil.getDatabaseList(profileName);
//                    if ((databaseList != null) && (databaseList.size() > 0))
//                    {
//                        if (!databaseList.contains(s.getInputObject().getDatabaseIdentifier().getDBname()))
//                        {
//                            status = EditorConstants.CP_STATUS_DISCONNECTED;
//                        }
//                    }
//                }
//                catch (Exception e)
//                {
//                    status = EditorConstants.CP_STATUS_DISCONNECTED;
//                }
                info.setProfileStatus(status);
                SQLEditor editor = (SQLEditor)ref.getEditor(false);
            	if (editor != null)
            	{
            		//to refresh ui
            		editor.setConnectionInfo(info);
            	}
            }
		}
    }
    
    /**
     * As long as there're dirty editors, don't close
     * @param profileName
     * @return
     */
	public static boolean okToCloseEditors(String profileName)
	{
		IEditorReference[] ht = SQLEditorPlugin.getActiveWorkbenchPage().getEditorReferences();
        
        if (ht == null || ht.length == 0)
        {
            return true;
        }
        for (int i = 0; i < ht.length; i++) {
        	final IEditorReference ref = ht[i];
        	IEditorInput input = null;
			try {
				input = ref.getEditorInput();
			} catch (PartInitException e) {
				SQLEditorPlugin.getDefault().log(e);
			}
			//(external)file editors can be saved in disconnected mode
            if (input instanceof ISQLEditorInput && !(input instanceof FileEditorInput || input instanceof ILocationProvider))
            {
                ISQLEditorInput sqlEditorInput = (ISQLEditorInput) input;
                ISQLEditorConnectionInfo info = sqlEditorInput.getConnectionInfo();
                if (profileName.equals(info.getConnectionProfileName()))
        		{
                	final boolean[] ok = new boolean[]{true};
                	SQLEditorPlugin.getDisplay().syncExec(new Runnable(){
                		public void run()
                		{
                        	//isDirty should be called in UI thread
                        	if (ref.isDirty())
                        	{
                        		MessageDialog.openError(null, Messages.Disconnect_error, Messages.Cannot_disconnect_before_save);
                            	ok[0] = false;
                        	}
                		}
                	});
                	if (!ok[0])
                	{
                		return false;
                	}
        		}
            }
        	
        }
		return true;
	}
	
	/**
	 * Tries to close all editors associated with the connection profile. Always returns true.
	 * @param profileName
	 * @return
	 */
    public static boolean closeAllEditors(String profileName)
    {
        IEditorReference[] ht = SQLEditorPlugin.getActiveWorkbenchPage().getEditorReferences();
        
        if (ht == null || ht.length == 0)
        {
            return true;
        }
        boolean success = true;
        for (int i = 0; i < ht.length; i++) {
        	IEditorReference ref = ht[i];
        	IEditorInput input = null;
			try {
				input = ref.getEditorInput();
			} catch (PartInitException e) {
				SQLEditorPlugin.getDefault().log(e);
			}
            if (input instanceof ISQLEditorInput)
            {
                ISQLEditorInput sqlEditorInput = (ISQLEditorInput) input;
                ISQLEditorConnectionInfo info = sqlEditorInput.getConnectionInfo();
                if (profileName.equals(info.getConnectionProfileName()))
        		{
                	ITextEditor editor = (ITextEditor)ref.getEditor(false);
                	if (editor != null)
                	{
                		editor.close(true);
                	}
        		}
            }
        }
        return success;
    }

    public static void renameEditorsProfileName(String oldName, String newName)
    {
        IEditorReference[] ht = SQLEditorPlugin.getActiveWorkbenchPage().getEditorReferences();
        
        if (ht == null || ht.length == 0)
        {
            return;
        }
        for (int i = 0; i < ht.length; i++) {
        	IEditorReference ref = ht[i];
        	IEditorInput input = null;
			try {
				input = ref.getEditorInput();
			} catch (PartInitException e) {
				SQLEditorPlugin.getDefault().log(e);
			}
            if (input instanceof ISQLEditorInput)
            {
                ISQLEditorInput sqlEditorInput = (ISQLEditorInput) input;
                ISQLEditorConnectionInfo info = sqlEditorInput.getConnectionInfo();
                if (oldName.equals(info.getConnectionProfileName()))
        		{
                	info.setConnectionProfileName(newName);
                	SQLEditor editor = (SQLEditor)ref.getEditor(false);
                	if (editor != null)
                	{
                		//to refresh ui
                		editor.setConnectionInfo(info);
                	}
        		}
            }
        }
     }

}
