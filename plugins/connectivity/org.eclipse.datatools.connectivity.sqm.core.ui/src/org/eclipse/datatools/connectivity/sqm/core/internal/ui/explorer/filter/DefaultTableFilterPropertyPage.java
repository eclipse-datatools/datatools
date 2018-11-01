/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.IHelpContextsSQMCoreUI;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.RDBCoreUIPlugin;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class DefaultTableFilterPropertyPage 
	extends DefaultFilterPropertyPage
	implements IContextProvider {

	public DefaultTableFilterPropertyPage() {
		super();
		setDefaultPageTitle(
				ResourceLoader.getResourceLoader().queryString("_UI_TABLE_FILTER_PAGE_TITLE")); //$NON-NLS-1$
	}

	protected String getConnectionFilterType() {
		return ConnectionFilter.TABLE_FILTER;
	}

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(RDBCoreUIPlugin.getDefault().getBundle().getSymbolicName());

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), 
				HelpUtil.getContextId(IHelpContextsSQMCoreUI.DEFAULT_TABLE_FILTER_PROPERTY_PAGE, 
							RDBCoreUIPlugin.getDefault().getBundle().getSymbolicName()));
		return contents;
	}
}
