/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import org.eclipse.datatools.sqltools.core.modelvalidity.DefaultSQLModelValidator;

/**
 * Validator for Adaptive Server Anywhere constraints
 * 
 * @author Idull
 */
public class SybaseASABaseConstraintValidator extends DefaultSQLModelValidator
{
    public static final String PK_VALIDATION_TYPE  = "vpk";
    public static final String FK_VALIDATION_TYPE  = "vfk";
    public static final String UNI_VALIDATION_TYPE = "vunique";
}
