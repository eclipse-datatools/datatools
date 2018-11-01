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
 * Constants used when evaluating perspective state through
 * IActionFilter or IPropertyTester
 * 
 * TODO: remove duplicate entries.
 * TODO: When we don't have to support 3.3, we can remove this class.
 * See the implementation of the WorkbenchWindowPerspectiveActionFilter
 * for details.
 */
public interface IWorkbenchWindowPerspectiveActionFilter extends IActionFilter {
	
	/**
	 * Used to filter views within a specific perspective
	 */
	public static final String WORKBENCH_WINDOW_PERSPECTIVE_PROPERTY_PERSPECTIVE_ID = "org.eclipse.datatools.connectivity.workbench.window.perspective.property.perspectiveID"; //$NON-NLS-1$
	public static final String PERSPECTIVE_ID = "perspectiveID"; //$NON-NLS-1$
}
