/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.ui.dse.views;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.ui.dse.DSEPlugin;
import org.eclipse.datatools.connectivity.ui.dse.IHelpContextsConnectivityUIDSE;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;

public class DataSourceExplorerView extends CommonNavigator
	implements IContextProvider {
	
	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(DSEPlugin.getDefault().getBundle().getSymbolicName());
	
	public DataSourceExplorerView() {
		super();
	}

	protected IAdaptable getInitialInput() {
		return ProfileManager.getInstance();
	}

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getCommonViewer().getTree(), IHelpContextsConnectivityUIDSE.CONTEXT_ID_CONNECTIVITY_DSE_VIEW);
	}
}
