/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: AndExpression.java,v 1.1 2009/01/30 00:23:57 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>And Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A built-in composite filter expression whose child expressions are combined by the And boolean operator.  The composite expression is evaluated to be true only if all its child expressions are evaluated as true.  
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAndExpression()
 * @since 3.3 (DTP 1.8)
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
