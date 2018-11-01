/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.source;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeployable;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParamDefList;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ASASourcePage extends SourcePage implements ISybaseASADdlConstants
{
    //TODO consider adding a preference page for user to configure
    DateFormat[] DATE_PARSERS = new DateFormat[]{
        ObjectviewerConstants.DATE_FORMAT,
        new SimpleDateFormat(Messages.ASASourcePage_date_format),
        DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault()),
        DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault()),
        DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault()),
        DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault()),
        
    };
    
    DateFormat[] TIME_PARSERS = new DateFormat[]{
        ObjectviewerConstants.TIME_FORMAT,
        new SimpleDateFormat(Messages.ASASourcePage_time_format),
        DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault()),
        DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.getDefault()),
        DateFormat.getTimeInstance(DateFormat.LONG, Locale.getDefault()),
        DateFormat.getTimeInstance(DateFormat.FULL, Locale.getDefault()),
    };


    public ASASourcePage()
    {
        // TODO Auto-generated constructor stub
    }

    public ASASourcePage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
        // TODO Auto-generated constructor stub
    }

    public ASASourcePage(String id, String title)
    {
        super(id, title);
        // TODO Auto-generated constructor stub
    }
    
    public IEditorPart getNestedEditor()
    {
        if (_routineEditor == null)
        {
            _routineEditor = new ASARoutineSourceEditor();
            _routineEditor.setParentEditor(this.getEditor());
        }
        return _routineEditor;
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

    protected void reInitParameters(SybaseRoutine proc, IASTDeployable astProc, Schema schema)
    {
        EList parameters = proc.getParameters();
        parameters.clear();
        for (int j = 0; j < astProc.jjtGetNumChildren(); j++)
        {
            Node astParamList = astProc.jjtGetChild(j);
            if (astParamList instanceof IASTSQLParamDefList)
            {
                DatabaseDefinition dbdef = null;
                EObject database = ContainmentServiceImpl.INSTANCE.getRootElement(schema);
                if(database instanceof Database) {
                    dbdef = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) database);
                }
                for (int k = 0; k < astParamList.jjtGetNumChildren(); k++)
                {
                    Node child = astParamList.jjtGetChild(k);
                    if (! (child instanceof IASTSQLParam))
                    {
                        continue;
                    }
                            
                    IASTSQLParam astParam = (IASTSQLParam)child;
                    SybaseASABaseParameter param = SybaseasabasesqlmodelFactory.eINSTANCE.createSybaseASABaseParameter();
                    if (child.getFirstToken() == child.getLastToken())
                    {
                        //SQLCODE or SQLSTATE
                        param.setParmType(ParameterType.get(child.getFirstToken().image.toUpperCase()));
                    }
                    else
                    {
                        param.setParmType(ParameterType.VARIABLE_LITERAL);
                        param.setName(astParam.getName());
                        if (dbdef != null)
                        {
                            SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByProfileName(_routineEditor
                                    .getDatabaseIdentifier().getProfileName());
                            DataTypeProvider provider = config.getSQLDataService().getDataTypeProvider();
                            DataType newType = provider.getDataType(SQLUtil.unquote(astParam.getType()), schema,
                                    DataTypeProvider.VARIABLE, _routineEditor.getDatabaseIdentifier());
                            param.setDataType(newType);
                        }
                        ParameterMode mode = ParameterMode.get(astParam.getDirection());
                        param.setMode(mode);
                        param.setDefaultValue(astParam.getDefaultValue());
                    }
                    parameters.add(param);
                }
            }
        }
        
    }
    
    
    protected String getBody()
    {
        if (_mainObject instanceof SybaseASABaseEvent)
        {
            SybaseASABaseEvent e = (SybaseASABaseEvent) _mainObject;
            return e.getAction();
        }
        return super.getBody();
    }
    
    
    protected void setBody(IDocument doc)
    {
        if (_mainObject instanceof SybaseASABaseEvent)
        {
            ((SybaseASABaseEvent) _mainObject).setAction(doc.get());
        }
        super.setBody(doc);
    }
    
}
