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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.ICompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui.INameCompositeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.modelvalidity.DiagnosticUtil;
import org.eclipse.datatools.sqltools.core.modelvalidity.IValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidationDelegate;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidationItem;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.sql.updater.ProceduralObjectSourceUpdater;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


/**
 * 
 * @author Hui Cao
 * 
 */
public abstract class ProceduralObjectGeneralPage extends SchemaObjectEditorPage implements ISchemaObjectEditorPage
{
    protected INameCompositeProvider        _nameProvider;
    protected SQLObject                     _mainObject;
    protected ISchemaObjectEditModel        _editModelObject;
    protected DatabaseDefinition            _databaseDefinition;
    protected DatabaseIdentifier            _databaseIdentifier;
    protected Boolean                       _inInit      = Boolean.TRUE;
    protected ProceduralObjectSourceUpdater _updater     = null;
    protected boolean                       _enabled     = true;
    protected Composite                     _comp;

    /**
     * Keeps track of UI changes
     */
    protected Boolean                       _nameChanged = Boolean.FALSE;

    protected class NameValidator implements Runnable
    {
        public void run()
        {
            Event e = new Event();
            e.widget = _nameProvider.getNameControl();
            validateName(new TypedEvent(e));
        };
    }

    protected NameValidator _nameValidator = new NameValidator();

    public ProceduralObjectGeneralPage()
    {
        // TODO Auto-generated constructor stub
    }

    protected void createFormContent(IManagedForm managedForm)
    {
        super.createFormContent(managedForm);
        Composite comp = managedForm.getForm().getBody();
        _comp = comp;
        managedForm.getForm().setText(Messages.ProceduralObjectGeneralPage_page_name);
        TableWrapLayout layout = new TableWrapLayout();
        comp.setLayout(layout);

        FormToolkit toolkit = managedForm.getToolkit();
        Composite container = toolkit.createComposite(comp);
        layout = new TableWrapLayout();
        container.setLayout(layout);
        TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
        container.setLayoutData(td);

        composeSections(toolkit, container);
        _comp.setEnabled(_enabled);

        ProcIdentifier proc = SQLDevToolsUtil.getProcIdentifier(_databaseIdentifier, _mainObject);
        if (proc != null && SPDebugModelUtil.isProcInDebugging(proc))
        {
            _comp.setEnabled(false);
        }

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
    }

    protected void createTitleSection(FormToolkit toolkit, Composite container)
    {
        Section title = toolkit.createSection(container, 0);
        TableWrapData td1 = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
        title.setLayoutData(td1);
        _nameProvider = createNameComposite();
        Composite nameComp = _nameProvider.getComposite(title, toolkit, ICompositeProvider.NONE);
        title.setClient(nameComp);

        _nameProvider.getNameControl().addModifyListener(new ModifyListener()
        {
            public void modifyText(final ModifyEvent e)
            {
                if (!_inInit.booleanValue())
                {
                    _nameChanged = Boolean.TRUE;
                    markDirty();
                    getSite().getShell().getDisplay().timerExec(1000, _nameValidator);
                }
            }
        });
        _nameProvider.getNameControl().addFocusListener(new FocusAdapter()
        {

            public void focusLost(FocusEvent e)
            {
                super.focusLost(e);
                validateName(e);
            }
        });
    }

    protected abstract INameCompositeProvider createNameComposite();

