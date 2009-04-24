/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.general;

import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseASARoutineValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseParameterValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.ASAProcedureSourceUpdater;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.INameCompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.NameCompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.ParametersTableProvider;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableDataChangeListener;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidationDelegate;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.sql.updater.ProceduralObjectSourceUpdater;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * ASA procedure general page, handles both tsql and watcom sql
 * 
 * @author Hui Cao
 * 
 */
public class ASAProcedureGeneralPage extends RoutineGeneralPage implements ISchemaObjectEditorPage,
        ITableDataChangeListener, ISybaseASADdlConstants
{
    protected Diagnostic                   _resultDiagnostic;
    
    protected static boolean               SUPPORT_EXPRESSION  = true;
    
    public ASAProcedureGeneralPage()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * Includes the data type diagnostic for results before entering the validator
     */
    protected DiagnosticChain getDiagnosticChain(TypedEvent event)
    {
        DiagnosticChain chain = super.getDiagnosticChain(event);
        if (_resultDiagnostic != null)
        {
            chain.add(_resultDiagnostic);
        }
        return chain;
    }

    /**
     * Subclass should override this method to create and compose sections
     * 
     * @param toolkit
     * @param container
     */
    protected void composeSections(FormToolkit toolkit, Composite container)
    {
        createTitleSection(toolkit, container);
        ((NameCompositeProvider) _nameProvider).textComment.addModifyListener(new ModifyListener()
        {

            public void modifyText(ModifyEvent e)
            {
                if (!_inInit.booleanValue())
                {
                    markDirty();
                    _mainObject.setDescription(((NameCompositeProvider) _nameProvider).textComment.getText());
                }
            }

        });
        createParameterSection(toolkit, container);
    }

    protected INameCompositeProvider createNameComposite()
    {
        NameCompositeProvider nameCompositeProvider = new NameCompositeProvider();
        return nameCompositeProvider;
    }

    protected ProceduralObjectSourceUpdater createSourceUpdater()
    {
        return new ASAProcedureSourceUpdater((SybaseRoutine) _mainObject, _databaseDefinition);
    }
    
    
    protected SybaseParameter createParameter()
    {
        SybaseASABaseParameter p = SybaseasabasesqlmodelFactory.eINSTANCE.createSybaseASABaseParameter();
        p.setParmType(ParameterType.VARIABLE_LITERAL);
        return p;
    }

	protected void initParameters(EList params) {
		_paramData.init(params, _databaseDefinition, true);
	}
	
    protected ParametersTableProvider createParametersTableProvider()
    {
        return new ParametersTableProvider(false, true, SUPPORT_EXPRESSION);
    }
    
    protected void initControls()
    {
        super.initControls();
        // comment
        String desc = _mainObject.getDescription();
        if (desc == null)
        {
            desc = ""; //$NON-NLS-1$
        }
        ((NameCompositeProvider) _nameProvider).textComment.setText(desc);

        configureParametersTableProvider();
    }

    protected Map buildValidationContext(TypedEvent e)
    {
        Map context = super.buildValidationContext(e);
        context.put(SQLModelValidationDelegate.VALIDATIOR_KEY, new SybaseASARoutineValidator());

        return context;
    }

    protected Map buildSharedParams(TypedEvent e)
    {
        Map sharedParams = super.buildSharedParams(e);
        //even for tsql syntax, expression default value is allowed
        sharedParams.put(SybaseParameterValidator.SUPPORTS_EXPRESSION, ""+SUPPORT_EXPRESSION);
        return sharedParams;
    }
    
    protected EList getResultColumns()
    {
        EList results = ((Procedure)_mainObject).getResultSet();
        if (results.isEmpty())
        {
            RoutineResultTable table = SQLRoutinesFactory.eINSTANCE.createRoutineResultTable();
            results.add(table);
        }
        RoutineResultTable table = (RoutineResultTable)results.get(0);
        return table.getColumns();
    }

    public void revert()
    {
        _resultDiagnostic = null;
        super.revert();
    }    
    
    protected void createParameterSection(FormToolkit toolkit, Composite container)
    {
        super.createParameterSection(toolkit, container);
        Button[] buttons = _paraSection.getButtons();
        for(int i = 0 ; i < buttons.length; i ++)
        {
            buttons[i].setEnabled(false);
        }
    }
    
    protected void createTitleSection(FormToolkit toolkit, Composite container)
    {
        super.createTitleSection(toolkit, container);
        _nameProvider.getNameControl().setEditable(false);
        ((NameCompositeProvider) _nameProvider).textComment.setEditable(false);
    }
}
