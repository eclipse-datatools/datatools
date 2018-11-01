/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.internal.core.rte;

public final class EngineeringOptionCategory {
	private String name;
	private String id;
	private String description;
	
	public EngineeringOptionCategory(String id, String name, String desc) {
		this.name = name;
		this.id = id;
		this.description = desc;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
