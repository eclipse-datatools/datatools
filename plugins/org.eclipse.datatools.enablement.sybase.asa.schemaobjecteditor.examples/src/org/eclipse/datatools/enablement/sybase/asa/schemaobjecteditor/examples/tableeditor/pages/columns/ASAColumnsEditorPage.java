/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.columns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.LabelFormEntry;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.ASASQLDataOfflineValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.SybaseASABaseConstraintValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.ASATableSchemaEditorPage;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.ICuttablePart;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.IEditableColumns;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.IValidatablePart;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.model.ASATableSchemaEditModel;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils.TableModelUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.common.core.tableviewer.AbstractTableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableDataChangeListener;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.AccessibleTableViewer;
import org.eclipse.datatools.sqltools.core.modelvalidity.ContainmentFeatureValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.DiagnosticUtil;
import org.eclipse.datatools.sqltools.core.modelvalidity.IValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidationDelegate;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IErrorItem;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.SchemaObjectEditorModelListenersNotifier;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.CompositeEditSection;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.TextFormEntry;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.ErrorItem;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * The "Columns" page for ASA
 * 
 * @author Idull
 */
public class ASAColumnsEditorPage extends ASATableSchemaEditorPage implements ISchemaObjectEditorPage,
        IValidatablePart, ICuttablePart, IEditableColumns
{
    ASATableEditorColumnsViewerCellModifier _modifier = new ASATableEditorColumnsViewerCellModifier(null, _table)
    {
        /**
         * Dont set dirty marker if it's not a real modification.
         */
        public void modify(Object element, String property, Object value)
        {
            super.modify(element, property, value);

            ASATableEditorColumnRowData row = (ASATableEditorColumnRowData) _viewer.getRow();

            // It can be a new row
            if (row == null)
            {
                return;
            }

            if (row.isDrity())
            {
                ASAColumnsEditorPage.this.markDirty();
            }

            getPagesNotifier().removeModelListener(ASAColumnsEditorPage.this);

            validateAndShowErrorsAfterPopulating(null);

            getPagesNotifier().addModelListener(ASAColumnsEditorPage.this);
        }

        public boolean canChange(Object element, int index)
        {
            return ASAColumnsEditorPage.this.canChange(element, index);
        }        
    };
    /**
     * "Columns" section in "Columns" page
     * 
     * @author Idull
     */
    class ColumnsSection extends CompositeEditSection
    {
        private static final int            ADD_BUTTON_INDEX    = 0;
        private static final int            REMOVE_BUTTON_INDEX = 1;

        private final static int            BUTTON_WIDTH        = 60;

        private ASATableEditorColumnsViewer _columnsViewer;
        private DatabaseDefinition          _dbdefinition;

        public ColumnsSection(FormToolkit toolkit, String title, Display display, boolean isCollapseable,
                boolean isCollapsed, int estyle)
        {
            super(toolkit, title, display, isCollapseable, isCollapsed, estyle, new String[]
            {
                Messages.ASAColumnsEditorPage_add, Messages.ASAColumnsEditorPage_delete
            }, BUTTON_WIDTH);
            _dbdefinition = ProfileUtil.getDatabaseDefinition(getDatabaseIdentifier().getProfileName());
        }

        public void createSectionContent(Composite parent)
        {
            // Set layout data and layout for section
            getSection().setLayoutData(new GridData(GridData.FILL_BOTH));
            getSection().setLayout(new GridLayout());

            // Set layout data and layout for content
            parent.setLayoutData(new GridData(GridData.FILL_BOTH));
            parent.setLayout(new GridLayout());

            super.createSectionContent(parent);

            _columnsViewer = ASAColumnsEditorPage.this.createTableEditorColumnsViewer(_modifier, _dbdefinition, _data,
                    _table.getSchema(), false, true, getPagesNotifier(), _toolkit, ASAColumnsEditorPage.this,
                    ASAColumnsEditorPage.this, ASAColumnsEditorPage.this.getEditor());
            _columnsViewer.createTable(getLeftComposite());
            _modifier.setViewer(_columnsViewer.getViewer());
            _columnsViewer.getViewer().getTable().setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
        }

        public ASATableEditorColumnsViewer getColumnsViewer()
        {
            return _columnsViewer;
        }

        public boolean cutOrRemove()
        {
            if (_columnsSection.getColumnsViewer().getViewer().getCursor().getRow() == null
                    || !(_columnsSection.getColumnsViewer().getViewer().getCursor().getRow().getData() instanceof ASATableEditorColumnRowData))
            {
                return false;
            }

            ASATableEditorColumnRowData row = (ASATableEditorColumnRowData) _columnsSection.getColumnsViewer()
                    .getViewer().getCursor().getRow().getData();

            if (row.getColumn() != null && _originalColumns.contains(row.getColumn()) && _originalColumns.size() == 1)
            {
                String[] buttons = new String[]
                {
                    IDialogConstants.OK_LABEL
                };
                MessageDialog d = new MessageDialog(ExamplePlugin.getActiveWorkbenchShell(), Messages.ASAColumnsEditorPage_can_not_remove, null,
                        Messages.ASAColumnsEditorPage_can_not_remove_desp, MessageDialog.WARNING, buttons, 0);
                d.open();
                return false;
            }

            // Check if it's ok to remove the selected column
            if (row.getColumn() != null && TableModelUtil.isReferencedColumn(_table, row.getColumn()))
            {
                // Check if it's reference by a FK
                List fks = TableModelUtil.getMatchedColumnForeignKeys(_table, row.getColumn());
                if (fks.size() > 0)
                {
                    String[] buttons = new String[]
                    {
                        IDialogConstants.OK_LABEL
                    };
                    MessageDialog d = new MessageDialog(_tableNameEntry.getText().getShell(), Messages.ASAColumnsEditorPage_error, null,
                            Messages.ASAColumnsEditorPage_referenced_by_fk
                                    + TableModelUtil.constructConstraintNamesList(fks)
                                    + Messages.ASAColumnsEditorPage_remove_fks, MessageDialog.ERROR, buttons, 0);
                    d.open();
                    return false;
                }

                // Check if there are unique constraints to be removed due the deletion of this column
                List constraintsTobeRemoved = TableModelUtil.getMatchedColumnUniqueConstraint(_table, row.getColumn());
                List columnCkConstraints = TableModelUtil.getMatchedColumnCheckConstraint(_table, row.getColumn());
                constraintsTobeRemoved.addAll(columnCkConstraints);
                if (constraintsTobeRemoved.size() > 0)
                {
                    IPreferenceStore store = ExamplePlugin.getDefault().getPreferenceStore();
                    if (true)
                    {
                        MessageDialogWithToggle d = MessageDialogWithToggle.openYesNoQuestion(_tableNameEntry.getText()
                                .getShell(), Messages.ASAColumnsEditorPage_remove_constraints, Messages.ASAColumnsEditorPage_also_remove_constraints
                                + TableModelUtil.constructConstraintNamesList(constraintsTobeRemoved) + Messages.ASAColumnsEditorPage_continue,
                                Messages.ASAColumnsEditorPage_always_remove, false, store,
                                "");
                        int result = d.getReturnCode();
                        switch (result)
                        {
                            case IDialogConstants.YES_ID:
                                Iterator iter = constraintsTobeRemoved.iterator();
                                while (iter.hasNext())
                                {
                                    _table.getConstraints().remove(iter.next());
                                }
                                break;
                            case IDialogConstants.NO_ID:
                                return false;
                            default:
                                break;
                        }
                    }
                    else
                    {
                        Iterator iter = constraintsTobeRemoved.iterator();
                        while (iter.hasNext())
                        {
                            _table.getConstraints().remove(iter.next());
                        }
                    }
                }
            }

            // remove this column from reference constraints
            TableModelUtil.removeColumnFromRefConstraints(_table, row.getColumn());

            ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) getEditor().getEditorInput();
            List authids = (List) input.getEditModelObject().getAdditionalSQLObjects().get(
                    PrivilegesConstants.AUTH_ID_ITEMS);

            // remove the privileges for this column
            TableModelUtil.removePrivilegeForColumn(row.getColumn(), authids);

            if (row.getColumn() != null)
            {
                _table.getColumns().remove(row.getColumn());
                if (_originalColumns.contains(row.getColumn()))
                {
                    _originalColumns.remove(row.getColumn());
                }
            }
            return true;
        }

        
        protected void buttonSelected(SelectionEvent e, int buttonIndex)
        {
            AccessibleTableViewer viewer = _columnsSection.getColumnsViewer().getViewer();
            switch (buttonIndex)
            {
                case REMOVE_BUTTON_INDEX:
                    TableItem selectedItem = viewer.getCursor().getRow();
                    /**
                     * Because there is one row to be removed, so need to use the item count minus 2, and may due to
                     * accessible table viewer's defect, it always return one more row when calculating the row count
                     */
                    int selectionIndex = selectedItem != null ? viewer.getTable().indexOf(selectedItem) : viewer
                            .getTable().getItemCount() - 3;
                    if (selectionIndex > viewer.getTable().getItemCount() - 3)
                    {
                        selectionIndex = viewer.getTable().getItemCount() - 3;
                    }
                    boolean okToProceed = cutOrRemove();
                    if (!okToProceed)
                    {
                        return;
                    }
                    // remove the selected IRowData from ITableData
                    viewer.doDelete();
                    viewer.refresh();
                    viewer.getCursor().setSelection(selectionIndex, ASATableEditorColumnsTableData.NAME_COLUMN + 1);

                    // _table.eSetDeliver(false);
                    validateAndShowErrorsAfterPopulating(e);
                    ASAColumnsEditorPage.this.markDirty();
                    break;
                case ADD_BUTTON_INDEX:
                    insertRow();
                    break;
                default:
                    break;
            }

        }

		public void insertRow() {
			_data.insertRow();
			_columnsSection.getColumnsViewer().getViewer().refresh();
			ASAColumnsEditorPage.this.markDirty();
			AccessibleTableViewer viewer = _columnsSection.getColumnsViewer().getViewer(); 
			int selection = viewer.getTable().getItemCount() - 2;
			viewer.getCursor().setSelection(selection, ASATableEditorColumnsTableData.NAME_COLUMN + 1);
			viewer.getCursor().edit();
		}

        public void refresh()
        {
            // dont unset the dirty maker
        }
        
    }

    private TextFormEntry                  _tableNameEntry;
    private TextFormEntry                  _commentEntry;
    private ColumnsSection                 _columnsSection;

    // The columns table data
    private ASATableEditorColumnsTableData _data;

    private boolean                        _isFirstTimeValidation = true;
    private Diagnostic                     _nameDiagnostic;
    private boolean                        _checkName;
    private boolean                        _modelRegenerated      = false;
    private IEditorPart                    _dataEditor;
    public static final int                COLUMN_FOCUS           = 0;
    

	/**
     * Keep all the orginal columns in a list to prevent the end user removing all the columns.<br>
     * This should be refreshed when model is regenerated.
     */
    private List                           _originalColumns;
    protected ModelConverter               _modelConverter;
    private Runnable                       _validateNameRunnable  = new Runnable()
                                                                  {
                                                                      public void run()
                                                                      {
                                                                          validateAndShowErrorsAfterPopulating(null);
                                                                      }
                                                                  };
    private ModifyListener                 _nameModifyListener    = new ModifyListener()
                                                                  {
                                                                      public void modifyText(final ModifyEvent e)
                                                                      {
                                                                          if (_table.getForeignKeys().size() > 0)
                                                                          {
                                                                              String[] buttons = new String[]
                                                                              {
                                                                                  IDialogConstants.OK_LABEL
                                                                              };
                                                                              MessageDialog d = new MessageDialog(
                                                                                      ExamplePlugin
                                                                                              .getActiveWorkbenchShell(),
                                                                                      Messages.ASAColumnsEditorPage_warning,
                                                                                      null,
                                                                                      Messages.ASAColumnsEditorPage_can_not_rename,
                                                                                      MessageDialog.WARNING, buttons, 0);
                                                                              d.open();
                                                                              _tableNameEntry.getText()
                                                                                      .removeModifyListener(
                                                                                              _nameModifyListener);
                                                                              _tableNameEntry.getText().setText(
                                                                                      _table.getName());
                                                                              _tableNameEntry.getText()
                                                                                      .addModifyListener(
                                                                                              _nameModifyListener);
                                                                              return;
                                                                          }
                                                                          // _table.eSetDeliver(true);
                                                                          _table.setName(_tableNameEntry.getText()
                                                                                  .getText());
                                                                          // _table.eSetDeliver(false);
                                                                          markDirty();
                                                                          _tableNameEntry.getText().getDisplay()
                                                                                  .timerExec(400, _validateNameRunnable);
                                                                      }
                                                                  };

    private ModifyListener                 _commentModifyListener = new ModifyListener()
                                                                  {
                                                                      public void modifyText(ModifyEvent e)
                                                                      {
                                                                          _table.setDescription(_commentEntry.getText()
                                                                                  .getText());
                                                                          markDirty();
                                                                      }
                                                                  };
    private ITableDataChangeListener       _rowModifedListener    = new ITableDataChangeListener()
                                                                  {
                                                                      public void rowAdded(IRowData row)
                                                                      {
                                                                          ASAColumnsEditorPage.this.markDirty();
                                                                      }

                                                                      public void rowDataUpdated(IRowData row, int col,
                                                                              Object oldVal, Object newVal)
                                                                      {
                                                                          /**
                                                                             * Set dirty marker when the value of the
                                                                             * given row is updated (MARKER column and
                                                                             * PRI_KEY column not included, and the
                                                                             * dirty marker for PRI_KEY column is set in
                                                                             * the <code>modify()</code> method of
                                                                             * cell modifier)
                                                                             */
                                                                          if (row != null
                                                                                  && col != ASATableEditorColumnsTableData.MARKER_COLUMN
                                                                                  && col != ASATableEditorColumnsTableData.PRI_KEY_COLUMN)
                                                                          {
                                                                              /**
                                                                                 * If the new value and old value are
                                                                                 * null or empty, they are identical
                                                                                 */
                                                                              if (newVal == null && oldVal == null)
                                                                              {
                                                                                  return;
                                                                              }
                                                                              else if (newVal == null
                                                                                      && (oldVal.toString().length() == 0))
                                                                              {
                                                                                  return;
                                                                              }
                                                                              else if (oldVal == null
                                                                                      && (newVal.toString().length() == 0))
                                                                              {
                                                                                  return;
                                                                              }

                                                                              /**
                                                                                 * All other types (Integer, Boolean,
                                                                                 * etc.) can be compared by converting
                                                                                 * to a string
                                                                                 */
                                                                              else if (newVal != null && oldVal != null)
                                                                              {
                                                                                  String currentStrValue = newVal
                                                                                          .toString();
                                                                                  String modifiedStringValue = oldVal
                                                                                          .toString();
                                                                                  if (col == ASATableEditorColumnsTableData.TYPE_COLUMN)
                                                                                  {
                                                                                      // For datatype column, the case
                                                                                      // can be
                                                                                      // ignored
                                                                                      if (currentStrValue
                                                                                              .equalsIgnoreCase(modifiedStringValue))
                                                                                      {
                                                                                          return;
                                                                                      }
                                                                                  }
                                                                                  if (currentStrValue
                                                                                          .equals(modifiedStringValue))
                                                                                  {
                                                                                      return;
                                                                                  }
                                                                              }

                                                                              /**
                                                                                 * Set the dirty marker for both row and
                                                                                 * editor if the value changes
                                                                                 */
                                                                              ((ASATableEditorColumnRowData) row)
                                                                                      .markDirty(true);
                                                                              ASAColumnsEditorPage.this.markDirty();
                                                                              _columnsSection.getColumnsViewer()
                                                                                      .getViewer().refresh();
                                                                          }
                                                                      }

                                                                      public void rowDeleted(IRowData row)
                                                                      {
                                                                          ASAColumnsEditorPage.this.markDirty();
                                                                      }
                                                                  };

    public ASAColumnsEditorPage()
    {
        _originalColumns = new ArrayList();
    }

    public ASAColumnsEditorPage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
        _originalColumns = new ArrayList();
    }

    public ASAColumnsEditorPage(String id, String title)
    {
        super(id, title);
        _originalColumns = new ArrayList();
    }

    protected void createFormContent(IManagedForm managedForm)
    {
        super.createFormContent(managedForm);
        _data = getModelConverter().getColumnsData(_table, null);
        _data.addTableDataChangeListener(_rowModifedListener);
        _originalColumns.clear();
        _originalColumns.addAll(_table.getColumns());

        managedForm.getForm().setText(Messages.ASAColumnsEditorPage_columns);

        Composite parent = managedForm.getForm().getBody();
        GridLayout layout = new GridLayout();

        layout.numColumns = 1;
        layout.horizontalSpacing = 10;
        parent.setLayout(layout);

        FormToolkit toolKit = managedForm.getToolkit();

        Composite top = toolKit.createComposite(parent, SWT.NONE);
        top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        layout = new GridLayout();
        layout.numColumns = 2;
        top.setLayout(layout);

        // Create the owner entry
        LabelFormEntry ownerNameLabel = new LabelFormEntry(top, toolKit, SWT.NONE, Messages.ASAColumnsEditorPage_owner, 2);
        ownerNameLabel.getValueLabel().setText(_table.getSchema().getName());

        // Create the name entry
        _tableNameEntry = new TextFormEntry(top, toolKit, SWT.NONE, Messages.ASAColumnsEditorPage_table_name, null, 2);
        _tableNameEntry.getText().setText(_table.getName());
        _tableNameEntry.getText().setToolTipText(Messages.ASAColumnsEditorPage_table_name_tip);
        _tableNameEntry.getText().addModifyListener(_nameModifyListener);

        // Create the comment entry
        _commentEntry = new TextFormEntry(top, toolKit, SWT.WRAP | SWT.V_SCROLL | SWT.MULTI, Messages.ASAColumnsEditorPage_comment_of_table, null, 2);
        _commentEntry.getText().setToolTipText(Messages.ASAColumnsEditorPage_comment_tip);
        GridData gd = (GridData) _commentEntry.getText().getLayoutData();
        gd.heightHint = 40;
        _commentEntry.getText().setLayoutData(gd);
        if (_table.getDescription() != null)
        {
            _commentEntry.getText().setText(_table.getDescription());
        }
        _commentEntry.getText().addModifyListener(_commentModifyListener);

        Composite bottom = toolKit.createComposite(parent, SWT.NONE);
        bottom.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout glayout = new GridLayout();
        bottom.setLayout(glayout);

        _columnsSection = new ColumnsSection(toolKit, Messages.ASAColumnsEditorPage_columns_section, bottom.getDisplay(), false, false, SWT.NONE);
        _columnsSection.createControl(bottom, 1, null);
        managedForm.addPart(_columnsSection);
        _columnsSection.getColumnsViewer().getViewer().getTable().setToolTipText(Messages.ASAColumnsEditorPage_columns_tip);
        toolKit.paintBordersFor(top);

        // reflow the scrolled form
        managedForm.getForm().reflow(true);
    }

    /**
     * When populating the SQL object, should remove the "this" listener to avoid refresh itself
     */
    public void populateSQLObjects(TypedEvent event)
    {
        getPagesNotifier().removeModelListener(this);
        // _table.eSetDeliver(false);
        // _table.setName(_tableNameEntry.getText().getText());

        // _table.setDescription(_commentEntry.getText().getText());
        _columnsSection.getColumnsViewer().populateSQLObject(_table);

        getPagesNotifier().addModelListener(this);
    }

    /**
     * Need to validate the table name on this page, and perform a level 1 complete validation for all columns
     * 
     * @param event
     */
    protected Map buildValidationContext(TypedEvent event)
    {
        Map context = new HashMap();
        context.put(SQLModelValidationDelegate.VALIDATION_SCOPE_KEY,
                SQLModelValidationDelegate.VALIDATION_SCOPE_BASIC_VALUE);

        List items = new ArrayList();

        if (_initialTabelName != null && !_initialTabelName.equals(_table.getName()))
        {
            // should be checked for the first time, or the event is null, or the name is changed
            if (event == null || (event != null && event.getSource() == _tableNameEntry.getText())
                    || _isFirstTimeValidation)
            {
                IValidationItem item = new ValidationItem(SybaseasasqlmodelPackage.SYBASE_ASA_TABLE__NAME);
                if (_isOnlineMode)
                {
                    item.getContext().put(ValidatorConstants.VALIDATE_DUPLICATE_NAME_VIA_DB, Boolean.TRUE);
                }
                items.add(item);
                _checkName = true;
            }
            else
            {
                _checkName = false;
            }
        }
        // Check all columns
        ContainmentFeatureValidationItem item = new ContainmentFeatureValidationItem(
                SybaseasasqlmodelPackage.SYBASE_ASA_TABLE__COLUMNS);
        item.setContext(SQLModelValidationDelegate.getPlainValidationContext());
        items.add(item);
        
        // Only check members in primary key
        item = new ContainmentFeatureValidationItem(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__CONSTRAINTS);
        Map constraintsContext = SQLModelValidationDelegate.getBasicValidationContext();

        Map pkContext = SQLModelValidationDelegate.getBasicValidationContext();
        List pkItems = new ArrayList();
        // check the members in pk 
        pkItems.add(new ValidationItem(SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PRIMARY_KEY__MEMBERS, IValidationItem.REFERENCE_TYPE));
        pkContext.put(SQLModelValidationDelegate.VALIDATION_ITEMS_KEY, pkItems);

        constraintsContext.put(SybaseASABaseConstraintValidator.PK_VALIDATION_TYPE, pkContext);
        item.setContext(constraintsContext);

        items.add(item);

        context.put(SQLModelValidationDelegate.VALIDATION_ITEMS_KEY, items);
        //context.put(SQLModelValidationDelegate.VALIDATIOR_KEY, new SybaseASABaseTableValidator());

        _isFirstTimeValidation = false;
        return context;
    }

    /**
     * The database identifier is needed by the identifier validator
     * 
     */
    protected Map buildSharedParams(TypedEvent e)
    {
        Map sharedParams = new HashMap();
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) getEditor().getEditorInput();
        sharedParams.put(ValidatorConstants.DATABASE_IDENTIFIER, input.getDatabaseIdentifier());
        if (_isOnlineMode)
        {
            sharedParams.put(ValidatorConstants.VALIDATE_DEFAULT_VALUE_VIA_DB, Boolean.TRUE);
        }
        sharedParams.put(ValidatorConstants.DATA_OFFLINE_VALIDATOR, new ASASQLDataOfflineValidator());
        return sharedParams;
    }

    /**
     * Althought the main objects on this page are columns, other information is also included: table name.
     * <p>
     * The validation can be fine-grain controlled by the <code>buildValidationContext()</code> method
     * 
     * @return
     */
    protected SQLObject[] getSQLObjects()
    {
        return new SQLObject[]
        {
            _table
        };
    }

    /**
     * Refreshes the UI according to the new model.<br>
     * MUST be careful when calling this method, coz it may overwrite what the user inputs if this method is called
     * before the <code>populateSQLObjects</code> is called
     */
    public void refresh()
    {
        super.refresh();
        if (!isPageOpened())
        {
            return;
        }

        // Generate a new model based on the dirty table
        ASATableEditorColumnsTableData data = getModelConverter().getColumnsData(_table,
                _columnsSection.getColumnsViewer().getData().getRows());

        // Set dirty marker except the model is a newly generated one
        if (!_modelRegenerated)
        {
            Vector rows = _columnsSection.getColumnsViewer().getData().getRows();
            for (int i = 0; (i < data.getRows().size()) && (i < rows.size()); i++)
            {
                ASATableEditorColumnRowData row = (ASATableEditorColumnRowData) rows.get(i);
                if (row.isDrity())
                {
                    ((ASATableEditorColumnRowData) data.getRows().get(i)).markDirty(true);
                }
            }
        }
        else
        {
            // Clear the "newly added" information for all rows since this model is cloned after sync with db
            Iterator i = data.getRows().iterator();
            while(i.hasNext())
            {
                Object obj = i.next();
                if(obj instanceof ASATableEditorColumnRowData)
                {
                    ASATableEditorColumnRowData row = (ASATableEditorColumnRowData)obj;
                    row.setIsNewlyAdded(false);
                }
            }
        }
        _modelRegenerated = false;

        // Reset the columns (wont trigger the modify listeners)
        _data = data;
        _data.addTableDataChangeListener(_rowModifedListener);
        _columnsSection.getColumnsViewer().setDataAsInput(data);
        _columnsSection.getColumnsViewer().getViewer().refresh();

        // Reset name
        _tableNameEntry.getText().removeModifyListener(_nameModifyListener);
        _tableNameEntry.getText().setText(_table.getName());
        _tableNameEntry.getText().addModifyListener(_nameModifyListener);

        // Reset comment
        _commentEntry.getText().removeModifyListener(_commentModifyListener);
        _commentEntry.getText().setText(_table.getDescription() == null ? "" : _table.getDescription()); //$NON-NLS-1$
        _commentEntry.getText().addModifyListener(_commentModifyListener);
        validateAndShowErrorsAfterPopulating(null);
    }

    /**
     * Does nothing but refresh the page according to the reverted model
     */
    public void revert()
    {
        _modelRegenerated = true;
        // Overwrite the old model
        _table = (BaseTable) ((ASATableSchemaEditModel) ((ISchemaObjectEditorInput) getEditorInput())
                .getEditModelObject()).getMainSQLObject();
        refresh();
    }

    public IErrorItem[] validate(TypedEvent event)
    {
        if (!isPageOpened())
        {
            return new IErrorItem[0];
        }

        IErrorItem[] errors = super.validate(event);

        // If the name is checked, should keep the diagnostic
        if (_checkName)
        {
            BasicDiagnostic basicDiagnostic = (BasicDiagnostic) _diagnostics;
            _nameDiagnostic = DiagnosticUtil.getDiagnostic(Integer
                    .toString(SybaseasasqlmodelPackage.SYBASE_ASA_TABLE__NAME), basicDiagnostic,
                    SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseTable());
        }
        // Else add the kept diagnostic
        else
        {
            if (_nameDiagnostic != null)
            {
                IErrorItem[] items = new IErrorItem[errors.length + 1];
                items[0] = new ErrorItem(_tableNameEntry.getText(), _nameDiagnostic.getMessage());
                for (int i = 0; i < errors.length; i++)
                {
                    items[i + 1] = errors[i];
                }
                return items;
            }
        }

        return errors;
    }

    protected DiagnosticChain getDiagnosticChain(TypedEvent event)
    {
        DiagnosticChain diagnostics = new BasicDiagnostic();

        // add the check result for datatype
        if (_columnsSection.getColumnsViewer().getDiagnosticAfterPopulating() != null)
        {
            diagnostics.add(_columnsSection.getColumnsViewer().getDiagnosticAfterPopulating());
        }
        return diagnostics;
    }

    /**
     * Do something when the model change event occurs, generally we refresh this page, but since the refresh method is
     * called when page changes (Lazy refresh), we dont need to refresh here.
     */
    
    public void notifyChanged(Notification msg)
    {

    }

    
    public void modelRegenerated()
    {
        _modelRegenerated = true;
        super.modelRegenerated();
        _originalColumns.clear();
        _originalColumns.addAll(_table.getColumns());
    }

    public String[] getPreferencePageIds()
    {
        return new String[]
        {
            ""
        };
    }

    public void validatePart(TypedEvent event)
    {
        validateAndShowErrorsAfterPopulating(event);
    }

    /**
     * After this method is called and returns <code>true</code>, cut operation MUST proceed.
     * 
     * @return
     */
    public boolean prepareCut()
    {
        return _columnsSection.cutOrRemove();
    }
    
	public void setFocus(int itemType, Object item) 
    {
		super.setFocus(itemType, item);
		if(itemType == UNKNOWN_ITEM_TYPE)
		{
			//Set focus to a column
			if(item instanceof Column)
			{
				Column c = (Column)item;
				setFocus(COLUMN_FOCUS, c.getName());
			}
			
			//Set focus to the name field of the table
			if(item instanceof Table)
			{
				_tableNameEntry.getText().forceFocus();
				_tableNameEntry.getText().selectAll();
			}
		}
		if(itemType == COLUMN_FOCUS)
		{
			if(item != null)
			{
				if(item instanceof String)
				{
					AccessibleTableViewer viewer = _columnsSection.getColumnsViewer().getViewer();
					String colName = (String)item;
					Iterator iter = _data.getRows().iterator();
					int i = 0;
					while(iter.hasNext())
					{
						Object obj = iter.next();
				        if (!(obj instanceof ASATableEditorColumnRowData)) 
				        {
							continue;
						}
						ASATableEditorColumnRowData row = (ASATableEditorColumnRowData) obj;
						if(row.getColumn().getName() != null && row.getColumn().getName().equals(colName))
						{
							viewer.getCursor().setSelection(i, ASATableEditorColumnsTableData.NAME_COLUMN + 1);
		                    viewer.getCursor().edit();
		                    return;
						}
						i++;
					}
				}
			}
		}
	}
	
    // /////////////////////////////////Refactor to allow IQ extend
    
    protected ASATableEditorColumnsViewer createTableEditorColumnsViewer(ASATableEditorColumnsViewerCellModifier modifier,
            DatabaseDefinition dbdefinition, AbstractTableData data, Schema schema, boolean needUniqueColumn,
            boolean needMarkerColumn, SchemaObjectEditorModelListenersNotifier notifier, FormToolkit toolkit,
            IValidatablePart part, ICuttablePart cuttablePart, IEditorPart editor)
    {
        return new ASATableEditorColumnsViewer(modifier, dbdefinition, data, schema, needUniqueColumn,
                needMarkerColumn, notifier, toolkit, part, cuttablePart, editor, getDatabaseIdentifier());
    }
    
    protected ModelConverter getModelConverter()
    {
        if(_modelConverter == null)
        {
            _modelConverter = new ModelConverter();
        }
        return _modelConverter;
    }
    
    protected boolean canChange(Object element, int index)
    {
        if (index == ASATableEditorColumnsTableData.UNIQUE_COLUMN)
        {
            return false;
        }
        if (index == ASATableEditorColumnsTableData.MARKER_COLUMN)
        {
            return false;
        }
        return true;
    }

    public boolean isColumnsDirty()
    {
        if (_data == null || _data.getRows() == null)
        {
            return false;
        }
        Iterator iter = _data.getRows().iterator();
        while (iter.hasNext())
        {
            Object obj = iter.next();
            if (!(obj instanceof ASATableEditorColumnRowData))
            {
                continue;
            }
            ASATableEditorColumnRowData row = (ASATableEditorColumnRowData) obj;
            if (row.isDrity())
            {
                return true;
            }
        }
        return false;
    }
    
    public void insertColumn()
    {
    	_columnsSection.insertRow();
    }
    
}