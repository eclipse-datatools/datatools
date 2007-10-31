/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.rte;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public interface DDLParser {
	public Database[] parse(String fileName, Database[] existingDBs, IProgressMonitor progressMonitor);
	public String[] getParserMessages();
	public boolean isIncrementalSupported();
}
