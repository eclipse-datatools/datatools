/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.general;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseParameterValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.DataTypeProviderExt;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.RoutineSourceUpdater;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.FullNameCompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.ICompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.INameCompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.ParametersData;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.ParametersRowData;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.ParametersTableProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilderWrapper;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableDataChangeListener;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.AccessibleTableViewer;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.RemoveAllRowAction;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.RemoveRowAction;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DataTypeValidator;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.modelvalidity.ContainmentFeatureValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.DiagnosticUtil;
import org.eclipse.datatools.sqltools.core.modelvalidity.IValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidationDelegate;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IErrorItem;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.CompositeEditSection;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.ErrorItem;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.sql.updater.ProceduralObjectSourceUpdater;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;


/**
 * 
 * @author Hui Cao
 * 
 */
public class RoutineGeneralPage extends ProceduralObjectGeneralPage implements ISchemaObjectEditorPage, ITableDataChangeListener
{

    protected CompositeEditSection _paraSection;
    protected ParametersTableProvider _paraProvider;
    
    protected ParametersData _paramData = new ParametersData();
    protected Schema _schema;
    protected Routine _oldRoutine;
    protected List _schemas = new ArrayList();
    protected Diagnostic              _uiDiagnostic;
    
    public static final int           PARAMETER_FOCUS           = 0;
	/*
     *  To cache ParameterRows is for getting their current index in ParametersData.
     *  
     */
    protected List _cachedParamRow = new ArrayList();
    
    /**
     * To cache all valid ParamRow were added for getting the current index to pass to updater
     */
    protected List _validParamRow = new ArrayList();
    
    protected class AddRowAction extends Action
    {
        private AccessibleTableViewer _tableViewer;
        
        public AddRowAction(AccessibleTableViewer viewer)
        {
            setText(Messages.RoutineGeneralPage_add);
            this._tableViewer = viewer;
        }
        
        public void run()
        {
            ITableData tableData = ((ITableData)_tableViewer.getInput());
            tableData.insertRow();
            _tableViewer.refresh();
            
            int selection = _tableViewer.getTable().getItemCount() - 2;
            _tableViewer.getCursor().setSelection(selection, ParametersData.NAME_COLUMN + 1);
            _tableViewer.getCursor().edit();
        }
    }
    
    public RoutineGeneralPage()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * Subclass should override this method to create and compose sections
     * @param toolkit
     * @param container
     */
    protected void composeSections(FormToolkit toolkit, Composite container)
    {
        createTitleSection(toolkit, container);
        createParameterSection(toolkit, container);
    }

