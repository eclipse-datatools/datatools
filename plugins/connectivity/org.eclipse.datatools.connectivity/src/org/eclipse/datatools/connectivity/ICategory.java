/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import java.util.List;

/**
 * This interface is used for accessing functionality specific to connection
 * profile categories.
 * 
 * @author rcernich
 * 
 * Created on Jan 15, 2004
 */
public interface ICategory {

	/**
	 * @return the id of this category
	 */
	String getId();

	/**
	 * @return the name of this category
	 */
	String getName();

	/**
	 * @return the parent of this category; null if no parent exists.
	 */
	ICategory getParent();

	/**
	 * Returns a List of ICategory objects that are direct children of this
	 * category.
	 * 
	 * @return all child categories of this category
	 */
	List getChildCategories();

	/**
	 * Returns a List of IConnectionProfile objects that are direct children of
	 * this category
	 * 
	 * @return all connection profiles within the specified container.
	 */
	List getAssociatedProfiles();
}
