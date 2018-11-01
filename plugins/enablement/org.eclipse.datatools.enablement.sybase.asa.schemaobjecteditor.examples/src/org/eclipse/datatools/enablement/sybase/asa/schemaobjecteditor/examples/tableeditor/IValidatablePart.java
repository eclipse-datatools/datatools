/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor;

import org.eclipse.swt.events.TypedEvent;

/**
 * Represents a validatable part
 * 
 * @author Idull
 */
public interface IValidatablePart
{
    /**
     * Validate this part, and may set error message accordingly
     * 
     * @param event
     */
    public void validatePart(TypedEvent event);
}
