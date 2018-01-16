/*******************************************************************************
 * Copyright (c) 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;

public interface ICreationInfo {
	boolean isCreateActionSupported(DatabaseDefinition dbdef);
	boolean isCreateDistinctTypeSupported(DatabaseDefinition dbdef);
	boolean isCreateRowTypeSupported(DatabaseDefinition dbdef);
}
