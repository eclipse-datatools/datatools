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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IErrorItem;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IResourceChangeEvent;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorPageDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.core.ErrorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ILogger;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.part.IWorkbenchPartOrientation;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * The base editor, which is responsible for loading the pages defined by the consumers according to the end-user's
 * setting (visibility & order). Through this class, consumer can get the run-time page and static page instance.
 * <p>
 * To define the run-time behaviour of the multi-page eidtor, the consumer need to extend
 * <code>DefaultSchemaObjectEditorHandler</code> and set it in the extension accordingly.
 * <p>
 * The editor input of this base editor is defined as <code>SchemeObjectEditorInput</code>, which contains a model
 * object, the consumer can use that field to store the domain model, for example the Table object.
 * <p>
 * Some methods defined in the parent classes are overrided to emplify the visibility since the consumer can not extend
 * and override this class.
 * 
 * @author Idull
 */
public class SchemaObjectEditor extends FormEditor implements ISchemaObjectEditor
{
    /**
     * The consumer can store application data in this field
     */
    public Object                      _data;
    private boolean                    _dirty                = false;
    /**
     * The edit model of this editor, this is always a "dirty" one compared with the orginal model
     */
    public Object                      _editModel;
    private IEditorDescriptor          _exEditor;
    private ISchemaObjectEditorHandler _handler;
    private ILogger                    _logger               = SOEUIPlugin.getLogger(null);

    private Map                        _nameToPages          = new HashMap();
    private Map                        _idToPages            = new HashMap();
    private List                       _ids                  = new ArrayList();
    private List                       _editorPages;
    private boolean                    _syncSave             = false;
    private boolean                    _needRefreshAfterSave = true;

    private ActivationListener         _activationListener   = null;
    // Edit Model may not be available when editor is closed, part listener will set this flag.
    private boolean                    _isEditModelAvailable = true;

    public SchemaObjectEditor()
    {
        _editorPages = new ArrayList();
    }