    protected SQLObject[] getSQLObjects()
    {
        if (_mainObject != null)
        {
            return new SQLObject[]
            {
                _mainObject
            };
        }
        return super.getSQLObjects();
    }

    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
        super.init(site, input);
        setPageInput(input);
    }

    protected void setPageInput(IEditorInput input)
    {
        if (input instanceof SchemaObjectEditorInput)
        {
            _editModelObject = ((SchemaObjectEditorInput) input).getEditModelObject();
            _mainObject = _editModelObject.getMainSQLObject();
            SQLObject oldMainObject = _editModelObject.getSchemaObjectImmutableModel().getMainSQLObject();
            _databaseDefinition = ModelUtil.getDatabaseDefinition(oldMainObject);
            _databaseIdentifier = SQLDevToolsUtil.getDatabaseIdentifier(oldMainObject);
            _updater = createSourceUpdater();
        }
    }

    protected abstract ProceduralObjectSourceUpdater createSourceUpdater();

    /**
     * Refreshes to display the procedural object header
     */
    public void refresh()
    {
        super.refresh();
        _mainObject = null;
        _editModelObject = null;
        setPageInput(getEditorInput());
        if (!isPageOpened())
        {
            return;
        }
        _inInit = Boolean.TRUE;
        initControls();
        _inInit = Boolean.FALSE;
    }

    /**
     * Initializes the controls with the model. Synchronized by _inInit.
     * 
     */
    protected void initControls()
    {
        _nameProvider.getNameControl().setText(_mainObject.getName());
        _nameChanged = Boolean.FALSE;
        validateAndShowErrors(null);
    }

    public void setActive(boolean active)
    {
        super.setActive(active);
        if (active)
        {
            _inInit = Boolean.TRUE;
            initControls();
            _inInit = Boolean.FALSE;
        }
    }

    public boolean canLeaveThePage()
    {
        _errorMsg = null;
        if (_nameChanged.booleanValue())
        {
            _nameValidator.run();
        }
        if (_diagnostics != null && ((Diagnostic) _diagnostics).getChildren().size() > 0)
        {
            Iterator iter = ((Diagnostic) _diagnostics).getChildren().iterator();
            if (iter.hasNext())
            {
                Diagnostic d = (Diagnostic) iter.next();
                if (d.getSeverity() == Diagnostic.ERROR)
                {
                    _errorMsg = d.getMessage();
                }
            }
        }
        if (_errorMsg != null)
        {
            boolean goon = MessageDialog.openQuestion(getSite().getShell(), Messages.GeneralPage_warning, NLS.bind(
                    Messages.GeneralPage_warning_msg, _errorMsg));
            if (goon)
            {
                // To avoid popup again
                _errorMsg = null;
                _diagnostics = new BasicDiagnostic();
            }
            return goon;
        }

        // if error is detected during save, we warn the user but do not prevent him from saving.
        return true;
    }

    /**
     * The ProceduralObjectGeneralPage implementation only creates validation item for the name feature
     */
    protected Map buildValidationContext(TypedEvent e)
    {
        Map context = null;
        if (e == null)
        {
            context = SQLModelValidationDelegate.getCompleteValidationContext(1);
        }
        else
        {
            context = SQLModelValidationDelegate.getBasicValidationContext();
        }
        List items = new ArrayList();
        context.put(SQLModelValidationDelegate.VALIDATION_ITEMS_KEY, items);
        // validate name when it's modified or during save
        if (e == null || e.widget == _nameProvider.getNameControl())
        {
            IValidationItem item = new ValidationItem(SQLSchemaPackage.SQL_OBJECT__NAME);
            // only check name duplication during rename
            if (e == null
                    && renamed())
            {
                // TODO consider offline mode in validator
                item.getContext().put(ValidatorConstants.VALIDATE_DUPLICATE_NAME_VIA_DB, Boolean.valueOf(true));
            }
            items.add(item);
        }

        return context;
    }

    protected boolean renamed()
    {
        SQLObject oldMainObject = _editModelObject.getSchemaObjectImmutableModel().getMainSQLObject();
        return !oldMainObject.getName().equalsIgnoreCase(_mainObject.getName());
    }

    protected Map buildSharedParams(TypedEvent e)
    {
        Map sharedParams = new HashMap();
        sharedParams.put(ValidatorConstants.DATABASE_IDENTIFIER, getDatabaseIdentifier());
        if (false)
        {
            sharedParams.put(ValidatorConstants.VALIDATE_DEFAULT_VALUE_VIA_DB, Boolean.valueOf(true));
        }
        return sharedParams;
    }

    /**
     * The ProceduralObjectGeneralPage implementation removes the diagnostic related to the name feature. Not intended
     * for override. Subclasses should overrided getFilteredDiagnostics() instead. Note: DiagnosticChain only supports
     * add and not remove
     */
    protected DiagnosticChain getDiagnosticChain(TypedEvent event)
    {
        DiagnosticChain diagnosticChain = super.getDiagnosticChain(event);
        // do not filter in case of save
        // _diagnostics contains the last validation result
        if (event == null || _diagnostics == null)
        {
            return diagnosticChain;
        }

        Collection filtered = getFilteredDiagnostics((BasicDiagnostic) _diagnostics, event);
        if (filtered != null && !filtered.isEmpty())
        {
            for (Iterator iter = ((Diagnostic) _diagnostics).getChildren().iterator(); iter.hasNext();)
            {
                Diagnostic d = (Diagnostic) iter.next();
                if (!filtered.contains(d))
                {
                    diagnosticChain.add(d);
                }
            }
        }
        else
        {
            return _diagnostics;
        }
        return diagnosticChain;
    }

    protected Collection getFilteredDiagnostics(BasicDiagnostic diagnostics, TypedEvent event)
    {
        ArrayList filtered = new ArrayList();
        if (event.widget == _nameProvider.getNameControl())
        {
            Diagnostic diag = DiagnosticUtil.getDiagnostic(Integer.toString(SQLSchemaPackage.SQL_OBJECT__NAME),
                    diagnostics, _mainObject);
            if (diag != null)
            {
                filtered.add(diag);
            }
        }
        return filtered;
    }

    protected void validateName(TypedEvent e)
    {
        if (_nameChanged.booleanValue())
        {
            String text = _nameProvider.getNameControl().getText();
            _mainObject.setName(text);
            _nameChanged = Boolean.FALSE;
            boolean valid = false;
            validateAndShowErrors(e);
            Diagnostic d = DiagnosticUtil.getDiagnostic(Integer.toString(SQLSchemaPackage.SQL_OBJECT__NAME),
                    (BasicDiagnostic) _diagnostics, _mainObject);
            if (d == null || d.getSeverity() == Diagnostic.INFO || d.getSeverity() == Diagnostic.WARNING
                    || d.getSeverity() == Diagnostic.OK)
            {
                SQLObject oldMainObject = _editModelObject.getSchemaObjectImmutableModel().getMainSQLObject();
                DatabaseIdentifier databaseIdentifier = SQLDevToolsUtil.getDatabaseIdentifier(oldMainObject);
                String quotedName = SQLUtil.quoteWhenNecessary(text, databaseIdentifier);

                boolean success = _updater.updateName(quotedName);
                if (success)
                {
                    valid = true;
                }
            }
            if (!valid)
            {
                // MessageDialog.openWarning(getSite().getShell(), Messages.Warning_title,
                // Messages.ProceduralObjectGeneralPage_source_not_updated);
            }
        }

    }

    public boolean aboutToSave(IProgressMonitor monitor)
    {
        // make sure the name is validated and updated
        if (_nameChanged.booleanValue())
        {
            _nameValidator.run();
        }
        return super.aboutToSave(monitor);
    }

    public void enable(boolean enabled)
    {
        _enabled = enabled;
        if (!isPageOpened())
        {
            return;
        }
        _comp.setEnabled(_enabled);
    }

    public void setFocus(int itemType, Object item)
    {
        if(item instanceof Trigger || item instanceof Routine || item instanceof org.eclipse.datatools.modelbase.sql.schema.Event)
        {
            _nameProvider.getNameControl().forceFocus();
            _nameProvider.getNameControl().selectAll();
        }
    }
}
