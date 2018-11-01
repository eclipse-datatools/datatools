/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui;

/**
 * A resource change handler is notified to do some cleaning job when the editing resource is changed, renamed or
 * deleted.
 * 
 * @author Idull
 */
public interface IResourceChangeHandler
{
    /**
     * The consumer need to call this method when the resouce is changed outside of the schema editor's scope
     * 
     */
    public void resouceChanged(IResourceChangeEvent event);
}
