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
 * Defines a design session requested by an ODA host designer.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#getDataAccessDesign <em>Data Access Design</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#isEditable <em>Editable</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#getSessionLocale <em>Session Locale</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#getDesignerState <em>Designer State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignSessionRequest()
 * @model 
 * @generated
 */
public interface DesignSessionRequest extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Data Access Design</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Access Design</em>' containment reference.
     * @see #setDataAccessDesign(DataAccessDesign)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignSessionRequest_DataAccessDesign()
     * @model containment="true" resolveProxies="false" required="true"
     * @generated
     */
    DataAccessDesign getDataAccessDesign();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#getDataAccessDesign <em>Data Access Design</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Access Design</em>' containment reference.
     * @see #getDataAccessDesign()
     * @generated
     */
    void setDataAccessDesign( DataAccessDesign value );

    /**
     * Returns the value of the '<em><b>Editable</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Specifies whether the design session is requested to be in an "editable" or "read-only" mode.  For a read-only design session, the ODA host designer would ignore any changes found in the session response.  It is up to individual ODA designer to honor the requested mode and adjusts its UI behavior.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Editable</em>' attribute.
     * @see #isSetEditable()
     * @see #unsetEditable()
     * @see #setEditable(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignSessionRequest_Editable()
     * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     * @generated
     */
    boolean isEditable();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#isEditable <em>Editable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Editable</em>' attribute.
     * @see #isSetEditable()
     * @see #unsetEditable()
     * @see #isEditable()
     * @generated
     */
    void setEditable( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#isEditable <em>Editable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetEditable()
     * @see #isEditable()
     * @see #setEditable(boolean)
     * @generated
     */
    void unsetEditable();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#isEditable <em>Editable</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Editable</em>' attribute is set.
     * @see #unsetEditable()
     * @see #isEditable()
     * @see #setEditable(boolean)
     * @generated
     */
    boolean isSetEditable();

    /**
     * Returns the value of the '<em><b>Session Locale</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The suggested locale of the requested design session; normally the client locale of a locale-sensitive host designer.  It is up to an ODA designer whether it can support and honor the suggested locale.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Session Locale</em>' containment reference.
     * @see #setSessionLocale(Locale)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignSessionRequest_SessionLocale()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    Locale getSessionLocale();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#getSessionLocale <em>Session Locale</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Session Locale</em>' containment reference.
     * @see #getSessionLocale()
     * @generated
     */
    void setSessionLocale( Locale value );

    /**
     * Returns the value of the '<em><b>Designer State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The private state of the ODA designer at the last design session.  It can be used by an ODA designer to resume the state of its last session.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Designer State</em>' containment reference.
     * @see #setDesignerState(DesignerState)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignSessionRequest_DesignerState()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    DesignerState getDesignerState();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#getDesignerState <em>Designer State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Designer State</em>' containment reference.
     * @see #getDesignerState()
     * @generated
     */
    void setDesignerState( DesignerState value );

} // DesignSessionRequest
