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

import org.eclipse.jface.wizard.IWizardPage;

/**
 * Specifies the behaivour of a wizard that can determine which page will be
 * next in the sequence.
 * 
 * Used in conjuction with <cide>ISkippable</code>.
 * 
 * @author shongxum
 */
public interface IMediator {

	/**
	 * Mediates the wizard page with respect to which page will be next in the
	 * sequence.
	 * 
	 * @param wizardPage Mediated page
	 */
	public void mediatePage(IWizardPage wizardPage);
}
