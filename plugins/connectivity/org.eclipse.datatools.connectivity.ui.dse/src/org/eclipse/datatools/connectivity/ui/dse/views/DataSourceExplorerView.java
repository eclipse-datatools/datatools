/*******************************************************************************
 * Copyright (c) 2006-2009 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *    brianf - updates to set state of ShowCategory handler
 *    brianf - fixes for BZ 272274
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.ui.dse.views;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.ui.dse.DSEPlugin;
import org.eclipse.datatools.connectivity.ui.dse.IHelpContextsConnectivityUIDSE;
import org.eclipse.datatools.connectivity.ui.dse.actions.ShowCategoryAction;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.commands.ToggleState;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.navigator.CommonNavigator;

public class DataSourceExplorerView extends CommonNavigator
	implements IContextProvider {
	
	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(DSEPlugin.getDefault().getBundle().getSymbolicName());
	
	/**
	 * Constructor
	 */
	public DataSourceExplorerView() {
		super();
	}

	/**
	 * Override this to provide a different input object to the DSE,
	 * This used to override the getInitialInput() from the CommonNavigator
	 * but the signature changed for 3.5 and we ran into some weird 
	 * JDK 1.4/1.5 incompatibilities with the changes. 
	 * See BZ 272274 for more details.
	 * @return IAdaptable
	 */
	protected IAdaptable getDSEInitialInput() {
		return ProfileManager.getInstance();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.help.IContextProvider#getContext(java.lang.Object)
	 */
	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.help.IContextProvider#getContextChangeMask()
	 */
	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.help.IContextProvider#getSearchExpression(java.lang.Object)
	 */
	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.CommonNavigator#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getCommonViewer().getTree(), IHelpContextsConnectivityUIDSE.CONTEXT_ID_CONNECTIVITY_DSE_VIEW);
		
		// added for BZ 272274, BTF
		try {
			getCommonViewer().getControl().setRedraw(false);
			getCommonViewer().setInput(getDSEInitialInput()); 
		} finally { 
			getCommonViewer().getControl().setRedraw(true);
		}	
		
		createHandlers();
	}
	
	/**
	 * creates the initial show category handler
	 */
	private void createHandlers() {

		// set the initial state of the ShowCategory menu & toolbar button
		IHandlerService handlerService = (IHandlerService) getSite()
				.getService(IHandlerService.class);
		ShowCategoryAction handler = new ShowCategoryAction();
		handler.init(this);
		handlerService.activateHandler(ShowCategoryAction.HANDLER_ID, 
				handler);
		ToggleState ts = new ToggleState();
		ts.setValue(Boolean.TRUE);
		handler.addState("STYLE", ts); //$NON-NLS-1$
	}
}
