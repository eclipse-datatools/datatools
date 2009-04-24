/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.constraints;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.FolderNode;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.LeafNode;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.ConstraintValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.ASATableSchemaEditorPage;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils.TreeUtil;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidationDelegate;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.ScrolledForm;


/**
 * "Constraints" page for ASA table schema editor.
 * 
 * @author Idull
 */
public class ASAConstraintsEditorPage extends ASATableSchemaEditorPage implements ISchemaObjectEditorPage
{
    protected SQLConstraintsBlock _constraintsBlock;
    protected TreeViewer          _constraintsViewer;
    public static final int       CONSTRAINT_FOCUS     =    0;
    public ASAConstraintsEditorPage()
    {

    }

    public ASAConstraintsEditorPage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
    }

    public ASAConstraintsEditorPage(String id, String title)
    {
        super(id, title);
    }

//    protected DiagnosticChain getDiagnosticChain(TypedEvent event)
//    {
//        if (_constraintsBlock != null)
//        {
//            DetailsPart detailsPart = _constraintsBlock.getDetailsPart();
//            IDetailsPage page = detailsPart.getCurrentPage();
//            if (page instanceof IPropertySheet)
//            {
//                DiagnosticChain chain = ((IPropertySheet) page).getDiagnosticChain();
//                if (chain != null)
//                {
//                    return chain;
//                }
//            }
//        }
//        return new BasicDiagnostic();
//    }
    
    protected void createFormContent(IManagedForm managedForm)
    {
        super.createFormContent(managedForm);
        getPagesNotifier().addModelListener(this);
        _constraintsBlock = createSQLConstraintsBlock(this, _table);
        _constraintsBlock.createContent(managedForm);
        _constraintsViewer = _constraintsBlock.getViewer();

        ScrolledForm sForm = managedForm.getForm();
        sForm.setText(Messages.ASAConstraintsEditorPage_constraints);

        // reflow the page
        managedForm.getForm().reflow(true);
    }

    protected SQLConstraintsBlock createSQLConstraintsBlock(SchemaObjectEditorPage page, BaseTable table)
    {
        return new ASASQLConstraintsBlock(page, table);
    }
    
    /**
     * Refreshes this page.<br>
     */
    public void refresh()
    {
        if (!isPageOpened())
        {
            return;
        }
        super.refresh();

        // Refresh the master part
        _constraintsBlock.refresh(_table);

        // Refresh the details part
        _constraintsViewer.getTree().notifyListeners(SWT.Selection, new Event());
    }

    
    public void modelRegenerated()
    {
        super.modelRegenerated();
        if (!isPageOpened())
        {
            return;
        }
        _constraintsBlock.refresh(_table);
    }

    /**
     * Should refresh this page (Active refresh) when:<br>
     * <ul>
     * <li>Column is added or removed from the constraint
     * <li>Constraint is added or removed
     * </ul>
     * <p>
     * Also, the page will be lazy refreshed when the page changes
     * 
     * @param msg
     */
    
    public void notifyChanged(Notification msg)
    {
        if (msg.isTouch())
        {
            return;
        }

        if (msg.getNotifier() instanceof ReferenceConstraint)
        {
            if (msg.getFeatureID(ReferenceConstraint.class) == SQLConstraintsPackage.eINSTANCE.REFERENCE_CONSTRAINT__MEMBERS)
            {
                refresh();
            }
        }
    }

    
    protected Map buildSharedParams(TypedEvent e)
    {
        Map sharedParams = new HashMap();
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) getEditor().getEditorInput();
        sharedParams.put(ValidatorConstants.DATABASE_IDENTIFIER, input.getDatabaseIdentifier());
        sharedParams.put(ConstraintValidator.DEFAULT_BASE_TABLE, _table);
        return sharedParams;
    }

    
    protected Map buildValidationContext(TypedEvent e)
    {
        Map context = SQLModelValidationDelegate.getCompleteValidationContext(1);
        // context.put(SQLModelValidationDelegate.VALIDATIOR_KEY, new SybaseASABasePrimaryKeyValidator());
        return context;
    }

    
    protected SQLObject[] getSQLObjects()
    {
        if (!isPageOpened() || _table.getConstraints() == null)
        {
            return new SQLObject[0];
        }
        return (SQLObject[]) _table.getConstraints().toArray(new SQLObject[_table.getConstraints().size()]);
    }

	public void setFocus(int itemType, Object item) 
	{
        super.setFocus(itemType, item);
        if(itemType == UNKNOWN_ITEM_TYPE)
        {
        	if(item !=null &&(item instanceof Constraint))
        	{
        		setFocus(CONSTRAINT_FOCUS, item);
        	}
        }
        if (itemType == CONSTRAINT_FOCUS)
        {
            if (item != null)
            {
                if (item instanceof Constraint)
                {
                    Constraint con = (Constraint) item;
                    FolderNode root = null;
                    if (_constraintsBlock.getViewer().getInput() instanceof FolderNode)
                    {
                        root = (FolderNode) _constraintsBlock.getViewer().getInput();
                        LeafNode node = TreeUtil.searchNode(con.getName(), root);
                        if (node != null)
                        {
                            IStructuredSelection ss = new StructuredSelection(new Object[]
                            {
                                node
                            });
                            _constraintsBlock.getViewer().setSelection(ss, true);
                        }
                    }
                }
            }
        }
    }
}
