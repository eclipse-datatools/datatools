/**
 * Created on 2005-12-19
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.debugger.view.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerCoreUIPlugin;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.PartInitException;

/**
 * 
 * @author sfyu
 */
public class DebugEventListener implements IDebugEventSetListener
{

    public void handleDebugEvents(DebugEvent[] events)
    {
        for (int i = 0; i < events.length; i++)
        {
            if(!(events[i].getSource() instanceof IDebugElement))
            {
                continue;
            }

            if(events[i].getKind() == DebugEvent.TERMINATE && SPDebugModelUtil.getModelIdentifier().equals(((IDebugElement)events[i].getSource()).getModelIdentifier()))
            {
                // In SQL debugger framwork, every time the debug finishes or is terminated, there will be a terminate
                // event.
                // Therefoce, it's not neccessary to judge DebugEvent.RESUME
                DebuggerCoreUIPlugin.getDisplay().syncExec(new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                        	DebuggerCoreUIPlugin.getActiveWorkbenchPage().showView(ResultsConstants.SQL_RESULTS_VIEW_ID);
                        }
                        catch (PartInitException e)
                        {
                            IStatus stat = new Status(IStatus.ERROR, EditorCorePlugin.PLUGIN_ID, 0,
                                e.getMessage() == null ? "" : e //$NON-NLS-1$
                                .getMessage(), e);

                            String title = Messages.DebugEventListener_error; 
                            ErrorDialog.openError(DebuggerCoreUIPlugin.getActiveWorkbenchShell(), title, Messages.DebugEventListener_open_result_view_error, //$NON-NLS-1$
                            stat);
                        }
                    }
                }
                );
            }
        }

    }

}
