/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.model;

import org.eclipse.emf.common.notify.Notification;

/**
 * Implementor of this class will be notified when the model change event occurs
 * 
 * @author Idull
 */
public interface ISchemaObjectEditorModelListener
{
    /**
     * The model is changed, the detailed information is contained in the <code>Notification</code> instance.
     * <p>
     * Implementors should filter the notifications
     * 
     * @param msg the notification
     */
    public void notifyChanged(Notification msg);
}
