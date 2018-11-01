/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui.launching;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.IEvent;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterWrapper;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.datatools.sqltools.routineeditor.parameter.EventParameter;
import org.eclipse.datatools.sqltools.routineeditor.parameter.internal.EventParameterTableDialog;
import org.eclipse.datatools.sqltools.routineeditor.parameter.internal.ParameterTableDialog;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.dialogs.ListSelectionDialog;

/**
 * @author Yang Liu
 */
public class LaunchUI
{

    /**
     * This method will popup a dialog for config parameter values.
     * 
     * @param shell
     * @param pds parameter descriptors. can't be null
     * @param valueList this parameter is both input and output. Input as initial value. Output as result value if
     *            success. can't be null.
     * @param always whether always popup a dialog even though no parameter is needed.
     * @return null means the user clicks Cancel
     * 		   not null means the parameter configs are modified
     */
    public static List configParameter(Shell shell, ParameterDescriptor[] pds, List valueList, boolean always, ILaunchConfiguration configuration)
    {
        if ((pds == null || pds.length == 0) && !always)
        {
            // there is no need to config parameters, so just clear the value list and return
            valueList.clear();
            return valueList;
        }
        ParameterWrapper[] wrappers = getParameterWrapper(pds, valueList);
        Dialog dialog = null;
        try {
			DatabaseIdentifier dbid = LaunchHelper.readDatabaseIdentifier(configuration);
			SQLDevToolsUIConfiguration conf = SQLToolsUIFacade.getConfiguration(dbid, null);
			dialog = conf.getUIComponentService().getParameterTableDialog(shell, wrappers, configuration);
		} catch (CoreException e) {
			RoutineEditorUIActivator.getDefault().log(e);
		}
        if (dialog == null)
        {
        	dialog = new ParameterTableDialog(shell, wrappers);
        }

        if (dialog.open() == Dialog.OK)
        {
            valueList.clear();
            for (int i = 0; i < wrappers.length; i++)
            {
                if (wrappers[i].isNull())
                valueList.add(null);
                else
                valueList.add(wrappers[i].getValue());
            }
            return valueList;
        }
        else
        {
            return null;
        }
    }

    /**
     * @param pds
     * @param valueList
     * @param wrappers
     */
    public static ParameterWrapper[] getParameterWrapper(ParameterDescriptor[] pds, List valueList)
    {
        ParameterWrapper[] wrappers = new ParameterWrapper[pds.length];
        for (int i = 0; i < pds.length; i++)
        {
            wrappers[i] = new ParameterWrapper(pds[i]);
            if (valueList.size() > i && valueList.get(i) != null)
            {
                wrappers[i].setNull(false);
                wrappers[i].setValue((String) valueList.get(i));
            }
            else
            {
                //get parameter default value
                String defaultValue = wrappers[i].getParameterDescriptor().getDefaultValue();
                if (defaultValue!=null)
                {
                    wrappers[i].setNull(false);
                    wrappers[i].setValue(defaultValue);
                }
                else 
                {
                    wrappers[i].setNull(true);
                }
            }
        }
        return wrappers;
    }

