/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.datatools.connectivity.ui.wizards.IProfileWizardProvider;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.swt.graphics.Point;

/**
 * @author shongxum
 */
public class CPWizardNode implements IWizardNode {

	private IProfileWizardProvider provider;
	private IWizard wizard;

	/**
	 * @param provider
	 */
	public CPWizardNode(IProfileWizardProvider provider) {
		this.provider = provider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizardNode#dispose()
	 */
	public void dispose() {
		if (wizard != null)
			wizard.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizardNode#getExtent()
	 */
	public Point getExtent() {
		return new Point(-1, -1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizardNode#getWizard()
	 */
	public IWizard getWizard() {
		if (wizard == null)
			wizard = createWizard();
		return wizard;
	}
	
	protected IWizard createWizard() {
		return provider.getWizard();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizardNode#isContentCreated()
	 */
	public boolean isContentCreated() {
		if (wizard == null)
			return false;
		else
			return true;
	}

	/**
	 * @return Returns the provider.
	 */
	public IProfileWizardProvider getProvider() {
		return provider;
	}
}
