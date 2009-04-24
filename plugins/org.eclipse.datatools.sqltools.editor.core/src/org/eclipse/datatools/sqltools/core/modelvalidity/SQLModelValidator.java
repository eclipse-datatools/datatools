/**
 * Created on 2006-11-16
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.core.modelvalidity;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * Abstract class for SQL model validator
 * 
 * @author Idull
 */
public abstract class SQLModelValidator extends EObjectValidator
{
    public static final int EMPTY_IDENTIFIER      = 1000;
    public static final int INVALID_IDENTIFIER    = 1001;
    public static final int DUPLICATE_IDENTIFIER  = 1002;

    public static final int INVALID_DEFAULT_VALUE = 1100;
    public static final int CANT_ALLOW_DUPLICATED_ROW = 1101;
    public static final int CANT_ALLOW_NULLABLE   = 1102;

    /**
     * Validate the validity of the given validation item which belongs the given SQL object
     * 
     * @param eObject the SQL object
     * @param item the validation
     * @param diagnostics the diagnostic chain
     * @param sharedParams shared parameters for all the containing features
     * @return <code>true</code> if the validation succeeds
     */
    public abstract boolean validate(EObject eObject, IValidationItem item, DiagnosticChain diagnostics, Map sharedParams);
}
