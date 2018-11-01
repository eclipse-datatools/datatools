/*******************************************************************************
 * Copyright (c) 2004, 2010 Sybase, Inc. and others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich, shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.navigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IProfileListener;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.Category;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
import org.eclipse.datatools.connectivity.internal.ui.AddConnectListenerRegistry;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.LocalRepositoryNode;
import org.eclipse.datatools.connectivity.ui.IAddConnectListener;
import org.eclipse.datatools.connectivity.ui.RefreshProfileJob;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.eclipse.ui.navigator.IExtensionStateModel;
import org.eclipse.ui.navigator.INavigatorContentService;

/**
 * @author rcernich, shongxum, brianf
 */
public class ConnectionProfileContentProvider implements ICommonContentProvider {

	private boolean mShowCategories = true;

	private Viewer mViewer;

	private Object mInput;
	
	private IExtensionStateModel mStateModel;
	
	private LocalRepositoryNode mLocalRepoNode;

	private IProfileListener mProfileListener = new IProfileListener() {

		public void profileAdded(IConnectionProfile profile) {
			profile.addPropertySetListener(mPropertyListener);
			refreshViewer(
					mShowCategories && !isRepositoryProfile(profile) ? profile
							.getCategory() : null, true);
		}

		public void profileDeleted(IConnectionProfile profile) {
			profile.removePropertySetListener(mPropertyListener);
			refreshViewer(
					mShowCategories && !isRepositoryProfile(profile) ? profile
							.getCategory() : null, true);
		}

		public void profileChanged(IConnectionProfile profile) {
			// refreshViewer(profile, false);
		}

	};

	private IPropertySetListener mPropertyListener = new IPropertySetListener() {

		public void propertySetChanged(IPropertySetChangeEvent event) {
			handleProfilePropertyChanged(event);
		}
	};
	
	private IPropertyChangeListener mPropertyChangeListener = new IPropertyChangeListener() {

		public void propertyChange(PropertyChangeEvent event) {
			if (ConnectivityUIPlugin.PROP_SHOW_CATEGORIES.equals(event.getProperty())) {
				setShowCategories(((Boolean) event.getNewValue())
						.booleanValue());
			}
		}
	};

	/**
	 * Constructor
	 */
	public ConnectionProfileContentProvider() {
		super();
		ProfileManager.getInstance().addProfileListener(mProfileListener);
	}

	public void setShowCategories(boolean showCategories) {
		if (showCategories != mShowCategories) {
			mShowCategories = showCategories;
			refreshViewer(null, false);
		}
	}

	public boolean isShowCategories() {
		return mShowCategories;
	}

	protected void refreshViewer(final Object object,
			final boolean expand) {
		if (mViewer instanceof TreeViewer && object instanceof IConnectionProfile ) {
			RefreshProfileJob.scheduleRefreshProfileJob((IConnectionProfile)object,
					(TreeViewer) mViewer);
		}
		else {
			Runnable runner = new Runnable() {

				public void run() {
				    if (mViewer instanceof CommonViewer) {
				    	RuntimeException exception = null;
				    	try {
				    		((CommonViewer)mViewer).refresh(object);
				    	} catch (SWTException swte){
				    		return;
				    	} catch (RuntimeException re) {
				    		exception = re;
				    		int count = 5;
				    		while (exception != null && count > 0) {
				    			count--;
						    	try {
						    		((CommonViewer)mViewer).refresh(object);
						    	} catch (RuntimeException re2) {
						    		exception = re2;
						    	}
				    		}
				    		if (count == 0 && exception != null)
				    			ConnectivityUIPlugin.getDefault().log(exception);
				    	}
				    }
				    else if (mViewer instanceof StructuredViewer) {
				        try {
							((StructuredViewer)mViewer).refresh(object);
						} catch (SWTException e) {
							return;
						}
				    }
				}
			};
			if (mViewer != null && !mViewer.getControl().isDisposed()
					&& !mViewer.getControl().getDisplay().isDisposed())
				mViewer.getControl().getDisplay().asyncExec(runner);
		}
	}

