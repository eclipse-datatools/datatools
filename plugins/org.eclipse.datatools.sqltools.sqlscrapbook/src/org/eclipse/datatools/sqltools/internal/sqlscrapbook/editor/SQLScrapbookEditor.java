/*******************************************************************************
 * Copyright (c) 2005 Exadel Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor;

import java.util.ResourceBundle;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.externalfile.ExternalSQLFileEditorInput;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions.SetConnectionInfoAction;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.ConnectionInfoComposite;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorageEditorInput;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.ILocationProvider;

public class SQLScrapbookEditor extends SQLEditor {

	public static final String EDITOR_ID = EditorConstants.SQLFILE_EDITOR_ID;
	
    public class ToolbarSourceViewer extends AdaptedSourceViewer implements Listener
    {
        private ConnectionInfoComposite connBar;
        public ToolbarSourceViewer(Composite parent, IVerticalRuler verticalRuler,
                IOverviewRuler overviewRuler, boolean showAnnotationsOverview, int styles)
        {
            super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles);
        }
        
        protected void createControl(Composite parent, int styles)
        {
            Composite fDefaultComposite= new Composite(parent, SWT.NONE);
            GridLayout gridLayout = new GridLayout();
            gridLayout.numColumns = 1;
            gridLayout.horizontalSpacing = 0;
            gridLayout.marginWidth = 0;
            gridLayout.marginBottom = 0;
            
            fDefaultComposite.setLayout(gridLayout);
            fDefaultComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

            connBar = new ConnectionInfoComposite(fDefaultComposite, this, SQLScrapbookEditor.this.getConnectionInfo(), null, false, false, null, false);
            connBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            
            Composite textComposite= new Composite(fDefaultComposite, SWT.NONE);
            textComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            textComposite.setLayout(new FillLayout());
            super.createControl(textComposite, styles);
            
        }

        public void handleEvent(Event event)
        {
            String content = getDocument().get();
            setConnectionInfo(connBar.getConnectionInfo());
            getDocument().set(content);
        }
    }
    
	private class ProfileStatusChecker implements IPartListener2
	{
	    public void partActivated(IWorkbenchPartReference partRef) {
			switch (getConnectionInfo().getProfileStatus()) {
			case EditorConstants.CP_STATUS_DELETED:
			case EditorConstants.CP_STATUS_PROP_CHANGED:
				if (isDirty()) {
					_setConnectionInfoAction.run();
				} else {
					close(false);
				}
				break;
			default:
				break;
			}
		}

	    public void partBroughtToTop(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partClosed(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partDeactivated(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partOpened(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partHidden(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partVisible(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partInputChanged(IWorkbenchPartReference partRef)
	    {
	    }

	}
	protected ProfileStatusChecker                              _profileChecker                   = new ProfileStatusChecker();
	private IAction _setConnectionInfoAction;
	
	public SQLScrapbookEditor() {
		super();
	}

	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		getSite().getPage().addPartListener(_profileChecker);
	}
	
	protected void initializeEditor() {
		super.initializeEditor();
        setDocumentProvider(SqlscrapbookPlugin.getDefault().getSQLEditorDocumentProvider());
	}

	public void doSetInput(IEditorInput input) throws CoreException {
		ISQLEditorInput newInput = null;
		if (input instanceof SQLScrapbookEditorInput) {
            newInput = (SQLScrapbookEditorInput) input;
        } else if (input instanceof IFileEditorInput) {
			newInput = new SQLScrapbookEditorInput(((IFileEditorInput) input).getFile());
        } else if (input instanceof IPathEditorInput) {
        	IFileStore store = EFS.getLocalFileSystem().getStore(((IPathEditorInput) input).getPath());
        	newInput = new ExternalSQLFileEditorInput(store);
        } else if (input instanceof ILocationProvider) {
        	IFileStore store = EFS.getLocalFileSystem().getStore(((ILocationProvider) input).getPath(input));
        	newInput = new ExternalSQLFileEditorInput(store);
        }
		
		if (newInput == null){
			super.doSetInput(input);			
		} else {
			if (input instanceof SQLScrapbookEditorInput) {
				((SQLScrapbookEditorInput)newInput).setEditorSite(this.getEditorSite());
			}
			super.doSetInput(newInput);			
		}

	}

	protected void createActions() {
		super.createActions();
        ResourceBundle bundle = Messages.getBundleForConstructedKeys();

        _setConnectionInfoAction = new SetConnectionInfoAction(bundle,
						"SetConnectionInfo.", this);
		setAction( ISQLEditorActionConstants.ATTACHE_PROFILE_ACTION_ID, _setConnectionInfoAction ); //$NON-NLS-1$

	}

	protected void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
        menu.add( new Separator() );
        addAction( menu, ISQLEditorActionConstants.ATTACHE_PROFILE_ACTION_ID); //$NON-NLS-1$
	}

    public void requestConnection()
    {
    	_setConnectionInfoAction.run();
    }
    
	public void setConnectionInfo(ISQLEditorConnectionInfo connInfo) {
		super.setConnectionInfo(connInfo);
		if (getEditorInput() instanceof IFileEditorInput)
		{
			SQLFileUtil.setEncodedConnectionInfo(((IFileEditorInput)getEditorInput()).getFile(), connInfo.encode());
		}
		// refresh title tooltip
        setTitleToolTip(getTitleToolTip());		
	}	

	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		getSite().getPage().removePartListener(_profileChecker);
	}	
	
    public void doSave(IProgressMonitor monitor)
    {
        if (getEditorInput() instanceof SQLEditorStorageEditorInput)
        {
            doSaveAs();
        }
        else
        {
            super.doSave(monitor);
        }
    }
    
    public void createPartControl(Composite parent)
    {
        super.createPartControl(parent);
    }
    
    protected AdaptedSourceViewer doCreateSourceViewer(Composite parent, IVerticalRuler ruler, int styles)
    {
        return new ToolbarSourceViewer(parent, ruler, getOverviewRuler(), isOverviewRulerVisible(),
                styles);
    }
}
