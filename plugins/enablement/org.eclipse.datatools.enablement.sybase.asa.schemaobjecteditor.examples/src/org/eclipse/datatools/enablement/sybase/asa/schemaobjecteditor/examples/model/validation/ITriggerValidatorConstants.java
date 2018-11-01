/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;
/**
 * 
 * @author Hui Cao
 * 
 */
public interface ITriggerValidatorConstants
{
    public static final int   NO_TRIGGER_EVENT        = 10001;
    public static final int   NO_TRIGGER_TABLE        = 10002;
    public static final int   DUPLICATE_TRIGGER_NAME_EVENT = 10003;
    public static final int   DUPLICATE_TRIGGER_TYPE_EVENT = 10004;
    public static final int   NO_TRIGGER_COLUMN = 10005;

    final public static byte INSERT_EVENT            = 1;
    final public static byte DELETE_EVENT            = 2;
    final public static byte UPDATE_EVENT            = 4;
    final public static byte UPDATE_COLUMN_EVENT     = 8;
    
}
