/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.core.expressions.IPropertyTester;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.internal.Category;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
import org.eclipse.datatools.connectivity.ui.ICategoryActionFilter;

/**
 * Allows filtering by category properties in plugin.xml
 * @author brianf
 *
 */
public class CategoryActionFilter extends PropertyTester 
	implements ICategoryActionFilter, IPropertyTester {

	private static boolean mDebug = ConnectivityPlugin.getDefault().isDebugging();
	
	/**
	 * Constructor
	 */
	public CategoryActionFilter() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionFilter#testAttribute(java.lang.Object, java.lang.String, java.lang.String)
	 */
	public boolean testAttribute(Object target, String name, String value) {
		if (target == null || !(target instanceof ICategory)) {
			return false;
		}
		ICategory category = (ICategory) target;
		debug("category testAttribute: name =" + name + ", value = " + value); //$NON-NLS-1$ //$NON-NLS-2$
		if (name.equals(CATEGORY_PROPERTY_CATEGORY_ID) || name.equals(CATEGORY_ID)) {
			return category.getId().equals(value);
		}
		else if (name.equals(CATEGORY_PROPERTY_REPOSITORY_IS_READ_ONLY) || name.equals(REPOSITORY_IS_READ_ONLY)) {
			if (category instanceof Category) {
				IConnectionProfile profile = ((Category) category)
						.getRepositoryProfile();
				// disable add action on categories in read-only
				// non-local repository
				if (profile != null) {
					IManagedConnection imc = profile
							.getManagedConnection(IConnectionProfileRepositoryConstants.REPOSITORY_CONNECTION_FACTORY_ID);
					if (imc != null && imc.isConnected()) {
						IConnectionProfileRepository repo = (IConnectionProfileRepository) imc
								.getConnection().getRawConnection();
						debug(CATEGORY_PROPERTY_REPOSITORY_IS_READ_ONLY + ", value = " + repo.isReadOnly()); //$NON-NLS-1$
						return repo.isReadOnly();
					}
				}
			}
			debug(CATEGORY_PROPERTY_REPOSITORY_IS_READ_ONLY + ", value = " + false); //$NON-NLS-1$
			return false;
		}
		else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		return testAttribute(receiver, property, expectedValue == null ? null : expectedValue.toString());
	}

	/**
	 * @param msg
	 */
	public static void debug ( String msg ) {
		if (mDebug)
			System.out.println("Debug: " + msg); //$NON-NLS-1$
	}
}
