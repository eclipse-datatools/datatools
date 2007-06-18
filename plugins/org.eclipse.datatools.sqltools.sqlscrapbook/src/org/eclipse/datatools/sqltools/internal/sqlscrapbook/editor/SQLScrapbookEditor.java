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

import java.lang.reflect.Method;
import java.net.URI;
import java.util.ResourceBundle;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.externalfile.ExternalSQLFileEditorInput;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions.SetConnectionInfoAction;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.AbstractConnectionInfoComposite;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.ConnectionInfoComposite2;
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
import org.eclipse.jface.window.Window;
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
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.ILocationProvider;

public class SQLScrapbookEditor extends SQLEditor {

	public static final String EDITOR_ID = EditorConstants.SQLFILE_EDITOR_ID;
	
    public class ToolbarSourceViewer extends AdaptedSourceViewer implements Listener
    {
        private AbstractConnectionInfoComposite connBar;
        private boolean initialized = false;
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

            //since SQL Editor is always left to right, we need to tell the composite explicitly about the orientation
            connBar = new ConnectionInfoComposite2(fDefaultComposite, Window.getDefaultOrientation(), this, SQLScrapbookEditor.this
                    .getConnectionInfo(), null, AbstractConnectionInfoComposite.STYLE_SEPARATE_TYPE_NAME
                    | AbstractConnectionInfoComposite.STYLE_SHOW_STATUS | AbstractConnectionInfoComposite.STYLE_SINGLE_GROUP | AbstractConnectionInfoComposite.STYLE_LAZY_INIT);
            connBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            ((GridLayout)connBar.getLayout()).marginWidth = 12;
            
            
            Composite textComposite= new Composite(fDefaultComposite, SWT.NONE);
            textComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            textComposite.setLayout(new FillLayout());
            super.createControl(textComposite, styles);
            
        }

        public void handleEvent(Event event)
        {
            String content = getDocument().get();
            doSetConnectionInfo(connBar.getConnectionInfo());
            getDocument().set(content);
        }
        
        public void refreshConnectionStatus()
        {
            if (connBar != null)
            {
                if (!initialized)
                {
                    connBar.init();
                    initialized = true;
                }
                connBar.refreshConnectionStatus();
            }
        }
    }
    
	private IAction _setConnectionInfoAction;
	
	public SQLScrapbookEditor() {
		super();
	}

	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
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
		} else if (input instanceof IAdaptable) {
			URI uri = (URI)((IAdaptable)input).getAdapter(URI.class);
			if (uri == null)
			{
				//TO run DTP both on Eclipse 3.2 and 3.3, we have to use java reflection to get the URI info from Eclipse 3.3 object FileStoreEditorInput
				Class clazz = input.getClass();
				try {
					Method getURI = clazz.getMethod("getURI", null);
					uri = (URI)getURI.invoke(input, null);
				} catch (Exception e) {
				}
			}
			if (uri != null)
			{
				IFileStore store = EFS.getStore(uri);
				newInput = new ExternalSQLFileEditorInput(store);
			}
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
		this.doSetConnectionInfo(connInfo);
        //updates the internal toolbar when user changes the connection info
        getSite().getShell().getDisplay().asyncExec(new Runnable(){
            public void run()
            {
                ((ToolbarSourceViewer) getSV()).connBar.init(
                        getConnectionInfo().getDatabaseVendorDefinitionId().toString(), getConnectionInfo()
                        .getConnectionProfileName(), getConnectionInfo().getDatabaseName());
            }
        });
	}	

	protected void doSetConnectionInfo(ISQLEditorConnectionInfo connInfo) {
	    super.setConnectionInfo(connInfo);
	    if (getEditorInput() instanceof IFileEditorInput)
	    {
	        SQLFileUtil.setEncodedConnectionInfo(((IFileEditorInput)getEditorInput()).getFile(), connInfo.encode());
	    }
	    // refresh title tooltip
	    getSite().getShell().getDisplay().asyncExec(new Runnable(){
            public void run()
            {
                setTitleToolTip(getTitleToolTip());
            }
        });
	    
	}	
	
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
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
    
    public void refreshConnectionStatus()
    {
        super.refreshConnectionStatus();
        if (getSV() != null)
        {
            ((ToolbarSourceViewer)getSV()).refreshConnectionStatus();
        }
    }

}
