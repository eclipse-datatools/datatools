/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.common.ui.helpsystem.CommonContextProvider;
import org.eclipse.datatools.sqltools.common.ui.helpsystem.HelpSystemEditorPart;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidationDelegate;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IErrorItem;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorPageDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ILogger;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Images;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.ManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.WorkbenchPart;

/**
 * Copied from <code>FormPage</code> since some fields are private and can not be set. To add a page to an editor, the
 * consumer need to extend this class to create the controls in the page.
 * 
 * @author Idull
 * 
 */
public class SchemaObjectEditorPage extends HelpSystemEditorPart implements ISchemaObjectEditorPage
{
    protected ILogger             _logger       = SOEUIPlugin.getLogger(null);
    private FormEditor            _editor;
    private PageForm              _mform;
    private int                   _index;
    private String                _id;
    protected String              _errorMsg;
    private IEditorDescriptor     _editorDescriptor;
    private IEditorPageDescriptor _pageDescriptor;
    protected DiagnosticChain     _diagnostics;
    protected List                _remembersMarkers;
    protected boolean             _pageOpened   = false;
    protected boolean             _isOnlineMode = false;

    public static class PageForm extends ManagedForm
    {
        public PageForm(SchemaObjectEditorPage page, ScrolledForm form)
        {
            super(page.getEditor().getToolkit(), form);
            setContainer(page);
        }

        public SchemaObjectEditorPage getPage()
        {
            return (SchemaObjectEditorPage) getContainer();
        }

        public void dirtyStateChanged()
        {
            getPage().getEditor().editorDirtyStateChanged();
        }

        public void staleStateChanged()
        {
            if (getPage().isActive())
            {
                refresh();
            }
        }
    }

    public SchemaObjectEditorPage()
    {

    }

    /**
     * A constructor that creates the page and initializes it with the editor.
     * 
     * @param editor the parent editor
     * @param id the unique identifier
     * @param title the page title
     */
    public SchemaObjectEditorPage(FormEditor editor, String id, String title)
    {
        this(id, title);
        initialize(editor);
    }

    /**
     * The constructor. The parent editor need to be passed in the <code>initialize</code> method if this constructor is
     * used.
     * 
     * @param id a unique page identifier
     * @param title a user-friendly page title
     */
    public SchemaObjectEditorPage(String id, String title)
    {
        this._id = id;
        setPartName(title);
    }

