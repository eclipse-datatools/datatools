/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.general;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.ITriggerValidatorConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.TriggerSourceUpdater;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.ICompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.INameCompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.TriggerEventsCompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.TriggerNameCompositeProvider;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.modelvalidity.IValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidationDelegate;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.CollapseableSection;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.sql.updater.ProceduralObjectSourceUpdater;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


/**
 * 
 * @author Hui Cao
 * 
 */
public abstract class TriggerGeneralPage extends ProceduralObjectGeneralPage implements SelectionListener
{
    protected TriggerEventsCompositeProvider _eventsProvider ;
    protected Schema _schema;
    protected Trigger _oldTrigger;
    protected boolean                        _popDlgDueToObjectLost = true;
    
    public TriggerGeneralPage()
    {
        // TODO Auto-generated constructor stub
    }

    
    protected void composeSections(FormToolkit toolkit, Composite container)
    {
        super.composeSections(toolkit, container);
        createEventsSection(toolkit, container);
    }
    
    protected void createEventsSection(FormToolkit toolkit, Composite container)
    {
        CollapseableSection events = new CollapseableSection(toolkit, Messages.TriggerGeneralPage_trigger_event_section, container.getDisplay(), SWT.NONE)
        {
        
            public void createSectionContent(Composite parent)
            {
              Section events = getSection();
              TableWrapData td1 = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
              events.setLayoutData(td1);
              TableWrapData td2 = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
              parent.setLayoutData(td2);
              parent.setLayout(new TableWrapLayout());
              Composite container = new Composite(parent, SWT.NONE);
              container.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB) );
              container.setLayout(new GridLayout());
              _eventsProvider = new TriggerEventsCompositeProvider();
              _eventsProvider.getComposite(container, _toolkit, ICompositeProvider.NONE);
            }
        };
        events.createControl(container, 1, getPageDescriptor().getContextHelpId());
        events.setPluginId(getEditorDescriptor().getPluginId());
        
        _eventsProvider.checkInsert.addSelectionListener(this);
        _eventsProvider.checkUpdate.addSelectionListener(this);
        _eventsProvider.checkDelete.addSelectionListener(this);
    }

    protected void setPageInput(IEditorInput input)
    {
        super.setPageInput(input);
        if (input instanceof SchemaObjectEditorInput)
        {
            _oldTrigger = (Trigger)_editModelObject.getSchemaObjectImmutableModel().getMainSQLObject();
            _schema = _oldTrigger.getSchema();
        }
    }
    
    protected void initControls()
    {
        super.initControls();
        if (_mainObject instanceof Trigger && _eventsProvider != null)
        {
            String dbName = ModelUtil.getDatabaseName(_schema); 
            
            // In case the object is dropped outside
            if(_oldTrigger.getSubjectTable() == null || _oldTrigger.getSubjectTable().getSchema() == null)
            {
                if(_popDlgDueToObjectLost)
                {
                    _popDlgDueToObjectLost = false;
                    MessageDialog
                            .openError(ExamplePlugin.getActiveWorkbenchShell(), Messages.TriggerGeneralPage_error,
                                    Messages.TriggerGeneralPage_cannot_get_table);
                    return;
                }
                return;
            }
            _nameProvider.setValues(dbName, _schema.getName(), _mainObject.getName(), _oldTrigger.getSubjectTable().getName(), _oldTrigger.getSubjectTable().getSchema().getName());
            
            Trigger trigger = (Trigger)_mainObject;
            _eventsProvider.checkInsert.setSelection(trigger.isInsertType());
            _eventsProvider.checkUpdate.setSelection(trigger.isUpdateType());
            _eventsProvider.checkDelete.setSelection(trigger.isDeleteType());
            
            _nameChanged = Boolean.FALSE;
        }
    }
    
    protected Map buildValidationContext(TypedEvent e)
    {
        Map context = super.buildValidationContext(e);
        //We do not have specilized trigger model, so there's no way to register the validator
        context.put(SQLModelValidationDelegate.VALIDATIOR_KEY, getModelValidator());

        if (e == null)
        {
            return context;
        }
        
        List items = (List)context.get(SQLModelValidationDelegate.VALIDATION_ITEMS_KEY);
        
        //validate events when they are changed or during save
        if (e == null || getMatchingFeature(e.widget) > 0)
        {
            IValidationItem item = new ValidationItem(SQLTablesPackage.TRIGGER__INSERT_TYPE);
            items.add(item);
            item = new ValidationItem(SQLTablesPackage.TRIGGER__UPDATE_TYPE);
            items.add(item);
            item = new ValidationItem(SQLTablesPackage.TRIGGER__DELETE_TYPE);
            items.add(item);
        }

        return context;
    }

    protected abstract SQLModelValidator getModelValidator();

    
    
    protected Collection getFilteredDiagnostics(BasicDiagnostic diagnostics, TypedEvent event)
    {
        Collection filtered = super.getFilteredDiagnostics(diagnostics, event);
        int feature = getMatchingFeature(event.widget);
        if (feature != -1)
        {
            String source = Integer.toString(feature);
            for (Iterator iter = ((Diagnostic) _diagnostics).getChildren().iterator(); iter.hasNext();)
            {
                Diagnostic d = (Diagnostic) iter.next();
                if (d.getSource().equals(source))
                {
                    filtered.add(d);
                }
            }
        }

        if (affectsDuplicationCheck(event.widget))
        {
            for (Iterator iter = ((Diagnostic) _diagnostics).getChildren().iterator(); iter.hasNext();)
            {
                Diagnostic d = (Diagnostic) iter.next();
                if (d.getCode() == ITriggerValidatorConstants.DUPLICATE_TRIGGER_TYPE_EVENT
                        || d.getCode() == ITriggerValidatorConstants.NO_TRIGGER_EVENT)
                {
                    filtered.add(d);
                }
            }
        }

        return filtered;
    }


    private boolean affectsDuplicationCheck(Widget widget)
    {
        if (_eventsProvider != null)
        {
            if (widget == _eventsProvider.checkInsert || widget == _eventsProvider.checkUpdate
                    || widget == _eventsProvider.checkDelete )
            {
                return true;
            }
        }
        return false;
    }

    private int getMatchingFeature(Widget widget)
    {
        if (_eventsProvider != null)
        {
            if (widget == _eventsProvider.checkInsert)
            {
                return SQLTablesPackage.TRIGGER__INSERT_TYPE;
            }
            else if (widget == _eventsProvider.checkUpdate)
            {
                return SQLTablesPackage.TRIGGER__UPDATE_TYPE;
            }
            else if (widget == _eventsProvider.checkDelete)
            {
                return SQLTablesPackage.TRIGGER__DELETE_TYPE;
            }
            
        }
        
        return -1;
    }

    public void widgetDefaultSelected(SelectionEvent e)
    {
    }

    public void widgetSelected(SelectionEvent e)
    {
        if (!_inInit.booleanValue())
        {
            markDirty();
            ((Trigger) _mainObject).setInsertType(_eventsProvider.checkInsert.getSelection());
            ((Trigger) _mainObject).setUpdateType(_eventsProvider.checkUpdate.getSelection());
            ((Trigger) _mainObject).setDeleteType(_eventsProvider.checkDelete.getSelection());
            validateAndShowErrors(e);
            ((TriggerSourceUpdater)_updater).updateEvents();
        }
    }

    
    protected INameCompositeProvider createNameComposite()
    {
        return new TriggerNameCompositeProvider();
    }

    
    protected ProceduralObjectSourceUpdater createSourceUpdater()
    {
        return new TriggerSourceUpdater((Trigger)_mainObject, _databaseDefinition); 
    }

    protected Map buildSharedParams(TypedEvent e)
    {
        Map sharedParams = super.buildSharedParams(e);
        sharedParams.put(ValidatorConstants.ORIGINAL_MODEL, _oldTrigger);

        return sharedParams;
    }

}
