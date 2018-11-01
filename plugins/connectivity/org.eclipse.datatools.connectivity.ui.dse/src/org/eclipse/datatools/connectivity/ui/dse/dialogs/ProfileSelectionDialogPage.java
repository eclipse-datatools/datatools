/*******************************************************************************
 * Copyright (c) 2005-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.dse.dialogs;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.ProfileConnectionManager;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.ui.actions.AddProfileViewAction;
import org.eclipse.datatools.connectivity.ui.actions.ConnectAction;
import org.eclipse.datatools.connectivity.ui.actions.DisconnectAction;
import org.eclipse.datatools.connectivity.ui.dse.DSEPlugin;
import org.eclipse.datatools.connectivity.ui.dse.dialogs.filters.DownToProfilesOnlyFilter;
import org.eclipse.datatools.connectivity.ui.dse.dialogs.filters.ProfileCategoryFilter;
import org.eclipse.datatools.connectivity.ui.navigator.ConnectionProfileContentProvider;
import org.eclipse.datatools.connectivity.ui.navigator.ConnectionProfileSorter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.actions.SelectionProviderAction;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorContentExtension;
import org.eclipse.ui.navigator.INavigatorContentService;

/**
 * Dialog page that can be hosted in a variety of places to
 * show the DSE view.
 * 
 * @author rcernich, brianf
 */
