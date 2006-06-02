/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