    /**
     * Initializes the form page.
     * 
     * @see IEditorPart#init
     */
    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
        setSite(site);
        setInput(input);
    }

    /**
     * Primes the form page with the parent editor instance.
     * 
     * @param editor the parent editor
     */
    public void initialize(FormEditor editor)
    {
        this._editor = editor;
    }

    /**
     * Returns the parent editor.
     * 
     * @return parent editor instance
     */
    public FormEditor getEditor()
    {
        return _editor;
    }

    /**
     * Returns the managed form owned by this page.
     * 
     * @return the managed form
     */
    public IManagedForm getManagedForm()
    {
        return _mform;
    }

    /**
     * Implements the required method by refreshing the form when set active. Subclasses must call super when overriding
     * this method.
     */
    public void setActive(boolean active)
    {
        if (active)
        {
            // We are switching to this page - refresh it
            // if needed.
            _mform.refresh();
        }
        else
        {
            removeRememberedMarkers();
        }
    }

    /**
     * Tests if the page is active by asking the parent editor if this page is the currently active page.
     * 
     * @return <code>true</code> if the page is currently active, <code>false</code> otherwise.
     */
    public boolean isActive()
    {
        return this.equals(_editor.getActivePageInstance());
    }

    /**
     * Creates the part control by creating the managed form using the parent editor's toolkit. Subclasses should
     * override <code>createFormContent(IManagedForm)</code> to populate the form with content.
     * 
     * @param parent the page parent composite
     */
    public void createPartControl(Composite parent)
    {
        ScrolledForm form = _editor.getToolkit().createScrolledForm(parent);
        _mform = createManagedForm(form);
        BusyIndicator.showWhile(parent.getDisplay(), new Runnable()
        {
            public void run()
            {
                createFormContent(_mform);
            }
        });

        _contextProvider = new CommonContextProvider(getEditorDescriptor().getPluginId());
        parent.getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(parent, HelpUtil.getContextId(_pageDescriptor.getContextHelpId(), getEditorDescriptor()
                .getPluginId()));
    }

    protected PageForm createManagedForm(ScrolledForm form)
    {
        return new PageForm(this, form);
    }

    /**
     * Subclasses should override this method to create content in the form hosted in this page.
     * 
     * @param managedForm the form hosted in this page.
     */
    protected void createFormContent(IManagedForm managedForm)
    {
        _pageOpened = true;
        // Add help action to the form tool bar if needed
        if (_pageDescriptor != null && _pageDescriptor.getContextHelpId() != null
                && _pageDescriptor.getContextHelpId().trim().length() != 0)
        {
            IToolBarManager toolBar = managedForm.getForm().getToolBarManager();
            Action help = new Action(Messages.SchemaObjectEditorPage_help_name)
            {
                public void run()
                {
                    displayHelp(_pageDescriptor.getContextHelpId());
                }
            };
            help.setImageDescriptor(Images.DESC_HELP);
            help.setToolTipText(Messages.SchemaObjectEditorPage_help_tip);
            toolBar.add(help);
            managedForm.getForm().updateToolBar();
        }

        // 3.3 API
        managedForm.getToolkit().decorateFormHeading(managedForm.getForm().getForm());
    }

    private void displayHelp(String contextHelpId)
    {
        String contextId = HelpUtil.getContextId(contextHelpId, getEditorDescriptor().getPluginId());
        if ((contextId != null) && (contextId.length() > 0))
        {
            PlatformUI.getWorkbench().getHelpSystem().displayHelp(contextId);
        }
        else
        {
            PlatformUI.getWorkbench().getHelpSystem().displayHelp();
        }
    }

    /**
     * Returns the form page control.
     * 
     * @return managed form's control
     */
    public Control getPartControl()
    {
        return _mform != null ? _mform.getForm() : null;
    }

    /**
     * Disposes the managed form.
     */
    public void dispose()
    {
        if (_mform != null)
        {
            _mform.dispose();
        }

        // remove all the markers when the page is disposed
        removeRememberedMarkers();
    }

    /**
     * Returns the unique identifier that can be used to reference this page.
     * 
     * @return the unique page identifier
     */
    public String getId()
    {
        return _id;
    }

    /**
     * Returns <code>null</code>- form page has no title image. Subclasses may override.
     * 
     * @return <code>null</code>
     */
    public Image getTitleImage()
    {
        return null;
    }

    /**
     * Sets the focus by delegating to the managed form.
     */
    public void setFocus()
    {
        if (_mform != null)
        {
            _mform.setFocus();
        }
    }

    /**
     * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void doSave(IProgressMonitor monitor)
    {
        if (_mform != null)
        {
            _mform.commit(true);
        }
    }

    /**
     * @see org.eclipse.ui.ISaveablePart#doSaveAs()
     */
    public void doSaveAs()
    {
    }

    /**
     * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
     */
    public boolean isSaveAsAllowed()
    {
        return false;
    }

    /**
     * Implemented by testing if the managed form is dirty.
     * 
     * @return <code>true</code> if the managed form is dirty, <code>false</code> otherwise.
     * 
     * @see org.eclipse.ui.ISaveablePart#isDirty()
     */
    public boolean isDirty()
    {
        return _mform != null ? _mform.isDirty() : false;
    }

    /**
     * Preserves the page index.
     * 
     * @param index the assigned page index
     */
    public void setIndex(int index)
    {
        this._index = index;
    }

    /**
     * Returns the saved page index.
     * 
     * @return the page index
     */
    public int getIndex()
    {
        return _index;
    }

    /**
     * Form pages are not editors.
     * 
     * @return <code>false</code>
     */
    public boolean isEditor()
    {
        return false;
    }

    /**
     * Attempts to select and reveal the given object by passing the request to the managed form.
     * 
     * @param object the object to select and reveal in the page if possible.
     * @return <code>true</code> if the page has been successfully selected and revealed by one of the managed form
     *         parts, <code>false</code> otherwise.
     */
    public boolean selectReveal(Object object)
    {
        if (_mform != null)
        {
            return _mform.setInput(object);
        }
        return false;
    }

    /**
     * By default, editor will be allowed to flip the page.
     * 
     * @return <code>true</code>
     */
    public boolean canLeaveThePage()
    {
        return true;
    }

    public void setEditor(FormEditor editor)
    {
        this._editor = editor;
    }

    public void setId(String id)
    {
        this._id = id;
    }

    public void setPartName(String title)
    {
        super.setPartName(title);
    }

    public String getErrorMsg()
    {
        return _errorMsg;
    }

    public IEditorDescriptor getEditorDescriptor()
    {
        return _editorDescriptor;
    }

    public IEditorPageDescriptor getPageDescriptor()
    {
        return _pageDescriptor;
    }

    public void setEditorDescriptor(IEditorDescriptor editor)
    {
        _editorDescriptor = editor;
    }

    public void setPageDescriptor(IEditorPageDescriptor page)
    {
        _pageDescriptor = page;
    }

    /**
     * Sub class should override this method to refresh the page according to the model and if this page is not opened,
     * should not refresh it
     */
    public void refresh()
    {
        removeRememberedMarkers();
    }

    public void revert()
    {
        refresh();
    }

    /**
     * Returns the database identifier
     * 
     * @return
     */
    public DatabaseIdentifier getDatabaseIdentifier()
    {
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) getEditor().getEditorInput();
        return input.getDatabaseIdentifier();
    }

    /**
     * Mark the dirty status
     * 
     */
    public void markDirty()
    {
        ISchemaObjectEditor editor = (ISchemaObjectEditor) getEditor();
        editor.markDirty();
    }

    /**
     * The default implementation of this method validate all the objects belong to this page and return the error items
     * 
     * @param event
     */
    public IErrorItem[] validate(TypedEvent event)
    {
        List errors = new ArrayList();
        _diagnostics = getDiagnosticChain(event);

        boolean isValid = ((BasicDiagnostic) _diagnostics).getChildren().size() == 0;

        SQLObject[] objs = getSQLObjects();
        if (objs == null)
        {
            return new IErrorItem[0];
        }

        // Check each object given in the context
        for (int i = 0; i < objs.length; i++)
        {
            isValid &= SQLModelValidationDelegate.getInstance().validate(objs[i], _diagnostics,
                    buildValidationContext(event), buildSharedParams(event));
        }

        if (!isValid)
        {
            if (((Diagnostic) _diagnostics).getChildren().size() > 0)
            {
                Iterator iter = ((Diagnostic) _diagnostics).getChildren().iterator();
                while (iter.hasNext())
                {
                    Diagnostic d = (Diagnostic) iter.next();
                    errors.add(new ErrorItem(null, d.getMessage(), d.getSeverity()));
                }
            }
        }

        IErrorItem[] items = (IErrorItem[]) errors.toArray(new IErrorItem[errors.size()]);
        return items;
    }

    protected Map buildValidationContext(TypedEvent e)
    {
        Map context = new HashMap();
        return context;
    }

    protected Map buildSharedParams(TypedEvent e)
    {
        Map context = new HashMap();
        return context;
    }

    protected Map buildSharedParams()
    {
        return buildSharedParams(null);
    }

    /**
     * Sub class should override this method to populate the SQL objects belong to this page
     * 
     * @param event
     */
    public void populateSQLObjects(TypedEvent event)
    {

    }

    /**
     * Validates this page and show errors accordingly
     * 
     * @param event
     */
    public void validateAndShowErrors(TypedEvent event)
    {
        IErrorItem[] errors = validate(event);
        showErrorItems(errors);
    }

    /**
     * Sub class should override this method to return the SQL objects belong to this page
     * 
     * @return
     */
    protected SQLObject[] getSQLObjects()
    {
        return new SQLObject[0];
    }

    /**
     * Shows the validation errors.<br>
     * Since multiple threads can concurrently show error items, to prevent duplicate error items, this method should be
     * executed synchronized.
     * 
     * @param items
     */
    protected synchronized void showErrorItems(IErrorItem[] items)
    {
        if (Thread.interrupted())
        {
            return;
        }
        ISchemaObjectEditor editor = (ISchemaObjectEditor) getEditor();
        removeRememberedMarkers();

        if (items == null)
        {
            return;
        }

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IMarker marker = null;
        try
        {
            for (int i = 0; i < items.length; i++)
            {
                int severity = items[i].getSeverity();
                if (severity == Diagnostic.WARNING)
                {
                    severity = IMarker.SEVERITY_WARNING;
                }
                else if (severity == Diagnostic.INFO)
                {
                    severity = IMarker.SEVERITY_INFO;
                }
                else
                {
                    severity = IMarker.SEVERITY_ERROR;
                }
                marker = workspace.getRoot().createMarker(IMarker.PROBLEM);
                marker.setAttribute(IMarker.MESSAGE, items[i].getMessage());
                marker.setAttribute(IMarker.USER_EDITABLE, Boolean.FALSE);
                marker.setAttribute(IMarker.SEVERITY, severity);
                marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
                marker.setAttribute(IMarker.TRANSIENT, Boolean.TRUE);
                marker.setAttribute(IMarker.LOCATION, ((WorkbenchPart) editor).getPartName() + ": "
                        + getPageDescriptor().getPageName());
                _remembersMarkers.add(marker);
            }
        }
        catch (Exception e)
        {
            _logger.error("SchemaObjectEditorPage_marker_creation_error", e);
        }
    }

    protected void removeRememberedMarkers()
    {
        if (_remembersMarkers == null)
        {
            _remembersMarkers = new ArrayList();
        }
        Iterator iter = _remembersMarkers.iterator();
        while (iter.hasNext())
        {
            IMarker marker = (IMarker) iter.next();
            try
            {
                marker.delete();
            }
            catch (CoreException cex)
            {
                _logger.error("SchemaObjectEditorPage_marker_remove_error", cex);
            }
        }
        _remembersMarkers.clear();
    }

    /**
     * Sub class can override this method to put some diagnostics into the chain before validating the model
     * 
     * @return
     */
    protected DiagnosticChain getDiagnosticChain(TypedEvent event)
    {
        return new BasicDiagnostic();
    }

    public boolean isPageOpened()
    {
        return _pageOpened;
    }

    public void modelRegenerated()
    {
        refresh();
    }

    public boolean aboutToSave(IProgressMonitor monitor)
    {
        return true;
    }

    public IErrorItem[] validateOnline(TypedEvent event)
    {
        _isOnlineMode = true;
        IErrorItem[] errors = validate(event);
        _isOnlineMode = false;
        return errors;
    }

    public void menuAboutToShow(IMenuManager manager)
    {

    }

    public String[] getPreferencePageIds()
    {
        return null;
    }

    /**
     * The default implementation sets the current page active
     */
    public void setFocus(int itemType, Object item)
    {
        if (getPageDescriptor().getPageId() != null)
        {
            getEditor().setActivePage(getPageDescriptor().getPageId());
        }
    }

    public void enable(boolean enabled)
    {

    }
}