    /**
     * This dialog will popup a dialog for user to config the event trigger parameter dialog.
     * 
     * @param shell
     * @param pNames the parameter names.
     * @param eventTriggerParams this is both input and output. Input as initial value, output as result value.
     *            String->string map.
     * @param eventTypeId the current event type id.
     * @return true if success, false is user canceled.
     */
    public static boolean configEventParameters(Shell shell, String[] pNames, Map eventTriggerParams,ProcIdentifier proc)
    {
        for (int i = 0; i < pNames.length; i++)
        {
            if (!eventTriggerParams.containsKey(pNames[i]))
            {
                eventTriggerParams.put(pNames[i],"");
            }
        }
        EventParameter[] eps = new EventParameter[eventTriggerParams.size()];

        int mapsize = eventTriggerParams.size();

        Iterator keyValuePairs1 = eventTriggerParams.entrySet().iterator();
        for (int i = 0; i < mapsize; i++)
        {
            Map.Entry entry = (Map.Entry) keyValuePairs1.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (eps[i]==null)
            {
                eps[i]= new EventParameter();
            }
            if (key!=null)
            {
                eps[i].setName((String)key);
            }
            if (value!=null)
            {
                eps[i].setValue((String)value);
            }
        }


        if (eps.length >= 0)//always popup the dialog
        {
            IControlConnection con;
            try
            {
                con = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(proc.getDatabaseIdentifier());
            }
            catch (Exception e)
            {
                RoutineEditorUIActivator.getDefault().log(e);
                //TODO: popup error dialog
                return false;
            }
            EventParameterTableDialog dlg = new EventParameterTableDialog(shell, eps,(IEvent)con.getDBItem(proc));
            if (dlg.open() == EventParameterTableDialog.OK)
            {
                eps = dlg.getEventParameter();
                eventTriggerParams.clear();
                for (int i = 0; i < eps.length; i++)
                {
                    if (!(eps[i].getValue() == null || "".equals(eps[i].getValue()) || "\'\'".equals(eps[i] //$NON-NLS-1$ //$NON-NLS-2$
                    .getValue())))
                    {
                        String name  = eps[i].getName();
                        String value =  eps[i].getValue();
                        eventTriggerParams.put(name, value);
                    }
                }
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }
    
    /**
     * get the current procidentifier from UI. If currently a ProcIdentifier is selected, or if the active editor is
     * showing a proc, then that is returned. Otherwise will return null
     * 
     * @return
     */
    public static ProcIdentifier getActiveProcIdentifier()
    {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window == null)
        return null;

        IWorkbenchPage page = window.getActivePage();
        if (page == null)
        return null;

        ISelection selection = page.getSelection();
        if (selection instanceof IStructuredSelection)
        {
            IStructuredSelection ss = (IStructuredSelection) selection;
            if (!ss.isEmpty())
            {
                Object obj = ss.getFirstElement();
                //TODO check sql model Routine
                if (obj instanceof ProcIdentifier) 
                {
                    return (ProcIdentifier) obj; 
                }
            }
        }
        IEditorPart part = page.getActiveEditor();
        if (part != null)
        {
            IEditorInput input = part.getEditorInput();
            return (ProcIdentifier) input.getAdapter(ProcIdentifier.class);
        }

        return null;
    }

    /**
     * 
     * @param shell
     * @param profile
     * @param type FIXME: here if type is TYPE_SP, then covers both TYPE_SP and TYPE_UDF	
     * @param old
     * @return
     * @throws SQLException
     * @throws NoSuchProfileException
     */
    public static ProcIdentifier selectDatabaseObject(Shell shell, DatabaseIdentifier databaseIdentifier, int type, ProcIdentifier old) throws SQLException, NoSuchProfileException
    {
        IControlConnection controlCon = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(databaseIdentifier);
        ProcIdentifier[] procids = controlCon.getAllProcs();
        List resultlist = new ArrayList();
        for (int i=0; i<procids.length; i++)
        {
            if (type == procids[i].getType())
            {
                resultlist.add(procids[i]);
            }
            else 
            {
            }
        }
        // Sort the proc identifier
        Collections.sort(resultlist, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {

                return ((ProcIdentifier) o1).getLongDisplayString().compareToIgnoreCase(
                        ((ProcIdentifier) o2).getLongDisplayString());
            }
        });
        procids = (ProcIdentifier[])resultlist.toArray(new ProcIdentifier[resultlist.size()]);
        ListDialog dialog = new ListDialog(shell);
        switch (type)
        {
            case (ProcIdentifier.TYPE_SP):
                dialog.setTitle(Messages.LaunchUI_selectSP); 
                dialog.setMessage(Messages.LaunchUI_selectSPMessage); 
                break;
            case (ProcIdentifier.TYPE_UDF):
                dialog.setTitle(Messages.LaunchUI_selectUDF); 
                dialog.setMessage(Messages.LaunchUI_selectUDFMessage); 
                break;
            case (ProcIdentifier.TYPE_EVENT):
                dialog.setTitle(Messages.LaunchUI_selectEVENT); 
                dialog.setMessage(Messages.LaunchUI_selectEVENTMessage); 
                break;
            case (ProcIdentifier.TYPE_TRIGGER):
                dialog.setTitle(Messages.LaunchUI_selectTRIGGER); 
                dialog.setMessage(Messages.LaunchUI_selectTRIGGERMessage); 
                break;
        }
        dialog.setInput(procids);

        dialog.setContentProvider(new IStructuredContentProvider()
        {
            public Object[] getElements(Object inputElement)
            {
                return (Object[])inputElement;
            }

            public void dispose()
            {
            }

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
            {
            }
        }
        );

        dialog.setLabelProvider(new LabelProvider()
        {
            public String getText(Object element)
            {
                return ((ProcIdentifier) element).getLongDisplayString();
            }
        }
        );

        if (old != null)
        {
            dialog.setInitialSelections(new Object[]
            {
                old
            }
            );
        }

        if (dialog.open() == ListSelectionDialog.OK)
        {
            Object[] result = dialog.getResult();
            if (result == null || result.length == 0) return null;
            else return (ProcIdentifier)result[0];
        }
        else
        {
            return null;
        }
    }

}
