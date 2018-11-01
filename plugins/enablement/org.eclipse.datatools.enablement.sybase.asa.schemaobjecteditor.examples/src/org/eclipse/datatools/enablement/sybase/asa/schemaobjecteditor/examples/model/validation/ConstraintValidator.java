/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;
/**
 * @author David Cui
 */
import org.eclipse.datatools.sqltools.core.modelvalidity.DefaultSQLModelValidator;
import org.eclipse.datatools.sqltools.core.modelvalidity.ValidatorConstants;

public abstract class ConstraintValidator extends DefaultSQLModelValidator
{
    public static final String DATABASE_IDENTIFIER = ValidatorConstants.DATABASE_IDENTIFIER;          //$NON-NLS-1$
    public static final String DEFAULT_BASE_TABLE  = "constraint.default_base_table"; //$NON-NLS-1$
	public static final String NEED_VALIDATE_SPECIAL_DATE_TYPE = "validate_spec_type";
	public static final String IMMU_CATALOG = "immu_catalog";
}
