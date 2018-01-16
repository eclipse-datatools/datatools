/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.constraints;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.LeafNode;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils.Images;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils.TreeUtil;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.CompositeEditSection;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * The constraints block of the "Constraints" page in table schema editor.<br>
 * To use this block the subclasses need to implement <code>registerPages</code> to register details page for various
 * selection on the constraint tree.
 * 
 * @author Idull
 */
public abstract class SQLConstraintsBlock extends MasterDetailsBlock
{
	/**
     * Horizontal layout the page
     * 
     * @author Idull
     */
    class HorizontalLayoutAction extends Action
    {
        public HorizontalLayoutAction()
        {
            super();
            setImageDescriptor(Images.DESC_HORIZONTAL);
            setText(Messages.SQLConstraintsBlock_h_layout); //$NON-NLS-1$
        }

        public void run()
        {
            sashForm.setOrientation(SWT.HORIZONTAL);
        }
    }

    /**
     * Vertical layout the page
     * 
     * @author Idull
     */
    class VerticalLayoutAction extends Action
    {
        public VerticalLayoutAction()
        {
            super();
            setImageDescriptor(Images.DESC_VERTICAL);
            setText(Messages.SQLConstraintsBlock_v_layout); //$NON-NLS-1$
        }

        public void run()
        {
            sashForm.setOrientation(SWT.VERTICAL);
        }
    }
    
    /**
     * Collapse the tree
     * 
     * @author Idull
     */
    class CollapseAction extends Action
    {
        public CollapseAction()
        {
            super();
            setImageDescriptor(Images.DESC_COLLAPSE_ALL);
            setText(Messages.SQLConstraintsBlock_collapse_all); //$NON-NLS-1$
        }

        public void run()
        {
            _cSection.getViewer().collapseAll();
        }
    }

    /**
     * The master section
     * 
     * @author Idull
     */
    public class ConstraintsSection extends CompositeEditSection
    {
        public static final int                  CONSTRAINT_NAME_COLUMN   = 0;
        public static final int                  CONSTRAINT_INFO_COLUMN   = 1;
        private final static int                 CONSTRAINTS_COLUMN_WIDTH = 150;

        protected Tree                             _cTree;
        private TreeViewer                       _cTreeViewer;
        
        private SQLConstraintsTreeViewerInput _inputProvider;

        public ConstraintsSection(FormToolkit toolkit, String title, Display display, boolean isCollapseable,
                boolean isCollapsed, int estyle)
        {
            super(toolkit, title, display, isCollapseable, isCollapsed, estyle, new String[]{}, 
            		BUTTON_WIDTH, Messages.SQLConstraintsBlock_constraints_description); //$NON-NLS-1$
        }

        public TreeViewer getViewer()
        {
            return _cTreeViewer;
        }
        
        public void createSectionContent(Composite parent)
        {
            GridData gd = new GridData(GridData.FILL_BOTH);
            gd.widthHint = 300;
            getSection().setLayoutData(gd);

            super.createSectionContent(parent);

            _cTree = _toolkit.createTree(getLeftComposite(), SWT.NONE | SWT.SINGLE);
            _cTree.setHeaderVisible(true);
            _cTree.setLinesVisible(true);

            _cTreeViewer = new TreeViewer(_cTree);
            
            TreeColumn cColumn = new TreeColumn(_cTree, SWT.NONE);
            cColumn.setText(Messages.SQLConstraintsBlock_constraints); //$NON-NLS-1$

            TreeColumn iColumn = new TreeColumn(_cTree, SWT.NONE);
            iColumn.setText(Messages.SQLConstraintsBlock_information); //$NON-NLS-1$

            cColumn.pack();
            iColumn.pack();
            cColumn.setWidth(CONSTRAINTS_COLUMN_WIDTH);

            gd = new GridData(GridData.FILL_BOTH);
            _cTree.setLayoutData(gd);

            _cTreeViewer.setContentProvider(new TreeViewerContentProvider());
            _cTreeViewer.setLabelProvider(getLabelProvider());

            _inputProvider = new SQLConstraintsTreeViewerInput(_table);
            _cTreeViewer.setInput(_inputProvider.getRoot());
            _cTreeViewer.expandToLevel(2);
            _toolkit.paintBordersFor(getLeftComposite());
            _toolkit.paintBordersFor(getButtonComp());
            ((ISchemaObjectEditor) _page.getEditor()).getEditorHandler().getNotifier().addModelListener(_inputProvider);
        }
        
