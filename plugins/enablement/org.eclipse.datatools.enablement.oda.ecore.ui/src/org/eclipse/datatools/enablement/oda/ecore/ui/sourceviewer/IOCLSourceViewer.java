/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.ui.sourceviewer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.widgets.Composite;

public interface IOCLSourceViewer {

	Composite createExpressionControl(Composite parent);

	Composite getExpressionControl();

	SourceViewer getSourceViewer();

	void setContext(EObject context);

	EObject getContext();

	void setExpression(String expression);

	String getExpression();
}
