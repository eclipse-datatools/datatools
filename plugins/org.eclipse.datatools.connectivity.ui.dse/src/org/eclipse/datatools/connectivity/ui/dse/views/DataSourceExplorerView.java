/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.dse.views;

import java.util.List;

import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.navigator.common.actions.NavigatorOpenAction;
import org.eclipse.datatools.connectivity.navigator.dnd2.NavigatorDragAdapter;
import org.eclipse.datatools.connectivity.navigator.views.NavigatorViewPart;
import org.eclipse.datatools.connectivity.navigator.views.NavigatorViewer;
import org.eclipse.datatools.connectivity.navigator.views.internal.NavigatorPlugin;
import org.eclipse.datatools.connectivity.navigator.views.registry.NavigatorRegistry;
import org.eclipse.datatools.connectivity.navigator.views.registry.NavigatorViewerDescriptor;
import org.eclipse.datatools.connectivity.ui.IContentExtension;
import org.eclipse.datatools.connectivity.ui.dse.DSEPlugin;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;

/**
 * DataSourceExplorerView is used to display servers content.
 * 
 * @author shongxum
 * @see ViewPart
 */
public class DataSourceExplorerView extends NavigatorViewPart {

	private final static String TAG_SHOWCATEGORY = "showCategory"; //$NON-NLS-1$

	private NavigatorViewer mViewer = null;

	private boolean mShowCategory = false;

	private IViewSite mSite;

