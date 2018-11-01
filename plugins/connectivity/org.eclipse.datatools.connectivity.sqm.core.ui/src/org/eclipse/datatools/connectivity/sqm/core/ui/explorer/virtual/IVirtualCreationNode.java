/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.resource.ImageDescriptor;

public interface IVirtualCreationNode extends IVirtualNode {
	/**
	 * Get the image descriptors for the Create menu.
	 * @return An array of ImageDescriptor objects.
	 */
	public ImageDescriptor[] getCreateImageDescriptor();

	/**
	 * Get the labels for the Create menu.
	 * @return An array of String objects.
	 */
	public String[] getCreateLabel();

	/**
	 * Get the types of objects to be created for the Create menu.
	 * @return An array of EClass objects.
	 */
	public EClass[] getCreateType();

	/**
	 * Returns true if the Create menu should be displayed as Add.
	 * @return boolean
	 */
	public boolean shouldDisplayAdd();

	/**
	 * Returns true if the Create menu should be displayed as Create.
	 * @return boolean
	 */
	public boolean shouldDisplayCreate();

}
