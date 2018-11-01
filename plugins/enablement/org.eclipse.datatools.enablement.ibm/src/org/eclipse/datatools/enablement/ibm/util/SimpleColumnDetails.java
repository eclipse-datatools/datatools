/*******************************************************************************
 * Copyright (c) 2002, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SimpleColumnDetails {
	public String name;
	public String type;
	public int length;
	public int scale;

	public SimpleColumnDetails(Column col) {
		this.name = col.getName();
		DataType type = col.getDataType();
		this.type = type.getName();
		EStructuralFeature f = type.eClass().getEStructuralFeature("length"); //$NON-NLS-1$
		if (f != null) {
			this.length = ((Integer) type.eGet(f)).intValue();
		}
		f = type.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
		if (f != null) {
			this.length = ((Integer) type.eGet(f)).intValue();
		}
		f = type.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
		if (f != null) {
			this.scale = ((Integer) type.eGet(f)).intValue();
		}
	}
}
