/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.columns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePrimaryKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.ICuttablePart;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.IValidatablePart;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui.ColumnDefaultValueInputDialog;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils.ConstraintCreationUtils;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils.SybComponentsImages;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.sqltools.common.core.tableviewer.AbstractTableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.AccessibleTableViewer;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.CopyRowAction;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.CutRowAction;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.ObjectTransfer;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.PasteRowAction;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.PasteRowActionUtil;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.RemoveRowAction;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableCellEditor;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableCheckBoxCellEditor;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableComboBoxCellEditor;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableDataContentProvider;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableDataLabelProvider;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableDialogCellEditor;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectImmutableModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.SchemaObjectEditorModelListenersNotifier;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * Columns viewer for ASA table schema editor
 * 
 * @author Idull
 */
public class ASATableEditorColumnsViewer
{
    class ColumnRowPasteAction extends PasteRowAction
    {
        public ColumnRowPasteAction(AccessibleTableViewer accessibleTableViewer, Clipboard clipboard)
        {
            super(accessibleTableViewer, clipboard);
            setText(Messages.ASATableEditorColumnsViewer_paste);
        }

        public void run()
        {
            if (!((ITableData) _accessibleTableViewer.getInput()).isNewRowDataAllowed())
            {
                return;
            }

            if (_clipboard == null || _accessibleTableViewer.getCursor().getVisible() == false)
            {
                return;
            }
            RowData rowData = (RowData) _clipboard.getContents(ObjectTransfer.getInstance());

            ITableData tableData = (ITableData) _accessibleTableViewer.getInput();

            Object rData = null;
            
            

            try
            {
                rData = rowData.clone();
                PasteRowActionUtil.addPostfix((RowData)rData, ASATableEditorColumnsTableData.NAME_COLUMN, tableData);
                ((RowData) rData).setTableData(tableData);
                ((ASATableEditorColumnRowData) rData).setColumn(null);
                ((ASATableEditorColumnRowData) rData).markDirty(true);
                ((ASATableEditorColumnRowData)rData).setIsNewlyAdded(true);
                TableItem selectedItem = _accessibleTableViewer.getCursor().getRow();

                // Always insert into the last row
                _insertIndex = _accessibleTableViewer.getTable().getItemCount() - 1;
                tableData.insertRow((IRowData) rData, _insertIndex);
                _accessibleTableViewer.refresh();

                // Focus to the name cell and activate the cell editor
                _accessibleTableViewer.getCursor().setSelection(_insertIndex,
                        ASATableEditorColumnsTableData.NAME_COLUMN + 1);

                // _accessibleTableViewer.getCursor().edit();
            }
            catch (Exception ex)
            {
                IStatus warning = new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, 1,
                        Messages.PasteRowAction_can_not_paste_info, ex); //$NON-NLS-1$
                ErrorDialog.openError(_accessibleTableViewer.getControl().getShell(),
                        Messages.PasteRowAction_can_not_paste_title, null, warning); //$NON-NLS-1$
            }

