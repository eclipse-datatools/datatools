/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design;

/**
 * <!-- begin-user-doc -->
 * <p>
 * <strong>EXPERIMENTAL</strong>.
 * </p>
 * A representation of the model object '<em><b>And Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A built-in composite filter expression whose child expressions are combined by the And boolean operator.  The composite expression is evaluated to be true only if all its child expressions are evaluated as true.  
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAndExpression()
 * @since 3.2 (DTP 1.7)
 * @model extendedMetaData="name='AndExpression' kind='elementOnly'"
 * @generated
 */
public interface AndExpression extends CompositeFilterExpression
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

} // AndExpression
