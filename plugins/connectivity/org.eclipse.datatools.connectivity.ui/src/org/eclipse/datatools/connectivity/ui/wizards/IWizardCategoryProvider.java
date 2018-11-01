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
package org.eclipse.datatools.connectivity.ui.wizards;


/**
 * @author shongxum
 */
public interface IWizardCategoryProvider extends IProfileWizardProvider {

	/**
	 * @return Returns the wizard title
	 */
	public abstract String getWizardTitle();

	/**
	 * @return Returns the wizard description
	 */
	public abstract String getWizardDescription();

}