            /**
             * Remember the index of current inserted row
             */
            _validatablePart.validatePart(null);
        }
    }

    class ColumnRowCutAction extends CutRowAction
    {
        AccessibleTableViewer _accViewer;

        public ColumnRowCutAction(AccessibleTableViewer accessibleTableViewer, Clipboard clipboard)
        {
            super(accessibleTableViewer, clipboard);
            setText(Messages.ASATableEditorColumnsViewer_cut);
            _accViewer = accessibleTableViewer;
        }

        public void run()
        {
            TableItem selectedItem = _accViewer.getCursor().getRow();

            /**
             * Because there is one row to be removed, so need to use the item count minus 2, and may due to accessible
             * table viewer's defect, it always return one more row when calculating the row count
             */
            int selectionIndex = selectedItem != null ? _accViewer.getTable().indexOf(selectedItem) : _accViewer
                    .getTable().getItemCount() - 3;
            if (selectionIndex > _accViewer.getTable().getItemCount() - 3)
            {
                selectionIndex = _accViewer.getTable().getItemCount() - 3;
            }

            /**
             * In fact, the <code>prepareCut</code> method may change some interanl status of the cuttable part, thus
             * if it returns <code>true</code>, cut operation MUST proceed.
             */
            boolean okToCut = _cuttablePart.prepareCut();
            if (!okToCut)
            {
                return;
            }

            super.run();
            _accViewer.getCursor().setSelection(selectionIndex, ASATableEditorColumnsTableData.NAME_COLUMN + 1);
        }

    }

    class ColumnnRowRemoveAction extends RemoveRowAction
    {
        AccessibleTableViewer _accViewer;

        public ColumnnRowRemoveAction(AccessibleTableViewer accessibleTableViewer)
        {
            super(accessibleTableViewer);
            _accViewer = accessibleTableViewer;
            setText(Messages.ASATableEditorColumnsViewer_delete);
            setImageDescriptor(SybComponentsImages.DESC_COLUMN_DELETE);
            setDisabledImageDescriptor(SybComponentsImages.DESC_COLUMN_DELETE_DISABLED);
            
        }

        public void run()
        {
            TableItem selectedItem = _accViewer.getCursor().getRow();

            /**
             * Because there is one row to be removed, so need to use the item count minus 2, and may due to accessible
             * table viewer's defect, it always return one more row when calculating the row count
             */
            int selectionIndex = selectedItem != null ? _accViewer.getTable().indexOf(selectedItem) : _accViewer
                    .getTable().getItemCount() - 3;
            if (selectionIndex > _accViewer.getTable().getItemCount() - 3)
            {
                selectionIndex = _accViewer.getTable().getItemCount() - 3;
            }

            /**
             * In fact, the <code>prepareCut</code> method may change some interanl status of the cuttable part, thus
             * if it returns <code>true</code>, cut operation MUST proceed.
             */
            boolean okToCut = _cuttablePart.prepareCut();
            if (!okToCut)
            {
                return;
            }

            super.run();
            _accViewer.getCursor().setSelection(selectionIndex, ASATableEditorColumnsTableData.NAME_COLUMN + 1);
            _validatablePart.validatePart(null);
        }
    }

    class ColumnRowAddAction extends Action
    {
        private AccessibleTableViewer _accessibleTableViewer;

        public ColumnRowAddAction(AccessibleTableViewer accessibleTableViewer)
        {
            _accessibleTableViewer = accessibleTableViewer;
            setText(Messages.ASATableEditorColumnsViewer_add);
            _accessibleTableViewer = accessibleTableViewer;
            setImageDescriptor(SybComponentsImages.DESC_COLUMN_ADD);
            setDisabledImageDescriptor(SybComponentsImages.DESC_COLUMN_ADD_DISABLED);
        }

        public void run()
        {
            ASATableEditorColumnsTableData data = (ASATableEditorColumnsTableData) _accessibleTableViewer.getInput();
            data.insertRow();
            _accessibleTableViewer.refresh();

            int selection = _accessibleTableViewer.getTable().getItemCount() - 2;
            _accessibleTableViewer.getCursor().setSelection(selection, ASATableEditorColumnsTableData.NAME_COLUMN + 1);
            _accessibleTableViewer.getCursor().edit();
        }
    }

    protected AccessibleTableViewer                    _viewer;
    protected AbstractTableData                        _data;
    protected ICellModifier                            _modifier;
    protected DatabaseDefinition                       _databaseDefinition;
    protected SchemaObjectEditorModelListenersNotifier _notifier;
    /**
     * The schema of the table, it is the container of the UDTs
     */
    protected Schema                                   _schema;
    protected Diagnostic                               _diagnostic;
    /**
     * Whether the unique column is needed or not, in table schema editor, we dont need this column on "Columns" page to
     * avoid coflict with the "Constraints" page
     */
    protected boolean                                  _needUniqueColumn              = true;
    protected boolean                                  _needMarkerColumn              = false;
    protected FormToolkit                              _toolkit;

    public static final String                         COPY_ROW_ACTION_EFINITION_ID   = "org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableschemaeditor.copycolumn"; //$NON-NLS-1$
    public static final String                         PASTE_ROW_ACTION_DEFINITION_ID = "org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableschemaeditor.pastecolumn"; //$NON-NLS-1$
    public static final String                         CUT_ROW_ACTION_DEFINITION_ID   = "org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableschemaeditor.cutcolumn";  //$NON-NLS-1$

    private CutRowAction                               _cutRowAction;
    private CopyRowAction                              _copyRowAction;
    private PasteRowAction                             _pasteRowAction;
    private RemoveRowAction                            _removeRowAction;
    private ColumnRowAddAction                         _addRowAction;
    private MenuManager                                _menuManager;
    private Clipboard                                  _clipboard;

    private static final String                        EDIT_GROUP                     = "edit";                                                //$NON-NLS-1$
    private static final String                        DEL_GROUP                      = "delete";                                              //$NON-NLS-1$

    protected IValidatablePart                         _validatablePart;
    protected ICuttablePart                            _cuttablePart;

    protected IEditorPart                              _editor;
    protected DatabaseIdentifier                       _databaseIdentifier;
    
    private void createActions()
    {
        _clipboard = new Clipboard(_viewer.getControl().getDisplay());
        _cutRowAction = new ColumnRowCutAction(_viewer, _clipboard);
        _copyRowAction = new CopyRowAction(_viewer, _clipboard);
        _copyRowAction.setText(Messages.ASATableEditorColumnsViewer_copy);
        _pasteRowAction = new ColumnRowPasteAction(_viewer, _clipboard);
        _removeRowAction = new ColumnnRowRemoveAction(_viewer);
        _addRowAction = new ColumnRowAddAction(_viewer);

        _cutRowAction.setActionDefinitionId(CUT_ROW_ACTION_DEFINITION_ID);
        _copyRowAction.setActionDefinitionId(COPY_ROW_ACTION_EFINITION_ID);
        _pasteRowAction.setActionDefinitionId(PASTE_ROW_ACTION_DEFINITION_ID);

        if (_editor != null)
        {
            IKeyBindingService keyBinding = _editor.getEditorSite().getKeyBindingService();
            IActionBars bars = _editor.getEditorSite().getActionBars();

            bars.setGlobalActionHandler(CUT_ROW_ACTION_DEFINITION_ID, _cutRowAction);
            keyBinding.registerAction(_cutRowAction);

            bars.setGlobalActionHandler(COPY_ROW_ACTION_EFINITION_ID, _copyRowAction);
            keyBinding.registerAction(_copyRowAction);

            bars.setGlobalActionHandler(PASTE_ROW_ACTION_DEFINITION_ID, _pasteRowAction);
            keyBinding.registerAction(_pasteRowAction);

            bars.updateActionBars();
        }
    }

    private void createContextMenu()
    {
        createActions();
        _menuManager = new MenuManager();
        _menuManager.setRemoveAllWhenShown(true);
        _menuManager.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                manager.add(new Separator(DEL_GROUP));
                manager.appendToGroup(DEL_GROUP, _addRowAction);
                manager.appendToGroup(DEL_GROUP, _removeRowAction);
                manager.add(new Separator(EDIT_GROUP));
                manager.appendToGroup(EDIT_GROUP, _cutRowAction);
                manager.appendToGroup(EDIT_GROUP, _copyRowAction);
                manager.appendToGroup(EDIT_GROUP, _pasteRowAction);
                _pasteRowAction.updateEnablement();
            }
        });
        Menu menu = _menuManager.createContextMenu(_viewer.getTable());
        _viewer.getTable().setMenu(menu);
        _viewer.getCursor().setMenu(menu);
    }

    /**
     * Constructor
     * 
     * @param modifier
     * @param dbdefinition
     * @param data
     * @param schema
     * @param needUniqueColumn whether the unique column is needed or not
     */
    public ASATableEditorColumnsViewer(ICellModifier modifier, DatabaseDefinition dbdefinition, AbstractTableData data,
            Schema schema, boolean needUniqueColumn, boolean needMarkerColumn,
            SchemaObjectEditorModelListenersNotifier notifier, FormToolkit toolkit, IValidatablePart part,
            ICuttablePart cuttablePart, IEditorPart editor, DatabaseIdentifier databaseIdentifier)
    {
        _modifier = modifier;
        _databaseDefinition = dbdefinition;
        _data = data;
        _schema = schema;
        _needUniqueColumn = needUniqueColumn;
        _needMarkerColumn = needMarkerColumn;
        _notifier = notifier;
        _toolkit = toolkit;
        _validatablePart = part;
        _cuttablePart = cuttablePart;
        _editor = editor;
        _databaseIdentifier = databaseIdentifier;
    }

    /**
     * This method initializes _table
     */
    public void createTable(Composite parent)
    {
        Table table = null;
        if (_toolkit != null)
        {
            table = _toolkit.createTable(parent, SWT.V_SCROLL|SWT.H_SCROLL);
            _toolkit.paintBordersFor(parent);
        }
        else
        {
            table = new Table(parent, SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
        }
        _viewer = new AccessibleTableViewer(table);
        createContextMenu();
        GridData gd = new GridData(GridData.FILL_BOTH);
        table.setLayoutData(gd);
        _viewer.setLabelProvider(createLabelProvider());
        _viewer.setContentProvider(new TableDataContentProvider());

        configureTable();
        _viewer.setInput(_data);

        // Set focus to the columns edit area
        _viewer.getTable().setSelection(0);
        _viewer.getCursor().setSelection(0, 3);
    }

    protected Schema getOriginalSchema()
    {
        ISchemaObjectImmutableModel model = ((ISchemaObjectEditorInput) _editor.getEditorInput())
        .getEditModelObject().getSchemaObjectImmutableModel();
        org.eclipse.datatools.modelbase.sql.tables.Table t = (org.eclipse.datatools.modelbase.sql.tables.Table)model.getMainSQLObject();
        return t.getSchema();
    }
    
    /**
     * Configure the columns table.
     * 
     */
    protected void configureTable()
    {
        _viewer.getTable().setHeaderVisible(true);
        _viewer.getTable().setLinesVisible(true);
        _viewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

        CellEditor editors[] = new CellEditor[_data.getColumnCount()];
        String[] properties = new String[_data.getColumnCount()];

        for (int i = 0; i < _data.getColumnCount(); i++)
        {
            TableColumn col = new TableColumn(_viewer.getTable(), SWT.NONE);
            col.setText(_data.getColumnName(i));
            col.pack();
            col.setWidth(ASATableEditorColumnsTableData.COLUMN_LENGTH[i]);

            if (i == ASATableEditorColumnsTableData.MARKER_COLUMN)
            {
                col.setResizable(false);
            }

            // Hide the unique column if not needed
            if (!_needMarkerColumn && i == ASATableEditorColumnsTableData.MARKER_COLUMN)
            {
                col.setResizable(false);
                col.setWidth(0);
                col.setText(""); //$NON-NLS-1$
            }

            // Hide the unique column if not needed
            if (!_needUniqueColumn && i == ASATableEditorColumnsTableData.UNIQUE_COLUMN)
            {
                col.setResizable(false);
                col.setWidth(0);
                col.setText(""); //$NON-NLS-1$
            }
            CellEditor currentEditor = null;
            switch (i)
            {
                case ASATableEditorColumnsTableData.PRI_KEY_COLUMN:
                    currentEditor = new TableCheckBoxCellEditor(_viewer);
                    break;
                case ASATableEditorColumnsTableData.NAME_COLUMN:
                    currentEditor = new TableCellEditor(_viewer);
                    break;
                case ASATableEditorColumnsTableData.TYPE_COLUMN:
                    SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByVendorIdentifier(ProfileUtil
                            .getDatabaseVendorDefinitionId(_databaseIdentifier.getProfileName()));
                    DataTypeProvider provider = config.getSQLDataService().getDataTypeProvider();

                    currentEditor = new TableComboBoxCellEditor(_viewer, provider.getAvailableDataTypesDisplayString(
                            DataTypeProvider.TABLE_COLUMN, getOriginalSchema(), _databaseIdentifier));
                    break;
                case ASATableEditorColumnsTableData.NULLABLE_COLUMN:
                    currentEditor = new TableCheckBoxCellEditor(_viewer);
                    break;
                case ASATableEditorColumnsTableData.DEFAULT_COLUMN:
                    currentEditor = new TableDialogCellEditor(_viewer)
                    {
                        public Object openDialogBox(Control cellEditorWindow)
                        {
                            if (_viewer.getRow() == null)
                            {
                                return null;
                            }
                            ASATableEditorColumnRowData row = (ASATableEditorColumnRowData)_viewer.getRow();
                            SybaseASABaseColumn column = ((ASATableEditorColumnRowData) _viewer.getRow()).getColumn();
                            if (column.getDataType() == null)
                            {
                                String[] buttons = new String[]
                                {
                                    IDialogConstants.OK_LABEL
                                };
                                MessageDialog d = new MessageDialog(ExamplePlugin.getActiveWorkbenchShell(),
                                        Messages.ASATableEditorColumnsViewer_warning, null,
                                        Messages.ASATableEditorColumnsViewer_invalid_data_type, MessageDialog.WARNING,
                                        buttons, 0);
                                d.open();
                                return null;
                            }
                            ColumnDefaultValueInputDialog dlg = new ColumnDefaultValueInputDialog(cellEditorWindow
                                    .getShell(), column, !(column.isIsComputedColumn() || true));
                            if (dlg.open() == Window.OK)
                            {
                                row.updateValue(ASATableEditorColumnsTableData.DEFAULT_COLUMN,
                                        column.getDefaultValue());
                                _viewer.refresh();
                                _viewer.getCursor().edit();
                            }
                            return null;
                        }
                    };
                    break;
                case ASATableEditorColumnsTableData.UNIQUE_COLUMN:
                    // dont create editor for the unique column if not needed
                    if (_needUniqueColumn)
                    {
                        currentEditor = new TableCheckBoxCellEditor(_viewer);
                    }
                    break;
                case ASATableEditorColumnsTableData.COMMENT_COLUMN:
                    currentEditor = new TableCellEditor(_viewer);
                    break;
                default:
                    break;
            }

            editors[i] = currentEditor;
            properties[i] = _data.getColumnName(i);
        }
        _viewer.setColumnProperties(properties);
        _viewer.setCellEditors(editors);
        _viewer.setCellModifier(_modifier);
    }

    public AccessibleTableViewer getViewer()
    {
        return _viewer;
    }

    public void setDataAsInput(AbstractTableData data)
    {
        if (data != null)
        {
            _viewer.setInput(data);
            _data = data;
        }
    }

    public AbstractTableData getData()
    {
        return _data;
    }

    /**
     * Creates a new column instance
     * 
     * @return
     */
    protected SybaseASABaseColumn createColumn()
    {
        return SybaseasabasesqlmodelFactory.eINSTANCE.createSybaseASABaseColumn();
    }

    /**
     * Populate the table using the user's input
     * 
     * @param table
     */
    public synchronized void populateSQLObject(BaseTable table)
    {
        // Clear the history diagnostic
        _diagnostic = null;

        Iterator iter = _data.getRows().iterator();
        boolean isPkCreated = false;

        if (_needUniqueColumn)
        {
            List uniquesTobeRemoved = new ArrayList();
            Iterator i = table.getConstraints().iterator();
            while (i.hasNext())
            {
                SybaseASABaseUniqueConstraint u = (SybaseASABaseUniqueConstraint) i.next();
                if ((u instanceof SybaseASABaseUniqueConstraint) && !(u instanceof SybaseASABasePrimaryKey))
                {
                    uniquesTobeRemoved.add(u);
                }
            }
            table.getConstraints().removeAll(uniquesTobeRemoved);
        }

        PrimaryKey pk = null;
        pk = table.getPrimaryKey();
        if (pk == null)
        {
            pk = SybaseasabasesqlmodelFactory.eINSTANCE.createSybaseASABasePrimaryKey();
            // Add the notifier to the new created element
            if (_notifier != null)
            {
                pk.eAdapters().add(_notifier);
            }
        }

        while (iter.hasNext())
        {
            ASATableEditorColumnRowData row = (ASATableEditorColumnRowData) iter.next();
            if(!row.isDrity())
            {
                continue;
            }
            SybaseASABaseColumn column = row.getColumn();
            boolean isNewCreatedColumn = false;

            // It's a new added column
            if (column == null)
            {
                column = createColumn();
                isNewCreatedColumn = true;
                if (_notifier != null)
                {
                    column.eAdapters().add(_notifier);
                }

                // Notify the listeners
                // table.eSetDeliver(true);
                table.getColumns().add(column);
                // table.eSetDeliver(false);
            }

            // column.eSetDeliver(true);
            column.setName((String) row.getValue(ASATableEditorColumnsTableData.NAME_COLUMN));
            column.setDescription((String) row.getValue(ASATableEditorColumnsTableData.COMMENT_COLUMN));
            String typeStr = (String) row.getValue(ASATableEditorColumnsTableData.TYPE_COLUMN);
            String defaultValue = (String) row.getValue(ASATableEditorColumnsTableData.DEFAULT_COLUMN);
            if (defaultValue != null && defaultValue.length() != 0)
            {
                column.setDefaultValue(defaultValue);
            }
            
            row.setDataTypeString(typeStr);
            try
            {
                if (column.getDataType() == null || (!column.getDataType().getName().equals(typeStr)))
                {
                    // First check if the input data type string is valid
                    SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByVendorIdentifier(ProfileUtil
                            .getDatabaseVendorDefinitionId(_databaseIdentifier.getProfileName()));
                    
                    String error = config.getSQLDataService().getDataTypeValidator()
                            .isValidDataTypeString(typeStr, getOriginalSchema(),
                                    DataTypeProvider.TABLE_COLUMN, _databaseIdentifier);
                    if(error != null)
                    {
                        throw new Exception(error);
                    }
                        
                    DataType dType = config.getSQLDataService().getDataTypeProvider().getDataType(
                            typeStr, getOriginalSchema(), DataTypeProvider.TABLE_COLUMN, _databaseIdentifier);

                    String oldTypeString = ConstraintCreationUtils.getDataTypeFullFormatString(column);
                    if (dType != null)
                    {
                        DataType oldType = column.getDataType();
                        column.setDataType(dType);
                        String newTypeString = ConstraintCreationUtils.getDataTypeFullFormatString(column);

                        // If the type does not change, set it back
                        if (oldTypeString.equals(newTypeString))
                        {
                            column.setDataType(oldType);
                        }
                    }
                    else
                    {
                        // Should not happen
                        column.setDataType(null);
                        if (_diagnostic == null)
                        {
                            _diagnostic = new BasicDiagnostic(Diagnostic.ERROR, Integer
                                    .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__CONTAINED_TYPE), 0,
                                    Messages.ASATableEditorColumnsViewer_invalid_type + typeStr, new Object[]
                                    {});
                        }
                    }
                }
            }
            catch (Exception e)
            {
                column.setDataType(null);
                if (_diagnostic == null)
                {
                    _diagnostic = new BasicDiagnostic(Diagnostic.ERROR, Integer
                            .toString(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__CONTAINED_TYPE), 0, e
                            .getMessage(), new Object[]
                    {});
                }
            }

            boolean nullable = false;
            String nullableStr = (String) row.getValue(ASATableEditorColumnsTableData.NULLABLE_COLUMN);
            if (nullableStr != null)
            {
                nullable = Boolean.valueOf(nullableStr).booleanValue();
            }
            // column.eSetDeliver(true);
            column.setNullable(nullable);
            // column.eSetDeliver(false);

            /**
             * If the current column is selected to be a member in PK, need to check if it's already included in PK, if
             * not, add it.<br>
             * Otherwise if is not selected, need to check if it's already included in PK, if yes, remove it.
             */
            if (Boolean.valueOf((String) row.getValue(ASATableEditorColumnsTableData.PRI_KEY_COLUMN)).booleanValue())
            {
                if (!pk.getMembers().contains(column))
                {
                    pk.getMembers().add(column);
                }
            }
            else
            {
                if (pk != null && pk.getMembers().contains(column))
                {
                    // pk.eSetDeliver(true);
                    pk.getMembers().remove(column);
                    // pk.eSetDeliver(false);
                }
            }
            // TODO: if not selected but contained in pk, remove it

            populateAddtionalColumnParts(column, row);

            if (_needUniqueColumn)
            {
                boolean unique = false;
                String uniqueStr = (String) row.getValue(ASATableEditorColumnsTableData.UNIQUE_COLUMN);
                if (uniqueStr != null)
                {
                    unique = Boolean.valueOf(uniqueStr).booleanValue();
                }
                if (unique)
                {
                    SybaseASABaseUniqueConstraint uniqueConstraint = SybaseasabasesqlmodelFactory.eINSTANCE
                            .createSybaseASABaseUniqueConstraint();
                    uniqueConstraint.getMembers().add(column);
                    table.getConstraints().add(uniqueConstraint);
                }
            }

            column.setTable(table);
        }

        // If it's a new created one
        if (table.getPrimaryKey() == null)
        {
            // table.eSetDeliver(true);
            table.getConstraints().add(pk);
            // table.eSetDeliver(false);
        }

        // remove the original pk
        if (pk.getMembers().size() == 0 && table.getPrimaryKey() != null)
        {
            // table.eSetDeliver(true);
            table.getConstraints().remove(table.getPrimaryKey());
            // table.eSetDeliver(false);
        }
    }

    /**
     * Populate the additional column parts beside the common parts
     * 
     * @param column
     * @param row
     */
    protected void populateAddtionalColumnParts(SybaseASABaseColumn column, IRowData row)
    {
        SybaseASABaseColumn asaColumn = (SybaseASABaseColumn) column;
        ((ASATableEditorColumnRowData) row).setColumn(asaColumn);
        column.setDescription((String) row.getValue(ASATableEditorColumnsTableData.COMMENT_COLUMN));
    }

    /**
     * Returns the diagnostic of this part, which is generated when populating the object. So this method should be
     * called after the <code>populateSQLObject</code> is called.
     * 
     * @return
     */
    public Diagnostic getDiagnosticAfterPopulating()
    {
        // TODO: real time check
        return _diagnostic;
    }

    /**
     * Creates the label provider for the viewer
     * 
     * @return
     */
    protected TableDataLabelProvider createLabelProvider()
    {
        return new ASATableEditorColumnsLabelProvider();
    }
}
