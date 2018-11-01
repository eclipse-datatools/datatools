/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: plevin - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.util.List;

/**
 * Specifies bahavior for a datasource that client wizards use to communicate
 * summary information to summary page.
 * 
 * @author plevin
 */
public interface ISummaryDataSource {

	/**
	 * Returns key-value summary data.
	 * 
	 * @return Summary data.
	 */
	public List getSummaryData();
}
