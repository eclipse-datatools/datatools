/*******************************************************************************
 * Copyright (c) 2011, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import org.eclipse.emf.ecore.EObject;

public interface IPostprocessProvider 
{
	/**
	 * Namespace post processing for EMF EObjects.
	 * Extension providers must perform any post processing on the provided objects if it is
	 * determined that post processing is required.  Modified EObjects, if any, will be be done
	 * on same array passed in.
	 * @param objs array of EObjects to process
	 * @return true if post processing is done, false if not
	 */
	public boolean postProcess(EObject[] objs);
}
