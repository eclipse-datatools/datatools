/**
 *************************************************************************
 * Copyright (c) 2005, 2010 Actuate Corporation.
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
 * $Id: AxisAttributes.java,v 1.3 2009/07/23 21:43:17 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Multi-dimensional attributes of a data element, such as a result set column.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#getAxisType <em>Axis Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#isOnColumnLayout <em>On Column Layout</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#getRelatedColumns <em>Related Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAxisAttributes()
 * @model extendedMetaData="name='AxisAttributes' kind='elementOnly'"
 * @generated
 */
public interface AxisAttributes extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Axis Type</b></em>' attribute.
     * The default value is <code>"Measure"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.AxisType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The axis type of the data element.  It provides design hints to a consumer application on how the column data should be analyzed, or applied in a multi-dimensional view.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Axis Type</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.AxisType
     * @see #isSetAxisType()
     * @see #unsetAxisType()
     * @see #setAxisType(AxisType)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAxisAttributes_AxisType()
     * @model default="Measure" unsettable="true"
     *        extendedMetaData="kind='element' name='axisType' namespace='##targetNamespace'"
     * @generated
     */
    AxisType getAxisType();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#getAxisType <em>Axis Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Axis Type</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.AxisType
     * @see #isSetAxisType()
     * @see #unsetAxisType()
     * @see #getAxisType()
     * @generated
     */
    void setAxisType( AxisType value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#getAxisType <em>Axis Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetAxisType()
     * @see #getAxisType()
     * @see #setAxisType(AxisType)
     * @generated
     */
    void unsetAxisType();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#getAxisType <em>Axis Type</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Axis Type</em>' attribute is set.
     * @see #unsetAxisType()
     * @see #getAxisType()
     * @see #setAxisType(AxisType)
     * @generated
     */
    boolean isSetAxisType();

    /**
     * Returns the value of the '<em><b>On Column Layout</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A design hint on whether the dimension data element should be laid out on a column or row.  It is normally used by presentation element such as a crosstab to design a default layout.
     * <!-- end-model-doc -->
     * @return the value of the '<em>On Column Layout</em>' attribute.
     * @see #isSetOnColumnLayout()
     * @see #unsetOnColumnLayout()
     * @see #setOnColumnLayout(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAxisAttributes_OnColumnLayout()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='onColumnLayout' namespace='##targetNamespace'"
     * @generated
     */
    boolean isOnColumnLayout();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#isOnColumnLayout <em>On Column Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>On Column Layout</em>' attribute.
     * @see #isSetOnColumnLayout()
     * @see #unsetOnColumnLayout()
     * @see #isOnColumnLayout()
     * @generated
     */
    void setOnColumnLayout( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#isOnColumnLayout <em>On Column Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetOnColumnLayout()
     * @see #isOnColumnLayout()
     * @see #setOnColumnLayout(boolean)
     * @generated
     */
    void unsetOnColumnLayout();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#isOnColumnLayout <em>On Column Layout</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>On Column Layout</em>' attribute is set.
     * @see #unsetOnColumnLayout()
     * @see #isOnColumnLayout()
     * @see #setOnColumnLayout(boolean)
     * @generated
     */
    boolean isSetOnColumnLayout();

    /**
     * Returns the value of the '<em><b>Related Columns</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The result set column(s) that are related to this data element.  For example, for a dimension member AxisType element, its related column(s) are those modeled to be its attribute. Multiple related columns may be combined to be a compounded attribute of a dimension.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Related Columns</em>' containment reference.
     * @see #setRelatedColumns(ResultSubset)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAxisAttributes_RelatedColumns()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='relatedColumns' namespace='##targetNamespace'"
     * @generated
     * @since 3.3.2
     */
    ResultSubset getRelatedColumns();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#getRelatedColumns <em>Related Columns</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Related Columns</em>' containment reference.
     * @see #getRelatedColumns()
     * @generated
     * @since 3.3.2
     */
    void setRelatedColumns( ResultSubset value );

} // AxisAttributes
