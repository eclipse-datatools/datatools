/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.general;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseASATriggerValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.ASATriggerSourceUpdater;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.INameCompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.TriggerNameCommentCompositeProvider;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.modelvalidity.ContainmentFeatureValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.IValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidationDelegate;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.sql.updater.ProceduralObjectSourceUpdater;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ASATriggerGeneralPage extends TriggerGeneralPage
{
    public ASATriggerGeneralPage()
    {
        // TODO Auto-generated constructor stub
    }

    protected SQLModelValidator getModelValidator()
    {
        // TODO Auto-generated method stub
        return null;
    }

    protected void composeSections(FormToolkit toolkit, Composite container)
    {
        createTitleSection(toolkit, container);
        // TODO create asa trigger name provider
        ((TriggerNameCommentCompositeProvider) _nameProvider).textComment.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                if (!_inInit.booleanValue())
                {
                    markDirty();
                    _mainObject.setDescription(((TriggerNameCommentCompositeProvider) _nameProvider).textComment
                            .getText());
                }
            }

        });
            
        createEventsSection(toolkit, container);
    }

    protected INameCompositeProvider createNameComposite()
    {
        TriggerNameCommentCompositeProvider nameCompositeProvider = new TriggerNameCommentCompositeProvider();
        return nameCompositeProvider;
    }

    
    protected void initControls()
    {
        // TODO recreate the page if the syntax type is changed.
        super.initControls();
        if (_mainObject instanceof SybaseASABaseTrigger)
        {
            String dbName = ModelUtil.getDatabaseName(_schema);
            
            // In case the object is dropped outside
            if(_oldTrigger.getSubjectTable() == null || _oldTrigger.getSubjectTable().getSchema() == null)
            {   
                return;
            }
            _nameProvider.setValues(dbName, _schema.getName(), _mainObject.getName(), _oldTrigger.getSubjectTable()
                    .getName(), _oldTrigger.getSubjectTable().getSchema().getName());
            _nameChanged = Boolean.FALSE;
            // comment
            String desc = _mainObject.getDescription();
            if (desc == null)
            {
                desc = ""; //$NON-NLS-1$
            }
            ((TriggerNameCommentCompositeProvider) _nameProvider).textComment.setText(desc);

            ((ASATriggerSourceUpdater) _updater).init();
        }

    }

    protected Map buildValidationContext(TypedEvent e)
    {
        Map context = super.buildValidationContext(e);
        context.put(SQLModelValidationDelegate.VALIDATIOR_KEY, new SybaseASATriggerValidator());

        if (e == null)
        {
            return context;
        }

        List items = (List) context.get(SQLModelValidationDelegate.VALIDATION_ITEMS_KEY);
        // validate parameters when parameters are added or deleted or during save
        int matchingFeature = getMatchingFeature(e.widget);
        if (matchingFeature != -1)
        {
            if (matchingFeature == SQLTablesPackage.TRIGGER__TRIGGER_COLUMN)
            {
                IValidationItem item = new ContainmentFeatureValidationItem(SQLTablesPackage.TRIGGER__TRIGGER_COLUMN);
                Map paramsContext = SQLModelValidationDelegate.getCompleteValidationContext(1);

                item.setContext(paramsContext);
                items.add(item);
            }
            else
            {
                IValidationItem item = new ValidationItem(matchingFeature);
                items.add(item);
            }
        }

        return context;
    }

    
    protected Map buildSharedParams()
    {
        Map sharedParams = super.buildSharedParams();
        sharedParams.put(ValidatorConstants.ORIGINAL_MODEL, _oldTrigger);
        return sharedParams;
    }

    
    protected Collection getFilteredDiagnostics(BasicDiagnostic diagnostics, TypedEvent event)
    {
        return super.getFilteredDiagnostics(diagnostics, event);
    }
    

    private int getMatchingFeature(Widget widget)
    {
        return -1;
    }

    protected ProceduralObjectSourceUpdater createSourceUpdater()
    {
        return new ASATriggerSourceUpdater((Trigger) _mainObject, _databaseDefinition);
    }
    
    protected void createTitleSection(FormToolkit toolkit, Composite container)
    {
        super.createTitleSection(toolkit, container);
        _nameProvider.getNameControl().setEditable(false);
    }
}
