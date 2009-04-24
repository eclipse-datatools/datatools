/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor;

/**
 * 
 * @author Idull
 */
public interface ICuttablePart
{
    /**
     * Warning: Cut operation MUST proceed after this method is called and returns <code>true</code>, otherwise internal changes can not recover
     * 
     * @return <code>true</code> if preparation succeeds, and can proceed cut operation.
     */
    public boolean prepareCut();
}