    protected void createParameterSection(FormToolkit toolkit, Composite container)
    {
        String[] buttonText = new String[]{
            Messages.RoutineGeneralPage_add,
            Messages.RoutineGeneralPage_delete, 
            Messages.RoutineGeneralPage_delete_all, 
            Messages.RoutineGeneralPage_up, 
            Messages.RoutineGeneralPage_down};
        _paraSection = new CompositeEditSection(toolkit, Messages.RoutineGeneralPage_parameters, container.getDisplay(), true, false, SWT.NONE, buttonText, 80){
            protected void buttonSelected(SelectionEvent e, int buttonIndex)
            {
                AccessibleTableViewer viewer = _paraProvider.getViewer();
                Action action = null;
                switch (buttonIndex)
                {
                    case 0:
                        action = new AddRowAction(viewer);
                        action.run();
                        updateButtons();
                        break;
                    case 1:
                        action = new RemoveRowAction(viewer);
                        action.run();
                        updateButtons();
                        break;
                    case 2:
                        action = new RemoveAllRowAction(viewer);
                        action.run();
                        updateButtons();
                        break;
                    case 3:
                        viewer.moveupRow();
                        rowMoved(viewer.getCursor().getTableRow());
                        break;
                    case 4:
                        viewer.movedownRow();
                        rowMoved(viewer.getCursor().getTableRow() - 1);
                        break;
                        
                }
            }
            
        };
        _paraSection.createControl(container, 1, null); //TODO helpid
        TableWrapData td2 = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
        _paraSection.getSection().setLayoutData(td2);

        Composite tableComp = _paraSection.getLeftComposite();
        _paraProvider = createParametersTableProvider();
        _paraProvider.getComposite(tableComp, toolkit, ICompositeProvider.NONE);
        
        _paraProvider.getViewer().addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
                updateButtons();
            }
        }
        );
        
        _paraProvider.getViewer().getTable().addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateButtons();
            }
            
        });
        
        _paramData.addTableDataChangeListener(this);

        configureParametersTableProvider();
    }

    /**
     * By default, this implementation only handles tsql syntax
     */
    protected void configureParametersTableProvider()
    {
        String[] inOuts = new String[]{"", ParameterMode.IN_LITERAL.getLiteral(), "OUT"};//TSQL
        _paraProvider.setInOutItems(inOuts);
        _paraProvider.setDataTypes(getDataTypes());
    }

    protected String[] getDataTypes()
    {
        DataTypeProvider provider = SQLToolsFacade.getConfigurationByVendorIdentifier(
                new DatabaseVendorDefinitionId(_databaseDefinition.getProduct(), _databaseDefinition.getVersion())).getSQLDataService()
                .getDataTypeProvider();
        String[] types = provider.getAvailableDataTypesDisplayString(DataTypeProvider.PARAMETER, _schema, _databaseIdentifier); 
        return types;
    }
    
    protected ParametersTableProvider createParametersTableProvider()
    {
        return new ParametersTableProvider(false, true);
    }

    protected void setPageInput(IEditorInput input)
    {
        super.setPageInput(input);
        if (input instanceof SchemaObjectEditorInput)
        {
            _oldRoutine = (Routine)_editModelObject.getSchemaObjectImmutableModel().getMainSQLObject();
            _schema = _oldRoutine.getSchema();
            Catalog catalog = ModelUtil.getCatalog(_schema);
            _schemas = ModelUtil.getSchemas(ModelUtil.getDatabase(_schema), catalog == null? null: catalog.getName());
        }
    }

    protected ProceduralObjectSourceUpdater createSourceUpdater()
    {
        return new RoutineSourceUpdater((SybaseRoutine)_mainObject, _databaseDefinition);
    }

    /**
     * Initializes the controls with the model. Synchronized by _inInit.
     *
     */
    protected void initControls()
    {
        super.initControls();
        if (_mainObject instanceof Routine)
        {
            Routine routine = (Routine)_mainObject;
         
            String dbName = ModelUtil.getDatabaseName(_schema); 
            _nameProvider.setValues(dbName, _schema.getName(), _mainObject.getName(), null, null);
            _nameChanged = Boolean.FALSE;

            EList params = routine.getParameters();
            initParameters(params);
            _paraProvider.setData(_paramData);
            
            _validParamRow.clear();
            List rows = _paramData.getRows();
            if (rows != null && rows.size() > 0)
            {            
                for (int i = 0; i < rows.size(); i++)
                {
                    _validParamRow.add(rows.get(i));                
                }
            }
            
            synchronizeCachedParamRow();            
        }
        updateButtons();
    }

	protected void initParameters(EList params) {
		_paramData.init(params, _databaseDefinition);
	}
    
    public void rowAdded(IRowData row)
    {
        synchronizeCachedParamRow();
        updateRow(row);
    }

    /**
     * Gets and initializes the SybaseParameter object associated with the row data. If it does not exist, creates one.
     * @param row
     * @return
     */
    protected SybaseParameter getParameter(IRowData row)
    {
        _uiDiagnostic = null;
        ParametersRowData pRow = (ParametersRowData)row;
        SybaseParameter param = pRow.getParameter();
        int index = _paramData.getRows().indexOf(row);
        EList parameters = ((Routine)_mainObject).getParameters();
        //must take into consideration of paste
        if (param == null || !parameters.contains(param))
        {
            param = createParameter();
            pRow.setParameter(param);
            //we have to add the parameters early to do the validation
            parameters.add(index, param);
        }

        param.setName((String)row.getValue(ParametersData.NAME_COLUMN));
        String typeName = (String)row.getValue(ParametersData.TYPE_COLUMN);
        
        // Check the validity of the data type
        DataTypeValidator v = SQLToolsFacade.getConfigurationByVendorIdentifier(
                ModelUtil.getDatabaseVendorDefinitionId(_schema)).getSQLDataService().getDataTypeValidator();
        String msg = v.isValidDataTypeString(typeName, _schema, DataTypeProvider.PARAMETER, _databaseIdentifier);
        if (msg != null)
        {
            _uiDiagnostic = new BasicDiagnostic(Diagnostic.ERROR, Integer
                    .toString(SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE), SybaseParameterValidator.INVALID_DATATYPE, msg, new Object[]
            {
                param
            });
        }
        // 
        
        DataType type = null;
        if (typeName != null && !typeName.equals("")) //$NON-NLS-1$
        {
            DataTypeProvider provider = SQLToolsFacade.getConfigurationByVendorIdentifier(
                    ModelUtil.getDatabaseVendorDefinitionId(_schema)).getSQLDataService().getDataTypeProvider();
            type = provider.getDataType(typeName, _schema, _databaseIdentifier);
            param.setDataType(type);
            if (type != null)
            {
                param.setDescription(null);
            }
            else
            {
                param.setDescription(typeName);
            }
        }
        else
        {
            param.setDataType(null);
            param.setDescription(null);
        }
        
        String modeValue = (String)row.getValue(ParametersData.INOUT_COLUMN);
        if (modeValue == null)
        {
        	modeValue = "";
        }
        param.setMode(_paramData.getParameterMode(modeValue));
        String defValue = (String)row.getValue(ParametersData.DEFAULT_COLUMN);
        if (defValue != null && defValue.length() > 0)
        {
        	String unquote = SQLUtil.unquote(defValue);
            if(isStringDataType(type))
            {
            	defValue = SQLUtil.quote(unquote, "'");
            }
            param.setDefaultValue(defValue);
        }
        else
        {
            param.setDefaultValue(null);
        }
        return param;
    }

    private boolean isStringDataType(DataType type)
    {
        if(type instanceof PredefinedDataType)
        {
            return ( type instanceof CharacterStringDataType || type instanceof DateDataType || type instanceof TimeDataType); 
        }
        else if (type instanceof DistinctUserDefinedType)
        {
            return isStringDataType(((DistinctUserDefinedType)type).getPredefinedRepresentation());
        }
        return false;
    }
    
	protected SybaseParameter createParameter()
    {
        return SybasesqlmodelFactory.eINSTANCE.createSybaseParameter();
    }

    public void rowDataUpdated(IRowData row, int col, Object oldVal, Object newVal)
    {
        //Actually, for the routineGeneralPage, the oldVal and newVal will always be String objects,
        //and the newVal will not be null.
        if(!newVal.equals(oldVal))
        {
            updateRow(row);
        }
    }

    /**
     * Includes the data type for paramter diagnostic before entering the validator
     */
    protected DiagnosticChain getDiagnosticChain(TypedEvent event)
    {
        DiagnosticChain chain = super.getDiagnosticChain(event);
        if (_uiDiagnostic != null)
        {
            chain.add(_uiDiagnostic);
        }
        return chain;
    }

    private void updateRow(IRowData row)
    {
        if (!_inInit.booleanValue())
        {
            markDirty();
            row.updateValue(ParametersData.DIRTY_COLUMN, "*");
        }
        SybaseParameter param = getParameter(row);
        //validate duplication
        Event e = new Event();
        e.data = row;
        e.widget = _paraProvider.getTable();
        validateAndShowErrors(new ModifyEvent(e));

        Diagnostic d = DiagnosticUtil.getDiagnostic( (BasicDiagnostic) _diagnostics, param);
        boolean warning = d != null && d.getSeverity() == Diagnostic.WARNING;
		if (d == null || warning)
        {
			if (warning)
			{
				_updater.setTestMode(true);
			}

			boolean success = true;
			int i = getUpdatedParamIndex(row);
            if (_validParamRow.contains(row))
            {
            	success = ((RoutineSourceUpdater)_updater).updateParameterModified((SybaseParameter) param, getParameterDef(param), i);            
            }
            else
            {
                success = ((RoutineSourceUpdater)_updater).updateParameterAdded((SybaseParameter) param, getParameterDef(param), i);
                if (success)
                {
                    _validParamRow.add(row);
                }
            }
            
            ((ParametersRowData)row).setValid(success);
            _updater.setTestMode(false);
            if (!success && d != null)
            {
            	Diagnostic error = new BasicDiagnostic(Diagnostic.ERROR, d.getSource(), d.getCode(), NLS.bind(Messages.RoutineGeneralPage_parameter_update_error, param.getName()), new Object[]{param});
            	_diagnostics.add(error);
                Iterator iter = ((Diagnostic) _diagnostics).getChildren().iterator();
                ArrayList errors = new ArrayList();
                while (iter.hasNext())
                {
                    Diagnostic diag = (Diagnostic) iter.next();
                    errors.add(new ErrorItem(null, diag.getMessage(), diag.getSeverity()));
                }
                IErrorItem[] items = (IErrorItem[]) errors.toArray(new IErrorItem[errors.size()]);
                showErrorItems(items);
            }

        }
        else
        {
            ((ParametersRowData)row).setValid(false);
        }		
    }

    public void rowDeleted(IRowData row)
    {
        if (!_inInit.booleanValue())
        {
            markDirty();
        }
        SybaseParameter param = ((ParametersRowData)row).getParameter();
        if (_uiDiagnostic != null && _uiDiagnostic.getData() != null && _uiDiagnostic.getData().contains(param))
        {
            _uiDiagnostic = null;
        }
        if (param != null )
        {
            EList params = ((Routine)_mainObject).getParameters();
            params.remove(param);
            if (_validParamRow.contains(row))
            {
                int i = getUpdatedParamIndex(row);
                ((RoutineSourceUpdater)_updater).updateParameterDeleted(i);
                _validParamRow.remove(row);
            }            
            
            synchronizeCachedParamRow();
            
            //validate duplication
            Event e = new Event();
            e.data = param;
            e.widget = _paraProvider.getTable();
            validateAndShowErrors(new ModifyEvent(e));
        }
        //if param is null, no need to validate
    }
    
    /**
     * fired when row index and index + 1 are switched 
     * @param index parameter index in the table
     */
    protected void rowMoved(int index)
    {
        List rows = _paramData.getRows();

        ParametersRowData row1 = null;
        ParametersRowData row2 = null;
        
        if (rows != null && index < rows.size())
        {
            row1 = (ParametersRowData)rows.get(index);
            row2 = (ParametersRowData)rows.get(index + 1);
        }
        
        if (row1 == null || row2 == null)
        {
            return; //no need to handle
        }
        SybaseParameter param1 = row1.getParameter();
        SybaseParameter param2 = row2.getParameter();
        
        if (param1 == null || param2 == null)
        {
            return; //no need to handle
        }
        EList params = ((Routine)_mainObject).getParameters();
        int ind1 = params.indexOf(param1);
        int ind2 = params.indexOf(param2);

        if (ind1 >= 0 && ind2 >= 0)
        {
            params.remove(param1);
            params.remove(param2);
            
            params.add(ind2, param1);
            params.add(ind1, param2);//ind2 should be lesser than ind1 because we are getting the moved rows
            
            if (_validParamRow.contains(row1) && _validParamRow.contains(row2))
            {
                ((RoutineSourceUpdater)_updater).updateParameterRearranged(index);                
            }
        }

        synchronizeCachedParamRow();
        updateButtons();
        markDirty();
    }
    
    protected String getParameterDef(SybaseParameter p)
    {
        return getTSQLParameterDef(p);
    }

    protected String getTSQLParameterDef(SybaseParameter p)
    {
        String name = p.getName();
        DataTypeProvider provider = SQLToolsFacade.getConfigurationByVendorIdentifier(
                ModelUtil.getDatabaseVendorDefinitionId(_schema)).getSQLDataService().getDataTypeProvider();
        
        //TODO: the method called here in DataTypeProvderExt will be pulled up to DTP later 
        DataTypeProviderExt providerExt = (DataTypeProviderExt)provider;
        String type = providerExt.getDataTypeString(p.getDataType(), _databaseIdentifier);        
        
        String dft = ((SybaseParameter)p).getDefaultValue();
        ParameterMode mode = p.getMode();
        String modeLiteral = mode.getLiteral();
        //In TSQL output == inout
        if (mode.getValue() == ParameterMode.INOUT)
        {
            modeLiteral = ParameterMode.OUT_LITERAL.getLiteral();
        }
        return SybaseDdlBuilderWrapper.getInstance().getTSQLParameter(name, type, dft, modeLiteral);
    }
    
    protected int getUpdatedParamIndex(IRowData row)
    {        
        int index = _cachedParamRow.indexOf(row);
        
        if (index > 0)
        {
            int validIndex = index;
            for (int i = 0; i < index; i++)
            {                
                if (!_validParamRow.contains(_cachedParamRow.get(i)))
                {
                    validIndex--;
                }
            }
            index = validIndex;
        }
        return index;
    }
    
    /**
     * Subclass must add validator key to the map
     */
    protected Map buildValidationContext(TypedEvent e)
    {
        Map context = super.buildValidationContext(e);

        List items = (List)context.get(SQLModelValidationDelegate.VALIDATION_ITEMS_KEY);
        //validate parameters when parameters are added or deleted or during save
        if (e == null || e.widget == _paraProvider.getTable())
        {
            IValidationItem item = new ContainmentFeatureValidationItem(SQLRoutinesPackage.PROCEDURE__PARAMETERS);
            Map paramsContext = SQLModelValidationDelegate.getCompleteValidationContext(2);
            
            item.setContext(paramsContext);
            items.add(item);
        }

        return context;
    }
    
    protected Collection getFilteredDiagnostics(BasicDiagnostic diagnostics, TypedEvent event)
    {
        Collection filtered = super.getFilteredDiagnostics(diagnostics, event);
        if (event.widget == _paraProvider.getTable())
        {
            for (Iterator iter = ((Diagnostic) _diagnostics).getChildren().iterator(); iter.hasNext();)
            {
                Diagnostic d = (Diagnostic) iter.next();
                if (Integer.toString(SQLRoutinesPackage.ROUTINE__PARAMETERS).equals(d.getSource()))
                {
                    filtered.add(d);
                    continue;
                }
                for (Iterator iterator = d.getData().iterator(); iterator.hasNext();)
                {
                    Object element = (Object) iterator.next();
                    if (element instanceof SybaseParameter)
                    {
                        filtered.add(d);
                        break;
                    }
                }
            }
        }
        return filtered;
    }

    protected void updateButtons()
    {
//        AccessibleTableViewer viewer = _paraProvider.getViewer();
//        int index = viewer.getCursor().getTableRow();
//        int count = viewer.getTable().getItemCount() - 1; //there's always an empty row at the bottom
//        boolean hasSelection = viewer.getCursor().getRow() != null ;
//        boolean tableEnabled = viewer.getTable().isEnabled();
//        //add
//        _paraSection.enableButton(tableEnabled, 0);
//        //delete
//        _paraSection.enableButton(tableEnabled && hasSelection, 1);
//        //TODO delete all: how to get row count?
//        _paraSection.enableButton(tableEnabled && (hasSelection || count > 0), 2);
//        //move up
//        _paraSection.enableButton(tableEnabled && hasSelection && index > 0, 3);
//        //move down
//        _paraSection.enableButton(tableEnabled && hasSelection && index < count - 1 , 4);
    }

    /**
     * RoutineGeneralPage implementation creates FullNameCompositeProvider by default
     */
    protected INameCompositeProvider createNameComposite()
    {
        return new FullNameCompositeProvider();
    }

    /**
     * Synchronize cached parameter row with ParametersData for correct index
     */
    private void synchronizeCachedParamRow() {
        _cachedParamRow.clear();
        List rows = _paramData.getRows();
        if (rows != null && rows.size() > 0)
        {            
            for (int i = 0; i < rows.size(); i++)
            {
                _cachedParamRow.add(rows.get(i));                
            }
        }
    }

    public void revert()
    {
        _uiDiagnostic = null;
        
        //changed fro CR469642-1 problem2
        _paraProvider.getViewer().refresh();
        super.revert();
    }

    public void setFocus(int itemType, Object item)
    {
        super.setFocus(itemType, item);
        if(itemType == PARAMETER_FOCUS)
        {
            AccessibleTableViewer viewer = _paraProvider.getViewer();
            if(item==null)
            {
                int rowNum = _paraProvider.geData().getRows().size();
                viewer.getCursor().setSelection(rowNum, ParametersData.NAME_COLUMN + 1);
                viewer.getCursor().edit();
                return;
            }
            else
            {
                if(item instanceof Parameter)
                {
                    String paraName = ((Parameter)item).getName();
                    Iterator iter = _paraProvider.geData().getRows().iterator();
                    int i = 0;
                    while(iter.hasNext())
                    {
                        Object obj = iter.next();
                        if (!(obj instanceof ParametersRowData)) 
                        {
                            continue;
                        }
                        ParametersRowData row = (ParametersRowData) obj;
                        if(row.getValue(ParametersData.NAME_COLUMN).equals(paraName))
                        {
                            viewer.getCursor().setSelection(i, ParametersData.NAME_COLUMN + 1);
                            viewer.getCursor().edit();
                            return;
                        }
                        i++;
                    }
                }
            }
        }
    }  
}

