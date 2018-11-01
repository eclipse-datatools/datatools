/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.action;

import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.ProceduralObjectEditorInput;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.model.ProceduralObjectEditModel;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.model.ProceduralObjectImmutableModel;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.general.RoutineGeneralPage;
import org.eclipse.datatools.enablement.sybase.virtual.ParametersNode;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.actions.EditRoutineAction;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action.IEditSchemaObjectAction;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.Constants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Messages;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.SchemaObjectEditorUtils;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;


/**
 * TODO check "link with editor"
 * 
 * @author Hui Cao
 * 
 */
public class EditRoutineInFormAction extends EditRoutineAction implements IEditSchemaObjectAction
{

    class OpenRoutineEditorJob extends Job
    {
        public OpenRoutineEditorJob(String name)
        {
            super(name);
        }

        protected IStatus run(IProgressMonitor monitor)
        {
            if (monitor == null)
            {
                monitor = manager.createProgressGroup();
            }

            monitor.beginTask(
                    org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.action.Messages.EditRoutineObjectAction_open_routine_editor,
                    IProgressMonitor.UNKNOWN);            
            _progressMonitor = monitor;
            EditRoutineInFormAction.this.run(monitor);
            if (monitor.isCanceled())
            {
                return Status.CANCEL_STATUS;
            }
            return Status.OK_STATUS;
        }
    }

    private String editorId      = null;

    private String defaultPageId = null;
    
    protected Object _selectedResource = null;
    
    protected final static int ADD_PARAMETER = 0;
    protected final static int EDIT_PARAMETER = 1;
    protected int actionId  =  EDIT_PARAMETER ;
    
    protected String _pageId = null;
    
    private IEditorPart _ediotrPart;
    
    private IProgressMonitor _progressMonitor;
    
    public EditRoutineInFormAction()
    {
        super();
    }
    
    public EditRoutineInFormAction(Object selectedResource)
    {
        super(selectedResource);
        _selectedResource = selectedResource;
    }

    public EditRoutineInFormAction(Object selectedResource, String editorId)
    {
        this(selectedResource, editorId, null);
        _selectedResource = selectedResource;
    }

    public EditRoutineInFormAction(Object selectedResource, String editorId, String defaultPageId)
    {
        super(selectedResource);
        this.editorId = editorId;
        this.defaultPageId = defaultPageId;
        _selectedResource = selectedResource;
    }

    public void setDefaultPageId(String defaultPageId)
    {
        this.defaultPageId = defaultPageId;
    }
    
    public void run()
    {
        OpenRoutineEditorJob job = new OpenRoutineEditorJob(
                org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.action.Messages.EditRoutineObjectAction_opening);
        job.setUser(true);
        job.schedule();
    }
    
