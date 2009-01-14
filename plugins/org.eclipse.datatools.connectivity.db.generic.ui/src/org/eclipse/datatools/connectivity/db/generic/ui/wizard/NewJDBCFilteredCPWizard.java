/*******************************************************************************
 * Copyright (c) 2009 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.db.generic.ui.wizard;

import org.eclipse.datatools.connectivity.db.generic.IDBConnectionProfileConstants;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizardCategoryFilter;
import org.eclipse.datatools.connectivity.ui.wizards.NewFilteredCPWizard;

/**
 * Simple class that filters out everything but Database profile wizards.
 * @author brianf
 */
public class NewJDBCFilteredCPWizard extends NewFilteredCPWizard {

	public NewJDBCFilteredCPWizard() {
		super(new NewCPWizardCategoryFilter(
				IDBConnectionProfileConstants.DATABASE_CATEGORY_ID),
				null);
	}

}