    protected void createPages()
    {
        super.createPages();

        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                final ISchemaObjectEditorPage page = getActiveEditorPage();

                manager.add(new Separator(Constants.REFRESH_REVERT_GROUP));
                IContributionItem item = ((IEditorPart) SchemaObjectEditor.this).getEditorSite().getActionBars()
                        .getToolBarManager().find(Constants.REVERT_EDITOR_ACTION_ID);
                if (item != null && item instanceof ActionContributionItem)
                {
                    manager.appendToGroup(Constants.REFRESH_REVERT_GROUP, ((ActionContributionItem) item).getAction());
                }

                item = ((IEditorPart) SchemaObjectEditor.this).getEditorSite().getActionBars().getToolBarManager()
                        .find(Constants.REFRESH_EDITOR_ACTION_ID);
                if (item != null && item instanceof ActionContributionItem)
                {
                    manager.appendToGroup(Constants.REFRESH_REVERT_GROUP, ((ActionContributionItem) item).getAction());
                }
                manager.add(new Separator(Constants.SAVE_PREFERENCE_GROUP));
                IAction prefAction = new Action()
                {
                    public void run()
                    {
                        String[] subPages = page.getPreferencePageIds();
                        int pageNum = subPages == null ? 0 : subPages.length;
                        String[] preferencePages = new String[1 + pageNum];
                        preferencePages[0] = Constants.PREFERENCE_PAGE_ID;
                        for (int i = 0; i < pageNum; i++)
                        {
                            preferencePages[i + 1] = subPages[i];
                        }

                        PreferencesUtil.createPreferenceDialogOn(null, preferencePages[0], preferencePages, null)
                                .open();
                    }
                };
                prefAction.setText(Messages.SchemaObjectEditor_preferences);
                manager.add(prefAction);

                IAction saveAction = new Action()
                {
                    public void run()
                    {
                        SOEUIPlugin.getActiveWorkbenchPage().saveEditor((IEditorPart) SchemaObjectEditor.this, false);
                    }
                };
                saveAction.setText(Messages.SchemaObjectEditor_save_to_server);
                saveAction.setImageDescriptor(Images.DESC_SAVE_TO_DB);
                saveAction.setEnabled(SchemaObjectEditor.this.isDirty());

                manager.appendToGroup(Constants.SAVE_PREFERENCE_GROUP, saveAction);

                if (page != null)
                {
                    page.menuAboutToShow(manager);
                }
            }
        });
        Menu menu = manager.createContextMenu(getContainer());
        getContainer().setMenu(menu);

        _activationListener = new ActivationListener(getEditorSite().getWorkbenchWindow().getPartService());

        // Open Problems view
        try
        {
            SOEUIPlugin.getActiveWorkbenchPage().showView(Constants.PROBLEMS_VIEW_ID, null, IWorkbenchPage.VIEW_VISIBLE);
        }
        catch (Exception e)
        {
            _logger.error("SchemaObjectEditorUtils_error_open_problemview", e); //$NON-NLS-1$
        }
    }

    protected void addPages()
    {
        if (_exEditor == null || _exEditor.getPageDescriptors() == null || _exEditor.getPageDescriptors().length == 0)
        {
            setupErrorPage(new ErrorPage(Messages.SchemaObjectEditor_no_page), _exEditor);
            return;
        }
        if (_exEditor.getHandler() == null)
        {
            setupErrorPage(new ErrorPage(Messages.SchemaObjectEditor_no_handler), _exEditor);
            return;
        }

        // set the editor
        _handler.setEditor(this);

        IEditorPageDescriptor[] pages = _exEditor.getSortedPages();
        for (int i = 0; i < pages.length; i++)
        {
            setupPage(pages[i], _exEditor);
        }
        _handler.hookInitialization();
    }

    public void clearDirty()
    {
        _dirty = false;
        Iterator pageIter = _editorPages.iterator();
        while (pageIter.hasNext())
        {
            Object obj = pageIter.next();
            if (obj instanceof ISchemaObjectEditorPage)
            {
                ISchemaObjectEditorPage page = (ISchemaObjectEditorPage) obj;
                if (page.getManagedForm() != null)
                {
                    page.getManagedForm().commit(true);
                }
            }
        }
        fireDirtyPropertyChange();
    }

    public void dispose()
    {
        for (int i = pages.size() - 1; i >= 0; i--)
        {
            Object page = pages.get(i);
            if (page instanceof NestedEditorPage)
            {
                disposePart(((NestedEditorPage) page).getNestedEditor());
                pages.remove(i);
            }
        }

        super.dispose();
        if (_handler != null)
        {
            _handler.dispose();
        }
        // cannot dispose the icon because it could be used by another editor instance
        // if (_exEditor.getIcon() != null)
        // {
        // _exEditor.getIcon().dispose();
        // }
    }

    private boolean aboutToSave(IProgressMonitor monitor)
    {
        boolean goon = true;
        for (Iterator iter = pages.iterator(); iter.hasNext();)
        {
            Object next = iter.next();
            if (next instanceof SchemaObjectEditorPage)
            {
                goon = ((SchemaObjectEditorPage) next).aboutToSave(monitor);
                if (!goon)
                {
                    break;
                }
            }
        }
        return goon;
    }

    public void doSave(IProgressMonitor monitor)
    {
        boolean goon = aboutToSave(monitor);
        if (goon)
        {
            if (_handler != null)
            {
                _handler.doSave(monitor);

                // By default, the object is saved asynchronizely
                _syncSave = false;
            }
        }
    }

    public void doSaveAs()
    {
        if (_handler != null)
        {
            _handler.doSaveAs();
        }
    }

    /**
     * A short-cut method
     */
    public void fireDirtyPropertyChange()
    {
        firePropertyChange(IEditorPart.PROP_DIRTY);
    }

    public void fireSchemaEditorProperChanged(final int propertyId)
    {
        super.firePropertyChange(propertyId);
    }

    /**
     * Returns the active page instance
     */
    public ISchemaObjectEditorPage getActiveEditorPage()
    {
        return (ISchemaObjectEditorPage) getActivePageInstance();
    }

    public Object getAdapter(Class adapter)
    {
        Object result = super.getAdapter(adapter);
        if (result != null && !IContentOutlinePage.class.equals(adapter))
        {
            return result;
        }
        if (_handler != null)
        {
            return _handler.getAdapter(adapter);
        }
        return null;
    }

    /**
     * Returns all pages loaded
     */
    public ISchemaObjectEditorPage[] getAllPages()
    {
        // Sometimes there is null page (?)
        ArrayList ps = new ArrayList();
        Iterator iter = _editorPages.iterator();
        while (iter.hasNext())
        {
            Object obj = iter.next();
            if (obj != null)
            {
                ps.add(obj);
            }
        }
        return (ISchemaObjectEditorPage[]) ps.toArray(new ISchemaObjectEditorPage[_editorPages.size()]);
    }

    public int getCurrentPageIndex()
    {
        return getCurrentPage();
    }

    public Object getData()
    {
        return _data;
    }

    public Object getEditModel()
    {
        return _editModel;
    }

    public Composite getEditorContainer()
    {
        return super.getContainer();
    }

    public ISchemaObjectEditorHandler getEditorHandler()
    {
        return _handler;
    }

    public Control getEditorPageControl(int pageIndex)
    {
        return super.getControl(pageIndex);
    }

    public int getEditorPageCount()
    {
        return super.getPageCount();
    }

    /**
     * Returns the page by id
     */
    public ISchemaObjectEditorPage getPageById(String id)
    {
        return (ISchemaObjectEditorPage) _idToPages.get(id);
    }

    /**
     * Returns the page by name
     */
    public ISchemaObjectEditorPage getPageByName(String name)
    {
        return (ISchemaObjectEditorPage) _nameToPages.get(name);
    }

    /**
     * Returns the static editor instance
     */
    public IEditorDescriptor getEditorDescriptor()
    {
        return _exEditor;
    }

    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
        super.init(site, input);
        if (input instanceof ISchemaObjectEditorInput)
        {
            ISchemaObjectEditorInput in = (ISchemaObjectEditorInput) input;
            _exEditor = in.getEditorDescriptor();
            _handler = in.getEditorDescriptor().getHandler();

            if (_exEditor != null && _exEditor.getEditorName() != null && _exEditor.getEditorName().length() != 0)
            {
                setPartName(_exEditor.getEditorName());
            }
            if (_exEditor != null && _exEditor.getIcon() != null)
            {
                setTitleImage(_exEditor.getIcon());
            }
        }
        else
        {
            _logger.error("SchemaObjectEditor_not_proper_iput"); //$NON-NLS-1$
            return;
        }
    }

    public boolean isDirty()
    {
        if (_dirty)
        {
            return _dirty;
        }
        return super.isDirty();
    }

    public boolean isSaveAsAllowed()
    {
        if (_handler != null)
        {
            return _handler.isSaveAsAllowed();
        }
        return false;
    }

    public void markDirty()
    {
        _dirty = true;
        fireDirtyPropertyChange();
    }

    /**
     * Delegate the call to consumer's code when the page is changed
     */
    protected void pageChange(int newPageIndex)
    {
        if (!_isEditModelAvailable || !_handler.checkSchemaObjectExistence(true))
        {
            return;
        }

        int oldPage = getCurrentPage();
        if (oldPage != -1 && _editorPages.size() > oldPage
                && _editorPages.get(oldPage) instanceof ISchemaObjectEditorPage && oldPage != newPageIndex)
        {
            // Check the old page
            ISchemaObjectEditorPage oldFormPage = (ISchemaObjectEditorPage) _editorPages.get(oldPage);
            // do not popup the error dialog here. let the page to handle it in canLeaveThePage()
            // if (oldFormPage.canLeaveThePage() == false)
            // {
            // String[] buttons = new String[]
            // {
            // IDialogConstants.OK_LABEL
            // }
            // ;
            // MessageDialog d = new MessageDialog(getSite().getShell(), Messages.SchemaObjectEditor_error, null,
            // oldFormPage.getErrorMsg() == null ? Messages.SchemaObjectEditor_pagechange_error :
            // oldFormPage.getErrorMsg(), MessageDialog.ERROR,
            // buttons, 0);
            // d.open();
            // setActivePage(oldPage);
            // return;
            // }
            if (oldFormPage instanceof NestedEditorPage)
            {
                ((NestedEditorPage) oldFormPage).aboutToLeave();
            }
        }

        // Do something when the page changed
        if (_handler != null)
        {
            _handler.pageChanged(newPageIndex);
        }
        super.pageChange(newPageIndex);
    }

    public void resouceChanged(IResourceChangeEvent event)
    {
        _handler.resouceChanged(event);
    }

    public void setData(Object _data)
    {
        this._data = _data;
    }

    public void setEditModel(Object model)
    {
        _editModel = model;
    }

    public void setEditorPageImage(int pageIndex, Image image)
    {
        super.setPageImage(pageIndex, image);
    }

    public void setEditorPageText(int pageIndex, String text)
    {
        super.setPageText(pageIndex, text);
    }

    public void setEditorPartName(final String name)
    {
        if (name != null && name.length() != 0)
        {
            getSite().getShell().getDisplay().asyncExec(new Runnable()
            {
                public void run()
                {
                    SchemaObjectEditor.super.setPartName(name);
                };
            });
        }
    }

    /**
     * Creates and adds a new page containing the given editor to this multi-page editor. The page is added at the given
     * index. This also hooks a property change listener on the nested editor.
     * 
     * @param index the index at which to add the page (0-based)
     * @param editor the nested editor
     * @param input the input for the nested editor
     * @exception PartInitException if a new page could not be created
     * 
     * @see MultiPageEditorPart#handlePropertyChange(int) the handler for property change events from the nested editor
     */
    public void addPage(int index, NestedEditorPage page) throws PartInitException
    {
        IEditorPart editor = page.getNestedEditor();
        IEditorInput input = page.getNestedEditorInput();
        IEditorSite site = createSite(editor);
        // call init first so that if an exception is thrown, we have created no
        // new widgets
        // The page is responsible for calling IEditorPart.init
        page.init(site, input);
        Composite parent2 = new Composite(getContainer(), getOrientation(editor));
        parent2.setLayout(new FillLayout());
        editor.createPartControl(parent2);
        page.setPartControl(parent2);
        editor.addPropertyListener(new IPropertyListener()
        {
            public void propertyChanged(Object source, int propertyId)
            {
                SchemaObjectEditor.this.handlePropertyChange(propertyId);
            }
        });
        // create item for page only after createPartControl has succeeded
        addPage(index, parent2);
        // remember the editor as data on the item's control
        parent2.setData(editor);

        configurePage(index, page);
    }

    protected IEditorSite createSite(IEditorPart editor)
    {
        if (editor.getEditorSite() != null)
        {
            return editor.getEditorSite();
        }
        else
        {
            return new MultiPageEditorSite(this, editor)
            {
                public String getId()
                {
                    return Constants.SCHEMA_EDITOR_NESTED_ID;
                }
            };
        }
    }

    /**
     * Get the orientation of the editor.
     * 
     * @param editor
     * @return int the orientation flag
     * @see SWT#RIGHT_TO_LEFT
     * @see SWT#LEFT_TO_RIGHT
     * @see SWT#NONE
     */
    private int getOrientation(IEditorPart editor)
    {
        if (editor instanceof IWorkbenchPartOrientation)
        {
            return ((IWorkbenchPartOrientation) editor).getOrientation();
        }
        return getOrientation();
    }

    /**
     * Sets up an error page to report the error message
     * 
     * @param page
     * @param editor
     */
    private void setupErrorPage(ISchemaObjectEditorPage page, IEditorDescriptor editor)
    {
        try
        {
            page.setId(Messages.SchemaObjectEditor_error_id);
            page.setEditor(this);
            page.setEditorDescriptor(_exEditor);
            page.setPartName(Messages.SchemaObjectEditor_error);
            addPage(page);
        }
        catch (Exception e)
        {
            _logger.error("SchemaObjectEditor_error_set_errorpage", e); //$NON-NLS-1$
        }
    }

    /**
     * Sets up a page defined by consumer
     * 
     * @param p
     * @param editor
     */
    private void setupPage(IEditorPageDescriptor p, IEditorDescriptor editor)
    {
        if (!p.isSelectedToShow())
        {
            return;
        }
        try
        {
            ISchemaObjectEditorPage page = (ISchemaObjectEditorPage) p.getPageClass();
            page.setId(p.getPageId());
            page.setEditor(this);
            page.setEditorDescriptor(_exEditor);
            page.setPageDescriptor(p);
            page.setPartName(p.getPageName());

            // The id or name should be unique
            _nameToPages.put(p.getPageName(), page);
            _idToPages.put(p.getPageId(), page);
            _ids.add(p.getPageId());

            // If the page is a nested editor, should add it as an editor
            if (page instanceof NestedEditorPage)
            {
                NestedEditorPage editorPage = (NestedEditorPage) page;
                addPage(getPageCount(), editorPage);
                // setPageText(getPageCount() - 1, editorPage.getTitle());
            }
            else
            {
                addPage(page);
            }
            _editorPages.add(page);
        }
        catch (Exception e)
        {
            _logger.error("SchemaObjectEditor_error_setup_page", e); //$NON-NLS-1$
        }
    }

    public Map validate()
    {
        Map pageErrors = new HashMap();
        ISchemaObjectEditorPage[] pages = getAllPages();
        for (int i = 0; i < pages.length; i++)
        {
            if (pages[i] != null)
            {
                IErrorItem[] items = pages[i].validateOnline(null);
                if (items != null && items.length != 0)
                {
                    pageErrors.put(pages[i], items);
                }
            }
        }
        return pageErrors;
    }

    /**
     * Tries to return a meaningful name
     */
    public String getDisplayName()
    {
        if (_handler != null && _handler.getDisplayName() != null
                && !_handler.getDisplayName().equals(Messages.SavePreviewDialog_noname_sql))
        {
            return _handler.getDisplayName();
        }
        String partName = getPartName();
        if (partName != null && !partName.equals("") && partName.indexOf('\\') < 0 && partName.indexOf('/') < 0 //$NON-NLS-1$
                && partName.indexOf(':') < 0 && partName.indexOf('*') < 0 && partName.indexOf('?') < 0
                && partName.indexOf('\"') < 0 && partName.indexOf('<') < 0 && partName.indexOf('>') < 0
                && partName.indexOf('|') < 0)
        {
            return partName;
        }
        return Messages.SavePreviewDialog_noname_sql;
    }

    public IFormPage setActivePage(String pageId)
    {
        IFormPage page = super.setActivePage(pageId);
        if (page != null)
        {
            return page;
        }

        int i = 0;
        Iterator idsIter = _ids.iterator();
        while (idsIter.hasNext())
        {
            String id = (String) idsIter.next();
            if (id.equals(pageId))
            {
                setActivePage(i);
                return (IFormPage) _idToPages.get(id);
            }
            i++;
        }

        return null;
    }

    /**
     * Widen the visibility
     */
    public void setActivePage(int pageIndex)
    {
        super.setActivePage(pageIndex);
    }

    /**
     * Disposes the given part and its site.
     * 
     * @param part The part to dispose; must not be <code>null</code>.
     */
    private void disposePart(final IWorkbenchPart part)
    {
        SafeRunner.run(new SafeRunnable()
        {
            public void run()
            {
                if (part.getSite() instanceof MultiPageEditorSite)
                {
                    MultiPageEditorSite partSite = (MultiPageEditorSite) part.getSite();
                    partSite.dispose();
                }
                part.dispose();
            }

            public void handleException(Throwable e)
            {
                // Exception has already being logged by Core. Do nothing.
            }
        });
    }

    /**
     * Returns the editor for the given page index. The page index must be valid.
     * 
     * @param pageIndex the index of the page
     * @return the editor for the specified page, or <code>null</code> if the specified page was not created with
     *         <code>addPage(IEditorPart,IEditorInput)</code>
     */
    protected IEditorPart getEditor(int pageIndex)
    {
        IEditorPart editor = super.getEditor(pageIndex);
        if (editor != null)
        {
            return editor;
        }
        Control control = getControl(pageIndex);
        if (control != null && control.getData() instanceof IEditorPart)
        {
            return (IEditorPart) control.getData();
        }

        return null;
    }

    public void setSyncSaveMode()
    {
        _syncSave = true;
    }

    public boolean isSyncSave()
    {
        return _syncSave;
    }

    public boolean needRefreshAfterSave()
    {
        return _needRefreshAfterSave;
    }

    public void setNeedRefreshAfterSave(boolean needRefresh)
    {
        _needRefreshAfterSave = needRefresh;
    }

    class ActivationListener implements IPartListener
    {
        IPartService part;

        public ActivationListener(IPartService part)
        {
            this.part = part;
            part.addPartListener(this);
        }

        public void partActivated(IWorkbenchPart part)
        {
            if (part == SchemaObjectEditor.this)
            {
                IPreferenceStore store = SOEUIPlugin.getDefault().getPreferenceStore();
                boolean isNeedCheck = store
                        .getBoolean(org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.Constants.PREFERENCE_CHECK_EXISTENCE);
                if (!_handler.checkSchemaObjectExistence(isNeedCheck))
                {
                    SchemaObjectEditor.this._isEditModelAvailable = false;
                    this.part.removePartListener(this);
                }
            }

        }

        public void partBroughtToTop(IWorkbenchPart part)
        {
        }

        public void partClosed(IWorkbenchPart part)
        {
            if (part == SchemaObjectEditor.this)
            {
                this.part.removePartListener(this);
            }
        }

        public void partDeactivated(IWorkbenchPart part)
        {
        }

        public void partOpened(IWorkbenchPart part)
        {
        }
    }

}
