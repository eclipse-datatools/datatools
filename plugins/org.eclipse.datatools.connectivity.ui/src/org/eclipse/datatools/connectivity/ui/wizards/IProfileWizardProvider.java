/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.graphics.Image;

/**
 * @author shongxum
 */
public interface IProfileWizardProvider {

	/**
	 * @return Returns the icon.
	 */
	public abstract ImageDescriptor getIcon();

	/**
	 * In order to reduce resources' leak
	 * 
	 * @return Image
	 */
	public abstract Image getCachedIcon();

	/**
	 * @return Returns the id.
	 */
	public abstract String getId();

	/**
	 * @return Returns the name.
	 */
	public abstract String getName();

	/**
	 * @return Returns the description
	 */
	public abstract String getDescription();

	/**
	 * @return Returns the category id
	 */
	public abstract String getCategory();

	/**
	 * Note: do not cache IWizard!!!
	 * 
	 * @return Returns the wizard.
	 */
	public abstract IWizard getWizard();
}
