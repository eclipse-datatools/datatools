/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: DesignSessionRequest.java,v 1.5 2006/03/09 08:50:09 lchan Exp $
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
 * @model extendedMetaData="name='DesignSessionRequest' kind='elementOnly'"
 * @generated
 */
public interface DesignSessionRequest extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Obtains the Data Source Design associated with the
     * top level Data Set in this Request session.
     * @return the value of the '<em>Data Source Design</em>' containment reference;
     *          may be null if none is specified.
     * @see #getDataAccessDesign()
     * @generated NOT
     */
    DataSourceDesign getDataSourceDesign();

    /**
     * Obtains the top-level Data Set Design associated with this
     * Request session.
     * @return the value of the '<em>Data Set Design</em>' containment reference;
     *          may be null if none is specified.
     * @see #getDataAccessDesign()
     * @generated NOT
     */
    DataSetDesign getDataSetDesign();

    /**
     * Returns the value of the '<em><b>Data Access Design</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Access Design</em>' containment reference.
     * @see #setDataAccessDesign(DataAccessDesign)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDesignSessionRequest_DataAccessDesign()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='dataAccessDesign' namespace='##targetNamespace'"
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
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#getDataAccessDesign <em>Data Access Design</em>}' containment reference
     * with a new data access design that contains a
     * new data set associated with given data source design.
     * @param dataSourceDesign
     * @see #setDataAccessDesign(DataAccessDesign)
     * @generated NOT
     */
    void setNewDataAccessDesign( DataSourceDesign dataSourceDesign );

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest#getDataAccessDesign <em>Data Access Design</em>}' containment reference
     * with a new data access design that contains the
     * specified data set design.
     * @param dataSetDesign
     * @see #setDataAccessDesign(DataAccessDesign)
     * @generated NOT
     */
    void setNewDataAccessDesign( DataSetDesign dataSetDesign );

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
     *        extendedMetaData="kind='element' name='editable' namespace='##targetNamespace'"
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
     * @model containment="true"
     *        extendedMetaData="kind='element' name='sessionLocale' namespace='##targetNamespace'"
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
     * @model containment="true"
     *        extendedMetaData="kind='element' name='designerState' namespace='##targetNamespace'"
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
