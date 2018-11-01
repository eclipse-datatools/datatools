/*******************************************************************************
 * Copyright (c) 2010, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.ddl;

public class LUWDeltaDdlGenerator95 extends LUWDeltaDdlGenerator9 {

	public LUWDeltaDdlGenerator95() {
		super();
		builder = new LUWDdlBuilder95();
	}
}
