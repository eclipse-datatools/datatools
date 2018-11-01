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

/**
 * Allows the wizard pages to listen to the event of switching of the page.
 * 
 * @author shongxum
 */
public interface IWizardAction {

	/**
	 * Called just before switching the page.
	 * 
	 * @return boolean
	 */
	public boolean onWizardNext();

	/**
	 * Called before showing this page.
	 * 
	 * @return boolean
	 */
	public void onSetActive();
}
