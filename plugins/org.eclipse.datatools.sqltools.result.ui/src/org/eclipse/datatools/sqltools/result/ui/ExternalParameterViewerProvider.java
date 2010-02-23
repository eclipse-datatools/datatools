/*******************************************************************************
 * Copyright 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.ui;

import org.eclipse.datatools.sqltools.result.ui.view.ParameterViewerProvider;

/**
 * Extends ParameterTableViewerProvider to allow extensions to inherit the default parameter viewer
 * @author Chetan Bhatia
 */
public class ExternalParameterViewerProvider extends ParameterViewerProvider {

	/**
	 * Configures the viewer. The default implementation creates a
	 * ParameterTableViewer.
	 */
	public void configureViewer() {
		tableViewer = new ExternalParameterTableViewer(parentComposite,
				tableStyle);
	}
}