public class ProfileSelectionDialogPage extends
	DialogPage implements IPropertySetListener {

	// holds the view to a minimum size
	private static final int MIN_VIEWER_ELEMENTS = 4;

	// title and icon for area
	public static final String TITLE = null;
	public static final ImageDescriptor ICON_DESC = null;
	
	// initial object to be selected
    protected Object mInitialSelection;
	private ICategory mCategory;
    
    // profile not selected (profile must be selected to be valid)
    protected String mMissingEndpointReferenceString;

    // viewer
	private CommonViewer mViewer;
	private ViewerFilter mFilter;
	
    // Connect and other actions
    private ActionGroup mConnectActions;
	private Button mConnect;
	private Button mNew;
	private Button mSelectAll;
	private Button mDeselectAll;

	// various settings
	private boolean mShowNew = true;
	private boolean mShowConnect = true;
	private boolean mShowSelectButtons = true;
	private boolean mLimitToProfiles = true;
	private boolean inConnect = false;
	private boolean doneConnect = false;
	private boolean failedConnection = false;

	/**
	 * @param title
	 * @param image
	 * @param missingEndpointReferenceString
	 */
	public ProfileSelectionDialogPage() {
		this(TITLE, ICON_DESC);
	}
	
	public ProfileSelectionDialogPage( String title ) {
		this( title, ICON_DESC);
	}

	/**
	 * @param title
	 * @param image
	 */
	public ProfileSelectionDialogPage(String title, ImageDescriptor image) {
		super(title, image);
		mMissingEndpointReferenceString = 
			DSEPlugin.getDefault().getResourceString("ProfileSelectionDialogPage.validation.error"); //$NON-NLS-1$
	}

	/**
	 * @param selection
	 * @param endpointType
	 * @param filter
	 * @param category
	 */
	public void init(Object selection, Class endpointType, ViewerFilter filter,
			ICategory category) {
		mInitialSelection = selection;
		mFilter = filter;
		mCategory = category;
		initControls();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout(2, false));
		content.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        doCreateControl(content);
        
		initControls();

		setControl(content);
	}

	/**
	 * @param content
	 */
	protected void doCreateControl(Composite content) {
		doCreateControl(content, false);
	}
	
	/**
	 * @param content
	 * @param flag
	 */
	protected void doCreateControl(Composite content, boolean flag) {
		doCreateControl(content, flag, true);
	}
	
	/**
	 * @param content
	 * @param flag
	 * @param multiSelect
	 */
	protected void doCreateControl(Composite content, boolean flag, boolean multiSelect) {
		INavigatorContentService navigatorService = null;
		
		int styles = SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER ;
		if (multiSelect)
			styles = SWT.MULTI | styles;
		else
			styles = SWT.SINGLE | styles;
		CommonViewer mCommonViewer = new CommonViewer(DSEPlugin.SERVERS_VIEW_VIEWER_ID, content, styles);			
		mViewer = mCommonViewer;
		navigatorService = mCommonViewer.getNavigatorContentService();			
		
		// Turn off categories.
		if (mLimitToProfiles) {
	        Set rootExtensions = navigatorService
					.findRootContentExtensions(ProfileManager.getInstance());
			for (Iterator iterator = rootExtensions.iterator(); iterator.hasNext();) {
				INavigatorContentExtension extension = (INavigatorContentExtension) iterator
						.next();
				ITreeContentProvider provider = extension.getContentProvider();
				if (extension.getContentProvider() instanceof ConnectionProfileContentProvider) {
					((ConnectionProfileContentProvider) provider)
							.setShowCategories(false);
				}
			}
		}
		
		Object input = ProfileManager.getInstance();

		final TreeViewer 	tViewer = mViewer;
		final Object			mInput = input;
        Display         		display = DSEPlugin.getDefault().getWorkbench().getDisplay();
        display.syncExec(new Runnable() {
            public void run() {
            	if (mInput != null) {
	            	tViewer.setInput(mInput);
            	}
            }
        });
        
        mConnectActions = new ConnectActions();
        hookContextMenu(mConnectActions);
        
		if (mFilter != null) {
			mViewer.addFilter(mFilter);
		}
		
        //		mViewer.setSorter(new SimpleViewerSorter());		
		GridData data = new GridData(GridData.FILL_BOTH);
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.horizontalSpan = 1;
		data.verticalSpan = MIN_VIEWER_ELEMENTS;
		data.heightHint = mViewer.getTree().getItemHeight()
				* MIN_VIEWER_ELEMENTS;
		mViewer.getControl().setLayoutData(data);

		if (this.mShowConnect) {
			mConnect = new Button(content, SWT.PUSH);
			mConnect
					.setText(DSEPlugin.getDefault().getResourceString("DSE.Connect.label")); //$NON-NLS-1$
			GridData gridData = new GridData();
			gridData.horizontalSpan = 1;
			gridData.horizontalAlignment = SWT.FILL;
			gridData.verticalAlignment = SWT.TOP;
			mConnect.setLayoutData(gridData);
			mConnect.setEnabled(false);
			mConnect.addSelectionListener(new SelectionAdapter() {
	
				public void widgetSelected(SelectionEvent event) {
					failedConnection = false;
					handleConnect();
				}
			});
		}
		if (this.mShowNew) {
			mNew = new Button(content, SWT.PUSH);
			
			mNew.setText(DSEPlugin.getDefault().getResourceString("ServersView.action.newCP")); //$NON-NLS-1$
			
			GridData gridData = new GridData();
			gridData.horizontalSpan = 1;
			gridData.horizontalAlignment = SWT.FILL;
			gridData.verticalAlignment = SWT.TOP;
			mNew.setLayoutData(gridData);
			mNew.addSelectionListener(new SelectionAdapter() {
	
				public void widgetSelected(SelectionEvent event) {
					handleNew();
				}
			});
		}
		if ( this.mShowSelectButtons ){
			mSelectAll = new Button(content, SWT.PUSH);
			mSelectAll.setText(DSEPlugin.getDefault().getResourceString("DSE.SelectAll.label")); //$NON-NLS-1$
			GridData gridData = new GridData();
			gridData.horizontalSpan = 1;
			gridData.horizontalAlignment = SWT.FILL;
			gridData.verticalAlignment = SWT.TOP;
			mSelectAll.setLayoutData(gridData);
			mSelectAll.addSelectionListener(new SelectionAdapter() {
	
				public void widgetSelected(SelectionEvent event) {
					selectAll();
				}
			});

			mDeselectAll = new Button(content, SWT.PUSH);
			mDeselectAll.setText(DSEPlugin.getDefault().getResourceString("DSE.DeselectAll.label")); //$NON-NLS-1$
			gridData = new GridData();
			gridData.horizontalSpan = 1;
			gridData.horizontalAlignment = SWT.FILL;
			gridData.verticalAlignment = SWT.TOP;
			mDeselectAll.setLayoutData(gridData);
			mDeselectAll.addSelectionListener(new SelectionAdapter() {
	
				public void widgetSelected(SelectionEvent event) {
					unselectAll();
				}
			});
		}
		
		mViewer.setSorter(new ConnectionProfileSorter());
	}

	/**
	 * set up the controls... 
	 */
	protected void initControls() {
		if (mViewer == null) {
			return;
		}
		if (mInitialSelection != null) {
			mViewer.setSelection(new StructuredSelection(mInitialSelection));
			mViewer.getTree().showSelection();
			if (mViewer.getTree().getItemCount() == 1 && this.mShowConnect) {
				handleConnect();
			}
		}
		else {
			validate();
		}

		setErrorMessage(null);

		boolean hasFilter = false;
		if (this.mFilter != null) {
			hasFilter = true;
			((StructuredViewer)this.mViewer).removeFilter(this.mFilter);
			((StructuredViewer)this.mViewer).addFilter(this.mFilter);
		}
		
		if (this.mCategory != null) {
			hasFilter = true;
			ProfileCategoryFilter categoryFilter = new ProfileCategoryFilter();
			categoryFilter.setSelectedCategory(this.mCategory.getId());
			mViewer.addFilter(categoryFilter);
		}

		if (mLimitToProfiles) {
			hasFilter = true;
			DownToProfilesOnlyFilter mProfileFilter =
				new DownToProfilesOnlyFilter();
			mViewer.addFilter(mProfileFilter);
		}
		
		if (hasFilter)
			mViewer.refresh();
		
		
	}

	/**
	 * validate the selection
	 */
	public void validate() {
		IStructuredSelection selection = (IStructuredSelection) mViewer
				.getSelection();
		String error = null;
		if (selection == null || selection.isEmpty()) {
			error = mMissingEndpointReferenceString;
			if (mConnect != null)
				mConnect.setEnabled(false);
		}
		else {
			Object obj = selection.getFirstElement();
			boolean isProfile = obj instanceof IConnectionProfile;
			boolean isProfileDisConnected = isProfile && ((IConnectionProfile) obj).getConnectionState() == IConnectionProfile.DISCONNECTED_STATE;

			if (isProfileDisConnected) {
				if (mConnect != null)
					mConnect.setEnabled(true);
				if (mSelectAll != null)
					mSelectAll.setEnabled(false);
			}
			else {
				if (mConnect != null)
					mConnect.setEnabled(false);
				if (mSelectAll != null)
					mSelectAll.setEnabled(true);
			}
			if (obj instanceof IConnectionProfile) {
				((IConnectionProfile) obj).removePropertySetListener(this);
				((IConnectionProfile) obj).addPropertySetListener(this);
			}
		}

		setErrorMessage(error);
//		setIsValid(error == null);
	}

	/**
	 * @return
	 */
	public TreeViewer getNavigatorViewer() {
		return mViewer;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#dispose()
	 */
	public void dispose() {
		// Make sure we remove ourself from the listener list to prevent widget
		// disposed errors.
		// It would be nice if we kept track of who we registered with, but
		// I'm not sure if it's worth the effort. Use brute force in its stead.
		for (Iterator it = Arrays.asList(
				ProfileManager.getInstance().getProfiles()).iterator(); it
				.hasNext();) {
			((IConnectionProfile) it.next()).removePropertySetListener(this);
		}
		super.dispose();
	}

	/**
	 * Connect
	 */
	public void handleConnect() {

		inConnect = true;
		doneConnect = false;
		if (failedConnection)
			return;
		
		final IStructuredSelection selection = (IStructuredSelection) mViewer
				.getSelection();

		final IConnectionProfile profile = (IConnectionProfile) selection.getFirstElement();
		
		if (profile != null ) {
			
			// Connect to the connection profile
			Display.getCurrent().readAndDispatch();
			IConnectionFactoryProvider factoryProvider = profile.getProvider().getConnectionFactory("java.sql.Connection");
			if(factoryProvider !=null){
				ProfileConnectionManager.getProfileConnectionManagerInstance().manageProfileConnection(profile, "org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo", this); //$NON-NLS-1$
			}
			if (profile.getConnectionState() != IConnectionProfile.CONNECTED_STATE) {
				
				try {
					IRunnableWithProgress op = new IRunnableWithProgress(){

						public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
							IStatus status = profile.connect();
							switch (status.getSeverity()) {
								case IStatus.OK:
								case IStatus.INFO:
								case IStatus.WARNING:
									break;
								case IStatus.ERROR:
									failedConnection = true;
									profile.disconnect();
									break;
							}
							if (Display.getCurrent() != null) {
								Display.getCurrent().readAndDispatch();
								Display.getCurrent().syncExec(new Runnable() {
	
									public void run() {
										mConnect.setEnabled(((IConnectionProfile) selection.getFirstElement())
												.getConnectionState() != IConnectionProfile.CONNECTED_STATE);
										inConnect = false;
										doneConnect = true;
					        			if (mViewer != null && !mViewer.getTree().isDisposed())
					        				mViewer.getTree().setFocus();
									}
								});
							}
						}};
				   new ProgressMonitorDialog(getControl().getShell()).run(true, false, op);
				} catch (InvocationTargetException e) {
				   // handle exception
				} catch (InterruptedException e) {
					failedConnection = true;
				   profile.disconnect();
				} finally {
				}
			}
		}
	}

	/**
	 * Create new profile
	 */
	public void handleNew() {
		final IStructuredSelection selection = (IStructuredSelection) mViewer
				.getSelection();

		// Create a new connection profile
		AddProfileViewAction newAction = new AddProfileViewAction(mCategory);
		newAction.selectionChanged(null, selection);
		if (this.mCategory != null)
			newAction.setCategory(this.mCategory);
		newAction.run(null);

		mViewer.refresh();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IPropertySetListener#propertySetChanged(org.eclipse.datatools.connectivity.IPropertySetChangeEvent)
	 */
	public void propertySetChanged(IPropertySetChangeEvent event) {
		if (event.getChangedProperty(IConnectionProfile.CONNECTION_STATE_PROPERTY_ID) != null) {
		    final IConnectionProfile profile = (IConnectionProfile) event.getConnectionProfile(); 
		    Runnable torun = new Runnable() {
                public void run() {
        			if (profile != null && ((IConnectionProfile) profile).getConnectionState() != IConnectionProfile.CONNECTED_STATE) {
        				if (mConnect != null && !mConnect.isDisposed() && !mConnect.isEnabled()) {
        					mConnect.setEnabled(true);
        				}
        			}
        			else {
        				if (mConnect != null &&  !mConnect.isDisposed() && mConnect.isEnabled()) {
        					mConnect.setEnabled(false);
        				}
        			}
                }		        
		    };
		    Display.getDefault().asyncExec(torun);
		}
	}
	
	/**
	 * @param flag
	 */
	public void setShowNew ( boolean flag ) {
		this.mShowNew = flag;
	}
	/**
	 * @return
	 */
	public boolean getShowNew () {
		return this.mShowNew;
	}
	/**
	 * @param flag
	 */
	public void setShowConnect ( boolean flag ) {
		this.mShowConnect = flag;
	}
	/**
	 * @return
	 */
	public boolean getShowConnect() {
		return this.mShowConnect;
	}
	/**
	 * @param flag
	 */
	public void setShowSelectButtons ( boolean flag ) {
		this.mShowSelectButtons = flag;
	}
	/**
	 * @return
	 */
	public boolean getShowSelectButtons() {
		return this.mShowSelectButtons;
	}
	/**
	 * @param flag
	 */
	public void setLimitToProfiles ( boolean flag ) {
		this.mLimitToProfiles = flag;
	}
	/**
	 * @return
	 */
	public boolean getLimitToProfiles() {
		return this.mLimitToProfiles;
	}
	/**
	 * @return
	 */
	public boolean getInConnect() {
		return this.inConnect;
	}
	/**
	 * @return
	 */
	public boolean getDoneConnect() {
		return this.doneConnect;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent, boolean flag) {
		createControl(parent, flag, true);
	}
	
	/**
	 * @param parent
	 * @param flag
	 * @param multiSelect
	 */
	public void createControl(Composite parent, boolean flag, boolean multiSelect) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout(2, false));
		content.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        doCreateControl(content, flag, multiSelect);
        
		setControl(content);

		initControls();

	}

	/**
	 * @param object
	 */
	public void setInput (Object object ) {
		if (this.getNavigatorViewer() != null)
			this.getNavigatorViewer().setInput(object);
	}
	
	/**
	 * @param profile
	 */
	public void select(IConnectionProfile profile) {
		if ( profile != null ) {
			this.mViewer.setSelection(new StructuredSelection(profile), true);
		}
	}

	/**
	 * Select all profiles
	 */
	public void selectAll(){
		TreeViewer viewer = (TreeViewer) this.getNavigatorViewer();
		viewer.getTree().selectAll();
		validate();
	}
	
	/**
	 * Deselect all profiles
	 */
	public void unselectAll(){
		TreeViewer viewer = (TreeViewer) this.getNavigatorViewer();
		viewer.getTree().deselectAll();
	}
	
	/**
	 * @param str_list
	 * @param token
	 * @return
	 */
	public static String[] parseString(String str_list, String token) {
		StringTokenizer tk = new StringTokenizer(str_list, token);
		String[] pieces = new String[tk.countTokens()];
		int index = 0;
		while (tk.hasMoreTokens())
			pieces[index++] = tk.nextToken();
		return pieces;
	}

	/**
	 * @return
	 */
	public IConnectionProfile[] getSelectedProfiles() {
		return new IConnectionProfile[0];
	}

	/**
	 * @param selectAllOnStartup
	 * @return
	 */
	public IConnectionProfile[] getSelectedProfiles(boolean selectAllOnStartup) {
		return new IConnectionProfile[0];
	}
	
	/**
	 * @return
	 */
	public boolean canConnect() {
		if (this.mConnect != null) {
			return this.mConnect.isEnabled();
		}
		return false;
	}
	
	/**
	 * @return
	 */
	public boolean canNew() {
		if (this.mNew != null) {
			return this.mNew.isEnabled();
		}
		return false;
	}
	
	/**
	 * @param flag
	 */
	public void setEnabled( boolean flag ) {
		if (getNavigatorViewer() != null) {
			getNavigatorViewer().getTree().setEnabled(flag);
			if (flag) {
				getNavigatorViewer().getTree().setBackground(getNavigatorViewer().getTree().getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
			}
			else {
				getNavigatorViewer().getTree().setBackground(getNavigatorViewer().getTree().getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.sybase.stf.common.ui.dialogs.IDialogPage#performOk()
	 */
	public boolean performOk() {
		return true;
	}

    /**
     * Set context menu
     * @param actionGroup
     */
    protected void hookContextMenu(ActionGroup actionGroup) {
        MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {

            public void menuAboutToShow(IMenuManager mgr) {
                IStructuredSelection selection = (IStructuredSelection) mViewer
                        .getSelection();

                // force selection changed event generation
                // to update the enablement of menu items
                mViewer.setSelection(selection);

                ActionContext context = new ActionContext(selection);

                mConnectActions.setContext(context);
                mConnectActions.fillContextMenu(mgr);
            }
        });

        Menu menu = menuMgr.createContextMenu(mViewer.getControl());

        mViewer.getControl().setMenu(menu);
    }

    /**
     * @author brianf
     *
     */
    class ConnectActions extends ActionGroup {
    	
        IAction connectAction = new InternalConnectAction(mViewer);

        IAction disconnectAction = new InternalDisconnectAction(mViewer);

        /**
         * @author brianf
         *
         */
        class InternalConnectAction extends SelectionProviderAction {

            IConnectionProfile connectionProfile;

            /**
             * @param provider
             */
            public InternalConnectAction(ISelectionProvider provider) {
                super(provider, DSEPlugin.getDefault().getResourceString("DSE.Connect.label")); //$NON-NLS-1$
            }

            /**
             * @see org.eclipse.jface.action.IAction#run()
             */
            public void run() {
            	failedConnection = false;
            	ConnectAction connectAction = new ConnectAction(mViewer.getTree().getShell());
            	connectAction.selectionChanged(this, this.getSelection());
            	connectAction.run(this);
            }

            /**
             * @see org.eclipse.ui.actions.SelectionProviderAction#selectionChanged(IStructuredSelection)
             */
            public void selectionChanged(IStructuredSelection selection) {
                if (!selection.isEmpty()) {
                    Object obj = selection.getFirstElement();

                    if (obj instanceof IConnectionProfile) {
                        connectionProfile = (IConnectionProfile) obj;
                        setEnabled(connectionProfile.getConnectionState() != IConnectionProfile.CONNECTED_STATE);
                    }
                }
            }
        }

        /**
         * @author brianf
         *
         */
        class InternalDisconnectAction extends SelectionProviderAction {

            IConnectionProfile connectionProfile;

            /**
             * @param provider
             */
            public InternalDisconnectAction(ISelectionProvider provider) {
                super(provider, DSEPlugin.getDefault().getResourceString("DSE.Disconnect.label")); //$NON-NLS-1$
            }

            /**
             * @see org.eclipse.jface.action.IAction#run()
             */
            public void run() {
            	DisconnectAction disconnectAction = new DisconnectAction();
            	disconnectAction.selectionChanged(this, this.getSelection());
            	disconnectAction.run(this);
            }

            /**
             * @see org.eclipse.ui.actions.SelectionProviderAction#selectionChanged(IStructuredSelection)
             */
            public void selectionChanged(IStructuredSelection selection) {
                if (!selection.isEmpty()) {
                    Object obj = selection.getFirstElement();

                    if (obj instanceof IConnectionProfile) {
                        connectionProfile = (IConnectionProfile) obj;
                        setEnabled(connectionProfile.getConnectionState() != IConnectionProfile.DISCONNECTED_STATE);
                    }
                }
            }
        }

        /* (non-Javadoc)
         * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
         */
        public void fillContextMenu(IMenuManager menu) {
            ActionContext context = getContext();

            IStructuredSelection selection = (IStructuredSelection) context
                    .getSelection();

            if (!selection.isEmpty()) {
                Object obj = selection.getFirstElement();

                if (obj instanceof IConnectionProfile) {
                	if (mShowConnect) {
                		menu.add(connectAction);
                		menu.add(disconnectAction);
                	}
                }
            }
        }
     }
}
