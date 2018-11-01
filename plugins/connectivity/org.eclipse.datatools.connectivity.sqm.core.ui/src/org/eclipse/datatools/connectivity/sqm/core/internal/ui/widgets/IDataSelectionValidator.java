/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.internal.ui.widgets;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * @author ledunnel
 */
public interface IDataSelectionValidator {

    public String isValid(IStructuredSelection selection);

}