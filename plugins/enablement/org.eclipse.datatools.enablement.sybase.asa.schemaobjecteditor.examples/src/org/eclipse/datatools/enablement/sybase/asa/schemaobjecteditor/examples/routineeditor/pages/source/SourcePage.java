/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.source;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.Messages;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilderWrapper;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsFactory;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.NestedEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeployable;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParamDefList;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTTrigger;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * 
 * @author Hui Cao TODO extract the parser logic into ParserModelUpdater
 */
public class SourcePage extends NestedEditorPage implements ISchemaObjectEditorPage
{

    public static final String       PAGE_ID        = "org.eclipse.datatools.sqltools.schemaobjecteditor.routineSourcePage";

    protected RoutineEditor          _routineEditor = null;
    protected ProcEditorInput        _input         = null;
    // whether the source has been updated since this source page is active
    protected boolean                _sourceChanged = false;
    protected boolean                _dirty         = false;
    protected SQLObject              _mainObject;
    protected List                   _schemas;
    protected ISchemaObjectEditModel _editModelObject;

    // TODO add reference to sourceUpdater
    private IDocumentListener        _docListener;

    protected class SourcePageForm extends PageForm
    {
        public SourcePageForm(SchemaObjectEditorPage page, ScrolledForm form)
        {
            super(page, form);
        }

        public void commit(boolean onSave)
        {
            super.commit(onSave);
            canLeaveThePage();
            // FIXME not called
        }
    }

    public SourcePage()
    {
        this(PAGE_ID, Messages.SourcePage_page_name);
    }

