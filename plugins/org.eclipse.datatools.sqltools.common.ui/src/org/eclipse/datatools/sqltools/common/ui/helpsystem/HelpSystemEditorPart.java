/**
 * Created on 2007-4-19
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.helpsystem;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * 
 * @author lihuang
 */
public class HelpSystemEditorPart extends EditorPart implements IContextProvider
{
    protected CommonContextProvider _contextProvider = new CommonContextProvider();

    public IContext getContext(Object target)
    {
        return _contextProvider.getContext(target);
    }

    public int getContextChangeMask()
    {
        return _contextProvider.getContextChangeMask();
    }

    public String getSearchExpression(Object target)
    {
        return _contextProvider.getSearchExpression(target);
    }

    
    public void doSave(IProgressMonitor monitor)
    {
    }

    
    public void doSaveAs()
    {
    }

    
    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
    }

    
    public boolean isDirty()
    {
        return false;
    }

    
    public boolean isSaveAsAllowed()
    {
        return false;
    }

    
    public void createPartControl(Composite parent)
    {
        
    }

    
    public void setFocus()
    {
        
    }
}
