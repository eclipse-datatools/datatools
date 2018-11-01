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
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.ui.IWorkbenchWindowPerspectiveActionFilter;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Allows filtering of Workbench Window properties, specifically for
 * perspective ID filtering. See the TODO in the constructor.
 * @author brianf
 *
 */
public class WorkbenchWindowPerspectiveActionFilter extends PropertyTester implements IWorkbenchWindowPerspectiveActionFilter, IPropertyTester {

	private static boolean mDebug = ConnectivityPlugin.getDefault().isDebugging();
	
	/**
	 * This class is used to check the perspective ID for the current workbench
	 * window. 
	 * 
	 * TODO: This class needs to be removed once we're no longer supporting
	 * Eclipse 3.3. In Eclipse 3.4, there is a new action filter built-in to
	 * the platform to handle this. ISources.ACTIVE_WORKBENCH_WINDOW_ACTIVE_PERSPECTIVE,
	 * which is basically "activeWorkbenchWindow.activePerspective" as the test.
	 */
	public WorkbenchWindowPerspectiveActionFilter() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionFilter#testAttribute(java.lang.Object, java.lang.String, java.lang.String)
	 */
	public boolean testAttribute(Object target, String name, String value) {
		if (target == null || !(target instanceof IWorkbenchWindow)) {
			return false;
		}
		final IWorkbenchWindow window = (IWorkbenchWindow) target;
		debug("WorkbenchWindow testAttribute: name =" + name + ", value = " + value); //$NON-NLS-1$ //$NON-NLS-2$
		if (name.equals(WORKBENCH_WINDOW_PERSPECTIVE_PROPERTY_PERSPECTIVE_ID) || name.equals(PERSPECTIVE_ID)) {
			IWorkbenchPage page = window.getActivePage();
			if (page != null) {
				IPerspectiveDescriptor persp = page.getPerspective();
				if (persp != null) {
					if (persp.getId().equals(value)) {
						debug(WORKBENCH_WINDOW_PERSPECTIVE_PROPERTY_PERSPECTIVE_ID + ", value = " + true); //$NON-NLS-1$
						return true;
					}
				}
			}
			debug(WORKBENCH_WINDOW_PERSPECTIVE_PROPERTY_PERSPECTIVE_ID + ", value = " + false); //$NON-NLS-1$
			return false;
		}
		return false;
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
