/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.ui.services;


/**
 * @author ljulien
 */
public interface ISorterValidatorProvider 
{
    public boolean isNotValid (Object element1, Object element2);
    public boolean shouldCompare (Object element1, Object element2);
    public int compareTo (Object element1, Object element2);
}
