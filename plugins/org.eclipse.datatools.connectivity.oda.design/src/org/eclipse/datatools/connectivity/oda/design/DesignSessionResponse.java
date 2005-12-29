/**
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines a design session response to an ODA host designer upon exit of the session.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#getSessionStatus <em>Session Status</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#getDataAccessDesign <em>Data Access Design</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#getDesignerState <em>Designer State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignSessionResponse()
 * @model 
 * @generated
 */
public interface DesignSessionResponse extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Session Status</b></em>' attribute.
     * The default value is <code>"Ok"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.SessionStatus}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * ODA design session exit status.  This tells the ODA host designer whether the design session was successful, and how it may consume and save the session response information.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Session Status</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.SessionStatus
     * @see #isSetSessionStatus()
     * @see #unsetSessionStatus()
     * @see #setSessionStatus(SessionStatus)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignSessionResponse_SessionStatus()
     * @model default="Ok" unique="false" unsettable="true" required="true"
     * @generated
     */
    SessionStatus getSessionStatus();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#getSessionStatus <em>Session Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Session Status</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.SessionStatus
     * @see #isSetSessionStatus()
     * @see #unsetSessionStatus()
     * @see #getSessionStatus()
     * @generated
     */
    void setSessionStatus( SessionStatus value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#getSessionStatus <em>Session Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetSessionStatus()
     * @see #getSessionStatus()
     * @see #setSessionStatus(SessionStatus)
     * @generated
     */
    void unsetSessionStatus();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#getSessionStatus <em>Session Status</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Session Status</em>' attribute is set.
     * @see #unsetSessionStatus()
     * @see #getSessionStatus()
     * @see #setSessionStatus(SessionStatus)
     * @generated
     */
    boolean isSetSessionStatus();

    /**
     * Returns the value of the '<em><b>Data Access Design</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The latest data access design as updated during the design session.  Any updates should be made in own instance, separate from the instance provided in the design session request.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Data Access Design</em>' containment reference.
     * @see #setDataAccessDesign(DataAccessDesign)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignSessionResponse_DataAccessDesign()
     * @model containment="true" resolveProxies="false" required="true"
     * @generated
     */
    DataAccessDesign getDataAccessDesign();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#getDataAccessDesign <em>Data Access Design</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Access Design</em>' containment reference.
     * @see #getDataAccessDesign()
     * @generated
     */
    void setDataAccessDesign( DataAccessDesign value );

    /**
     * Returns the value of the '<em><b>Designer State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The private state of the ODA designer upon exit of a design session.  An ODA host designer must handle it as a black-box; it should simply provide persistence services and pass it back in the next session request.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Designer State</em>' containment reference.
     * @see #setDesignerState(DesignerState)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignSessionResponse_DesignerState()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    DesignerState getDesignerState();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse#getDesignerState <em>Designer State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Designer State</em>' containment reference.
     * @see #getDesignerState()
     * @generated
     */
    void setDesignerState( DesignerState value );

} // DesignSessionResponse
