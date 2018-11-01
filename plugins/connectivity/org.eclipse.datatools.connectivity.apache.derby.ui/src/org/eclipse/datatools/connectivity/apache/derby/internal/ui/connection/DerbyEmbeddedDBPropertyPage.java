/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 * 				 brianf - customized for Derby url requirements
 *      IBM Corporation - migrated to new wizard framework
 ******************************************************************************/
package org.eclipse.datatools.connectivity.apache.derby.internal.ui.connection;

import org.eclipse.datatools.connectivity.apache.derby.internal.ui.DerbyUIPlugin;
import org.eclipse.datatools.connectivity.apache.internal.derby.connection.IDerbyConnectionProfileConstants;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsPropertyPage;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Property page for Derby Embedded profiles
 * 
 */
public class DerbyEmbeddedDBPropertyPage extends
		ExtensibleProfileDetailsPropertyPage implements IContextProvider {

	/**
	 * Constructor
	 */
	public DerbyEmbeddedDBPropertyPage() {
		super(IDerbyConnectionProfileConstants.DERBY_CATEGORY_ID);
	}

	private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(
			DerbyUIPlugin.getDefault().getBundle().getSymbolicName());

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
		getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp(getControl(), HelpUtil
				.getContextId(IHelpConstants.GENERIC_DB_PROFILE_PROPERTY_PAGE,
						ConnectivityUIPlugin.getDefault().getBundle()
								.getSymbolicName()));
		return contents;
	}
}