    public void run(IProgressMonitor monitor)
    {        
        IEditorPart ediotrPart = openEditor();
        if (_selectedResource instanceof Parameter || _selectedResource instanceof ParametersNode)
        {
            if (ediotrPart instanceof SchemaObjectEditor)
            {
                final SchemaObjectEditor editor = (SchemaObjectEditor) ediotrPart;
                ExamplePlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
                {
                    public void run()
                    {                        
                        if (_pageId != null)
                        {
                            editor.setActivePage(_pageId);
                        }
                        if (actionId == EDIT_PARAMETER)
                        {
                            editor.getActiveEditorPage().setFocus(RoutineGeneralPage.PARAMETER_FOCUS, _selectedResource);
                        }
                        else if (actionId == ADD_PARAMETER)
                        {
                            editor.getActiveEditorPage().setFocus(RoutineGeneralPage.PARAMETER_FOCUS, null);
                        }
                    }
                });
            }
        }
    }
    
    /**
     * Call after setSQLObject
     * 
     */
    public void prepare()
    {
        if (_sqlObject != null)
        {
            init();
            initSQLObject(this, _sqlObject);
            initConnectionProfile();
        }
    }

    private void objectNotFound()
    {
    	ExamplePlugin.getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                String[] buttons = new String[]
                {
                    IDialogConstants.OK_LABEL
                };
                MessageDialog d = new MessageDialog(ExamplePlugin.getActiveWorkbenchShell(),
                        org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.action.Messages.EditRoutineObjectAction_obj_not_found, null,
                        NLS.bind(org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.action.Messages.EditRoutineObjectAction_obj_not_found_detail, _sqlObject.getName()), MessageDialog.ERROR, buttons, 0);
                d.open();
            }
        });
    }
    
    public IEditorPart openEditor()
    {
        _ediotrPart = null;
        
        if (_progressMonitor != null)
        {
            _progressMonitor
                    .subTask(org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.action.Messages.EditRoutineObjectAction_read_objects);
        }
        
        if(_selectedResource instanceof Parameter)
        {
            _sqlObject = ((Parameter)_selectedResource).getRoutine();
            this.prepare();
        }
        
        if (_progressMonitor != null && _progressMonitor.isCanceled())
        {
            return null;
        }
        
        if(_sqlObject instanceof Routine)
        {
        	Routine r = (Routine)_sqlObject;
        	if(r.getSource() == null || r.getSource().getBody()==null||r.getSource().getBody().trim().length()==0)
        	{
        		objectNotFound();
                return null;
        	}
        }
        if(_sqlObject instanceof SybaseASABaseEvent)
        {
        	SybaseASABaseEvent e = (SybaseASABaseEvent)_sqlObject;
        	if(e.getEventCreator() == null)
        	{
        		objectNotFound();
        		return null;
        	}
        }
        
        if (_sqlObject != null && _connectionProfile != null) {
            IEditorDescriptor editor = null;
            if (editorId != null && !editorId.trim().equals(""))
            {
                editor = SchemaObjectEditorUtils.getEditorById(editorId);
            }
            else
            {
                DatabaseVendorDefinitionId vendorId = ProfileUtil.getDatabaseVendorDefinitionId(_connectionProfile.getName());
                editor = SchemaObjectEditorUtils.getEditorByObjectType(vendorId.getProductName(), vendorId.getVersion(), _sqlObject.eClass());
            }
            
            DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(_connectionProfile.getName(),
                                    getDatabaseName());
            
            if (_progressMonitor != null && _progressMonitor.isCanceled())
            {
                return null;
            }
            
            if (editor != null)
            {
                ProceduralObjectImmutableModel imodel = new ProceduralObjectImmutableModel(_sqlObject,new HashMap());
                ProceduralObjectEditModel emodel = new ProceduralObjectEditModel(imodel, databaseIdentifier);
                ProceduralObjectEditorInput input = new ProceduralObjectEditorInput(editor, emodel, databaseIdentifier);
                if(_selectedResource instanceof Parameter || _selectedResource instanceof ParametersNode)
                {
                    String productName = ProfileUtil.getDatabaseVendorDefinitionId(_connectionProfile.getName()).getProductName();
                    if(_pageId!=null)
                    {
                        input.setDefaultPageId(_pageId);
                    }
                }
                else if (defaultPageId != null)
                {
                	_pageId = defaultPageId;
                    input.setDefaultPageId(defaultPageId);
                }
                
                if (_progressMonitor != null && _progressMonitor.isCanceled())
                {
                    return null;
                }
                
                if (_progressMonitor != null)
                {
                    _progressMonitor
                            .subTask(org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.action.Messages.EditRoutineObjectAction_initializing);
                }
                final ProceduralObjectEditorInput tempInput = input;
                final String editorName = editor.getEditorName();
                ExamplePlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            _ediotrPart = ExamplePlugin.getActiveWorkbenchPage().openEditor(tempInput, Constants.SCHEMA_EDITOR_ID);
                        }
                        catch (Exception e)
                        {
                            _ediotrPart = null;
                            IStatus status = new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, 0,
                                    Messages.SchemaObjectEditorUtils_internal_error_when_opening + editorName, e);
                            ErrorDialog.openError(null, Messages.SchemaObjectEditorUtils_error,
                                    Messages.SchemaObjectEditorUtils_error_open, status);
                        }
                    }
                });
                return _ediotrPart;
            }
            else
            {
                final IStatus status = new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, 0,
                    Messages.SchemaObjectEditorUtils_no_suitable_editor, null);
                Display.getDefault().asyncExec(new Runnable()
                {
                    public void run() {
                        
                    ErrorDialog.openError(null, Messages.SchemaObjectEditorUtils_error,
                            Messages.SchemaObjectEditorUtils_editor_id + editorId
                            + Messages.SchemaObjectEditorUtils_no_extension, status);
                    };
                    
                }
                );
            }
            
		}
        return null;
    }
    
    public void setSQLObject(SQLObject object)
    {
        _sqlObject = object;
    }

    public ImageDescriptor getImageDescriptor()
    {
        if(_selectedResource !=null && _selectedResource instanceof Parameter)
        {
            return null;
        }
        return super.getImageDescriptor();
    }

    public int getActionId()
    {
        return actionId;
    }

    public void setActionId(int actionId)
    {
        this.actionId = actionId;
    }
}