        /**
         * This method shoud be called when constraint is added/removed, or model is re-generated
         */
        public void refreshInput()
        {
            String selectedConstraint = ""; //$NON-NLS-1$
            IStructuredSelection selection = (IStructuredSelection) _cSection.getViewer().getSelection();
            Object obj = selection.getFirstElement();
            if (obj instanceof LeafNode)
            {
                LeafNode node = (LeafNode) obj;
                Constraint c = (Constraint) node.getData();
                if (c != null)
                {
                    selectedConstraint = c.getName();
                }
            }
            
            // Remember the expand status of all folder
            boolean rootExpanded = _cSection.getViewer().getExpandedState(_inputProvider.getRoot());
            boolean pkExpanded = _cSection.getViewer().getExpandedState(_inputProvider.getPKFolderNode());
            boolean uniqueExpanded = _cSection.getViewer().getExpandedState(_inputProvider.getUniqueFolderNode());
            boolean fkExpanded = _cSection.getViewer().getExpandedState(_inputProvider.getFKFolderNode());
            boolean ckExpanded = _cSection.getViewer().getExpandedState(_inputProvider.getCKFolderNode());
            
            ((ISchemaObjectEditor) _page.getEditor()).getEditorHandler().getNotifier().removeModelListener(_inputProvider);
            _inputProvider.resetInput(_table);
            _cSection.getViewer().refresh();
            _cSection.getViewer().expandToLevel(2);
            ((ISchemaObjectEditor) _page.getEditor()).getEditorHandler().getNotifier().addModelListener(_inputProvider);
            
            // Reset the expand status of folders
            _cSection.getViewer().setExpandedState(_inputProvider.getRoot(), rootExpanded);
            _cSection.getViewer().setExpandedState(_inputProvider.getPKFolderNode(), pkExpanded);
            _cSection.getViewer().setExpandedState(_inputProvider.getUniqueFolderNode(), uniqueExpanded);
            _cSection.getViewer().setExpandedState(_inputProvider.getFKFolderNode(), fkExpanded);
            _cSection.getViewer().setExpandedState(_inputProvider.getCKFolderNode(), ckExpanded);

            // Reset the selected constraint after saving
            LeafNode newNode = TreeUtil.searchNode(selectedConstraint, _inputProvider.getRoot());
            if (newNode != null)
            {
                IStructuredSelection sel = new StructuredSelection(newNode);
                _cSection.getViewer().setSelection(sel, true);
                _cSection.getViewer().refresh();
            }
        }

    }

    protected SchemaObjectEditorPage _page;
    protected BaseTable              _table;
    protected ConstraintsSection     _cSection;
    protected MenuManager            _menuMgr;
    
    public SQLConstraintsBlock(SchemaObjectEditorPage page, BaseTable table)
    {
        super();
        _table = table;
        _page = page;
    }

    public SQLConstraintsBlock(SchemaObjectEditorPage page, BaseTable table, MenuManager menuMgr)
    {
        super();
        _table = table;
        _page = page;
        _menuMgr = menuMgr;
    }
    
    protected void createMasterPart(IManagedForm managedForm, Composite parent)
    {
        Composite constraintComp = managedForm.getToolkit().createComposite(parent);
        constraintComp.setLayoutData(new GridData(GridData.FILL_BOTH));
        constraintComp.setLayout(new GridLayout());

        _cSection = new ConstraintsSection(managedForm.getToolkit(), Messages.SQLConstraintsBlock_constraints, constraintComp.getDisplay(), false, //$NON-NLS-1$
                false, SWT.NONE);
        _cSection.createControl(constraintComp, 1, null);
        managedForm.addPart(_cSection);
    }

    protected void createToolBarActions(IManagedForm managedForm)
    {
        IToolBarManager toolBar = managedForm.getForm().getToolBarManager();
        toolBar.add(new CollapseAction());
        toolBar.add(new VerticalLayoutAction());
        toolBar.add(new HorizontalLayoutAction());
    }

    /**
     * Returns the viewer in the master part
     * 
     * @return
     */
    public TreeViewer getViewer()
    {
        return _cSection.getViewer();
    }

    /**
     * Returns the details part
     * 
     * @return
     */
    public DetailsPart getDetailsPart()
    {
        return detailsPart;
    }
    
    /**
     * Subclass can override this method to use customized LabelProvider.
     * @return
     */
    public ITableLabelProvider getLabelProvider()
    {
        return new ConstraintsLabelProvider();
    }
    

    public void refresh(BaseTable table)
    {
        _table = table;
        _cSection.refreshInput();
    }
}
