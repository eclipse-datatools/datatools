/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.datatools.connectivity.ui.wizards.IProfileWizardProvider;
import org.eclipse.jface.wizard.IWizard;

public class CPCategoryWizardNode extends CPWizardNode {

	public CPCategoryWizardNode(IProfileWizardProvider provider) {
		super(provider);
	}

	protected IWizard createWizard() {
		return new NewCategoryWizard();
	}

}
