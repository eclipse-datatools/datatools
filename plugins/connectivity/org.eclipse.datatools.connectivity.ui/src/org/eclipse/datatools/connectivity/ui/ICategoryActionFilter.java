/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    brianf - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.ui.IActionFilter;


/**
 * Constants used when evaluating category state through
 * IActionFilter or IPropertyTester
 * 
 * TODO: remove duplicate entries.
 */
public interface ICategoryActionFilter extends IActionFilter {
	
	/**
	 * Used to filter profiles within a specific category
	 */
	public static final String CATEGORY_PROPERTY_CATEGORY_ID = "org.eclipse.datatools.connectivity.category.property.categoryID"; //$NON-NLS-1$
	public static final String CATEGORY_ID = "categoryID"; //$NON-NLS-1$

	public static final String CATEGORY_PROPERTY_REPOSITORY_IS_READ_ONLY = "org.eclipse.datatools.connectivity.category.property.repositoryIsReadOnly";//$NON-NLS-1$
	public static final String REPOSITORY_IS_READ_ONLY = "repositoryIsReadOnly";//$NON-NLS-1$
}
