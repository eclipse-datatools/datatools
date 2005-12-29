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
 * A dynamic set of selectable input values to be retrieved at runtime using the query defined in a data set design.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#getDataSetDesign <em>Data Set Design</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#getValueColumn <em>Value Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#getDisplayNameColumn <em>Display Name Column</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDynamicValuesQuery()
 * @model 
 * @generated
 */
public interface DynamicValuesQuery extends EObject{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Data Set Design</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A nested instance of dataSetDesign to use for retrieving the set of selectable user values.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Data Set Design</em>' containment reference.
     * @see #setDataSetDesign(DataSetDesign)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDynamicValuesQuery_DataSetDesign()
     * @model containment="true" resolveProxies="false" required="true"
     * @generated
     */
    DataSetDesign getDataSetDesign();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#getDataSetDesign <em>Data Set Design</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Set Design</em>' containment reference.
     * @see #getDataSetDesign()
     * @generated
     */
    void setDataSetDesign( DataSetDesign value );

    /**
     * Returns the value of the '<em><b>Enabled</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * If not enabled, use staticValueChoices if defined.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Enabled</em>' attribute.
     * @see #isSetEnabled()
     * @see #unsetEnabled()
     * @see #setEnabled(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDynamicValuesQuery_Enabled()
     * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     * @generated
     */
    boolean isEnabled();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#isEnabled <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Enabled</em>' attribute.
     * @see #isSetEnabled()
     * @see #unsetEnabled()
     * @see #isEnabled()
     * @generated
     */
    void setEnabled( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#isEnabled <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetEnabled()
     * @see #isEnabled()
     * @see #setEnabled(boolean)
     * @generated
     */
    void unsetEnabled();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#isEnabled <em>Enabled</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Enabled</em>' attribute is set.
     * @see #unsetEnabled()
     * @see #isEnabled()
     * @see #setEnabled(boolean)
     * @generated
     */
    boolean isSetEnabled();

    /**
     * Returns the value of the '<em><b>Value Column</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The result set column name whose values are retrieved as the value choices.  This attribute must be one of the column names defined in the data set design's primary result set.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Value Column</em>' attribute.
     * @see #setValueColumn(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDynamicValuesQuery_ValueColumn()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * @generated
     */
    String getValueColumn();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#getValueColumn <em>Value Column</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Column</em>' attribute.
     * @see #getValueColumn()
     * @generated
     */
    void setValueColumn( String value );

    /**
     * Returns the value of the '<em><b>Display Name Column</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The result set column name whose values are the localized name that describes the corresponding value in the valueColumn on each data row.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Display Name Column</em>' attribute.
     * @see #setDisplayNameColumn(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDynamicValuesQuery_DisplayNameColumn()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getDisplayNameColumn();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery#getDisplayNameColumn <em>Display Name Column</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Name Column</em>' attribute.
     * @see #getDisplayNameColumn()
     * @generated
     */
    void setDisplayNameColumn( String value );

} // DynamicValuesQuery
