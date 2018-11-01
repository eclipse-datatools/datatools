/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.core.dbitem;


/**
 * Listens for parameter changes.
 * @author Zhi-hong(Bryan) Yang
 */
public interface IParamListener
{
	/**
	 * The value of the parameter specified by <code>pd</code> has been
	 * updated to <code>value</code>.
	 * 
	 * @param pd
	 * @param value
	 */
    public void paramValueUpdated(ParameterDescriptor pd, String value);

}