    public SourcePage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
    }

    public SourcePage(String id, String title)
    {
        super(id, title);
    }

    public IEditorPart getNestedEditor()
    {
        if (_routineEditor == null)
        {
            _routineEditor = new RoutineEditor();
            _routineEditor.setParentEditor(this.getEditor());
        }
        return _routineEditor;
    }

    public IEditorInput getNestedEditorInput()
    {
        if (_input != null)
        {
            return _input;
        }

        ProcIdentifier proc = getProcIdentifier();

        if (proc != null)
        {
            _input = new ProcEditorInput(proc);
            return _input;
        }
        return null;
    }

    protected ProcIdentifier getProcIdentifier()
    {
        ProcIdentifier proc = null;
        FormEditor editor = getEditor();
        if (editor != null && editor instanceof ISchemaObjectEditor)
        {
            ISchemaObjectEditorInput parentInput = (ISchemaObjectEditorInput) getEditor().getEditorInput();
            if (parentInput != null)
            {
                _mainObject = parentInput.getEditModelObject().getMainSQLObject();
                SQLObject oldMainObject = parentInput.getEditModelObject().getSchemaObjectImmutableModel()
                        .getMainSQLObject();
                Catalog catalog = ModelUtil.getCatalog(oldMainObject);
                Database db = (Database) ContainmentServiceImpl.INSTANCE.getRootElement(oldMainObject);
                _schemas = ModelUtil.getSchemas(db, catalog == null ? null : catalog.getName());

                _editModelObject = ((SchemaObjectEditorInput) parentInput).getEditModelObject();
                if (_mainObject instanceof SQLObject)
                {
                    proc = SQLDevToolsUtil.getProcIdentifier(parentInput.getDatabaseIdentifier(),
                            (SQLObject) _mainObject);
                }
            }
        }
        return proc;
    }

    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
        super.init(site, input);
        IDocument doc = _routineEditor.getDocumentProvider().getDocument(_input);
        _docListener = new IDocumentListener()
        {
            public void documentAboutToBeChanged(DocumentEvent event)
            {
            }

            public void documentChanged(DocumentEvent event)
            {
                _sourceChanged = true;
                _dirty = true;
            }

        };
        doc.addDocumentListener(_docListener);
        _dirty = false;
    }

    public void dispose()
    {
        super.dispose();
    }

    public void setActive(boolean active)
    {
        if (active)
        {
            _sourceChanged = false;
            String body = getBody();
            if (_routineEditor != null)
            {
                IDocument doc = _routineEditor.getDocumentProvider().getDocument(_input);
                if (getEditor().isDirty())
                {
                    // to prevent showing dirty state when user does nothing
                    doc.set(body);
                }
                doc.addDocumentListener(_docListener);
            }
        }
        else
        {
            if (_routineEditor != null)
            {
                IDocument doc = _routineEditor.getDocumentProvider().getDocument(_input);
                doc.removeDocumentListener(_docListener);
            }
        }
    }

    protected PageForm createManagedForm(ScrolledForm form)
    {
        return new SourcePageForm(this, form);
    }

    public void aboutToLeave()
    {

    }

    public boolean canLeaveThePage()
    {
        if (_routineEditor != null)
        {
            _errorMsg = validateSource(true);
        }
        if (_errorMsg != null)
        {
            // to maintain the integrity of the editor, we don't allow user to switch page when there's syntax error
            MessageDialog.openError(getSite().getShell(), Messages.SourcePage_error, _errorMsg);
            return false;
        }
        return true;
    }

    /**
     * Returns the source code from the model
     * 
     * @return
     */
    protected String getBody()
    {
        if (_mainObject instanceof Routine)
        {
            return ((Routine) _mainObject).getSource().getBody();
        }
        else if (_mainObject instanceof Trigger && _routineEditor != null)
        {
            return SybaseDdlBuilderWrapper.getInstance().getTriggerBody((Trigger) _mainObject);
        }
        return "";
    }

    /**
     * Sets the source code to the model
     * 
     * @param doc
     */
    protected void setBody(IDocument doc)
    {
        if (_mainObject instanceof Routine)
        {
            ((Routine) _mainObject).getSource().setBody(doc.get());
        }
        else if (_mainObject instanceof Trigger)
        {
            ((Trigger) _mainObject).getActionStatement().clear();
            SQLStatement stmt = SQLStatementsFactory.eINSTANCE.createSQLStatementDefault();
            stmt.setSQL(doc.get());
            ((Trigger) _mainObject).getActionStatement().add(stmt);
        }
    }

    private String validateSource(boolean headerMode)
    {
        boolean validationOn = SQLEditorPlugin.getDefault().getPreferenceStore().getBoolean(
                PreferenceConstants.SYNTAX_VALIDATION);
        ParsingResult result = null;

        _errorMsg = null;
        if (_sourceChanged)
        {
            // to prevent parse again
            _sourceChanged = false;

            IDocument doc = _routineEditor.getDocumentProvider().getDocument(_input);
            setBody(doc);

            SQLDevToolsConfiguration f = SQLToolsFacade.getConfigurationByVendorIdentifier(_routineEditor
                    .getConnectionInfo().getDatabaseVendorDefinitionId());
            SQLParser p = f.getSQLService().getSQLParser();

            ParserParameters parserParameters = new ParserParameters(false, _routineEditor.getSQLType());
            // user header mode to improve performance
            parserParameters.setProperty(ParserParameters.PARAM_HEADER_MODE, Boolean.TRUE);
            result = p.parse(doc.get(), parserParameters);
            result.getRootNode().setDocument(doc);

            if (!result.getExceptions().isEmpty())
            {
                _errorMsg = Messages.SourcePage_syntax_error;
            }
            else
            {
                _errorMsg = reInitSQLObject(result, _mainObject);
            }
        }

        if (_errorMsg == null && validationOn && !headerMode)
        {
            // we might fail to report the parser errors
            // if validation is on, use the full parsing result
            result = _routineEditor.getParsingResult();
            if (result != null && !result.getExceptions().isEmpty())
            {
                _errorMsg = Messages.SourcePage_syntax_error;
            }
        }

        return _errorMsg;
    }

    /**
     * Reinitializes the main sql object from the parse result. Subclass should override.
     * 
     * @param result
     * @param object
     * @return whether the operation is successful. false indicates the document contains errors.
     */
    protected String reInitSQLObject(ParsingResult result, Object object)
    {
        // TODO reinit name/parameter
        IASTStart root = result.getRootNode();
        if (root == null || root.jjtGetNumChildren() != 1)
        {
            // must contain only one procedural object
            return Messages.SourcePage_invalid_content;
        }
        for (int i = 0; i < root.jjtGetNumChildren(); i++)
        {
            Node node = root.jjtGetChild(i);
            if (node instanceof IASTDeployable && object instanceof SybaseRoutine)
            {
                SybaseRoutine proc = (SybaseRoutine) object;
                IASTDeployable astProc = (IASTDeployable) node;
                String msg = reInitRoutine(proc, astProc);
                if (msg != null)
                {
                    return msg;
                }
            }
            else if (node instanceof IASTTrigger && object instanceof Trigger)
            {
                Trigger trigger = (Trigger) object;
                IASTTrigger astTrigger = (IASTTrigger) node;
                String msg = reInitTrigger(trigger, astTrigger);
                if (msg != null)
                {
                    return msg;
                }
            }
            else if (node instanceof IASTDeployable && object instanceof Event)
            {
                Event event = (Event) object;
                String msg = reInitEvent(event, (IASTDeployable) node);
                if (msg != null)
                {
                    return msg;
                }
            }
            else
            {
                // the parse result contains invalid ast
                return Messages.SourcePage_invalid_content;
            }
        }

        return null;
    }

    protected String setName(SQLObject object, Schema schema, IASTDeployable deployable)
    {
        String[] name = SQLUtil.parseDatabaseObject(deployable.getDBObjectName());
        if (name == null || name.length == 0)
        {
            // TODO name can't be null
            return Messages.SourcePage_no_name;
        }
        object.setName(name[0]);
        if (name.length > 1)
        {
            if (name[1] == null || (schema != null && !name[1].equalsIgnoreCase(schema.getName())))
            {
                // TODO can't change owner
                return Messages.SourcePage_owner_changed;
            }
        }
        return null;
    }

    protected String reInitEvent(Event event, IASTDeployable node)
    {
        String msg = setName(event, null, node);
        if (msg != null)
        {
            return msg;
        }

        return null;
    }

    protected String reInitTrigger(Trigger trigger, IASTTrigger astTrigger)
    {
        Trigger oldTrigger = (Trigger) _editModelObject.getSchemaObjectImmutableModel().getMainSQLObject();
        Schema schema = oldTrigger.getSchema();
        String msg = setName(trigger, schema, astTrigger);
        if (msg != null)
        {
            return msg;
        }
        // TODO check table name & table owner name

        trigger.setInsertType(astTrigger.isInsertType());
        trigger.setUpdateType(astTrigger.isUpdateType());
        trigger.setDeleteType(astTrigger.isDeleteType());

        return null;
    }

    protected String reInitRoutine(SybaseRoutine proc, IASTDeployable astProc)
    {
        Routine oldRoutine = (Routine) _editModelObject.getSchemaObjectImmutableModel().getMainSQLObject();
        Schema schema = oldRoutine.getSchema();

        String msg = setName(proc, schema, astProc);
        if (msg != null)
        {
            return msg;
        }

        reInitParameters(proc, astProc, schema);
        return null;
    }

    /**
     * Handles TSQL parameters
     * 
     * @param proc
     * @param astProc
     * @param schema
     */
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
                if (database instanceof Database)
                {
                    dbdef = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) database);
                }
                for (int k = 0; k < astParamList.jjtGetNumChildren(); k++)
                {
                    Object child = astParamList.jjtGetChild(k);
                    if (!(child instanceof IASTSQLParam))
                    {
                        continue;
                    }

                    IASTSQLParam astParam = (IASTSQLParam) child;
                    SybaseParameter param = SybasesqlmodelFactory.eINSTANCE.createSybaseParameter();
                    param.setName(astParam.getName());
                    if (dbdef != null)
                    {
                        DataTypeProvider provider = SQLToolsFacade.getConfigurationByVendorIdentifier(
                                new DatabaseVendorDefinitionId(dbdef.getProduct(), dbdef.getVersion()))
                                .getSQLDataService().getDataTypeProvider();
                        DataType newType = provider.getDataType(SQLUtil.unquote(astParam.getType()), (Schema) _schemas
                                .get(0), _routineEditor.getDatabaseIdentifier());
                        param.setDataType(newType);
                    }
                    ParameterMode mode = ParameterMode.get(astParam.getDirection());
                    // In TSQL output == inout
                    if (mode.getValue() == ParameterMode.OUT)
                    {
                        mode = ParameterMode.INOUT_LITERAL;
                    }
                    param.setMode(mode);
                    param.setDefaultValue(astParam.getDefaultValue());
                    parameters.add(param);
                }
            }
        }
    }

    public boolean aboutToSave(IProgressMonitor monitor)
    {
        if (_dirty)
        {
            String msg = validateSource(false);
            if (msg != null)
            {
                boolean goon = MessageDialog.openQuestion(getSite().getShell(), Messages.SourcePage_warning, NLS.bind(
                        Messages.SourcePage_warning_msg, msg));
                return goon;
            }
        }
        return true;
        // TODO check syntax and warn user if necessary
    }

    public void refresh()
    {
        super.refresh();
        // refresh all the cached models since they are changed
        _input = null;
        _mainObject = null;
        _editModelObject = null;
        _schemas = null;

        _routineEditor.setInput(getNestedEditorInput());
        IDocument doc = _routineEditor.getDocumentProvider().getDocument(_input);
        doc.addDocumentListener(_docListener);
        _dirty = false;
        _sourceChanged = false;
        ((ISchemaObjectEditor) getEditor()).clearDirty();
    }
}
