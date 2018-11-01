/*******************************************************************************
 * Copyright (c) 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual;

import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;

public interface ICreationInfoProvider {

	boolean isCreateActionSupported(IVirtualNode virtualNode);

	boolean isCreateDistinctType();

	boolean isCreateRowTypeSupported();

}
