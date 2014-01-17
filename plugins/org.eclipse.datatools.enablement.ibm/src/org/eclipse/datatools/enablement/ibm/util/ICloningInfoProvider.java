/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import org.eclipse.emf.ecore.EObject;

public interface ICloningInfoProvider 
{
  /* Returns a boolean which indicates whether the object 
   * passed in should have its containment hierarchy also cloned
   * when the object itself is being cloned becuase it is externally
   * referenced by another object that is being cloned. */
  public boolean cloneContainmentHierarchyOnExtRef(EObject obj);

}
