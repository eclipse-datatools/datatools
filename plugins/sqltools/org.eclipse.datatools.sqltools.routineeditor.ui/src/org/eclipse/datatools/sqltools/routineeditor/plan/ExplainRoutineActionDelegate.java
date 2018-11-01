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
package org.eclipse.datatools.sqltools.routineeditor.plan;

import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.SQLEditorService;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.launching.IExtendedLaunchSupport;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.launching.SPLaunchShortcut;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.plan.ExplainSQLActionDelegate;
import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * This class can explain either routine object or SQL statement.
 * 
 * @author Dafan Yang
 */
public class ExplainRoutineActionDelegate extends ExplainSQLActionDelegate
{
    protected String               _procSql;
    protected ILaunchConfiguration _config;
    IExtendedLaunchSupport         _extendedLaunchSupport = null;

    public ExplainRoutineActionDelegate(SQLEditor targetEditor)
    {
        super(targetEditor);
    }

    public void run()
    {
        /**
         * If it's a routine object, needs to generate the invocation SQL statement first
         */
        if(explainAsRoutineObject())
        {
            final ProcEditorInput procEditorInput = (ProcEditorInput) _sqlEditor.getEditorInput();
            try
            {
                Runnable getQPRunnable = new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            // can not run <code>getLaunchConfiguration()<code> in UI thread 
                            _config = SPLaunchShortcut.getLaunchConfiguration(procEditorInput.getProcIdentifier(), "run");
                            if (_config == null)
                            {
                                return;
                            }
                            _procSql = LaunchHelper.constructDirectInvocationSQLString(_config);
                            
                            _conn = SQLToolsFacade.getConnectionService(getDatabaseIdentifier()).createConnection(
                                    getDatabaseIdentifier(), true);
                            
                            SQLEditorService s = SQLToolsFacade.getConfiguration(null,
                                    getDatabaseIdentifier()).getSQLEditorService();
                            _extendedLaunchSupport = s.getExtendedLaunchSupport();
                            if(_extendedLaunchSupport != null)
                            {
                                _extendedLaunchSupport.preLaunch(_config, _conn, "run");
                            }
                            
                            // The super code should be in UI thread
                            RoutineEditorUIActivator.getStandardDisplay().syncExec(new Runnable()
                            {
                                public void run()
                                {
                                    ExplainRoutineActionDelegate.this.runDelegate();
                                }
                            });
                            
                        }
                        catch (Exception e)
                        {
                        }
                    }
                };

                Thread th = new Thread(getQPRunnable);
                th.start();
            }
            catch (Exception ex)
            {
                
            }
        }
        else
        {
            super.run();
        }
    }

    private void runDelegate()
    {
        super.run();
    }
    
    public String getSQLStatements()
    {
        if(explainAsRoutineObject())
        {
            return _procSql;
        }
        else
        {
            return super.getSQLStatements();
        }
    }

    protected boolean explainAsRoutineObject()
    {
        if (isRoutineEditor()
                && (_sqlEditor.getSelectedText() == null || _sqlEditor.getSelectedText().trim().equals("")))
        {
            return true;
        }
        return false;
    }

    protected boolean isRoutineEditor()
    {
        return ((_sqlEditor != null) && (_sqlEditor.getEditorInput() instanceof ProcEditorInput));
    }
    
    protected boolean isSelectionEmpty()
    {
        return (_sqlEditor == null || _sqlEditor.getSelectedText() == null || _sqlEditor.getSelectedText().trim()
                .equals(""));
    }

    public void update()
    {
        if (isRoutineEditor())
        {
            ProcEditorInput procEditorInput = (ProcEditorInput) _sqlEditor.getEditorInput();
            int procType = procEditorInput.getProcIdentifier().getType();
            IPlanService service = SQLToolsUIFacade.getPlanService(getDatabaseIdentifier());
            boolean supported = false;
            if(service != null)
            {
                supported = service.getPlanOption() == null ? false : service.getPlanOption().supportPlan(procType);
            }
            if (!supported)
            {
                if (isSelectionEmpty())
                {
                    setEnabled(false);
                }
                else
                {
                    setEnabled(_sqlEditor != null && (_sqlEditor.getConnectionInfo().getSharedConnection() != null)
                            && super.canBeEnabled());
                }
            }
        }
        else
        {
            setEnabled(_sqlEditor != null && (_sqlEditor.isConnected()) && super.canBeEnabled());
        }
    }
}
