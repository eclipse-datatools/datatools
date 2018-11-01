/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl;

import java.text.MessageFormat;

import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IDecoration;

/**
 * @author ljulien
 */
public class DependencyDecorationService extends AbstractDecorationService
{
    private static final String TARGET_NAME = " --> ({0})"; //$NON-NLS-1$

    private void decorate (Dependency dependency, IDecoration decoration)
    {
        EObject eObject = dependency.getTargetEnd();
        if (eObject instanceof ENamedElement)
        {
            decoration.addSuffix(MessageFormat.format(TARGET_NAME, new String [] {((ENamedElement)eObject).getName()}));
        }
    }
    
	/**
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object, org.eclipse.jface.viewers.IDecoration)
	 */
	public void decorate(Object object, IDecoration decoration)
	{
	    if (object instanceof Dependency)
	    {
	        decorate ((Dependency)object, decoration);
	    }
	}
}
