/*******************************************************************************
 * Copyright (c) 2005-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: 
 *  shongxum - initial API and implementation
 *  Actuate Corporation - refactored to improve extensibility
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IProfileListener;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.Category;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.LocalRepositoryNode;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPCategoryWizardNode;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardNode;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardSelectionPage;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizard;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizardCategoryFilter;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCategoryWizard;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ProfileWizardProvider;
import org.eclipse.datatools.connectivity.ui.wizards.ICPWizard;
import org.eclipse.datatools.connectivity.ui.wizards.IWizardCategoryProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Ideally, this class should be split into two, one is for Action, the other
 * is for View Action.
 * 
 * @author shongxum, brianf
 */
public class AddProfileViewAction extends Action 
	implements IHandler, IViewActionDelegate {

	protected String categoryID;
	private IConnectionProfile parentProfile;
	private int returnCode;
	private IConnectionProfile addedProfile;
	private Shell shell;
	private boolean ignoreCategory = false;
	private boolean useSelection = true;
	
	private static String PROP_IGNORE_CATEGORY = "org.eclipse.datatools.connectivity.ui.ignoreCategory";//$NON-NLS-1$
	private static String PROP_USE_SELECTION = "org.eclipse.datatools.connectivity.ui.useSelection";//$NON-NLS-1$
	
	/**
	 * A collection of objects listening to changes to this manager. This
	 * collection is <code>null</code> if there are no listeners.
	 */
	private transient ListenerList listenerList = null;

	/**
	 * Constructor
	 */
	public AddProfileViewAction() {
		super();
		setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ServersView.action.newCP")); //$NON-NLS-1$
	}

	/**
	 * @param category
	 */
	public AddProfileViewAction(ICategory category) {
		this(category.getId());
	}
	
	/**
	 * @param categoryID
	 */
	public AddProfileViewAction ( String categoryID ) {
		this.categoryID = categoryID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart viewpart) {
		shell = viewpart.getSite().getShell();
	}
	
	/**
	 * Initialize the shell for use as the parent shell of the action's dialog. 
	 * Use this method when the action is extended to run without being associated 
	 * with a view.
	 * @param parentShell
	 */
	public void init( Shell parentShell )
	{
	    shell = parentShell;
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#addHandlerListener(org.eclipse.core.commands.IHandlerListener)
	 */
	public final void addHandlerListener(final IHandlerListener listener) {
		if (listenerList == null) {
			listenerList = new ListenerList(ListenerList.IDENTITY);
		}

		listenerList.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#dispose()
	 */
	public final void dispose() {
		listenerList = null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart part = HandlerUtil.getActivePart(event);
		if (part == null && event.getApplicationContext() != null) 
			return null;
		else if (part instanceof IViewPart)
			init((IViewPart)part);
		
	    String ignoreCategoryValue = event.getParameter(PROP_IGNORE_CATEGORY);
	    if (ignoreCategoryValue != null && ignoreCategoryValue.trim().length() > 0) {
	    	ignoreCategory = Boolean.valueOf(ignoreCategoryValue).booleanValue();
	    }
		
	    String useSelectionValue = event.getParameter(PROP_USE_SELECTION);
	    if (useSelectionValue != null && useSelectionValue.trim().length() > 0 ) {
	    	useSelection = Boolean.valueOf(useSelectionValue).booleanValue();
	    }

	    if (ignoreCategory) {
			categoryID = null;
		}
		else if (useSelection) {
			ISelection selection =
				part.getSite().getSelectionProvider().getSelection();
			if (selection instanceof IStructuredSelection) {
				Object sel = ((IStructuredSelection) selection).getFirstElement();
				// update enabled state for add action on categories.
				if (sel instanceof ICategory) {
					setCategory((ICategory)sel);
				}
				else if (sel instanceof IConnectionProfile) {
					setCategory(((IConnectionProfile)sel).getCategory());
				}
				else
					categoryID = null;
			}
		}

		IWizard wizard = getDefaultWizard(new String(), categoryID);

		if (wizard == null) {
	        ViewerFilter[] wizardSelectionFilters = getApplicableFilters( categoryID );
			wizard = new NewCPWizard(wizardSelectionFilters,parentProfile);
		}		

		WizardDialog wizardDialog = new WizardDialog(shell, wizard);		
		wizardDialog.setBlockOnOpen(true);
		
		InternalProfileListener listener = new InternalProfileListener();
		ProfileManager.getInstance().addProfileListener(listener);
		
		returnCode = wizardDialog.open();
		
		addedProfile = listener.cachedProfile;
		
		ProfileManager.getInstance().removeProfileListener(listener);
		
		fireHandlerChanged(new HandlerEvent(this, false, false));

		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#removeHandlerListener(org.eclipse.core.commands.IHandlerListener)
	 */
	public void removeHandlerListener(IHandlerListener handlerListener) {
		if (listenerList != null) {
			listenerList.remove(handlerListener);

			if (listenerList.isEmpty()) {
				listenerList = null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		
		if (shell == null) {
			shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
		}
		
		IWizard wizard = getDefaultWizard(new String(), categoryID);

		if (wizard == null) {
	        ViewerFilter[] wizardSelectionFilters = getApplicableFilters( categoryID );
			wizard = new NewCPWizard(wizardSelectionFilters,parentProfile);
		}		

		WizardDialog wizardDialog = new WizardDialog(shell, wizard);		
		wizardDialog.setBlockOnOpen(true);
		
		InternalProfileListener listener = new InternalProfileListener();
		ProfileManager.getInstance().addProfileListener(listener);
		
		returnCode = wizardDialog.open();
		
		addedProfile = listener.cachedProfile;
		
		ProfileManager.getInstance().removeProfileListener(listener);
	}
    
    /**
     * Gets all the applicable filters for the specified category id.
     * The returned collection starts with the required category filter,
     * followed by any additional wizard selection filters that may be provided 
     * by an extended implementation.
     * @param categoryId    category id
     * @return an array of ViewerFilter instances
     */
	private ViewerFilter[] getApplicableFilters( String categoryId ) {
        ArrayList filters = new ArrayList();

        // adds required category filter
        filters.add( getCategoryFilter( categoryId ) );
        
        // appends additional wizard selection filters, if any
        ViewerFilter[] moreFilters = getWizardSelectionFilters();
        if( moreFilters != null ) {
            for( int i=0; i < moreFilters.length; i++ ) {
                filters.add( moreFilters[i] );
            }
        }

	    return (ViewerFilter[]) filters.toArray( new ViewerFilter[ filters.size() ] );
	}
	
	/**
	 * Gets the category filter for the specified category id.
	 * @param categoryId
	 * @return  a category ViewerFilter
	 * @since DTP 1.6
	 */
	protected ViewerFilter getCategoryFilter( String categoryId ) {
	    return new NewCPWizardCategoryFilter( categoryId );
	}

    /**
     * Gets the viewer filters for filtering the display of the connection profile 
     * wizard selection.
     * @return an array of ViewerFilter instances
	 * @since DTP 1.6
     */
    protected ViewerFilter[] getWizardSelectionFilters() {
        // this base class provides a category filter only;
        // subclass may override and provide additional filter(s)
        return null;
    }
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection && action != null) {
			Object sel = ((IStructuredSelection) selection).getFirstElement();
			// always enable add action on local repository node.
			if (sel instanceof LocalRepositoryNode) {
				action.setEnabled(true);
			}
			// disable add action on read-only non-local repository
			if (sel instanceof IConnectionProfile) {
				IManagedConnection imc = ((IConnectionProfile) sel)
						.getManagedConnection(IConnectionProfileRepositoryConstants.REPOSITORY_CONNECTION_FACTORY_ID);
				if (imc != null && imc.isConnected()) {
					IConnectionProfileRepository repo = (IConnectionProfileRepository) imc
							.getConnection().getRawConnection();
					action.setEnabled(!repo.isReadOnly());
				}
			// update enabled state for add action on categories.
			} else if (sel instanceof ICategory) {
				if (sel instanceof Category) {
					IConnectionProfile profile = ((Category) sel)
							.getRepositoryProfile();
					// disable add action on categories in read-only
					// non-local repository
					if (profile != null) {
						IManagedConnection imc = profile
								.getManagedConnection(IConnectionProfileRepositoryConstants.REPOSITORY_CONNECTION_FACTORY_ID);
						if (imc != null && imc.isConnected()) {
							IConnectionProfileRepository repo = (IConnectionProfileRepository) imc
									.getConnection().getRawConnection();
							action.setEnabled(!repo.isReadOnly());
						}
					// enable add action on categories in local repository.
					} else {
						action.setEnabled(true);
					}
				}
			}
		}
	}
	
	/**
	 * @param category
	 */
	public void setCategory ( ICategory category ) {
		setCategory(category == null ? null : category.getId());
	}
	
	/**
	 * @param categoryID
	 */
	public void setCategory ( String categoryID ) {
		this.categoryID = categoryID;
	}

	/**
	 * @param profile
	 */
	public void setParentProfile(IConnectionProfile profile) {
		parentProfile = profile;
	}
	
	/**
	 * @return
	 */
	public IConnectionProfile getParentProfile() {
		return parentProfile;
	}
	
	/**
	 * @return
	 */
	public int getWizardReturnCode() {
		return this.returnCode;
	}
	
	/**
	 * @return
	 */
	public IConnectionProfile getAddedProfile() {
		return this.addedProfile;
	}
	
	/**
	 * @param parentCategoryID
	 * @param categoryID
	 * @return
	 */
	private IWizard getDefaultWizard(String parentCategoryID, String categoryID) {
		List wizardNodes = new CPWizardSelectionPage(new String())
				.getCategoryItems(parentCategoryID);

		// include category filter if categoryID is defined
	    ViewerFilter[] wizardNodeFilters = (categoryID != null) ?
        	                    getApplicableFilters( categoryID ) :
        	                    getWizardSelectionFilters();
        if ( wizardNodeFilters != null && wizardNodeFilters.length > 0 ) {
			for (Iterator it = wizardNodes.iterator(); it.hasNext();) {
			    Object wizardNode = it.next();
			    for ( int i = 0; i < wizardNodeFilters.length; i++ ) {
		            ViewerFilter filter = wizardNodeFilters[i];
    				if (!filter.select(null, null, wizardNode)) {
    					it.remove();
    					break;     // done with checking on filters
    				}
			    }
			}
        }

		if (wizardNodes == null || wizardNodes.size() == 0) {
			return null;
		}
		if (wizardNodes.size() > 1) {
			return null;
		}

		IWizard wizard;
		CPWizardNode wizardNode = (CPWizardNode) wizardNodes.get(0);
		if (wizardNode instanceof CPCategoryWizardNode) {
			wizard = getDefaultWizard(((CPCategoryWizardNode) wizardNode)
					.getProvider().getCategory(), null);
			if (wizard == null) {
				wizard = wizardNode.getWizard();
				if (wizard instanceof NewCategoryWizard) {
					((NewCategoryWizard) wizard)
							.initWizardCategory((IWizardCategoryProvider) wizardNode
									.getProvider());
				}
			}
		}
		else {
			wizard = wizardNode.getWizard();
			if (wizard instanceof ICPWizard) {
				((ICPWizard) wizard)
						.initProviderID(((ProfileWizardProvider) wizardNode
								.getProvider()).getProfile());
				((ICPWizard) wizard).setParentProfile(parentProfile);
			}
		}
		return wizard;
	}
	
	/**
	 * Internal listener to listen for the new profile
	 * @author brianf
	 *
	 */
	private class InternalProfileListener implements IProfileListener {

		protected IConnectionProfile cachedProfile;
		
		public void profileAdded(IConnectionProfile profile) {
			cachedProfile = profile;
		}

		public void profileChanged(IConnectionProfile profile) {
			// ignore
		}

		public void profileDeleted(IConnectionProfile profile) {
			// ignore
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#isEnabled()
	 */
	public boolean isEnabled() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#isHandled()
	 */
	public boolean isHandled() {
		return true;
	}
	
	/**
	 * @return
	 */
	public boolean getIgnoreCategory() {
		return this.ignoreCategory;
	}
	
	/**
	 * @param flag
	 */
	public void setIgnoreCategory( boolean flag ) {
		this.ignoreCategory = flag;
	}

	/**
	 * @return
	 */
	public boolean getUseSelection() {
		return this.useSelection;
	}
	
	/**
	 * @param flag
	 */
	public void setUseSelection( boolean flag ) {
		this.useSelection = flag;
	}

	/**
	 * @param handlerEvent
	 */
	protected void fireHandlerChanged(final HandlerEvent handlerEvent) {
		if (handlerEvent == null) {
			throw new NullPointerException();
		}
		if (listenerList == null)
			return;

		final Object[] listeners = listenerList.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			final IHandlerListener listener = (IHandlerListener) listeners[i];
			listener.handlerChanged(handlerEvent);
		}
	}
}