	protected void handleProfilePropertyChanged(IPropertySetChangeEvent event) {
		if (IConnectionProfile.CONNECTION_PROFILE_PROPERTY_SET.equals(event
				.getPropertySetType())
				&& event
						.getChangedProperty(IConnectionProfile.CONNECTION_STATE_PROPERTY_ID) != null) {

			final IPropertySetChangeEvent finalEvent = event;
			Display display = PlatformUI.getWorkbench().getDisplay();
			if (display != null && !display.isDisposed()) {
				display.asyncExec(new Runnable() {

					public void run() {
						final IWorkbenchPart dse = PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getActivePage()
								.findView(ConnectivityUIPlugin.SERVERS_VIEW_VIEWER_ID);
						if (dse != null) {
							CommonNavigator navigator = (CommonNavigator) dse;
							INavigatorContentService contentService = navigator
									.getNavigatorContentService();
							String description = contentService
									.createCommonDescriptionProvider()
									.getDescription(
											finalEvent.getConnectionProfile());
							Image image = contentService
									.createCommonLabelProvider().getImage(
											finalEvent.getConnectionProfile());
							navigator.getViewSite().getActionBars()
									.getStatusLineManager().setMessage(image,
											description);
						}
					}
				});
			}
			refreshViewer(event.getConnectionProfile(), false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		Object[] children = new Object[0];
		if (parentElement instanceof IWorkspaceRoot) {
			return getChildren(ProfileManager.getInstance());
		}
		else if (parentElement instanceof ProfileManager) {
			ProfileManager pm = (ProfileManager) parentElement;
			List kids = new ArrayList();

			if (this.mLocalRepoNode == null)
				this.mLocalRepoNode = new LocalRepositoryNode();
			kids.add(this.mLocalRepoNode);
			recurseSubCategoryProfiles(
					pm
							.getCategory(IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID),
					kids);
			if (kids.size() == 1) {
				// Don't show local repository node if it's the only repository
				// in use
				children = getChildren(kids.get(0));
			}
			else {
				children = kids.toArray();
			}
		}
		else if (parentElement instanceof LocalRepositoryNode) {
			List kids = new ArrayList();
			ProfileManager pm = ProfileManager.getInstance();			
			if (mShowCategories) {
				kids.addAll(Arrays.asList(pm.getRootCategories()));
				kids
						.remove(pm
								.getCategory(IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID));
				children = kids.toArray();

			}
			else {
				kids.addAll(Arrays.asList(pm.getProfiles(false)));
				removeRepositoryProfiles(
						kids,
						pm
								.getCategory(IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID));
			}
			children = kids.toArray();
		}
		else if (parentElement instanceof Object[]) {
			children = (Object[]) parentElement;
		}
		else if (parentElement instanceof ICategory) {
			ICategory icat = (ICategory) parentElement;
			ArrayList arry = new ArrayList();
			if (mShowCategories) {
				arry.addAll(icat.getChildCategories());
				arry.addAll(icat.getAssociatedProfiles());
			}
			else {
				arry.addAll(icat.getAssociatedProfiles());
				recurseSubCategoryProfiles(icat, arry);
			}
			children = arry.toArray();
		}
		else if (parentElement instanceof IConnectionProfile) {
			children = new Object[0];
		}
		if (children != null && children.length > 0) {
			Arrays.sort(children, new Comparator() {

				public int compare(Object e1, Object e2) {
					// Make sure the local repository node is the first item in the tree
					if (e1 instanceof LocalRepositoryNode) {
						return -1;
					}
					else if (e2 instanceof LocalRepositoryNode) {
						return 1;
					}
					if (e1 instanceof IConnectionProfile && e2 instanceof IConnectionProfile) {
						IConnectionProfile icp1 = (IConnectionProfile) e1;
						IConnectionProfile icp2 = (IConnectionProfile) e2;
						return icp1.getName().compareToIgnoreCase(icp2.getName());
					}
					if (e1 instanceof ICategory && e2 instanceof ICategory) {
						ICategory icat1 = (ICategory) e1;
						ICategory icat2 = (ICategory) e2;
						return icat1.getName().compareToIgnoreCase(icat2.getName());
					}
					return 0;
				}
			});
		}
		return children;
	}
	
	private void removeRepositoryProfiles(List kids, ICategory category) {
		kids.removeAll(category.getAssociatedProfiles());
		for (Iterator it = category.getChildCategories().iterator(); it
				.hasNext();) {
			removeRepositoryProfiles(kids, (ICategory) it.next());
		}
	}

	private void recurseSubCategoryProfiles(ICategory parent, List arry) {
		arry.addAll(parent.getAssociatedProfiles());
		for (Iterator it = parent.getChildCategories().iterator(); it.hasNext();) {
			ICategory sub = (ICategory) it.next();
			recurseSubCategoryProfiles(sub, arry);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		Object parent = null;

		if (element instanceof ICategory) {
			parent = ((ICategory) element).getParent();
			if (parent == null) {
				parent = ((Category)element).getRepositoryProfile();
			}
			if (parent == null) {
				parent = mInput;
			}
		}
		else if (element instanceof IConnectionProfile) {
			if (mShowCategories) {
				parent = ((IConnectionProfile) element).getCategory();
				if (parent == null
						|| isRepositoryProfile((IConnectionProfile) element)) {
					parent = mInput;
				}
			}
			else {
				parent = ((IConnectionProfile)element).getParentProfile();
				if (parent == null) {
					parent = mInput;
				}
			}
		}

		return parent;
	}
	
	private boolean isRepositoryProfile(IConnectionProfile profile) {
		for (ICategory cat = profile.getCategory(); cat != null; cat = cat
				.getParent()) {
			if (IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID
					.equals(cat.getId())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		ProfileManager.getInstance().removeProfileListener(mProfileListener);
		for (Iterator it = Arrays.asList(
				ProfileManager.getInstance().getProfiles()).iterator(); it
				.hasNext();) {
			((IConnectionProfile) it.next())
					.removePropertySetListener(mPropertyListener);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		mInput = newInput;
		mViewer = viewer;
		if (mViewer == null) {
			// Remove listeners
			for (Iterator it = Arrays.asList(
					ProfileManager.getInstance().getProfiles()).iterator(); it
					.hasNext();) {
				((IConnectionProfile) it.next())
						.removePropertySetListener(mPropertyListener);
			}
		}
		else {
			// Add listeners
			for (Iterator it = Arrays.asList(
					ProfileManager.getInstance().getProfiles()).iterator(); it
					.hasNext();) {
				((IConnectionProfile) it.next())
						.addPropertySetListener(mPropertyListener);
			}
			
	         for (Iterator it = Arrays.asList(
	                    ProfileManager.getInstance().getProfiles()).iterator(); it
	                    .hasNext();) {
	             IConnectionProfile thisProfile = (IConnectionProfile)it.next();
	             IAddConnectListener provider = AddConnectListenerRegistry.INSTANCE.getProvider(thisProfile);
	             if (provider != null) {
	                 provider.addConnectListener(thisProfile);
	             }
	        }

			if (mViewer instanceof CommonViewer) {
				((CommonViewer) mViewer).getNavigatorContentService()
						.findStateModel(ConnectivityUIPlugin.SERVERS_VIEW_CONTENT_EXTENSION_ID)
						.removePropertyChangeListener(mPropertyChangeListener);
				
				IExtensionStateModel stateModel = ((CommonViewer) mViewer)
						.getNavigatorContentService().findStateModel(
								ConnectivityUIPlugin.SERVERS_VIEW_CONTENT_EXTENSION_ID);
				
				stateModel.addPropertyChangeListener(mPropertyChangeListener);
				
				boolean testStateCategories =
					stateModel.getBooleanProperty(ConnectivityUIPlugin.PROP_SHOW_CATEGORIES);
				if (mShowCategories != testStateCategories)
					setShowCategories(testStateCategories);
			}
		}
	}

	public void init(ICommonContentExtensionSite aConfig) {
		mStateModel = aConfig.getExtensionStateModel();
	}

	public void restoreState(IMemento aMemento) {
//		String showCatString = aMemento
//				.getString(ConnectivityUIPlugin.PROP_SHOW_CATEGORIES);

		// Continue to use the default until we figure out how to update the
		// action state (ShowCategoryAction)
		//mShowCategories = showCatString == null ? true : Boolean.valueOf(
		//		showCatString).booleanValue();

		mStateModel.setBooleanProperty(ConnectivityUIPlugin.PROP_SHOW_CATEGORIES,
				mShowCategories);

	}

	public void saveState(IMemento aMemento) {
		aMemento.putString(ConnectivityUIPlugin.PROP_SHOW_CATEGORIES, Boolean.toString(isShowCategories()));
	}

}