/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