	/**
	 * @see ViewPart#createPartControl
	 */
	public void createPartControl(Composite parent) {
		mViewer = createPartControl(parent, getSite(), getViewSite(),
				getViewSite().getId(), this, ProfileManager.getInstance());
		final TreeViewer tviewer = mViewer;
		hookDoubleClickAction(tviewer);
		mViewer.refresh();
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
				IHelpConstants.CONTEXT_ID_CONNECTIVITY_DSE_VIEW);
	}

	public static NavigatorViewer createPartControl(Composite parent,
			IWorkbenchPartSite site, IViewSite viewSite,
			NavigatorViewPart viewPart, ViewerFilter filter) {
		NavigatorViewer viewer = createPartControl(parent, site, viewSite,
				DSEPlugin.SERVERS_VIEW_VIEWER_ID, viewPart, ProfileManager
						.getInstance());
		if (filter != null) {
			viewer.addFilter(filter);
		}
		return viewer;
	}

	public static NavigatorViewer createPartControl(Composite parent,
			IWorkbenchPartSite site, IViewSite viewSite, String viewSiteId,
			NavigatorViewPart viewPart, Object input) {
		DrillDownAdapter drillDownAdapter[] = new DrillDownAdapter[1];
		NavigatorViewer viewer = createViewer(parent, viewSite, viewSiteId,
				drillDownAdapter, viewPart);
		if (viewPart != null) {
			((DataSourceExplorerView) viewPart).mViewer = viewer;
			((DataSourceExplorerView) viewPart)
					.setShowCategory(((DataSourceExplorerView) viewPart)
							.isShowCategory());
		}
		updateDescriptors();

		if (site != null) {
			site.setSelectionProvider(viewer);
		}

		createContextMenu(viewer, site);
		createToolbarMenu(viewSite, drillDownAdapter[0]);

		viewer.setInput(input);
		viewer.expandToLevel(2);

		return viewer;
	}

	/**
	 * Creates the viewer.
	 * 
	 * @param parent the parent composite
	 */
	protected static NavigatorViewer createViewer(Composite parent,
			IViewSite viewSite, String viewSiteId,
			DrillDownAdapter drillDownAdapter[], NavigatorViewPart viewPart) {
		NavigatorViewer viewer = new NavigatorViewer(parent, viewSiteId,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, viewPart);
		viewer.setUseHashlookup(true);
		initDragAndDrop(viewer);
		// initFilters(viewer);
		// initListeners(viewer);
		// Create drill down adapter for the viewer
		drillDownAdapter[0] = new DrillDownAdapter(viewer);
		// contributeToActionBars();
		viewer.setSorter(new ViewerSorter());

		return viewer;
	}

	private void hookDoubleClickAction(final TreeViewer tviewer) {
		tviewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) tviewer
						.getSelection();
				Object obj = selection.getFirstElement();
				if (obj instanceof IContentExtension) {
					IContentExtension ice = (IContentExtension) obj;
					if (ice.getConnection() != null
							&& ice.getConnection().getConnectException() != null) {
						Throwable ex = ice.getConnection()
								.getConnectException();
						ExceptionHandler
								.showException(
										mSite.getShell(),
										DSEPlugin
												.getDefault()
												.getResourceString(
														"dialog.exception.title"), //$NON-NLS-1$
										DSEPlugin
												.getDefault()
												.getResourceString(
														"dialog.exception.message", //$NON-NLS-1$
														new Object[] { ex
																.getMessage()}),
										ex);
						ice.getConnectionProfile().disconnect();
						return;
					}
				}
				if (tviewer.getExpandedState(obj)) {
					tviewer.collapseToLevel(obj, 1);
				}
				else {
					tviewer.expandToLevel(obj, 1);
				}
				handleOpen(selection);
				// tviewer.refresh(); CR364716-1
			}
		});
	}

	/**
	 * Add Drag & Drop ability
	 * 
	 * @param viewer
	 */
	protected static void initDragAndDrop(NavigatorViewer viewer) {
		int ops = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { PluginTransfer.getInstance(),
				LocalSelectionTransfer.getInstance()};
		viewer.addDragSupport(ops, transfers, new NavigatorDragAdapter(viewer
				.getSite()));
	}

	/*
	 * Create view toolbar menu
	 */
	private static void createToolbarMenu(IViewSite viewSite,
			DrillDownAdapter drillDownAdapter) {
		if (viewSite != null) {
			IToolBarManager toolBar = viewSite.getActionBars()
					.getToolBarManager();
			drillDownAdapter.addNavigationActions(toolBar);
			toolBar.add(new Separator("slot1"));
			toolBar.add(new Separator("slot2"));
			toolBar.add(new Separator("slot3"));
			toolBar.add(new Separator("slotn"));

			IMenuManager menuBar = viewSite.getActionBars().getMenuManager();
			menuBar.add(new Separator("slotn"));
			menuBar.add(new Separator("slot3"));
			menuBar.add(new Separator("slot2"));
			menuBar.add(new Separator("slot1"));
		}
	}

	private static void createContextMenu(NavigatorViewer viewer,
			IWorkbenchPartSite site) {
		MenuManager menuManager = new MenuManager();
		menuManager.setRemoveAllWhenShown(true);
		final NavigatorViewer mViewer = viewer;
		menuManager.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(IMenuManager manager) {
				mViewer.fillContextMenu(manager);
			}
		});
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);

		if (site != null) {
			site.registerContextMenu(menuManager, viewer);
		}
	}

	private static void updateDescriptors() {
		NavigatorPlugin plugin = NavigatorPlugin.getDefault();
		NavigatorRegistry registry = plugin.getNavigatorRegistry();

		List views = registry
				.getViewDescriptors(DSEPlugin.SERVERS_VIEW_VIEWER_ID);
		for (int i = 0; i < views.size(); i++) {
			Object obj = views.get(i);
			if (obj instanceof NavigatorViewerDescriptor) {
				NavigatorViewerDescriptor nvd = (NavigatorViewerDescriptor) obj;
				nvd.setVisible(true);
			}

		}
	}

	/**
	 * @see ViewPart#setFocus
	 */
	public void setFocus() {
		mViewer.getControl().setFocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewPart#init(org.eclipse.ui.IViewSite,
	 *      org.eclipse.ui.IMemento)
	 */
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		super.init(site, memento);
		mSite = site;
		if (memento != null) {
			String showCat = memento.getString(TAG_SHOWCATEGORY);
			if (showCat != null && showCat.equalsIgnoreCase("Yes")) //$NON-NLS-1$
				mShowCategory = true;
		}
		else {
			// Default will be displaying cateogories
			mShowCategory = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewPart#saveState(org.eclipse.ui.IMemento)
	 */
	public void saveState(IMemento memento) {
		if (memento != null) {
			memento.putString(TAG_SHOWCATEGORY, isShowCategory() ? "Yes" //$NON-NLS-1$
					: "No"); //$NON-NLS-1$
		}
		super.saveState(memento);
	}

	/**
	 * @return Returns the showCategory.
	 */
	public boolean isShowCategory() {
		return mShowCategory;
	}

	/**
	 * @param showCategory The showCategory to set.
	 */
	public void setShowCategory(boolean showCategory) {
		mShowCategory = showCategory;
		getServersContentProvider().setShowCategories(showCategory);
	}

	private ConnectionProfileContentProvider getServersContentProvider() {
		return (ConnectionProfileContentProvider) getExtensionSite()
				.getContentExtension(
						DSEPlugin.SERVERS_VIEW_CONTENT_EXTENSION_ID)
				.getContentProvider();
	}

	/**
	 * @return Returns the viewer.
	 */
	public TreeViewer getViewer() {
		return mViewer;
	}

	/**
	 * Handles a double-click event from the viewer. Expands or collapses a
	 * folder when double-clicked.
	 * 
	 * @param event the double-click event
	 */
	void handleOpen(IStructuredSelection selection) {
		NavigatorOpenAction openAction = new NavigatorOpenAction();
		openAction.init(mViewer.getSite());
		openAction.run();
	}
}